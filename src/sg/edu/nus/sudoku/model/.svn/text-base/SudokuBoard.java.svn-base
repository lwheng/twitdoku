package sg.edu.nus.sudoku.model;


/**
 * SudokuBoard implements the Facade design pattern, and provides methods to access the various
 * classes in the package.
 * @author Breton, Low Wee, Yipeng and Shawn
 *
 */
public class SudokuBoard {
	
	private SudokuCell[] cells;

	private SudokuConstraint[] columnConstraints;

	private SudokuConstraint[] rowConstraints;

	private SudokuConstraint[] squareConstraints;
	
	private String solution;

	/**
	 * Default constructor.
	 */
	public SudokuBoard(){
		initialize();
	}
	/**
	 * Takes in an 81 character string and constructs a {@link SudokuBoard} from it.
	 * Given {@link SudokuCell}s will be return <code>true</code> for isGiven();
	 * @param sudokuString
	 */
	public SudokuBoard(String sudokuString){
		initialize();
		for(int i=0;i<sudokuString.length();i++){
			SudokuValue value = SudokuValue.sudokuValue(sudokuString.charAt(i));
			if(value!=null) {
				cells[i].setValue(value);
				cells[i].setGiven(true);
			}
		}
	}
	/**
	 * Returns an 81-element array of {@link SudokuCell}s belonging to the board.
	 * @return the cells
	 */
	protected SudokuCell[] getCells() {
		return cells;
	}
	/**
	 * Returns the 9 {@link SudokuConstraint}s that govern the vertical constraints of the board.
	 * @return the columnConstraints
	 */
	protected SudokuConstraint[] getColumnConstraints() {
		return columnConstraints;
	}
	
	/**
	 *  Returns the 9 {@link SudokuConstraint}s that govern the horizontal constraints of the board.
	 * @return the rowConstraints
	 */
	protected SudokuConstraint[] getRowConstraints() {
		return rowConstraints;
	}
	
	/**
	 * Returns the 81-character string that represents the board's solution.
	 * @return
	 */
	protected String getSolution() {
		return this.solution;
	}
	/**
	 *  Returns the 9 {@link SudokuConstraint}s that govern the square constraints of the board.
	 * @return the squareConstraints
	 */
	protected SudokuConstraint[] getSquareConstraints() {
		return squareConstraints;
	}
	
	private void createBoard(){
		for(int i=0;i<cells.length;i++){
			int row = i/9;
			int col = i%9;
			int square = (row/3) * 3 + (col/3);
	
			cells[i] = new SudokuCell(i,rowConstraints[row],columnConstraints[col], squareConstraints[square]);
			rowConstraints[row].addCell(cells[i]);
			columnConstraints[col].addCell(cells[i]);
			squareConstraints[square].addCell(cells[i]);
		}
	}
	private void initialize() {
		this.rowConstraints = new SudokuConstraint[9];
		populateConstraintArray(this.rowConstraints);
		this.columnConstraints =  new SudokuConstraint[9];
		populateConstraintArray(this.columnConstraints);
		this.squareConstraints =  new SudokuConstraint[9];
		populateConstraintArray(this.squareConstraints);
		this.cells = new SudokuCell[81];
		this.createBoard();
	}
	private void populateConstraintArray(SudokuConstraint[] sc){
		for(int i=0;i<sc.length;i++){
			sc[i] = new SudokuConstraint(this);
		}
	}
	/**
	 * Clears all user-input cells.
	 */
	public void clear(){
		for(int i=0;i<cells.length;i++){
			if(!cells[i].isGiven())cells[i].clear();
		}
	}
	/**
	 * Returns the serialized un-filled board with only given values in the form of an 81-character string.
	 * @return 81-character string representing the given cells.
	 */
	public String serializeBoard(){
		StringBuffer sb = new StringBuffer(81);
		for(int i=0;i<cells.length;i++){
			if(cells[i].isGiven())sb.append(cells[i].getValue());
			else sb.append('0');
		}
		return sb.toString();
	}
	
	/**
	 * Returns the board in string form in its current state. All values, given or not given,
	 * will be serialized into an 81-character string. Not the same as <tt>serializeBoard()</tt>
	 * @return 81-character string representing the current state of the board.
	 */
	public String serializeBoardState(){
		StringBuffer sb = new StringBuffer(81);
		for(int i=0;i<cells.length;i++){
			if(cells[i].getValue() !=null )sb.append(cells[i].getValue());
			else sb.append('0');
		}
		return sb.toString();
	}
	
	
	/**
	 * Generates a board based on an 81-character string of given values for a sudoku puzzle.
	 * Taking return value from serializeBoard() as a parameter for this method generates the same un-filled board
	 * @param sudokuString 81-character string representing the given cells
	 */
	public void deserializeBoard(String sudokuString){
		resetBoard();
		for(int i=0;i<sudokuString.length();i++){
			SudokuValue value = SudokuValue.sudokuValue(sudokuString.charAt(i));
			if(value!=null) {
				cells[i].setValue(value);
				cells[i].setGiven(true);
			}
		}
	}
	
	/**
	 * Fills up the SudokuCells based on the 2 given strings, first for only the given cells,
	 * the second for the filled non-given cells.
	 * @param sudokuString 81-character string representing the given cells
	 * @param userFilled 81-character string representing the user-input
	 */
	public void deserializeBoardState(String given,String nongiven){
		resetBoard();
		SudokuValue value;
		for(int i=0;i<nongiven.length();i++){
			value = SudokuValue.sudokuValue(nongiven.charAt(i));
			if(value!=null) {
				cells[i].setValue(value);
			}
			value = SudokuValue.sudokuValue(given.charAt(i));
			if(value!=null) {
				System.out.print(value);
				cells[i].setValue(value);
				cells[i].setGiven(true);
			}
			
		}
	}
	
	/**
	 * Fills the SudokuCells using the values from the 81-character string
	 * @param nongiven String of 81-characters that represents the solution.
	 */
	public void deserializeBoardState(String nongiven){
		clear();
		for(int i=0;i<cells.length;i++){
			if(!cells[i].isGiven()) {
				SudokuValue value = SudokuValue.sudokuValue(nongiven.charAt(i));
				if(value!=null) cells[i].setValue(value);
			}
		}
	}
	
	/**
	 * Sets the solution variable.
	 * @param soln 81-character string representing the solution to the board.
	 */
	public void setSolution(String soln) {
		 this.solution=soln;
	}
	
	/**
	 * Converts the <tt>value</tt> into an instance of SudokuValue and then
	 * sets the SudokuValue of the cell with <tt>cellId</tt>
	 * @param cellId Cell ID
	 * @param value String value
	 * @return <tt>true</tt> if operation was successful.
	 */
	public boolean setValue(int cellId, String value) {
		SudokuValue newValue = SudokuValue.sudokuValue(value);
		if(cells[cellId].setValue(newValue)){
			return true;
		} else return false;
		 
	}
	
	/**
	 * Gets the SudokuValue of the SudokuCell with ID <tt>cellID</tt>
	 * @param cellId Cell ID
	 * @return String value of the SudokuCell
	 */
	public String getValue(int cellId) {
		if(cells[cellId]== null) return "";
		else if(cells[cellId].getValue()== null) return "";
		else return cells[cellId].getValue().toString();
	}
	/**
	 * Sets the SudokuCell value to the next available value
	 * @param cellId
	 */
	public void setNextAvailableValue(int cellId){
		cells[cellId].nextAvailableValue();
	}
	/**
	 * Sets the SudokuCell value to the previous available value
	 * @param cellId Cell ID
	 */
	public void setPreviousAvailableValue(int cellId){
		cells[cellId].previousAvailableValue();
	}
	/**
	 * Sets the SudokuCell with ID <tt>cellId</tt> value to null.
	 * @param cellId Cell ID
	 */
	public void clearValue(int cellId){
		cells[cellId].clear();
	}
	/**
	 * Returns the AvailableValues object for cell with ID <tt>cellId</tt>
	 * @param cellId Cell ID
	 * @return AvailableValues object for SudokuCell
	 */
	public AvailableValues getAvailableValues(int cellId){
		return cells[cellId].getAvailableValues();
	}
	/**
	 * Returns <tt>true</tt> if cell is system-generated or provided.
	 * @param cellId Cell ID
	 * @return
	 */
	public boolean isCellGiven(int cellId){
		return cells[cellId].isGiven();
	}
	
	/**
	 * Prints board to STDOUT
	 */
	public void printBoard() {
		for(int i=0;i<cells.length;i++){
			
			if((i!=0) && (i%9 == 0) )System.out.print("|\n|");
			else if(i%3==0) System.out.print('|');
			
			else System.out.print(' ');
			if(cells[i].getValue() == null) System.out.print(' ');
			else {
				if(cells[i].isGiven())  System.out.print('G');
				else System.out.print(cells[i].getValue());
			}
		}
		System.out.print('|');
		System.out.println();
	}
	
	
	/**
	 * Resets all values (both given and non-given) 
	 */
	public void resetBoard(){
		for(int i=0;i<cells.length;i++){
			cells[i].clear();
			cells[i].setGiven(false);
		}
		solution = null;
	}
	/**
	 * Checks if the board is fully filled.
	 * @return The win state of the board.
	 */
	public boolean verifyWin(){
		for(int i=0;i<rowConstraints.length;i++){
			if(rowConstraints[i].availableValuesSize()!=0) return false;
		}
		for(int i=0;i<columnConstraints.length;i++){
			if(columnConstraints[i].availableValuesSize()!=0) return false;
		}
		for(int i=0;i<squareConstraints.length;i++){
			if(squareConstraints[i].availableValuesSize()!=0) return false;
		}
		return true;
	}
	
	/**
	 * Counts and returns the number of given values for the puzzles.
	 * @return number of given values for the puzzle.
	 */
	public int getNumberOfClues(){
		int count = 0;
		for(int i=0;i<cells.length;i++){
			if (cells[i].isGiven()) count++;
		}
		return count;
	}
	
	/**
	 * Set all user-filled cells as given. <br/>
	 * This is to prepare the SudokuBoard for the SudokuSolver in the case of a user-created puzzle.
	 */
	public void setFilledCellsAsGiven() {
		for(int i=0;i<cells.length;i++){
			if(cells[i].getValue()!=null) cells[i].setGiven(true);
		}
	}
	
	/**
	 * Sets all cells to not given.
	 */
	public void setAllCellsAsNotGiven(){
		for(int i=0;i<cells.length;i++){
			cells[i].setGiven(false);
		}
	}
	
}
