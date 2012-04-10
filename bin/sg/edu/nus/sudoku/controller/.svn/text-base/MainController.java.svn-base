package sg.edu.nus.sudoku.controller;
import java.awt.Component;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.commons.httpclient.HttpException;
import sg.edu.nus.sudoku.gui.DialogBoxHelper;
import sg.edu.nus.sudoku.gui.GuiFrame;
import sg.edu.nus.sudoku.gui.MainPanel;
import sg.edu.nus.sudoku.gui.Resources;
import sg.edu.nus.sudoku.gui.SidebarPanel;
import sg.edu.nus.sudoku.gui.Splash;
import sg.edu.nus.sudoku.gui.WelcomePanel;
import sg.edu.nus.sudoku.halloffame.Difficulty;
import sg.edu.nus.sudoku.halloffame.Record;
import sg.edu.nus.sudoku.model.RandomSudokuGenerator;
import sg.edu.nus.sudoku.model.SudokuBoard;
import sg.edu.nus.sudoku.model.SudokuSolver;
import sg.edu.nus.sudoku.persistence.Persistence;
import sg.edu.nus.sudoku.persistence.SaveGame;
import sg.edu.nus.sudoku.twitter.SudokuTweet;
import sg.edu.nus.sudoku.twitter.TwitterConnection;
import sg.edu.nus.sudoku.util.Compressor;
import sg.edu.nus.sudoku.util.WorkerThread;

/**
 * @author Yipeng, Low Wee, Breton and Shawn
 *
 */
public class MainController {

	final private static String GAME_LOADING = "GAME LOADING";
	final private static String GAME_LOADING_MESSAGE = "please wait while I think of a solvable puzzle...";
	final private static String GAME_SOLVING = "SOLVING";
	final private static String GAME_SOLVING_MESSAGE= "please wait while I try to solve this..";
	final private static String GAME_PAUSED = "GAME PAUSED";
	final private static String GAME_PAUSED_MESSAGE = "how long you want me to wait for you? -_-\"";
	final private static String GAME_WIN = "YOU WIN!!!";
	final private static String GAME_WIN_MESSAGE = "You set a high score!";
	final private static String GAME_HIGHSCORE_MESSAGE = "You set a high score! Transferring you to the Hall of Fame...";

	final private static String DIALOG_LOGIN_TITLE = "Twitter Login";
	final private static String DIALOG_LOGIN_FAIL_TITLE = "Twitter Login Failed!";
	final private static String DIALOG_LOGIN_SUCCESS_MESSAGE = "You've successfully logged in!";
	final private static String DIALOG_MESSAGE_TITLE = "Share Game To Twitter";
	final private static String DIALOG_SHARE_SUCCESS_MESSAGE = "Puzzle is shared successfully!";
	final private static String DIALOG_NO_CONNECTION_MESSAGE = "Twitdoku+ was unable to establish a network connection! Please try again!";

	private static RandomSudokuGenerator randomBoardGenerator = new RandomSudokuGenerator();
	private WorkerThread worker = new WorkerThread();
	private SudokuBoard sudokuBoard;
	private GuiFrame guiFrame;
	private MainPanel mainPanel;
	private SidebarPanel sidebarPanel;
	private GameController gameController;
	private Persistence persistence;
	private TwitterConnection twitter;

	private Runnable loadingCode =  new Runnable() {
		public void run() {
			randomBoardGenerator.getRandomSudokuBoard(sudokuBoard);
			if(!worker.isJobInterrupted()) {
				Difficulty d = Difficulty.getDifficulty(sudokuBoard.getNumberOfClues());
				gameController.prepareGame(d);
				sidebarPanel.setTimeLeft(gameController.getTimeLeft());
				showSudokuPanel(SidebarPanel.SUDOKU_SIDEBAR);
			}
		}
	};

	private Runnable updateFeed = new Runnable() {
		public void run() {
			List<SudokuTweet> tweets = null;
			try {
				tweets = twitter.getMainListing();
				mainPanel.getWelcomePanel().updateFeed(tweets);
			}  catch (IOException e) {
				tweets = twitter.getCachedMainListing();
				mainPanel.getWelcomePanel().updateFeed(tweets);
				mainPanel.getWelcomePanel().showNoConnection();
			}
		}
	};

	/**
	 * <tt>initialize()</tt> is called when the programme is started. This method
	 * shows the splash screen and loads up the rest of the GUI.
	 */
	public void initialize(){
		//Persistence
		this.persistence = Persistence.getInstance();

		//Model
		this.sudokuBoard = new SudokuBoard();

		//View
		this.guiFrame = new GuiFrame(this);
		new Splash(this);
		this.mainPanel = this.guiFrame.getMainPanel();
		this.sidebarPanel = this.guiFrame.getSidebarPanel();
		mainPanel.getSettingsPanel().setController(this);
		mainPanel.getHallOfFamePanel().setController(this);
		mainPanel.getSettingsPanel().getCbShowHints().setSelected(persistence.isHintsShown());
		mainPanel.getSettingsPanel().getCbEnableSolve().setSelected(persistence.isSolveGameEnabled());
		mainPanel.getHallOfFamePanel().initializeHallOfFame(persistence.getHallOfFame().getRecords());

		//Controller
		this.gameController = new GameController(this);

		if(persistence.getSavedTweets() != null) {
			this.twitter = TwitterConnection.getInstance(persistence.getSavedTweets());
		}
		else {
			this.twitter = TwitterConnection.getInstance();
		}
	}

	/**
	 * <tt>displayGui()</tt> displays the GuiFrame
	 */
	public void displayGui() {
		//Do GUI Magic
		mainPanel.show(MainPanel.WELCOME_PANEL);
		sidebarPanel.show(SidebarPanel.WELCOME_SIDEBAR);
		guiFrame.setVisible(true);

		//twitter = TwitterConnection.getInstance();
		refreshMainListing();
	}
	
	/**
	 * Refreshes the main feed on the <tt>WelcomePanel</tt> asynchronously.
	 */

	public void refreshMainListing() {
		mainPanel.getWelcomePanel().showLoading();
		new Thread(updateFeed).start();
	}

	/*
	 * Main menu functions
	 */
	/**
	 * Called when user clicks the "New Game" button on the main menu.
	 * Shows the user a "Loading" screen, and proceeds to generate a puzzle.
	 * Once done, it displays the board to the user.
	 */
	public void newGame(){
		loadSettings();
		mainPanel.gameCover(GAME_LOADING, GAME_LOADING_MESSAGE, Resources.STOPWATCH_ICON,true);
		sidebarPanel.show(SidebarPanel.RETURN_TO_MENU_SIDEBAR);
		mainPanel.getSudokuPanel().enableCells();
		worker.queueJob(loadingCode);
	}
	/**
	 * Shows user the hall of fame.
	 */
	public void hallOfFame() {
		sidebarPanel.show(SidebarPanel.HALL_OF_FAME_SIDEBAR);
		mainPanel.show(MainPanel.HALL_OF_FAME_PANEL);
		sidebarPanel.requestFocusInWindow();
	}
	/**
	 * Displays a dialog box prompting user to select a game to play.
	 * Loads the game, and displays the game to the user.
	 */
	public void loadGame(){
		loadSettings();
		String gameName = DialogBoxHelper.showLoadGameDialogue(guiFrame);
		if(gameName==null) return;
		SaveGame sg = persistence.loadGame(gameName);
		sudokuBoard.resetBoard();
		loadGameFromGameString(sg.getGameString(),sg.getGameStateString());
		gameController.prepareGame(Difficulty.getDifficulty(sudokuBoard.getNumberOfClues()),sg.getTimeLeft());
		sidebarPanel.setTimeLeft(gameController.getTimeLeft());
	}
	
	
	/**
	 * Called by {@link WelcomePanel} to load games from tweets.
	 * @param tweet
	 */
	public void loadGameFromTweet(SudokuTweet tweet) {
		loadSettings();
		gameController.setTweet(tweet);
		loadGameFromGameString(tweet.getSudokuString(), null);
	}
	
	/**
	 * Creates a game from a puzzle string and a puzzle-state string.
	 * If the puzzle-state string is null, game will be created as if it were "new" (Only clues, no user input).
	 * 
	 * @param gameString
	 * @param gameStateString
	 */
	public void loadGameFromGameString(String gameString, String gameStateString) {
		loadSettings();
		if(gameStateString!=null) sudokuBoard.deserializeBoardState(gameString,gameStateString);
		else sudokuBoard.deserializeBoard(gameString);
		gameController.prepareGame(Difficulty.getDifficulty(sudokuBoard.getNumberOfClues()));
		sidebarPanel.setTimeLeft(gameController.getTimeLeft());
		showSudokuPanel(SidebarPanel.SUDOKU_SIDEBAR);

	}

	/***
	 * Displays an empty Sudoku board to the user for their Sudoku board input.
	 */
	public void blankMap(){
		showSudokuPanel(SidebarPanel.SUDOKU_BM_SIDEBAR);
	}
	/***
	 * Displays settings panel.
	 */
	public void settings() {
		mainPanel.show(MainPanel.SETTINGS_PANEL);
		sidebarPanel.show(SidebarPanel.RETURN_TO_MENU_SIDEBAR);
	}

	/**
	 * Writes saved games and hall of fame data to file and quits the program.
	 */
	public void exitProgram(){
		if(gameController.isInGame()) {
			if(!exitCurrentGame()) return;
		}
		persistence.setSavedTweets(twitter.getCachedTweets());
		guiFrame.dispose();
		Persistence.writeToFile();
		System.exit(0);
	}
	/*
	 * End of main menu actions
	 */


	/*
	 * Game actions
	 */
	/**
	 * Prompts the user to save game and returns to main menu when they are playing the normal game.
	 */
	public boolean exitCurrentGame(){
		// Save Game Functionality 
		int savRes= DialogBoxHelper.showSaveQueryDialogue(guiFrame);
		if (savRes==0) saveGame();
		else if (savRes==2) return false;

		sudokuBoard.resetBoard();
		gameController.endGame();
		returnToMainMenu();
		return true;
	}

	/**
	 * Prompts the user to save game and returns to main menu when they are using the blank board.
	 */
	public void exitBlankMap(){
		sudokuBoard.resetBoard();
		gameController.endGame();
		returnToMainMenu();
	}
	/**
	 * General method to set GUI to the main menu.
	 */
	public void returnToMainMenu(){
		worker.interrupt();
		sudokuBoard.resetBoard();
		sidebarPanel.show(SidebarPanel.WELCOME_SIDEBAR);
		mainPanel.show(MainPanel.WELCOME_PANEL);
		refreshMainListing();
		sidebarPanel.requestFocusInWindow();
	}

	/**
	 * Displays a dialog box with a text field so user can input the name of their save game.
	 * Prompts the user to key in a new name if a duplicate name is detected in one of the other saved games.
	 * Saves to the persistence object.
	 */
	public void saveGame(){
		mainPanel.gameCover(GAME_PAUSED, GAME_PAUSED_MESSAGE, Resources.STOPWATCH_ICON,false);
		gameController.timerPause();
		String saveName = showSaveGameDialogUntilConfirm();
		while (saveName!=null && saveName.equals("")) saveName = showSaveGameDialogUntilConfirm();
		if(saveName!=null) {
			persistence.saveGame(saveName, sudokuBoard.serializeBoard(), sudokuBoard.serializeBoardState(),gameController.getTimeLeft(),gameController.getTimeTaken());
			DialogBoxHelper.updateSaveGame(saveName);
		} else {
			
		}
		gameController.timerResume();
		mainPanel.show(MainPanel.BOARD_PANEL);
	}
	/**
	 * Serializes game into a string before sharing it on Twitter.
	 * <b>To be implemented.</b>
	 * @param identifier 
	 */
	public void shareGame(String identifier){
		if(showLoginUntilAuthenticated(guiFrame)) {
			String compressedSudokuString = Compressor.stringCompressor(sudokuBoard.serializeBoard()); 
			int charsLeft = TwitterConnection.MAX_TWITTER_LENGTH - compressedSudokuString.length() - " #Twitdoku ".length();
			String userMessage = DialogBoxHelper.showWriteMessageDialog(guiFrame,charsLeft,identifier);
			if (userMessage != null) {
				try {
					twitter.shareSudoku(userMessage,compressedSudokuString);
					DialogBoxHelper.showMessageDialogue(guiFrame, DIALOG_SHARE_SUCCESS_MESSAGE, DIALOG_MESSAGE_TITLE, JOptionPane.INFORMATION_MESSAGE);
				} catch (HttpException e) {
					if(!twitter.isAuthenticated()) {
						DialogBoxHelper.loginToTwitter(guiFrame, DIALOG_LOGIN_FAIL_TITLE, JOptionPane.ERROR_MESSAGE);
					}
					else e.printStackTrace();
				} catch (IOException e) {
					DialogBoxHelper.showMessageDialogue(guiFrame, DIALOG_NO_CONNECTION_MESSAGE, DIALOG_MESSAGE_TITLE, JOptionPane.INFORMATION_MESSAGE);
				}
			}
			else {
				return;
			}
		} else {
			return;
		}
	}
	/**
	 * 
	 * @return false means the user clicked cancel.
	 */
	public boolean showLoginUntilAuthenticated(Component parentComponent) {
		int type = JOptionPane.INFORMATION_MESSAGE;
		String message = DIALOG_LOGIN_TITLE;
		while(!twitter.isAuthenticated()) {
			String[] login = DialogBoxHelper.loginToTwitter(parentComponent, message, type);
			if(login == null) return false;
			try {
				if(twitter.authenticate(login[0], login[1])){
					DialogBoxHelper.showMessageDialogue(parentComponent, DIALOG_LOGIN_SUCCESS_MESSAGE, DIALOG_LOGIN_TITLE, JOptionPane.INFORMATION_MESSAGE);
					return true;
				}
				else {
					type = JOptionPane.ERROR_MESSAGE;
					message = DIALOG_LOGIN_FAIL_TITLE;
				}
			} 
			catch (IOException e) {
				DialogBoxHelper.showMessageDialogue(parentComponent, DIALOG_NO_CONNECTION_MESSAGE, DIALOG_MESSAGE_TITLE, JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		return twitter.isAuthenticated();
	}

	/**
	 * Solves game and fills up the board. Displays user the filled up board and disables the Cells.
	 */
	public void solveGame(){
		SudokuSolver.solveAndFillSudokuBoard(sudokuBoard);
		mainPanel.getSudokuPanel().updatePanel();
		mainPanel.getSudokuPanel().disableCells();
		sidebarPanel.show(SidebarPanel.RETURN_TO_MENU_SIDEBAR);
	}
	/**
	 * Determines if the currently filled up blank board has a unique solution.
	 * 
	 * Method displays a loading page, and attempts to solve the user-specified Sudoku.
	 * If it fails, an error message is shown, and the user is able to continue creating his/her Sudoku.
	 * If there is a unique solution, the game is saved for the user to be able to play at a later time.
	 * 
	 */
	public void checkBlankMap() {
		sudokuBoard.setFilledCellsAsGiven();
		mainPanel.gameCover(GAME_SOLVING, GAME_SOLVING_MESSAGE, Resources.STOPWATCH_ICON, true);
		sidebarPanel.show(SidebarPanel.RETURN_TO_MENU_SIDEBAR);
		worker.queueJob(new Runnable(){
			public void run() {
				if(SudokuSolver.hasUniqueSolution(sudokuBoard)){
					gameController.setTimeLeft(1500);
					String name = showSaveGameDialogUntilConfirm();
					while (name!=null && name.equals("")) name = showSaveGameDialogUntilConfirm();

					if (name!=null){
						persistence.saveGame(name, sudokuBoard.serializeBoardState(), null, gameController.getTimeLeft(), gameController.getTimeTaken());
						DialogBoxHelper.updateSaveGame(name);
						shareGame("Created Game");
					}

				} else {
					DialogBoxHelper.showNoUniqueSolutionDialogue(guiFrame);
				}
				sudokuBoard.setAllCellsAsNotGiven();
				sudokuBoard.clear();
				mainPanel.show(MainPanel.BOARD_PANEL);
				sidebarPanel.show(SidebarPanel.SUDOKU_BM_SIDEBAR);
			}
		});
	}
	/**
	 * Clears all user input from the Sudoku board.
	 */
	public void clearGame() {
		sudokuBoard.clear();
		sudokuBoard.resetBoard();
		mainPanel.getSudokuPanel().updatePanel();
	}
	/**
	 * Prompts user before clearing all user input from the Sudoku board.
	 */
	public void resetGame(){
		int clearStatus = DialogBoxHelper.showClearQueryDialogue(guiFrame);
		if(clearStatus == 0) {
			sudokuBoard.clear();
			mainPanel.getSudokuPanel().updatePanel();
		}
	}

	/**
	 * Shows the dialogbox for users to paste an 81-character puzzle string to save and share.
	 */
	public void createFromString() {
		String strValue = DialogBoxHelper.showStringCreationDialog(guiFrame);
		if (strValue==null){sudokuBoard.setAllCellsAsNotGiven();return;}
		for(int i=0; i<81; i++){
			char j = strValue.charAt(i);
			sudokuBoard.setValue(i, String.valueOf(j));
		}
		checkBlankMap();
		sudokuBoard.clear();
	}

	private void doGameWinActions() {
		SudokuTweet tweet = gameController.getTweet();
		int timeLeft = gameController.getTimeLeft();
		Record record = gameController.gameWon();
		record.setGameString(sudokuBoard.serializeBoard());
		sidebarPanel.show(SidebarPanel.RETURN_TO_MENU_SIDEBAR);
		if(timeLeft > 0 && persistence.getHallOfFame().addRecord(record)) {
			mainPanel.gameCover(GAME_WIN, GAME_HIGHSCORE_MESSAGE, Resources.VICTORY_ICON, false);
			record.setPlayerName(DialogBoxHelper.showEnterNameDialogue(guiFrame));
			mainPanel.getHallOfFamePanel().updateHighScore(record.getGameDifficulty());
			mainPanel.show(MainPanel.HALL_OF_FAME_PANEL);
			sidebarPanel.show(SidebarPanel.HALL_OF_FAME_SIDEBAR);
		}
		else {
			mainPanel.gameCover(GAME_WIN, GAME_WIN_MESSAGE, Resources.VICTORY_ICON, false);
		}
		if(tweet==null) {
			shareGame("I just completed this sudoku!");
		} else {
			shareGame("@"+tweet.getAuthor()+" I just completed this sudoku too!");
		}
		sudokuBoard.resetBoard();
		mainPanel.getSudokuPanel().redrawPanel();
	}

	/*
	 * End of save game Actions
	 */

	/*
	 * Helper methods
	 */
	private String showSaveGameDialogUntilConfirm() {
		String gameName = DialogBoxHelper.showSaveGameDialogue(guiFrame);
		if(gameName == null) return null;
		while(persistence.gameExists(gameName)){
			if(DialogBoxHelper.showConfirmOverwriteDialogue(guiFrame)) break;
			else {
				gameName = DialogBoxHelper.showSaveGameDialogue(guiFrame);
				if(gameName == null) return null;
			}
		}
		return gameName;
	}

	private void doSetValueActions(){
		gameController.start();
		if(sudokuBoard.verifyWin()) doGameWinActions();
	}


	private void showSudokuPanel(String sidebarString) {
		mainPanel.getSudokuPanel().redrawPanel();
		sidebarPanel.show(sidebarString);
		sidebarPanel.requestFocusInWindow();
		mainPanel.show(MainPanel.BOARD_PANEL);
	}

	/**
	 * Updates the SidebarPanel during the running of the game.
	 * Called by {@link GameController}
	 */
	protected void updateGameUI(){
		sidebarPanel.setTimeLeft(gameController.getTimeLeft());
		if (gameController.getTimeLeft() == 0) sidebarPanel.setBtnSolveGameEnable(true);
	}
	/**
	 * Sets the value of the {@link SudokuCell} with the ID: <tt>cellId</tt> to the String: <tt>s</tt>
	 * 
	 * @param cellId Cell ID
	 * @param s		 String to set.
	 * @return 		 The success of the operation.
	 */
	protected boolean setSudokuCellValue(int cellId,String s){
		boolean b = sudokuBoard.setValue(cellId, s);
		doSetValueActions();
		return b;
	}
	/**
	 * Clears the contents of Cell with ID: <tt></tt>
	 * @param cellId Cell ID
	 */
	protected void clearSudokuCellValue(int cellId){
		sudokuBoard.clearValue(cellId);
	}
	/**
	 * Clears the cell of its value
	 * @param cellId Cell ID
	 * @return
	 */
	protected String getSudokuCellValue(int cellId){
		return sudokuBoard.getValue(cellId);
	}

	/**
	 * Sets the SudokuCell's value to its next possible value.
	 * @param cellId Cell ID
	 */
	protected void setNextAvailableValue(int cellId){
		sudokuBoard.setNextAvailableValue(cellId);
		doSetValueActions();
	}
	/**
	 * Sets the SudokuCell's value to its previous possible value.
	 * @param cellId Cell ID
	 */
	protected void setPreviousAvailableValue(int cellId){
		sudokuBoard.setPreviousAvailableValue(cellId);
		doSetValueActions();
	}
	/**
	 * Get the possible values for the current cell as a comma-delimited string. 
	 * @param cellId Cell ID
	 */
	protected String getAvailableValuesString(int cellId){
		return sudokuBoard.getAvailableValues(cellId).toString();
	}
	/**
	 * Returns <tt>true</tt> if value is a system provided value.
	 * @param cellId Cell ID
	 * @return
	 */
	public boolean isCellGiven(int cellId){
		return sudokuBoard.isCellGiven(cellId);
	}
	
	/**
	 * Called to save settings to the {@link Persistence} object.
	 */

	public void saveSettings() {
		persistence.setHintsShown(mainPanel.getSettingsPanel().getCbShowHints().isSelected());
		persistence.setSolveGameEnabled(mainPanel.getSettingsPanel().getCbEnableSolve().isSelected());
		loadSettings();
	}
	
	/**
	 * Called to load settings from the {@link Persistence} object.
	 */

	public void loadSettings() {
		CellController cc = mainPanel.getSudokuPanel().getCellController();
		cc.setHintsEnabled(persistence.isHintsShown());
		mainPanel.getSettingsPanel().getCbEnableSolve().setSelected(persistence.isSolveGameEnabled());
		sidebarPanel.setBtnSolveGameEnable(persistence.isSolveGameEnabled());
	}
	
	protected boolean isHintsShown() {
		return persistence.isHintsShown();
	}
}
