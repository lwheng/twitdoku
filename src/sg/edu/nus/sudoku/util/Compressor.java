package sg.edu.nus.sudoku.util;

import java.util.Hashtable;


public class Compressor {
	private Hashtable<String, String> table = new Hashtable<String, String>();
	public static void main(String[] args) {
		String com = stringCompressor("10000001234010203040506070809080706050403020100000000000000000000000012340");
		String decom = stringDecompressor("1E12340102030405060708090807060504030201W12340");
		System.out.println("-"+com+"-");
		System.out.println("-"+decom+"-");
	}

	Compressor () {
	}

	public static String stringCompressor(String input) {
		int noOfZero;
		int sum = 63;
		StringBuffer output = new StringBuffer();

		for(int i=0; i<input.length();i++) {
			if(input.charAt(i) == '0') {
				noOfZero = 1;
				while(i+noOfZero<input.length() && input.charAt(i+noOfZero)=='0') noOfZero++;
				if(noOfZero > 1) {
					output.append((char)(noOfZero+sum));
					i = i + (noOfZero - 1);
				}
				else output.append('0');
			} else {
				output.append(input.charAt(i));
			}
		} 
		return output.toString();
	}

	public static String stringDecompressor(String input) {
		int dec = 0;
		int noOfZero = 0;
		StringBuffer output = new StringBuffer();
		char c;
		for (int i=0; i<input.length(); i++) {
			c = input.charAt(i);
			dec = (int)c;
			if (dec > 64) {
				noOfZero = dec - 63;
				for (int j=0; j<noOfZero; j++) {
					output.append('0');
				}
			}
			else {
				output.append(c);
			}
		}
		return output.toString();
	}
}
