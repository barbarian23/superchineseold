package konec.superchinese.commons;

public class StringUtils {
	public static boolean isNotEmpty(String string) {
		return string != null && string != "" ? true : false;
	}
	public static boolean isEmpty(String string) {
		return string == null | string == "" ? true : false;
	}
}
