package controller;
import entity.Point;
import impl.Main;
import impl.ReSimulate;
import impl.ReadInput;
import impl.Simulate;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swing_viewer.SwingViewer;
import org.graphstream.ui.swing_viewer.ViewPanel;
import org.graphstream.ui.view.Viewer;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MainFrame2 extends javax.swing.JFrame {
    static int NumberNode = 0;
    static int MAXN = 10000005;
    static Point point[] = new Point[MAXN];
    static int trace[] = new int[MAXN];

    static  String filename;
    static String s1,s2;
    // public static List<Integer> suggest = new ArrayList<Integer>();
    public static org.graphstream.graph.Graph graph = new SingleGraph("main graph");
    public MainFrame2() {
        initComponents();
    }

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    static Simulate simulate= new Simulate();

    // có
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:suggestion
        Main.start=Integer.parseInt(JOptionPane.showInputDialog(null,"Input your first node:"));
        Main.end=Integer.parseInt(JOptionPane.showInputDialog(null,"Input your second node:"));
        Main.CurrentNode= Main.start;
        graph.getNode(String.valueOf(Main.CurrentNode)).setAttribute("ui.style", "shadow-mode: plain; shadow-width: 0px; shadow-color: #999;" +
                " shadow-offset: 3px, -3px;stroke-mode: plain;shape: circle;fill-color: #c277ed;size: 20px; text-alignment: center;");
        simulate= new Simulate();
        simulate.FindAllPaths();
        simulate.Save.add(Main.CurrentNode);
        simulate.Recommend();
        Simulate.map.put(Main.CurrentNode,Simulate.Suggest);
    }

    Map<Integer,List<Integer>> map= new HashMap<>();
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {
        /// co suggestion
        simulate.Input();
        simulate.Recommend();
//        System.out.println("textfield hien tai:"+jTextField2.getText()+" suggestion hien tai:"+simulate.Suggest);
        System.out.println("save:"+simulate.Save);
        drawRoute();
        //map.put(Integer.valueOf(jTextField2.getText()),simulate.Suggest);
    }
    //prev button(co suggestion)
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        if(Main.CurrentNode!=Main.start){
            System.out.println("current node:"+Main.CurrentNode);
            Node node= graph.getNode((jTextField2.getText()));
            node.setAttribute("ui.style","stroke-mode: plain;shape:circle;fill-color: yellow;size: 20px; text-alignment: center;");
            clearRoute();
            Simulate.Save.remove(Simulate.Save.size()-1);
//            clearRoute();
            drawRoute();
            System.out.println("Save:"+Simulate.Save);
            drawRoute();
            if(Simulate.Save.size()!=0) jTextField2.setText(String.valueOf((Simulate.Save.get(Simulate.Save.size()-1))));
            for (int me : map.keySet()) {
                System.out.println("key:"+me+" value:"+map.get(me));
                if(me==(Integer.parseInt(jTextField2.getText()))){
                    System.out.println("ok");
                    jTextField1.setText(String.valueOf(map.get(me)));
                    System.out.println("suggestion:"+map.get(me));
                    break;
                }
            }
        }
    }

    void clearRoute(){
        if(simulate.Save.size()>=2){
            for(int i=0;i<simulate.Save.size()-1;i++){
                Edge e=graph.getEdge(simulate.Save.get(i)+""+simulate.Save.get(i+1));
                e.setAttribute("ui.style","arrow-shape: arrow;fill-color :#7a7a73;size :1px;arrow-size :5px;");
                // check=true;
            }
        }
    }

    void drawRoute(){
        if(simulate.Save.size()>=2){
            for(int i=0;i<simulate.Save.size()-1;i++){
                Edge e=graph.getEdge(simulate.Save.get(i)+""+simulate.Save.get(i+1));
                e.setAttribute("ui.style","arrow-shape: arrow;fill-color :#32a852;arrow-size :8px;size: 4px;");
                // check=true;
            }
        }
    }



    static ReSimulate reSimulate= new ReSimulate();
    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        Main.start=Integer.parseInt(JOptionPane.showInputDialog(null,"Input your first node:"));
        Main.end=Integer.parseInt(JOptionPane.showInputDialog(null,"Input your second node:"));
        Main.CurrentNode= Main.start;
        reSimulate= new ReSimulate();
        reSimulate.Save.add(Main.CurrentNode);
        reSimulate.Recommend();
    }






    // next
    // next button(co suggestion)

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        System.out.println("You now are in node " + Main.CurrentNode);
        reSimulate.Input();
        reSimulate.Recommend();
        jTextField4.setText("");
    }
    // key enter
    static String getNumberNode;
    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:

    }

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        if(check){
            resetRow();
        }
        int x= jTable1.getSelectedRow();
        String y=(String)jTable1.getValueAt(x,0);
        y=y.trim();
        // System.out.println(y);
        temp2= y.split(" ");
        for(int i=0;i<temp2.length-1;i++){
            System.out.println(temp2[i]+""+temp2[i+1]);
            if(graph.getEdge(temp2[i]+""+temp2[i+1])==null) continue;
            graph.getEdge((temp2[i]+""+temp2[i+1])).setAttribute("ui.style","arrow-shape: arrow;fill-color :#32a852;arrow-size :8px;size: 4px;");
        }
        for(int i=0;i<temp2.length;i++){
            System.out.println("day la tem2:"+temp2[i]);
            Node node=graph.getNode(temp2[i]);
            node.setAttribute("ui.style", "shadow-mode: plain; shadow-width: 0px; shadow-color: #999;" +
                    " shadow-offset: 3px, -3px;stroke-mode: plain;shape: circle;fill-color: #c277ed;size: 20px; text-alignment: center;");
            check=true;
        }
    }

    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        getRootPane().setDefaultButton(jButton7);
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
            getNumberNode=jTextField1.getText();
            getNumberNode= getNumberNode.replaceAll("\\s+","");
        }
    }

    private void jTextField4KeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        getRootPane().setDefaultButton(jButton9);
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
            getNumberNode=jTextField3.getText();
            getNumberNode= getNumberNode.replaceAll("\\s+","");
        }
    }

    // next button (ko co suggestion)

    //back button (co suggestion)
    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }
    @SuppressWarnings("unchecked")
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
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jTextField4 = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 462, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTable1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
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
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 822, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
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

        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel1.setText("Suggestion");

        jButton6.setBackground(new java.awt.Color(255, 255, 255));
        jButton6.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jButton6.setText("Prev");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jButton7.setText("Next");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField2KeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel2.setText("Suggestion ");

        jButton8.setText("Prev");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("Next");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField4KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addContainerGap(28, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButton9))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jButton6)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jButton7)))
                                .addGap(18, 18, 18))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGap(50, 50, 50)
                                                .addComponent(jLabel1))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGap(31, 31, 31)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jButton2)
                                                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                                        .addGap(1, 1, 1)
                                                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(jLabel2)
                                .addGap(0, 0, Short.MAX_VALUE))
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
                                .addGap(37, 37, 37)
                                .addComponent(jButton5)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton6)
                                        .addComponent(jButton7))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton8)
                                        .addComponent(jButton9))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenuBar1.setBackground(new java.awt.Color(204, 204, 204));

        jMenu1.setText("File");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N

        jMenuItem1.setText("Open File");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Simulation");
        jMenu2.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N

        jMenuItem2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jMenuItem2.setText("Simulation with suggestion");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jMenuItem3.setText("Simulation with no guild line");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>
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
    private static void Remove(){
        for(int index= 1; index<=  Main.NumberNode; index++)
            for (Integer node : Main.point[index].getList())
                if(graph.getEdge(index+""+node)!=null)
                    graph.removeEdge(index+""+node);

        for(int index= 1; index<= Main.NumberNode; index++)
            if(graph.getNode(String.valueOf(index))!= null)
                graph.removeNode(String.valueOf(index));

    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        jPanel1.removeAll();
        jPanel1.revalidate();
        jPanel1.repaint();
        Remove();
        //reader= new FileReader();
        System.setProperty("org.graphstream.ui","swing");//
        //System.setProperty("org.graphstream.ui", "org.graphstream.ui.swing.util.Display");
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
                    edge.setAttribute("ui.style","arrow-shape: arrow;fill-color :#7a7a73;size :1px;arrow-size :5px;");
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
    static void addEdge(){
        s1= JOptionPane.showInputDialog("Enter first node:");
        s2=JOptionPane.showInputDialog("Enter tail:");
        Edge edge;
        graph.addEdge(s1+s2,s1,s2,true);
        edge =graph.getEdge(s1+s2);
        edge.setAttribute("ui.style","arrow-shape: arrow;fill-color :#7a7a73;size :1px;arrow-size :5px;");
        int u= Integer.parseInt(s1);
        int v= Integer.parseInt(s2);
        Main.point[u].getList().add(v);
    }
    static void removeNode(){
        String node=  JOptionPane.showInputDialog(null,"Input node:");
        graph.removeNode(node);
        int a= Integer.parseInt(node);
        Main.exist[a]= false;
        Main.point[a].getList().clear();
        while(Main.exist[Main.NumberNode]== false) --Main.NumberNode;
        for(int i= 1; i<= Main.NumberNode; i++){
            for(int index= 0; index< Main.point[i].getList().size(); index++)
                if(Main.point[i].getList().get(index)== a) Main.point[i].getList().remove(index);
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
        editAuto();
    }

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        viewer.disableAutoLayout();
        if(temp2!=null){
            for(int i=0;i<temp2.length;i++){
                Node node=graph.getNode(temp2[i]);
                node.removeAttribute("shadow-mode: plain; shadow-width: 0px; shadow-color: #999; shadow-offset: 3px, -3px;");
                node.setAttribute("ui.style", "stroke-mode: plain;shape: circle;fill-color: yellow;size: 20px; text-alignment: center;");
            }
            for(int i=0;i<temp2.length-1;i++){
                graph.getEdge((temp2[i]+""+temp2[i+1])).setAttribute("ui.style","arrow-shape: arrow;fill-color :#7a7a73;size: 1px;arrow-size :5px;");
            }
        }
    }

    static String temp;
    static boolean check=false;
    public static DefaultTableModel defaultTableModel;
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {
        if(check){
            clear();
        }
        defaultTableModel= new DefaultTableModel();
        Main.start= Integer.parseInt(JOptionPane.showInputDialog(null, "Input your first node:"));
        Main.end= Integer.parseInt(JOptionPane.showInputDialog(null, "Input your second node:"));
        System.out.println(Main.start+ " "+ Main.end);
        jTable1.setModel(new DefaultTableModel(null,new String[]{"All of routes"}));
        defaultTableModel.addColumn("All of routes:");
        ReSimulate reSimulate= new ReSimulate();
        reSimulate.FindAllPaths();

        jTable1.setModel(defaultTableModel);
    }

    static String[] temp2;
    static void resetRow(){
        if(temp2!=null){
            for(int i=0;i<temp2.length;i++){
                // System.out.println("temp2:"+(temp2[i]));
                if(graph.getNode(String.valueOf(temp2[i]))==null) continue;
                Node node=graph.getNode(String.valueOf(temp2[i]));
                node.removeAttribute("shadow-mode: plain; shadow-width: 0px; shadow-color: #999; shadow-offset: 3px, -3px;");
                node.setAttribute("ui.style", "stroke-mode: plain;shape: circle;fill-color: yellow;size: 20px; text-alignment: center;");
                // temp2[i]="";
            }
            for(int i=0;i<temp2.length-1;i++){
                if(graph.getEdge(temp2[i]+""+temp2[i+1])==null) continue;
                graph.getEdge((temp2[i]+""+temp2[i+1])).setAttribute("ui.style","arrow-shape: arrow;fill-color :#7a7a73;size: 1px;arrow-size :5px;");
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
            java.util.logging.Logger.getLogger(MainFrame2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable jTable1;
    public static JTextField jTextField1;
    public static JTextField jTextField2;
    public static JTextField jTextField3;
    public static JTextField jTextField4;
    // End of variables declaration
}