package sg.edu.nus.sudoku.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import sg.edu.nus.sudoku.halloffame.Difficulty;
import sg.edu.nus.sudoku.halloffame.Record;
import sg.edu.nus.sudoku.twitter.SudokuTweet;

public class GameController implements ActionListener {
	
	private Timer timer;
	private MainController controller;
	private SudokuTweet tweet;

	private int timeLeft;
	private int timeTaken;
	private Difficulty gameDifficulty;
	private boolean started;
	private boolean inGame;
	final private int TIMER_DELAY = 1000;
	
	public GameController(MainController controller) {
		this.controller = controller;
		this.timer = new Timer(0, this);
		this.timer.setDelay(TIMER_DELAY);
		reset();
	}
	public void prepareGame(Difficulty d){
		int timeGiven = 0;
		switch(d){
		case EASY:
			timeGiven = 500;
			break;
		case MEDIUM:
			timeGiven = 1000;
			break;
		case HARD:
			timeGiven = 1500;
			break;
		}
		prepareGame(d,timeGiven);
	}
	
	public void prepareGame(Difficulty d , int timeLeft) {
		prepareGame(d,timeLeft,0);
	}
	
	public void setTimeLeft(int timeLeft){
		this.timeLeft = timeLeft;
	}
	public void getDifficulty(){}

	public void prepareGame(Difficulty d, int timeLeft, int timeTaken){
		this.gameDifficulty = d;
		this.timeLeft = timeLeft;
		this.timeTaken = timeTaken;
		this.inGame = true;
	}
	public void start(){
		if(started) return;
		if(gameDifficulty != null) {
			if(controller.isHintsShown()) timer.setDelay(TIMER_DELAY/2);
			else timer.setDelay(TIMER_DELAY);
			timer.start();
			started = true;
		}
	}
	public boolean isInGame(){
		return inGame;
	}
	
	/**
	 * 
	 * @return {@link Record}
	 */
	public Record gameWon(){
		timer.stop();
		Record r = new Record("PlayerName", gameDifficulty, null, timeTaken);//TODO: get PlayerName from settings.
		reset();
		return r;
	}
	/**
	 * Lose or quit.
	 */
	public void endGame(){
		timer.stop();
		reset();
	}
	
	public void timerPause(){
		timer.stop();
	}
	public void timerResume() {
		timer.start();
	}
	
	/**
	 * Set all values to initial
	 */
	public void reset(){
		tweet = null;
		gameDifficulty = null;
		timeLeft = 0;
		timeTaken = 0;
		started = false;
		inGame = false;
	}
	
	public void resetTime(){
		started = false;
		prepareGame(gameDifficulty);
	}
	
	public void actionPerformed(ActionEvent e) {
		timeLeft = timeLeft - 1;
		timeTaken = timeTaken + 1;
		controller.updateGameUI();
		if(timeLeft==0) {
			timeUp();
		}
	}
	
	private void timeUp(){
		timer.stop();
	}
	/**
	 * @return the timeLeft
	 */
	protected int getTimeLeft() {
		return timeLeft;
	}
	/**
	 * @return the gameDifficulty
	 */
	protected Difficulty getGameDifficulty() {
		return gameDifficulty;
	}
	
	protected void setTweet(SudokuTweet tweet) {
		this.tweet = tweet;
	}
	public SudokuTweet getTweet() {
		return tweet;
	}
	public int getTimeTaken() {
		return timeTaken;
	}
}
