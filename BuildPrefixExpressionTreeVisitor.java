package cs4321.project1;

import cs4321.project1.list.*;
import cs4321.project1.tree.*;

/**
 * Provide a comment about what your class does and the overall logic
 * 
 * @author Your names and netids go here
 */
public class BuildPrefixExpressionTreeVisitor implements TreeVisitor {

	private ListNode result;
	private ListNode head;
	
	public BuildPrefixExpressionTreeVisitor() {
		// TODO fill me in
		head = new NumberListNode(0);
		result = head;
	}

	public ListNode getResult() {
		// TODO fill me in
		return head.getNext();
	}

	@Override
	public void visit(LeafTreeNode node) {
		// TODO fill me in
		result.setNext(new NumberListNode(node.getData()));
		result = result.getNext();
	}

	@Override
	public void visit(UnaryMinusTreeNode node) {
		// TODO fill me in
		result.setNext(new UnaryMinusListNode());
		result = result.getNext();
		result.setNext(new NumberListNode(((LeafTreeNode) node.getChild()).getData()));
		result = result.getNext();
	}

	@Override
	public void visit(AdditionTreeNode node) {
		// TODO fill me in
		result.setNext(new AdditionListNode());
		result = result.getNext();
		result.setNext(new NumberListNode(((LeafTreeNode) node.getLeftChild()).getData()));
		result = result.getNext();
		result.setNext(new NumberListNode(((LeafTreeNode) node.getRightChild()).getData()));
		result = result.getNext();
	}

	@Override
	public void visit(MultiplicationTreeNode node) {
		// TODO fill me in
		result.setNext(new MultiplicationListNode());
		result = result.getNext();
		result.setNext(new NumberListNode(((LeafTreeNode) node.getLeftChild()).getData()));
		result = result.getNext();
		result.setNext(new NumberListNode(((LeafTreeNode) node.getRightChild()).getData()));
		result = result.getNext();
	}

	@Override
	public void visit(SubtractionTreeNode node) {
		// TODO fill me in
		result.setNext(new SubtractionListNode());
		result = result.getNext();
		result.setNext(new NumberListNode(((LeafTreeNode) node.getLeftChild()).getData()));
		result = result.getNext();
		result.setNext(new NumberListNode(((LeafTreeNode) node.getRightChild()).getData()));
		result = result.getNext();
	}

	@Override
	public void visit(DivisionTreeNode node) {
		// TODO fill me in
		result.setNext(new DivisionListNode());
		result = result.getNext();
		result.setNext(new NumberListNode(((LeafTreeNode) node.getLeftChild()).getData()));
		result = result.getNext();
		result.setNext(new NumberListNode(((LeafTreeNode) node.getRightChild()).getData()));
		result = result.getNext();
	}

}
