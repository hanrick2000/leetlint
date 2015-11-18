package Easy;

/**
 * http://www.lintcode.com/en/problem/total-occurrence-of-target/ Created at 9:35 AM on 11/17/15.
 */
public class Occurrence {
  public static void main(String[] args) {
    Occurrence oc = new Occurrence();
    int[] A = new int[]{3, 7, 9, 9, 9, 10, 21, 21};
    int ans = oc.totalOccurence(A, 3);
    System.out.println(ans);
  }
  /**
   * @param A an integer array sorted in ascending order
   * @param target an integer
   * @return an integer
   */
  public int totalOccurence(int[] A, int target) {
    int l = 0, h = A.length - 1;
    while (l + 1 < h) {
      int mid = (l + h) / 2;
      if (A[mid] == target) {
        h = mid;
      } else if (A[mid] < target) {
        l = mid;
      } else {
        h = mid;
      }
    }
    int start = -1;
    if (A[l] == target) {
      start = l;
    } else if (A[h] == target) {
      start = h;
    }

    l = 0;
    h = A.length - 1;
    while (l + 1 < h) {
      int mid = (l + h) / 2;
      if (A[mid] <= target) {
        l = mid;
      } else {
        h = mid;
      }
    }
    int end = -1;
    if (A[h] == target) {
      end = h;
    } else if (A[l] == target) {
      end = l;
    }
    if (start + end < 0)  return 0;
    return end - start + 1;
  }
}
