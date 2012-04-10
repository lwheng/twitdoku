package sg.edu.nus.sudoku.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import sg.edu.nus.sudoku.controller.MainController;

public class SettingsPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8914877157152370771L;
	private JCheckBox cbShowHints, cbEnableSolve;
	private JLabel lblTooltipIcon;
	private MainController controller;
	private ActionListener generalComponentActionListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			controller.saveSettings();
		}
	};
	
	public void setController(MainController controller) {
		this.controller = controller;
	}


	public SettingsPanel() {
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		GridBagConstraints c = new GridBagConstraints();
		
		c.anchor = GridBagConstraints.PAGE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.weighty = 0.5;
		
		c.gridx = 0;
		c.gridy = 0;
		JLabel lblTitle = new JLabel("  Settings");
		lblTitle.setFont(Resources.CELL_FONT);
		lblTitle.setIcon(Resources.SETTINGS_ICON);
		add(lblTitle,c);
		
		c.weighty = 10;
		c.gridx = 0;
		c.gridy = 1;
		add(getCbShowHints(),c);
		
		c.weightx = 10;
		c.gridx = 1;
		c.gridy = 1;
		add(getTooltipIcon(), c);
		
		c.weighty = 30;
		c.gridx = 0;
		c.gridy = 2;
		add(getCbEnableSolve(),c);
		
	}
	
	
	public JCheckBox getCbShowHints() {
		if(cbShowHints == null) {
			cbShowHints = new JCheckBox("Show tooltip hints.");
			cbShowHints.setBorder(BorderFactory.createTitledBorder(""));;
			cbShowHints.addActionListener(generalComponentActionListener);
		}
		return cbShowHints;
	}
	
	public JLabel getTooltipIcon() {
		if(lblTooltipIcon == null) {
			lblTooltipIcon = new JLabel("");
			lblTooltipIcon.setIcon(Resources.TOOLTIP_ICON);
		}
		return lblTooltipIcon;
	}
	
	public JCheckBox getCbEnableSolve() {
		if(cbEnableSolve == null) {
			cbEnableSolve = new JCheckBox("Enable Solve Game Function");
			cbEnableSolve.setBorder(BorderFactory.createTitledBorder(""));;
			cbEnableSolve.addActionListener(generalComponentActionListener);
		}
		return cbEnableSolve;
	}
}
