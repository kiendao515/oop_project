//package controller;
//
//
//import entity.FileReader;
//import entity.Point;
//import org.graphstream.graph.Edge;
//import org.graphstream.graph.Node;
//import org.graphstream.graph.implementations.MultiGraph;
//import org.graphstream.graph.implementations.SingleGraph;
//import org.graphstream.ui.swing_viewer.SwingViewer;
//import org.graphstream.ui.swing_viewer.ViewPanel;
//import org.graphstream.ui.view.Viewer;
//
//import java.awt.*;
//import java.awt.event.KeyEvent;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.*;
//import java.util.List;
//import javax.swing.*;
//
///**
// *
// * @author admin kien
// */
//public class GraphGui extends javax.swing.JFrame {
//
//    /**
//     * Creates new form ChatPanel
//     *
//     */
//    int maxNode;
//    static int NumberNode = 0;
//    static int MAXN = 10000005;
//    static Point point[] = new Point[MAXN];
//    static int trace[] = new int[MAXN];
//    static List<List<Integer>> Path= new ArrayList<>();
//    static List<Integer> Save = new ArrayList<>();
//    static List<Integer> paths = new ArrayList<>();
//    String filename;
//    List<Integer> suggest;
//    Scanner myReader;
//    org.graphstream.graph.Graph graph = new SingleGraph("main graph");
//
//    static void convert(String s) {
//        int index = 0, node, next;
//        String string = "";
//        while (s.charAt(index) != ' ')
//            string += s.charAt(index++);
//        node = Integer.parseInt(string);
//        NumberNode = Math.max(NumberNode, node);
//        string = "";
//        ++index;
//        // point[node]= new Point(node);
//
//        while (index <= s.length()) {
//            if (index == s.length() || s.charAt(index) == ' ') {
//                next = Integer.parseInt(string);
//                NumberNode = Math.max(NumberNode, next);
//                point[node].getList().add(next);
//                ++index;
//                string = "";
//                continue;
//            }
//            string += s.charAt(index++);
//        }
//        Collections.sort(point[node].getList());
//    }
//    static void Trace(int index) {
//        ArrayList<Integer> path = new ArrayList<Integer>();
//        while (true) {
//            // System.out.print(index+ " ");
//            path.add(index);
//            index = trace[index];
//            if (index == 1)
//                break;
//        }
//        // System.out.println(1);
//        path.add(1);
//        Collections.reverse(path);
//        Path.add(path);
//    }
//
//    static void DFS(int index) {
//        if (index == NumberNode) {
//            Trace(NumberNode);
//            return;
//        }
//
//        for (Integer node : point[index].getList()) {
//            if (trace[node] != 0)
//                continue;
//            trace[node] = index;
//            DFS(node);
//            trace[node] = 0;
//        }
//    }
//    //
//    static boolean Comparable(int middle) {
//        paths = Path.get(middle);
//        for (int index = 0; index < Save.size(); index++)
//        {
//            if (Save.get(index) < paths.get(index))
//                return false;
//            if (Save.get(index) > paths.get(index))
//                return true;
//        }
//        return false;
//    }
//
//    static boolean ReComparable(int middle) {
//        paths = Path.get(middle);
//        for (int index = 0; index < Save.size(); index++)
//        {
//            if (Save.get(index) > paths.get(index))
//                return false;
//            if (Save.get(index) < paths.get(index))
//                return true;
//        }
//        return false;
//    }
//    public GraphGui() {
//        initComponents();
//    }
//
//    @SuppressWarnings("unchecked")
//    // <editor-fold defaultstate="collapsed" desc="Generated Code">
//    private void initComponents() {
//
//        button1 = new JButton();
//        button2 = new JButton();
//        button3 = new JButton();
//        button4 = new JButton();
//        jLabel1 = new javax.swing.JLabel();
//        jLabel2 = new javax.swing.JLabel();
//        button5 = new JButton();
//        jTabbedPane1 = new javax.swing.JTabbedPane();
//        jPanel1 = new javax.swing.JPanel();
//        jScrollPane1 = new javax.swing.JScrollPane();
//        jTextArea1 = new javax.swing.JTextArea();
//        jLabel3 = new javax.swing.JLabel();
//        jTextField1 = new javax.swing.JTextField();
//        jTextField2 = new javax.swing.JTextField();
//        jTextField3 = new javax.swing.JTextField();
//        button6 = new JButton();
//        button7 = new JButton();
//
//        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
//
//        button1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
//        button1.setLabel("Browse file");
//        button1.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                button1ActionPerformed(evt);
//            }
//        });
//
//        button2.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
//        button2.setLabel("Display graph");
//        button2.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                button2ActionPerformed(evt);
//            }
//        });
//
//        button3.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
//        button3.setLabel("All routes");
//        button3.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                button3ActionPerformed(evt);
//            }
//        });
//
//        button4.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
//        button4.setLabel("Member");
//        button4.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                button4ActionPerformed(evt);
//            }
//        });
//
//        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
//        jLabel1.setText("Possible routes");
//
//        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
//        jLabel2.setText("Suggestion node");
//
//        button5.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
//        button5.setLabel("Input node ");
//        button5.setName(""); // NOI18N
//        button5.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                button5ActionPerformed(evt);
//                getRootPane().setDefaultButton(button5);
//            }
//        });
//
//        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
//
//        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
//        jPanel1.setLayout(jPanel1Layout);
//        jPanel1Layout.setHorizontalGroup(
//                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                        .addGap(0, 888, Short.MAX_VALUE)
//        );
//        jPanel1Layout.setVerticalGroup(
//                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                        .addGap(0, 532, Short.MAX_VALUE)
//        );
//
//        jTabbedPane1.addTab("Display graph ", jPanel1);
//
//        jTextArea1.setColumns(20);
//        jTextArea1.setRows(5);
//        jScrollPane1.setViewportView(jTextArea1);
//
//        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
//        jLabel3.setText("Node traversals");
//
//        jTextField1.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                jTextField1ActionPerformed(evt);
//            }
//        });
//
//        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
//            public void keyPressed(java.awt.event.KeyEvent evt) {
//                jTextField2KeyPressed(evt);
//                getRootPane().setDefaultButton(button5);
//            }
//        });
//        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
//            public void keyPressed(java.awt.event.KeyEvent evt) {
//                jTextField2KeyPressed(evt);
//            }
//        });
//
//        jTextField3.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                jTextField3ActionPerformed(evt);
//            }
//        });
//
//        button6.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
//        button6.setLabel("Take screenshot");
//        button6.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                button6ActionPerformed(evt);
//            }
//        });
//
//        button7.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
//        button7.setLabel("Back");
//        button7.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                button7ActionPerformed(evt);
//            }
//        });
//
//        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
//        getContentPane().setLayout(layout);
//        layout.setHorizontalGroup(
//                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                        .addGroup(layout.createSequentialGroup()
//                                .addContainerGap()
//                                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                .addGap(79, 79, 79)
//                                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                .addGap(90, 90, 90)
//                                .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                .addGap(44, 44, 44)
//                                .addComponent(jLabel1)
//                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
//                                                .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                                .addGap(21, 21, 21))
//                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
//                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
//                                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                                        .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
//                                                .addGap(24, 24, 24))))
//                        .addGroup(layout.createSequentialGroup()
//                                .addComponent(jTabbedPane1)
//                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
//                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                                        .addComponent(button6, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                        .addComponent(jLabel3)
//                                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                        .addGroup(layout.createSequentialGroup()
//                                                .addGap(2, 2, 2)
//                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
//                                                        .addComponent(button7, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                                        .addComponent(jLabel2))))
//                                .addGap(21, 21, 21))
//        );
//        layout.setVerticalGroup(
//                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                        .addGroup(layout.createSequentialGroup()
//                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                                        .addGroup(layout.createSequentialGroup()
//                                                .addGap(37, 37, 37)
//                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
//                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                                                                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                                                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                                                .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
//                                                        .addComponent(jLabel1))
//                                                .addGap(45, 45, 45))
//                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
//                                                .addContainerGap()
//                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
//                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                                        .addGroup(layout.createSequentialGroup()
//                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                                                .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                                .addGap(22, 22, 22)
//                                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                                .addGap(25, 25, 25)
//                                                .addComponent(button7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                                .addGap(30, 30, 30)
//                                                .addComponent(jLabel2)
//                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                                .addGap(24, 24, 24)
//                                                .addComponent(jLabel3)
//                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                                .addGap(34, 34, 34)
//                                                .addComponent(button6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                                .addGap(104, 104, 104)
//                                                .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                                .addGap(46, 46, 46))
//                                        .addGroup(layout.createSequentialGroup()
//                                                .addGap(9, 9, 9)
//                                                .addComponent(jTabbedPane1))))
//        );
//
//        pack();
//    }// </editor-fold>
//
//    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {
//        //TODO add your handling code here:
//        JFileChooser chooser= new JFileChooser();
//        chooser.setCurrentDirectory(new File("C:\\Users\\admin\\Dropbox\\project_inputtest"));
//        chooser.showOpenDialog(null);
//        File file=chooser.getSelectedFile();
//        filename= file.getAbsolutePath();
//
//    }
//
//
//    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {
//        // TODO add your handling code here:
//        jPanel1.removeAll();
//        jPanel1.revalidate();
//        jPanel1.repaint();
//        jTextArea1.setText(null);
//        jTabbedPane1.setSelectedIndex(0);
//        FileReader reader= new FileReader();
//        System.setProperty("org.graphstream.ui","swing");//
//        System.setProperty("org.graphstream.ui", "org.graphstream.ui.swing.util.Display");
//        graph.setStrict(false);
//        graph.setAutoCreate(true);
//
//        for(int i= 1; ; i++)
//            reader.point[i]= new Point();
//
//        try {
//            File myObj = new File(filename);
//            myReader = new Scanner(myObj);
//            while (myReader.hasNextLine()) {
//                String data = myReader.nextLine();
//                reader.convert(data);
//                //System.out.println(data);
//            }
//            myReader.close();
//        } catch (FileNotFoundException e) {
//            System.out.println("An error occurred.");
//            e.printStackTrace();
//        }
//
////        graph.addNode(String.valueOf(NumberNode));
//
//        // finding routes
//        for(int index= 1; index<= reader.NumberNode; index++){
//            Node e1;
//            graph.addNode(String.valueOf(index));
//            e1=graph.getNode(String.valueOf(index));
//            e1.setAttribute("ui.style", "stroke-mode: plain;shape:circle;fill-color: yellow;size: 20px; text-alignment: center;");
//            e1.setAttribute("ui.label", String.valueOf(index));
//            e1.setAttribute("ui.class","marked");
//            for (Integer node : reader.point[index].getList()) {
//                Edge edge;
//                graph.addNode(String.valueOf(node));
//                e1=graph.getNode(String.valueOf(node));
//                e1.setAttribute("ui.style", "stroke-mode: plain;shape: circle;fill-color: yellow;size: 20px; text-alignment: center;");
//                e1.setAttribute("ui.label", String.valueOf(node));
//                graph.addEdge(index+""+node,String.valueOf(node),String.valueOf(index),true);
//                if(graph.getEdge(index+""+node)!=null){
//                    edge= graph.getEdge(index+""+node);
//                    edge.setAttribute("ui.style","arrow-shape: arrow;fill-color :#a86b32;");
//                }
//            }
//        }
//        graph.setAttribute("ui.antialias");
//        String styleSheet = "graph { padding: 20px; stroke-width: 0px; }"
//                + "node:selected { fill-color: red;  fill-mode: plain; }"
//                + "node:clicked  { fill-color: blue; fill-mode: plain; }"
//                + "node.marked        { fill-color: green, yellow, purple; fill-mode: dyn-plain; }";
//        graph.setAttribute(styleSheet);
////        graph.display(true);
//
//        SwingViewer viewer = new SwingViewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
//        jPanel1.setLayout(new GridLayout());
//        ViewPanel viewPanel = (ViewPanel) viewer.addDefaultView(false);
//        jPanel1.add(viewPanel);
//        jPanel1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
//        viewer.enableAutoLayout();
//
//        setVisible(true);
////
//    }
//    static List<List<Integer>> routes= new ArrayList<>();
//
//    //delete
//    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {
//        // TODO add your handling code here:
//        graph.removeNode(jTextField2.getText());
//
//    }
//
//    private void button4ActionPerformed(java.awt.event.ActionEvent evt) {
//        // TODO add your handling code here:
//        String s="Members:\n•\t";
//        s+="Đào Trung Kiên-20194306\n•\tĐào Xuân An-20190076\n•\tNguyễn Tiến Đạt - 20194243\n•\tNguyễn Quang Long - 2019432\n•\tNguyễn Bá Bình - 2019423" +
//                "\n•\tĐinh Trọng Nghĩa - 20194340";
//        JOptionPane.showMessageDialog(null,s);
//    }
//
//    // insert node
//    private void button5ActionPerformed(java.awt.event.ActionEvent evt) {
//        // TODO add your handling code here:
//        Node e1;
//        graph.addNode(String.valueOf(getNumberNode));
//        e1=graph.getNode(String.valueOf(getNumberNode));
//       // e1.setAttribute("layout.frozen");
//        e1.setAttribute("ui.style", "stroke-mode: plain;shape:circle;fill-color: yellow;size: 20px; text-alignment: center;");
//        e1.setAttribute("ui.label", String.valueOf(getNumberNode));
//
//
//    }
//    static String getNumberNode;
//    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {
//        // TODO add your handling code here:
//    }
//
//    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {
//        // TODO add your handling code here:
//        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
//            getNumberNode=jTextField2.getText();
//        }
//    }
//
//    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {
//        // TODO add your handling code here:
//    }
//
//    private void button6ActionPerformed(java.awt.event.ActionEvent evt) {
//        Edge edge;
//        graph.addEdge(jTextField1.getText()+""+jTextField3.getText(),jTextField1.getText(),jTextField3.getText(),true);
//        edge =graph.getEdge(jTextField1.getText()+""+jTextField3.getText());
//        edge.setAttribute("ui.style","arrow-shape: arrow;fill-color :#a86b32;");
//
//    }
//
//    // back button
//    static Map<Integer,List<Integer>> map= new HashMap<>();
//    static String style="stroke-mode: plain;shape:circle;fill-color: yellow;size: 20px; text-alignment: center;";
//    private void button7ActionPerformed(java.awt.event.ActionEvent evt) {
//        // TODO add your handling code here:
//
//    }
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(GraphGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(GraphGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(GraphGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(GraphGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new GraphGui().setVisible(true);
//            }
//        });
//    }
//
//    // Variables declaration - do not modify
//    private JButton button1;
//    private JButton button2;
//    private JButton button3;
//    private JButton button4;
//    private JButton button5;
//    private JButton button6;
//    private JButton button7;
//    private javax.swing.JLabel jLabel1;
//    private javax.swing.JLabel jLabel2;
//    private javax.swing.JLabel jLabel3;
//    private javax.swing.JPanel jPanel1;
//    private javax.swing.JScrollPane jScrollPane1;
//    private javax.swing.JTabbedPane jTabbedPane1;
//    private javax.swing.JTextArea jTextArea1;
//    private javax.swing.JTextField jTextField1;
//    private javax.swing.JTextField jTextField2;
//    private javax.swing.JTextField jTextField3;
//    // End of variables declaration
//}