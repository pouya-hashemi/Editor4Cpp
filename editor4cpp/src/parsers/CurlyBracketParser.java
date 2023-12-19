package parsers;

import entities.ParsingNode;
import entities.TokenTypes.Punctuations.CloseCurlyBracket;
import entities.TokenTypes.Punctuations.OpenCurlyBracket;

public class CurlyBracketParser extends Parser {

	public CurlyBracketParser() {
		root = new ParsingNode();

		ParsingNode open_Node1 = new ParsingNode();
		open_Node1.setTokenType(new OpenCurlyBracket());

		ParsingNode close_Node2 = new ParsingNode();
		close_Node2.setTokenType(new CloseCurlyBracket());

		root.addNode(open_Node1);
		root.addNode(close_Node2);

		currentNode = root;
	}

	@Override
	public boolean isEnd() {

		return currentNode.getTokenType() instanceof OpenCurlyBracket
				|| currentNode.getTokenType() instanceof CloseCurlyBracket;
	}
}
