package cs4321.project1;

import cs4321.project1.list.DivisionListNode;
import cs4321.project1.list.SubtractionListNode;
import cs4321.project1.list.NumberListNode;

import java.util.Stack;

import cs4321.project1.list.AdditionListNode;
import cs4321.project1.list.MultiplicationListNode;
import cs4321.project1.list.UnaryMinusListNode;

/**
 * Provide a comment about what your class does and the overall logic
 * 
 * @author Your names and netids go here
 */
public class EvaluatePostfixListVisitor implements ListVisitor {
	
	private Stack <Double>s ;

	public EvaluatePostfixListVisitor() {
		// TODO fill me in
		s = new Stack<Double>();
		
	}

	public double getResult() {
		// TODO fill me in
		return s.pop(); // so that skeleton code compiles
	}

	@Override
	public void visit(NumberListNode node) {
		// TODO fill me in
		s.push(node.getData());
		if(node.getNext() != null)
			node.getNext().accept(this);
	}

	@Override
	public void visit(AdditionListNode node) {
		// TODO fill me in
		double a = s.pop();
		double b = s.pop();
		s.push((a + b));
		if(node.getNext() != null)
			node.getNext().accept(this);
		
	}

	@Override
	public void visit(SubtractionListNode node) {
		// TODO fill me in
		double a = s.pop();
		double b = s.pop();
		s.push((a - b));
		if(node.getNext() != null)
			node.getNext().accept(this);
	}

	@Override
	public void visit(MultiplicationListNode node) {
		// TODO fill me in
		double a = s.pop();
		double b = s.pop();
		s.push((a * b));
		if(node.getNext() != null)
			node.getNext().accept(this);
	}

	@Override
	public void visit(DivisionListNode node) {
		// TODO fill me in
		double a = s.pop();
		double b = s.pop();
		s.push((a / b));
		if(node.getNext() != null)
			node.getNext().accept(this);
	}

	@Override
	public void visit(UnaryMinusListNode node) {
		// TODO fill me in
		double a = s.pop();
		s.push((-1) * a);
		if(node.getNext() != null)
			node.getNext().accept(this);
	}

}
