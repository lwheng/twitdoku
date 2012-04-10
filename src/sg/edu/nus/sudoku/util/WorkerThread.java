package sg.edu.nus.sudoku.util;

import java.util.LinkedList;
import java.util.WeakHashMap;

public class WorkerThread extends Thread {
	private LinkedList<Runnable> workQueue;
	private WeakHashMap<Runnable, Boolean> done;
	private boolean jobInterrupted = false;
	public boolean isJobInterrupted() {
		return jobInterrupted;
	}
	public WorkerThread() {
		workQueue = new LinkedList<Runnable>();
		done = new WeakHashMap<Runnable, Boolean>();
		setDaemon(true);
		setName("SudokuPlus Worker");
		start();
	}
	public synchronized void queueJob(Runnable r) {
		workQueue.addLast(r);
		notify();
	}

	public synchronized boolean isJobDone(Runnable r){
		return done.containsKey(r);
	}


	public synchronized Runnable getNextJob() throws InterruptedException {
		while(workQueue.isEmpty()) wait();
		return workQueue.pop();

	}
	
	

	public void run() {
		Runnable r = null;
		while(true) {
			try {
				r = getNextJob();
				jobInterrupted = false;
				r.run();
				done.put(r, true);
			}
			catch (InterruptedException e) {
				done.put(r, false);
				jobInterrupted = true;
			}
		}
	}


}