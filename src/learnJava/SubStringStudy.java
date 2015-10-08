package learnJava;

import java.util.*;

/**
 * 只要是String题目, 都要熟练regX或者string的操作. 例如Restore IP 里面用到很多substring.
 * http://n00tc0d3r.blogspot.com/2013/05/restore-ip-addresses.html
 * 
 * @author tzhang
 *
 */
public class SubStringStudy {
  public static void main(String[] args) {
    String a = "HaloJing";
    String b = "nihao ma?";
    String sub1 = a.substring(3);
    String sub2 = a.substring(0, 4);
    // System.out.println(sub1);
    // System.out.println(sub2);
    List<String>[] dp = (ArrayList<String>[]) new ArrayList[3];
    List<String> temp = new ArrayList<>();
    dp[0] = new ArrayList<>();
    temp.add(sub2);
    temp.add(a);
    List<List<String>> listoflist = new LinkedList<List<String>>();
    // listoflist.get(0).addAll(temp); // 这样是不对的.
    System.out.println(dp[0]);
    listoflist.add(0, temp);
    /*
     * new an array != new the element of the array. 所以不要在不是int/bool之类的时候用array.
     * http://stackoverflow
     * .com/questions/13055895/java-null-pointer-exception-with-array-of-arraylist
     */

    // dp[0].add(a.substring(3));
    // dp[1].add(a.substring(6));
    // dp[2].add(a.substring(3,4));
    // System.out.println(dp);
    System.out.println(listoflist);

    // for(String s : sub2)
    // System.err.println()

    // StringBuilder path = new StringBuilder();
    // path.append("hello");
    // System.out.println(path.toString());
    // System.out.println(path.delete(0, 3).toString());

    String path = "/home/"; // "/a/./b/../../c/";
    String[] splits = path.split("/");
    System.out.println(splits.length);
    for (String split : splits) {
      System.err.println(split + "|");
    }

    System.out.println("\nlet me see " + "".equals(""));

  }
}
