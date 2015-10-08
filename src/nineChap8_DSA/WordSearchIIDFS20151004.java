package nineChap8_DSA;

import java.util.*;

/**
 * My trial to do this simple DFS ............. Took me 2 hours! Also very ugly.
 *  http://www.lintcode.com/en/problem/word-search-ii/
 */
public class WordSearchIIDFS20151004 {
  public static enum Direction {
    LEFT, RIGHT, UP, DOWN
  };

  static class tuple {
    String s;
    int idx;

    tuple(String str, int index) {
      s = str;
      idx = index;
    }
  }

  public static void main(String[] args) {
    char[][] Mat =
    // new char[][] { {'d', 'o', 'a', 'f'}, {'a', 'g', 'a', 'i'},
    // {'d', 'c', 'a', 'n'}};
        new char[][] { {'a', 'd'}, {'o', 'b'}

        };

    String[] dict = new String[] {"dbd", "oa"}; // {"dad", "dog", "dgdg", "can", "again"};
    List<List<String>> ans;
    ans = wordSearchII(Mat, dict);

    // for (Direction d : Direction.values()) {
    // String tony = getNeighbor("XJ", Mat, 6, d);
    // System.out.println(tony);
    // }

    System.out.println("\nResult: ");
    for (List<String> l : ans) {
      for (String s : l) {
        System.out.print(s + " ");
      }
      System.out.println();
    }

  }

  public static List<List<String>> wordSearchII(char[][] Mat,
      String[] dictionary) {
    if (Mat == null || dictionary == null) {
      return null;
    }

    Set<String> dict = new HashSet<>();
    for (String s : dictionary) {
      dict.add(s);
    }

    List<String> path = new ArrayList<>();
    List<List<String>> result = new ArrayList<List<String>>();
    int ROW = Mat.length, COL = Mat[0].length;
    
    for (int i = 0; i < Mat.length * Mat[0].length; ++i) {
      int r = i / COL, c = i % COL;
      dfs(result, path, i, Mat[r][c]+"", Mat, dict);
    }
    return result;
  }

  private static void dfs(List<List<String>> result, List<String> path,
      int idx, String s, char[][] Mat, Set<String> dict) {
    if (s.length() == 4 || s == null) {
      return;
    }
    // if (idx == Mat.length * Mat[0].length - 1) {
    // return;
    // }
    // add path into result
    if (path.size() != 0) {
      result.add(new ArrayList<String>(path));
      return;
    }
    // DFS traverse with prune juice
    // for (int i = 0; i < Mat.length * Mat[0].length; ++i) {
    for (Direction direction : Direction.values()) {
      // int[] index = new int[] {idx};
      // String nextWord = getNeighbor(s, Mat, idx, direction, index);
      tuple nextWordIdx = getNeighbor(s, Mat, idx, direction);
      String nextWord = nextWordIdx.s;
      int newIdx = nextWordIdx.idx;
      if (nextWord == null)
        continue;
      if (dict.contains(nextWord)) {
        path.add(nextWord);
      }
      System.out.println(nextWord);

      // idx = index[0];
      dfs(result, path, newIdx, nextWord, Mat, dict);
      if (dict.contains(nextWord))
        path.remove(path.size() - 1);
    }
    // }
  }

  private static tuple getNeighbor(String start, char[][] Mat, int idx,
      Direction direction) {
    int ROW = Mat.length, COL = Mat[0].length;
    int r = idx / COL, c = idx % COL;
    String nextWord;
    switch (direction) {
      case LEFT:
        if (c != 0) {
          nextWord = start + Mat[r][c - 1];
          idx -= 1;
        } else {
          nextWord = null;
          idx = -1;
        }
        break;
      case RIGHT:
        if (c != COL - 1) {
          nextWord = start + Mat[r][c + 1];
          idx += 1;
        } else {
          nextWord = null;
          idx = -1;
        }
        break;
      case UP:
        if (r != 0) {
          nextWord = start + Mat[r - 1][c];
          idx -= COL;
        } else {
          nextWord = null;
          idx = -1;
        }
        break;
      case DOWN:
        if (r != ROW - 1) {
          nextWord = start + Mat[r + 1][c];
          idx += COL;
        } else {
          nextWord = null;
          idx = -1;
        }
        break;
      default:
        nextWord = null;
        idx = -1;
        break;
    }

    tuple returnVal = new tuple(nextWord, idx);
    return returnVal;
  }
}
