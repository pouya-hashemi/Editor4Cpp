package grammars.subGrammars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import constants.GrammarLibrary;
import entities.GrammarNode;
import entities.SingleNode;
import entities.StatementNode;
import entities.TokenTypes.FloatingPointLiteral;
import entities.TokenTypes.NumericLiteral;
import entities.TokenTypes.TextLiteral;
import entities.TokenTypes.Keywords.BreakKeyword;
import entities.TokenTypes.Keywords.CaseKeyword;
import entities.TokenTypes.Literals.BoolLiteral;
import entities.TokenTypes.Punctuations.CloseCurlyBracket;
import entities.TokenTypes.Punctuations.ColonType;
import entities.TokenTypes.Punctuations.CommaType;
import entities.TokenTypes.Punctuations.OpenCurlyBracket;
import entities.TokenTypes.Punctuations.SemicolonType;
import grammars.Grammar;

public class ArrayValueSubGrammar  extends Grammar {

	public ArrayValueSubGrammar(int id, List<GrammarNode> grammarNodes, UUID rootNodeId) {
		super(id, grammarNodes, rootNodeId);

	}

	public ArrayValueSubGrammar() {
		super();

		GrammarNode root = new GrammarNode();
		rootNodeId = root.Id;
		
		SingleNode openCurlyBracket_Node1 = new SingleNode(new OpenCurlyBracket(), false);
		
		StatementNode arrayValue_Node2 = new StatementNode(() -> GrammarLibrary.getParsingObjectsOfArrayValueSubGrammar(), false);

		SingleNode textLiteral_Node3 = new SingleNode(new TextLiteral(), false);

		SingleNode numericLiteral_Node4 = new SingleNode(new NumericLiteral(), false);

		SingleNode floatLiteral_Node5 = new SingleNode(new FloatingPointLiteral(), false);

		SingleNode boolLiteral_Node6 = new SingleNode(new BoolLiteral(), false);
		
		SingleNode commaForLiteral_Node7 = new SingleNode(new CommaType(), false);
		
		SingleNode commaForArrayValue_Node8 = new SingleNode(new CommaType(), false);
		
		SingleNode closeCurlyBracket_Node9 = new SingleNode(new CloseCurlyBracket(), true);
		
		SingleNode secondFloatLiteral_Node10 = new SingleNode(new FloatingPointLiteral(), false);
		
		SingleNode ThirdFloatLiteral_Node11 = new SingleNode(new FloatingPointLiteral(), false);

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
		
		textLiteral_Node3.addChild(textLiteral_Node3.Id);
		textLiteral_Node3.addChild(commaForLiteral_Node7.Id);
		
		numericLiteral_Node4.addChild(commaForLiteral_Node7.Id);
		
		floatLiteral_Node5.addChild(secondFloatLiteral_Node10.Id);
		
		secondFloatLiteral_Node10.addChild(ThirdFloatLiteral_Node11.Id);

		
		
		boolLiteral_Node6.addChild(commaForLiteral_Node7.Id);
		boolLiteral_Node6.addChild(closeCurlyBracket_Node9.Id);
		
		textLiteral_Node3.addChild(commaForLiteral_Node7.Id);
		textLiteral_Node3.addChild(closeCurlyBracket_Node9.Id);
		
		ThirdFloatLiteral_Node11.addChild(commaForLiteral_Node7.Id);
		ThirdFloatLiteral_Node11.addChild(closeCurlyBracket_Node9.Id);
		
		numericLiteral_Node4.addChild(commaForLiteral_Node7.Id);
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
		grammarNodes.add(secondFloatLiteral_Node10);
		grammarNodes.add(ThirdFloatLiteral_Node11);


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
