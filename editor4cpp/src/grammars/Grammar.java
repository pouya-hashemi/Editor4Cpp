package grammars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import entities.GrammarNode;

public class Grammar implements Cloneable  {

	public int Id;
	public List<GrammarNode> grammarNodes;
	public UUID rootNodeId;

	public Grammar() {
		this.Id = GrammarIdGenerator.GetInstance().generateId();
	}

	public Grammar(int id, List<GrammarNode> grammarNodes, UUID rootNodeId) {
		this.Id = id;
		this.grammarNodes = grammarNodes;
		this.rootNodeId = rootNodeId;
	}

	public GrammarNode getGrammarNodeById(UUID id) {
		return grammarNodes.stream().filter(a -> a.Id.equals(id)).findFirst().orElse(null);
	}

//	public List<Grammar> breakDown(UUID currentNodeId, UUID childId) {
//		List<Grammar> newGrammars = new ArrayList<Grammar>();
//
//		var childNode = getGrammarNodeById(childId).get();
//
//		if (childNode.getClass() == SingleNode.class) {
//
//			return newGrammars;
//		}
//
//		else if (childNode.getClass() == StatementNode.class) {
//			var nextNodes = childNode.getChildIds();
//
//			for (ParsingObject parsingObject : ((StatementNode) childNode).cloneParsingObject()) {
//
//				var newGrammar = clone();
//
//				List<GrammarNode> nodesToAdd = parsingObject.grammar.getPureNodes();
//
//				nodesToAdd.stream().filter(a -> a.canBeEnd).forEach(o -> o.addChild(nextNodes));
//
//				newGrammar.grammarNodes.addAll(nodesToAdd);
//
//				// parsingObject.grammar.grammarNodes.stream().filter(a->a.canBeEnd).forEach(o->o.addChild(nextNodes));
//				var currentNode = newGrammar.grammarNodes.stream().filter(a -> a.Id == currentNodeId).findFirst();
//				if (currentNode.isEmpty())
//					return null;
//
//				currentNode.get().removeChild(childId);
//				currentNode.get().addChild(
//						parsingObject.grammar.getGrammarNodeById(parsingObject.grammar.rootNodeId).get().getChildIds());
//				newGrammars.add(newGrammar);
//			}
//
//		}
//
//		return newGrammars;
//	}

	public List<GrammarNode> getPureNodes() {
		var nodes = new ArrayList<GrammarNode>();
		for (GrammarNode node : this.grammarNodes.stream().filter(a -> a.Id != this.rootNodeId)
				.collect(Collectors.toList())) {
			nodes.add(node.clone());
		}
		return nodes;
	}

	@Override
	public Grammar clone() {
		var nodes = new ArrayList<GrammarNode>();
		for (GrammarNode node : this.grammarNodes) {
			nodes.add(node.clone());
		}
		return new Grammar(this.Id, nodes, this.rootNodeId);
	}



	
}
