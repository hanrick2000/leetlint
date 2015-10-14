package Easy;

/**
 * Inspired by Lintcode's count and say, 9 chap's solution is elegent
 * Created by 10:34 PM on 10/13/2015.
 */
public class RunLengthEncoding {
    public static void main(String[] args) {
        String[] test = new String[]{"AAAAAABCCCC", "123444444444444444444444444444444444444"};
        for (String str : test) {
            System.out.println(rle(str));
        }
    }

    public static String rle(String str) {
        char[] oldChars = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < oldChars.length; ++i) {
            int cnt = 1;
            while (i+1 < oldChars.length && oldChars[i] == oldChars[i+1]) {
                i++;
                cnt++;
            }
            // if cnt > 10, then I can simply use ascii's 'A' or '0' to make it up to 50
            sb.append(String.valueOf((char)(cnt + 'A'-1)) + "" + String.valueOf(oldChars[i]));
        }
        return sb.toString();
    }
}
