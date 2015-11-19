package nineChap0_ArrNum;

/**
 * http://www.cnblogs.com/AnnieKim/archive/2013/04/21/3034631.html
 * http://www.lintcode.com/en/problem/first-missing-positive/
 * Given [1,2,0] return 3, and [3,4,-1,1] return 2.
 * Created by 4:56 PM on 10/14/2015.
 */
public class FirstMissPos {
  public static void main(String[] args) {
    int[] A = new int[]{-1,4,2,1,9,10}; //{2,1}; //{3,4,-1,1};  // {3,4,3,1}
    FirstMissPos fmp = new FirstMissPos();
    int ans = fmp.firstMissingPositive(A);
    System.out.println(ans);
  }
  /**
   * http://jane4532.blogspot.com/2013/06/find-first-missing-positive-integer.html
   * http://bangbingsyb.blogspot.com/2014/11/leetcode-first-missing-positive.html
   * @param A: an array of integers
   * @return: an integer
   */
  public int firstMissingPositive(int[] A) {
    // write your code here
    if (A == null || A.length == 0) {
      return 0;
    }
    for (int i = 0; i < A.length; ) {
      if (A[i] > 0 && A[i] < A.length && A[i] != i+1 && A[i] != A[A[i] - 1]) {
        swap(A, i, A[i]-1);
      }
      else {
        ++i; // !!! important
      }
    }

    for (int i = 0; i < A.length; ++i) {
      if (A[i] != i+1)  return i+1;
    }
    return A.length + 1;
  }

  public int firstMissingPositiveLint(int[] A) {
    // write your code here
    if (A == null || A.length == 0) {
      return 1;
    }

    for (int i = 0; i < A.length; ++i) {
      if (A[i] > 0 && A[i] < A.length && A[i] != i+1 && A[i] != A[A[i]-1]) {
        //int tmp = A[i];
        //A[i] = A[A[i]-1];  // hehehe
        //A[A[i]-1] = tmp;
        int ind1 = A[i]-1;
        int tmp = A[i];
        A[i] = A[ind1];
        A[ind1] = tmp;
      }
    }
    for (int i = 0; i < A.length; ++i) {
      if (A[i] != i+1) {
        return i+1;
      }
    }
    return A.length+1;
  }

  private void swap(int[] A, int i, int j) {
    int tmp = A[i];
    A[i] = A[j];
    A[j] = tmp;
  }
}
