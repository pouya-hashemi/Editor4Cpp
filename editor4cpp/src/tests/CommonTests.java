package tests;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import entities.Token;
import services.Tokenizer;

public class CommonTests {

	public static String[] noError_Data() {
		return new String[] { "#include <iostream>\r\n"
				+ "#include <cmath>\r\n"
				+ "\r\n"
				+ "// Function to calculate the square of a number\r\n"
				+ "int calculateSquare(int x) {\r\n"
				+ "    return x * x;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "int main() {\r\n"
				+ "    int num1 = 5;\r\n"
				+ "    int num2 = 3;\r\n"
				+ "\r\n"
				+ "    if (num1 > num2) {\r\n"
				+ "        for (int i = 0; i < 5; ++i) {\r\n"
				+ "            num1 += i;\r\n"
				+ "        }\r\n"
				+ "    } else {\r\n"
				+ "        while (num2 > 0) {\r\n"
				+ "            num2--;\r\n"
				+ "        }\r\n"
				+ "    }\r\n"
				+ "\r\n"
				+ "    // Arrays\r\n"
				+ "    int array1[5] = {1, 2, 3, 4, 5};\r\n"
				+ "    char charArray[] = \"Hello\";\r\n"
				+ "\r\n"
				+ "    // Switch statement\r\n"
				+ "    char operation = '+';\r\n"
				+ "    switch (operation) {\r\n"
				+ "        case '+':\r\n"
				+ "            num1 = num1 + num2;\r\n"
				+ "            break;\r\n"
				+ "        case '-':\r\n"
				+ "            num1 = num1 - num2;\r\n"
				+ "            break;\r\n"
				+ "        default:\r\n"
				+ "            break;\r\n"
				+ "    }\r\n"
				+ "\r\n"
				+ "    // Function call\r\n"
				+ "    int result = calculateSquare(num1);\r\n"
				+ "\r\n"
				+ "    // Pointer\r\n"
				+ "    int* ptr = &result;\r\n"
				+ "\r\n"
				+ "    // Dynamic memory allocation\r\n"
				+ "    int* dynamicArray = new int[3];\r\n"
				+ "    dynamicArray[0] = 10;\r\n"
				+ "    dynamicArray[1] = 20;\r\n"
				+ "    dynamicArray[2] = 30;\r\n"
				+ "\r\n"
				+ "    delete[] dynamicArray;\r\n"
				+ "\r\n"
				+ "    // Mathematical operations\r\n"
				+ "    double pi = M_PI;  // Using cmath library\r\n"
				+ "    double sqrtResult = sqrt(result);\r\n"
				+ "\r\n"
				+ "    // Enum\r\n"
				+ "    enum Color { RED, GREEN, BLUE };\r\n"
				+ "    Color myColor = GREEN;\r\n"
				+ "\r\n"
				+ "    // Conditional operator\r\n"
				+ "    int maxValue = (num1 > num2) ? num1 : num2;\r\n"
				+ "\r\n"
				+ "    return 0;\r\n"
				+ "}\r\n"
				+ "" };
	}

	@ParameterizedTest
	@MethodSource(value = "noError_Data")
	public void noError(String text) {
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

}
