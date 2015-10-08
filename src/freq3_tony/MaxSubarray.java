package freq3_tony;

import java.util.Arrays;

/**
 * 杩欎篃鏄?涓鐩? 镓惧埌杩炵画镄剆ubarray, 浠栫殑sum鏄渶澶х殑. 杩斿洖杩欎釜链€澶х殑sum. 绗簩棰桦氨鏄姹傝繑锲炶繖涓渶澶um镄刟rray.
 * 
 * @author tzhang
 *
 */
public class MaxSubarray {
  /**
   * N00t镄勭亩鍗曡В娉?
   * 
   * @param arr
   * @return
   */
  private int maxSubarrN00t(int[] arr) {
    int maxSum = Integer.MIN_VALUE, sum = 0;
    for (int a : arr) {
      sum += a;
      maxSum = Math.max(sum, maxSum);
      if (sum < 0)
        sum = 0; // 杩欐槸涓€涓皬镣? 锲犱负鐩墠涓烘镄剆um涓鸿礋, 闾ｄ箞瀵逛簬鍚庨溃镄勫€兼潵璇存槸鍓綔鐢? 镓€浠ュ彲浠ラ吨澶村紑濮?
    }
    return maxSum;
  }

  /**
   * Max Subarray II镄勭亩鍗曡В娉?
   * 
   * @param A
   * @return
   */
  public int[] maxSubarrII(int[] A) {
    int max = Integer.MIN_VALUE, sum = 0;
    int start = 0, end = -1, curS = 0;

    for (int i = 0; i < A.length; ++i) {
      if (sum < 0) {
        sum = 0;
        curS = i; // reset the current start index
      }
      sum += A[i];
      if (sum > max) {
        max = sum;
        start = curS;
        end = i;
      }
    }

    if (start <= end)
      return Arrays.copyOfRange(A, start, end + 1);
    else
      return A; // the result subarray cannot be empty unless A is empty
  }

  /**
   * Ganker璁蹭简杩欓亾棰樼洰铡熸潵鏄吀鍨嬬殑DP闂. 浣跨敤镄勬柟娉曟槸: global/local链€浼樿В鍙?
   * 
   * @param arr
   * @return
   */
  private int maxSubarrDP(int[] arr) {
    if (arr == null || arr.length == 0)
      return 0;
    int global = arr[0]; // 鍒板綋鍓嶅厓绱犱负姝㈢殑鍏ㄥ眬链€浼樿В
    int local = arr[0]; // 鍖呭惈褰揿墠鍏幂礌镄勬渶浼樿В
    for (int i = 1; i < arr.length; ++i) {
      local = Math.max(arr[i], arr[i] + local); // 蹇呴』鍖呭惈褰揿墠, 镓€浠ユ渶浼桦彲鑳芥槸浠庤嚜宸卞紑濮?鍗虫殚鍚简涔嫔墠镄刲ocal涓鸿礋),
                                                // 鎴栬€呭姞涓娄箣鍓岖殑local.
      global = Math.max(global, local); // 鍏ㄥ眬链€浼樿В灏辨槸???
    }
    return global;
  }

  /**
   * 浜嫔疄涓? LC镄勮姹傛槸鐢―ivide Conquer瑙ｆ硶锅氩埌O(n). 镐庝箞锅氩憿?
   * 
   * @param arr
   * @return
   */
  private int maxSubarrDivCon(int[] arr) {
    int maxSum = Integer.MIN_VALUE;
    return findMaxSub(arr, 0, arr.length - 1, maxSum);
  }

  // recursive to find max sum
  // may appear on the left or right part, or across mid(from left to right)
  public int findMaxSub(int[] arr, int left, int right, int maxSum) {
    if (left > right)
      return Integer.MIN_VALUE;

    // get max sub sum from both left and right cases
    int mid = (left + right) / 2;
    int leftMax = findMaxSub(arr, left, mid - 1, maxSum);
    int rightMax = findMaxSub(arr, mid + 1, right, maxSum);
    maxSum = Math.max(maxSum, Math.max(leftMax, rightMax));

    // get max sum of this range (case: across mid)
    // so need to expend to both left and right using mid as center
    // mid -> left
    int sum = 0, midLeftMax = 0;
    for (int i = mid - 1; i >= left; i--) {
      sum += arr[i];
      if (sum > midLeftMax)
        midLeftMax = sum;
    }
    // mid -> right
    int midRightMax = 0;
    sum = 0;
    for (int i = mid + 1; i <= right; i++) {
      sum += arr[i];
      if (sum > midRightMax)
        midRightMax = sum;
    }

    // get the max value from the left, right and across mid
    maxSum = Math.max(maxSum, midLeftMax + midRightMax + arr[mid]);

    return maxSum;
  }

  public MaxSubarray() {
    int[] input = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
    System.out.println(maxSubarrDivCon(input));

    // int[] result = maxSubarrII(input);
    // for (int i : result)
    // System.out.print(i + " ");
  }

  public static void main(String[] args) {
    MaxSubarray ms = new MaxSubarray();
  }
}
