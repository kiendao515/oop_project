package service_impl;

import entity.FileReader;
import entity.Point;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import service.Graph;
import service.ReadFile;
import org.graphstream.graph.implementations.Graphs;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GraphImpl implements Graph {
    private ReadFile readFile= new ReadFileImpl();
    @Override
    public void display() {
        boolean rs= false;
        FileReader reader= new FileReader();
        System.setProperty("org.graphstream.ui","swing");//
        org.graphstream.graph.Graph graph = new SingleGraph("Tutorial 1");
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
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

//        graph.addNode(String.valueOf(NumberNode));
        for(int index= 1; index<= reader.NumberNode; index++){
                System.out.println(index);
            for (Integer node : reader.point[index].getList()) {
                Node e1;
                Edge edge;
                graph.addNode(String.valueOf(node));
                e1=graph.getNode(String.valueOf(node));
                e1.setAttribute("ui.style", "stroke-mode: plain;shape:circle;fill-color: yellow;size: 20px; text-alignment: center;");
                e1.setAttribute("ui.label", String.valueOf(node));
                graph.addEdge(index+""+node,String.valueOf(node),String.valueOf(index),true);
                if(graph.getEdge(index+""+node)!=null){
                    edge= graph.getEdge(index+""+node);
                    edge.setAttribute("ui.style","arrow-shape: arrow;");
                }
            }
        }
        Node node= graph.getNode(String.valueOf(reader.NumberNode));
        System.out.println(reader.NumberNode);
        node.setAttribute("ui.style", "stroke-mode: plain;shape:circle;fill-color: yellow;size: 20px; text-alignment: center;");
        node.setAttribute("ui.label",String.valueOf(reader.NumberNode));
        graph.display();
        if(graph!=null){
            rs= true;
            graph.setAttribute("ui.screenshot", "C:\\Users\\admin\\IdeaProjects\\OopsBigAssignment\\output.png");
        }

    }

    public static void main(String[] args) {
        Graph graph= new GraphImpl();
        graph.display();
    }
}
