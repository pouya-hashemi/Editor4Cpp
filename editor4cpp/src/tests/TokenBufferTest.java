package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import services.TokenBuffer;

public class TokenBufferTest {
	public static Object[][] getTokenTest_Data() {
		return new Object[][] { {"int a=;",0,"i" },{"int a=;",3," " },{"int a=;",6,";" },{"int a=;",7,null }};
	}

	@ParameterizedTest
	@MethodSource(value = "getTokenTest_Data")
	public void getTokenTest(String text,int currentIndex,String expectedValue) {
		// Arrange
		TokenBuffer tokenBuffer=new TokenBuffer();
		tokenBuffer.setTextAndReset(text);
		for(int i=0;i<currentIndex;i++)
			tokenBuffer.consume();
		// Act
		var character=tokenBuffer.peek();
		// Assert
		assertEquals(character,expectedValue);
		

	}

}
