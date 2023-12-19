package entities;

import java.util.ArrayList;
import java.util.List;


public class ParsingNode {

	private TokenType tokenType;
	private List<ParsingNode> nextNodes;

	public ParsingNode() {
		this.nextNodes = new ArrayList<ParsingNode>();
	}

	public TokenType getTokenType() {
		return tokenType;
	}

	public List<ParsingNode> getNextNodes() {
		return nextNodes;
	}

	public boolean isEnd() {
		return nextNodes.isEmpty();
	}

	public void addNode(ParsingNode nextNode) {
		this.nextNodes.add(nextNode);
	}

	public void setTokenType(TokenType tokenType) {
		this.tokenType = tokenType;
	}
}
