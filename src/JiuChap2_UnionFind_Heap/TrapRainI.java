package JiuChap2_UnionFind_Heap;

/**
 * http://www.lintcode.com/en/problem/trapping-rain-water/
 * Created this class in JiuChap2_UnionFind_Heap at 9:31 AM, 11/4/2015.
 */
public class TrapRainI {
  public static void main(String[] args) {
    TrapRainI tr1 = new TrapRainI();
    int[] data = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
    int water = tr1.trapRainWater(data);
    System.out.println(water);
  }
  /**
   * @param heights: an array of integers
   * @return: a integer
   */
  public int trapRainWater(int[] heights) {
    // write your code here
    int l = 0, r = heights.length-1;
    int cnt = 0;
    while (l + 1 < r) {  // 9chap's template!
      if (heights[l] > heights[r]) {
        int hr = heights[r];
        while (r > l && heights[r-1] <= hr) {
          cnt += hr - heights[r-1];
          r--;
        }
        r = r-1;
      }
      else {
        int hl = heights[l];
        while (l < r && heights[l+1] <= hl) {
          cnt += hl - heights[l+1];
          l++;
        }
        l = l+1;
      }
    }
    return cnt;
  }
}
