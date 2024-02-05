package tests;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import entities.TokenTypes.Literals.StringLiteral;
import services.VTokenizer;

public class VTokenizerTest {
	public static Object[][] stringProcessing_Data() {
		return new Object[][] { {"int a=2;\r\n"
				+ "char a[]=\"asdasd\";\r\n"
				+ "std::someTHing\r\n"
				+ "vector<int>\r\n"
				+ "for(int i=0;i<=10;i++){\r\n"
				+ "int a=2;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "//Empty curly brackets is valid\r\n"
				+ "if(1>3){\r\n"
				+ "	if(2==4){\r\n"
				+ "		a=2;\r\n"
				+ "}\r\n"
				+ "//detect the closing curly bracket missing\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "int c=2;\r\n"
				+ "//Empty curly brackets is valid\r\n"
				+ "if(1>3){\r\n"
				+ "	if(2==4)\r\n"
				+ "		a=2;\r\n"
				+ "}\r\n"
				+ "" }};
	}

	@ParameterizedTest
	@MethodSource(value = "stringProcessing_Data")
	public void stringProcessing(String text) {
		// Arrange
		VTokenizer tokenizer=new VTokenizer();
		
		// Act
		var tokens=tokenizer.tokenizeString(text,false);
		// Assert
		assertInstanceOf(StringLiteral.class,tokens.get(0).tokenType);

	}
	
}
