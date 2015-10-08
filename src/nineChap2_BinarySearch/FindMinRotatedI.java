package nineChap2_BinarySearch;

/**
 * Find minimum in rotated sorted array
 * 
 * @author tzhang
 *
 */
public class FindMinRotatedI {
  public static void main(String[] args) {
    int[] rotSort = new int[] {4, 5, 6, 7, 0, 1, 2};
    int ans = findMin(rotSort);
    System.out.println(ans);
  }
  
  public static int findMin(int[] arr) {
    if (arr == null || arr.length == 0) {
      return -1;
    }
    
    int start = 0, end = arr.length-1;
    int mid;
    
    while (start + 1 < end) {
      mid = start + (end-start)/2;
      if (arr[mid] > arr[end]) {
        start = mid;
      }
      else if (arr[mid] < arr[end] ) {
        end = mid;
      }
    }
    return Math.min(arr[start], arr[end]);
  }
}
