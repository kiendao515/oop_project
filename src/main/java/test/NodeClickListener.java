package test;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swing_viewer.SwingViewer;
import org.graphstream.ui.swing_viewer.util.DefaultMouseManager;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.ViewerListener;
import org.graphstream.ui.view.ViewerPipe;
import org.graphstream.ui.view.util.InteractiveElement;

import java.util.EnumSet;

public class NodeClickListener implements ViewerListener {
    protected boolean loop = true;

    public static void main(String args[]) {
        System.setProperty("org.graphstream.ui", "swing");
        new NodeClickListener();
    }
    public NodeClickListener() {
        // We do as usual to display a graph. This
        // connect the graph outputs to the viewer.
        // The viewer is a sink of the graph.
        Graph graph = new SingleGraph("Clicks");
        Viewer viewer = graph.display();

        graph.addNode("a");
        graph.addNode("b");
        graph.addNode("c");
        graph.addEdge("ab","a","b");
        graph.addEdge("bc","c","b");
        graph.setAttribute("ui.stylesheet", styleSheet);

        // The default action when closing the view is to quit
        // the program.
       // viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.HIDE_ONLY);

        // We connect back the viewer to the graph,
        // the graph becomes a sink for the viewer.
        // We also install us as a viewer listener to
        // intercept the graphic events.
        ViewerPipe fromViewer = viewer.newViewerPipe();
        fromViewer.addViewerListener(this);
        fromViewer.addSink(graph);
        viewer.getDefaultView().enableMouseOptions();
        // Then we need a loop to do our work and to wait for events.
        // In this loop we will need to call the
        // pump() method before each use of the graph to copy back events
        // that have already occurred in the viewer thread inside
        // our thread.

        while(loop) {
            fromViewer.pump(); // or fromViewer.blockingPump(); in the nightly builds
            // here your simulation code.
            Node node =graph.getNode("a");
            node.setAttribute("ui.clicked",styleSheet);

            // You do not necessarily need to use a loop, this is only an example.
            // as long as you call pump() before using the graph. pump() is non
            // blocking.  If you only use the loop to look at event, use blockingPump()
            // to avoid 100% CPU usage. The blockingPump() method is only available from
            // the nightly builds.
        }
    }
    protected static String styleSheet = "graph { padding: 20px; stroke-width: 0px; }"
            + "node:selected { fill-color: red;  fill-mode: plain; }"
            + "node:clicked  { fill-color: blue; fill-mode: plain; }";
    public void viewClosed(String id) {
        loop = false;
    }

    public void buttonPushed(String id) {
        System.out.println("Button pushed on node "+id);
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

    public void buttonReleased(String id) {
        System.out.println("Button released on node "+id);
    }

    public void mouseOver(String id) {
        System.out.println("Need the Mouse Options to be activated");
    }

    public void mouseLeft(String id) {
        System.out.println("Need the Mouse Options to be activated");
    }
}