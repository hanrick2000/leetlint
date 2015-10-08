package nineChap8_DSA;

import java.util.*;

public class LongestConsecutive {
  public static void main(String[] args) {
    int[] arr = new int[]{0, 0, 1}; //{100, 101, 4, 200, 1, 3, 2};
//    int ans = findLCS1(arr);
    int ans = findLCS2(arr);
    System.out.println(ans);
  }
  
  // Dummy solution: O(nlogn)
  public static int findLCS1(int[] arr) {
    Arrays.sort(arr);
    int cnt = 1, ans = 0;
    for (int i = 1; i < arr.length; ++i) {
      if (arr[i] == arr[i-1]+1) {
        cnt++;
        ans = Math.max(ans, cnt);
      }
      else {
        cnt = 0;
      }
    }
    return ans;
  }
  
  // 
  public static int findLCS2(int[] arr) {
    Map<Integer, Integer> map = new HashMap<>();
    
    // step1: pre-process to build a map 
    for (int i : arr) {
      if (!map.containsKey(i)) {
        map.put(i, 1);
      }
      else {
        map.put(i, map.get(i)+1);
      }
    }
    
    // find LCS
    int ans = 0;
    for (int i = 0; i < arr.length; ++i) {
      int cnt = 1;  // every new search need to init cnt
//      if (!map.containsKey(arr[i]))
      if (map.get(arr[i]) == 0) {
        continue;
      }
      // find smaller
      int cur = arr[i], lhs = arr[i], rhs = arr[i];
      map.put(cur, 0);
      while (map.containsKey(--lhs)) {
        map.put(lhs, 0);
        cnt++;
      }
      // find larger
      while (map.containsKey(++rhs)) {
        map.put(rhs, 0);
        cnt++;
      }
      ans = Math.max(ans, cnt);
    }
    return ans;
  }
}
