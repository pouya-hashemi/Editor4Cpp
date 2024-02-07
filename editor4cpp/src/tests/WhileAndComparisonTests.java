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
import services.Tokenizer;

public class WhileAndComparisonTests {
	public static String[] ComparisonTests_Data() {
		return new String[] { "while(1>2) int a=2;", "int a=2;while(a>2) int a=2;", "int a=2;while(a>a) int a=2;",
				"int a=2;while(a>2&&2>a) int a=2;", "int a=2;while(a>2&&2>a&&1<2) int a=2;",
				"int a=2;while(a>=2) int a=2;", "int a=2;while(a==2) int a=2;", "int a=2;while((a>2)) int a=2;",
				"int a=2;while((a>2)&&a>2) int a=2;", "int a=2;while((a>2)&&(a>2)) int a=2;",
				"int a=2;while(((a>2)&&(a>2))||a==2) int a=2;", "while(1>2) int a=2;", "while(1>2) {int a=2;}",
				"while(1>2) {int a=2; int b=3;int c=3;}" };
	}

	@ParameterizedTest
	@MethodSource(value = "ComparisonTests_Data")
	public void ComparisonTests(String text) {
		// Arrange
		var tokenHighlighter=new TokenHighlighter();
		ParsingFacade parsingFacade = new ParsingFacade(tokenHighlighter,new TextFormatting(tokenHighlighter),new Tokenizer(),new Parser());
		
		// Act
		List<Token> tokens = parsingFacade.ParseText(text,false);
		// Assert
		for (int i = 0; i < tokens.size(); i++) {
			assertTrue(tokens.get(i).errors.size() == 0,
					"index:" + i);
		}

	}

	public static Object[][] ComparisonErrorsTests() {
		return new Object[][] { { "while(1=>2) int a=2;", 3 }, { "while((1>2) int a=2;", 8 },
				{ "while(1>2)) int a=2;", 6 } };

	}

	@ParameterizedTest
	@MethodSource(value = "ComparisonErrorsTests")
	public void ComparisonErrorsTests(String text, int index) {
		// Arrange
		var tokenHighlighter=new TokenHighlighter();
		ParsingFacade parsingFacade = new ParsingFacade(tokenHighlighter,new TextFormatting(tokenHighlighter),new Tokenizer(),new Parser());
		
		// Act
		List<Token> tokens = parsingFacade.ParseText(text,false);
		// Assert
		assertTrue(tokens.get(index).errors.size() > 0, tokens.get(index).errors.get(0));

	}

	///////////////////////////////////////////////////////////////////
}
