package JiuChap4_TwoPointers;

/**
 * Created this class in JiuChap4_TwoPointers at 8:44 PM, 11/9/2015.
 */
public class MinSizeSubarraySum {
  public static void main(String[] args) {
    int[] nums = new int[]{2,3,1,2,4,3};
    int k = 7;
    MinSizeSubarraySum mss = new MinSizeSubarraySum();
    int ans = mss.minimumSize(nums, k);
    System.out.println(ans);
  }

  public int minimumSize(int[] nums, int s) {
    if (nums == null || nums.length == 0) {
      return -1;
    }
    int sum = 0;
    int leng = nums.length;
    int j = 0;
    int ans = Integer.MAX_VALUE;
    for (int i = 0; i < leng; ++i) {
      while (j <  leng) {
        if (sum + nums[j] < s) {
          sum += nums[j];
          j++;
        }
        else {
          ans = Math.min(ans, j-i+1);
          break;
        }
      }
      sum -= nums[i];
    }
    if (ans == Integer.MAX_VALUE) {
      return -1;
    }
    return ans;
  }

  public int minimumSizeWRONG(int[] nums, int s) {
    int size = 0;
    if (nums == null || nums.length == 0) {
      return size;
    }
    int sum = 0;
    int i = 0, j = 0;
    int leng = nums.length;
    while (i < leng) {
      while (j < leng) {
        if (sum + nums[j] < s) {
          size++;
          sum += nums[j];
          j++;
        }
        else {
          break;
        }
      }
      sum -= nums[i];
      size--;
      i++;
    }

    return size;
  }
}
