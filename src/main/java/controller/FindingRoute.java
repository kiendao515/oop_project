package controller;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author admin
 */
public class FindingRoute extends javax.swing.JFrame {

    /**
     * Creates new form FindingRoute
     */
    static  javax.swing.GroupLayout jPanel2Layout;
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
    static String filename;
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
                break;
            }
            else System.out.println("Bad choice, please choose another one: ");
        }

        //CurrentNode;
    }
    public FindingRoute() {
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
        jPanel1 = new javax.swing.JPanel();
        label1 = new java.awt.Label();
        jPanel2 = new javax.swing.JPanel();
        button1 = new java.awt.Button();
        button2 = new java.awt.Button();
        textField1 = new java.awt.TextField();
        label2 = new java.awt.Label();
        textField2 = new java.awt.TextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 255, 255));


        label1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        label1.setName(""); // NOI18N
        label1.setText("Finding Routes");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(426, 426, 426)
                                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);

        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 742, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 614, Short.MAX_VALUE)
        );

        button1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        button1.setLabel("Browse file");
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        button2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        button2.setLabel("Finding route");
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });

        label2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        label2.setText("Suggestion");
        textField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textField2KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(button1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(button2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(textField1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(87, 87, 87))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(textField2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addContainerGap())))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(43, 43, 43)
                                                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(91, 91, 91)
                                                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(21, 21, 21)
                                                .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(78, 78, 78)
                                                .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(20, 20, 20)
                                                .addComponent(textField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>

    public final void display(String pathname) {
//        JFrame frame= new JFrame();
//        JPanel panel = new JPanel(new GridLayout()){
//            @Override
//            public Dimension getPreferredSize() {
//                return new Dimension(800, 680);
//            }
//        };

//        panel.setBorder(BorderFactory.createLineBorder(Color.blue, 5));

        jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        add(jPanel2);
        jPanel2.setLayout(jPanel2Layout);

        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 742, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 614, Short.MAX_VALUE)
        );

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
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
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
        String styleSheet = "graph { padding: 20px; stroke-width: 0px; }"
                + "node:selected { fill-color: red;  fill-mode: plain; }"
                + "node:clicked  { fill-color: blue; fill-mode: plain; }"
                + "node.marked        { fill-color: green, yellow, purple; fill-mode: dyn-plain; }";
        graph.setAttribute(styleSheet);
//        graph.display(true);
        graph.setAttribute("ui.screenshot", "C:\\Users\\admin\\IdeaProjects\\OopsBigAssignment\\output.png");



        for (int index = 1; index < MAXN; index++)
            point[index] = new Point();

        try {
            File myObj = new File(pathname);
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
        // System.out.println(Path);
//        Print();
        graph.getNode("1").setAttribute("ui.style","stroke-mode:" +
                " plain;shape:circle;fill-color: red;size: 20px; text-alignment: center;");
        System.out.println("You now are in node 1!");
        SwingViewer viewer = new SwingViewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
        ViewPanel viewPanel = (ViewPanel) viewer.addDefaultView(false);
        viewPanel.enableMouseOptions();
        jPanel2.add(viewPanel);
        viewer.enableAutoLayout();
        jPanel2.setVisible(true);
        add(jPanel2);
//
//        frame.add(panel);
//        frame.setSize(1000,600);
//        frame.pack();
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);

        while (true) {
            OnTheWay();
            Node node= graph.getNode(String.valueOf(CurrentNode));
            node.setAttribute("ui.style", "stroke-mode: plain;shape:circle;fill-color: red;size: 20px; text-alignment: center;");
            graph.setAttribute("ui.screenshot", "C:\\Users\\admin\\IdeaProjects\\OopsBigAssignment\\output.png");
            System.out.println(Save);
            if (CurrentNode == NumberNode)
                break;
        }

    }

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        JFileChooser chooser= new JFileChooser();
        chooser.setCurrentDirectory(new File("C:\\Users\\admin\\Dropbox\\project_inputtest"));
        chooser.showOpenDialog(null);
        File file=chooser.getSelectedFile();
        filename= file.getAbsolutePath();

    }

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                display(filename);
            }
        });

    }
    private void textField2KeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
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
            java.util.logging.Logger.getLogger(FindingRoute.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FindingRoute.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FindingRoute.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FindingRoute.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FindingRoute().setVisible(true);
            }
        });

//        new FindingRoute().display("input.txt");
    }

    // Variables declaration - do not modify
    private java.awt.Button button1;
    private java.awt.Button button2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.TextField textField1;
    private java.awt.TextField textField2;
    private JFrame jFrame;
    // End of variables declaration
}
