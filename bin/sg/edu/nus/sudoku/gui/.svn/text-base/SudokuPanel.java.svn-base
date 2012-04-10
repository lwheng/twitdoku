package sg.edu.nus.sudoku.gui;

import java.awt.GridLayout;

import javax.swing.JPanel;

import sg.edu.nus.sudoku.controller.CellController;
import sg.edu.nus.sudoku.controller.MainController;


/**
 * SudokuPanel takes in a SudokuBoard objects and displays it on a 9 by 9 grid.
 * @author Your Name Here
 *
 */
public class SudokuPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private MainController controller;
	private CellController cellController;
	public CellController getCellController() {
		return cellController;
	}

	private Cell[] cells;
	public SudokuPanel(MainController controller) {
		this.controller = controller;
		this.cellController = new CellController(controller);
		this.cells = new Cell[81];
		GridLayout layout = new GridLayout(3, 3);
		layout.setVgap(9);
		layout.setHgap(9);
		this.setLayout(layout);

		JPanel[] squarePanels = new JPanel[9];
		
		int row = 0;
		int col = 0;
		int square = 0;

		for(int i=0;i<cells.length;i++){
			row = i/9;
			col = i%9;
			square = (row/3) * 3 + (col/3);
			if(squarePanels[square]==null) squarePanels[square]=new JPanel(new GridLayout(3, 3));
			cells[i] = new Cell(i,cellController);
			if(this.controller.isCellGiven(i)) {
				cells[i].setGiven(true);
			}
			squarePanels[square].add(cells[i]);
		}
		for(int j=0;j<squarePanels.length;j++){
			this.add(squarePanels[j]);
		}
		updatePanel();
	}
	public void updatePanel(){
		for(Cell c: cells){
			if (c != null) {
				cellController.updateCellText(c);
			}
		}
	}
	public void redrawPanel(){
		for(int i=0;i<cells.length;i++){
			cells[i].setGiven(controller.isCellGiven(i));
		}
		cells[0].setEditable(false); //crude hack. keeps getting focus whenever panel updates, retains its old value;
		updatePanel();
		cells[0].setEditable(true);
	}
	
	public void enableCells() {
		for(int i=0; i<cells.length; i++) {
			if(this.controller.isCellGiven(i)) {
				cells[i].setEditable(false);
			}
			else {
				cells[i].setEditable(true);
			}
		}
	}
	
	public void disableCells() {
		for(int i=0; i<cells.length; i++) {
			cells[i].setEditable(false);
		}
	}

}
