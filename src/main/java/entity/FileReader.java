package entity;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.stream.file.FileSinkImages;
import org.graphstream.stream.file.FileSourceDGS;
import org.graphstream.stream.file.images.Resolution;
import org.graphstream.stream.file.images.Resolutions;
import org.graphstream.ui.spriteManager.Sprite;
import org.graphstream.ui.spriteManager.SpriteManager;
import org.graphstream.ui.view.camera.Camera;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class FileReader {
    static int NumberNode= 0;
    static final int MAXN= 10000005;
    //private static Object point;
    static Point point[]= new Point[MAXN];
    static int trace[]= new int[MAXN];

    static void convert(String s)
    {

        int index= 0, node, next;
        String string= "";
        while(s.charAt(index)!= ' ')
            string+= s.charAt(index++);
        node= Integer.parseInt(string);
        NumberNode= Math.max(NumberNode, node);
        string= "";
        ++index;
        //point[node]= new Point(node);

        while(index<= s.length()){
            if(index== s.length()|| s.charAt(index)== ' '){
                next= Integer.parseInt(string);
                //graph.addEdge(node+""+next,String.valueOf(node),string);
                NumberNode= Math.max(NumberNode, next);
                point[next].list.add(node);
                ++index;
                string= "";
                continue;
            }
            string+= s.charAt(index++);
        }
        //graph.display();
    }

    static void Print(int index)
    {
        while(true){
            System.out.print(index+ " ");
            index= trace[index];
            if(index== NumberNode) break;
        }
        System.out.println(NumberNode);
    }

    static void DFS(int index)
    {
        if(index== 1){
            Print(1);
            return;
        }

        for(Integer node: point[index].list)
        {
            if(trace[node]!= 0) continue;
            trace[node]= index;
            DFS(node);
            trace[node]= 0;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setProperty("org.graphstream.ui","swing");//
        Graph graph = new SingleGraph("Tutorial 1");
        graph.setStrict(false);
        graph.setAutoCreate( true );
// ...
        SpriteManager sprite = new SpriteManager(graph);

        for(int i= 1; i< MAXN; i++)
            point[i]= new Point();

        try {
            File myObj = new File("input.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                convert(data);
                //System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

//        graph.addNode(String.valueOf(NumberNode));
        for(int index= 1; index<= NumberNode; index++)
        {
            for (Integer node : point[index].list) {
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
        Node node= graph.getNode(String.valueOf(NumberNode));
        node.setAttribute("ui.style", "stroke-mode: plain;shape:circle;fill-color: yellow;size: 20px; text-alignment: center;");
        node.setAttribute("ui.label",String.valueOf(NumberNode));
        graph.display();
        graph.setAttribute("ui.screenshot", "C:\\Users\\admin\\IdeaProjects\\OopsBigAssignment\\output.png");
        DFS(NumberNode);
    }
}
