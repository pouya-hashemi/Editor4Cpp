package parsers;

import entities.ParsingNode;
import entities.TokenTypes.Identifier;
import entities.TokenTypes.Literal;
import entities.TokenTypes.Keywords.IfKeyword;
import entities.TokenTypes.Operations.DoubleComparisonOperator;
import entities.TokenTypes.Operations.LogicalNotOperator;
import entities.TokenTypes.Operations.LogicalOperator;
import entities.TokenTypes.Operations.SingleComparisonOperator;
import entities.TokenTypes.Punctuations.CloseParenthesisType;
import entities.TokenTypes.Punctuations.OpenParenthesisType;

public class IfStatementParser extends Parser  {

	public IfStatementParser() {
		root = new ParsingNode();

		ParsingNode if_Node1 = new ParsingNode();
		if_Node1.setTokenType(new IfKeyword());
		
		ParsingNode openParenthesis_Node2 = new ParsingNode();
		openParenthesis_Node2.setTokenType(new OpenParenthesisType());
		
		ParsingNode logicalNot_Node3 = new ParsingNode();
		logicalNot_Node3.setTokenType(new LogicalNotOperator());
		
		ParsingNode identifier_Node4 = new ParsingNode();
		identifier_Node4.setTokenType(new Identifier(""));
		
		ParsingNode literal_Node5 = new ParsingNode();
		literal_Node5.setTokenType(new Literal());
		
		ParsingNode singleComparisonOperator_Node6 = new ParsingNode();
		singleComparisonOperator_Node6.setTokenType(new SingleComparisonOperator());
		
		ParsingNode firstDoubleComparisonOperator_Node7 = new ParsingNode();
		firstDoubleComparisonOperator_Node7.setTokenType(new DoubleComparisonOperator());
		
		ParsingNode secondDoubleComparisonOperator_Node8 = new ParsingNode();
		secondDoubleComparisonOperator_Node8.setTokenType(new DoubleComparisonOperator());
		
		ParsingNode identifier_Node9 = new ParsingNode();
		identifier_Node9.setTokenType(new Identifier(""));
		
		ParsingNode literal_Node10 = new ParsingNode();
		literal_Node10.setTokenType(new Literal());
		
		ParsingNode logicalOperator_Node11 = new ParsingNode();
		logicalOperator_Node11.setTokenType(new LogicalOperator());
		
		ParsingNode closeParenthesis_Node12 = new ParsingNode();
		closeParenthesis_Node12.setTokenType(new CloseParenthesisType());
		
		ParsingNode secondLogicalOperator_Node13 = new ParsingNode();
		secondLogicalOperator_Node13.setTokenType(new LogicalOperator());
		//-----------------------------------------------------------------
		root.addNode(if_Node1);
		if_Node1.addNode(openParenthesis_Node2);//E1
		openParenthesis_Node2.addNode(logicalNot_Node3);//E2
		logicalNot_Node3.addNode(openParenthesis_Node2);//E3
		openParenthesis_Node2.addNode(openParenthesis_Node2);//E4
		openParenthesis_Node2.addNode(identifier_Node4);//E5
		openParenthesis_Node2.addNode(literal_Node5);//E6
		identifier_Node4.addNode(singleComparisonOperator_Node6);//E7
		identifier_Node4.addNode(firstDoubleComparisonOperator_Node7);//E8
		literal_Node5.addNode(singleComparisonOperator_Node6);//E9
		literal_Node5.addNode(firstDoubleComparisonOperator_Node7);//E10
		firstDoubleComparisonOperator_Node7.addNode(secondDoubleComparisonOperator_Node8);//E11
		singleComparisonOperator_Node6.addNode(identifier_Node9);//E12
		singleComparisonOperator_Node6.addNode(literal_Node10);//E13
		secondDoubleComparisonOperator_Node8.addNode(literal_Node10);//E14
		secondDoubleComparisonOperator_Node8.addNode(identifier_Node9);//E15
		literal_Node10.addNode(closeParenthesis_Node12);//E16
		literal_Node10.addNode(logicalOperator_Node11);//E17
		identifier_Node9.addNode(logicalOperator_Node11);//E18
		identifier_Node9.addNode(closeParenthesis_Node12);//E19
		closeParenthesis_Node12.addNode(closeParenthesis_Node12);//E20
		closeParenthesis_Node12.addNode(logicalOperator_Node11);//E21
		secondLogicalOperator_Node13.addNode(literal_Node5);//E22
		secondLogicalOperator_Node13.addNode(identifier_Node4);//E23
		secondLogicalOperator_Node13.addNode(openParenthesis_Node2);//E24
		
		literal_Node5.addNode(literal_Node5);//E25
		literal_Node10.addNode(literal_Node10);//E26
		logicalNot_Node3.addNode(identifier_Node4);//E27
		logicalNot_Node3.addNode(literal_Node5);//E28
		logicalOperator_Node11.addNode(secondLogicalOperator_Node13);//E29
		secondLogicalOperator_Node13.addNode(logicalNot_Node3);//E30

		
		currentNode=root;
		
	}
	@Override
	public boolean isEnd() {
		return currentNode.getTokenType() instanceof CloseParenthesisType && openParenthesis<=0;
	}
}
