package components;

import services.Tokenizer;
import javax.swing.*;
import javax.swing.text.*;
import constants.CustomStyle;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import entities.Token;
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
	private Token errorToken;

	public TextEditor() {
		setFont(new Font("Monospaced", Font.PLAIN, 14));
		// add default Style to editor
		tokenizer = new Tokenizer();
		doc = getStyledDocument();

		addStyleToDocument();

		addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyTyped(java.awt.event.KeyEvent evt) {
				SwingUtilities.invokeLater(() -> {
					errorToken = null;
					setToolTipText(null);
					List<Token> tokens = tokenizer.tokenizeString(getText());

					for (Token token : tokens) {
						doc.setCharacterAttributes(token.startIndex, token.value.length(), token.tokenStyle, false);
						if (token.error != null && token.error.length() > 0) {
							errorToken = token;
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
				if (errorToken != null && errorToken.error != null && errorToken.error.length() > 0) {

					// Set dynamic tooltip text based on the mouse position
					@SuppressWarnings("deprecation")
					int offset = viewToModel(e.getPoint());
					if (getWordUnderMouse(offset))
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

		CustomStyle.commentStyle = commentStyle;

		// Define the style for string
		Style stringStyle = doc.addStyle("stringStyle", null);
		StyleConstants.setForeground(stringStyle, Color.decode("#d6491a"));
		StyleConstants.setUnderline(stringStyle, false);

		CustomStyle.stringStyle = stringStyle;

		// Define the style for directives
		Style directiveStyle = doc.addStyle("directiveStyle", null);
		StyleConstants.setForeground(directiveStyle, Color.decode("#535453"));
		StyleConstants.setUnderline(directiveStyle, false);

		CustomStyle.directiveStyle = directiveStyle;

		// Define the style for classes
		Style classStyle = doc.addStyle("classStyle", null);
		StyleConstants.setForeground(classStyle, Color.decode("#006d60"));
		StyleConstants.setUnderline(classStyle, false);

		CustomStyle.classStyle = classStyle;

		Style defaultStyle = doc.addStyle("defaultStyle", null);
		StyleConstants.setForeground(defaultStyle, Color.BLACK);
		StyleConstants.setUnderline(defaultStyle, false);

		CustomStyle.defaultStyle = defaultStyle;

		Style errorStyle = doc.addStyle("errorStyle", null);
		StyleConstants.setForeground(errorStyle, Color.RED);
		StyleConstants.setUnderline(errorStyle, true);

		CustomStyle.errorStyle = errorStyle;

	}

	private Boolean getWordUnderMouse(int offset) {
		try {
			int start = Utilities.getWordStart(this, offset);
			int end = Utilities.getWordEnd(this, offset);
			return errorToken.startIndex==start&&errorToken.endIndex==end;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
