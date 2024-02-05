package services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TokenBuffer {
	private Matcher matcher;
	private int currentIndex = 0;

	public TokenBuffer(String text) {
		currentIndex = 0;
		text = text.replace("\r\n", "\r");
		Pattern pattern = Pattern.compile("[\\s\\S]");
		matcher = pattern.matcher(text);
	}

	public String peek() {
		if (!matcher.find(currentIndex)) {
			return null;
		}
		return matcher.group();
	}
	public String peek(int extraSteps) {
		if (!matcher.find(currentIndex+extraSteps)) {
			return null;
		}
		return matcher.group();
	}

	public String consume() {
		if (!matcher.find(currentIndex++)) {
			return null;
		}
		return matcher.group();

	}

}
