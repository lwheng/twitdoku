
package sg.edu.nus.sudoku.model;

/**
 * It is my opinion that in algorithmically solving a sudoku puzzle, all the
 * modelled cell need not know its x,y position inside the 9X9 square. But
 * each cell, however belongs 2 3 different constraints, 1 row, 1 column, and
 * 1 3X3 square. Notice that each of these contsraints has 9 cells.
 * <br/>
 * This class is an object model of the constraints that the cells belong to.
 * Each sudoku game, controlled by <tt>SudokuController</tt> has 27 constraints,
 * and each constraint has 9 <tt>SudokuCell</tt>s
 * <br/>
 * The reason there are <tt>availableValues</tt> attributes in both Cell and
 * constraint is because this would allow the model to be able to give hints
 * to the player, and at the same time, simplifying the process of solving 
 * the sudoku (if we ever get to that point). Without it, we would need to
 * re-compute the "allowed values" every time we need it.
 * 
 * @see <a href="http://theory.tifr.res.in/~sgupta/sudoku/algo.html">The mathematics of Su Doku</a>
 * @author shawn
 *
 */
public class SudokuConstraint {
	private AvailableValues availableValues;
	private int count;
	private SudokuCell[] sudokuCells;
	SudokuConstraint(SudokuBoard sudokuBoard){
		this.availableValues = new AvailableValues();
		this.sudokuCells = new SudokuCell[9];
		this.count = 0;
	}
	
	protected void addCell(SudokuCell cell){
		this.sudokuCells[count] = cell;
		this.count++;
	}
	
	public boolean checkValidity(SudokuValue value) {
		return availableValues.contains(value);

	}
	
	/**
	 * @return the sudokuCells
	 */
	public SudokuCell[] getSudokuCells() {
		return sudokuCells;
	}
	
	public void putBackAvailable(SudokuCell editedCell,SudokuValue value){
		if (value==null) return;
		availableValues.put(value);
		for(SudokuCell cell: sudokuCells){
			if(cell == editedCell) continue;
 			cell.putBackAvailable(value);
		}
		
	}
	public void takeFromAvailable(SudokuCell editedCell,SudokuValue value){
		if (value==null) return;
		availableValues.take(value);
		for(SudokuCell cell: sudokuCells){
			if(cell == editedCell) continue;
			cell.takeFromAvailable(value);
		}
	}

	/**
	 * @return
	 * @see sg.edu.nus.sudoku.model.AvailableValues#size()
	 */
	public int availableValuesSize() {
		return availableValues.size();
	}
	
}
