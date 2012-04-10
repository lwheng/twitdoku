package sg.edu.nus.sudoku.model;

import java.util.Iterator;
import java.util.ListIterator;

public class AvailableValues implements Iterable<SudokuValue>{
	private SudokuValue[] availableValues = new SudokuValue[9];;
	private int size;
	private String valueString;
	
	public AvailableValues() {
		for(int i=0;i<availableValues.length;i++){
			availableValues[i] = SudokuValue.DEFAULT_SUDOKU_VALUES[i];
		}
		size = availableValues.length;
	}
	protected boolean contains(SudokuValue value) {
		if(size == 0) return false;
		for(int i=0;i<availableValues.length;i++){
			if(availableValues[i]==value) return true;
		}
		return false;
	}
	public SudokuValue[] getArray(){
		return availableValues;
	}
	
	protected synchronized boolean take(SudokuValue value) {
		for(int i=0;i<availableValues.length;i++){
			if(availableValues[i]==value){
				availableValues[i] = null;
				valueString = null;
				size--;
				return true;
			}
		}
		return false;
	}
	protected synchronized boolean put(SudokuValue value) {
		for(int i=0;i<SudokuValue.DEFAULT_SUDOKU_VALUES.length;i++){
			if(SudokuValue.DEFAULT_SUDOKU_VALUES[i]==value){
				if(availableValues[i] != null) return false;
				availableValues[i] = SudokuValue.DEFAULT_SUDOKU_VALUES[i];
				valueString = null;
				size++;
				return true;
			}
		}
		return false;
	}

	public int size(){
		return size;
	}
	@Override
	public Iterator<SudokuValue> iterator() {
		// TODO Auto-generated method stub
		return listIterator();
	}
	public ListIterator<SudokuValue> listIterator(){
		return new AvailableValueIterator();
	}
	
	public String toString() {
		if(size==0) return "No values available";
		if(valueString==null){
			StringBuffer sb = new StringBuffer(25);
			boolean first = true;
			for(int i=0;i<availableValues.length;i++){
				if(availableValues[i]!=null){
					if(first){
						sb.append(availableValues[i]);
						first = false;
					} else {
						sb.append(", ").append(availableValues[i]);
					}
					
					
					
				}
				
			}
			valueString = sb.toString();
		}
		return valueString;
	}
	
	
	private class AvailableValueIterator implements ListIterator<SudokuValue>{
		int pointer = -1;
		@Override
		public void add(SudokuValue e) {
			add(e);
		}

		@Override
		public boolean hasNext() {
			if(pointer == availableValues.length - 1) return false;
			int i = pointer;
			while(i<availableValues.length-1){
				if(availableValues[i+1]!=null) {
					pointer = i; 
					return true;
				}
				i++;
			}
			return false;
		}

		@Override
		public boolean hasPrevious() {
			if(pointer==-1) return false;
			int i = pointer;
			while(i>-1){
				if(availableValues[i]!=null) {
					pointer = i; 
					return true;
				}
				i--;
			}
			return false;
		}

		@Override
		public SudokuValue next() {
			if(hasNext()){
				pointer++;
				return availableValues[pointer];
			} else return null;
		}

		@Override
		public int nextIndex() {
			if(hasNext()){
				return pointer+1;
			} else return -1;
		}

		@Override
		public SudokuValue previous() {
			if(hasPrevious()) {
				SudokuValue v =availableValues[pointer];
				pointer--;
				return v;
			} return null;
		}

		@Override
		public int previousIndex() {
			if(hasPrevious()){
				return pointer;
			} return -1;
		}

		@Override
		public void remove() {
			//not implemented.
		}

		@Override
		public void set(SudokuValue e) {
			//not implemented
		}
		
	}

	public static void main(String[] args) {
		AvailableValues av = new AvailableValues();
		ListIterator<SudokuValue> itr = av.listIterator();
		while(itr.hasNext()) {
			System.out.println(itr.next());
		}
		System.out.println(itr.hasNext());
		System.out.println(itr.hasPrevious());
		itr.previous();
		System.out.println(itr.hasNext());
		System.out.println(itr.hasPrevious());
		while(itr.hasPrevious()) {
			System.out.println(itr.previous());
		}
		System.out.println(itr.hasNext());
		System.out.println(itr.hasPrevious());
		itr.next();
		System.out.println(itr.hasNext());
		System.out.println(itr.hasPrevious());
	}
}