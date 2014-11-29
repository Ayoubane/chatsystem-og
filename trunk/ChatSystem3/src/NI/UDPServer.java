package NI;

import signals.TextMessage;
import signals.HelloOk;
import signals.Hello;
import signals.Goodbye;
import chatsystem.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.Arrays;

public class UDPServer extends Thread {

    
    static final int BUFFERSIZE = 4 * 1024;//256;
    NI ni;
    int portr;
    private Hello hello=null;
    private HelloOk helloOk=null;
    private Goodbye goodbye=null;
    private TextMessage msgRecu=null;

    public UDPServer(NI ni, int portr) {
        this.ni = ni;
        this.portr=portr;
    }
    

    public void deserializePacket(byte[] data)
    {
        try
        {
            hello=null;
            helloOk=null;
            goodbye=null;
            msgRecu=null;
            ObjectInputStream iStream = new ObjectInputStream(new ByteArrayInputStream(data));
            Object resultat = iStream.readObject();
            if(resultat instanceof TextMessage){
                msgRecu=(TextMessage) resultat;
                iStream.close();
            }
            else if(resultat instanceof Hello){
                hello = (Hello) resultat;
                iStream.close();
            }
            else if(resultat instanceof HelloOk){
                helloOk = (HelloOk) resultat;
                iStream.close();
            }
            else if(resultat instanceof Goodbye){
                goodbye = (Goodbye) resultat;
                iStream.close();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

   
    
    
    public void receive() {

        try {

            DatagramSocket sock = new DatagramSocket(portr);
            // echo back everything
            while (true) {
                DatagramPacket pack = new DatagramPacket(new byte[BUFFERSIZE], BUFFERSIZE);
                sock.receive(pack);
                //sock.send(pack);
                //String decodedData = new String(pack.getData(),0, pack.getLength());
                
                deserializePacket(pack.getData());
                
                if (ni.controller.CONNECTED == true && pack.getLength() > 0 && hello!=null && helloOk==null && goodbye==null) {
                    ni.getMessage(hello);
                    System.out.println(hello);
                    System.out.println("Recived a Hello");
                }
                else if(ni.controller.CONNECTED == true && pack.getLength() > 0 && hello==null && helloOk==null && goodbye==null){
                    ni.getMessage(msgRecu);
                    System.out.println(msgRecu);
                    System.out.println("Recived a Message");
                }
                else if (ni.controller.CONNECTED == true && pack.getLength() > 0 && hello==null && helloOk!=null && goodbye==null) {
                    ni.getMessage(helloOk);
                    System.out.println(helloOk);
                    System.out.println("Recived a HelloOk");
                }
                else if (ni.controller.CONNECTED == true && pack.getLength() > 0 && hello==null && helloOk==null && goodbye!=null) {
                    ni.getMessage(goodbye);
                    System.out.println(goodbye);
                    System.out.println("Recived a Goodbye");
                }
                else{
                    System.out.println(hello+" // "+helloOk +" // "+goodbye+" // "+msgRecu);
                    System.out.println("ERROR!");
                }

//                System.out.println("Server recives : "+decodedData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        this.receive();
    }
}
