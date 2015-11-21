package JiuChap7_FollowUpB;

import java.util.ArrayList;

/**
 * http://www.lintcode.com/en/problem/continuous-subarray-sum/
 * Created at 10:47 PM on 11/19/15.
 */
public class ContinuousSubarraySum {
  public static void main(String[] args) {

  }
  /**
   * @param A an integer array
   * @return  A list of integers includes the index of the first number and the index of the last number
   */
  public ArrayList<Integer> continuousSubarraySum(int[] A) {
    // Write your code here
    ArrayList<Integer> result = new ArrayList<>();
    if (A == null || A.length == 0) {
      return result;
    }
    int local = 0, global = Integer.MIN_VALUE;
    int start = 0;
    int idL = 0, idR = 0;
    for (int i = 0; i < A.length; ++i) {
      local += A[i];
      if (local > global) {
        global = local;
        idL = start;
        idR = i;
      }
      if (local <= 0) {
        start = i+1; // i;
        local = 0;
      }
    }

    result.add(idL);
    result.add(idR);

    return result;
  }
}
