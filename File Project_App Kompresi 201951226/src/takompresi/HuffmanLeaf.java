/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package takompresi;

/**
 *
 * @author ASRLXX
 */
public class HuffmanLeaf extends HuffmanTree {
  public final char value; //the character this leaf represents
    
    public HuffmanLeaf (int freq, char val) {
    super (freq);
    value = val;
    }   
}
