package nineChap8_DSA;

import java.util.Stack;

/*-
 * This is an example of using Stack!
 * Remember the conclusion(though the proof of this idea take some time to understand)
 *      :Use stack to find 1st smaller number to the left to each number, 
 * @author tzhang
 *
 */
public class LargeRecInstogram {
  public static void main(String[] args) {
    int[] rec = new int[] {2, 1, 5, 6, 2, 3};
    int ans;
    // ans = O3(rec);
    // ans = O2(rec);
    ans = O1stack(rec);
    System.out.println(ans);
  }

  public static int O3(int[] rec) {
    int max = 0;
    int leng = rec.length;
    for (int i = 0; i < leng; ++i) {
      int areaij = 0, height = rec[i];
      for (int j = i; j < leng; ++j) {
        for (int k = i; k <= j; ++k) {
          height = Math.min(height, rec[j]);
          // areaij += height;
          areaij = height * (k - i + 1);
          max = Math.max(areaij, max);
        }
      }
    }

    return max;
  }

  public static int O2(int[] rec) {
    int max = 0, leng = rec.length;
    for (int i = 0; i < leng; ++i) {
      int height = rec[i];
      int l = i, r = i;
      // find left first smaller
      while (l >= 0 && height <= rec[l]) {
        l--;
      }
      while (r < leng && height <= rec[r]) {
        r++;
      }
      // for (; l >= 0; l--) {
      // if (rec[l] < rec[i]) {
      // break;
      // }
      // }
      // for (; r < leng; ++r) {
      // if (rec[r] < rec[i]) {
      // break;
      // }
      // }

      // int area = height * (r-l+1);
      int area = height * (r - l - 1);
      max = Math.max(area, max);
    }

    return max;
  }

  /**
   * The classical usage of Stack. I did it wrong, not fully understand the algs and flow, or say,
   * not fully familiar with for/while loop!
   * 
   * @param Rec
   * @return
   */
  public static int O1stackWRONG(int[] Rec) {
    if (Rec == null || Rec.length == 0) {
      return 0;
    }
    Stack<Integer> incStack = new Stack<>();
    incStack.push(0);

    int height = Rec[0];
    int area = 0, maxarea = 0;

    int i = 1;
    int lhs = -1;
    while (i < Rec.length) {
      if (Rec[i] < Rec[incStack.peek()]) {
        area = Rec[incStack.pop()] * (i - lhs - 1);
        maxarea = Math.max(area, maxarea);
      } else {
        incStack.push(i);
      }
      i++;
    }

    return maxarea;
  }
  
  /**
   * https://sites.google.com/site/jennyshelloworld/company-blog/chapter-8---data-structure
   * 
   * @param Rec
   * @return
   */
  public static int O1stack(int[] Rec) {
    if (Rec == null || Rec.length == 0) {
      return 0;
    }
    Stack<Integer> incS = new Stack<>();
    int ans = 0;
    
    for (int i = 0; i <= Rec.length; ++i) {
      int curHeight;
      if (i == Rec.length) {
        curHeight = 0;
      }
      else {
        curHeight = Rec[i];
      }
//      while (!incS.empty() && curHeight <= incS.peek()) {
      while (!incS.empty() && curHeight <= Rec[incS.peek()]) {
        // maintain invariant: incS is always monolithic increasing
        int h = Rec[incS.pop()];
        int w = 0;
        if (!incS.empty()) {
          w = i - incS.peek() - 1;
        }
        else {
          w = i;
        }
        ans = Math.max(ans, w * h);
      }
      incS.push(i);
    }
    return ans;
  }

}
