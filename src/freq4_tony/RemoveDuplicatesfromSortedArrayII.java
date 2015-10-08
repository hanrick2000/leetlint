package freq4_tony;

/**
 * Follow up for "Remove Duplicates": What if duplicates are allowed at most twice?
 * http://n00tc0d3r.blogspot.com/2013/05/remove-element-from-arraylist.html
 * @author tzhang
 * 
 */
public class RemoveDuplicatesfromSortedArrayII {

  public int removeDuplicates(int[] A) {
    int cnt = 0;
    for (int i = 2; i < A.length; i++) {
      // if (A[i] == A[i - 1] && A[i] == A[i - 2])     --- trap
      /*
       * In the above problem, we only need to test the one right before the current 
       * one which is not possible to have been overwritten. But now, we also need to 
       * test the one that is two nodes away which is possible to have been overwritten.
       *  That said, we should test the two nodes that a
       * re supposed to be ahead of the current node in the new array.
       * 
       */
      if (A[i] == A[i - cnt - 1] && A[i] == A[i - cnt - 2])  // 关键点
        ++cnt;
      else {
        A[i - cnt] = A[i];
      }
    }
    for (int i = 0; i < A.length - cnt; i++) {
      System.out.print(A[i] + " ");
    }
    return A.length - cnt;
  }

  public static void main(String[] args) {
    int[] A = {1, 1, 2, 3, 3, 3, 3, 4, 5};
    for (int i : A) {
      System.out.print(i + " ");
    }
    System.out.println("");
    int l = (new RemoveDuplicatesfromSortedArrayII()).removeDuplicates(A);
    System.out.println();
    System.out.print(l);
  }
}
