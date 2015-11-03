package JiuChap2_UnionFind_Heap;

/**
 * http://www.lintcode.com/en/problem/heapify/
 * Created this class in JiuChap2_UnionFind_Heap at 11:17 PM, 11/2/2015.
 */
public class Array2Heap {
  public static void main(String[] args) {
    int[] A = new int[]{3,2,1,4,5};
    new Array2Heap().heapify(A);
    for (int i : A) {
      System.out.print(i + " ");
    }
  }

  /**
   * Perfect O(n) solution: Need to fully understand how O(n) is calculated!
   * @param A
   */
  public void heapify(int[] A) {
    for (int i = A.length/2; i >= 0; i--) {
      sink(A, i);
    }
  }

  /**
   * Sink A[i], here the heap is MinPQ
   *
   * @param A
   * @param i
   */
  private void sink(int[] A, int i) {
    while (2*i+1 < A.length) {
      int left = 2*i+1;
      int right = 2*i+2;
      int son = 0;
      if (right >= A.length || A[left] < A[right]) {
        son = left;
      }
      else {
        son = right;
      }
      if (A[i] > A[son]) {
        exch(A, i, son);
      }
      else {
        break;
      }
      i = son;
    }
  }

  private void exch(int[] A, int i, int j) {
    int temp = A[i];
    A[i] = A[j];
    A[j] = temp;
  }
}
