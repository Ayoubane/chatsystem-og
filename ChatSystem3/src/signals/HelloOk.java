/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package signals;

import java.io.Serializable;
import java.net.InetAddress;

/**
 *
 * @author Ayoub
 */
public class HelloOk implements Serializable{
    private String username;
    private InetAddress adr;
    private static final long serialVersionUID = 1L;
    
    public HelloOk(String user, InetAddress adr){
        super();
        this.username=user;
        this.adr=adr;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public InetAddress getAdr() {
        return adr;
    }

    public void setAdr(InetAddress adr) {
        this.adr = adr;
    }
}
