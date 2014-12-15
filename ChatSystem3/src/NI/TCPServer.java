package NI;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The TCP Server
 * @author Ayoub, Omar
 */
public class TCPServer extends Thread {

    public final static int SOCKET_PORT = 4444;      // you may change this
    public String FILE_TO_RECEIVED = "fileRcv";
    public String PATH_TO_RECEIVED = "/root/Desktop/";
    public boolean RUN = true;
    ServerSocket servsock = null;
    public final static int FILE_SIZE = 20*1024*1024; // file size temporary hard coded
    // should bigger than the file to be downloaded

    
    /**
     * Stops the receiving of a file
     */
    public void stopReceiving(){
        this.RUN=false;
    }
    
    /**
     * Opens a socket to receive the file
     * @param fileName 
     */
    void acceptFileTransfer(String fileName) {
       
        
        try {
            servsock = new ServerSocket(SOCKET_PORT);
            while (RUN) {
                System.out.println("Waiting...");

                Socket sock=null;
                sock = servsock.accept();
                System.out.println("Accepted connection : " + sock);
                // receive file
                
                acceptFile(fileName, sock);
            }
        }catch(Exception e){
            
        }
        
    }
    
    /**
     * Sets the file to receive name
     * @param fileName 
     */
    public void setfileName(String fileName){
        this.FILE_TO_RECEIVED=fileName;
    }
    
    public void run(){
        acceptFileTransfer(FILE_TO_RECEIVED);
    }
    
    /**
     * Receives the file and downloads it to the right folder
     * @param fileName
     * @param sock 
     */
    public void acceptFile(String fileName,Socket sock) {
        int bytesRead;
        int current = 0;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        try {
            byte[] mybytearray = new byte[FILE_SIZE];
            InputStream is = sock.getInputStream();
            fos = new FileOutputStream(PATH_TO_RECEIVED+ FILE_TO_RECEIVED);
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
}
