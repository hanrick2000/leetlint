package misc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ������SOF: http://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram
 * 
 * @author tzhang
 *
 */
public class BTtreePrinter {

  public static <T extends Comparable<?>> void printNode(TreeNode root) {
    int maxLevel = BTtreePrinter.maxLevel(root);

    printNodeInternal(Collections.singletonList(root), 1, maxLevel);
  }

  private static <T extends Comparable<?>> void printNodeInternal(
      List<TreeNode> nodes, int level, int maxLevel) {
    if (nodes.isEmpty() || BTtreePrinter.isAllElementsNull(nodes))
      return;

    int floor = maxLevel - level;
    int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
    int firstSpaces = (int) Math.pow(2, (floor)) - 1;
    int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

    BTtreePrinter.printWhitespaces(firstSpaces);

    List<TreeNode> newNodes = new ArrayList<TreeNode>();
    for (TreeNode node : nodes) {
      if (node != null) {
        System.out.print(node.val);
        newNodes.add(node.left);
        newNodes.add(node.right);
      } else {
        newNodes.add(null);
        newNodes.add(null);
        System.out.print(" ");
      }

      BTtreePrinter.printWhitespaces(betweenSpaces);
    }
    System.out.println("");

    for (int i = 1; i <= endgeLines; i++) {
      for (int j = 0; j < nodes.size(); j++) {
        BTtreePrinter.printWhitespaces(firstSpaces - i);
        if (nodes.get(j) == null) {
          BTtreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
          continue;
        }

        if (nodes.get(j).left != null)
          System.out.print("/");
        else
          BTtreePrinter.printWhitespaces(1);

        BTtreePrinter.printWhitespaces(i + i - 1);

        if (nodes.get(j).right != null)
          System.out.print("\\");
        else
          BTtreePrinter.printWhitespaces(1);

        BTtreePrinter.printWhitespaces(endgeLines + endgeLines - i);
      }

      System.out.println("");
    }

    printNodeInternal(newNodes, level + 1, maxLevel);
  }

  private static void printWhitespaces(int count) {
    for (int i = 0; i < count; i++)
      System.out.print(" ");
  }

  private static <T extends Comparable<?>> int maxLevel(TreeNode node) {
    if (node == null)
      return 0;

    return Math.max(BTtreePrinter.maxLevel(node.left),
        BTtreePrinter.maxLevel(node.right)) + 1;
  }

  private static boolean isAllElementsNull(List list) {
    for (Object object : list) {
      if (object != null)
        return false;
    }

    return true;
  }

}
