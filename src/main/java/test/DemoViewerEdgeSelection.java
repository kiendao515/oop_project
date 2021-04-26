package test;

import java.util.EnumSet;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.ui.swing_viewer.SwingViewer;
import org.graphstream.ui.swing_viewer.util.DefaultMouseManager;
import org.graphstream.ui.view.ViewerListener;
import org.graphstream.ui.view.ViewerPipe;
import org.graphstream.ui.view.util.InteractiveElement;

/**
 * Test the viewer.
 */
public class DemoViewerEdgeSelection implements ViewerListener {
    public static void main(String args[]) {
        // System.setProperty( "gs.ui.renderer",
        // "org.graphstream.ui.j2dviewer.J2DGraphRenderer" );
        System.setProperty("org.graphstream.ui", "org.graphstream.ui.swing.util.Display");
        new DemoViewerEdgeSelection();
    }

    protected boolean loop = true;

    public DemoViewerEdgeSelection() {
        Graph graph = new MultiGraph("main graph");
        SwingViewer view = (SwingViewer) graph.display(true);
        view.getDefaultView().enableMouseOptions();
       // viewer.getDefaultView().enableMouseOptions();
        view.getDefaultView().setMouseManager(new DefaultMouseManager(EnumSet.of(InteractiveElement.EDGE, InteractiveElement.NODE, InteractiveElement.SPRITE)));
        ViewerPipe pipe = view.newViewerPipe();

        // graph.setAttribute( "ui.quality" );
        graph.setAttribute("ui.antialias");

        pipe.addViewerListener(this);

        for (String nodeId : new String[]{"A", "B", "C","D","E"}) {
            Node node = graph.addNode(nodeId);
            node.setAttribute("ui.label", nodeId);
        }

        graph.addEdge("AB", "A", "B", true);
        graph.addEdge("BC", "B", "C", true);
        graph.addEdge("CA", "C", "A", true);
        graph.addEdge("CD","C","D",true);
        graph.addEdge("AE","A","E",true);

        graph.setAttribute("ui.stylesheet", styleSheet);

        float color = 0;
        float dir = 0.01f;

        while (loop) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            pipe.pump();

            showSelection(graph);
        }

        System.out.printf("Bye bye ...%n");
        System.exit(0);
    }

    protected void showSelection(Graph graph) {
        boolean selection = false;
        StringBuilder sb = new StringBuilder();

        sb.append("[");

        for (Node node : graph) {
            if (node.hasAttribute("ui.selected")) {
                sb.append(String.format(" %s", node.getId()));
                selection = true;
            }
            if (node.hasAttribute("ui.clicked")) {
                System.err.printf("node %s clicked%n", node.getId());
            }
        }

        sb.append(" ]");
        if (selection)
            System.err.printf("selection = %s%n", sb.toString());
    }

    protected static String styleSheet = "graph { padding: 20px; stroke-width: 0px; }"
            + "node:selected { fill-color: red;  fill-mode: plain; }"
            + "node:clicked  { fill-color: blue; fill-mode: plain; }"
            + "edge:selected { fill-color: purple; fill-mode: plain; }"
            + "edge:clicked  { fill-color: orange; fill-mode: plain; }"
            + "node#A        { fill-color: green, yellow, purple; fill-mode: dyn-plain; }";

    public void buttonPushed(String id) {
        Graph graph = new MultiGraph("main graph");
        SwingViewer view = (SwingViewer) graph.display(true);
        view.getDefaultView().setMouseManager(new DefaultMouseManager(EnumSet.of(InteractiveElement.EDGE, InteractiveElement.NODE, InteractiveElement.SPRITE)));
        ViewerPipe pipe = view.newViewerPipe();

        // graph.setAttribute( "ui.quality" );
        graph.setAttribute("ui.antialias");

        pipe.addViewerListener(this);

        for (String nodeId : new String[]{"A", "B", "C","D","E"}) {
            Node node = graph.addNode(nodeId);
            node.setAttribute("ui.label", nodeId);
        }

        graph.addEdge("AB", "A", "B", true);
        graph.addEdge("BC", "B", "C", true);
        graph.addEdge("CA", "C", "A", true);
        graph.addEdge("CD","C","D",true);
        graph.addEdge("AE","A","E",true);

        graph.setAttribute("ui.stylesheet", styleSheet);

        float color = 0;
        float dir = 0.01f;

        while (loop) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            pipe.pump();

            color += dir;

            if (color > 1) {
                color = 1;
                dir = -dir;
            } else if (color < 0) {
                color = 0;
                dir = -dir;
            }

            showSelection(graph);
        }

        System.out.printf("Bye bye ...%n");
        System.exit(0);
        System.out.println("you have clicked");
    }

    public void buttonReleased(String id) {
    }

    public void mouseOver(String id) {
    }

    public void mouseLeft(String id) {
    }

    public void viewClosed(String viewName) {
        loop = false;
    }
}