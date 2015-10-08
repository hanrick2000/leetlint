package freq3_tony;
/**
 * input: sorted的数列. 注意是没有重复的数据. 这点和searchForRange不同.
 * input: 一个key
 * output: 如果找到就返回index. 否则返回应该插入的位置. 这个是个tricky的问题. 插到哪?
 * @author tzhang
 *
 */
public class SearchSortArrInsert {
  
  /**
   * http://bangbingsyb.blogspot.com/2014/11/leetcode-search-insert-position.html
   * 这个分析的比N00t的好. 这道题是考察对base case(即while最终的时刻)的分析. 
   * @param arr
   * @param target
   */
  public static int searchInsert(int[] arr, int target) {
    int lo = 0, hi = arr.length;
    while (lo <= hi) {
      int mid = lo + (hi-lo)/2;
      if (target < arr[mid])  hi = mid-1;
      else if (target > arr[mid])  lo = mid+1;
      else return mid;
    }
    return lo;
  }
  
  public static void main(String[] args) {
    int[] arr = new int[] {1, 3, 5, 7};
    System.out.println(searchInsert(arr, 2));
  }
}
