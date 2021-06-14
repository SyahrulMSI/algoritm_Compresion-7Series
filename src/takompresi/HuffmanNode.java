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
public class HuffmanNode extends HuffmanTree {
     public final HuffmanTree right; //subtrees
    public final HuffmanTree left;  //subtrees
    
    public HuffmanNode(HuffmanTree l, HuffmanTree r) {
        super(l.frequency + r.frequency);
        left = l;
        right = r;
    } 
}
