package Easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * http://www.lintcode.com/en/problem/count-and-say/
 * Created by 8:25 PM on 10/13/2015.
 */
public class CountSay {
    public static void main(String[] args) {
//        int[] test = new int[]{1, 14, 245, 1234};
//        for (int i : test) {
//            System.out.println(Arrays.toString(itoa(i)));
//        }
//
//        System.out.println(countSayComplicate(5));
//        System.out.println(atoi("123"));

        System.out.println(countSay(5));
    }

    /**
     * 9chap's solution, using: tony.toCharArray(), String.valueOf(27).
     * http://www.cnblogs.com/TenosDoIt/p/3776356.html, Proof: countandSay can only be 1,2,3! no larger number!
     * @param n
     * @return
     */
    public static String countSay(int n) {
        String oldString = "1";
        while (--n > 0) {
            StringBuilder sb = new StringBuilder();
            char[] oldChars = oldString.toCharArray();

            for (int i = 0; i < oldChars.length; ++i) {
                int count  = 1;
                while (i+1 < oldChars.length && oldChars[i] == oldChars[i+1]) {
                    count++;
                    i++;
                }
                sb.append(String.valueOf(count) + String.valueOf(oldChars[i]));
            }
            oldString = sb.toString();
        }
        return oldString;
    }

    public static String countSayComplicate(int n) {
        int num = 1;
        String tmp = "";
        for (int i = 0; i < n-1; ++i) {
            tmp = countSayHelper(num);
            num = atoi(tmp);
            System.out.println(tmp + " : " + num);
        }
        return tmp;
    }

    private static int atoi(String cnt) {
        int size = cnt.length();

        int num = 0;
        for (int i = 0; i < size; ++i) {
            num = num * 10 + (cnt.charAt(i) - '0');
        }
        return num;
    }

    public static String countSayHelper(int n) {
        int[] i2a = itoa(n);
        int size = i2a.length;
        int cnt = 1;
        String output = "";
        for (int i = 0, j = 0; i < size || j < size; ) {
            int cur = i2a[i];
            while (j < size - 1 && i2a[i] == i2a[j + 1]) {
                j++;
                cnt++;
            }
            j++;
            i = j;

            output += cnt + "" + cur;
            cnt = 1;
        }
        return output;
    }

    /**
     * find each size of same neighbor elements:
     * eg: [1] = 1, [1,2,2,2,3] = [1,3,1].
     *
     * @param nums
     * @return
     */
    private static int[] same(int[] nums) {
        return null;
    }

    private static int[] itoa(int n) {
//        int n = num;
        List<Integer> arr = new ArrayList<>();
        do {
            arr.add(n % 10);
            n /= 10;
        } while (n > 0);

        int size = arr.size();
        int[] res = new int[size];

        for (int i = 0; i < size; ++i) {
            res[size - i - 1] = arr.get(i);
        }
        return res;
    }
}
