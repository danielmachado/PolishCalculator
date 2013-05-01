package uo.miw.gcw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Calculator {
	public static final String PLUS = "+";
	public static final String MINUS = "-";
	public static final String MULT = "*";
	public static final String DIV = "/";
	public static final String CLR = "Clr";
	public static final String MI = "Mi";
	public static final String MO = "Mo";
	
	private Stack stack = new Stack();
	private String errorMsg;
	private double memory;

	public void addValue(String value) {
		try {
			stack.push(Double.parseDouble(value));
			errorMsg = null;
		} catch (NumberFormatException nef) {
			errorMsg = "Not a number";
		}
	}

	public void operate(String operator) {
		double tmp;
		operator = operator.trim();
		try {
			if (operator.equals(PLUS)) {
				assertAtLeastTwoOperands();
				stack.push(stack.pop() + stack.pop());
			}
			if (operator.equals(MINUS)) {
				assertAtLeastTwoOperands();
				tmp = stack.pop();
				stack.push(stack.pop() - tmp);
			}
			if (operator.equals(MULT)) {
				assertAtLeastTwoOperands();
				stack.push(stack.pop() * stack.pop());
			}
			if (operator.equals(DIV)) {
				assertAtLeastTwoOperands();
				tmp = stack.pop();
				stack.push(stack.pop() / tmp);
			}
			if (operator.equals(CLR)) {
				stack.clear();
			}
			if (operator.equals(MI)) {
				memory = stack.peek();
			}
			if (operator.equals(MO)) {
				stack.push(memory);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
		}
	}

	private void assertAtLeastTwoOperands() throws Exception {
		if (stack.size() < 2) {
			throw new Exception("Al least two operands needed");
		}
	}

	public String getResult() {
		if (errorMsg != null) {
			return errorMsg;
		}
		if (stack.isEmpty()) {
			return "0";
		}
		return Double.toString(stack.peek());
	}
	
	public List<String> getLines(){
		return stack.dump();
	}

	private class Stack {
		private List<Double> list = new ArrayList<Double>();

		public void push(double operand) {
			list.add(operand);
		}

		public double pop() {
			int pos = list.size() - 1;
			double res = list.get(pos);
			list.remove(pos);
			return res;
		}

		public double peek() {
			int pos = list.size() - 1;
			return list.get(pos);
		}

		public void clear() {
			list.clear();
		}

		public boolean isEmpty() {
			return list.size() == 0;
		}

		public int size() {
			return list.size();
		}

		public List<String> dump() {
			List<String> res = new ArrayList<String>();
			
			for(Double d: list){
				res.add(d.toString());
			}
			Collections.reverse(res);
			return res;
		}
	}
}
