package entity;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class FileReader {
    public static int NumberNode= 0;
    public static final int MAXN= 10000005;
    public static Point point[]= new Point[MAXN];
    public static int trace[]= new int[MAXN];

    public static void convert(String s) {
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
                point[next].getList().add(node);
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
        for(Integer node: point[index].getList())
        {
            if(trace[node]!= 0) continue;
            trace[node]= index;
            DFS(node);
            trace[node]= 0;
        }
    }
}
