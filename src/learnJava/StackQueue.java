package learnJava;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

// import java.util.Stack;

public class StackQueue {
  public static void main(String[] args) {
    // -------------------------------------------Stack
    // Stack<Integer> s = new Stack<>();  // ����
    // LinkedList<Integer> s = new LinkedList<Integer>(); // Ganker
    Deque<Integer> s = new ArrayDeque<Integer>();  // ���Ƽ�д��
    s.push(1);
    s.push(2);
    s.push(3);
    s.push(4);

    while (!s.isEmpty())
      System.out.print(s.pop() + " ");

    // -------------------------------------------Queue
    // Queue<Integer> q = new Queue<>();
    Queue<Integer> q = new LinkedList<Integer>();
    q.offer(1);
    q.offer(2);
    q.offer(3);
    q.offer(4);

    System.out.println();
    while (!q.isEmpty())
      System.out.print(q.poll() + " ");

    // -------------------------------------------Stack
    // Ganker's simplify path using linkedlist as stack
    LinkedList<Integer> stk = new LinkedList<>();
    stk.push(4);
    stk.push(3);
    stk.push(2);
    stk.push(1);
    //Integer[] holder = stk.toArray(new Integer[stk.size()]);
    while (!stk.isEmpty()) {
      stk.pop();
    }

  }
}
