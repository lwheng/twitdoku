package sg.edu.nus.sudoku.halloffame;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.ListIterator;

public class HallOfFame implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4019229582620287149L;
	private Hashtable<Difficulty, LinkedList<Record>> halloffame;
	private final static int NUM_OF_RECORDS_TO_DISPLAY = 3;
	
	public HallOfFame() {
		halloffame = new Hashtable<Difficulty, LinkedList<Record>>();
		halloffame.put(Difficulty.EASY, this.createDefaultRecords(Difficulty.EASY));
		halloffame.put(Difficulty.MEDIUM, this.createDefaultRecords(Difficulty.MEDIUM));
		halloffame.put(Difficulty.HARD, this.createDefaultRecords(Difficulty.HARD));
	}

	
	public boolean addRecord(Record record) {
		LinkedList<Record> tempRecords = halloffame.get(record.getGameDifficulty());
		ListIterator<Record> itr = tempRecords.listIterator();
		Record currentRecord;
		while (itr.hasNext()) {
			currentRecord = itr.next();
			if (currentRecord.getTime() > record.getTime()) {
				currentRecord = itr.previous();
				itr.add(record);
				if (tempRecords.size() > NUM_OF_RECORDS_TO_DISPLAY) tempRecords.removeLast();
				return true;
			}
		}
		return false;
	}
	
	private LinkedList<Record> createDefaultRecords(Difficulty gameDifficulty) {
		LinkedList<Record> temp = new LinkedList<Record>();
		for (int i=0; i<NUM_OF_RECORDS_TO_DISPLAY; i++) {
			temp.add(new Record(gameDifficulty));
		}
		
		return temp;
	}

	public Hashtable<Difficulty,LinkedList<Record>> getRecords() {
		return halloffame;
	}
}
