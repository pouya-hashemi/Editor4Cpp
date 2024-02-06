package grammars.subGrammars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import constants.GrammarLibrary;
import entities.GrammarNode;
import entities.TerminalNode;
import entities.NonTerminalNode;
import entities.TokenTypes.FloatingPointLiteral;
import entities.TokenTypes.NumericLiteral;
import entities.TokenTypes.TextLiteral;
import entities.TokenTypes.Literals.BoolLiteral;
import entities.TokenTypes.Punctuations.CloseCurlyBracket;
import entities.TokenTypes.Punctuations.CommaType;
import entities.TokenTypes.Punctuations.OpenCurlyBracket;
import grammars.Grammar;

public class ArrayValueSubGrammar  extends Grammar {

	public ArrayValueSubGrammar(int id, List<GrammarNode> grammarNodes, UUID rootNodeId) {
		super(id, grammarNodes, rootNodeId);

	}

	public ArrayValueSubGrammar() {
		super();

		GrammarNode root = new GrammarNode();
		rootNodeId = root.Id;
		
		TerminalNode openCurlyBracket_Node1 = new TerminalNode(new OpenCurlyBracket(), false);
		
		NonTerminalNode arrayValue_Node2 = new NonTerminalNode(() -> GrammarLibrary.getParsingObjectsOfArrayValueSubGrammar(), false);

		TerminalNode textLiteral_Node3 = new TerminalNode(new TextLiteral(), false);

		TerminalNode numericLiteral_Node4 = new TerminalNode(new NumericLiteral(), false);

		TerminalNode floatLiteral_Node5 = new TerminalNode(new FloatingPointLiteral(), false);

		TerminalNode boolLiteral_Node6 = new TerminalNode(new BoolLiteral(), false);
		
		TerminalNode commaForLiteral_Node7 = new TerminalNode(new CommaType(), false);
		
		TerminalNode commaForArrayValue_Node8 = new TerminalNode(new CommaType(), false);
		
		TerminalNode closeCurlyBracket_Node9 = new TerminalNode(new CloseCurlyBracket(), true);
		
//		TerminalNode secondFloatLiteral_Node10 = new TerminalNode(new FloatingPointLiteral(), false);
		
//		TerminalNode ThirdFloatLiteral_Node11 = new TerminalNode(new FloatingPointLiteral(), false);

		// -----------------------------------------------------------------
		root.addChild(openCurlyBracket_Node1.Id);
		
		openCurlyBracket_Node1.addChild(textLiteral_Node3.Id);
		openCurlyBracket_Node1.addChild(numericLiteral_Node4.Id);
		openCurlyBracket_Node1.addChild(floatLiteral_Node5.Id);
		openCurlyBracket_Node1.addChild(boolLiteral_Node6.Id);
		openCurlyBracket_Node1.addChild(arrayValue_Node2.Id);
		
		arrayValue_Node2.addChild(commaForArrayValue_Node8.Id);
		arrayValue_Node2.addChild(closeCurlyBracket_Node9.Id);
		commaForArrayValue_Node8.addChild(arrayValue_Node2.Id);
		
//		textLiteral_Node3.addChild(textLiteral_Node3.Id);
		textLiteral_Node3.addChild(commaForLiteral_Node7.Id);
		
		numericLiteral_Node4.addChild(commaForLiteral_Node7.Id);
		
//		floatLiteral_Node5.addChild(secondFloatLiteral_Node10.Id);
		
//		secondFloatLiteral_Node10.addChild(ThirdFloatLiteral_Node11.Id);

		
		boolLiteral_Node6.addChild(commaForLiteral_Node7.Id);
		commaForLiteral_Node7.addChild(boolLiteral_Node6.Id);
		boolLiteral_Node6.addChild(closeCurlyBracket_Node9.Id);
		
		textLiteral_Node3.addChild(commaForLiteral_Node7.Id);
		commaForLiteral_Node7.addChild(textLiteral_Node3.Id);
		textLiteral_Node3.addChild(closeCurlyBracket_Node9.Id);
		
		floatLiteral_Node5.addChild(commaForLiteral_Node7.Id);
		commaForLiteral_Node7.addChild(floatLiteral_Node5.Id);
		floatLiteral_Node5.addChild(closeCurlyBracket_Node9.Id);
		
		numericLiteral_Node4.addChild(commaForLiteral_Node7.Id);
		commaForLiteral_Node7.addChild(numericLiteral_Node4.Id);
		numericLiteral_Node4.addChild(closeCurlyBracket_Node9.Id);
		

		if (grammarNodes == null)
			grammarNodes = new ArrayList<GrammarNode>();
		grammarNodes.add(root);
		grammarNodes.add(openCurlyBracket_Node1);
		grammarNodes.add(arrayValue_Node2);
		grammarNodes.add(textLiteral_Node3);
		grammarNodes.add(numericLiteral_Node4);
		grammarNodes.add(floatLiteral_Node5);
		grammarNodes.add(boolLiteral_Node6);
		grammarNodes.add(commaForLiteral_Node7);
		grammarNodes.add(commaForArrayValue_Node8);
		grammarNodes.add(closeCurlyBracket_Node9);
//		grammarNodes.add(secondFloatLiteral_Node10);
//		grammarNodes.add(ThirdFloatLiteral_Node11);


	}

	@Override
	public Grammar clone() {
		var nodes = new ArrayList<GrammarNode>();
		for (GrammarNode node : this.grammarNodes) {
			nodes.add(node.clone());
		}
		return new ArrayValueSubGrammar(this.Id, nodes, this.rootNodeId);
	}
}
