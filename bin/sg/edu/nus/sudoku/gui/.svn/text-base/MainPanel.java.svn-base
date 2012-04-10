package sg.edu.nus.sudoku.gui;

import java.awt.CardLayout;

import javax.swing.Icon;
import javax.swing.JPanel;

import sg.edu.nus.sudoku.controller.MainController;
import sg.edu.nus.sudoku.persistence.Trivia;

public class MainPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	final public static String WELCOME_PANEL = "welcome";
	final public static String BOARD_PANEL = "board";
	final public static String HALL_OF_FAME_PANEL = "hallOfFame";
	final public static String GAMECOVER_PANEL = "gamecover";
	final public static String SETTINGS_PANEL = "settings";
	private CardLayout cardLayout;

	private SudokuPanel sudokuPanel;
	private WelcomePanel welcomePanel;
	private HallOfFamePanel hallOfFamePanel;
	private GameCoverPanel gameCoverPanel;
	private SettingsPanel settingsPanel;

	public SettingsPanel getSettingsPanel() {
		return settingsPanel;
	}

	/**
	 * @param controller
	 */
	public MainPanel(MainController controller) {

		this.cardLayout = new CardLayout();
		this.welcomePanel = new WelcomePanel(controller);
		this.hallOfFamePanel = new HallOfFamePanel();
		this.gameCoverPanel = new GameCoverPanel();
		this.settingsPanel = new SettingsPanel();
		
		// yipeng moved the init of SudokuPanel here 
		setSudokuPanel(new SudokuPanel(controller));
		//this.sudokuPanel = new SudokuPanel(this.controller);
		this.setLayout(cardLayout);
		this.add(this.sudokuPanel, BOARD_PANEL);
		this.add(this.welcomePanel, WELCOME_PANEL);
		this.add(this.gameCoverPanel, GAMECOVER_PANEL);
		this.add(this.hallOfFamePanel, HALL_OF_FAME_PANEL);
		this.add(this.settingsPanel, SETTINGS_PANEL);
		
	}
	
	/**
	 * @return sudokuPanel
	 */
	public SudokuPanel getSudokuPanel() {
		return sudokuPanel;
	}

	/**
	 * @return welcomePanels
	 */
	public WelcomePanel getWelcomePanel() {
		return welcomePanel;
	}

	public HallOfFamePanel getHallOfFamePanel() {
		return hallOfFamePanel;
	}
	
	public GameCoverPanel getGameCoverPanel() {
		return gameCoverPanel;
	}
	
	/**
	 * Used by the MainController to set a new SudokuPanel before starting a new game,
	 * or loading one.
	 * @param sudokuPanel
	 */
	public void setSudokuPanel(SudokuPanel sudokuPanel) {
		
		if(this.sudokuPanel!=null) this.remove(this.sudokuPanel);
		this.add(sudokuPanel, BOARD_PANEL);
		this.sudokuPanel = sudokuPanel;
	}

	/**
	 * Used by the MainController to show panels in MainPanel's CardLayout.
	 * @param MainPanel.BOARD_PANEL,MainPanel.WELCOME_PANEL
	 */
	public void show(String name) {
		cardLayout.show(this, name);
	}
	
	public void gameCover(String mainTextToShow, String anyOtherSubText, Icon anyIcon,boolean progressBar) {
		gameCoverPanel.setTitle(mainTextToShow);
		gameCoverPanel.setTriviaMsg(Trivia.getInstance().getRandomTrivia());
		gameCoverPanel.setMessage(anyOtherSubText);
		gameCoverPanel.setIcon(anyIcon);
		gameCoverPanel.setProgressBarVisible(progressBar);
		this.show(MainPanel.GAMECOVER_PANEL);
	}
}
