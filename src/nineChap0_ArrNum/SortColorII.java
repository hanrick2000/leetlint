package nineChap0_ArrNum;

import java.util.Arrays;

/**
 * Created by 12:48 AM on 10/21/2015.
 */
public class SortColorII {
    public static void main(String[] args) {
        int[] test = new int[]{3, 2, 2, 1, 4};
        new SortColorII().sortColors2(test, 4);
    }

    public void sortColors2(int[] colors, int k) {
        // write your code here
        if (colors == null || colors.length < k) {
            return;
        }
        int i = 0, j = colors.length - 1;
        for (int lhs = 1; lhs <= k; ++lhs) {
            int start = divideLhs(colors, i, j, lhs);
            i = start;
            j = colors.length - 1;
        }

        System.out.println(Arrays.toString(colors));
    }

    private int divideLhs(int[] colors, int i, int j, int left) {
        while (true) {
            while (i < colors.length && colors[i] == left) {
                i++;
            }
            while (j >= 0 && colors[j] > left) {
                j--;
            }
            if (i > j) {
                return i;
            }
            exch(colors, i, j);
        }
    }

    private void exch(int[] c, int i, int j) {
        int tmp = c[i];
        c[i] = c[j];
        c[j] = tmp;
    }
}
