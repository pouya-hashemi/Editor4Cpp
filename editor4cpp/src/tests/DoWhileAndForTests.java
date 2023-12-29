package tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import entities.Token;
import services.Tokenizer;

public class DoWhileAndForTests {
	public static String[] DoWhileTests_Data() {
		return new String[] { "do int a=2; while(1==1);", "do {int a=2;} while(1==1);",
				"do {int a=2;long b=3;} while(1==1);", };
	}

	@ParameterizedTest
	@MethodSource(value = "DoWhileTests_Data")
	public void DoWhileTests(String text) {
		// Arrange
		Tokenizer tokenizer = new Tokenizer();
		// Act
		List<Token> tokens = tokenizer.tokenizeString(text);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			assertTrue(tokens.get(i).error == null || tokens.get(i).error.length() == 0,
					"index:" + i + " error: " + tokens.get(i).error);
		}

	}

	public static Object[][] DoWhileTestsErrors_Data() {
		return new Object[][] { { "do(1>2) int a=2; while(1==1);", 1 } };

	}

	@ParameterizedTest
	@MethodSource(value = "DoWhileTestsErrors_Data")
	public void DoWhileTestsErrors(String text, int index) {
		// Arrange
		Tokenizer tokenizer = new Tokenizer();
		// Act
		List<Token> tokens = tokenizer.tokenizeString(text);
		// Assert
		assertTrue(tokens.get(index).error != null && tokens.get(index).error.length() > 0, tokens.get(index).error);

	}

	////////////////////////////////////////////////////////
	public static String[] ForTests_Data() {
		return new String[] { "for(int i=0;i<=2;i++)int a=2;", "for(int i=0;i<=2;i++){int a=2;}",
				"for(int i=0;i<=2;i++){int a=2;\nint b=3;}", "for(int i=0,b=9;i<=2;i++,b--){int a=2;}",
				"int i;for( i=0;i<=2;i++){int a=2;}", "for(int i=0;i<=2;--i){int a=2;}" };
	}

	@ParameterizedTest
	@MethodSource(value = "ForTests_Data")
	public void ForTests(String text) {
		// Arrange
		Tokenizer tokenizer = new Tokenizer();
		// Act
		List<Token> tokens = tokenizer.tokenizeString(text);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			assertTrue(tokens.get(i).error == null || tokens.get(i).error.length() == 0,
					"index:" + i + " error: " + tokens.get(i).error);
		}

	}

	public static Object[][] ForErrorTests_Data() {
		return new Object[][] { { "for(int i=0,long b=2;i<=2;i++)int a=2;", 8 },
				{ "for(int i=0,;i<=2;i++)int a=2;", 8 }, { "for(int i=0;i<=2;i++,)int a=2;", 17 } };

	}

	@ParameterizedTest
	@MethodSource(value = "ForErrorTests_Data")
	public void ForErrorTests(String text, int index) {
		// Arrange
		Tokenizer tokenizer = new Tokenizer();
		// Act
		List<Token> tokens = tokenizer.tokenizeString(text);
		// Assert
		assertTrue(tokens.get(index).error != null && tokens.get(index).error.length() > 0, tokens.get(index).error);

	}
}
