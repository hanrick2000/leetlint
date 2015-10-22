package Easy;

import java.util.Arrays;

/**
 * http://www.lintcode.com/en/problem/reverse-words-in-a-string/
 * Reduce multiple space to a single space in the reversed string.
 * Created by 3:09 AM on 10/15/2015.
 */
public class ReverseWordsString {
    public static void main(String[] args) {
        String s = "Sky  is blue ";  //" ";//
        String res = new ReverseWordsString().reverseWords(s);
        System.out.println(res);
    }


    /**
     * Need to be careful in edge cases, also use split()+regex for multiple spaces
     *
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        String[] arr = s.split("\\s+");
        System.out.println(Arrays.toString(arr));
        int i;
        for (i = arr.length - 1; i >= 0; i--) {
            sb.append(arr[i]);
            if (i != 0)  sb.append(" ");
        }

        return sb.length() == 0 ? "" : sb.toString();
    }
}
