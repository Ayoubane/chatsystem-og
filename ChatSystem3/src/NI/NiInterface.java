    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NI;

import java.net.UnknownHostException;
import javax.swing.text.BadLocationException;

/**
 * The NI Interface
 * @author Ayoub, Omar
 */
public interface NiInterface {
    
    public void getMessage(Object obj) throws UnknownHostException, BadLocationException;
    public void sendHello(String userName);
    public void sendGoodbye(String userName);
    public void sendMessage(String msg);
    public void sendFileProposal(String Name, long size);
    public void acceptFileTransfer(String fileName, String from);
    
}
