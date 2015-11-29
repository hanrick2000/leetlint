package nineChap0_ArrNum;

/**
 * http://www.lintcode.com/en/problem/gas-station/ Created by 1:17 AM on 10/15/2015.
 */
public class GasStation {
  public static void main(String[] args) {
    GasStation gs = new GasStation();
    int[] gas = new int[]{4}; //{1,1,3,1}; //{2,0,1,2,3,4,0};  //
    int[] cost = new int[]{5}; //{2,2,1,1}; //{0,1,0,0,0,0,11}; //
    int ans = gs.canCompleteCircuit(gas, cost);
    System.out.println(ans);
  }

  /**
   * Lexi's Leetcode solutions
   * learned the looping of circular array: i = 0 to 2*N-1, i = i %N
   * http://hehejun.blogspot.com/2014/12/leetcodegas-station.html
   * @param gas
   * @param cost
   * @return
   */
  public int canCompleteCircuit(int[] gas, int[] cost) {
    int N = gas.length;
    int sum = 0;
    int start = 0;
    int total = 0;
    for (int i = 0; i < N; ++i) {
      int diff = gas[i]-cost[i];
      if (sum + diff < 0) {
        start = i+1;
        sum = 0;
      }
      else {
        sum += diff;
      }
      total += diff;
    }
    return total >= 0? start : -1;
  }

  public int canCompleteCircuitON2(int[] gas, int[] cost) {
    // write your code here
    int N = gas.length;
    for (int i = 0; i < N; ++i) {
      long diff = gas[i] - cost[i];
      if (diff<0) {
        continue;
      }
      for (int j = i+1; j < 2*N; ++j) {
        diff += gas[j % N] - cost[j%N];
        if (diff < 0) {
          break;
        }
        else {
          if (j % N == (N-i - 1)) {
            return i;
          }
        }
      }
    }
    return -1;
  }
}
