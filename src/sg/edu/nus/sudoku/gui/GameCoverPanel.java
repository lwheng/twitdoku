package sg.edu.nus.sudoku.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class GameCoverPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel lblTitle;
	private JTextArea lblTrivia;
	private JLabel lblIcon;
	private JLabel lblMessage;
	private JProgressBar progressBar;
	GameCoverPanel() {
		GridBagLayout layout = new GridBagLayout();
		
		this.setLayout(layout);
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 0.5;
		c.weighty = 0.5;
		
		
		lblTitle = new JLabel("GameCover", SwingConstants.CENTER);
		lblTitle.setFont(Resources.CELL_FONT);
		lblTitle.setVerticalAlignment(SwingConstants.BOTTOM);
		c.anchor = GridBagConstraints.CENTER;
		c.ipady = 0;
		c.gridx = 0;
		c.gridy = 0;
		c.insets.top =70;
		c.fill = GridBagConstraints.HORIZONTAL;
		this.add(lblTitle,c);
		
		lblTrivia = new JTextArea("Trivia Message");
		lblTrivia.setEditable(false);
		lblTrivia.setBorder(null);
		lblTrivia.setLineWrap(true);
		lblTrivia.setWrapStyleWord(true);
		lblTrivia.setFont(Resources.TRIVIA_FONT);
		lblTrivia.setAlignmentX(SwingConstants.CENTER);
		lblTrivia.setAlignmentY(SwingConstants.BOTTOM);
		//lblTrivia.setVerticalAlignment(SwingConstants.BOTTOM);
		c.anchor = GridBagConstraints.CENTER;
		c.ipady = 0;
		c.gridx = 0;
		c.gridy = 3;
		c.insets.top =70;
		c.fill = GridBagConstraints.HORIZONTAL;
		this.add(lblTrivia,c);
		
		lblIcon = new JLabel();
		lblIcon.setIcon(Resources.RETURNTOMENU_ICON);
		lblIcon.setHorizontalAlignment(SwingConstants.CENTER);
		c.anchor = GridBagConstraints.CENTER;
		c.ipady = 0;
		c.gridx = 0;
		c.gridy = 1;
		c.insets.top =0;
		c.fill = GridBagConstraints.HORIZONTAL;
		this.add(lblIcon,c);
		
		progressBar = new JProgressBar();
		progressBar.setIndeterminate(true);
		progressBar.setVisible(false);
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;
		c.gridx = 0;
		c.gridy=2;
		c.insets.top = 0;
		this.add(progressBar,c);
		
		lblMessage = new JLabel("GameCover Message.",SwingConstants.CENTER);
		lblMessage.setVerticalAlignment(SwingConstants.TOP);
		c.anchor = GridBagConstraints.CENTER;
		c.ipady = 0;
		c.gridx = 0;
		c.gridy = 3;
		c.insets.bottom = 70;
		c.fill = GridBagConstraints.HORIZONTAL;
		this.add(lblMessage,c);
		
		


		
		
	}
	
	public void setTitle(String mainTextToShow) {
		this.lblTitle.setText(mainTextToShow);
	}
	
	public void setTriviaMsg(String triviaMsg) {
		this.lblTrivia.setText(triviaMsg);
	}
	
	public void setMessage(String anyOtherSubText) {
		this.lblMessage.setText(anyOtherSubText);
	}
	
	public void setIcon(Icon icon) {
		this.lblIcon.setIcon(icon);
	}
	public void setProgressBarVisible(boolean visible){
		progressBar.setVisible(visible);
	}
}
