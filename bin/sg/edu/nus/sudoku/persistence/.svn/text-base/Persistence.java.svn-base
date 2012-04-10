package sg.edu.nus.sudoku.persistence;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import sg.edu.nus.sudoku.halloffame.HallOfFame;
import sg.edu.nus.sudoku.twitter.SudokuTweet;

/***
 * 
 * @author Low Wee, Yipeng, Breton and Shawn
 *
 */
public class Persistence implements Serializable {
	private Hashtable<String,SaveGame> savedGames;
	private HallOfFame hallOfFame;
	private static Persistence instance;
	private final static String SAVE_FILE_NAME = "sudokuplus.data";
	
	private boolean hintsShown, solveGameEnable;
	private List<SudokuTweet> savedTweets;
	
	public List<SudokuTweet> getSavedTweets() {
		return savedTweets;
	}

	public void setSavedTweets(List<SudokuTweet> savedTweets) {
		this.savedTweets = savedTweets;
	}

	public boolean isSolveGameEnabled() {
		return solveGameEnable;
	}
	
	public boolean isHintsShown() {
		return hintsShown;
	}

	public void setHintsShown(boolean hintsShown) {
		this.hintsShown = hintsShown;
	}
	
	public void setSolveGameEnabled(boolean solveGameEnable) {
		this.solveGameEnable = solveGameEnable;
	}

	private Persistence(){
		savedGames = new Hashtable<String,SaveGame>();
		hallOfFame= new HallOfFame();
		hintsShown = true;
		solveGameEnable = false;
		//sample saved records.
	}
	
	/**
	 * Returns the persisted HallOfFame object
	 * @return HallOfFame object.
	 * @see HallOfFame
	 */
	public HallOfFame getHallOfFame() {
		return hallOfFame;
	}

	/**
	 * Persistence implements the singleton design pattern for consistency.<br/>
	 * Returns the instance of persistence.
	 * @return Persistance instance.
	 */
	public static Persistence getInstance(){
		if(instance == null){
			instance = readFromFile();
			if(instance==null) instance = new Persistence();
		}
		return instance;
	}

	/**
	 * Saves game.
	 * @param name	Saved Game's Name
	 * @param gameString Serialized string of the Sudoku game.
	 * @param gameState Serialized string of the Sudoku game with user-input.
	 * @param timeLeft Time remaining on the timer.
	 */
	public void saveGame(String name,String gameString, String gameState, int timeLeft, int timeTaken){
		savedGames.put(name, new SaveGame(name, gameString, gameState,timeLeft,timeTaken));
		System.out.println(savedGames);
	}
	
	/**
	 * Returns an array of the names of the saved games.
	 * @return String[] of saved game names.
	 */
	public String[] getSavedGames(){
		Enumeration<String> e = savedGames.keys();
		String[] names = new String[savedGames.size()];
		for(int i=0;i<names.length;i++){
			names[i] = e.nextElement();
		}
		return names;
	}
	
	/**
	 * Loads game from its unique name.
	 * @param name Name of saved game.
	 * @return
	 */
	public SaveGame loadGame(String name){
		return savedGames.get(name);
	}
	
	/**
	 * Returns true if the name of the game already exists.
	 * @param name
	 * @return <tt>true</tt> if the name of the game already exists.
	 */
	public boolean gameExists(String name){
		return savedGames.containsKey(name);
	}

	/**
	 * Writes the Persistence instance to file, along with saved game data and hall of fame data.
	 * @see MainController
	 */
	public static void writeToFile(){
		try {
			FileOutputStream fOut = new FileOutputStream(SAVE_FILE_NAME);
			ObjectOutputStream objOut = new ObjectOutputStream (fOut);
			objOut.writeObject (instance);
			objOut.close();
		} catch (IOException e) {}
	}

	private static Persistence readFromFile(){
		try {
			FileInputStream fOut = new FileInputStream(SAVE_FILE_NAME);
			ObjectInputStream objOut = new ObjectInputStream(fOut);
			Persistence p = (Persistence)objOut.readObject();
			return p;
		} catch (IOException e) {
			System.out.println("Save file not found.");
			return null;
		} 
		catch (ClassNotFoundException e) {
			return null;
		}
	}
}
