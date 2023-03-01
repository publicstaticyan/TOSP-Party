package oldschoolproject.Modules.Formatters;

public class TimeConverter {
	
	public static String convertSecondsToStr(int secs) {
		int minutes = (secs % 3600) / 60;
		int seconds = secs % 60;
		return seconds < 10 ? minutes + ":" + "0" + seconds : minutes + ":" + seconds;
	}

}
