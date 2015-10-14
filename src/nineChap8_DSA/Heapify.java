package nineChap8_DSA;

import java.util.PriorityQueue;

public class Heapify {
    /**
     * A heapify but with O(lgn)*n = O(nlogn)
     * @param input
     */
    public static void heapifyMinPQ(int[] input) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(input.length);
        for (int i : input) {
            heap.offer(i);
        }
        int[] output = new int[input.length];

        for (int i = 0; i < output.length; ++i) {
            // System.out.print(output[i] + " ");
            System.out.print(heap.poll() + " ");
        }
    }

    /**
     * WHy this is O(N)? check 1p3r thread
     * http://www.1point3acres.com/bbs/thread-139934-1-1.html
     *
     * This is 9chap's solution, a little diff from Algs, it's start from index 1 instead od 0
     * @param A
     * @param k
     */
    public static void heapifyHelper(int[] A, int k) {
        while (k < A.length) { // fixing this subtree
            int smallest = k;
            if (2 * k + 1 < A.length && less(A, 2*k+1, smallest)) {
                smallest = 2*k+1;
            }
            if (2*k+2 < A.length && less(A, 2*k+2, smallest)) {
                smallest = 2*k+2;
            }
            if (smallest == k)  break;  // if subtree is good
            exch(A, k, smallest);
            k = smallest;
        }
    }

    private static boolean less(int[] A, int x, int y) {
        return (A[x] < A[y]);
    }

    private static void exch(int[] A, int x, int y) {
        int tmp = A[x];
        A[x] = A[y];
        A[y] = tmp;
    }

    /**
     * 9chap, it's good to set i to start from middle level
     * @param A
     */
    public static void heapify(int[] A) {
        for (int i = A.length/2; i >= 0; i--) { // start from the middle level and goes up to root
            heapifyHelper(A, i);
        }
    }

    public static void main(String[] args) {
        int[] input = new int[]{3, 2, 1, 5, 4};
        heapify(input);
        for (int i : input) {
            System.out.print(i + " ");
        }
    }
}
