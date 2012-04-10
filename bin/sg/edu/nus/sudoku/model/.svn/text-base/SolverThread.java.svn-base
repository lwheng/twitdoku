package sg.edu.nus.sudoku.model;

import java.util.LinkedList;

public class SolverThread extends Thread {
	private RandomSudokuGenerator rsg;
	private boolean solving = false;
	private boolean stopThread = false;
	private LinkedList<String> solutions = new LinkedList<String>();
	SolverThread(RandomSudokuGenerator rsg) {
		this.rsg = rsg;
		setDaemon(true);
		setName("SolverThread");
	}
	public void stopThread() {
		stopThread = true;
	}
	public void run() {

		while(true){
			try {
				SudokuBoard board = null;

				board = rsg.getSudokuBoard();
				solving = true;
				SudokuCell[] cells = board.getCells();
				int noOfSolutions = SudokuSolver.fillCells(cells,0,solutions,2);
				if(noOfSolutions==1) {
					board.setSolution(solutions.getLast());
					solutions.clear();
				}
				rsg.putSolutionNumber(noOfSolutions);
				//System.out.println("Solved!");
				solving = false;

			} catch (InterruptedException e){
				//System.out.println("Solver interrupted");
				if(stopThread) break;
			}
		}
	}
	public boolean isSolving(){
		return solving;
	}
}


