package cs4321.project1;

import cs4321.project1.tree.*;

/**
 * Class for a parser that can parse a string and produce an expression tree. To
 * keep the code simple, this does no input checking whatsoever so it only works
 * on correct input.
 * 
 * An expression is one or more terms separated by + or - signs. A term is one
 * or more factors separated by * or / signs. A factor is an expression in
 * parentheses (), a factor with a unary - before it, or a number.
 * 
 * @author Lucja Kot
 * @author Your names and netids go here
 */
public class Parser {

	private String[] tokens;
	private int currentToken; // pointer to next input token to be processed

	/**
	 * @precondition input represents a valid expression with all tokens
	 *               separated by spaces, e.g. "3.0 - ( 1.0 + 2.0 ) / - 5.0. All
	 *               tokens must be either numbers that parse to Double, or one
	 *               of the symbols +, -, /, *, ( or ), and all parentheses must
	 *               be matched and properly nested.
	 */
	public Parser(String input) {
		this.tokens = input.split("\\s+");
		currentToken = 0;
	}

	/**
	 * Parse the input and build the expression tree
	 * 
	 * @return the (root node of) the resulting tree
	 */
	public TreeNode parse() {
		return expression();
	}

	/**
	 * Parse the remaining input as far as needed to get the next factor
	 * 
	 * @return the (root node of) the resulting subtree
	 */
	private TreeNode factor() {

		// TODO fill me in
		if(tokens[currentToken].charAt(0) != '(' 
				&& tokens[currentToken].charAt(0) != '-' 
				&& tokens[currentToken].charAt(0) != ')'){
			double r = Double.parseDouble(tokens[currentToken]);
			//System.out.println(Double.parseDouble(tokens[currentToken]));
			return new LeafTreeNode(r);
		}
		else if(tokens[currentToken].charAt(0) == '-'){
			return new UnaryMinusTreeNode(new Parser(tokens[currentToken + 1]).factor());
		}
		else if(tokens[currentToken].charAt(0) == '('){
			String ele = "";
			for(int p = currentToken + 1; p < tokens.length; p++){
				if(tokens[p].charAt(0) == ')'){
					break;
				}
				else
					ele += tokens[p];
			}
			return new Parser(ele).expression();
		}
		else
			currentToken ++;
		return null;
	}

	/**
	 * Parse the remaining input as far as needed to get the next term
	 * 
	 * @return the (root node of) the resulting subtree
	 */
	private TreeNode term() {

		// TODO fill me in
		int pointer = tokens.length;
		for(int i = 0; i < tokens.length; i++){
			if (tokens[i].charAt(0) == '*' && tokens[i].charAt(0) == '/'){
				pointer = i + 1;
				break;
			}
			else
			    this.currentToken ++;
		}
		String s1 = "";
		String s2 = "";
		if(pointer == tokens.length){
			for(int a = 0; a < tokens.length - 1; a++){
				s1 = s1 + tokens[a] + " ";
			}
			s1 += tokens[tokens.length - 1];
			TreeNode result = new Parser(s1).factor();
			
			return result;
		}
		else{
			for(int j = 0; j < pointer - 2; j++)
				s1 = s1 + tokens[j] + " ";
			s1 = s1 + tokens[pointer - 2];
			for(int k = pointer; k < tokens.length - 1; k++){
				s2 = s2 + tokens[k] + " ";
			}
			s2 = s2 + tokens[tokens.length - 1];
			TreeNode result = new Parser(s1).factor();
			TreeNode result2;
			if(s1 == "")
				result2 = new Parser("-" + s2).factor();
			else
				result2 = new Parser(s2).factor();
			if(tokens[pointer - 1].charAt(0) == '*'){
				TreeNode root = new MultiplicationTreeNode(result, result2);
				result = root;
			}
			else if(tokens[pointer - 1].charAt(0) == '/'){
				TreeNode root = new DivisionTreeNode(result, result2);
				result = root;
			}
			return result;
		}
	}

	/**
	 * Parse the remaining input as far as needed to get the next expression
	 * 
	 * @return the (root node of) the resulting subtree
	 */
	private TreeNode expression() {

		// TODO fill me in
		int pointer = tokens.length;
		for(int i = 0; i < tokens.length; i++){
			
			if (tokens[i].charAt(0) == '+' && tokens[i].charAt(0) == '-'){
				pointer = i + 1;
				break;
			}
			else
			    this.currentToken ++;
		}
		String s1 = "";
		String s2 = "";
		
		if(pointer == tokens.length){
			for(int a = 0; a < tokens.length - 1; a++){
				s1 = s1 + tokens[a] + " ";
			}
			s1 += tokens[tokens.length - 1];
			TreeNode result = new Parser(s1).term();
			return result;
		}
		else{
			for(int j = 0; j < pointer - 2; j++)
				s1 = s1 + tokens[j] + " ";
			s1 = s1 + tokens[pointer - 2];
			for(int k = pointer; k < tokens.length - 1; k++){
				s2 = s2 + tokens[k] + " ";
			}
			s2 = s2 + tokens[tokens.length - 1];
			TreeNode result = new Parser(s1).term();
			TreeNode result2;
			if(s1 == "")
				result2 = new Parser("-" + s2).factor();
			else
				result2 = new Parser(s2).factor();
			if(tokens[pointer - 1].charAt(0) == '+'){
				TreeNode root = new AdditionTreeNode(result, result2);
				result = root;
			}
			else if(tokens[pointer - 1].charAt(0) == '-'){
				TreeNode root = new SubtractionTreeNode(result, result2);
				result = root;
			}
			return result;
		}
	}
}
