package nl.haunted;

import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.server.RMISocketFactory;

/**
 *
 * @author Mike Evers
 * @see http://scis.athabascau.ca/html/courses/comp489/mysql/rmi-firewall.htm
 */
public class MyClientRMISocketFactory extends RMISocketFactory implements Serializable {

    @Override
    public Socket createSocket(String host, int port) throws IOException {
        System.out.println("creating socket to host : " + host + " on port " + port);
        return new Socket(host, port); 
    }

    @Override
    public ServerSocket createServerSocket(int port) throws IOException {
        port = (port == 0 ? 8763 : port);
        System.out.println("creating ServerSocket on port " + port); 
        return new ServerSocket(port);
    }  
}
