package JiuChap7_FollowUpB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * http://www.lintcode.com/en/problem/continuous-subarray-sum-ii/
 * Created at 11:59 PM on 11/19/15.
 */
public class ContinuousSubarraySumII {
  public static void main(String[] args) {
    // {-101, -2, -4}; //{-101,-33,-44,-55,-67,-78,-101,-33,-44,-55,-67,-78,-100,-200,-1000,-2,-100,-200,-1000,-22}; //
    int[] A = new int[]{-5,10,5,-3,1,1,1,-2,3,-4}; //{2,-1,-2,-3,-100,1,2,3,100}; //{0}; //{3,1,-100,-3,4};
    ContinuousSubarraySumII css2 = new ContinuousSubarraySumII();
    ArrayList<Integer> ans = css2.continuousSubarraySumIIFAILED(A);//continuousSubarraySumII(A);
    System.out.println(ans);
  }

  /**
   * 9chap's solution, I understand his lecture, but can't think out this code
   * @param A
   * @return
   */
  public ArrayList<Integer> continuousSubarraySumII(int[] A) {
    int local = 0, global = Integer.MIN_VALUE; //0;
    int start = 0, end = 0;
    int total = 0;
    ArrayList<Integer> result = new ArrayList<>(2);
    result.add(0);
    result.add(1);
    for (int i = 0; i < A.length; ++i)  {
      total += A[i];
      // find localMax > 0
      if (local < 0) {
        local = A[i];
        start = end = i;
      }
      else {
        local += A[i];
        end = i;
      }
      if (local > global) {
        global = local;
        result.set(0, start);
        result.set(1, end);
      }
    }
    start = 0;
    end = 0;
    local = 0;
    //global = 0;
    for (int i = 0; i < A.length; ++i) {
      // find localMin < 0
      if (local > 0) {
        local = A[i];
        start = end = i;
      }
      else {
        local += A[i];
        end = i;
      }
      if (start == 0 && end == A.length-1)  continue;
      if (total - local > global) {
        global = total - local;
        result.set(0, (end+1) % A.length);
        result.set(1, (start-1+A.length) % A.length );
      }
    }
    return result;
  }
  /**
   * Directed extended from continuous subarray sum I
   * Failed at: {-101, -2, -4}
   * @param A an integer array
   * @return  A list of integers includes the index of the first number and the index of the last number
   */
  public ArrayList<Integer> continuousSubarraySumIIFAILED(int[] A) {
    // Write your code here
    ArrayList<Integer> result = new ArrayList<>();
    int len = A.length;
    //int min, max;
    int globalMin = 0; //Integer.MAX_VALUE;
    int globalMax = Integer.MIN_VALUE;
    int minL = 0, minR = 0;
    int maxL = 0, maxR = 0;
    int sum = 0;
    int start = 0;
    int begin = 0;
    Map<Integer, Integer> map = new HashMap<>();
    int localMax = 0;
    int localMin = 0;
    for (int i = 0; i < len; ++i) {
      localMax += A[i];
      localMin += A[i];
      sum += A[i];
      if (localMax > globalMax) {
        maxL = start;
        maxR = i;
        globalMax = localMax;
      }
      else {
      //else if (localMax <= 0) {
        localMax = 0;
        start = i+1;
      }

      if (localMin < globalMin) {
        minL = begin;
        minR = i;
        globalMin = localMin;
      }
      else {
      //else if (localMin >= 0) {
        localMin = 0;
        begin = i+1;
      }
    }

    int sumB = globalMax;
    int sumAC = sum - globalMin;
    if (sumB > sumAC) {
      result.add(maxL%len);
      result.add(maxR%len);
    }
    else {
      result.add((minR+1)%len);
      result.add((minL-1)%len);
    }

    return result;
  }
}
