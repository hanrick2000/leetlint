package nineChap0_ArrNum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * http://www.lintcode.com/en/problem/subarray-sum-closest/ Created by 7:24 PM on 10/20/2015.
 */
public class SubSumClose {
  public static void main(String[] args) {
    int[] test = new int[] {-3, 1, 1, -3, 5};
    ArrayList<Integer> result = new ArrayList<>();
    result = new SubSumClose().subarraySumClosestLint(test);
    System.out.println(result);
  }

  /*-
   * I like this problem, a small problem with sub-class. Using hashTable or a simple array for Pair obj, I use the
   * latter one, array, easy to sort.
   *
   * 9chap's idea, still using prefix-sum array. And knowing the diff of prefix-sum array is the sum of arr.
   * And notice the len of sum-array is: len+1. We always append 0 at sum[0] which is also helpful in here.
   *
   * eg: sum[4] - sum[2] = SUM{arr[2], ... ,arr[4-1]}.
   *      sum[0] - sum[1] = SUM{arr[0] + ... + arr[1-1]} => SUM{arr[0]}. or by index: {0,0}. which is one element subarray.
   * @param nums
   * @return
   */
  public ArrayList<Integer> subarraySumClosest(int[] nums) {
    // write your code here
    if (nums == null || nums.length < 1) {
      return null;
    }

    ArrayList<Integer> result = new ArrayList<>();
    int len = nums.length;
    int[] sumL = new int[len + 1];
    sumL[0] = 0; // sumL arr is the pre-sum array, length = len+1;
    List<Pair> sumLlist = new ArrayList<>(len + 1);
    sumLlist.add(new Pair(0, 0));
    for (int i = 1; i < len + 1;
        ++i) {  // put sumL into sumLlist as a pair object(sumL.sum, sumL.idx)
      sumL[i] = sumL[i - 1] + nums[i - 1];
      sumLlist.add(new Pair(sumL[i], i));
    }

    Pair[] sumLarr =
        sumLlist.stream().toArray(Pair[]::new); // turn sumLlist into array, size of len+1.
    Arrays.sort(sumLarr);
    int minSumDiff = Integer.MAX_VALUE;
    for (int i = 0; i < len; ++i) {  // sumLarr's length = len+1;
      int diff = sumLarr[i + 1].sum - sumLarr[i].sum;
      if (diff <= minSumDiff) {
        if (diff < minSumDiff) {
          minSumDiff = sumLarr[i + 1].sum - sumLarr[i].sum;
          result = new ArrayList<>();
        }
        int[] temp = new int[] {sumLarr[i + 1].idx, sumLarr[i].idx};
        Arrays.sort(temp);
        result.add(temp[0]);
        result.add(temp[1] - 1);
      } else {
        // do nothing
      }
    }
    return result;
  }

  public ArrayList<Integer> subarraySumClosestLint(int[] nums) {
    // write your code here
    if (nums == null || nums.length < 1) {
      return null;
    }
    int leng = nums.length;
    ArrayList<Integer> result = new ArrayList<>();

    if (leng == 1) {
      result.add(0);
      result.add(0);
      return result;
    }

    int[] sumPre = new int[leng + 1];
    Pair[] sumPair = new Pair[leng + 1];
    sumPair[0] = new Pair(0, 0);
    for (int i = 0; i < leng; ++i) {
      sumPre[i + 1] = sumPre[i] + nums[i];
      sumPair[i + 1] = new Pair(sumPre[i + 1], i + 1);
    }
    Arrays.sort(sumPair);

    int minDiff = Integer.MAX_VALUE;
    for (int i = 1; i < leng + 1; ++i) {
      int diff = sumPair[i].sum - sumPair[i - 1].sum;
      if (diff <= minDiff) {
        if (diff < minDiff) {
          result = new ArrayList<>();
          minDiff = diff;
        }
        int[] temp = new int[] {sumPair[i].idx, sumPair[i - 1].idx};
        Arrays.sort(temp);
        result.add(temp[0]);
        result.add(temp[1] - 1);
      }
    }

    return result;
  }

  private class Pair implements Comparable<Pair> {
    int sum;
    int idx;

    Pair(int v, int i) {
      sum = v;
      idx = i;
    }

    @Override
    public int compareTo(Pair other) {
      return this.sum - other.sum;  // increasing order. If max-heap, reverse it.
    }
  }
}
