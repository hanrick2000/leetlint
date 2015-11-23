package nineChap2_BinarySearch;

/**
 * classical algorithm improvement, based on sea
 * Created at 2:19 PM on 11/21/15.
 */
public class Search2DMatrixII {
  public static void main(String[] args) {
    int[][] M = new int[][] {
        {1, 3, 5, 7},
        {2, 4, 7, 8},
        {3, 5, 9, 10}
    };
    Search2DMatrixII s2mII = new Search2DMatrixII();
    int ans = s2mII.searchMatrix(M,5);
    System.out.println(ans);
  }
  /**
   * @param matrix: A list of lists of integers
   * @param: A number you want to search in the matrix
   * @return: An integer indicate the occurrence of target in the given matrix
   */
  public int searchMatrix(int[][] matrix, int target) {
    // write your code here
    int occur = 0;
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
      return occur;
    }
    int r = matrix.length-1, c = 0;
    while (true) {
      if (target < matrix[r][c]) {
        if (r - 1 >= 0) {
          r--;
          continue;
        }
        else {
          break;
        }
      }
      else if (target > matrix[r][c]) {
        if (c+1 < matrix[0].length) {
          c++;
          continue;
        }
        else {
          break;
        }
      }
      else {
        occur++;
        if (r-1 >= 0) {
          r--;
          continue;
        }
        else if (c+1 < matrix[0].length) {
          c++;
          continue;
        }
        else {
          break;
        }
      }
    }
    return occur;
  }
}
