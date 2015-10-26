package Easy;

import edu.princeton.cs.algs4.StdOut;

import java.util.*;

/**
 * http://www.lintcode.com/en/problem/find-the-connected-component-in-the-undirected-graph/
 *
 * Created this class in Easy at 6:18 PM, 10/22/2015.
 */
public class ConnectedSet {
    public static void main(String[] args) {
        ConnectedSet cs = new ConnectedSet();
        cs.test();

        int[] test = new int[]{-15,4,-13,0,-8,-2,-1,8,3,-6,2,-12,-7,9,1,-11,13,10,-4,6,14,-3,-10,11,5,-14,-5};
        Arrays.sort(test);
        System.out.println(Arrays.toString(test));
    }

    public void test() {
        UndirectedGraphNode ugA = new UndirectedGraphNode(1);
        UndirectedGraphNode ugB = new UndirectedGraphNode(2);
        UndirectedGraphNode ugC = new UndirectedGraphNode(3);
        UndirectedGraphNode ugD = new UndirectedGraphNode(4);
        UndirectedGraphNode ugE = new UndirectedGraphNode(5);
        UndirectedGraphNode ugF = new UndirectedGraphNode(6);
        UndirectedGraphNode ugG = new UndirectedGraphNode(7);

        ugA.addNeighbor(ugB);
        ugA.addNeighbor(ugD);
        ugB.addNeighbor(ugD);
        ugC.addNeighbor(ugE);

        ugD.addNeighbor(ugF);
        ugF.addNeighbor(ugG);

//        ugA.printNeighbor();
//        ugB.printNeighbor();
//        ugC.printNeighbor();
//        ugD.printNeighbor();
//        ugE.printNeighbor();

        ArrayList<UndirectedGraphNode> nodes = new ArrayList<>();
        nodes.add(ugA);
        nodes.add(ugB);
        nodes.add(ugC);
        nodes.add(ugD);
        nodes.add(ugE);

        for (List<Integer> list : connectedSet(nodes)) {
            StdOut.print(list);
        }
    }

    /**
     * @param nodes a array of Undirected graph node
     * @return a connected set of a Undirected graph
     */
    public List<List<Integer>> connectedSet1(ArrayList<UndirectedGraphNode> nodes) {
        // Write your code here
        if (nodes == null || nodes.size() == 0) {
            return null;
        }

        Queue<UndirectedGraphNode> bfsQ = new LinkedList<>();
        Set<UndirectedGraphNode> visited = new HashSet<>();
        List<List<Integer>> result = new ArrayList<>();

//        int idx = 0;
//        UndirectedGraphNode root = nodes.remove(idx);
//        bfsQ.offer(root);
//        visited.add(root);

        for (int idx = 0; idx < nodes.size(); ++idx) {
            if (visited.contains(nodes.get(idx))) {
                continue;
            }
            UndirectedGraphNode root = nodes.get(idx);
            bfsQ.offer(root);
            visited.add(root);
            List<Integer> cc = new ArrayList<>();

            while (!bfsQ.isEmpty()) {
                UndirectedGraphNode no = bfsQ.poll();
                cc.add(no.label);
                int size = no.neighbors.size();
                for (int i = 0; i < size; ++i) {
                    if (!visited.contains(no.neighbors.get(i))) {
//                    cc.add(no.neighbors.get(i));      // cc result
                        visited.add(no.neighbors.get(i)); // visited set
                        bfsQ.offer(no.neighbors.get(i));    // bfsQ offer
                    }
                }
            }

            result.add(cc);
        }

        return result;
    }



    public List<List<Integer>> connectedSet(ArrayList<UndirectedGraphNode> nodes) {
        // Write your code here
        if (nodes == null || nodes.size() == 0) {
            return null;
        }

        Set<UndirectedGraphNode> visted = new HashSet<>();
        List<List<Integer>> result = new ArrayList<>();
        for (UndirectedGraphNode node : nodes) {
            bfs(node, result, visted);
        }

        return result;
    }

    private void bfs(UndirectedGraphNode root, List<List<Integer>> result, Set<UndirectedGraphNode> visited) {
        if (visited.contains(root)) {
            return;
        }

        Queue<UndirectedGraphNode> bfsQ = new LinkedList<>();
        bfsQ.offer(root);
        visited.add(root);

        List<Integer> list = new ArrayList<>();

        while (!bfsQ.isEmpty()) {
            UndirectedGraphNode ug = bfsQ.poll();
            list.add(ug.label);

            for (UndirectedGraphNode neig : ug.neighbors) {
                if (visited.contains(neig)) {
                    continue;
                }
                bfsQ.offer(neig);
//                list.add(neig.label);
                visited.add(neig);
            }
        }

        Collections.sort(list);
        result.add(list);
    }

    /**
     * Definition for Undirected graph.
     */
    private class UndirectedGraphNode {
        int label;
        ArrayList<UndirectedGraphNode> neighbors;
        UndirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<>();
        }

        void addNeighbor(UndirectedGraphNode node) {
            this.neighbors.add(node);
            node.neighbors.add(this);
        }

        void printNeighbor() {
            UndirectedGraphNode root = this;
            System.out.print(root.label + ": ");
            for (UndirectedGraphNode nei : root.neighbors) {
                System.out.print(nei.label + " ");
            }
            System.out.println();
        }
    }

}
