package parsers;

import entities.ParsingNode;
import entities.TokenTypes.FloatingPointLiteral;
import entities.TokenTypes.Identifier;
import entities.TokenTypes.Literal;
import entities.TokenTypes.TextLiteral;
import entities.TokenTypes.Punctuations.EqualType;
import entities.TokenTypes.Punctuations.SemicolonType;

public class SimpleAssignmentParser extends Parser {

	public SimpleAssignmentParser(Identifier identifier,Literal literal) {
		root = new ParsingNode();

		ParsingNode identifierNode = new ParsingNode();
		identifierNode.setTokenType(identifier);
		root.addNode(identifierNode);
		
		ParsingNode equalNode = new ParsingNode();
		equalNode.setTokenType(new EqualType());
		identifierNode.addNode(equalNode);
		
		ParsingNode literalNode = new ParsingNode();
		literalNode.setTokenType(literal);
		equalNode.addNode(literalNode);
		
		
		ParsingNode semicolonNode = new ParsingNode();
		semicolonNode.setTokenType(new SemicolonType());
		literalNode.addNode(semicolonNode);
		
		
		if(literal instanceof TextLiteral)
			literalNode.addNode(literalNode);
		
		if(literal instanceof FloatingPointLiteral) {
			ParsingNode DotNode = new ParsingNode();
			DotNode.setTokenType(new FloatingPointLiteral());
			
			literalNode.addNode(DotNode);
			
			ParsingNode floatingPointNode = new ParsingNode();
			floatingPointNode.setTokenType(new FloatingPointLiteral());
			
			DotNode.addNode(floatingPointNode);
			floatingPointNode.addNode(semicolonNode);
		}
		
		ParsingNode identifierToAssignToNode = new ParsingNode();
		identifierToAssignToNode.setTokenType(identifier);
		
		equalNode.addNode(identifierToAssignToNode);
		identifierToAssignToNode.addNode(semicolonNode);
		
		
		currentNode = root;
		
	}

}
