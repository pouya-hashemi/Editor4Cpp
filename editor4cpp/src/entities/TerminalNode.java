package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SingleNode extends GrammarNode {

	public TokenType tokenType;

	public SingleNode(TokenType tokenType, boolean canBeEnd) {
		super();
		this.tokenType = tokenType;
		this.canBeEnd = canBeEnd;
	}
	public SingleNode(TokenType tokenType, boolean canBeEnd,boolean countParenthesis) {
		super();
		this.tokenType = tokenType;
		this.canBeEnd = canBeEnd;
		this.countParenthesis=countParenthesis;
	}

	public SingleNode(UUID id, List<UUID> childIds, boolean canBeEnd, TokenType tokenType,boolean countParenthesis) {
		super(id, childIds, canBeEnd,countParenthesis);
		this.tokenType = tokenType;
	}

	public SingleNode() {
		super();

	}

	@Override
	public SingleNode clone() {
		var newIds = new ArrayList<UUID>();
		if (this.childIds != null)
			for (UUID id : this.childIds)
				newIds.add( UUID.fromString(id.toString()));
		return new SingleNode(this.Id, newIds, this.canBeEnd, this.tokenType,this.countParenthesis);
	}
}
