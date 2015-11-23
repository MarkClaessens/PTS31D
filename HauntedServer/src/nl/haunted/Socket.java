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
import static java.lang.System.out;
import java.net.DatagramPacket;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.nio.channels.SocketChannel;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Scanner;

/**
 *
 * @author Mal
 */
public class Socket {

    MulticastSocket sock;
    InetAddress groupIp;

    public void socketSetup(String groupname, int port) throws IOException {
        sock = new MulticastSocket(port);
        groupIp = InetAddress.getByName(groupname);
        Scanner input = new Scanner(System.in);
        NetworkInterface nic = null;//this.getnetworkinterface(); //commented for futuure over internet support
        if (nic == null) {
            listNics();
            System.out.println("What Network interface do you want to connect with?");
            nic = NetworkInterface.getByName(input.nextLine());
        }

        sock.joinGroup(new InetSocketAddress(groupIp, port), nic);
    }

    public void send(Object o) throws IOException {
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

    public Object[][] receive() throws IOException, ClassNotFoundException {
        byte[] recvBuf = new byte[5000];
        DatagramPacket packet = new DatagramPacket(recvBuf,
                recvBuf.length);
        sock.receive(packet);
        int byteCount = packet.getLength();
        ByteArrayInputStream byteStream = new ByteArrayInputStream(recvBuf);
        Object o;
        try (ObjectInputStream is = new ObjectInputStream(new BufferedInputStream(byteStream))) {
            o = is.readObject();
        }
        return (Object[][]) (o);
    }

    public void close() throws IOException {
        sock.leaveGroup(groupIp);
        sock.close();
    }

    public void listNics() throws SocketException {
        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
        for (NetworkInterface netint : Collections.list(nets)) {
            if (!netint.isVirtual()&& !netint.getDisplayName().toLowerCase().contains("vmware") && !netint.getDisplayName().toLowerCase().contains("6to4") && netint.isUp() && !netint.isLoopback()) {
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

    public NetworkInterface getnetworkinterface() throws SocketException, IOException {
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
}
