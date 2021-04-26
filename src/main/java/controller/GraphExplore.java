package controller;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swing_viewer.SwingViewer;
import org.graphstream.ui.swing_viewer.ViewPanel;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;
import service_impl.GraphImpl;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

/**
 *
 * @author admin
 */
public class GraphExplore extends javax.swing.JFrame {


    public static void main(String args[]) {
//        new GraphExplore();
        service.Graph graph= new GraphImpl();
        graph.display("C:\\Users\\admin\\Dropbox\\project_inputtest\\input.txt");
    }

    public GraphExplore() {
        System.setProperty("org.graphstream.ui","swing");//
        System.setProperty("org.graphstream.ui", "org.graphstream.ui.swing.util.Display");
        Graph graph = new SingleGraph("tutorial 1");

        graph.setAttribute("ui.stylesheet", styleSheet);
        graph.setAutoCreate(true);
        graph.setStrict(false);
        graph.display();

        graph.addEdge("AB", "A", "B");
        graph.addEdge("BC", "B", "C");
        graph.addEdge("CA", "C", "A");
        graph.addEdge("AD", "A", "D");
        graph.addEdge("DE", "D", "E");
        graph.addEdge("DF", "D", "F");
        graph.addEdge("EF", "E", "F");

        for (Node node : graph) {
            node.setAttribute("ui.label", node.getId());
        }

        explore(graph.getNode("A"));
    }

    public void explore(Node source) {
        Iterator<? extends Node> k = source.getBreadthFirstIterator();

        while (k.hasNext()) {
            Node next = k.next();
            next.setAttribute("ui.class", "marked");
            sleep();
        }
    }

    protected void sleep() {
        try { Thread.sleep(1000); } catch (Exception e) {}
    }

    protected String styleSheet =
            "node {" +
                    "	fill-color: black;" +
                    "}" +
                    "node.marked {" +
                    "	fill-color: red;" +
                    "}";
}
