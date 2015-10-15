package Easy;

/**
 * http://www.lintcode.com/en/problem/two-strings-are-anagrams/#
 * Created by 12:42 PM on 10/14/2015.
 */
public class TwoStringAnagram {
    public static void main(String[] args) {
        System.out.println(new TwoStringAnagram().anagram("Hello", "lleHo"));
    }

    /**
     * Given s="abcd", t="dcab", return true.
     *
     * @param s
     * @param t
     * @return
     */
    public boolean anagram(String s, String t) {
        // write your code here
        int sLen = s.length();
        int tLen = t.length();
        if (sLen != tLen) {
            return false;
        }

        int[] freq = new int[256]; // ASCII
        for (int i = 0; i < sLen; ++i) {
            freq[s.charAt(i)]++;
            freq[t.charAt(i)]--;
        }

        for (int i = 0; i < 256; ++i) {
            if (freq[i] !=  0) {
                return false;
            }
        }
        return true;
    }
}
