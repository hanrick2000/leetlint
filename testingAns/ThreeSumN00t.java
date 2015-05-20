package testingAns;

import java.util.*;

public class ThreeSumN00t {
  public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
    ArrayList<ArrayList<Integer>> resSet = new ArrayList<ArrayList<Integer>>();
    if (num.length < 3)
      return resSet;
    Arrays.sort(num);
    for (int i = 0; i < num.length - 2 && num[i] <= 0; ++i) { // Ϊʲônum[i]<=0?
      // remove duplicates
      if (i > 0 && num[i] == num[i - 1])
        continue;
      // use two pointer to find b+c = -a
      int start = i + 1, end = num.length - 1;
      while (start < end) {
        int sum = num[i] + num[start] + num[end];
        if (sum < 0) {
          start++;
        } else if (sum > 0) {
          end--;
        } else { // find one
          ArrayList<Integer> res = new ArrayList<Integer>(3);
          res.add(num[i]);
          res.add(num[start]);
          res.add(num[end]);
          resSet.add(res);
          // move the left pointer to right and skip duplicates;
          do {
            start++;
          } while (start < end && num[start] == num[start - 1]);
          // move the right pointer to left and skip duplicates
          do {
            end--;
          } while (start < end && num[end] == num[end + 1]);
        }
      }
    }
    return resSet;
  }
  
  public ThreeSumN00t() {
    int[] input = {-8, -5, 0, 3, 0, 1, 4, 4, 4};
    ArrayList<ArrayList<Integer>> res = threeSum(input);
    // ArrayList<ArrayList<Integer>> res = twoSum(input, input.length-1, 4);
    System.out.println(res);
  }

  public static void main(String[] args) {
    ThreeSumN00t tsg = new ThreeSumN00t();
  }
}
