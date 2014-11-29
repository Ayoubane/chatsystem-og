/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package signals;

/**
 *
 * @author Ayoub
 */

import java.io.Serializable;
import java.net.InetAddress;

public class Goodbye implements Serializable {
        private String username;
        private InetAddress adr;
        private static final long serialVersionUID = 1L;

        public Goodbye(String username, InetAddress adr) {
                super();
                this.username = username;
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
