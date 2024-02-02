package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;
import Dtos.ParsingObject;


public class NonTerminalNode extends GrammarNode {
	public List<ParsingObject> parsingObjects;
	private Supplier<List<ParsingObject>> parsingObjCollector;

	public NonTerminalNode(Supplier<List<ParsingObject>> parsingObjCollector, boolean canBeEnd) {
		this.canBeEnd = canBeEnd;
		this.parsingObjCollector = parsingObjCollector;
	}
	

	public NonTerminalNode(UUID id, List<UUID> childIds, boolean canBeEnd,
			Supplier<List<ParsingObject>> parsingObjCollector) {
		super(id, childIds, canBeEnd);
		this.parsingObjCollector = parsingObjCollector;
	}

	public List<ParsingObject> cloneParsingObject() {
		parsingObjects = parsingObjCollector.get();
		var result = new ArrayList<ParsingObject>();
		for (ParsingObject obj : parsingObjects) {
			result.add(obj.clone());
		}
		return result;
	}

	@Override
	public NonTerminalNode clone() {
		var newIds = new ArrayList<UUID>();
		if (this.childIds != null)
			for (UUID id : this.childIds)
				newIds.add( UUID.fromString(id.toString()));
		return new NonTerminalNode(this.Id, newIds, this.canBeEnd, this.parsingObjCollector);
	}

}
