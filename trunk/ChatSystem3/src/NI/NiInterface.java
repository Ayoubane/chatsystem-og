    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NI;

/**
 *
 * @author root
 */
public interface NiInterface {
    
    public void sendHello(String userName);
    public void closeConnection();
    public void sendMsg(String msg, String from,String[] to);
    public void sendFile(String path, String from,String[] to);
    
}
