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
    abstract class HuffmanTree implements Comparable<HuffmanTree> {

    public final int frequency; //the frequency of this tree
    
    public HuffmanTree (int freq) {
        frequency = freq;
    }
    //compares on the frequency
    @Override
    public int compareTo(HuffmanTree tree) {
        return frequency - tree.frequency;
    }
    }