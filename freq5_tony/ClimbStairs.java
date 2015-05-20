package freq5_tony;

public class ClimbStairs {

  public int climbStairs(int stairs) {
    // TODO Auto-generated constructor stub
    int[] uniqueWays = new int[stairs + 1];
    uniqueWays[0] = 1;
    uniqueWays[1] = 1;
    for (int i = 2; i < stairs + 1; i++) {
      uniqueWays[i] = uniqueWays[i - 1] + uniqueWays[i - 2];
    }
    return uniqueWays[stairs];
  }

  public static void main(String[] args) {
    System.out.println("I got this: " + (new ClimbStairs()).climbStairs(5));
  }

}
