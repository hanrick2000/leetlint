package LearnJgraphT;

/**
 * How did Jgraph enable my move of nodes work???
 *
 * Created this class in LearnJgraphT at 10:25 AM, 10/31/2015.
 */
import org.jgraph.JGraph;
import org.jgraph.graph.AttributeMap;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;
import org.jgrapht.DirectedGraph;
import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultListenableGraph;
import org.jgrapht.graph.DirectedMultigraph;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

// resolve ambiguity


/**
 * A demo applet that shows how to use JGraph to visualize JGraphT graphs.
 *
 * @author Barak Naveh
 * @since Aug 3, 2003
 */
public class JGraphAdapterDemo
    extends JApplet
{


  private static final long serialVersionUID = 3256444702936019250L;
  private static final Color DEFAULT_BG_COLOR = Color.decode("#FAFBFF");
  private static final Dimension DEFAULT_SIZE = new Dimension(530, 320);



  //
  private JGraphModelAdapter<String, DefaultEdge> jgAdapter;



  /**
   * An alternative starting point for this demo, to also allow running this
   * applet as an application.
   *
   * @param args ignored.
   */
  public static void main(String [] args)
  {
    JGraphAdapterDemo applet = new JGraphAdapterDemo();
    applet.init();

    JFrame frame = new JFrame();
    frame.getContentPane().add(applet);
    frame.setTitle("JGraphT Adapter to JGraph Demo");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }

  /**
   * {@inheritDoc}
   */
  public void init()
  {
    // create a JGraphT graph
    ListenableGraph<String, DefaultEdge> g =
        new ListenableDirectedMultigraph<String, DefaultEdge>(
            DefaultEdge.class);

    // create a visualization using JGraph, via an adapter
    // Modifications made to the underlying JGraphT graph are reflected to this JGraph model if and
    //  only if the underlying JGraphT graph is a ListenableGraph. If the underlying JGraphT graph is
    //    not ListenableGraph, then this JGraph model represent a snapshot if the graph at the time of its creation.
    jgAdapter = new JGraphModelAdapter<String, DefaultEdge>(g);

    // arm jgraph with jgAdatper, ttt
    JGraph jgraph = new JGraph(jgAdapter);

    adjustDisplaySettings(jgraph);
    getContentPane().add(jgraph);
    resize(DEFAULT_SIZE);

    String v1 = "v1";
    String v2 = "v2";
    String v3 = "v3";
    String v4 = "v4";

    // add some sample data (graph manipulated via JGraphT)
    g.addVertex(v1);
    g.addVertex(v2);
    g.addVertex(v3);
    g.addVertex(v4);

    g.addEdge(v1, v2);
    g.addEdge(v2, v3);
    g.addEdge(v3, v1);
    g.addEdge(v4, v3);

    // position vertices nicely within JGraph component
    positionVertexAt(v1, 130, 40);
    positionVertexAt(v2, 60, 200);
    positionVertexAt(v3, 310, 230);
    positionVertexAt(v4, 380, 70);

    // that's all there is to it!...
  }

  private void adjustDisplaySettings(JGraph jg)
  {
    jg.setPreferredSize(DEFAULT_SIZE);

    Color c = DEFAULT_BG_COLOR;
    String colorStr = null;

    try {
      colorStr = getParameter("bgcolor");
    } catch (Exception e) {
    }

    if (colorStr != null) {
      c = Color.decode(colorStr);
    }

    jg.setBackground(c);
  }

  @SuppressWarnings("unchecked") // FIXME hb 28-nov-05: See FIXME below
  private void positionVertexAt(Object vertex, int x, int y)
  {
    DefaultGraphCell cell = jgAdapter.getVertexCell(vertex);
    AttributeMap attr = cell.getAttributes();
    Rectangle2D bounds = GraphConstants.getBounds(attr);

    Rectangle2D newBounds =
        new Rectangle2D.Double(
            x,
            y,
            bounds.getWidth(),
            bounds.getHeight());

    GraphConstants.setBounds(attr, newBounds);

    // TODO: Clean up generics once JGraph goes generic
    AttributeMap cellAttr = new AttributeMap();
    cellAttr.put(cell, attr);
    jgAdapter.edit(cellAttr, null, null, null);
  }



  /**
   * a listenable directed multigraph that allows loops and parallel edges.
   */
  private static class ListenableDirectedMultigraph<V, E>
      extends DefaultListenableGraph<V, E>
      implements DirectedGraph<V, E>
  {
    private static final long serialVersionUID = 1L;

    ListenableDirectedMultigraph(Class<E> edgeClass)
    {
      super(new DirectedMultigraph<V, E>(edgeClass));
    }
  }
}