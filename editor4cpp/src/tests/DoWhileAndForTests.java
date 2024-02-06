package tests;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import entities.Token;
import services.Parser;
import services.ParsingFacade;
import services.TextFormatting;
import services.TokenHighlighter;
import services.VTokenizer;

public class DoWhileAndForTests {
	public static String[] DoWhileTests_Data() {
		return new String[] { "do int a=2; while(1==1);", "do {int a=2;} while(1==1);",
				"do {int a=2;long b=3;} while(1==1);", };
	}

	@ParameterizedTest
	@MethodSource(value = "DoWhileTests_Data")
	public void DoWhileTests(String text) {
		// Arrange
		var tokenHighlighter=new TokenHighlighter();
		ParsingFacade parsingFacade = new ParsingFacade(tokenHighlighter,new TextFormatting(tokenHighlighter),new VTokenizer(),new Parser());
		// Act
		List<Token> tokens = parsingFacade.ParseText(text,false);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			assertTrue(tokens.get(i).errors.size() == 0,
					"index:" + i);
		}

	}

	public static Object[][] DoWhileTestsErrors_Data() {
		return new Object[][] { { "do(1>2) int a=2; while(1==1);", 1 } };

	}

	@ParameterizedTest
	@MethodSource(value = "DoWhileTestsErrors_Data")
	public void DoWhileTestsErrors(String text, int index) {
		// Arrange
		var tokenHighlighter=new TokenHighlighter();
		ParsingFacade parsingFacade = new ParsingFacade(tokenHighlighter,new TextFormatting(tokenHighlighter),new VTokenizer(),new Parser());
		// Act
		List<Token> tokens = parsingFacade.ParseText(text,false);
		// Assert
		assertTrue(tokens.get(index).errors.size() > 0,"");

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
		var tokenHighlighter=new TokenHighlighter();
		ParsingFacade parsingFacade = new ParsingFacade(tokenHighlighter,new TextFormatting(tokenHighlighter),new VTokenizer(),new Parser());
		// Act
		List<Token> tokens = parsingFacade.ParseText(text,false);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			assertTrue( tokens.get(i).errors.size() == 0,
					"index:" + i);
		}

	}

	public static Object[][] ForErrorTests_Data() {
		return new Object[][] { { "for(int i=0,long b=2;i<=2;i++)int a=2;", 8 },
				{ "for(int i=0,;i<=2;i++)int a=2;", 8 }, { "for(int i=0;i<=2;i++,)int a=2;", 15 } };

	}

	@ParameterizedTest
	@MethodSource(value = "ForErrorTests_Data")
	public void ForErrorTests(String text, int index) {
		// Arrange
		var tokenHighlighter=new TokenHighlighter();
		ParsingFacade parsingFacade = new ParsingFacade(tokenHighlighter,new TextFormatting(tokenHighlighter),new VTokenizer(),new Parser());
		// Act
		List<Token> tokens = parsingFacade.ParseText(text,false);
		// Assert
		assertTrue(tokens.get(index).errors.size() > 0,"");

	}
}
