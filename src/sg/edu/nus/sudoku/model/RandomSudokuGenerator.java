package sg.edu.nus.sudoku.model;

import java.util.Random;

/**
 * Generates a random SudokuBoard.
 * @author Shawn, Yipeng, Breton and Low Wee
 */
public class RandomSudokuGenerator {

	final private static Random random = new Random();
	final private static int WAIT_FACTOR = 100;
	
	private SolverThread st;
	private int solutions =0;
	private SudokuBoard board = null;

	
	
	
	public RandomSudokuGenerator (){
		st = new SolverThread(this);
		st.start();
	}
	public synchronized void getRandomSudokuBoard(SudokuBoard board){
		while(!fillRandomCellNo(0,board));
	}
	
	private synchronized boolean fillRandomCellNo(int number,SudokuBoard board) {
		SudokuCell[] cells = board.getCells();
		SudokuCell c = null;
		int count = 0;
		while(count < (cells.length - number)) {
			c = fillRandomCell(cells);
			while(!SudokuSolver.checkCellConstraints(c)) {
				c.setGiven(false);
				c.clear();
				c = fillRandomCell(cells);
			}
			
			try {
				
				this.board = board;	//put board somewhere the solverthread can take it.
				notify();
				wait(WAIT_FACTOR);	//wait for some time, then check.
				st.interrupt();		//stop solving thread
				
			} catch (InterruptedException e) {}
			
			if(st.isSolving()) board.clear();
			
			if(solutions == 0) {  //was still running, no solutions, delete current and return.	
				c.setGiven(false);
				c.clear();
				return false;
			}
			else if(solutions > 1)  {				//more than 1 solutions, add more clues;
				if(fillRandomCellNo(number + 1,board)) return true; 
				else {
					count++;
					c.setGiven(false);
					c.clear();
				}
			}
			else if(solutions == 1){
				return true;
			}
		}
		return false;
	}
	

	protected synchronized void putSolutionNumber(int i){
			solutions = i;
			notify();
			board = null;
		
	}
	
	protected synchronized SudokuBoard getSudokuBoard() throws InterruptedException{
		while(this.board == null) wait();
		return this.board;
	}
	

	private synchronized SudokuCell fillRandomCell(SudokuCell[] cells){

		int index = random.nextInt(cells.length);
		while(cells[index].getValue()!=null) index = random.nextInt(cells.length);
		SudokuValue[] values = cells[index].getAvailableValues().getArray();
		int valueIndex = random.nextInt(values.length);
		while(values[valueIndex]==null) {
			valueIndex++;
			if(valueIndex==values.length) valueIndex=0;
		}
		
		cells[index].setValue(values[valueIndex]);
		
		cells[index].setGiven(true);
		return cells[index];
	}
	
	@Override
	protected void finalize() throws Throwable {
		st.stopThread();
	}

}




