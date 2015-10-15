package Easy;

import java.util.*;

/**
 * Created by 2:46 AM on 10/15/2015.
 */
public class ValidParenthese {
    public static void main(String[] args) {
        String[] test = new String[]{"(([ab])){}", "(](]"};
        for (String str : test) {
            System.out.println(new ValidParenthese().isValidParentheses(str));
        }
    }

    public boolean isValidParentheses(String s) {
        // Write your code here
        if (s == null || s.length() == 0) {
            return true;
        }

        Stack<Character> parenth = new Stack<>();
        parenth.push('#'); // ease
        Map<Character, Integer> dict = new HashMap<>();
        dict.put('(', 0);
        dict.put(')', 1);
        dict.put('[', 10);
        dict.put(']', 11);
        dict.put('{', 20);
        dict.put('}', 21);

        char[] arr = s.toCharArray();
        for (char a : arr) {
            if (dict.containsKey(a)) {
                if (dict.containsKey(parenth.peek()) && dict.get(parenth.peek()) + 1 == dict.get(a)) {
                    parenth.pop();
                }
                else {
                    parenth.push(a);
                }
            }
        }

        if (parenth.size() == 1) {
            return true;
        }
        else {
            return false;
        }
    }
}
