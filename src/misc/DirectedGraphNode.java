package misc;

import java.util.ArrayList;

/**
 * Created at 5:06 PM on 11/19/15.
 */
public class DirectedGraphNode {
  public int label;
  public ArrayList<DirectedGraphNode> neighbors;

  public DirectedGraphNode(int x) {
    label = x;
    neighbors = new ArrayList<>();
  }
}
