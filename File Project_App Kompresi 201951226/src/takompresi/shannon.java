/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package takompresi;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.HashMap;
/**
 *
 * @author ASRLXX
 */
public class shannon {
    private static final int ASCII_LENGTH = 7;
    
    private String originalString;
	private int originalStringLength;
	private HashMap<Character, String> compressedResult;
	private HashMap<Character, Double> characterFrequency;
	private double entropy;
	private double averageLengthBefore;
	private double averageLengthAfter;
	private boolean probabilityIsGiven;

    public shannon(String str) {
		super();
		originalString = str;
		originalStringLength = str.length();
		characterFrequency = new HashMap<Character, Double>();
		compressedResult = new HashMap<Character, String>();
		entropy = 0.0;
		averageLengthBefore = 0.0;
		averageLengthAfter = 0.0;
		probabilityIsGiven = false;
                
                this.calculateFrequency();
		this.compressString();
		this.calculateEntropy();
		this.calculateAverageLengthBeforeCompression();
		this.calculateAverageLengthAfterCompression();
    }
    
    public shannon(String str, HashMap<Character, Double> probablity) {
        super();
		originalString = str;
		originalStringLength = str.length();
                
                characterFrequency = new HashMap<Character, Double>();
                double checkPoint = 0;
		for (Character c : originalString.toCharArray()) {
			checkPoint += probablity.get(c);
			characterFrequency.put(c, originalStringLength * probablity.get(c));
                }
                
                assert checkPoint == 1.0;
                compressedResult = new HashMap<Character, String>();
		entropy = 0.0;
		averageLengthBefore = 0.0;
		averageLengthAfter = 0.0;
		probabilityIsGiven = true;
                
                this.compressString();
		this.calculateEntropy();
		this.calculateAverageLengthBeforeCompression();
		this.calculateAverageLengthAfterCompression();

    }
    
    private void compressString() {
        List<Character> charList = new ArrayList<Character>();
        Iterator<Entry<Character, Double>> entries = characterFrequency.entrySet().iterator();
	while (entries.hasNext()) {
            Entry<Character, Double> entry = entries.next();
            charList.add(entry.getKey());
        }
        appendBit(compressedResult, charList, true);
    }
    
    private void appendBit(HashMap<Character, String> result, List<Character> charList, boolean up) {
        String bit = "";
		if (!result.isEmpty()) {
                    bit = (up) ? "0" : "1";
                }
                for (Character c : charList) {
			String s = (result.get(c) == null) ? "" : result.get(c);
			result.put(c, s + bit);
                }
                if (charList.size() >= 2) {
			int separator = (int) Math.floor((float) charList.size() / 2.0);

			List<Character> upList = charList.subList(0, separator);
			appendBit(result, upList, true);
			List<Character> downList = charList.subList(separator, charList.size());
			appendBit(result, downList, false);
                }
    }
    
    private void calculateFrequency() {
        for (Character c : originalString.toCharArray()) {
			if (characterFrequency.containsKey(c)) {
				characterFrequency.put(c, new Double(characterFrequency.get(c) + 1.0));
                        } else{
                            characterFrequency.put(c, 1.0);
                        }
                }
    }
    
    private void calculateEntropy() {
        double probability = 0.0;
		for (Character c : originalString.toCharArray()) {
			probability = 1.0 * characterFrequency.get(c) / originalStringLength;
			entropy += probability * (Math.log(1.0 / probability) / Math.log(2));
                }
    }
    
    private void calculateAverageLengthBeforeCompression() {
        double probability = 0.0;
		for (Character c : originalString.toCharArray()) {
			probability = 1.0 * characterFrequency.get(c) / originalStringLength;
			averageLengthBefore += probability * ASCII_LENGTH;
                }
    }
    
    private void calculateAverageLengthAfterCompression() {
        double probability = 0.0;
		for (Character c : originalString.toCharArray()) {
			probability = 1.0 * characterFrequency.get(c) / originalStringLength;
			averageLengthAfter += probability * compressedResult.get(c).length();
                }
    }
    
    @SuppressWarnings("unchecked")
    public HashMap<Character, String> getCompressedResult() {
		return (HashMap<Character, String>) compressedResult.clone();
    }
    
    @Override
    
    public String toString() {
     String str = "";
		str += "*** Probability is" + (probabilityIsGiven ? " " : " Not ") + "Given. "
				+ (probabilityIsGiven ? "We did not calculate the probability."
						: "Probability was calculated using frequency of each character in the given String.")+ "\n";
		str += "Original String: \"" + originalString + "\"\n";
		str += "------------------------------------------------------------------------\n";
		str += "Symbol\t\tFrequency\tProbability\tShannon-F Code\tASCII Code\n";
		str += "------------------------------------------------------------------------\n";
                for (Character c : compressedResult.keySet()) {
			str += "'" + c + "'" + "\t\t" + Math.round(characterFrequency.get(c) * 100.0) / 100.0 + "\t\t"
					+ Math.round(characterFrequency.get(c) / originalStringLength * 10000.0) / 10000.0 + "\t\t"
					+ compressedResult.get(c) + "\t\t" + Integer.toBinaryString((int) c);
                        str += "\n";
                }
                str += "------------------------------------------------------------------------\n";
		str += "Efficiency before Compression: " + 100 * (Math.round((entropy / averageLengthBefore) * 100.0) / 100.0)
				+ "%\n";
		str += "Efficiency after Compression: " + 100 * (Math.round((entropy / averageLengthAfter) * 100.0) / 100.0)
				+ "%\n";
		str += "------------------------------------------------------------------------\n";
		return str;

    }
    
    public static void main(String[] args){
        String givenString = "kurniawan asrul as'ad";
        
        shannon sfc = new shannon(givenString);
		System.out.println(sfc);

      HashMap<Character, Double> probability = new HashMap<Character, Double>();
      
      probability.put(' ', 0.1686);
		probability.put('e', 0.1031);
		probability.put('t', 0.0796);
		probability.put('a', 0.0642);
		probability.put('o', 0.0632);
		probability.put('i', 0.0575);
		probability.put('n', 0.0574);
		probability.put('s', 0.0514);
		probability.put('r', 0.0484);
		probability.put('h', 0.0467);
		probability.put('l', 0.0321);
		probability.put('d', 0.0317);
		probability.put('u', 0.0228);
		probability.put('c', 0.0218);
		probability.put('f', 0.0208);
		probability.put('m', 0.0198);
		probability.put('w', 0.0175);
		probability.put('?', 0.0173);
		probability.put('y', 0.0164);
		probability.put('p', 0.0152);
		probability.put('g', 0.0152);
		probability.put('b', 0.0127);
		probability.put('v', 0.0083);
		probability.put('k', 0.0049);
		probability.put('x', 0.0013);
		probability.put('q', 0.0008);
		probability.put('j', 0.0008);
		probability.put('z', 0.0005);
                
       shannon sfcx = new shannon(givenString, probability);
		System.out.println(sfcx);


    }

}
