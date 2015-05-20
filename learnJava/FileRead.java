package learnJava;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 学习如何从file path读取数据 java.io.BufferedReader java.io.FileReader regex try, catch. 20150224
 * 
 * @author tzhang
 *
 */
public class FileRead {
  // http://stackoverflow.com/questions/1415955/private-final-static-attribute-vs-private-final-attribute
  // final 说明这个var是在init的时候确定.
  // static 就是class attr, 和每个instance无关. 参考Integer.ParseInt().
  // private static final int Vsf;
  // private final int vf;
  // private static int vS;
  private int V;
  private int E;
  private Map<Integer, List<Integer>> adj;

  /**
   * ctor 从input文件创建一个adjacent list的Graph
   * 
   * @param args
   */
  public FileRead() {
    // ttt: 注意Eclipse里面的文件位置是相对于src folder的.
    String path = "src/learnJava/input.txt";
    // 注意BufferedReader/FileReader都是要new一个object. 而且I/O操作必须放在try/catch里面. 和malloc必须用null(0)判断一次.
    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
      String line = null;
      String[] token = null;
      // lazy init adj. 因为要先判断读入的file是否有东西. 避免的new的大操作.
      adj = new HashMap<>();
      // readline()是得到了br的当前的第一行, 因为是I/O, 所以也要null判断
      while ((line = br.readLine()) != null) {
        // split就是用到了regex. 参考vogel/a的tutorial很不错
        token = line.split("\\s+");
        V++;
        List<Integer> list = new LinkedList<Integer>();
        int v = Integer.parseInt(token[0]);
        for (int i = 1; i < token.length; i++) {
          list.add(Integer.parseInt(token[i]));
        }
        adj.put(v, list);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // show-off the readin adj
  public void present() {
    System.out.printf("adj has %d vertex\n", V);
    int cnt = V;
    for (int v : adj.keySet()) {
      List<int[]> presentEdge = new LinkedList<>();
      for (int t : adj.get(v)) {
        // 只放比tail大的head进来, 避免重复edge.
        if (t > v) {
          int[] edge = new int[] {v, t};
          presentEdge.add(edge);
        }
      }
      System.out.print(v + ": ");
      for (int[] a : presentEdge)
        System.out.print(a[1] + " ");
      System.out.println();
//      System.out.printf("building %d's edgeSet\n", cnt--);
    }
  }

  public static void main(String[] args) {
    FileRead fr = new FileRead();
    fr.present();
    System.out.println("\nHello worked");
  }
}
