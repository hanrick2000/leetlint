package frequency_5;

import java.util.*;

/**
 * 学完了Ganker和N00t的2sum, 3sum之后, 然后看了N00t的3 sum closest. 加深了理解: 简化为2sum+binary search.
 * @author tzhang
 *
 */
public class ThreeSumClosest {
  /**
   * N00t的第一个方法: 3sum + binary search
   * @param num
   * @param target
   * @return
   */
  public int threeSumClosest(int[] num, int target) {
    if (num.length < 3)
      return 0;
    Arrays.sort(num);
    int res = num[0]+num[1]+num[2];
    for (int i = 0; i < num.length-2; ++i) {  //  不用加 && num[i] <= target
      if (i > 0 && num[i] == num[i-1])
        continue;  // remove duplicate caused by i
      for (int j = i+1; j < num.length-1; ++j) {
        if (j > i+1 && num[j] == num[j-1])
          continue;
        int l = j+1, r = num.length-1;
        while (l <= r) {
          int mid = l + (r-l)/2;
          int sum = num[i] + num[j] + num[mid];
          int diff = sum - target;
          if (Math.abs(diff) < Math.abs(res-target)) 
            res = sum;  // 更新res.
          if (diff == 0) 
            return res; // 直接就可以推出了.
          else if (diff < 0) {
            l = mid+1;
          }
          else {
            r = mid-1;
          }
        }
      }
    }
    return res;
  }
  
  /**
   * N00t的第二个方法. 比第一个还要好, 因为不需要binary search.
   * @param num
   * @param target
   * @return
   */
  public int threeSumClosest2(int[] num, int target) {
    if (num.length < 3)
      return 0;
    int res = num[0] + num[1] + num[2];
    for (int i = 0; i < num.length-2; ++i) {
      if (i > 0 && num[i] == num[i-1])  continue;
      int l = i+1, r = num.length-2;
      while (l <  r) {
        int sum = num[i] + num[l] + num[r];
        if (Math.abs(res-target) > Math.abs(sum-target)) {
          res = sum;
        }
        if (sum == target) {
          return sum;
        }
        else if (sum > target) {
          r--;
        }
        else {
          l++;
        }
      }
    }
    return res;
  }
  
  public static void main(String[] args) {
    int num[] = {-8, -5, 0, 3, 0, 1, 4, 4, 4};
    System.out.println(new ThreeSumClosest().threeSumClosest2(num,3));

  }
}
