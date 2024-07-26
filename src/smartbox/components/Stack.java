package smartbox.components;

import smartbox.*;

public class Stack extends Component implements IStack {

    private java.util.Stack<Double> stack;

    public Stack() {
        super();
        stack = new java.util.Stack<Double>();
    }

    @Override
    public void push(Double num) {
        stack.push(num);
    }

    @Override
    public void pop() {
        stack.pop();
    }

    @Override
    public Double top() {
        return stack.peek();
    }

    @Override
    public void clear() {
        while(!stack.empty()) {
            stack.pop();
        }
    }

    @Override
    public Boolean isEmpty() {
        return stack.empty();
    }

// ???
}