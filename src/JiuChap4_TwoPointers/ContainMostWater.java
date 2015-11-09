package JiuChap4_TwoPointers;

/**
 * http://www.lintcode.com/en/problem/container-with-most-water/
 * The Math idea is important as well as the starting point to design the code.
 * Created this class in TwoPointers at 4:58 PM, 11/8/2015.
 */
public class ContainMostWater {
  public static void main(String[] args) {
    ContainMostWater cmw = new ContainMostWater();
    int[] wat = new int[]{1,3,2};
    int res = cmw.maxArea(wat);
    System.out.println(res);
  }
  /**
   * @param heights: an array of integers
   * @return: an integer
   */
  public int maxArea(int[] heights) {
    // write your code here
    int ans = 0;
    if (heights == null || heights.length < 2) {
      return ans;
    }
    int leng = heights.length;
    int i = 0, j = leng-1;
    while (i < j) {
      if (heights[i] < heights[j]) {
        ans = Math.max(ans, (j-i) * heights[i]);
        i++;
      }
      else if (heights[i] > heights[j]){
        ans = Math.max(ans, (j-i) * heights[j]);
        j--;
      }
      else {
        ans = Math.max(ans, (j-i) * heights[i]);
        i++;
      }
    }
    return ans;
  }
}
