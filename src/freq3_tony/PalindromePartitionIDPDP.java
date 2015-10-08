package freq3_tony;

import java.util.*;

public class PalindromePartitionIDPDP {
  /* return all partitions of string s */   
  public ArrayList<ArrayList<String>> DPpartition(String s) {  
    // build up a table for palindrome substrings  
    boolean[][] T = PalindromePartitionIDPrecur.palindromeTbl(s);  
    
    // this map is used to store previous results  
    // preRes.get(i) is all partitions of s[i..len-1]  
    HashMap<Integer, ArrayList<ArrayList<String>>> preRes =  
        new HashMap<Integer, ArrayList<ArrayList<String>>>();  
    
    for (int i=s.length()-1; i>=0; --i) {  
      ArrayList<ArrayList<String>> partitions = new ArrayList<ArrayList<String>>();  
      for (int j=i; j<s.length(); ++j) {  
        if (T[i][j]) {  
          if (j == s.length()-1) {  
            ArrayList<String> pp = new ArrayList<String>();  
            pp.add(s.substring(i, j+1));  
            partitions.add(pp);  
          } else {  
            for (ArrayList<String> p : preRes.get(j+1)) {  
              ArrayList<String> pp = new ArrayList<String>();  
              pp.add(s.substring(i, j+1));  
              pp.addAll(p);  
              partitions.add(pp);  
            }  
          }  
        }  
      }  
      preRes.put(i, partitions);  
    }  
    
    return preRes.get(0);  
  }   
  
  public static void main(String[] args) {
  String input = "aabaa";
  PalindromePartitionIDPDP ppdd = new PalindromePartitionIDPDP();
  System.out.println(ppdd.DPpartition(input));
}
}
