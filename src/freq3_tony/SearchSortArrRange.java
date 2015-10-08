package freq3_tony;

/**
 * N00t的漂亮做法. 
 * @author tzhang
 *
 */
public class SearchSortArrRange {
  /**
   * 用2个binary search搜索左右边界. 虽然意思很简单, 但是N00t的代码写的真好
   * @param arr
   * @param target
   * @return
   */
  public static int[] searchRange(int[] arr, int target) {
    int[] res = new int[] {-1, 1};
    int lo = 0, hi = arr.length;
    while (lo <= hi) {
      int mid = lo + (hi-lo)/2;
      if (target == arr[mid]) {
        // 找到左边界, 现在开始找右边
        if (mid == 0 || arr[mid-1] < target) {
          res[0] = mid;
          // reset
          lo = mid;
          hi = arr.length-1;
        }
        // 找到右边界, 开始找左边
        if (mid == arr.length-1 || arr[mid+1] > target) {
          res[1] = mid;
          lo = 0;
          hi = mid;
        }
        if (res[0] > -1 && res[1] > 1)  return res;
        if (res[0] < 0)  hi = mid-1;
        else if (res[1] < 0)  lo = mid+1;
        else {
          System.out.println("in the middle of searching");
        }
      }
      else if (arr[mid] < target)  
        lo = mid+1;
      else if (arr[mid] > target) 
        hi = mid-1;
    }
    return res;
  }
  
  public static void main(String[] args) {
    int[] A = new int[] {5, 7, 7, 8, 8, 10};
    int[] range = searchRange(A, 8);
    System.out.println("Got: " + range[0] + ", " + range[1]);
  }
}
