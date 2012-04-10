package sg.edu.nus.sudoku.gui;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import sg.edu.nus.sudoku.controller.MainController;
import sg.edu.nus.sudoku.persistence.Trivia;

/**
 * 
 * This class forms up the menu on the right-hand side of the screen.
 * @author Your Name Here
 *
 */
public class SidebarPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JPanel welcomeSidebarPanel;
	private JPanel sudokuSidebarPanel;
	private JPanel sudokuBMPanel;
	private JPanel hallOfFameSidebarPanel;
	private JPanel returnToMenuPanel;
	private MainController controller;
	private CardLayout cardLayout;
	final public static String WELCOME_SIDEBAR = "welcomeSidebar";
	final public static String SUDOKU_SIDEBAR = "sudokuSidebar";
	final public static String SUDOKU_BM_SIDEBAR = "blankMapSidebar";
	final public static String HALL_OF_FAME_SIDEBAR = "hallOfFameSidebar";
	final public static String RETURN_TO_MENU_SIDEBAR = "returnToMenuSidebar";

	private JTextField txtTimer;
	final private static String TIMER_TEXT_NO_TIME = "Time's UP!";
	private JTextArea lblTrivia;
	private JButton btnSolveGame;

	MouseListener menuFocusListener =  new MouseAdapter() {
		public void mouseEntered(MouseEvent arg0) {
			((JButton) arg0.getSource()).requestFocus();
		}
		//mouseadapter doesn't require you to implement the unused methods. Cleaner code!
	};

	private ActionListener newGameAction = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			controller.newGame();
		}
	};
	private ActionListener blankGameAction = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			controller.blankMap();
		}
	};

	private ActionListener halloffameAction = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			controller.hallOfFame();
		}
	};

	private ActionListener saveGameAction = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			controller.saveGame();
		}
	};

	private ActionListener solveGameAction = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			controller.solveGame();
		}
	};

	private ActionListener checkGameAction = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			controller.checkBlankMap();
		}
	};

	private ActionListener returnToMenuAction = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			controller.returnToMainMenu();
		}
	};

	private ActionListener exitGameAction = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			controller.exitCurrentGame();
		}
	};

	private ActionListener exitBlankMap = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			controller.exitBlankMap();
		}
	};

	private ActionListener shareGameAction = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			controller.shareGame("I'm trying to solve this puzzle!");
		}
	};

	private ActionListener loadGameAction =  new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			controller.loadGame();
		}
	};
	private ActionListener settingsAction =  new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			controller.settings();
		}
	};
	private ActionListener resetGameAction =  new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			controller.resetGame();
		}
	};
	private ActionListener clearGameAction = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			controller.clearGame();
		}
	};
	private ActionListener exitSudokuProgramAction =  new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			controller.exitProgram();
		}
	};
	private ActionListener createStringAction =  new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			controller.createFromString();
		}
	};
	private ActionListener refreshAction =  new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			controller.refreshMainListing();
		}
	};






	/**
	 * @param controller
	 */
	public SidebarPanel(MainController controller) {
		this.cardLayout = new CardLayout();
		this.controller = controller;

		this.welcomeSidebarPanel = getWelcomeSidebarPanel();
		this.sudokuSidebarPanel = getSudokuSidebarPanel();
		this.sudokuBMPanel = getSudokuBlankMapPanel();
		this.hallOfFameSidebarPanel = getHallOfFamePanel();
		this.returnToMenuPanel = getReturnToMenuPanel();
		this.setFont(Resources.STANDARD_FONT);
		this.setLayout(this.cardLayout);
		this.add(this.welcomeSidebarPanel, WELCOME_SIDEBAR);
		this.add(this.sudokuSidebarPanel, SUDOKU_SIDEBAR);
		this.add(this.sudokuBMPanel, SUDOKU_BM_SIDEBAR);
		this.add(this.hallOfFameSidebarPanel, HALL_OF_FAME_SIDEBAR);
		this.add(returnToMenuPanel,RETURN_TO_MENU_SIDEBAR);
	}

	/**
	 * 
	 * Shows the relevant menu panel for the corresponding modes.
	 * @param SidebarPanel.WELCOME_SIDEBAR, SidebarPanel.SUDOKU_SIDEBAR
	 *
	 */
	public void show(String name) {
		cardLayout.show(this, name);
		lblTrivia.setText(Trivia.getInstance().getRandomTrivia());
	}

	public void setTimeLeft(int timeLeft){
		if(timeLeft>0) {
			txtTimer.setText(Integer.toString(timeLeft));
		} else {
			txtTimer.setText(TIMER_TEXT_NO_TIME);
		}
	}

	/**
	 * @return
	 */
	private JPanel getWelcomeSidebarPanel() {
		JPanel panel = new JPanel();
		GridLayout layout = new GridLayout(9, 1);
		panel.setLayout(layout);
		layout.setVgap(5);
		layout.setHgap(2);
		panel.add(createButton("Generate Game", newGameAction,Resources.NEWGAME_ICON));
		panel.add(createButton("Create Ur Own",blankGameAction,Resources.BLANKMAP_ICON));
		panel.add(createButton("Load Game",loadGameAction,Resources.LOADGAME_ICON));
		panel.add(createButton("Hall Of Fame", halloffameAction, Resources.HALLOFFAME_ICON));
		panel.add(createButton("Settings",settingsAction, Resources.SETTINGS_ICON));
		panel.add(createButton("Refresh Feed", refreshAction, Resources.REFRESH_ICON));
		panel.add(createButton("Exit Game",exitSudokuProgramAction,Resources.EXITGAME_ICON));
		return panel;
	}

	/**
	 * @return
	 */
	private JPanel getSudokuSidebarPanel() {
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints c  = new GridBagConstraints();
		c.anchor = GridBagConstraints.CENTER;
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 0;
		c.weighty=0.5;
		c.insets.top = 5;
		c.insets.left = 2;
		panel.add(createButton("Save Game",saveGameAction,Resources.SAVEGAME_ICON), c);
		c.gridy = 1;
		panel.add(createButton("Share Game",shareGameAction,Resources.SHAREGAME_ICON), c);
		c.gridy = 2;
		panel.add(getBtnSolveGame(), c);
		//panel.add(createButton("Solve Game",solveGameAction,Resources.SOLVER_ICON), c);
		c.gridy = 3;
		panel.add(createButton("Clear Board",resetGameAction, Resources.CLEARBOARD_ICON), c);
		c.gridy = 4;
		panel.add(createButton("Return to Menu",exitGameAction,Resources.RETURNTOMENU_ICON), c);
		JLabel lblTime =  new JLabel("Time left:");
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		c.gridy = 5;
		c.ipady = 35;

		panel.add(getTimerTextField(), c);
		lblTrivia = new JTextArea(Trivia.getInstance().getRandomTrivia());
		lblTrivia.setFont(Resources.TRIVIA_FONT);
		lblTrivia.setEditable(false);
		lblTrivia.setBorder(null);
		lblTrivia.setLineWrap(true);
		lblTrivia.setWrapStyleWord(true);
		JScrollPane scroller = new JScrollPane(lblTrivia);
		scroller.setBorder(null);
		scroller.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener(){
			public void adjustmentValueChanged(AdjustmentEvent e){
				lblTrivia.select(0,0);
			}});
		c.gridy = 6;
		c.ipady = 130;
		panel.add(scroller, c);
		return panel;
	}

	private JPanel getSudokuBlankMapPanel() {
		JPanel panel = new JPanel();
		GridLayout layout = new GridLayout(9, 1);
		panel.setLayout(layout);
		layout.setVgap(5);
		layout.setHgap(2);
		panel.add(createButton("Check & Share",checkGameAction,Resources.SHAREGAME_ICON));
		panel.add(createButton("String Creation",createStringAction,Resources.SHAREGAME_ICON));
		panel.add(createButton("Clear Board",clearGameAction, Resources.CLEARBOARD_ICON));//in BlankMap mode, allow users to Clear map too.
		panel.add(createButton("Return to Menu",exitBlankMap,Resources.RETURNTOMENU_ICON));
		return panel;
	}

	private JPanel getHallOfFamePanel() {
		JPanel panel = new JPanel();
		GridLayout layout = new GridLayout(9, 1);
		panel.setLayout(layout);
		layout.setVgap(5);
		layout.setHgap(2);
		panel.add(createButton("Return to Menu", returnToMenuAction ,Resources.RETURNTOMENU_ICON));
		return panel;
	}


	private JPanel getReturnToMenuPanel() {
		JPanel panel = new JPanel();
		GridLayout layout = new GridLayout(9, 1);
		panel.setLayout(layout);
		layout.setVgap(5);
		layout.setHgap(2);
		panel.add(createButton("Return to Menu", returnToMenuAction ,Resources.RETURNTOMENU_ICON));
		return panel;
	}

	private JTextField getTimerTextField(){
		if(txtTimer == null) {
			txtTimer = new JTextField();
			txtTimer.setHorizontalAlignment(SwingConstants.CENTER);
			txtTimer.setFont(Resources.CELL_FONT);
			txtTimer.setBorder(BorderFactory.createTitledBorder("Time Left: "));
			txtTimer.setEditable(false);
		}
		return txtTimer;

	}

	/**
	 * The reason I created this method is so that we have a generalised method for creating
	 * buttons for this panel.
	 * In the future, we may want to add images to the buttons, and this may make our job
	 * a lot easier.
	 * @param btnText
	 * @param action
	 * @param object 
	 * @return
	 */
	private JButton createButton(String btnText,ActionListener action, Icon icon){
		JButton btn = createNewButtonWithText(btnText);
		btn.addActionListener(action);
		if(action==null) btn.setEnabled(false); //for developmental purposes.
		if(icon!=null) btn.setIcon(icon);
		btn.setHorizontalAlignment(SwingConstants.LEFT);
		btn.addMouseListener(menuFocusListener);
		return btn;
	}

	/**
	 * @param btnText
	 * @return
	 */
	private JButton createNewButtonWithText(String btnText){
		JButton temp = new JButton(btnText);
		temp.setBorder(BorderFactory.createTitledBorder(""));
		return temp;
	}

	private JButton getBtnSolveGame() {
		if (btnSolveGame == null) {
			btnSolveGame = new JButton("Solve Game");
			btnSolveGame.setBorder(BorderFactory.createTitledBorder(""));
			btnSolveGame.addActionListener(solveGameAction);
			btnSolveGame.setIcon(Resources.SOLVER_ICON);
			btnSolveGame.setHorizontalAlignment(SwingConstants.LEFT);
			btnSolveGame.addMouseListener(menuFocusListener);
			btnSolveGame.setEnabled(false);
		}
		return btnSolveGame;
	}

	public void setBtnSolveGameEnable(boolean solveGameEnable) {
		btnSolveGame.setEnabled(solveGameEnable);
	}
}
