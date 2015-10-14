package nineChap8_DSA;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/**
 * http://www.lintcode.com/en/problem/min-stack/
 * Implement a stack with min() function, which will return the smallest number in the stack.
 * It should support push, pop and min operation all in O(1) cost.
 * Very good problem! data struct focusing on API, not the real data underhood!
 *
 * Created by 6:55 PM on 10/12/2015.
 */
public class MinStack {
    Stack<Integer> stk;
//    Queue<Integer> minQ;
    Stack<Integer> minStk;

    public MinStack() {
        stk = new Stack<>();
//        minQ = new PriorityQueue<>();
        minStk = new Stack<>();
    }

    public void push(int number) {
        stk.push(number);
//        minQ.offer(number);
        if (minStk.isEmpty()) {
            minStk.push(number);
        }
        else if (minStk.peek() <= number) {
            minStk.push(minStk.peek());
        }
        else {
            minStk.push(number);
        }
    }

    public int pop() {
        int popV = stk.pop();
//        minQ.remove(popV);
        minStk.pop();
        return popV;
    }

    public int min() {
//        int minV = minQ.poll();
//        stk.remove(minV);
//        stk.removeElement(minV);
        int minV = minStk.peek();
        return minV;
    }

    public static void main(String[] args) {
        MinStack ms = new MinStack();
        int v;
        ms.push(1);
        v = ms.pop();
        System.out.println(v);
        ms.push(2);
        ms.push(3);
        v = ms.min();
        System.out.println(v);
        ms.push(1);
        v = ms.min();
        System.out.println(v);
    }
}
