package freq3_tony;

/**
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the
 * following properties: 也就是面试不会直接让你写binary search. 总会有点变形. 这里就是一个变形题. 其实就是2次Binary search. 虽然意思很简单.
 * 其实写的时候就像Qsort. 很容易出错. 还是得熟练. 而且和之前的(哪一???)题很像, 要深入理解left, right在while跳出来之后的关系.
 * 
 * @author tzhang
 *
 */
public class SearchMatrix {
  /**
   * 这里觉得code ganker的解答比N00t的更易懂些.
   * 
   * @param mat
   * @param target
   * @return
   */
  private static boolean searchMatrixCG(int[][] mat, int target) {
    if (mat == null || mat.length == 0 || mat[0].length == 0)
      return false;
    int l = 0, r = mat.length - 1;
    while (l <= r) {
      int mid = l + (r - l) / 2;
      if (target == mat[mid][0])
        return true;
      if (target < mat[mid][0])
        r = mid - 1;
      else
        l = mid + 1;
    }
    int row = r;
    if (row < 0)
      return false;
    l = 0;
    r = mat[0].length - 1;
    while (l <= r) {
      int mid = l + (r - l) / 2;
      if (target == mat[row][mid])
        return true;
      if (target < mat[row][mid])
        r = mid - 1;
      else
        l = mid + 1;
    }
    return false;
  }

  /**
   * N00t的第二解法其实最容易想到. 因为这个Mat就是把sorted array分了段而已.
   * @param mat
   * @param target
   * @return
   */
  private static boolean searchMatN00t(int[][] mat, int target) {
    int col = mat[0].length, row = mat.length;
    int l = 0, r = row * col -1;
    while (l <= r) {
      int mid = l + (r-l)/2;
      if (target == mat[mid/col][mid%col])  return true;
      if (target < mat[mid/col][mid%col])  r = mid-1;
      else  l = mid+1;
    }
    return false;
  }
  public static void main(String[] args) {
    int[][] M = new int[][] { {1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 50}};
    boolean result ;
//    result = searchMatrixCG(M, 12);
    result = searchMatN00t(M, 12);
    System.out.println(result);
  }
}
