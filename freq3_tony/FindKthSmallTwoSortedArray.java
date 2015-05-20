package freq3_tony;

import java.util.Arrays;

/**
 * Stanford算法课里面的QuickSort讲过使用median的好处??? 这里是因为merge Sort才引出的... 这里的要求是O(log(m+n))的解法. 看了1337,
 * 原来问题来自于MIT的作业:
 * http://www2.myoops.org/course_material/mit/NR/rdonlyres/Electrical-Engineering-and-
 * Computer-Science/6-046JFall-2005/30C68118-E436-4FE3-8C79-6BAFBB07D935/0/ps9sol.pdf
 * 
 * @author tzhang
 *
 */


public class FindKthSmallTwoSortedArray {
  /**
   * 使用1337的解法. 具体的关键处理解可以参考Algs&me的blog:
   * http://www.algorithmsandme.com/2014/12/fins-kth-smallest-element-in-two-sorted.html
   * 但是这个直接从1337port过来用Java写的话很不好, 因为必须要copy整个array. 不像C++那样可以用指针!
   * 
   * @param A
   * @param m
   * @param B
   * @param n
   * @param k
   * @return
   */
  public int findKthSmallestDUMMY(int[] A, int m, int[] B, int n, int k) {
    int i = (int) ((double) m / (m + n) * (k - 1));
    int j = (k - 1) - i;

    int Ai_1 = ((i == 0)) ? Integer.MIN_VALUE : A[i - 1];
    int Bj_1 = ((j == 0)) ? Integer.MIN_VALUE : B[j - 1];
    int Ai = ((i == m)) ? Integer.MAX_VALUE : A[i];
    int Bj = ((j == n)) ? Integer.MAX_VALUE : B[j];

    if (Bj_1 < Ai && Ai < Bj)
      return Ai;
    else if (Ai_1 < Bj && Bj < Ai)
      return Bj;

    //
    int[] Anext = Arrays.copyOfRange(A, i + 1, A.length);
    int[] Bnext = Arrays.copyOfRange(B, 0, j);

    int[] Anext2 = Arrays.copyOfRange(A, 0, i);
    int[] Bnext2 = Arrays.copyOfRange(B, j + 1, B.length);
    if (Ai < Bj)
      return findKthSmallestDUMMY(Anext, Anext.length, Bnext, Bnext.length, k - i
          - 1);
    else
      // if (Bj < Ai)  // 为什么这样写会报错: no return value?
      return findKthSmallestDUMMY(Anext2, Anext2.length, Bnext2, Bnext2.length, k
          - j - 1);
  }

  //http://stackoverflow.com/questions/22004501/find-kthsmallest-element-from-two-sorted-arrays
  public int findKthSmall(int ... a){
    return 0;
  }
  public FindKthSmallTwoSortedArray() {
    int a[] = new int[] {10, 30, 40, 50, 60};
    int b[] = new int[] {30, 50, 100, 110, 200};

    System.out.printf("\n Kth smallest element is "
        + findKthSmallestDUMMY(a, a.length, b, b.length, 4));
  }
  
  public static void main(String[] args) {
    FindKthSmallTwoSortedArray fkta = new FindKthSmallTwoSortedArray();
  }
}
