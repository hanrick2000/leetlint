package nineChap8_DSA;

import java.util.Stack;

/**
 * http://www.lintcode.com/en/problem/implement-queue-by-two-stacks/
 * Created by 10:32 PM on 10/12/2015.
 */
public class Queue2Stack {
    Stack<Integer> stk1;
    Stack<Integer> stk2;

    public Queue2Stack() {
        stk1 = new Stack<>();
        stk2 = new Stack<>();
    }

    public void push(int n) {
        stk1.push(n);
    }

    /**
     * The less else, the better!
     * Compare with top().
     *
     * @return
     */
    public int pop() {
        if (!stk2.isEmpty()) {
            return stk2.pop();
        }
        else {
            while (!stk1.isEmpty()) {
                stk2.push(stk1.pop());
            }
            return stk2.pop();
        }
    }

    public int top() {
        if (stk2.isEmpty()) {
            while (!stk1.isEmpty()) {
                stk2.push(stk1.pop());
            }
        }
        return stk2.peek();
    }
}
