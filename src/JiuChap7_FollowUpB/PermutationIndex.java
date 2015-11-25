package JiuChap7_FollowUpB;

/**
 * Created at 11:46 AM on 11/24/15.
 */
public class PermutationIndex {
  public static void main(String[] args) {
    int[] A = new int[]{2,4,1}; // {1,2,4};
    PermutationIndex pi = new PermutationIndex();
    long ans = pi.permutationIndex(A);
    System.out.println(ans);
  }
  /**
   * http://algorithm.yuanbin.me/zh-cn/exhaustive_search/permutation_index.html
   * @param A an integer array
   * @return a long integer
   */
  public long permutationIndex(int[] A) {
    if (A == null || A.length == 0) {
      return 0L;
    }
    long index = 1, fact = 1;
    for (int i = A.length - 1; i >= 0; i--) {
      // get rank in every iteration
      int rank = 0;
      for (int j = i + 1; j < A.length; j++) {
        if (A[i] > A[j]) rank++;
      }

      index += rank * fact;
      fact *= (A.length - i);
    }

    return index;
  }

  public long permutationIndexTTT(int[] A) {
    if (A == null || A.length == 0) {
      return 0L;
    }
    int sum = 1;
    for (int i = 0; i < A.length; ++i) {
      int rank = 0;
      for (int j = i+1; j < A.length; ++j) {
        if (A[i] > A[j])  rank++;
      }
      sum += rank * factorial(A.length-1-i);
    }
    return sum;
  }

  /**
   * Get 1 * 2 * ... * leng => leng!
   * @param leng
   * @return
   */
  private int factorial(int leng) {
    int fact = 1;
    for (int i = 1; i <= leng; ++i) {
      fact *= i;
    }
    return fact;
  }

  public long permutationIndexWRONG(int[] A) {
    // Write your code here
    if (A == null || A.length == 0) {
      return 0;
    }

    int cnt = 0;
    for (int i = 0 ; i < A.length; ++i) {
      int fac = 1;
      for (int j = 1; j < A.length-i; j++) {
        fac *= j;
      }
      cnt += rank(A, i) * fac;
    }
    return cnt;
  }

  private int rank(int[] A, int loc) {
    int rank = 0;
    for (int i = 0; i < A.length; ++i) {
      if (A[i] < A[loc]) {
        rank++;
      }
    }
    return rank;
  }
}
