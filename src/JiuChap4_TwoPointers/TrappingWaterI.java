package JiuChap4_TwoPointers;

/**
 * http://www.lintcode.com/en/problem/trapping-rain-water/
 * 9chap's Math idea is very important to understand, why the cases in between can be ignored.
 * Created this class in TwoPointers at 5:23 PM, 11/8/2015.
 */
public class TrappingWaterI {
  public static void main(String[] args) {
    TrappingWaterI tr1 = new TrappingWaterI();
    int[] data = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
    int water = tr1.trapRainWater(data);
    System.out.println(water);
  }

  /**
   * Using the 9chap's two pointers' template with http://www.shuatiblog.com/blog/2014/05/14/Trapping-Rain-Water/
   * @param heights
   * @return
   */
  public int trapRainWaterTemplate(int[] heights) {
    int ans = 0;
    if (heights == null || heights.length < 2) {
      return ans;
    }

    int i = 0, j = heights.length - 1;
    int lh = 0, rh = 0;
    while (i < j) {
      lh = Math.max(lh, heights[i]);
      rh = Math.max(rh, heights[j]);
      if (lh < rh) { // increase left pointer
        ans += lh - heights[i];
        i++;
      }
      else {
        ans += rh - heights[j];
        j--;
      }
    }
    return ans;
  }

  /**
   * 9chap algsI 's solution
   * @param heights: an array of integers
   * @return: a integer
   */
  public int trapRainWater(int[] heights) {
    int ans = 0;
    if (heights == null || heights.length < 2) {
      return ans;
    }

    int i = 0, j = heights.length - 1;
    int lh = heights[i];
    int rh = heights[j];
    while (i < j) {
      if (lh < rh) {
        i++;
        if (lh > heights[i]) {
          ans += (lh - heights[i]);
        }
        else {
          lh = heights[i];
        }
      }
      else {
        j--;
        if (rh > heights[j]) {
          ans += (rh - heights[j]);
        }
        else {
          rh = heights[j];
        }
      }
    }
    return ans;
  }

  /**
   *
   */
  public int trapRainWaterWRONG(int[] heights) {
    // write your code here
    int ans = 0;
    if (heights == null || heights.length < 2) {
      return ans;
    }

    int i = 0, j = heights.length - 1;
    while (i + 1 < j) {
      while (i+1 < j && heights[i] >= heights[i+1]) {
        ans += heights[i++];
      }
      while (i+1 < j && heights[j] <= heights[i]) {
        ans += heights[j--];
      }
    }
    return ans;
  }
}
