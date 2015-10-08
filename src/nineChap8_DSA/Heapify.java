package nineChap8_DSA;

public class Heapify {
  public static void main(String[] args) {
    int[] input = new int[] {3, 2, 1, 5, 4};
    MaxPQ<Integer> heap = new MaxPQ<Integer>(input.length);
    for (int i : input) {
      heap.insert(i);
    }
    Object[] output = heap.getPQ();
    for (int i = 0; i < output.length; ++i) {
      // System.out.print(output[i] + " ");
      System.out.print(heap.delMax() + " ");
    }
  }
}
