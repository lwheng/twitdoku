package sg.edu.nus.sudoku.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.LinkedList;

import javax.swing.Timer;

import sg.edu.nus.sudoku.gui.Cell;

public class CellController implements  FocusListener,
MouseWheelListener,
MouseListener,
KeyListener{
	private MainController controller;
	private LinkedList<Cell> invalidCells;
	private Timer timer;
	private boolean hintsEnabled;

	public CellController(MainController controller){
		this.controller = controller;
		timer = new Timer(200,new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cell invalidCell = invalidCells.pop();
				updateCellText(invalidCell);
				invalidCell.setInvalid(false);
				invalidCell = null;
				if(invalidCells.isEmpty()) timer.stop();
			}
		});
		timer.setRepeats(true);
		invalidCells = new LinkedList<Cell>();
		hintsEnabled = true;
	}

	@Override
	public void focusGained(FocusEvent e) {
		Cell source = (Cell)e.getSource();
		if(!(source.isEditable())) return;
		source.setText("");
	}


	@Override
	public void focusLost(FocusEvent e) {
		Cell source = (Cell)e.getSource();
		if(!(source.isEditable())) return;
		if(!controller.getSudokuCellValue(source.getId()).equals(source.getText())) {
			source.setInvalid(true);
			invalidCells.add(source);
			timer.start();
		} else {
			source.setInvalid(false);
			invalidCells.remove(source);
		}


		//updateCellText(source);
	}

	public String getAvailableValuesString(Cell c){
		return controller.getAvailableValuesString(c.getId());
	}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void keyTyped(KeyEvent e) {
		Cell c = (Cell)e.getSource();
		if(!(c.isEditable())) return;
		if(c.getText().length()==1) {
			e.consume();
			return;
		}
		//if(c.getText().equals("")) return;
		controller.setSudokuCellValue(c.getId(), Character.toString(e.getKeyChar()));

	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}


	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
	@Override
	public void mousePressed(MouseEvent e) {
		Cell source = (Cell)e.getSource();
		if(!(source.isEditable())) return;
		if(e.getButton()==MouseEvent.BUTTON3) controller.clearSudokuCellValue(source.getId());
		updateCellText(source);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		Cell source = (Cell)e.getSource();
		if(!(source.isEditable())) return;
		if(e.getWheelRotation() ==  1)	controller.setNextAvailableValue(source.getId());
		if(e.getWheelRotation() == -1)	controller.setPreviousAvailableValue(source.getId());
		updateCellText(source);
	}

	public void updateCellText(Cell c){
		c.setText(controller.getSudokuCellValue(c.getId()));
	}

	public boolean updateSudokuCell(Cell c) {
		boolean b = controller.setSudokuCellValue(c.getId(), c.getText());
		return b;
	}

	/**
	 * @return the hintsEnabled
	 */
	public boolean isHintsEnabled() {
		return hintsEnabled;
	}

	/**
	 * @param hintsEnabled the hintsEnabled to set
	 */
	public void setHintsEnabled(boolean hintsEnabled) {
		this.hintsEnabled = hintsEnabled;
	}
}
