package sg.edu.nus.sudoku.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Time {
	final private static int DEFAULT_TIME = Integer.MAX_VALUE;
	final private static String DEFAULT_TIME_DISPLAY = "-- mins -- secs";
	final private static int NO_OF_SECS_PER_MINUTE = 60;
	final private static String DATE_FORMAT_NOW = "yyyy/MM/dd HH:mm:ss";

	Time() {
	}
	

	public static String formatTime(int time) {
		if (time == DEFAULT_TIME) {
			return DEFAULT_TIME_DISPLAY;
		}
		else {
			int noOfMinutes = time/NO_OF_SECS_PER_MINUTE;
			int noOfSeconds = time%NO_OF_SECS_PER_MINUTE;
			return "" + noOfMinutes + " mins " + noOfSeconds + " secs";
		}
	}

	public static String getTimeDifference(int time1, int time2) {
		int timeDifference = time1 - time2;
		timeDifference = timeDifference < 0 ? -timeDifference : timeDifference;
		int noOfMinutes = timeDifference/NO_OF_SECS_PER_MINUTE;
		int noOfSeconds = timeDifference%NO_OF_SECS_PER_MINUTE;
		return "" + noOfMinutes + " mins " + noOfSeconds + " secs";
	}
	
	public static String getCurrentTime() {
	    Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
	    return sdf.format(cal.getTime()) + " Hrs";
	}
}
