package services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TokenBuffer {
	private Matcher matcher;
	private int currentIndex;

	public TokenBuffer() {
		currentIndex=0;
	}
	public void setTextAndReset(String text) {
		currentIndex = 0;
		text = text.replace("\r\n", "\r");
		Pattern pattern = Pattern.compile("[\\s\\S]");
		matcher = pattern.matcher(text);
	}

	public String peek() {
		if(matcher == null)
			throw new NullPointerException("The matcher of buffer is null. remmember to set the ;text before calling peek()");
		if (!matcher.find(currentIndex)) {
			return null;
		}
		return matcher.group();
	}
	public String peek(int extraSteps) {
		if(matcher == null)
			throw new NullPointerException("The matcher of buffer is null. remmember to set the ;text before calling peek()");
		if(currentIndex+extraSteps <0)
			return null;
		if (!matcher.find(currentIndex+extraSteps)) {
			return null;
		}
		return matcher.group();
	}

	public String consume() {
		if(matcher == null)
			throw new NullPointerException("The matcher of buffer is null. remmember to set the ;text before calling consume()");
		if (!matcher.find(currentIndex++)) {
			return null;
		}
		return matcher.group();

	}
}
