package NI;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPServ extends Thread {

    public final static int SOCKET_PORT = 13267;      // you may change this
    public String FILE_TO_RECEIVED = "fileRcv";
    public String PATH_TO_RECEIVED = "/root/Desktop/";
    public boolean RUN = true;
    
    public final static int FILE_SIZE = 6022386; // file size temporary hard coded
    // should bigger than the file to be downloaded

    void acceptFileTransfer(String fileName) {
       
        ServerSocket servsock = null;
        Socket sock = null;
        try {
            servsock = new ServerSocket(SOCKET_PORT);
            while (RUN) {
                System.out.println("Waiting...");

                sock = servsock.accept();
                System.out.println("Accepted connection : " + sock);
                // receive file
                
                acceptFile(fileName, sock);
                this.join();
            }
        }catch(Exception e){
            
        }
        
    }
    
    public void setfileName(String fileName){
        this.FILE_TO_RECEIVED=fileName;
    }
    
    public void run(){
        acceptFileTransfer(FILE_TO_RECEIVED);
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
}
