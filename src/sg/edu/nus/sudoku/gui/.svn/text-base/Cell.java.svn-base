package sg.edu.nus.sudoku.gui;


import java.awt.Color;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import sg.edu.nus.sudoku.controller.CellController;

/**
 * The Cell is an extension of a JFormattedTextField, with behaviours included
 * so that it would function as part of the <tt>SudokuPanel</tt>
 * 
 * The entity handling its backend logic is the <tt>SudokuCellsss</tt>
 * @author Your Name Here
 *
 */
public class Cell extends JTextField {
	
	private static final long serialVersionUID = 1L;
	private int id;
	private CellController controller;
	private TitledBorder border;
	
	/**
	 * Cell constructor.
	 */
	public Cell(int id, CellController cellControl) {
		this.id = id;
		
		this.setFont(Resources.CELL_FONT);
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.controller = cellControl;
		this.addFocusListener(controller);
		this.addMouseWheelListener(controller);
		this.addMouseListener(controller);
		this.addKeyListener(controller);
		//this.set(SwingConstants.BOTTOM);
		this.setToolTipText("");

		this.border=BorderFactory.createTitledBorder("");
		this.setBorder(border);
	}
	
	protected void setGiven(boolean given) {
		if(given){
			this.setEditable(false);
			this.setForeground(Resources.NON_EDITABLE_CELL_COLOR);

		} else {
			this.setEditable(true);
			this.setForeground(Color.BLACK);
		}
	}
	
	public void setInvalid(boolean invalid) {
		if(invalid){
			this.setEditable(false);
			this.setForeground(Color.RED);

		} else {
			this.setEditable(true);
			this.setForeground(Color.BLACK);
		}
	}
	
	public int getId() {
		return id;
	}


	/* (non-Javadoc)
	 * @see javax.swing.text.JTextComponent#getToolTipText(java.awt.event.MouseEvent)
	 */
	@Override
	public String getToolTipText(MouseEvent event) {
		//return sudokuCell.identifier;
		if(isEditable() && controller.isHintsEnabled()) return controller.getAvailableValuesString(this);
		else return null;
	}


}
