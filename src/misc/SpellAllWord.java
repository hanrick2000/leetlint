package misc;

import java.util.ArrayList;

public class SpellAllWord {
  /**
   * Simply port C++ to Java
   * @param str
   * @param buff
   * @param i
   * @param j
   * @param n
   */
  public static void printPatternUtil(String str, char[] buff, int i, int j, int n) {
    if (i == n) {
      System.out.println(buff);
      return;
    }
    
    // either put the current char
    buff[j] = (str.charAt(i));
    printPatternUtil(str, buff, i+1, j+1, n);
    
    // or print ' ' and then the current char
    buff[j] = ' ';
    buff[j+1] = (str.charAt(i));
    printPatternUtil(str, buff, i+1, j+2, n);
  }
  
//  public static void printPatternUtil(String str, StringBuilder buff, int i, int j, int n) {
//    if (i == n) {
//      System.out.println(buff.toString());
//      return;
//    }
//    
//    // either put the current char
//    buff.setCharAt(j, str.charAt(i));
//    printPatternUtil(str, buff, i+1, j+1, n);
//    
//    // or print ' ' and then the current char
//    buff.setCharAt(j, ' ');
//    buff.setCharAt(j+1, str.charAt(i));
//    printPatternUtil(str, buff, i+1, j+2, n);
//  }
  
  public static void printPattern(String s) {
    int n = s.length();
    char[] buff = new char[2*n];
    buff[0] = s.charAt(0);
//    StringBuilder buff = new StringBuilder();
//    buff.setLength(2*n);  // StringBuilderʹ��֮ǰҪ��ʼ��, ���������??? ����ֻ��append, ����setCharAt().
//    buff.setCharAt(0, s.charAt(0));
    printPatternUtil(s, buff, 1, 1, n);
  }
  
  
  /**
   * Spell all word������: Amazon OA�ĵ�һ��. �����ǻظ��и�����һ���ⷨ
   * http://www.geeksforgeeks.org/print-possible-strings-can-made-placing-spaces/
   * @param input
   */
  public static void spellAllWordMethod2(String input) {
    String s = input;
    int exp = (int) Math.pow(2, s.length());

    for (int i = 0; i < (exp / 2); i++) {
      for (int j = 0; j < s.length(); j++) {
        if (j == 0) {
          System.out.print(s.charAt(j));
          continue;
        }
        int check = 1 << j - 1 & i;  // ???û����
        if (check >= 1) {
          System.out.print(" " + s.charAt(j));
        } else {
          System.out.print(s.charAt(j));
        }
      }
      System.out.print("\n");
    }
  }

  public static void main(String[] args) {
//    spellAllWordMethod2("ABCD");
    printPattern("ABCD");
  }
}
