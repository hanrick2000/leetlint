package nineChap0_ArrNum;

import java.util.*;

/**
 * http://www.lintcode.com/en/problem/majority-number-iii/
 * Created by 10:50 PM on 10/15/2015.
 */
public class MajorityNumIII {
    public static void main(String[] args) {
        int[] test = new int[]{1,2,1,2,1,3,3}; // {3,1,2,3,2,3,3,4,4,4}; //{7, 1, 7, 7, 61, 61, 61, 10, 10, 10, 61}; {1,1,2}; ////
        ArrayList<Integer> nums = new ArrayList<>();
        for (int i : test) {
            nums.add(i);
        }
        System.out.println(new MajorityNumIII().majorityNumber3(nums, 3));
    }

    /**
     * Keep clear mind!
     *
     * @param nums
     * @param k
     * @return
     */
    public int majorityNumber3(ArrayList<Integer> nums, int k) {
        // write your code
        Map<Integer, Integer> map = new HashMap<>();
        int numCandidates = 0;
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else if (numCandidates == k-1) {
                Set<Integer> keys = map.keySet();
//                for (int candidate : keys) {
//                    if (map.get(candidate) == 1) {
//                        map.remove(candidate);
//                        numCandidates--;
//                    } else {
//                        map.put(candidate, map.get(candidate) - 1);
//                    }
//                }
                for (Iterator<Map.Entry<Integer, Integer>> it = map.entrySet().iterator(); it.hasNext();) {
                    Map.Entry<Integer, Integer> entry = it.next();
                    if (entry.getValue() == 1) {
                        it.remove();
                        numCandidates--;
                    }
                    else {
                        map.put(entry.getKey(), entry.getValue()-1);
                    }

                }
            } else if (numCandidates < k-1) {
                map.put(num, 1);
                numCandidates++;
            }
        }

        for (int candidate : map.keySet()) {
            map.put(candidate, 0);
        }

        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            }
        }

        int res = -1, cnt = 0;
        for (int candidate : map.keySet()) {
            if (map.get(candidate) > cnt) {
                res = candidate;
                cnt = map.get(candidate);
            }
        }
        return res;
    }
}
