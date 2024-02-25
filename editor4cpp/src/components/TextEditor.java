package components;

import javax.swing.*;
import javax.swing.text.*;
import constants.CustomStyle;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import entities.Token;
import entities.TokenTypes.EndOfText;
import entities.TokenTypes.WhiteSpace;
import interfaces.IParsingFacade;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TextEditor extends JTextPane {

	private static final long serialVersionUID = 3526352590503713441L;

	private IParsingFacade parsingFacade;
	private List<Token> errorTokens;
	private boolean needSave = false;

	public TextEditor(IParsingFacade parsingFacade) {
		this.parsingFacade = parsingFacade;
		errorTokens = new ArrayList<Token>();

		setFont(new Font("Monospaced", Font.PLAIN, 14));
		setTabStops(4);
		addEventListeners();
		this.setEditorKit(new StyledEditorKit());

	}
	 @Override
	    public boolean getScrollableTracksViewportWidth() {
	        return getUI().getPreferredSize(this).width <= getParent().getSize().width;
	    }

	private void addEventListeners() {
		addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyTyped(java.awt.event.KeyEvent e) {

				SwingUtilities.invokeLater(() -> {
					if (!needSave)
						needSave = true;
					errorTokens.clear();
					setToolTipText(null);
					buildText(getText(), false);
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
						if (getWordUnderMouse(offset, errorToken)) {
							var errors = errorToken.errors.subList(0, Math.min(errorToken.errors.size(), 5));
							var message = String.join("  -*-  ", errors);
							setToolTipText(message);
						}
				}
			}
		});
	}

	public void setEditorText(String text) {
		this.setText(text);
		buildText(text, false);

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
		needSave = value;
	}

	public void formatText() {
		buildText(getText(), true);
	}

	public void buildText(String text, boolean formatText) {
		var doc = getStyledDocument();
		List<Token> tokens = parsingFacade.ParseText(text, formatText);

		if (formatText)
			setText(String.join("", tokens.stream().map(a -> a.value).collect(Collectors.toList())));
		int currentIndex = 0;
		var size=tokens.size();
		for (int i = 0; i < size; i++) {
			var token = tokens.get(i);
			token.startIndex = currentIndex;
			token.endIndex = currentIndex + token.value.length();
			doc.setCharacterAttributes(currentIndex, token.value.length(), token.tokenStyle, false);
			if (token.errors.size() > 0) {
				if (token.tokenType instanceof EndOfText &&i>0) {
					var j=2;
					var prevToken = tokens.get( size- j);
					while(prevToken.tokenType instanceof WhiteSpace&& j<size) {
						 prevToken = tokens.get(size - ++j);
					}
					if (errorTokens.contains(prevToken)) {
						continue;
					}else {
						prevToken.errors.addAll(token.errors);
						errorTokens.add(prevToken);
					}
					doc.setCharacterAttributes(prevToken.startIndex, prevToken.value.length(), CustomStyle.errorStyle, false);
					
				}
				else {
					errorTokens.add(token);
					doc.setCharacterAttributes(currentIndex, token.value.length(), CustomStyle.errorStyle, false);
				}
			}
			currentIndex += token.value.length();
		}
	}

	private void setTabStops(int tabSize) {
		// Calculate the width of a single space
		int spaceWidth = getFontMetrics(getFont()).charWidth(' ');

		// Calculate the tab width based on the specified number of spaces
		int tabWidth = tabSize * spaceWidth;

		// Create a TabSet with the calculated tab width
		TabStop[] tabStops = new TabStop[10]; // You can adjust the number of tab stops
		for (int i = 0; i < tabStops.length; i++) {
			tabStops[i] = new TabStop((i + 1) * tabWidth);
		}
		TabSet tabSet = new TabSet(tabStops);

		// Apply the TabSet to the text pane's paragraph attributes
		SimpleAttributeSet paragraphAttributes = new SimpleAttributeSet();
		StyleConstants.setTabSet(paragraphAttributes, tabSet);
		getStyledDocument().setParagraphAttributes(0, getDocument().getLength(), paragraphAttributes, false);
	}

}
