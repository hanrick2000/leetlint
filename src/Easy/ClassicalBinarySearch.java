package Easy;

/**
 * http://www.lintcode.com/en/problem/classical-binary-search/
 * Created at 9:17 PM on 11/13/15.
 */
public class ClassicalBinarySearch {
  public static void main(String[] args) {
    ClassicalBinarySearch cbs = new ClassicalBinarySearch();
    int[] A = new int[]{1,2,2,4,5,5};
    int t = 2;
    System.out.println(cbs.find(A, t));
  }

  public int find(int[] A, int t) {
    int n = A.length;
    int i = 0, j = n-1;
    while (i + 1 < j) {
      int mid = i + (j-i)/2;
      if (A[mid] == t) {
        //j = mid;
        i = mid;
      }
      else if (A[mid] < t) {
        i = mid;
      }
      else if (A[mid] > t) {
        j = mid;
      }
    }
    if (A[j] == t) {
      return j;
    }
    if (A[i] == t) {
      return i;
    }
    return -1;
  }
}
