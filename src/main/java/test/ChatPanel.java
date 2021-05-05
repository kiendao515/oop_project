package test;


import entity.FileReader;
import entity.Point;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.ui.swing_viewer.SwingViewer;
import org.graphstream.ui.swing_viewer.ViewPanel;
import org.graphstream.ui.view.Viewer;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import javax.swing.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author admin
 */
public class ChatPanel extends javax.swing.JFrame {

    /**
     * Creates new form ChatPanel
     *
     */
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


    List<Integer> nodeUsed= new ArrayList<>();
    List<Integer> suggest= new ArrayList<>();


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
    static Scanner sc;
    static void OnTheWay2(){
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
         jTextField3.setText(String.valueOf(Suggest));


        System.out.println("Your choice: ");
            CurrentNode= Integer.parseInt(getNumberNode);
            boolean nice= Suggest.contains(CurrentNode);
            if(nice){
                System.out.println("Nice choice!!!");
            }
            else System.out.println("Bad choice, please choose another one: ");


    }

    static void OnTheWay() throws java.lang.IndexOutOfBoundsException,java.lang.NumberFormatException{
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
       // jTextField3.setText(String.valueOf(Save));


        System.out.println("Your choice: ");


        while(true){ //int choice;
            if(jTextField2.getText()!=null){
                jTextField2.setText(Suggest.get(0).toString());
//            CurrentNode= Suggest.get(0);
            }
            CurrentNode= Integer.parseInt(jTextField2.getText());
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
    public ChatPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        button5 = new JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField2KeyPressed(evt);
                getRootPane().setDefaultButton(button5);
            }
        });
        jTextField3 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        button1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        button1.setLabel("Browse file");
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        button2.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        button2.setLabel("Display graph");
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });

        button3.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        button3.setLabel("Finding routes");
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });

        button4.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        button4.setLabel("Exit system");
        button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button4ActionPerformed(evt);
                getRootPane().setDefaultButton(button4);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Possible routes");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Suggestion node");

        button5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        button5.setLabel("Input node ");
        button5.setName(""); // NOI18N
        button5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button5ActionPerformed(evt);
                getRootPane().setDefaultButton(button5);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 890, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 532, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Display graph ", jPanel1);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 890, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 532, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Finding routes", jPanel2);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 890, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 532, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Temp", jPanel3);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Number of routes");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(79, 79, 79)
                                                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(90, 90, 90)
                                                .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(44, 44, 44)
                                                .addComponent(jLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jTabbedPane1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                .addComponent(button5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                                        .addGap(2, 2, 2)
                                                                        .addComponent(jLabel2))))
                                                .addGap(24, 24, 24))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                .addComponent(jLabel3)
                                                                .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(21, 21, 21))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(37, 37, 37)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(jLabel1)
                                                        .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(45, 45, 45))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(22, 22, 22)
                                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(43, 43, 43)
                                                .addComponent(jLabel2)
                                                .addGap(18, 18, 18)
                                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(28, 28, 28)
                                                .addComponent(jLabel3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(177, 177, 177)
                                                .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(46, 46, 46))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(9, 9, 9)
                                                .addComponent(jTabbedPane1))))
        );

        pack();
    }// </editor-fold>

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        JFileChooser chooser= new JFileChooser();
        chooser.setCurrentDirectory(new File("C:\\Users\\admin\\Dropbox\\project_inputtest"));
        chooser.showOpenDialog(null);
        File file=chooser.getSelectedFile();
        filename= file.getAbsolutePath();

    }

    static String filename;


    // display graph
    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        jPanel1.removeAll();
        jTabbedPane1.setSelectedIndex(0);
        FileReader reader= new FileReader();
        System.setProperty("org.graphstream.ui","swing");//
        System.setProperty("org.graphstream.ui", "org.graphstream.ui.swing.util.Display");
        org.graphstream.graph.Graph graph = new MultiGraph("main graph");
        graph.setStrict(false);
        graph.setAutoCreate(true);

        for(int i= 1; i< reader.MAXN; i++)
            reader.point[i]= new Point();

        try {
            File myObj = new File(filename);
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

        // finding routes
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
        String styleSheet = "graph { padding: 20px; stroke-width: 0px; }"
                + "node:selected { fill-color: red;  fill-mode: plain; }"
                + "node:clicked  { fill-color: blue; fill-mode: plain; }"
                + "node.marked        { fill-color: green, yellow, purple; fill-mode: dyn-plain; }";
        graph.setAttribute(styleSheet);
//        graph.display(true);
        graph.setAttribute("ui.screenshot", "C:\\Users\\admin\\IdeaProjects\\OopsBigAssignment\\output.png");

        SwingViewer viewer = new SwingViewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
        jPanel1.setLayout(new GridLayout());
        ViewPanel viewPanel = (ViewPanel) viewer.addDefaultView(false);
        jPanel1.add(viewPanel);
        jPanel1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        viewer.enableAutoLayout();

//        jLayeredPane1.add(jPanel1);
//        pack();
//        setLocationRelativeTo(null);
        setVisible(true);
    }


    // insert node
    private void button5ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        jPanel3.removeAll();
        jTabbedPane1.setSelectedIndex(2);
        FileReader reader= new FileReader();
        System.setProperty("org.graphstream.ui","swing");//
        System.setProperty("org.graphstream.ui", "org.graphstream.ui.swing.util.Display");
        org.graphstream.graph.Graph graph = new MultiGraph("main graph");
        graph.setStrict(false);
        graph.setAutoCreate(true);

        for(int i= 1; i< reader.MAXN; i++)
            reader.point[i]= new Point();

        try {
            File myObj = new File(filename);
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

        // finding routes
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
        String styleSheet = "graph { padding: 20px; stroke-width: 0px; }"
                + "node:selected { fill-color: red;  fill-mode: plain; }"
                + "node:clicked  { fill-color: blue; fill-mode: plain; }"
                + "node.marked        { fill-color: green, yellow, purple; fill-mode: dyn-plain; }";
        graph.setAttribute(styleSheet);
//        graph.display(true);
        SwingViewer viewer = new SwingViewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
        jPanel3.setLayout(new GridLayout());
        ViewPanel viewPanel = (ViewPanel) viewer.addDefaultView(false);
        jPanel3.add(viewPanel);
        jPanel3.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        viewer.enableAutoLayout();
        setVisible(true);

        Node node = graph.getNode(getNumberNode);
        System.out.println(getNumberNode);
//        for(int i=0;i<Path.size();i++){
//            for(int j=0;j<Path.get(i).size();j++){
//                if(node.equals(String.valueOf(Path.get(i).get(j)))){
//                    nodeUsed.add(Integer.parseInt(getNumberNode));
//                }
//            }
//        }

        for (int index = 1; index < MAXN; index++)
            point[index] = new Point();

        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                convert(data);
                // System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        trace[1]= -1;
        DFS(1);
        System.out.println(Path);

        //
//        Save.add(Integer.parseInt(getNumberNode));
//        System.out.println("I have some suggestions for your next node: ");
//
//        int Left, Right, left= -1, right= Path.size()- 1;
//        while(right- left> 1){
//            int middle= (right+ left)/ 2;
//            if(Comparable(middle)) left= middle;
//            else right= middle;
//        }
//        Left= right;
//        left= 0;
//        right= Path.size();
//        while(right- left> 1){
//            int middle= (right+ left)/ 2;
//            if(ReComparable(middle)) right= middle;
//            else left= middle;
//        }
//        Right= right;
//
//        suggest.clear();
//        int Pre= 0;
//        for(int index= Left; index< Right; index++)
//        {
//            paths= Path.get(index);
//            //if(Suggest.get(Suggest.size()- 1)!= paths.get(Save.size()))
//            if(paths.get(Save.size())!= Pre)
//            {
//                suggest.add(paths.get(Save.size()));
//                Pre= paths.get(Save.size());
//            }
//        }
//        System.out.println(suggest);





        //Path

        nodeUsed.add(Integer.parseInt(getNumberNode));
        jTextField3.setText(String.valueOf(Suggest));

        for(int i=0;i<nodeUsed.size();i++){
            System.out.println(nodeUsed.get(i));
            Node node1= graph.getNode(String.valueOf(nodeUsed.get(i)));
            node1.setAttribute("ui.style", "stroke-mode: plain;shape:circle;fill-color: rgb(204, 204, 255);size: 20px; text-alignment: center;");
        }

    }
    protected static String styleSheet = "graph { padding: 20px; stroke-width: 0px; }"
            + "node:selected { fill-color: red;  fill-mode: plain; }"
            + "node:clicked  { fill-color: blue; fill-mode: plain; }"
            + "node.marked        { fill-color: green, yellow, purple; fill-mode: dyn-plain; }";


    // find routes
    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        jPanel2.removeAll();
         jTextArea1.setText(null);
        jTextField1.setText(null);
        jTextField2.setText(null);
        //jTextField3.setText("");

        jTabbedPane1.setSelectedIndex(1);
        jPanel2.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        FileReader reader= new FileReader();
        System.setProperty("org.graphstream.ui","swing");//
        System.setProperty("org.graphstream.ui", "org.graphstream.ui.swing.util.Display");
        org.graphstream.graph.Graph graph = new MultiGraph("main graph");
        graph.setStrict(false);
        graph.setAutoCreate(true);

        for(int i= 1; i< reader.MAXN; i++)
            reader.point[i]= new Point();

        try {
            File myObj = new File(filename);
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
//        graph.display(true);
        //graph.setAttribute("ui.screenshot", "C:\\Users\\admin\\IdeaProjects\\OopsBigAssignment\\output.png");

        SwingViewer viewer = new SwingViewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
        jPanel2.setLayout(new GridLayout());
        ViewPanel viewPanel = (ViewPanel) viewer.addDefaultView(false);
        jPanel2.add(viewPanel);
//        jPanel2.setBorder(BorderFactory.createLineBorder(Color.red, 5));
        viewer.enableAutoLayout();
        setResizable(false);

        for (int index = 1; index < MAXN; index++)
            point[index] = new Point();

        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                convert(data);
                // System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        trace[1]= -1;
        DFS(1);

        // number of routes
        jTextField1.setText(String.valueOf(Path.size()));

        // possible routes
        String temp="";
        for(int i=0;i< Path.size();i++){
            //temp+=Path.get(i)+"\n";
            for(int j=0;j<Path.get(i).size()-1;j++){
                temp+=Path.get(i).get(j)+" -> ";
            }
            temp+=Path.get(i).get(Path.get(i).size()-1);
            temp+="\n";
        }
        jTextArea1.setText(temp);

        graph.getNode("1").setAttribute("ui.style","stroke-mode:" +
                " plain;shape:circle;fill-color: rgb(204, 204, 255);size: 20px; text-alignment: center;");
        System.out.println("You now are in node 1!");
        while (true) {
            OnTheWay();
           // jTextField3.setText(String.valueOf(Suggest));
            Node node= graph.getNode(String.valueOf(CurrentNode));
            node.setAttribute("ui.style", "stroke-mode: plain;shape:circle;fill-color: rgb(204, 204, 255);size: 20px; text-alignment: center;");
            //graph.setAttribute("ui.screenshot", "C:\\Users\\admin\\IdeaProjects\\OopsBigAssignment\\output.png");
            System.out.println(Save);
            if (CurrentNode == NumberNode)
                break;
        }

    }

    private void button4ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
//        jPanel1.removeAll();
//        jPanel2.removeAll();
//        jTextArea1.setText(null);
//        jTextField1.setText(null);
//        jTextField2.setText(null);
//        jTextField3.setText("");
    }

    private void textField1KeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
    }
    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }
    static String getNumberNode;
    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
            getNumberNode=jTextField2.getText();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ChatPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChatPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChatPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChatPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChatPanel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private JButton button1;
    private  JButton button2;
    private JButton button3;
    private JButton button4;
    private javax.swing.JButton button5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    public static  javax.swing.JTextArea jTextArea1;
    public static  javax.swing.JTextField jTextField1;
    public static  javax.swing.JTextField jTextField2;
    public static javax.swing.JTextField jTextField3;
    // End of variables declaration
}

