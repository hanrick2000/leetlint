package JiuChap1_intro;

/**
 * http://www.lintcode.com/en/problem/segment-tree-query-ii/
 * Created at 3:50 PM on 11/24/15.
 */
public class SegmentTreeQueryII {
  public static void main(String[] args) {
    int[] test = new int[]{0,3,2};
    SegmentTreeQueryII stq2 = new SegmentTreeQueryII();
    SegmentTreeNode root = stq2.build(test);
    System.out.println(stq2.query(root, 1,1));
    System.out.println(stq2.query(root, 1,2));
    System.out.println(stq2.query(root, 2,3));
    System.out.println(stq2.query(root, 0,13));
    //SegmentTreeNode.inOrd(root);
  }

  public SegmentTreeNode build(int[] A) {
    if (A == null || A.length == 0) {
      return null;
    }
    //int start = 0, end = A.length;
    int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
    for (int a : A) {
      min = Math.min(min, a);
      max = Math.max(max, a);
    }

    return helpBuild(A, min, max);
  }

  private SegmentTreeNode helpBuild(int[] A, int start, int end) {
    if (start > end) {
      return null;
    }
    if (start == end) {
      int count = 0;
      for (int i : A) {
        if (i == start) {
          count ++;
        }
      }
      return new SegmentTreeNode(start, end, count);
    }

    int mid = start + (end - start) / 2;
    SegmentTreeNode root = new SegmentTreeNode(start, end, -1);
    SegmentTreeNode left = helpBuild(A, start, mid);
    SegmentTreeNode right = helpBuild(A, mid+1, end);

    // bottom-up update
    root.left = left;
    root.right = right;
    root.count = left.count + right.count;
    return root;
  }

  public int query(SegmentTreeNode root, int start, int end) {
    // write your code here
    if (root == null) {
      return 0; //-1;
    }
    if (start <= root.start && root.end <= end) {
      return root.count;
    }
    //if (root.start == start && root.end == end) {
    //  return root.count;
    //}

    int mid = root.start + (root.end - root.start) / 2;
    int countL = 0, countR = 0;
    if (start <= mid) {
      if (end <= mid) {
        countL = query(root.left, start, end);
      }
      else {
        countL = query(root.left, start, mid);
      }
    }
    //else {  Wrong again!!!!!
    if (end > mid) {
      if (start >= mid+1) {
        countR = query(root.right, start, end);
      }
      else {
        //countR = query(root.right, mid, end);
        countR = query(root.right, mid+1, end);  // should follow the same way i build the Tree, so mid+1!
      }
    }
    return countL + countR;
  }

  static class SegmentTreeNode {
    int start, end, count;
    SegmentTreeNode left, right;
    SegmentTreeNode(int s, int e, int c) {
      this.start = s;
      this.end = e;
      this.count = c;
      this.left = this.right = null;
    }

    public static void inOrd(SegmentTreeNode root) {
      if (root == null) {
        return;
      }

      inOrd(root.left);
      System.out.println(root.start + " " + root.end + ": " + root.count);
      inOrd(root.right);
    }
  }
}
