package sg.edu.nus.sudoku.persistence;

import java.io.Serializable;

import sg.edu.nus.sudoku.twitter.SudokuTweet;

public class SaveGame implements Serializable{
	private String gameName;
	private String gameString;
	private String gameState;
	private int timeLeft;
	private int timeTaken;
	private SudokuTweet sudokuTweet;
	
	public SudokuTweet getSudokuTweet() {
		return sudokuTweet;
	}
	public void setSudokuTweet(SudokuTweet sudokuTweet) {
		this.sudokuTweet = sudokuTweet;
	}
	protected SaveGame(String gameName,String gameString,String gameState,int timeLeft,int timeTaken){
		this.gameName = gameName;
		this.gameString = gameString;
		this.gameState = gameState;
		this.timeLeft = timeLeft;
		this.timeTaken = timeTaken;
	}
	/**
	 * @return the gameName
	 */
	public String getGameName() {
		return gameName;
	}
	/**
	 * @return the gameString
	 */
	public String getGameString() {
		return gameString;
	}
	/**
	 * @return the gameState
	 */
	public String getGameStateString() {
		return gameState;
	}
	public int getTimeLeft(){
		return timeLeft;
	}
	/**
	 * @return the timeTaken
	 */
	public int getTimeTaken() {
		return timeTaken;
	}
	/**
	 * @param timeTaken the timeTaken to set
	 */
	public void setTimeTaken(int timeTaken) {
		this.timeTaken = timeTaken;
	}
	
}
