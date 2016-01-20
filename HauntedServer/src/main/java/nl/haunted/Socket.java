/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.haunted;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import static java.lang.System.out;
import java.net.DatagramPacket;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
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
public class Socket implements Serializable {

    //variables for setup.
    NetworkInterface nic;
    MulticastSocket sock;
    InetAddress groupIp;
    int port;
    String IP = "";

    //temporary lists to read out.
    Object[][] object;
    List<String> messages = new ArrayList();
    List<String[]> inputArray = new ArrayList();

    /**
     * This function sets up the correct NIC, it requests a groupname and a
     * port.
     *
     * @param groupname
     * @param port
     * @throws IOException
     */
    public void socketSetup(String groupname, int port) throws IOException {
        this.sock = new MulticastSocket(port);
        this.nic = this.getLocalNIC();
        if (nic == null) {
            Scanner input = new Scanner(System.in);
            listNics();
            System.out.println("What Network interface do you want to connect with?");
            nic = NetworkInterface.getByName(input.nextLine());
        }

        //set IP
        Enumeration<InetAddress> addresses = nic.getInetAddresses();
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
                this.IP = address.toString();
            }
        }

        this.port = port;

        if (port == 9877) {
            sock.setLoopbackMode(false);
        } else {
            sock.setLoopbackMode(true); // turn to true for dedicated server
        }
        groupIp = InetAddress.getByName(groupname);

        sock.joinGroup(new InetSocketAddress(groupIp, port), nic);
        object = null;
    }

    /**
     * this returns the current network interface
     *
     * @return
     */
    public NetworkInterface getNIC() {
        return nic;
    }

    /**
     * This returns the current IPAddress.
     *
     * @return
     */
    public String getIPAddress() {
        return this.IP;
    }

    /**
     * This function lets you send an Object to the other computers connected to
     * the Multicastgroup. this function expects an two-dimensional ObjectArray
     * Object[][].
     *
     * @param o
     * @throws IOException
     */
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
            sock.send(packet);
        }
    }

    /**
     * This function lets you send a Message to the other computers.
     *
     * @param m
     * @throws IOException
     */
    public void sendMessage(String m) throws IOException {
        byte[] buf = m.getBytes();
        DatagramPacket packet = new DatagramPacket(
                buf, buf.length, groupIp, 9877);
        sock.send(packet);

    }

    /**
     * This function lets you send input from the client to the server.
     *
     * @param s
     * @param port
     * @throws IOException
     */
    public void sendInput(String s, int port) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(this.IP).append(":").append(s);
        byte[] buf = sb.toString().getBytes();
        DatagramPacket packet = new DatagramPacket(
                buf, buf.length, groupIp, port);
        sock.send(packet);
    }

    /**
     * This function returns an ObjectArray
     *
     * @return
     */
    public Object[][] getObject() {
        return this.object;
    }

    /**
     * This function returns all the messages stored.
     *
     * @return
     */
    public List<String> getMessages() {
        List<String> tempMessages = new ArrayList();
        tempMessages.addAll(this.messages);
        this.messages.clear();
        return tempMessages;
    }

    /**
     * This Function receives a two-dimensional ObjectArray and saves it in the
     * variable this.object
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void receiveObject() throws IOException, ClassNotFoundException {
        byte[] recvBuf = new byte[5000];
        DatagramPacket packet = new DatagramPacket(recvBuf,
                recvBuf.length);
        sock.setSoTimeout(15);
        try {
            sock.receive(packet);
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

    /**
     * This function receives a message from the socket and puts it in the
     * this.messages list.
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void receiveMessage() throws IOException, ClassNotFoundException {
        byte[] recvBuf = new byte[300];
        DatagramPacket packet = new DatagramPacket(recvBuf,
                recvBuf.length);
        sock.setSoTimeout(5);
        try {
            sock.receive(packet);
        } catch (SocketTimeoutException ex) {
        }
        if (packet.getLength() < 300) {
            String s = new String(packet.getData(), 0, packet.getLength());
            this.messages.add(s);
        }
    }

    /**
     * This function receives input from the socket and puts it in
     * this.inputArray variable.
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void receiveInput() throws IOException, ClassNotFoundException {
        byte[] recvBuf = new byte[1000];
        DatagramPacket packet = new DatagramPacket(recvBuf,
                recvBuf.length);
        sock.setSoTimeout(5);
        try {
            sock.receive(packet);
        } catch (SocketTimeoutException ex) {
        }
        if (packet.getLength() < 1000) {
            String str = new String(packet.getData(), 0, packet.getLength());
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

    /**
     * This function returns the saved this.inputArray variable.
     *
     * @return
     */
    public List<String[]> getInputArray() {
        return Collections.unmodifiableList(this.inputArray);
    }

    /**
     * This function closes the connections to the other computers.
     *
     * @throws IOException
     */
    public void close() throws IOException {
        sock.leaveGroup(groupIp);
        sock.close();
    }

    /**
     * This function prints a list of NICs
     *
     * @throws SocketException
     */
    public void listNics() throws SocketException {
        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
        for (NetworkInterface netint : Collections.list(nets)) {
            if (!netint.isVirtual() && !netint.getDisplayName().toLowerCase().contains("vmware") && !netint.getDisplayName().toLowerCase().contains("6to4") && netint.isUp() && !netint.isLoopback()) {
                displayInterfaceInformation(netint);
            }
        }
    }

    /**
     * This function Prints information from a NIC.
     *
     * @param netint
     * @throws SocketException
     */
    public void displayInterfaceInformation(NetworkInterface netint) throws SocketException {
        out.printf("Display name: %s\n", netint.getDisplayName());
        out.printf("Name: %s\n", netint.getName());
        Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
        Collections.list(inetAddresses).stream().forEach((inetAddress) -> {
            out.printf("InetAddress: %s\n", inetAddress);
        });
        out.printf("\n");
    }

    /**
     * This function returns the loopback network interface.
     *
     * @return
     * @throws SocketException
     */
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

    /**
     * this function returns a network interface that has an Internet
     * connection.
     *
     * @return
     * @throws SocketException
     * @throws IOException
     */
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

    /**
     * this function returns a network interface that is connected to a local
     * network.
     *
     * @return
     * @throws IOException
     */
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
