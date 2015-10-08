package learnJava;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * ѧϰ��δ�file path��ȡ���� java.io.BufferedReader java.io.FileReader regex try, catch. 20150224
 * 
 * @author tzhang
 *
 */
public class FileRead {
  // http://stackoverflow.com/questions/1415955/private-final-static-attribute-vs-private-final-attribute
  // final ˵�����var����init��ʱ��ȷ��.
  // static ����class attr, ��ÿ��instance�޹�. �ο�Integer.ParseInt().
  // private static final int Vsf;
  // private final int vf;
  // private static int vS;
  private int V;
  private int E;
  private Map<Integer, List<Integer>> adj;

  /**
   * ctor ��input�ļ�����һ��adjacent list��Graph
   * 
   * @param args
   */
  public FileRead() {
    // ttt: ע��Eclipse������ļ�λ���������src folder��.
    String path = "src/learnJava/input.txt";
    // ע��BufferedReader/FileReader����Ҫnewһ��object. ����I/O�����������try/catch����. ��malloc������null(0)�ж�һ��.
    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
      String line = null;
      String[] token = null;
      // lazy init adj. ��ΪҪ���ж϶����file�Ƿ��ж���. �����new�Ĵ����.
      adj = new HashMap<>();
      // readline()�ǵõ���br�ĵ�ǰ�ĵ�һ��, ��Ϊ��I/O, ����ҲҪnull�ж�
      while ((line = br.readLine()) != null) {
        // split�����õ���regex. �ο�vogel/a��tutorial�ܲ���
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
        // ֻ�ű�tail���head����, �����ظ�edge.
        if (t > v) {
          int[] edge = new int[] {v, t};
          presentEdge.add(edge);
        }
      }
      System.out.print(v + ": ");
      for (int[] a : presentEdge)
        System.out.print(a[1] + " ");
      System.out.println();
      // System.out.printf("building %d's edgeSet\n", cnt--);
    }
  }

  public static void main(String[] args) {
    FileRead fr = new FileRead();
    fr.present();
    System.out.println("\nHello worked");
  }
}
