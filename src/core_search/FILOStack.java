package core_search;

import java.util.Stack;

public class FILOStack<S,A> implements MyPriorityQueue<S,A> {
    private final Stack<Node<S,A>> myStack = new Stack<>();
    public Node<S,A> pop() {
        return myStack.pop();
    }

    public void add(Node<S, A> node) {
        myStack.add(node);
    }

    public boolean isEmpty() {
        return myStack.isEmpty();
    }
}