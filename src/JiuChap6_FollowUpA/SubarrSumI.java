package JiuChap6_FollowUpA;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * http://www.lintcode.com/en/problem/subarray-sum/
 * Created at 9:41 AM on 11/18/15.
 */
public class SubarrSumI {
  public static void main(String[] args) {
    SubarrSumI ssi = new SubarrSumI();
    int[] nums = new int[]{-3,1,2,-3,4,-6}; //{-3,1,2,-3,1,2,-3,4,1,2,-3};
    ArrayList<Integer> result = ssi.subarraySum(nums);
    System.out.println(result);
    //printSubarrays(nums, 0);
  }

  public ArrayList<Integer> subarraySum(int[] nums) {
    int len = nums.length;
    ArrayList<Integer> ans = new ArrayList<>();
    HashMap<Integer, Integer> map = new HashMap<>();

    final int SUM = -2;
    map.put(0, -1);
    int preSum = 0;
    for (int i = 0; i < len; ++i) {
      preSum += nums[i];
      if (map.containsKey(preSum - SUM)) {
        ans.add(map.get(preSum - SUM) + 1);
        ans.add(i);
      }
      map.put(preSum, i);
    }

    return ans;
  }
  /**
   * Complicated solution due to bad index design.
   * still not catching all solutions, this becomes a combination problem???
   *      NO: check the stackOverflow solution: not combination, but a well defined looping--> fixed end
   * @param nums: A list of integers
   * @return: A list of integers includes the index of the first number
   *          and the index of the last number
   */
  public ArrayList<Integer> subarraySumBAD(int[] nums) {
    // write your code here
    int[] preSum = new int[nums.length+1];
    Map<Integer, List<Integer>> map = new HashMap<>();
    List<Integer> tmp = new ArrayList<>();
    tmp.add(0);
    map.put(0, tmp);
    for (int i = 1; i <= nums.length; ++i) {
      preSum[i] = preSum[i-1] + nums[i-1];
      if (map.containsKey(preSum[i])) {
        map.get(preSum[i]).add(i);
      }
      else {
        List<Integer> list = new ArrayList<>();
        list.add(i);
        map.put(preSum[i], list);
      }
    }
    ArrayList<Integer> result = new ArrayList<>();
    for (Integer presum : map.keySet()) {
      if (map.get(presum).size() > 1) {
        List<Integer> index = map.get(presum);
        Collections.sort(index);
        result.add(index.get(0));
        result.add(index.get(1)-1);
      }
    }
    return result;
  }

  /**
   * http://stackoverflow.com/questions/14948258/given-an-input-array-find-all-subarrays-with-given-sum-k
   * @param input
   * @param k
   */
  private static void printSubarrays(int[] input, int k) {
    Map<Integer, List<Integer>> map = new HashMap<>();
    List<Integer> initial = new ArrayList<>();
    initial.add(-1);
    map.put(0, initial);
    int preSum = 0;

    // Loop across all elements of the array
    for(int i=0; i< input.length; i++) {
      preSum += input[i];
      // If point where sum = (preSum - k) is present, it means that between that
      // point and this, the sum has to equal k
      if(map.containsKey(preSum - k)) {   // Subarray found
        List<Integer> startIndices = map.get(preSum - k);
        for(int start : startIndices) {
          System.out.println("Start: "+ (start+1)+ "\tEnd: "+ i);
        }
      }

      List<Integer> newStart = new ArrayList<>();
      if(map.containsKey(preSum)) {
        newStart = map.get(preSum);
      }
      newStart.add(i);
      map.put(preSum, newStart);
    }
  }
}
