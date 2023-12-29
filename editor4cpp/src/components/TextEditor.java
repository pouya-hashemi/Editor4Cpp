package components;

import services.Tokenizer;
import javax.swing.*;
import javax.swing.text.*;
import constants.CustomStyle;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import entities.Token;

import java.util.ArrayList;
import java.util.List;

public class TextEditor extends JTextPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3526352590503713441L;
	/**
	 * 
	 */
	private StyledDocument doc;
	private Tokenizer tokenizer;
	private List<Token> errorTokens;
	private boolean needSave = false;

	public TextEditor() {
		setFont(new Font("Monospaced", Font.PLAIN, 14));
		// add default Style to editor
		tokenizer = new Tokenizer();
		doc = getStyledDocument();
		errorTokens = new ArrayList<Token>();
		addStyleToDocument();

		addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyTyped(java.awt.event.KeyEvent evt) {
				SwingUtilities.invokeLater(() -> {
					if (!needSave)
						needSave = true;
					errorTokens.clear();
					setToolTipText(null);
					List<Token> tokens = tokenizer.tokenizeString(getText());

					for (Token token : tokens) {
						doc.setCharacterAttributes(token.startIndex, token.value.length(), token.tokenStyle, false);
						if (token.error != null && token.error.length() > 0) {
							errorTokens.add(token);
							doc.setCharacterAttributes(token.startIndex, token.value.length(), CustomStyle.errorStyle,
									false);
						}
					}
				});
			}
		});

		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if (!errorTokens.isEmpty()) {

					// Set dynamic tooltip text based on the mouse position
					@SuppressWarnings("deprecation")
					int offset = viewToModel(e.getPoint());
					for (Token errorToken : errorTokens)
						if (getWordUnderMouse(offset, errorToken))
							setToolTipText(errorToken.error);
				}
			}
		});

	}

	public void setEditorText(String text) {
		this.setText(text);
		List<Token> tokens = tokenizer.tokenizeString(text);
		for (Token token : tokens) {
			doc.setCharacterAttributes(token.startIndex, token.value.length(), token.tokenStyle, false);
		}

	}

	private void addStyleToDocument() {
		Style keywordStyle = doc.addStyle("keywordStyle", null);
		StyleConstants.setForeground(keywordStyle, Color.decode("#0f31ba"));
		StyleConstants.setBold(keywordStyle, true);
		StyleConstants.setUnderline(keywordStyle, false);
		CustomStyle.keywordStyle = keywordStyle;

		// Define the style for comments
		Style commentStyle = doc.addStyle("commentStyle", null);
		StyleConstants.setForeground(commentStyle, Color.decode("#0b7a1e"));
		StyleConstants.setItalic(commentStyle, true);
		StyleConstants.setUnderline(commentStyle, false);
		StyleConstants.setBold(commentStyle, false);

		CustomStyle.commentStyle = commentStyle;

		// Define the style for string
		Style stringStyle = doc.addStyle("stringStyle", null);
		StyleConstants.setForeground(stringStyle, Color.decode("#d6491a"));
		StyleConstants.setUnderline(stringStyle, false);
		StyleConstants.setBold(stringStyle, false);

		CustomStyle.stringStyle = stringStyle;

		// Define the style for number
		Style numberStyle = doc.addStyle("numberStyle", null);
		StyleConstants.setForeground(numberStyle, Color.decode("#fcad03"));
		StyleConstants.setUnderline(numberStyle, false);
		StyleConstants.setBold(numberStyle, false);

		CustomStyle.numberStyle = numberStyle;
		// Define the style for function
		Style funcStyle = doc.addStyle("funcStyle", null);
		StyleConstants.setForeground(funcStyle, Color.decode("#db03fc"));
		StyleConstants.setUnderline(funcStyle, false);
		StyleConstants.setBold(funcStyle, false);

		CustomStyle.funcStyle = funcStyle;
		// Define the style for punctuations
		Style punctStyle = doc.addStyle("punctStyle", null);
		StyleConstants.setForeground(punctStyle, Color.decode("#9803fc"));
		StyleConstants.setUnderline(punctStyle, false);
		StyleConstants.setBold(punctStyle, true);

		CustomStyle.punctStyle = punctStyle;
		// Define the style for directives
		Style directiveStyle = doc.addStyle("directiveStyle", null);
		StyleConstants.setForeground(directiveStyle, Color.decode("#535453"));
		StyleConstants.setUnderline(directiveStyle, false);
		StyleConstants.setBold(directiveStyle, false);

		CustomStyle.directiveStyle = directiveStyle;

		// Define the style for classes
		Style classStyle = doc.addStyle("classStyle", null);
		StyleConstants.setForeground(classStyle, Color.decode("#006d60"));
		StyleConstants.setUnderline(classStyle, false);
		StyleConstants.setBold(classStyle, false);

		CustomStyle.classStyle = classStyle;

		Style defaultStyle = doc.addStyle("defaultStyle", null);
		StyleConstants.setForeground(defaultStyle, Color.BLACK);
		StyleConstants.setUnderline(defaultStyle, false);
		StyleConstants.setBold(defaultStyle, false);

		CustomStyle.defaultStyle = defaultStyle;

		Style errorStyle = doc.addStyle("errorStyle", null);
		StyleConstants.setForeground(errorStyle, Color.RED);
		StyleConstants.setUnderline(errorStyle, true);
		StyleConstants.setBold(errorStyle, true);

		CustomStyle.errorStyle = errorStyle;

	}

	private Boolean getWordUnderMouse(int offset, Token errorToken) {
		try {
			int start = Utilities.getWordStart(this, offset);
			int end = Utilities.getWordEnd(this, offset);
			return errorToken.startIndex == start && errorToken.endIndex == end;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean getNeedSave() {
		return needSave;
	}
	public void setNeedSave(boolean value) {
		 needSave=value;
	}

}
