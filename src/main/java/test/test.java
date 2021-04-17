package test;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.spriteManager.Sprite;
import org.graphstream.ui.spriteManager.SpriteManager;

public class test {
    public static void main(String[] args) {
            System.setProperty("org.graphstream.ui", "swing");
            SingleGraph graph = new SingleGraph("Use");
            graph.addNode("A");
            graph.addNode("B");
            graph.addNode("C");
            graph.addNode("D");
            graph.addNode("E");
            graph.addNode("F");
            graph.addNode(1+"");
            graph.addNode(2+"");
            graph.addEdge("AB", "A", "B",true);
            graph.addEdge(1+""+2+"", 1+"", 2+"",true);
            graph.addEdge("BC", "B", "C",true);
            graph.addEdge("CA", "C", "A",true);
            graph.addEdge("CD", "C", "D",true);
            graph.addEdge("DF", "D", "F",true);
            graph.addEdge("EF", "E", "F",true);
            graph.addEdge("DE", "D", "E",true);
            Node e1=graph.getNode("A");

            e1.setAttribute("ui.style", "shape:circle;fill-color: yellow;size: 10px; text-alignment: center;");
            e1.setAttribute("ui.label", "1");
            graph.display();

    }
}
