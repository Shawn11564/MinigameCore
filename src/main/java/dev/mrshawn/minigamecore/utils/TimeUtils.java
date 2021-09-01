package dev.mrshawn.minigamecore.utils;

public class TimeUtils {

	public static String formatSecondsToMinutes(int seconds) {
		if (seconds >= 60) {
			int mins = seconds / 60;
			int secs = seconds % 60;
			if (secs < 10) {
				return mins + ":0" + secs;
			}
			return mins + ":" + secs;
		} else {
			return String.valueOf(seconds);
		}
	}

}
