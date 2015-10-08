package testingAns;

import java.util.*;

/**
 * 这个package用于测试网上的答案, 用来一步步看, 加深理解. 这里的Threesum是Ganker的解答. 很赞.
 * 
 * @author tzhang
 *
 */
public class ThreeSumGanker {
  public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
    if (num == null || num.length <= 2)
      return res;
    Arrays.sort(num);
    for (int i = num.length - 1; i >= 2; i--) {
      if (i < num.length - 1 && num[i] == num[i + 1])
        continue; // 当然是if, 因为continue是跳过最内层的loop. 但是i并不变, 所以死循环.
      ArrayList<ArrayList<Integer>> curRes = twoSum(num, i - 1, -num[i]);
      for (int j = 0; j < curRes.size(); j++) {
        curRes.get(j).add(num[i]);
      }
      res.addAll(curRes);
    }
    return res;
  }

  /**
   * 为什要用end? 因为3sum里面的target从后往前扫, 所以只要搜索target-1->0之间2sum的和就ok了.
   * 
   * @param num
   * @param end
   * @param target
   * @return
   */
  private ArrayList<ArrayList<Integer>> twoSum(int[] num, int end, int target) {
    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
    // Arrays.sort(num);
    if (num == null || num.length <= 1)
      return res;
    int l = 0;
    int r = end;
    while (l < r) {
      if (num[l] + num[r] == target) {
        ArrayList<Integer> item = new ArrayList<Integer>();
        item.add(num[l]);
        item.add(num[r]);
        res.add(item);
        l++;
        r--;
        while (l < r && num[l] == num[l - 1])
          l++;
        while (l < r && num[r] == num[r + 1])
          r--;
      } else if (num[l] + num[r] > target) {
        r--;
      } else {
        l++;
      }
    }
    return res;
  }

  /**
   * N00t的3 sum closest: 3sum + binary search
   * @param num
   * @param target
   * @return
   */
  public int threeSumClosest(int[] num, int target) {
    if (num.length < 3)
      return 0;
    Arrays.sort(num);
    int res = num[0] + num[1] + num[2];
    for (int i = 0; i < num.length - 2; ++i) {
      // skip duplicates
      if (i > 0 && num[i] == num[i - 1])
        continue;
      for (int j = i + 1; j < num.length - 1; ++j) {
        // skip duplicates
        if (j > i + 1 && num[j] == num[j - 1])
          continue;
        // binary search for the third element
        int start = j + 1, end = num.length - 1;
        while (start <= end) {
          int mid = (start + end) / 2;
          int sum = num[i] + num[j] + num[mid];
          int diff = sum - target;
          if (Math.abs(diff) < Math.abs(res - target)) {
            res = sum;
          }
          if (diff == 0) { // find the target
            return res;
          } else if (diff < 0) {
            start = mid + 1;
          } else {
            end = mid - 1;
          }
        }
      }
    }
    return res;
  }

  public ThreeSumGanker() {
    int[] input = {-8, -5, 0, 3, 0, 1, 4, 4, 4};
    ArrayList<ArrayList<Integer>> res = threeSum(input);
    // ArrayList<ArrayList<Integer>> res = twoSum(input, input.length-1, 4);
    System.out.println(res);
  }

  public static void main(String[] args) {
    ThreeSumGanker tsg = new ThreeSumGanker();
  }
}
