package sg.edu.nus.sudoku.model;

import java.util.ListIterator;

/**
 * The <tt>SudokuCell</tt> class is used as the backend representation of the
 * cells present in any sudoku game. The difference between <tt>SudokuCell</tt>
 * and the <tt>Cell</tt> class is its scope of work. The responsibility of
 * the <tt>SudokuCell</tt> is to check if any value assigned to it fits in with
 * its <tt>SudokuConstraint</tt>s and either returns false or spits out an
 * Exception if it is unable to do so. The <tt>Cell</tt>'s job is simply to 
 * display values and to take them in. I would suggest strongly that whoever 
 * writes anything within this code <b>NOT</b> reference any GUI component
 * directly, but rather, expose it using the <tt>ValueDisplayInterface</tt>.
 *
 * @author shawn
 *
 */
public class SudokuCell {
	private AvailableValues availableValues;
	private boolean given;
	private SudokuConstraint rowConstraint,columnConstraint,squareConstraint;
	private SudokuValue value;
	private ListIterator<SudokuValue> valueIterator;
	
	SudokuCell(
			int id,
			SudokuConstraint rowConstraint,
			SudokuConstraint columnConstraint, SudokuConstraint squareConstraint){
		this.initialize(null, false, rowConstraint, columnConstraint, squareConstraint);
	}


	public void clear(){
		putBackIntoConstraints(value);
		if(valueIterator == null) valueIterator = availableValues.listIterator();
		while(valueIterator.hasPrevious()) valueIterator.previous();
		value = null;
	}

	/**
	 * @return the availableValues
	 */
	public AvailableValues getAvailableValues() {
		return availableValues;
	}

	/**
	 * @return the value
	 */
	public SudokuValue getValue() {
		return value;
	}
	/**
	 * @return the isGiven
	 */
	public boolean isGiven() {
		return given;
	}
	public void nextAvailableValue(){
		if(valueIterator == null) valueIterator = availableValues.listIterator();
		if(!valueIterator.hasNext()) {
			while(valueIterator.hasPrevious()) System.out.println( valueIterator.previous()); //start from first if at last.
		}
		setValueAndUpdateConstraints(valueIterator.next());
	}
	public void previousAvailableValue(){
		if(valueIterator == null) valueIterator = availableValues.listIterator();
		if(!valueIterator.hasPrevious()) {
			while(valueIterator.hasNext()) valueIterator.next(); //start from last if at first
		}
		if(valueIterator.hasPrevious()) setValueAndUpdateConstraints(valueIterator.previous());
	}
	
	private void setIteratorToCurrentValue(){
		if(valueIterator == null) valueIterator = availableValues.listIterator();
		while(valueIterator.hasNext()){
			if(valueIterator.next() == value){
				valueIterator.previous();
				return;
			}
		}
		while(valueIterator.hasPrevious()) {
			if(valueIterator.previous() == value){
				return;
			}
		}
	}

	/**
	 * @param value the value to set
	 * the String value set value is user populated - don't confuse..
	 * shawn, why are we protecting setvalue(SV value?) 
	 * Why not SudokuValue newValue = SudokuValue.sudokuValue(this.getText()); in Cell.java, and save one step? YP
	 * Because right now, SudokuCell is the only class that is "exposed" to the Cell class. So this String setValue
	 * method is the friendlier version that is seen by all.
	 * And isn't it cleaner?
	 */
	public boolean setValue(String value) {
		SudokuValue newValue = SudokuValue.sudokuValue(value);
		if(setValue(newValue)){
			return true;
		} else return false;
		 
	}
	private void initialize(
			SudokuValue value,
			boolean isGiven,
			SudokuConstraint rowConstraint,
			SudokuConstraint columnConstraint,
			SudokuConstraint squareConstraint){
		this.availableValues = new AvailableValues();
		this.given = isGiven;
		this.rowConstraint = rowConstraint;
		this.columnConstraint = columnConstraint;
		this.squareConstraint = squareConstraint;
		this.setValue(value);
	}

	private void putBackIntoConstraints(SudokuValue value){
		rowConstraint.putBackAvailable(this,value);
		columnConstraint.putBackAvailable(this,value);
		squareConstraint.putBackAvailable(this,value);
	}
	private void updateConstraints(SudokuValue previous, SudokuValue current) {
		putBackIntoConstraints(previous);
		rowConstraint.takeFromAvailable(this,current);
		columnConstraint.takeFromAvailable(this,current);
		squareConstraint.takeFromAvailable(this,current);
	}
	
	/**
	 * @return the columnConstraint
	 */
	protected SudokuConstraint getColumnConstraint() {
		return columnConstraint;
	}

	/**
	 * @return the rowConstraint
	 */
	protected SudokuConstraint getRowConstraint() {
		return rowConstraint;
	}



	/**
	 * @return the squareConstraint
	 */
	protected SudokuConstraint getSquareConstraint() {
		return squareConstraint;
	}

	protected void putBackAvailable(SudokuValue value) {
		if(!rowConstraint.checkValidity(value)) return;
		if(!columnConstraint.checkValidity(value)) return;
		if(!squareConstraint.checkValidity(value)) return;
		availableValues.put(value);
	}

	/**
	 * @param isGiven the isGiven to set
	 */
	protected void setGiven(boolean isGiven) {
		this.given = isGiven;
	}

	


	protected boolean setValue(SudokuValue value) {
		if(availableValues.contains(value)){
			setValueAndUpdateConstraints(value);
			setIteratorToCurrentValue();
			return true;
		}
		return false;
	}


	private void setValueAndUpdateConstraints(SudokuValue value) {
		SudokuValue prev = this.value;
		updateConstraints(prev,value);
		this.value=value;
	}

	protected boolean takeFromAvailable(SudokuValue value) {
		return availableValues.take(value);

	}
	
	
}
