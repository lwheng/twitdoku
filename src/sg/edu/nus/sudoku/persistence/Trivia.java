package sg.edu.nus.sudoku.persistence;

import java.util.ArrayList;

import sg.edu.nus.sudoku.gui.Resources;

public class Trivia {
	private ArrayList<String> trivia;
	private static Trivia instance;
	private Trivia() {
		trivia = Resources.TRIVIA_LINES;

    }
	
	public static Trivia getInstance() {
		if(instance == null) {
			instance =  new Trivia();
		}
		return instance;
	}
	
	public String getRandomTrivia() {
        int randomNumber = (int)(trivia.size()*Math.random());
		return trivia.get(randomNumber);
	}
}
