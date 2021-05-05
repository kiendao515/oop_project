package controller;

import entity.FileReader;
import entity.Point;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.ui.swing_viewer.SwingViewer;
import org.graphstream.ui.swing_viewer.ViewPanel;
import org.graphstream.ui.view.Viewer;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FindRoute extends JFrame {
    public static void main(String[] args)  {
       new FindRoute().display();
    }
    void FindingRoute(){

    }
    JFrame frame;
    JPanel jPanel;
    JButton button;
    public void display(){
        System.out.println("clicked");
        frame= new JFrame("Graph stream project");
        frame.setLayout(new BorderLayout());

        jPanel = new JPanel(new BorderLayout());
        jPanel.setBackground(Color.LIGHT_GRAY);
        jPanel.setPreferredSize(new Dimension(600,400));
        frame.add(jPanel,BorderLayout.WEST);
        frame.setSize(800,600);

        button= new JButton("Display graph");
        button.setBounds(700,100,100,40);
        button.addActionListener(e -> {
            System.out.println("clicked");
            displayGraph();

        });
        frame.add(button);

        frame.setVisible(true);
    }
    void displayGraph(){
//        frame= new JFrame("Graph stream project");
//        frame= new JFrame("Graph stream project");
//        frame.setLayout(new BorderLayout());
//
//        jPanel = new JPanel(new BorderLayout());
//        jPanel.setBackground(Color.LIGHT_GRAY);
//        jPanel.setPreferredSize(new Dimension(600,400));
//
//        frame.setSize(800,600);
//
//        button= new JButton("Display graph");
//        button.setBounds(700,100,100,40);
//        button.addActionListener(e -> {
//            System.out.println("clicked");
//            displayGraph();
//
//        });
//        frame.add(button);

        frame.setVisible(true);
        FileReader reader= new FileReader();
        System.setProperty("org.graphstream.ui","swing");//
        System.setProperty("org.graphstream.ui", "org.graphstream.ui.swing.util.Display");
        org.graphstream.graph.Graph graph = new MultiGraph("main graph");
        graph.setStrict(false);
        graph.setAutoCreate(true);

        for(int i= 1; i< reader.MAXN; i++)
            reader.point[i]= new Point();

        try {
            File myObj = new File("input.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                reader.convert(data);
                //System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e2) {
            System.out.println("An error occurred.");
            e2.printStackTrace();
        }

//        graph.addNode(String.valueOf(NumberNode));

        // display graph
        for(int index= 1; index<= reader.NumberNode; index++){
            Node e1;
            graph.addNode(String.valueOf(index));
            e1=graph.getNode(String.valueOf(index));
            e1.setAttribute("ui.style", "stroke-mode: plain;shape:circle;fill-color: yellow;size: 20px; text-alignment: center;");
            e1.setAttribute("ui.label", String.valueOf(index));
            e1.setAttribute("ui.class","marked");
            for (Integer node : reader.point[index].getList()) {
                Edge edge;
                graph.addNode(String.valueOf(node));
                e1=graph.getNode(String.valueOf(node));
                e1.setAttribute("ui.style", "stroke-mode: plain;shape: circle;fill-color: yellow;size: 20px; text-alignment: center;");
                e1.setAttribute("ui.label", String.valueOf(node));
                graph.addEdge(index+""+node,String.valueOf(node),String.valueOf(index),true);
                if(graph.getEdge(index+""+node)!=null){
                    edge= graph.getEdge(index+""+node);
                    edge.setAttribute("ui.style","arrow-shape: arrow;");
                }
            }
        }
        graph.setAttribute("ui.antialias");
        graph.setAttribute("ui.screenshot", "C:\\Users\\admin\\IdeaProjects\\OopsBigAssignment\\output.png");

        SwingViewer viewer = new SwingViewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
        ViewPanel viewPanel = (ViewPanel) viewer.addDefaultView(false);
        viewer.enableAutoLayout();
        jPanel.add(viewPanel);

        frame.add(jPanel,BorderLayout.WEST);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        graph.setAttribute("ui.screenshot", "C:\\Users\\admin\\IdeaProjects\\OopsBigAssignment\\output.png");
    }



}
