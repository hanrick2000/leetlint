package nineChap0_ArrNum;

/**
 * http://www.lintcode.com/en/problem/sort-letters-by-case/
 * Created by 2:19 PM on 10/14/2015.
 */
public class SortLetterCase {
    public static void main(String[] args) {
        String str = "abAcD";
        char[] test = str.toCharArray();
        new SortLetterCase().sortLetters(test);
        for (char c : test) {
            System.out.print(c);
        }
    }

    public void sortLetters(char[] chars) {
        //write your code here
        if (chars == null || chars.length == 0) {
            return;
        }
        int len = chars.length;

        int lo = 0, hi = len - 1;
        int higher = 'a';

        while (true) {
            while (lo < len && chars[lo] >= higher) {
                lo++;
            }
            while (hi >= 0 && chars[hi] < higher) {
                hi--;
            }
            if (lo > hi) {
                return;
            }
            exch(chars, lo, hi);
        }
    }

    private void exch(char[] str, int i, int j) {
        char tmp = str[i];
        str[i]  = str[j];
        str[j]  = tmp;
    }
}
