package misc;

import java.util.*;

import freq3_tony.PalindromePartitionIDPrecur;

/**
 * ����palindrome partionting֮ǰ, ���뵽һ��С����. һ��string�ֳܷɶ�����substring. ���subset�е�����. eg: "abc" =>
 * [[a,b,c], [ab,c], [a,bc], [abc]]
 * 
 * ���Ƶ���matrix multiplication, word break, word segment, etc
 * http://www.geeksforgeeks.org/dynamic-programming-set-8-matrix-chain-multiplication/
 * http://n00tc0d3r.blogspot.com/2013/03/word-segment.html
 * 
 * @author tzhang
 *
 */
public class SegmentString {

  /**
   * N00t�����DP���partion. ��������PalindromePartitionI��DPDP�ⷨ.
   * 
   * @param s
   * @return
   */
  private static ArrayList<ArrayList<String>> DPpartition(String s) {
    // build up a table for palindrome substrings
    // boolean[][] T = PalindromePartitionIDPrecur.palindromeTbl(s);

    // this map is used to store previous results
    // preRes.get(i) is all partitions of s[i..len-1]
    HashMap<Integer, ArrayList<ArrayList<String>>> preRes =
        new HashMap<Integer, ArrayList<ArrayList<String>>>();

    // �����ʵ��recursion�ĺ�����. ��������ֱ��iterate�Ϳ�����, ����scalable.
    for (int left = s.length() - 1; left >= 0; --left) {
      ArrayList<ArrayList<String>> partitions = new ArrayList<ArrayList<String>>();
      for (int i = left; i < s.length(); ++i) {
        if (true) {// (T[i][j]) {
          // �൱��Recursion��base condition
          if (i == s.length() - 1) {
            ArrayList<String> pp = new ArrayList<String>();
            pp.add(s.substring(left, i + 1));
            partitions.add(pp);
          } 
          // �������recursion�Ĳ���. ������ͬ, ���ǵ���leaf��������. ����leftһ��ʼָ��ĩβ.
          else {
            for (ArrayList<String> p : preRes.get(i + 1)) {
              ArrayList<String> pp = new ArrayList<String>();
              pp.add(s.substring(left, i + 1));
              pp.addAll(p);
              partitions.add(pp);
            }
          }
        }
      }
      // left pointer move to next position
      preRes.put(left, partitions);
    }

    return preRes.get(0);
  }


  /**
   * 
   * @param s
   * @param left
   * @param result
   */
  private static void tony(String s, int left, List<List<String>> result) {
    if (left == s.length()) {
      result.add(new ArrayList<String>());
    }
    for (int i = left; i < s.length(); ++i) {
      List<List<String>> temp = new ArrayList<List<String>>();
      tony(s, i + 1, temp);
      for (List<String> partitions : temp) {
        partitions.add(0, s.substring(left, i + 1));
      }
      result.addAll(temp); // add()��addAll()������.
    }
  }

  /**
   * ̫����, ��ѧ�����µ�Container: linkedHashSet. �����recursionҲ�Ǽ��õ�. һ��recursion, ������foreach������. ref:
   * http://stackoverflow.com/questions/5275002/recursively-spell-out-a-word
   * http://stackoverflow.com/questions/717725/understanding-recursion
   * 
   * ��������word break II��ʱ��ڶ��ο����SOF������, ���Ǻܾ��޵�recursion.
   * 
   * @param s
   * @return
   */
  public static List<List<String>> segment(String s) {
    // set itself
    List<List<String>> result = new LinkedList<List<String>>();
    List<String> root = new LinkedList<String>();
    root.add(s);
    result.add(root);

    // set union of each substring s\x
    for (int i = 1; i < s.length(); i++) {
      String prefix = s.substring(0, i);
      String rest = s.substring(i);
      for (List<String> segments : segment(rest)) {
        List<String> segment = new LinkedList<>();
        segment.add(prefix);
        segment.addAll(segments);
        result.add(segment);
      }
    }
    return result;
  }

  // ��ôд���÷���ֵ��recursion?
  public static void segment2(String s, Set<Set<String>> result) {
    // set itself
    // Set<Set<String>> result = new HashSet<Set<String>>();
    Set<String> root = new HashSet<String>();
    root.add(s);
    result.add(root);

    // set union of each substring s\x
    for (int i = 1; i < s.length(); i++) {
      String prefix = s.substring(0, i);
      String rest = s.substring(i);
      // for (Set<String> segments : segment2(rest, result)) {
      // Set<String> segment = new HashSet<>();
      // segment.add(prefix);
      // segment.addAll(segments);
      // result.add(segment);
      // }
    }
  }

  public static void main(String[] args) {
    // List<List<String>> res = new ArrayList<List<String>>();
    // tony("ABC", 0, res);
    // System.out.println(res);
    // System.out.println(segment("ABCDE"));
    System.out.println(DPpartition("ABC"));
  }
}
