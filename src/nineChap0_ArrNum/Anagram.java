package nineChap0_ArrNum;

import java.util.*;

/**
 * http://www.lintcode.com/en/problem/anagrams/
 * Created by 12:23 AM on 10/16/2015.
 */
public class Anagram {
    public static void main(String[] args) {
        String[] strs = new String[]{"tho","tin","erg","end","pug","ton","alb","mes","job","ads","soy","toe","tap","sen","ape","led","rig","rig","con","wac","gog","zen","hay","lie","pay","kid","oaf","arc","hay","vet","sat","gap","hop","ben","gem","dem","pie","eco","cub","coy","pep","wot","wee"};
                //{"ray", "cod", "abe", "ned", "arc", "jar", "owl", "pop", "paw", "sky", "yup", "fed", "jul", "woo", "ado", "why", "ben", "mys", "den", "dem", "fat", "you", "eon", "sui", "oct", "asp", "ago", "lea", "sow", "hus", "fee", "yup", "eve", "red", "flo", "ids", "tic", "pup", "hag", "ito", "zoo"}; // {"ab", "ba", "cd", "dc", "e"}; // {"lint", "intl", "inlt", "code"};
        System.out.println(new Anagram().anagrams(strs));
    }

    /**
     * Array only equals if they are the same object!!! So DON'T use array as Map's key
     * http://stackoverflow.com/questions/16839182/can-a-java-array-be-used-as-a-hashmap-key
     *
     * @param strs
     * @return
     */
    public List<String> anagramsWrong(String[] strs) {
        // write your code here
        Map<char[], List<Integer>> anal = new HashMap<>();
        int idx = 0;
        for (String str : strs) {
            char[] patt = anagram(str);
            if (!anal.containsKey(patt)) {
                List<Integer> pos = new ArrayList<>();
                pos.add(idx);
                anal.put(patt, pos);
            } else {
                anal.get(patt).add(idx);
            }
            idx++;
        }
        List<String> res = new ArrayList<>();
        for (char[] patt : anal.keySet()) {
            if (anal.get(patt).size() > 1) {
                System.out.println(anal.get(patt));
                res.add(anal.get(patt).toString());
            }
        }
        return null;
    }

    public List<String> anagrams(String[] strs) {
        Map<Integer, List<String>> grams = new HashMap<>();
        for (String str : strs) {
            int patt = hash33(anagram(str));
            if (!grams.containsKey(patt)) {
                List<String> annony = new ArrayList<>();
                annony.add(str);
                grams.put(patt, annony);
            } else {
                grams.get(patt).add(str);
            }
        }
        List<String> result = new ArrayList<>();
        for (int patt : grams.keySet()) {
            if (grams.get(patt).size() > 1) {
                result.addAll(grams.get(patt));
            }
        }
        return result;
    }

    /**
     * O(n) for each string
     *
     * @param str
     * @return
     */
    private char[] anagram(String str) {
        char[] bitmap = new char[26];
        for (char c : str.toCharArray()) {
            bitmap[c - 'a']++;
        }
        return bitmap;
    }

    /**
     * http://planetmath.org/goodhashtableprimes
     * @param bitmap
     * @return
     */
    private int hash33(char[] bitmap) {
        long sum = 0;
        int HASH_SIZE = 12582917; // I simply like 12 + () + 17
        for (int i = 0; i < 26; ++i) {
            sum = (sum + bitmap[i]) * 33;
            sum = sum % HASH_SIZE;
        }
        return (int) sum;
    }
}
