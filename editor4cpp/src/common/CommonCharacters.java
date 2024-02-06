package common;

public class CommonCharacters {
	public static boolean isNextLine(String value) {
		if (value == null)
			return false;
		if (value.equals("\r") || value.equals("\n"))
			return true;
		return false;
	}
	public static boolean isSynchronizedToken(String value) {
		if (value == null)
			return false;
		if (value.equals("\r") || value.equals("\n")|| value.equals(";")|| value.equals("}"))
			return true;
		return false;
	}
}
