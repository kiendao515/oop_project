package service_impl;

import entity.FileReader;
import entity.Point;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.ui.swing_viewer.DefaultView;
import org.graphstream.ui.swing_viewer.SwingViewer;
import org.graphstream.ui.swing_viewer.ViewPanel;
import org.graphstream.ui.view.Viewer;
import service.Graph;
import service.ReadFile;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Scanner;

public class GraphImpl implements Graph {
    static int NumberNode = 0;
    static int MAXN = 10000005;
    // private static Object point;
    static Point point[] = new Point[MAXN];
    static int trace[] = new int[MAXN];
    static int NumberPath = 0;
    static int CurrentNode = 1;
    static ArrayList<ArrayList<Integer>> Path = new ArrayList<ArrayList<Integer>>();
    static ArrayList<Integer> Save = new ArrayList<Integer>();
    static ArrayList<Integer> paths = new ArrayList<Integer>();
    static ArrayList<Integer> Suggest = new ArrayList<Integer>();

    static void convert(String s) {
        int index = 0, node, next;
        String string = "";
        while (s.charAt(index) != ' ')
            string += s.charAt(index++);
        node = Integer.parseInt(string);
        NumberNode = Math.max(NumberNode, node);
        string = "";
        ++index;
        // point[node]= new Point(node);

        while (index <= s.length()) {
            if (index == s.length() || s.charAt(index) == ' ') {
                next = Integer.parseInt(string);
                NumberNode = Math.max(NumberNode, next);
                point[node].getList().add(next);
                ++index;
                string = "";
                continue;
            }
            string += s.charAt(index++);
        }
        Collections.sort(point[node].getList());
    }

    static void Print() {
        for (ArrayList<Integer> a : Path) {
            for (Integer b : a)
                System.out.print(b + " ");
            System.out.println();
        }
    }

    static void Trace(int index) {
        ArrayList<Integer> path = new ArrayList<Integer>();
        while (true) {
            // System.out.print(index+ " ");
            path.add(index);
            index = trace[index];
            if (index == 1)
                break;
        }
        // System.out.println(1);
        path.add(1);
        Collections.reverse(path);
        Path.add(path);
    }

    static void DFS(int index) {
        if (index == NumberNode) {
            Trace(NumberNode);
            return;
        }

        for (Integer node : point[index].getList()) {
            if (trace[node] != 0)
                continue;
            trace[node] = index;
            DFS(node);
            trace[node] = 0;
        }
    }

    static boolean Comparable(int middle) {
        paths = Path.get(middle);
        for (int index = 0; index < Save.size(); index++)
        {
            if (Save.get(index) < paths.get(index))
                return false;
            if (Save.get(index) > paths.get(index))
                return true;
        }
        return false;
    }

    static boolean ReComparable(int middle) {
        paths = Path.get(middle);
        for (int index = 0; index < Save.size(); index++)
        {
            if (Save.get(index) > paths.get(index))
                return false;
            if (Save.get(index) < paths.get(index))
                return true;
        }
        return false;
    }

    static void OnTheWay()
    {
        Save.add(CurrentNode);
        System.out.println("I have some suggestions for your next node: ");

        int Left, Right, left= -1, right= Path.size()- 1;
        while(right- left> 1){
            int middle= (right+ left)/ 2;
            if(Comparable(middle)) left= middle;
            else right= middle;
        }
        Left= right;

        left= 0;
        right= Path.size();
        while(right- left> 1){
            int middle= (right+ left)/ 2;
            if(ReComparable(middle)) right= middle;
            else left= middle;
        }
        Right= right;

//        System.out.println(Left+ " "+ Right);

        Suggest.clear();
        int Pre= 0;
        for(int index= Left; index< Right; index++)
        {
            paths= Path.get(index);
            //if(Suggest.get(Suggest.size()- 1)!= paths.get(Save.size()))
            if(paths.get(Save.size())!= Pre)
            {
                Suggest.add(paths.get(Save.size()));
                Pre= paths.get(Save.size());
            }
        }

        System.out.println(Suggest);
        System.out.println("Your choice: ");

        //int choice;
        while(true){
            Scanner scanner = new Scanner(System.in);
            CurrentNode= scanner.nextInt();
            boolean nice= Suggest.contains(CurrentNode);
            if(nice){
                System.out.println("Nice choice!!!");
//                e1.setAttribute("ui.style", "stroke-mode: plain;shape:circle;fill-color: yellow;size: 20px; text-alignment: center;");
                break;
            }
            else System.out.println("Bad choice, please choose another one: ");
        }

        //CurrentNode;
    }


    private ReadFile readFile= new ReadFileImpl();
    JFrame  frame= new JFrame();
    JButton myButton = new JButton("MyButton");

    @Override
    public void display(String pathname) {
        frame.setPreferredSize(new Dimension(600, 600));
        myButton.addActionListener(e -> {
            FileReader reader= new FileReader();
            System.setProperty("org.graphstream.ui","swing");//
            System.setProperty("org.graphstream.ui", "org.graphstream.ui.swing.util.Display");
            org.graphstream.graph.Graph graph = new MultiGraph("main graph");
            graph.setStrict(false);
            graph.setAutoCreate(true);


            for(int i= 1; i< reader.MAXN; i++)
                reader.point[i]= new Point();

            try {
                File myObj = new File(pathname);
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
            graph.setAttribute(styleSheet);
            SwingViewer viewer = new SwingViewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
            DefaultView view = (DefaultView) viewer.addDefaultView(false);   // false indicates "no JFrame".
            view.setPreferredSize(new Dimension(400, 400));
            frame.setLayout(new FlowLayout());
            frame.add(view);
            viewer.enableAutoLayout();
        });
        frame.add(myButton);

//        graph.display(true);


//        ViewPanel viewPanel = (ViewPanel) viewer.addDefaultView(false);
//        viewPanel.enableMouseOptions();
//        panel.add(viewPanel);


//        panel.setBorder(BorderFactory.createLineBorder(Color.blue, 5));
//        frame.add(panel);


        frame.pack();


        frame.setLocationRelativeTo(null);
        frame.setVisible(true);



//        for (int index = 1; index < MAXN; index++)
//            point[index] = new Point();
//
//        try {
//            File myObj = new File(pathname);
//            Scanner myReader = new Scanner(myObj);
//            while (myReader.hasNextLine()) {
//                String data = myReader.nextLine();
//                convert(data);
//                // System.out.println(data);
//            }
//            myReader.close();
//        } catch (FileNotFoundException e) {
//            System.out.println("An error occurred.");
//            e.printStackTrace();
//        }
//
//        trace[1]= -1;
//        DFS(1);
//        // System.out.println(Path);
////        Print();
//            graph.getNode("1").setAttribute("ui.style","stroke-mode:" +
//                    " plain;shape:circle;fill-color: red;size: 20px; text-alignment: center;");
//        System.out.println("You now are in node 1!");
//        while (true) {
//            OnTheWay();
//            Node node= graph.getNode(String.valueOf(CurrentNode));
//            node.setAttribute("ui.style", "stroke-mode: plain;shape:circle;fill-color: red;size: 20px; text-alignment: center;");
//            graph.setAttribute("ui.screenshot", "C:\\Users\\admin\\IdeaProjects\\OopsBigAssignment\\output.png");
//            System.out.println(Save);
//            if (CurrentNode == NumberNode)
//                break;
//        }


    }

    protected static String styleSheet = "graph { padding: 20px; stroke-width: 0px; }"
            + "node:selected { fill-color: red;  fill-mode: plain; }"
            + "node:clicked  { fill-color: blue; fill-mode: plain; }"
            + "node.marked        { fill-color: green, yellow, purple; fill-mode: dyn-plain; }";

    public static void main(String[] args) {
        Graph graph= new GraphImpl();

        graph.display("input.txt");
    }
}
