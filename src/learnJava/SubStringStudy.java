package learnJava;

import java.util.*;

/**
 * ֻҪ��String��Ŀ, ��Ҫ����regX����string�Ĳ���. ����Restore IP �����õ��ܶ�substring.
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
    // listoflist.get(0).addAll(temp); // �����ǲ��Ե�.
    System.out.println(dp[0]);
    listoflist.add(0, temp);
    /*
     * new an array != new the element of the array. ���Բ�Ҫ�ڲ���int/bool֮���ʱ����array.
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

    // substring length
    String s1 = "", s2 = "";
    System.out.println(s1.length() + ", "  + s2.length());
    //System.out.println("Char at 0: " + s1.charAt(0));

    //s1 = "great";
    //s2 = "rgtae";
    //System.out.println(s1.substring(0, 4) + ", " + s1.substring(4));
    //System.out.println(s2.substring(0, 1) + ", " + s2.substring(1));

  }
}
