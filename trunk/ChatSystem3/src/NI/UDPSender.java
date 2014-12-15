package NI;

import signals.*;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

/**
 * The UDP Sender
 * @author Ayoub , Omar
 */
public class UDPSender extends Thread {

    private ArrayList<String> buffer;
    DatagramSocket dsocket;
    NI ni;
    int ports;

    /**
     * Creates a new UDPSender for the NI
     * @param ni
     * @param ports 
     */
    public UDPSender(NI ni, int ports) {
        buffer = new ArrayList<String>(20);
        this.ni = ni;
        this.ports = ports;
    }

    /**
     * Sends a Hello to the Remote UDPServer
     * @param hello
     * @param address
     * @param broadcast 
     */
    public void sendHello(Hello hello, InetAddress address, boolean broadcast) {

        try {
            //  String host = "localhost";
            int port = ports;
            //BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
            //String msg1 = is.readLine();
            //byte[] message = msg.getBytes();

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(4 * 1024);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(hello);
            byte[] message = byteArrayOutputStream.toByteArray();
            // Get the internet address of the specified host
            //InetAddress address = address;//InetAddress.getByName(host);
            // Initialize a datagram packet with data and address
            DatagramPacket packet = new DatagramPacket(message, message.length, address, port);
            dsocket = new DatagramSocket();
            dsocket.setBroadcast(broadcast);
            dsocket.send(packet);
            dsocket.close();

        } catch (Exception e) {
            System.err.println(e);
        }
    }

    /**
     * Sends a HelloOk to the Remote UDPServer
     * @param helloOk 
     * @param address
     * @param broadcast 
     */
    public void sendHelloOk(HelloOK helloOk, InetAddress address, boolean broadcast) {
        try {

            //  String host = "localhost";
            int port = ports;
            //BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
            //String msg1 = is.readLine();
            //byte[] message = msg.getBytes();

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(4 * 1024);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(helloOk);
            byte[] message = byteArrayOutputStream.toByteArray();
            // Get the internet address of the specified host
            //InetAddress address = address;//InetAddress.getByName(host);
            // Initialize a datagram packet with data and address
            DatagramPacket packet = new DatagramPacket(message, message.length, address, port);
            dsocket = new DatagramSocket();
            dsocket.setBroadcast(broadcast);
            dsocket.send(packet);
            dsocket.close();

        } catch (Exception e) {
            System.err.println(e);
        }
    }

    /**
     * Sends a GoodBye to the Remote UDPServer
     * @param goodbye 
     * @param address
     * @param broadcast 
     */
    public void sendGoodbye(Goodbye goodbye, InetAddress address, boolean broadcast) {
        try {

            //  String host = "localhost";
            int port = ports;
            //BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
            //String msg1 = is.readLine();
            //byte[] message = msg.getBytes();

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(4 * 1024);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(goodbye);
            byte[] message = byteArrayOutputStream.toByteArray();
            // Get the internet address of the specified host
            //InetAddress address = address;//InetAddress.getByName(host);
            // Initialize a datagram packet with data and address
            DatagramPacket packet = new DatagramPacket(message, message.length, address, port);
            dsocket = new DatagramSocket();
            dsocket.setBroadcast(broadcast);
            dsocket.send(packet);
            dsocket.close();

        } catch (Exception e) {
            System.err.println(e);
        }
    }

    /**
     * Sends a Message to the Remote UDPServer
     * @param msg 
     * @param address
     * @param broadcast 
     */
    public void sendMsg(TextMessage msg, InetAddress address, boolean broadcast) {

        try {

            //  String host = "localhost";
            int port = ports;
            //BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
            //String msg1 = is.readLine();
            //byte[] message = msg.getBytes();

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(4 * 1024);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(msg);
            byte[] message = byteArrayOutputStream.toByteArray();
            // Get the internet address of the specified host
            //InetAddress address = address;//InetAddress.getByName(host);
            // Initialize a datagram packet with data and address
            DatagramPacket packet = new DatagramPacket(message, message.length, address, port);
            dsocket = new DatagramSocket();
            dsocket.setBroadcast(broadcast);
            dsocket.send(packet);
            dsocket.close();

        } catch (Exception e) {
            System.err.println(e);
        }
    }

    /**
     * Adds a message to the buffer
     * @param msg 
     */
    public void addToBuffer(String msg) {
        buffer.add(msg);
    }

    /**
     * Sends a file Proposal to the remote UDPServer
     * @param fileprop
     * @param address
     * @param b 
     */
    void sendFilePropose(FileProposal fileprop, InetAddress address, boolean b) {
        try {

            //  String host = "localhost";
            int port = ports;
            //BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
            //String msg1 = is.readLine();
            //byte[] message = msg.getBytes();

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(4 * 1024);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(fileprop);
            byte[] message = byteArrayOutputStream.toByteArray();
            // Get the internet address of the specified host
            //InetAddress address = address;//InetAddress.getByName(host);
            // Initialize a datagram packet with data and address
            DatagramPacket packet = new DatagramPacket(message, message.length, address, port);
            dsocket = new DatagramSocket();
            dsocket.setBroadcast(b);
            dsocket.send(packet);
            dsocket.close();

        } catch (Exception e) {
            System.err.println(e);
        }
    }

    /**
     * Sends a FileProposalOk to the remote UDPServer
     * @param filetransOK
     * @param address
     * @param b 
     */
    void sendFileProposeOK(FileTransferAccepted filetransOK, InetAddress address, boolean b) {
        try {

            //  String host = "localhost";
            int port = ports;
            //BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
            //String msg1 = is.readLine();
            //byte[] message = msg.getBytes();

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(4 * 1024);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(filetransOK);
            byte[] message = byteArrayOutputStream.toByteArray();
            // Get the internet address of the specified host
            //InetAddress address = address;//InetAddress.getByName(host);
            // Initialize a datagram packet with data and address
            DatagramPacket packet = new DatagramPacket(message, message.length, address, port);
            dsocket = new DatagramSocket();
            dsocket.setBroadcast(b);
            dsocket.send(packet);
            dsocket.close();

        } catch (Exception e) {
            System.err.println(e);
        }
    }

    /**
     * Sends a FileProposalNOTOk to the remote UDPServer
     * @param filetransNotOK
     * @param address
     * @param b 
     */
    void sendFileProposeNOTOK(FileTransferNOK filetransNotOK, InetAddress address, boolean b) {
        try {

            //  String host = "localhost";
            int port = ports;
            //BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
            //String msg1 = is.readLine();
            //byte[] message = msg.getBytes();

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(4 * 1024);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(filetransNotOK);
            byte[] message = byteArrayOutputStream.toByteArray();
            // Get the internet address of the specified host
            //InetAddress address = address;//InetAddress.getByName(host);
            // Initialize a datagram packet with data and address
            DatagramPacket packet = new DatagramPacket(message, message.length, address, port);
            dsocket = new DatagramSocket();
            dsocket.setBroadcast(b);
            dsocket.send(packet);
            dsocket.close();

        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
