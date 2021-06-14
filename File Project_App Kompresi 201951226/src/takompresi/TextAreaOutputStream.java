/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package takompresi;
import java.io.IOException;
import java.io.OutputStream;
import javax.swing.JTextArea;
/**
 *
 * @author ASRLXX
 */
public class TextAreaOutputStream extends OutputStream{
    private JTextArea textcontrol;
    
    public TextAreaOutputStream( JTextArea control){
        textcontrol = control;
    }
    
    public void write (int b) throws IOException{
        textcontrol.append(String.valueOf((char)b));
    }
    
}
