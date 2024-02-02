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


	public List<GrammarNode> getNodesExceptRoot() {
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
