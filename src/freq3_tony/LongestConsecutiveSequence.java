package freq3_tony;

import java.util.*;

/**
 * 使用的N00t的方法. 分析的很好. 每个题目的各个条件都是关键. 这里就是consecutive和O(n). 因为O(n), 所以不能用sorting. 那么就剩HashTable了.
 * 但是HashTable设计的话, 要清楚key/value分别干什么. 这时候注意到是consecutive, 那么在grow的过程中, 每个cluster都是连续的, 所以只要只到边界就行.
 * 那么边界对应的value就是长度了, 因为边界-长度=左边界, 于是就知道整个cluster了. 因为循环的时候只考虑当前这个数的前一个或者后一个.
 * 
 * http://n00tc0d3r.blogspot.com/2013/03/longest-consecutive-sequence.html
 * 
 * @author tzhang
 *
 */
public class LongestConsecutiveSequence {
  // 方法一: Union/Find---使用hashTable.
  /**
   * coding关键在于理解与设计, 之后只是写下来实现而已. 不要不清楚自己要做什么之前就开始写.
   * 
   * @param map : key?val? key是其中一个边界, value是这个边界的长度.
   * @param l : l既是这个range的key, 又是这个range的边界点
   * @param r
   * @return : 返回merge之后的长度. 他的left/right边界分别就是l,r这2个输入值.
   */
  private int merge(HashMap<Integer, Integer> map, int l, int r) {
    int left = l - map.get(l) + 1;
    int right = r + map.get(r) - 1;
    int range = right - left + 1;
    map.put(left, range);
    map.put(right, range);
    return range;
  }

  /**
   * 然后就是不断Union即可. 当看到一个没有的key的时候, 就put(num, 1) 表示一个小丢丢的cluster, 然后才开始merge.
   * 注意merge的3个input分别是总体的map(因为在建, 所以必须作为merge的输入), 然后是左右cluster的边界. 要学会Abstraction.
   * 这里的API设计就是要意识到虽然是range, 但不要总以为一定要左右边界. 就好比minPQ不要老以为必须用Tree, 或者Union-Find必须要obj, 其实用一个paralle的int[]就行.
   * @param nums
   * @return
   */
  public int longestConsecutive(int[] nums) {
    HashMap<Integer, Integer> map = new HashMap<>();
    int maxLen = (nums.length == 0) ? 0 : 1;
    for (int num : nums) {
      if (!map.containsKey(num)) {
        map.put(num, 1);
        if (map.containsKey(num - 1))
          maxLen = Math.max(merge(map, num-1, num), maxLen);
        if (map.containsKey(num + 1))
          maxLen = Math.max(merge(map, num, num+1), maxLen);
      }
    }
    return maxLen;
  }

  //方法二: 直接全部放到HashTable里面, 然后过一遍, 同时增长. 
  /**
   * 关键是怎么增长? 还是和Union-Find 方法一样, 看有没有num +/- 1的存在 
   * step是什么???
   * @param map
   * @param num
   * @param step : 当寻找num的左边界的时候, step为-1, 所以while()里面一直找左边, 当找num右边界时, 自然是step=+1
   * @return
   */
  private int findConsecutive(HashMap<Integer, Boolean> map, int num, int step) {
    int len = 0;
    while (map.containsKey(num)) {
      ++len;
      map.put(num, true); // 先placehold所有的input numbers, 然是value=false, 这样在loop里面的时候double check才value=true
      num += step;
    }
    return len;
  }
  
  // 这样才对的简单的方法. 网上很多用多个loop会超时.
  public int longestConsecutiveSimple(int[] nums) {
    HashMap<Integer, Boolean> map = new HashMap<>();
    for(int num:nums)
      map.put(num, false); // 巧妙的init
    
    int maxLen = 0;
    for(int num:nums) {
      if(!map.get(num)) {
        map.put(num, true);
        int len = 1 + findConsecutive(map, num-1, -1);  // 找到做range
        len += findConsecutive(map, num+1, +1);   // 找到右range
        maxLen = Math.max(len, maxLen);
      }
    }
    return maxLen;
  }
  // ctor as the client of algs
  public LongestConsecutiveSequence() {
    int[] input = new int[] {100, 4, 200, 1, 2, 3};
    System.out.println("Result: " + longestConsecutiveSimple(input));
  }

  public static void main(String[] args) {
    LongestConsecutiveSequence lcs = new LongestConsecutiveSequence();
  }
}
