package controller;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import entity.FileReader;
import entity.Model;
import entity.Point;
import impl.Main;
import impl.ReadInput;

import impl.Simulate;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swing_viewer.SwingViewer;
import org.graphstream.ui.swing_viewer.ViewPanel;
import org.graphstream.ui.view.Viewer;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public class MainFrame extends javax.swing.JFrame {
    static int NumberNode = 0;
    static int MAXN = 10000005;
    static Point point[] = new Point[MAXN];
    static int trace[] = new int[MAXN];
    static List<List<Integer>> Path= new ArrayList<>();
    static List<Integer> Save = new ArrayList<>();
    static List<Integer> paths = new ArrayList<>();
    static  String filename;
    static String s1,s2;
    static org.graphstream.graph.Graph graph = new SingleGraph("main graph");
    public MainFrame() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
//        jMenuBar1 = new javax.swing.JMenuBar();
//        jMenu1 = new javax.swing.JMenu();
//        jMenuItem1 = new javax.swing.JMenuItem();
//        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 960, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 570, Short.MAX_VALUE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTable1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "All of routes"
                }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 961, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("Browse File");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setText("Display Graph");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setText("Edit Graph");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 255, 255));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton4.setText("Lock Graph");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(255, 255, 255));
        jButton5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton5.setText("Finding Routes");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                                .addGap(31, 31, 31)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jButton2)
                                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButton1)
                                .addGap(38, 38, 38)
                                .addComponent(jButton2)
                                .addGap(35, 35, 35)
                                .addComponent(jButton3)
                                .addGap(38, 38, 38)
                                .addComponent(jButton4)
                                .addGap(38, 38, 38)
                                .addComponent(jButton5)
                                .addGap(441, 441, 441))
        );


        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>


    private static void Remove() {
        for(int index= 1; index<= Main.NumberNode; index++)
            for (Integer node : Main.point[index].getList())
                if(graph.getEdge(index+""+node)!=null)
                    graph.removeEdge(String.valueOf(index)+ String.valueOf(node));



        for(int index= 1; index<= Main.NumberNode; index++)
            if(graph.getNode(String.valueOf(index))!= null)
                graph.removeNode(String.valueOf(index));

    }

    //browse file
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:

        Remove();
        JFileChooser chooser= new JFileChooser();
        chooser.setCurrentDirectory(new File("C:\\Users\\admin\\Dropbox\\project_inputtest"));
        chooser.showOpenDialog(null);
        File file=chooser.getSelectedFile();
        filename= file.getAbsolutePath();
        ReadInput readInput= new ReadInput();
        readInput.Read(filename);
    }

    // display graph
    static SwingViewer viewer;
    static FileReader reader;
    static File myObj;
    static String data;
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        jPanel1.removeAll();
        jPanel1.revalidate();
        jPanel1.repaint();
        Remove();
        System.setProperty("org.graphstream.ui","swing");
        graph.setStrict(false);
        graph.setAutoCreate(true);

        //System.out.println(Main.NumberNode);

        for(int index= 1; index<= Main.NumberNode; index++){
            Node e1;
            if(Main.exist[index]){
                graph.addNode(String.valueOf(index));
                e1=graph.getNode(String.valueOf(index));
                e1.setAttribute("ui.style", "stroke-mode: plain;shape:circle;fill-color: yellow;size: 20px; text-alignment: center;");
                e1.setAttribute("ui.label", String.valueOf(index));
                e1.setAttribute("ui.class","marked");
            }
            for (Integer node : Main.point[index].getList()) {
                Edge edge;
                graph.addNode(String.valueOf(node));
                e1=graph.getNode(String.valueOf(node));
                e1.setAttribute("ui.style", "stroke-mode: plain;shape: circle;fill-color: yellow;size: 20px; text-alignment: center;");
                e1.setAttribute("ui.label", String.valueOf(node));
                graph.addEdge(index+""+node,String.valueOf(index),String.valueOf(node),true);
                if(graph.getEdge(index+""+node)!=null){
                    edge= graph.getEdge(index+""+node);
                    edge.setAttribute("ui.style","arrow-shape: arrow;fill-color :#a86b32;");
                }
            }
        }

        //System.out.println(Main.point[1].getList());
        graph.setAttribute("ui.antialias");
        String styleSheet = "graph { padding: 20px; stroke-width: 0px; }"
                + "node:selected { fill-color: red;  fill-mode: plain; }"
                + "node:clicked  { fill-color: blue; fill-mode: plain; }"
                + "node.marked        { fill-color: green, yellow, purple; fill-mode: dyn-plain; }";
        graph.setAttribute(styleSheet);
//        graph.display(true);

        viewer = new SwingViewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
        jPanel1.setLayout(new GridLayout());
        ViewPanel viewPanel = (ViewPanel) viewer.addDefaultView(false);
        jPanel1.add(viewPanel);
        jPanel1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        add(jPanel1);
        add(jPanel2);
        add(jPanel3);
        viewer.enableAutoLayout();

        setVisible(true);
    }
    static String temp="";
    static boolean check=false;

    // insert data
    static DefaultTableModel defaultTableModel;
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {
        if(check){
            clear();
        }
        defaultTableModel= new DefaultTableModel();
        int number1=Integer.parseInt(JOptionPane.showInputDialog(null, "Input your first node:"));
        int number2=Integer.parseInt(JOptionPane.showInputDialog(null, "Input your second node:"));
        Main.start=number1;
        Main.end=number2;
        Simulate simulate= new Simulate();
        simulate.FindAllPaths(number1);
        jTable1.setModel(new DefaultTableModel(null,new String[]{"All of routes"}));
        defaultTableModel.addColumn("All of routes:");
        for (int i = 0; i < Simulate.Path.size(); i++) {
            for (int j = 0; j < Simulate.Path.get(i).size(); j++) {
                temp += Simulate.Path.get(i).get(j) + " ";
            }
            defaultTableModel.addRow(new Object[]{temp});
            temp = "";
            check = true;
        }
        jTable1.setModel(defaultTableModel);
    }

    static void addnode(){
        String str = JOptionPane.showInputDialog("Enter node");
        Node e1;
        graph.addNode(String.valueOf(str));
        e1=graph.getNode(str);
        e1.setAttribute("ui.style", "stroke-mode: plain;shape:circle;fill-color: yellow;size: 20px; text-alignment: center;");
        e1.setAttribute("ui.label", str);
        viewer.enableAutoLayout();
        Main.NumberNode= Math.max(Main.NumberNode, Integer.parseInt(str));
        Main.exist[Integer.parseInt(str)]= true;
    }
    static void addEdge() {
        s1= JOptionPane.showInputDialog("Enter first node:");
        s2=JOptionPane.showInputDialog("Enter tail:");
        Edge edge;
        graph.addEdge(s2+s1,s1,s2,true);
        edge =graph.getEdge(s2+s1);
        edge.setAttribute("ui.style","arrow-shape: arrow;fill-color :#a86b32;");
        int u= Integer.parseInt(s1);
        int v= Integer.parseInt(s2);
        Main.point[u].getList().add(v);
    }
    static void removeNode(){
        String node=  JOptionPane.showInputDialog(null,"Input node:");
        graph.removeNode(String.valueOf(node));
        int a= Integer.parseInt(node);
        Main.exist[a]= false;
        Main.point[a].getList().clear();
        while(Main.exist[Main.NumberNode]== false) --Main.NumberNode;
        for(int i= 1; i<= Main.NumberNode; i++){
            for(int index= 0; index< Main.point[i].getList().size(); index++)
                if(Main.point[i].getList().get(index)== a){
                    System.out.println("Remove"+a);
                    Main.point[i].getList().remove(index);
                }
        }

    }
    static void removeEdge(){

        s1= JOptionPane.showInputDialog("Enter first node:");
        s2= JOptionPane.showInputDialog("Enter tail:");
        graph.removeEdge(s2+s1);
        int i= Integer.parseInt(s1);
        for(int index= 0; index< Main.point[i].getList().size(); index++)
            if(Main.point[i].getList().get(index)== Integer.parseInt(s2))
                Main.point[i].getList().remove(index);
    }

    static void editByHand(){
        JTextArea textArea = new JTextArea();
        textArea.setWrapStyleWord(true);
        textArea.setSize(textArea.getPreferredSize().width, 1);
        String temp="";
        try {
            File file = new File(filename);
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                temp+=data+"\n";
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        textArea.setText(temp);
        JOptionPane.showMessageDialog(null, new JScrollPane( textArea), "File edit:",
                JOptionPane.WARNING_MESSAGE);
        System.out.println(filename);

        temp=textArea.getText();
        PrintWriter printWriter=null;
        try {
            printWriter= new PrintWriter(new FileOutputStream(filename));
            printWriter.println(temp.trim());
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        finally {
            printWriter.close();
        }

    }
    static void editAuto(){
        String[] category = new String[4];
        category[0] = "Add Node";
        category[1] = "Remove Node";
        category[2] = "Add Edge";
        category[3]="Remove Edge";

        Object selectedOp = JOptionPane.showInputDialog(null, "Choose your selection", "Your Selection", JOptionPane.QUESTION_MESSAGE, null, category,"Add Node");
        if(selectedOp.equals("Add Node")){
            addnode();
        }else if(selectedOp.equals("Add Edge")){
            addEdge();
        }else if(selectedOp.equals("Remove Node")){
            removeNode();
        }else if(selectedOp.equals("Remove Edge")){
            removeEdge();
        }
    }
    // edit graph
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        String[] category= new String[2];
        category[0]= "Edit by overwritting file";
        category[1]="Edit step by step";
        Object select =JOptionPane.showInputDialog(null, "Choose your selection", "Your selection",JOptionPane.QUESTION_MESSAGE,null,category,category[0]);
        if(select.equals(category[0])){
            editByHand();
        }else if(select.equals(category[1])){
            editAuto();
        }
    }
    static String[] temp2;
    static boolean check2;

    // click to table
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        int x= jTable1.getSelectedRow();
        String y=(String)jTable1.getValueAt(x,0);
        y=y.trim();
       // System.out.println(y);
        temp2= y.split(" ");
        if(check){
            resetRow();
        }
        for(int i=0;i<temp2.length;i++){
            System.out.println("day la tem2:"+temp2[i]);
            Node node=graph.getNode(temp2[i]);
            node.setAttribute("ui.style", "stroke-mode: plain;shape: circle;fill-color: red;size: 20px; text-alignment: center;");
            check=true;
        }
    }

    static void resetRow(){
        if(temp2!=null){
            for(int i=0;i<temp2.length;i++){
                System.out.println("temp2:"+(temp2[i]));
                Node node=graph.getNode(String.valueOf(temp2[i]));
                node.setAttribute("ui.style", "stroke-mode: plain;shape: circle;fill-color: yellow;size: 20px; text-alignment: center;");
                temp2[i]="";
            }
        }
    }

    static void clear(){
        int x;
        if(defaultTableModel.getRowCount()!=0){
            x = defaultTableModel.getRowCount();
            for (int i = x - 1; i >= 0; i--) {
                defaultTableModel.removeRow(i);
            }
        }
        Simulate.Path.removeAll(Simulate.Path);
        System.out.println(Simulate.Path);
    }


    //lock graph
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
      //  viewer.disableAutoLayout();
//        Simulate.Path.removeAll(Simulate.Path);
//        if(temp2!=null){
//            for(int i=0;i<temp2.length;i++){
//                Node node=graph.getNode(temp2[i]);
//                node.setAttribute("ui.style", "stroke-mode: plain;shape: circle;fill-color: yellow;size: 20px; text-alignment: center;");
//            }
//        }
//        Simulate.Path.removeAll(Simulate.Path);
        Remove();
        clear();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    public static  javax.swing.JTable jTable1;
    private JButton jButton5;
    // End of variables declaration
}