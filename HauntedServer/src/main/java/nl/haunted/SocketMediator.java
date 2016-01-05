/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.haunted;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import static java.lang.System.out;
import java.net.DatagramPacket;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Mal
 */
public class SocketMediator implements Serializable {

    MulticastSocket UDPsocket;
    ServerSocket TCPServerSocket;
    Socket TCPSocket;
    InetAddress groupIp;
    NetworkInterface nic;
    Object[][] object;
    int port;
    List<String> messages = new ArrayList();
    String IP = "";
    List<String[]> inputArray = new ArrayList();

    public void socketSetup(String groupname, int port, String type) throws IOException {
        groupIp = InetAddress.getByName(groupname);
        Scanner input = new Scanner(System.in);
        nic = this.getLocalNIC(); /* this.getLoopbackNick(); this.getInternetNIC(); //commented for futuure over internet support */

        if (nic == null) {
            listNics();
            System.out.println("What Network interface do you want to connect with?");
            nic = NetworkInterface.getByName(input.nextLine());
        }
        IP = nic.getInetAddresses().nextElement().getHostAddress();
        object = null;
        this.port = port;
        switch (type) {
            case "TCPC":
                TCPSocket = new Socket("client", port);
                break;
            case "TCPS":
                TCPServerSocket = new ServerSocket(port);
                TCPSocket = TCPServerSocket.accept();
                break;
            case "UDP":
                UDPsocket = new MulticastSocket(port);
                if (port == 9877) {
                    UDPsocket.setLoopbackMode(false);
                } else {
                    UDPsocket.setLoopbackMode(true); // turn to true for dedicated server
                }
                UDPsocket.joinGroup(new InetSocketAddress(groupIp, port), nic);
                break;
        }

    }

    public NetworkInterface getNIC() {
        return nic;
    }

    public String getIPAddress() {
        return this.IP;
    }

    public void sendObject(Object o) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(5000);
        try (ObjectOutputStream os = new ObjectOutputStream(new BufferedOutputStream(byteStream))) {
            os.flush();
            os.writeObject(o);
            os.flush();
            //retrieves byte array
            byte[] buf = byteStream.toByteArray();
            DatagramPacket packet = new DatagramPacket(
                    buf, buf.length, groupIp, 9876);
            int byteCount = packet.getLength();
            UDPsocket.send(packet);
        }
    }

    public void sendMessage(String m) throws IOException {
        byte[] buf = m.getBytes();
        DatagramPacket packet = new DatagramPacket(
                buf, buf.length, groupIp, 9877);
        UDPsocket.send(packet);

    }

    public void sendInput(String s) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(this.IP).append(":").append(s);
        try (PrintWriter out = new PrintWriter(TCPSocket.getOutputStream(), true)) {
            out.print(sb.toString());
        }
        out.close();
        TCPSocket.close();
    }

    public Object[][] getObject() {
        return this.object;
    }

    public List<String> getMessages() {
        List<String> tempMessages = new ArrayList();
        tempMessages.addAll(this.messages);
        this.messages.clear();
        return tempMessages;
    }

    public void receiveObject() throws IOException, ClassNotFoundException {
        byte[] recvBuf = new byte[5000];
        DatagramPacket packet = new DatagramPacket(recvBuf,
                recvBuf.length);
        UDPsocket.setSoTimeout(15);
        try {
            UDPsocket.receive(packet);
        } catch (IOException ex) {
        }
        int byteCount = packet.getLength();
        ByteArrayInputStream byteStream = new ByteArrayInputStream(recvBuf);
        Object o = null;
        try (ObjectInputStream is = new ObjectInputStream(new BufferedInputStream(byteStream))) {
            o = is.readObject();
        } catch (Exception ex) {

        }
        if (o != null) {
            this.object = (Object[][]) o;
        }

    }

    public void receiveMessage() throws IOException, ClassNotFoundException {
        byte[] recvBuf = new byte[300];
        DatagramPacket packet = new DatagramPacket(recvBuf,
                recvBuf.length);
        UDPsocket.setSoTimeout(5);
        try {
            UDPsocket.receive(packet);
        } catch (SocketTimeoutException ex) {
        }
        if (packet.getLength() < 300) {
            String s = new String(packet.getData(), 0, packet.getLength());
            this.messages.add(s);
        }
    }

    public void receiveInput() throws IOException, ClassNotFoundException {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(TCPSocket.getInputStream()))) {
            while (in.ready()) {
                String str = in.readLine();
                boolean found = false;
                if (!str.contains("  ")) {
                    for (String[] s : inputArray) {
                        if (s[0].equalsIgnoreCase(str.substring(0, str.indexOf(":")))) {
                            if (!s[1].equalsIgnoreCase(str.substring(str.indexOf(":") + 1))) {
                                s[1] = str.substring(str.indexOf(":") + 1);
                                
                            }
                            found = true;
                        }
                    }
                    if (!found) {
                        String[] newstr = new String[2];
                        newstr[0] = str.substring(0, str.indexOf(":"));
                        newstr[1] = str.substring(str.indexOf(":") + 1);
                        this.inputArray.add(newstr);
                    }
                }
            }
        }
        TCPSocket.close();
    }

    public List<String[]> getInputArray() {
        return Collections.unmodifiableList(this.inputArray);
    }

    public void close() throws IOException {
        UDPsocket.leaveGroup(groupIp);
        UDPsocket.close();
    }

    public void listNics() throws SocketException {
        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
        for (NetworkInterface netint : Collections.list(nets)) {
            if (!netint.isVirtual() && !netint.getDisplayName().toLowerCase().contains("vmware") && !netint.getDisplayName().toLowerCase().contains("6to4") && netint.isUp() && !netint.isLoopback()) {
                displayInterfaceInformation(netint);
            }
        }
    }

    public void displayInterfaceInformation(NetworkInterface netint) throws SocketException {
        out.printf("Display name: %s\n", netint.getDisplayName());
        out.printf("Name: %s\n", netint.getName());
        Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
        Collections.list(inetAddresses).stream().forEach((inetAddress) -> {
            out.printf("InetAddress: %s\n", inetAddress);
        });
        out.printf("\n");
    }

    public NetworkInterface getLoopbackNick() throws SocketException {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        OUTER:
        for (NetworkInterface interface_ : Collections.list(interfaces)) {
            if (interface_.isLoopback()) {
                return interface_;
            }
        }
        return null;
    }

    public NetworkInterface getInternetNIC() throws SocketException, IOException {
        // iterate over the network interfaces known to java
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        OUTER:
        for (NetworkInterface interface_ : Collections.list(interfaces)) {
            // we shouldn't care about loopback addresses
            if (interface_.isLoopback()) {
                continue;
            }

            // if you don't expect the interface to be up you can skip this
            // though it would question the usability of the rest of the code
            if (!interface_.isUp()) {
                continue;
            }

            // iterate over the addresses associated with the interface
            Enumeration<InetAddress> addresses = interface_.getInetAddresses();
            for (InetAddress address : Collections.list(addresses)) {
                // look only for ipv4 addresses
                if (address instanceof Inet6Address) {
                    continue;
                }

                // use a timeout big enough for your needs
                if (!address.isReachable(3000)) {
                    continue;
                }

                // java 7's try-with-resources statement, so that
                // we close the socket immediately after use
                try (SocketChannel socket = SocketChannel.open()) {
                    // again, use a big enough timeout
                    socket.socket().setSoTimeout(3000);

                    // bind the socket to your local interface
                    socket.bind(new InetSocketAddress(address, 8080));

                    // try to connect to *somewhere*
                    socket.connect(new InetSocketAddress("google.com", 80));
                } catch (IOException ex) {
                    continue;
                }

                return interface_;
            }
        }
        return null;
    }

    public NetworkInterface getLocalNIC() throws IOException {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        OUTER:
        for (NetworkInterface interface_ : Collections.list(interfaces)) {
            // we shouldn't care about loopback addresses
            if (interface_.isLoopback()) {
                continue;
            }

            // if you don't expect the interface to be up you can skip this
            // though it would question the usability of the rest of the code
            if (!interface_.isUp()) {
                continue;
            }

            // iterate over the addresses associated with the interface
            Enumeration<InetAddress> addresses = interface_.getInetAddresses();
            for (InetAddress address : Collections.list(addresses)) {
                // look only for ipv4 addresses
                if (address instanceof Inet6Address) {
                    continue;
                }

                // use a timeout big enough for your needs
                if (!address.isReachable(3000)) {
                    continue;
                }

                // java 7's try-with-resources statement, so that
                // we close the socket immediately after use
                if (address.toString().indexOf("10.1.3.") > 0) {
                    System.out.println(address.getHostAddress());
                    return interface_;
                }
            }
        }
        return null;
    }

}
