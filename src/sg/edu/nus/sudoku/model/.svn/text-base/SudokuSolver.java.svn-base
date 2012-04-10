package sg.edu.nus.sudoku.model;

import java.util.LinkedList;

/**
 * 
 * A static class providing methods to solve SudokuBoard objects.
 * @author Shawn, Low Wee, Yipeng and Breton
 *
 */
public class SudokuSolver {
	final private static InterruptedException interruptedException = new InterruptedException();
	
	/**
	 * Solves and fills the cells of the SudokuBoard object passed to it.
	 * Also sets the <tt>solution</tt> attribute of the SudokuBoard object.
	 * @param board SudokuBoard to solve
	 * @return returns <tt>true</tt> if the board is solvable.
	 */
	public static boolean solveAndFillSudokuBoard(SudokuBoard board){
		String solution =board.getSolution();
		if(solution == null) {
			SudokuCell[] cells = board.getCells();
			LinkedList<String> solutions = new LinkedList<String>();
			//int fillStatus = 0;

			try {
				//fillStatus = 
				fillCells(cells,0,solutions,1);
			} catch (InterruptedException e) {}

			if(solutions.size()>0) {
				solution = solutions.peekFirst();
				board.setSolution(solution);
				
			}
		}
		board.deserializeBoardState(solution);
		return solution!=null;
	}
	
	/**
	 * Determines if board has a unique solution.
	 * @param board SudokuBoard to evaluate
	 * @return <tt>true</tt> if solution is unique.
	 */
	public static boolean hasUniqueSolution(SudokuBoard board) {
		SudokuCell[] cells = board.getCells();
		try {
			int solutions = fillCells(cells, 0, null, 2);
			return solutions==1;
		} catch (InterruptedException e) {}
		return false;
	}
	
	/**
	 * Internal method used by this class and {@link RandomSudokuGenerator} to solve the SudokuBoard. <br/>
	 * This method recursively tries to fill the SudokuCells, and checks for validity. It backtracks if no possible value is found
	 * and tries another value, continuously doing this until it solves the board. If no solution is found, it returns <tt>false</tt>
	 * @param cells 81-length array of SudokuCell
	 * @param index the index in the array to start from.
	 * @param solutions <tt>LinkedList</tt> of possible solutions. <tt>null</tt> is a valid parameter.
	 * @param stopAt Number of solutions to stop the recursion at.
	 * @return <tt>true</tt> if a solution is found.
	 * @throws InterruptedException
	 */
	protected static int fillCells(SudokuCell[] cells, int index,LinkedList<String> solutions,int stopAt) throws InterruptedException{
		if(Thread.interrupted()) throw SudokuSolver.interruptedException;
		if(index == 81){
			addSolutionString(solutions,cells);
			return 1;
		}

		SudokuCell currentCell = cells[index];
		if(currentCell.isGiven()) return fillCells(cells,index+1,solutions,stopAt);

		int possibleSolutions = 0;

		SudokuValue[] values = currentCell.getAvailableValues().getArray();
		int i=0;
		while(i<values.length){
			while(i<values.length && values[i]==null ) i++;
			if(i<values.length) {

				currentCell.setValue(values[i]);
				i++;
				if(!checkCellConstraints(currentCell)) continue;
				//if(_fillCells(cells,index+1)) return true;
				possibleSolutions = possibleSolutions + fillCells(cells,index+1,solutions,stopAt);
				if(stopAt==-1);
				else if(possibleSolutions>stopAt){
					currentCell.clear();
					return possibleSolutions;
				}
			}
		}
		currentCell.clear();
		return possibleSolutions;
	}

	private static void addSolutionString(LinkedList<String> solutions,SudokuCell cells[]){
		//System.out.println("Solution found!");
		if(solutions!=null){
			StringBuffer sb = new StringBuffer(81);
			for(int i = 0; i < cells.length; i++){ 
				sb.append(cells[i].getValue());
			}
			solutions.add(sb.toString());
		}
	}
	
	/**
	 * Checks the SudokuCell's SudokuConstraints for their SudokuCell's number of available values
	 * @param c SudokuCell to check
	 * @return Returns <tt>true</tt> if all SudokuCells affected by this SudokuCell has more than 1 available value.
	 */
	protected static boolean checkCellConstraints(SudokuCell c){
		SudokuConstraint
		constraint = c.getRowConstraint();
		if(!checkConstraint(constraint,c)) return false;
		constraint = c.getColumnConstraint();
		if(!checkConstraint(constraint,c)) return false;
		constraint = c.getSquareConstraint();
		if(!checkConstraint(constraint,c)) return false;
		return true;
	}

	/**
	 * Checks the constraint with respect to the SudokuCell. <br/>
	 * Returns <tt>false</tt> if SudokuCells with no more available values are found.
	 * @param constraint Constraint to check
	 * @param c SudokuCell to check with respect to.
	 * @return Returns <tt>true</tt> if all SudokuCells have at least 1 available values.
	 */
	protected static boolean checkConstraint(SudokuConstraint constraint,SudokuCell c){
		SudokuCell cells[] = constraint.getSudokuCells();
		for(SudokuCell sc:cells){
			if(sc==c) continue;
			if(sc.getAvailableValues().size() == 0) return false;
		}
		return true;
	}



}
