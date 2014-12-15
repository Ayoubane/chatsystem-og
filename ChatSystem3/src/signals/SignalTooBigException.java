/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signals;



/**
 * An exception raised when the signal received is too big
 * @author Ayoub, Omar
 */
public class SignalTooBigException extends Exception {

    public SignalTooBigException(String signal_too_big) {
        super(signal_too_big);
    }
    
}
