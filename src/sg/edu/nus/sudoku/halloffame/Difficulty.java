package sg.edu.nus.sudoku.halloffame;

public enum Difficulty { //Difficulty enum.
	EASY, MEDIUM, HARD;
	private final static int noOfCluesForEasy = 50;
	private final static int noOfCluesForHard = 30;
	
	public static Difficulty getDifficulty(int noOfClues) {
		if (noOfClues > noOfCluesForEasy) return EASY;
		else if (noOfCluesForHard < noOfClues && noOfClues <= noOfCluesForEasy) return MEDIUM;
		else return HARD;
	}
}


