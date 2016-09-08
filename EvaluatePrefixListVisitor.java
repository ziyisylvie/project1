package cs4321.project1;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import cs4321.project1.list.*;

/**
 * Provide a comment about what your class does and the overall logic
 * 
 * @author Your names and netids go here
 */

public class EvaluatePrefixListVisitor implements ListVisitor {

	private Stack <Double> operands;
	private Stack <Map<ListNode, Integer>> operator;
	
	public EvaluatePrefixListVisitor() {
		// TODO fill me in
		operands = new Stack<Double>();
		operator = new Stack<Map<ListNode, Integer>>();
	}

	public double getResult() {
		// TODO fill me in
		 // so that skeleton code compiles
		if(operator.isEmpty())
			return operands.pop();
		for(ListNode i : operator.peek().keySet()){
			if(operator.peek().get(i) == 0){
				for(Map<ListNode, Integer> m : operator){
					if(m.keySet().toArray()[0] instanceof AdditionListNode){
						double a = operands.pop();
						double b = operands.pop();
						operands.push(a + b);
					}
					else if(m.keySet().toArray()[0] instanceof SubtractionListNode){
						double a = operands.pop();
						double b = operands.pop();
						operands.push(a - b);
					}
					else if(m.keySet().toArray()[0] instanceof MultiplicationListNode){
						double a = operands.pop();
						double b = operands.pop();
						operands.push(a * b);
					}
					else if(m.keySet().toArray()[0] instanceof DivisionListNode){
						double a = operands.pop();
						double b = operands.pop();
						operands.push(a / b);
					}
					else if(m.keySet().toArray()[0] instanceof UnaryMinusListNode){
						double a = operands.pop();
						operands.push((-1) * a);
					}
				}
			}
		}
		return operands.pop();
	}

	@Override
	public void visit(NumberListNode node) {
		// TODO fill me in
		operands.push(node.getData());
		for(Map<ListNode, Integer> m: operator){
			for(ListNode i : m.keySet()){
				if(m.get(i) != 0)
					m.put(i, m.get(i) - 1);
			}
 		}
		if(node.getNext() != null)
			node.getNext().accept(this);
	}

	@Override
	public void visit(AdditionListNode node) {
		// TODO fill me in
		ListNode pointer = node;
		int count = 0;
		while (pointer!= null){
			if(pointer instanceof NumberListNode){
				count++;
			}
			pointer = pointer.getNext();
		}
		Map<ListNode, Integer> m = new HashMap<ListNode, Integer>(); 
		m.put(node, count);
		operator.push(m);
		node.getNext().accept(this);
		
	}

	@Override
	public void visit(SubtractionListNode node) {
		// TODO fill me in
		ListNode pointer = node;
		int count = 0;
		while (pointer!= null){
			if(pointer instanceof NumberListNode){
				count++;
			}
			pointer = pointer.getNext();
		}
		Map<ListNode, Integer> m = new HashMap<ListNode, Integer>(); 
		m.put(node, count);
		operator.push(m);
		node.getNext().accept(this);
	}

	@Override
	public void visit(MultiplicationListNode node) {
		// TODO fill me in
		ListNode pointer = node;
		int count = 0;
		while (pointer!= null){
			if(pointer instanceof NumberListNode){
				count++;
			}
			pointer = pointer.getNext();
		}
		Map<ListNode, Integer> m = new HashMap<ListNode, Integer>(); 
		m.put(node, count);
		operator.push(m);
		node.getNext().accept(this);
	}

	@Override
	public void visit(DivisionListNode node) {
		// TODO fill me in
		ListNode pointer = node;
		int count = 0;
		while (pointer!= null){
			if(pointer instanceof NumberListNode){
				count++;
			}
			pointer = pointer.getNext();
		}
		Map<ListNode, Integer> m = new HashMap<ListNode, Integer>(); 
		m.put(node, count);
		operator.push(m);
		node.getNext().accept(this);
	}

	@Override
	public void visit(UnaryMinusListNode node) {
		// TODO fill me in
		ListNode pointer = node;
		int count = 0;
		while (pointer!= null){
			if(pointer instanceof NumberListNode){
				count++;
			}
			pointer = pointer.getNext();
		}
		Map<ListNode, Integer> m = new HashMap<ListNode, Integer>(); 
		m.put(node, count);
		operator.push(m);
		node.getNext().accept(this);
	}
}
