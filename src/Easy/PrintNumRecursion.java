package Easy;

import java.util.*;

/**
 * Created by 11:17 PM on 10/13/2015.
 */
public class PrintNumRecursion {
    public static void main(String[] args) {
        List<Integer> path = new ArrayList<>();
        rec(path, 2, 0);
        Collections.sort(path);
        System.out.println(path);
    }

    public static void tip(List<Integer> result, int order, int pos) {
        // add into result
        if (pos > Math.pow(10, order)) {
            return;
        }
        result.add(pos);
        tip(result, order, pos+1);
    }

    public static void rec(List<Integer> result, int order, int pos) {
        // add into result
        if (pos >= Math.pow(10, order)) {
            return;
        }
        if (pos != 0) {
            result.add(pos);
        }
        for (int l = 0; l < 10; ++l) {
            if (pos + l == 0) {
                continue;
            }
            rec(result, order, pos * 10 + l );
        }
    }
}
