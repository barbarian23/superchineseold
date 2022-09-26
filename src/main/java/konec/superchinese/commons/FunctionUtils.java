package konec.superchinese.commons;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FunctionUtils implements ReturnCodeConst {
	public static String convertMilisecondsToDate(String milisecond) {
		if (milisecond != null && milisecond != "") {
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

			long milliSeconds = Long.parseLong(milisecond);
			System.out.println(milliSeconds);

			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(milliSeconds);
			return formatter.format(calendar.getTime());
		} else {
			return "";
		}
	}

	public static String packageName(String packageCode) {
		return packageCode.contains(SC) ? SUPER_CHINESE : SUPER_TEST;
	}

	public static String packageTime(String packageCode) {
		if(packageCode.contains("M")) {
			return MONTH;
		} else if(packageCode.contains("Y")) {
			return YEAR;
		} else if(packageCode.contains("L")) {
			return LIMITED;
		} else {
			return "";
		}
	}
}
