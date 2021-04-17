package service_impl;


import entity.Point;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import service.ReadFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import entity.Filee;
public class ReadFileImpl implements ReadFile {
    Filee file= new Filee();
    public void convert(String s) {
        int index= 0, node, next;
        String string= "";
        while(s.charAt(index)!= ' ')
            string+= s.charAt(index++);
        node= Integer.parseInt(string);
        file.setNumberNode(Math.max(file.getNumberNode(), node));
        string= "";
        ++index;
        //point[node]= new Point(node);

        while(index<= s.length()){
            if(index== s.length()|| s.charAt(index)== ' '){
                next= Integer.parseInt(string);
                //graph.addEdge(node+""+next,String.valueOf(node),string);
                file.setNumberNode(Math.max(file.getNumberNode(), next));
                file.point[next].getList().add(node);
                ++index;
                string= "";
                continue;
            }
            string+= s.charAt(index++);
        }
    }

    public void print(int index)
    {
        while(true){
            System.out.print(index+ " ");
            index=file.trace[index];
            if(index== file.getNumberNode()) break;
        }
    }

    public void DFS(int index)
    {
        if(index== 1){
            print(1);
            return;
        }
        for(Integer node: file.point[index].getList())
        {
            if(file.trace[node]!= 0) continue;
            file.trace[node]= index;
            DFS(node);
            file.trace[node]= 0;
        }
    }

public static void main(String[] args) {
        ReadFile readFile= new ReadFileImpl();
    System.setProperty("org.graphstream.ui","swing");//
    Graph graph = new SingleGraph("Tutorial 1");
    graph.setStrict(false);
    graph.setAutoCreate(true);

    for(int i= 1; i< ((ReadFileImpl) readFile).file.getMAXN(); i++)
        ((ReadFileImpl) readFile).file.point[i]= new Point();

    try {
        File myObj = new File("input.txt");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            readFile.convert(data);
            //System.out.println(data);
        }
        myReader.close();
    } catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
    }
    readFile.print(((ReadFileImpl) readFile).file.getNumberNode());

//        graph.addNode(String.valueOf(NumberNode));
    for(int index= 1; index<= ((ReadFileImpl) readFile).file.getMAXN(); index++){
        for (Integer node : ((ReadFileImpl) readFile).file.point[index].getList()) {
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
    Node node= graph.getNode(String.valueOf(((ReadFileImpl) readFile).file.getNumberNode()));
    node.setAttribute("ui.style", "stroke-mode: plain;shape:circle;fill-color: yellow;size: 20px; text-alignment: center;");
    node.setAttribute("ui.label",String.valueOf(((ReadFileImpl) readFile).file.getNumberNode()));
    graph.display();
    graph.setAttribute("ui.screenshot", "C:\\Users\\admin\\IdeaProjects\\OopsBigAssignment\\output.png");
    readFile.DFS(((ReadFileImpl) readFile).file.getNumberNode());
}

}
