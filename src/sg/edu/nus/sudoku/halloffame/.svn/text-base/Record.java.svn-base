package sg.edu.nus.sudoku.halloffame;

import java.io.Serializable;

import sg.edu.nus.sudoku.halloffame.Difficulty;

public class Record implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6531320631010463696L;
	private String playerName;
	private Difficulty gameDifficulty;
	private String gameString;
	private int time;
	//default record
	public Record(Difficulty gameDifficulty) {
		this.playerName = "No Record";
		this.gameDifficulty = gameDifficulty;
		this.time = Integer.MAX_VALUE;
	}
	
	public Record(String playerName, Difficulty gameDifficulty, String gameString, int time) {
		this.playerName = playerName;
		this.gameDifficulty = gameDifficulty;
		this.gameString = gameString;
		this.time = time;
	}
	
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	public String getPlayerName() {
		return this.playerName;
	}
	
	public void setGameDifficulty(Difficulty gameDifficulty) {
		this.gameDifficulty = gameDifficulty;
	}
	
	public Difficulty getGameDifficulty() {
		return this.gameDifficulty;
	}
	
	public void setTime(int time) {
		this.time = time;
	}
	
	public int getTime() {
		return this.time;
	}
	public void setGameString(String gameString) {
		this.gameString = gameString;
	}

	public String getGameString() {
		return gameString;
	}
}
