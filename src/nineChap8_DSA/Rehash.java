package nineChap8_DSA;

import java.util.List;

/**
 * Created by 9:12 PM on 10/12/2015.
 */
public class Rehash {
    /**
     * Why I must use static inner class?
     * Bcz I need to print null. but non-static method cannot stay in inner class
     * Or, I can simply put print out of inner class!
     */
    class ListNode {
        int val;
        ListNode next;

        ListNode(int v) {
            this.val = v;
        }
    }

    public static void print(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println("NUL_node");
    }

    public void test() {
        ListNode ey = new ListNode(21);
        ListNode ji = new ListNode(-9);
        ListNode tt = new ListNode(29);
        ListNode ttt = new ListNode(37);
        ey.next = ji;
        ji.next = tt;
        tt.next = ttt;

        ListNode ss = new ListNode(14);
        ListNode[] input = new ListNode[]{null, ey, ss, null};

        input = rehashing(input);
        for (ListNode node : input) {
//            node.print(node);
            print(node);
        }
    }

    /**
     * How about neg node????
     *
     * @param node
     * @param capacity
     * @return
     */
    private static int hash(ListNode node, int capacity) {
        return (node.val % capacity + capacity) % capacity;
    }

    public ListNode[] rehashing(ListNode[] hashTable) {
        int capacity = hashTable.length;
        ListNode[] newHash = new ListNode[capacity*2];

        for (ListNode node : hashTable) {
            while (node != null) {
                int idx = hash(node, capacity*2);
                if (newHash[idx] == null) {
                    newHash[idx] = new ListNode(node.val);
                }
                else {
//                    newHash[idx].next = new ListNode(node.val);  // Need to be careful!
                    ListNode head = newHash[idx];
                    while (head.next != null) {
                        head = head.next;
                    }
                    head.next = new ListNode(node.val);
                }
                node = node.next;
            }
        }
        return newHash;
    }

    public static void main(String[] args) {
        Rehash rh = new Rehash();
        rh.test();
    }


}
