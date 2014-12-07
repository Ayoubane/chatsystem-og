package NI;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPServer extends Thread {

    public final static int SOCKET_PORT = 13267;      // you may change this

    public String SERVER = "127.0.0.1";  // localhost

    public String FILE_TO_RECEIVED = "fileRcv";
    public String PATH_TO_RECEIVED = "D:\\";

    public final static int FILE_SIZE = 6022386; // file size temporary hard coded
    // should be bigger than the file to be downloaded

    public void setSERVER(String server) {
        this.SERVER = server;
    }
    
    public void acceptFileTransfer(String fileName) {
        Socket sock = null;
        try {
            
            sock = new Socket(SERVER, SOCKET_PORT);
            System.out.println("Connecting...");

            // receive file
            acceptFile(fileName, sock);
            this.join(200);
        } catch (Exception e) {
            e.printStackTrace();
        } 
                
          
    }

    public void acceptFile(String fileName,Socket sock) {
        int bytesRead;
        int current = 0;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        try {
            byte[] mybytearray = new byte[FILE_SIZE];
            InputStream is = sock.getInputStream();
            fos = new FileOutputStream(PATH_TO_RECEIVED+ fileName);
            bos = new BufferedOutputStream(fos);
            bytesRead = is.read(mybytearray, 0, mybytearray.length);
            current = bytesRead;

            while (bytesRead > -1) {
                bytesRead = is.read(mybytearray, current, (mybytearray.length - current));
                if (bytesRead >= 0) {
                    current += bytesRead;
                }
            }

            bos.write(mybytearray, 0, current);
            bos.flush();
            System.out.println("File " +PATH_TO_RECEIVED+ FILE_TO_RECEIVED
                    + " downloaded (" + current + " bytes read)");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (bos != null) {
                    bos.close();
                }
                if (sock != null) {
                    sock.close();
                }
            } catch (Exception ex) {
                Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void setfileName(String fileName) {
        this.FILE_TO_RECEIVED = fileName;
    }

    public void run() {
        acceptFileTransfer(FILE_TO_RECEIVED);
    }
}