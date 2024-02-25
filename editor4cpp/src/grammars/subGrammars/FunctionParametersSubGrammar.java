package grammars.subGrammars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import constants.GrammarLibrary;
import entities.GrammarNode;
import entities.NonTerminalNode;
import entities.TerminalNode;
import entities.TokenTypes.Identifier;
import entities.TokenTypes.StreamExtractionType;
import entities.TokenTypes.StreamInsertionType;
import entities.TokenTypes.Operations.MultiplyOperator;
import entities.TokenTypes.Punctuations.CloseParenthesisType;
import entities.TokenTypes.Punctuations.CommaType;
import entities.TokenTypes.Punctuations.OpenParenthesisType;
import grammars.Grammar;

public class FunctionParametersSubGrammar extends Grammar {

	public FunctionParametersSubGrammar(int id, List<GrammarNode> grammarNodes, UUID rootNodeId) {
		super(id, grammarNodes, rootNodeId);

	}

	public FunctionParametersSubGrammar() {
		super();

		GrammarNode root = new GrammarNode();
		rootNodeId = root.Id;
		
		TerminalNode openParenthesis_Node2 = new TerminalNode(new OpenParenthesisType(), false);

		NonTerminalNode topLevel_Node3 = new NonTerminalNode(() -> GrammarLibrary.getParsingObjectsOfMathematikOperationTopLayerSubGrammar(), false);

		TerminalNode comma_Node4 = new TerminalNode(new CommaType(), false);
		TerminalNode streamInsertion_Node6 = new TerminalNode(new StreamInsertionType(), false);
		TerminalNode streamExtraction_Node7 = new TerminalNode(new StreamExtractionType(), false);
		
		TerminalNode closeParenthesis_Node5 = new TerminalNode(new CloseParenthesisType(), true);
		
		TerminalNode starType_Node8 = new TerminalNode(new MultiplyOperator(), false);
		TerminalNode rightSideIdentifier_Node9 = new TerminalNode(new Identifier(""), false);
		

		// -----------------------------------------------------------------

		
		root.addChild(openParenthesis_Node2.Id);
		openParenthesis_Node2.addChild(closeParenthesis_Node5.Id);
		openParenthesis_Node2.addChild(topLevel_Node3.Id);
		openParenthesis_Node2.addChild(starType_Node8.Id);
		
		topLevel_Node3.addChild(comma_Node4.Id);
		topLevel_Node3.addChild(streamInsertion_Node6.Id);
		topLevel_Node3.addChild(streamExtraction_Node7.Id);
		topLevel_Node3.addChild(closeParenthesis_Node5.Id);
		
		comma_Node4.addChild(topLevel_Node3.Id);
		comma_Node4.addChild(starType_Node8.Id);
		
		streamInsertion_Node6.addChild(topLevel_Node3.Id);
		streamInsertion_Node6.addChild(starType_Node8.Id);
		
		streamExtraction_Node7.addChild(topLevel_Node3.Id);
		streamExtraction_Node7.addChild(starType_Node8.Id);

		starType_Node8.addChild(rightSideIdentifier_Node9.Id);
		
		rightSideIdentifier_Node9.addChild(comma_Node4.Id);
		rightSideIdentifier_Node9.addChild(streamInsertion_Node6.Id);
		rightSideIdentifier_Node9.addChild(streamExtraction_Node7.Id);
		rightSideIdentifier_Node9.addChild(closeParenthesis_Node5.Id);

		if (grammarNodes == null)
			grammarNodes = new ArrayList<GrammarNode>();
		grammarNodes.add(root);
		grammarNodes.add(openParenthesis_Node2);
		grammarNodes.add(topLevel_Node3);
		grammarNodes.add(comma_Node4);
		grammarNodes.add(closeParenthesis_Node5);
		grammarNodes.add(streamInsertion_Node6);
		grammarNodes.add(streamExtraction_Node7);
		grammarNodes.add(starType_Node8);
		grammarNodes.add(rightSideIdentifier_Node9);


	}

	@Override
	public Grammar clone() {
		var nodes = new ArrayList<GrammarNode>();
		for (GrammarNode node : this.grammarNodes) {
			nodes.add(node.clone());
		}
		return new FunctionParametersSubGrammar(this.Id, nodes, this.rootNodeId);
	}
}

