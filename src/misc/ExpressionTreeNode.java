package misc;

/**
 * Created this class in misc at 4:01 PM, 11/6/2015.
 */
public class ExpressionTreeNode {
  public String symbol;
  public ExpressionTreeNode left, right;

  public ExpressionTreeNode(String symbol) {
    this.symbol = symbol;
    this.left = this.right = null;
  }
}
