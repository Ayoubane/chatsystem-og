package NI;

import signals.TextMessage;
import signals.HelloOK;
import signals.Hello;
import signals.Goodbye;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import signals.FileProposal;
import signals.FileTransferNOK;
import signals.FileTransferOK;

public class UDPSender extends Thread {

    private ArrayList<String> buffer;
    DatagramSocket dsocket;
    NI ni;
    int ports;

    public UDPSender(NI ni, int ports) {
        buffer = new ArrayList<String>(20);
        this.ni = ni;
        this.ports = ports;
    }

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

    public void addToBuffer(String msg) {
        buffer.add(msg);
    }

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

    void sendFileProposeOK(FileTransferOK filetransOK, InetAddress address, boolean b) {
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
