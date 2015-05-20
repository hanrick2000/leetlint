package freq4_tony;

/*
 * Given a sorted array, remove the duplicates in place such that each element appear only once and
 * return the new length. Do not allocate extra space for another array, you must do this in place
 * with constant memory.
 */

public class RemoveDuplicatesfromSortedArray {

  /**
   * @func: 跟remove element解法一样.因为sorted, 所以相同的数都练起来的. 这样真正的结果只记录所有数字一次.所以2个pointer. 一个 j
   *        pointer一直加,代表着原来的数组. 另一个i pointer只在不等的时候才加.这样相同的数的话的pointer一样. 然后在不同的时候把A[i]=A[j].
   * @param A
   * @return
   */

  /**
   * A[j] = 1 2 2 2 3 4 4 5 6 j = 0 1 2 3 4 5 6 7 8 i = 0 1 1 1 2 3 3 4 5 output is: A[i] = 1 2 3 4
   * 5 6
   */


  /**
   * @func: 也可以用一个cnt来记录重复的数. 然后隔开重复的数. 这个相对2个pointer更简单.
   * @param A
   * @return
   */
  public int removeDuplicates(int[] A) {
    int cnt = 0;
    for (int i = 1; i < A.length; i++) {
      if (A[i] == A[i - 1]) {
        ++cnt;
      } else if (cnt > 0) {
        A[i - cnt] = A[i];
      }
    }
    for (int i = 0; i<A.length - cnt; i++){
      System.out.print(A[i] + " ");
    }
    return A.length - cnt;
  }

  /**
   * 通用的方法:A[i] == A[i-cnt-1]
   * A[i] 是原来的数组, A[i-cnt-1] 表示结果的数组. 这里检查结果数组的前一个是不是重复的.
   * 说明可以cnt++. 
   * @param A
   * @return
   */
  public int removeDuplicates2(int[] A) {
    int cnt = 0;
    for (int i = 1; i<A.length; i++){
//      if (A[i] == A[i-cnt-1] && A[i] == A[i-cnt-2] && A[i] == A[i-cnt-3])
      if (A[i] == A[i-cnt-1])
        ++cnt;
      else {
        A[i-cnt]= A[i];
      }
    }
    for (int i = 0; i<A.length - cnt; i++){
      System.out.print(A[i] + " ");
    }
    return A.length - cnt;
  }
  
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    int[] A = {1, 1, 2, 3, 3, 3, 3, 4, 5};
    for (int i : A) {
      System.out.print(i + " ");
    }
    System.out.println("");
    int l = (new RemoveDuplicatesfromSortedArray()).removeDuplicates2(A);
    System.out.println();
    System.out.print(l);
  }

}
