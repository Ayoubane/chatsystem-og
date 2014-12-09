package NI;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPSend implements Runnable{

    public final static int SOCKET_PORT = 4444;  // you may change this
    public static String FILE_TO_SEND = "";
    public String RECEIVER = "127.0.0.1";  // localhost

    public void setReceiver(String rcvr) {
        this.RECEIVER = rcvr;
    }

    public void setFileName(String fileName) {
        this.FILE_TO_SEND = fileName;
    }

    public void sendFileTransfer(String fileName) {
        ServerSocket servsock = null;
        Socket sock = null;
        System.out.println("asdsasdasda");
        try {
            sock = new Socket(RECEIVER, SOCKET_PORT);
            System.out.println("Connecting...");
            // send file
            sendFile(fileName, sock);

        } catch (Exception e) {

        } finally {
            try {
                if (servsock != null) {
                    servsock.close();
                }
                
            } catch (Exception ex) {
                Logger.getLogger(TCPSender.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    public void run() {
        System.out.println("Sender Run, send to " + RECEIVER + " ... " + FILE_TO_SEND);
        if (FILE_TO_SEND.length() != 0) {
            this.sendFileTransfer(FILE_TO_SEND);
        }
    }

    public void sendFile(String fileName, Socket sock) {
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            File myFile = new File(fileName);
            byte[] mybytearray = new byte[(int) myFile.length()];
            fis = new FileInputStream(myFile);
            bis = new BufferedInputStream(fis);
            bis.read(mybytearray, 0, mybytearray.length);
            os = sock.getOutputStream();
            System.out.println("Sending " + FILE_TO_SEND + "(" + mybytearray.length + " bytes)");
            os.write(mybytearray, 0, mybytearray.length);
            os.flush();
            System.out.println("Done.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if (bis != null) {
                    bis.close();
                }
                if (os != null) {
                    os.close();
                }
                if (sock != null) {
                    sock.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(TCPSender.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
