package constants;

import java.awt.Color;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class CustomStyle {

	 static {
		 initStyles();
	    }
	public static Style defaultStyle;

	public static Style keywordStyle;

	public static Style commentStyle;

	public static Style stringStyle;

	public static Style directiveStyle;

	public static Style classStyle;

	public static Style errorStyle;

	public static Style numberStyle;

	public static Style punctStyle;

	public static Style funcStyle;

	private static void initStyles() {
		StyleContext styleContext = new StyleContext();

		Style keywordStyle = styleContext.addStyle("keywordStyle", null);
		StyleConstants.setForeground(keywordStyle, Color.decode("#0f31ba"));
		StyleConstants.setBold(keywordStyle, true);
		StyleConstants.setUnderline(keywordStyle, false);
		CustomStyle.keywordStyle = keywordStyle;

		// Define the style for comments
		Style commentStyle = styleContext.addStyle("commentStyle", null);
		StyleConstants.setForeground(commentStyle, Color.decode("#0b7a1e"));
		StyleConstants.setItalic(commentStyle, true);
		StyleConstants.setUnderline(commentStyle, false);
		StyleConstants.setBold(commentStyle, false);

		CustomStyle.commentStyle = commentStyle;

		// Define the style for string
		Style stringStyle = styleContext.addStyle("stringStyle", null);
		StyleConstants.setForeground(stringStyle, Color.decode("#d6491a"));
		StyleConstants.setUnderline(stringStyle, false);
		StyleConstants.setBold(stringStyle, false);

		CustomStyle.stringStyle = stringStyle;

		// Define the style for number
		Style numberStyle = styleContext.addStyle("numberStyle", null);
		StyleConstants.setForeground(numberStyle, Color.decode("#fcad03"));
		StyleConstants.setUnderline(numberStyle, false);
		StyleConstants.setBold(numberStyle, false);

		CustomStyle.numberStyle = numberStyle;
		// Define the style for function
		Style funcStyle = styleContext.addStyle("funcStyle", null);
		StyleConstants.setForeground(funcStyle, Color.decode("#db03fc"));
		StyleConstants.setUnderline(funcStyle, false);
		StyleConstants.setBold(funcStyle, false);

		CustomStyle.funcStyle = funcStyle;
		// Define the style for punctuations
		Style punctStyle = styleContext.addStyle("punctStyle", null);
		StyleConstants.setForeground(punctStyle, Color.decode("#9803fc"));
		StyleConstants.setUnderline(punctStyle, false);
		StyleConstants.setBold(punctStyle, true);

		CustomStyle.punctStyle = punctStyle;
		// Define the style for directives
		Style directiveStyle = styleContext.addStyle("directiveStyle", null);
		StyleConstants.setForeground(directiveStyle, Color.decode("#535453"));
		StyleConstants.setUnderline(directiveStyle, false);
		StyleConstants.setBold(directiveStyle, false);

		CustomStyle.directiveStyle = directiveStyle;

		// Define the style for classes
		Style classStyle = styleContext.addStyle("classStyle", null);
		StyleConstants.setForeground(classStyle, Color.decode("#006d60"));
		StyleConstants.setUnderline(classStyle, false);
		StyleConstants.setBold(classStyle, false);

		CustomStyle.classStyle = classStyle;

		Style defaultStyle = styleContext.addStyle("defaultStyle", null);
		StyleConstants.setForeground(defaultStyle, Color.BLACK);
		StyleConstants.setUnderline(defaultStyle, false);
		StyleConstants.setBold(defaultStyle, false);

		CustomStyle.defaultStyle = defaultStyle;

		Style errorStyle = styleContext.addStyle("errorStyle", null);
		StyleConstants.setForeground(errorStyle, Color.RED);
		StyleConstants.setUnderline(errorStyle, true);
		StyleConstants.setBold(errorStyle, true);

		CustomStyle.errorStyle = errorStyle;
	}

}
