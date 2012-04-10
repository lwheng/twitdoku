package sg.edu.nus.sudoku.model;

/**
 * You may think that having an enumeration for something like 9 different
 * characters may be an overkill. But lemme try to argue my point of view.
 * <br/><br/>
 * First off, let me justify why I used Strings instead of chars. When text
 * is retrieved from <tt>JTextFields</tt>, the <tt>.getText()</tt> method returns a String.
 * No doubt the code we may use inside the Cell class may restrict it to being
 * a single character <tt>String</tt>, but Java doesn't give a fuck: It's still a string.
 * Retrieving the first character from the string wastes cpu cycles, but more
 * importantly, it wastes our precious time typing that extra
 * <tt>.indexOf(0)</tt> every time we need the value from the textfield. And
 * when we update the <tt>Cell</tt> we need to <tt>""+character</tt>. In short,
 * its fucking troublesome.
 * <br/><br/>
 * 
 * Why have the enum at all?<br/>
 * Because if we code based on these enumerated values, we can change sudoku
 * to a game of ANY character. Don't like digits? We could do A to I. Also,
 * it allows for us to have utility methods that check what values are in the
 * enum that aren't in a <tt>Collection</tt>, giving us a list of values which
 * could be used in either the <tt>SudokuCell</tt> or the <tt>SudokuConstraint</tt>.
 * @author shawn
 *
 */
public class SudokuValue {
	final private static String[] DEFAULT_VALUES = {"1","2","3","4","5","6","7","8","9"};
	final protected static SudokuValue[] DEFAULT_SUDOKU_VALUES = createSudokuValues(DEFAULT_VALUES);

	final public static SudokuValue EMPTY_VALUE=null;
	private static SudokuValue[] values;
	static {
		values = DEFAULT_SUDOKU_VALUES;
	}
	/**
	 * Copies enum to a list object
	 */
	public static SudokuValue sudokuValue(String s){
		if(s.length() == 0) return null;
		return sudokuValue(s.charAt(0));
	}
	public static SudokuValue sudokuValue(char c){
		for(SudokuValue val: values){
			if(val.getValue().charAt(0) == c){
				return val;
			}
		}
		return null;
	}
	
	public static SudokuValue[] values(){
		return values;
	}
	public static void setDefaultSudokuValues(){
		values = DEFAULT_SUDOKU_VALUES;
	}
	
	public static SudokuValue[] createSudokuValues(String[] strValues){
		
		SudokuValue[] vals = new SudokuValue[9];
		int i = 0;
		for(String s: strValues){
			vals[i] = new SudokuValue(s);
			i++;
		}
		return vals;
	}
	
	
	
	
	private String value;
	private SudokuValue(String value){
		this.value = value;
	}
	@Override
	public String toString() {
        return this.value;
    }
	public String getValue(){
		return this.value;
	}
	

}
