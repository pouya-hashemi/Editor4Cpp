package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

import Dtos.ParsingObject;

import services.ErrorDetecter;

public class StatementNode extends GrammarNode {
	public List<ParsingObject> parsingObjects;
	public ErrorDetecter errorDetector;
	private Supplier<List<ParsingObject>> parsingObjCollector;

	public StatementNode(Supplier<List<ParsingObject>> parsingObjCollector, boolean canBeEnd) {
		this.canBeEnd = canBeEnd;
		this.parsingObjCollector = parsingObjCollector;
	}
	public StatementNode(Supplier<List<ParsingObject>> parsingObjCollector, boolean canBeEnd,boolean countParenthesis) {
		this.canBeEnd = canBeEnd;
		this.parsingObjCollector = parsingObjCollector;
		this.countParenthesis=countParenthesis;
	}
	

	public StatementNode(UUID id, List<UUID> childIds, boolean canBeEnd,
			Supplier<List<ParsingObject>> parsingObjCollector,boolean countParenthesis) {
		super(id, childIds, canBeEnd,countParenthesis);
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
	public StatementNode clone() {
		var newIds = new ArrayList<UUID>();
		if (this.childIds != null)
			for (UUID id : this.childIds)
				newIds.add( UUID.fromString(id.toString()));
		return new StatementNode(this.Id, newIds, this.canBeEnd, this.parsingObjCollector,this.countParenthesis);
	}

}
