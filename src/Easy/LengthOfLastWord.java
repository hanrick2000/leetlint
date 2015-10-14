package Easy;

/**
 * http://www.lintcode.com/en/problem/length-of-last-word/
 * Created by 10:46 PM on 10/13/2015.
 */
public class LengthOfLastWord {
    public static void main(String[] args) {
        String[] test = new String[]{" 1  23 ", " ", " abc"};
        for (String str : test) {
            System.out.println(leng(str));
        }
    }

    public static int leng(String str) {
        char[] old = str.toCharArray();
        int oSize = old.length;
        int j = -1;
        for (int i = oSize - 1; i >= 0; i--) {
            if (old[i] != ' ') {
                if (j == -1) {
                    j = i;
                }
            }
            else if (j != -1) {
                return j - i;

            }
        }
        if (j != -1) {
            return j - 0;
        }
        else {
            return 0;
        }
    }
}
