package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TerminalNode extends GrammarNode {

	public TokenType tokenType;

	public TerminalNode(TokenType tokenType, boolean canBeEnd) {
		super();
		this.tokenType = tokenType;
		this.canBeEnd = canBeEnd;
	}

	public TerminalNode(UUID id, List<UUID> childIds, boolean canBeEnd, TokenType tokenType) {
		super(id, childIds, canBeEnd);
		this.tokenType = tokenType;
	}

	public TerminalNode() {
		super();

	}

	@Override
	public TerminalNode clone() {
		var newIds = new ArrayList<UUID>();
		if (this.childIds != null)
			for (UUID id : this.childIds)
				newIds.add( UUID.fromString(id.toString()));
		return new TerminalNode(this.Id, newIds, this.canBeEnd, this.tokenType);
	}
}
