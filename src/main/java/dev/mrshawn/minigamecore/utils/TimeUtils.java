package dev.mrshawn.minigamecore.utils;

public class TimeUtils {

	public static String formatSeconds(int seconds) {
		int hrs = (seconds % 86400 ) / 3600;
		int mins = ((seconds % 86400 ) % 3600 ) / 60;
		int secs = ((seconds % 86400 ) % 3600 ) % 60;

		StringBuilder sb = new StringBuilder();
		if (hrs > 0) {
			sb.append(hrs >= 10 ? sb.append(hrs) : sb.append("0").append(hrs)).append(":");
		}
		if (mins > 0) {
			sb.append(mins >= 10 ? sb.append(mins) : sb.append("0").append(mins)).append(":");
		}
		if (secs > 0) {
			sb.append(secs >= 10 ? sb.append(secs) : sb.append("0").append(secs));
		}
		return sb.toString();
	}

}
