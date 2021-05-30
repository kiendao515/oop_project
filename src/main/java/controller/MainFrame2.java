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
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;
import java.util.List;
import javax.imageio.ImageIO;
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
        jLabel1.setVisible(false);
        jButton6.setVisible(false);
        jButton7.setVisible(false);
        jButton8.setVisible(false);
        jButton9.setVisible(false);
        jTextField1.setVisible(false);
        jTextField2.setVisible(false);
        jTextField3.setVisible(false);
        jTextField4.setVisible(false);
        jTextField5.setVisible(false);
    }

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null,"hshs");
    }

    static Simulate simulate= new Simulate();

    // có
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:suggestion
        jLabel1.setVisible(true);
        jButton6.setVisible(true);
        jButton7.setVisible(true);
        jTextField1.setVisible(true);
        jTextField2.setVisible(true);
        jButton8.setVisible(false);
        jButton9.setVisible(false);
        jTextField3.setVisible(false);
        jTextField4.setVisible(false);
        jTextField5.setVisible(false);
        if(temp2!=null){
            for(int i=0;i<temp2.length;i++){
                Node node=graph.getNode(temp2[i]);
                node.removeAttribute("shadow-mode: plain; shadow-width: 0px; shadow-color: #999; shadow-offset: 3px, -3px;");
                node.setAttribute("ui.style", "stroke-mode: plain;shape: circle;fill-color: #f5f29f;size: 20px; text-alignment: center;");
            }
            for(int i=0;i<temp2.length-1;i++){
                graph.getEdge((temp2[i]+""+temp2[i+1])).setAttribute("ui.style","arrow-shape: arrow;fill-color :#7a7a73;size: 1px;arrow-size :5px;");
            }
        }
        Main.start=Integer.parseInt(JOptionPane.showInputDialog(null,"Input your first node:"));
        Main.end=Integer.parseInt(JOptionPane.showInputDialog(null,"Input your second node:"));
        Main.CurrentNode= Main.start;
        graph.getNode(String.valueOf(Main.CurrentNode)).setAttribute("ui.style", "shadow-mode: plain; shadow-width: 0px; shadow-color: #999;" +
                " shadow-offset: 3px, -3px;stroke-mode: plain;shape: circle;fill-color: #c277ed;size: 25px; text-alignment: center;");
        simulate= new Simulate();
        simulate.FindAllPaths();
        simulate.Save.add(Main.CurrentNode);
        simulate.Recommend();
        Simulate.map.put(Main.CurrentNode,Simulate.Suggest);
    }

    public static Map<Integer,List<Integer>> map= new HashMap<>();
    //next btn
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {
        /// co suggestion
        simulate.Input();
        simulate.Recommend();
        drawRoute();
        jTextField2.setText("");
    }

    //prev button(co suggestion)
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        if(Main.CurrentNode!=Main.start){
            System.out.println("current node:"+Main.CurrentNode);
            Node node= graph.getNode(String.valueOf(simulate.Save.get(Simulate.Save.size()-1)));
            node.setAttribute("ui.style","stroke-mode: plain;shape:circle;fill-color: #f5f29f;size: 20px; text-alignment: center;");
            clearRoute();
            Simulate.Save.remove(Simulate.Save.size()-1);
            simulate.Recommend();
            drawRoute();
            jTextField2.setText("");
            System.out.println("Save:"+Simulate.Save);
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
        jLabel1.setVisible(true);
        jButton8.setVisible(true);
        jButton9.setVisible(true);
        jTextField3.setVisible(true);
        jTextField4.setVisible(true);
        jButton6.setVisible(false);
        jButton7.setVisible(false);
        jTextField1.setVisible(false);
        jTextField2.setVisible(false);
        jTextField5.setVisible(true);
//        reClearRoute();
        
        if(temp2!=null){
            for(int i=0;i<temp2.length;i++){
                Node node=graph.getNode(temp2[i]);
                node.removeAttribute("shadow-mode: plain; shadow-width: 0px; shadow-color: #999; shadow-offset: 3px, -3px;");
                node.setAttribute("ui.style", "stroke-mode: plain;shape: circle;fill-color: #f5f29f;size: 20px; text-alignment: center;");
            }
            for(int i=0;i<temp2.length-1;i++){
                graph.getEdge((temp2[i]+""+temp2[i+1])).setAttribute("ui.style","arrow-shape: arrow;fill-color :#7a7a73;size: 1px;arrow-size :5px;");
            }
        }
        Main.start=Integer.parseInt(JOptionPane.showInputDialog(null,"Input your first node:"));
        Main.end=Integer.parseInt(JOptionPane.showInputDialog(null,"Input your second node:"));
        Main.CurrentNode= Main.start;
        graph.getNode(String.valueOf(Main.CurrentNode)).setAttribute("ui.style", "shadow-mode: plain; shadow-width: 0px; shadow-color: #999;" +
                "shadow-offset: 3px, -3px;stroke-mode: plain;shape: circle;fill-color: #c277ed;size: 25px; text-alignment: center;");
        reSimulate= new ReSimulate();
        reSimulate.Save.add(Main.CurrentNode);
        reSimulate.Recommend();
    }


    // next button(ko suggestion)
    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        if(Integer.parseInt(jTextField4.getText())==Main.start){
            Node node= graph.getNode(String.valueOf(Main.start));
            node.setAttribute("ui.style","stroke-mode: plain;shape:circle;fill-color: #c277ed;size: 25px; text-alignment: center;");
        }
        System.out.println("You now are in node " + Main.CurrentNode);
        reSimulate.Input();
        reSimulate.Recommend();
        System.out.println("size:"+ map.size());
        jTextField4.setText("");
        jTextField5.setText(String.valueOf(reSimulate.Save));
        if(reSimulate.Suggest.size()==0) JOptionPane.showMessageDialog(null,"Sorry no node detect!");
        reDrawRoute();
    }

//    public static Map<Integer,List<Integer>>map = new

    //back button (ko suggestion)
    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        if(reSimulate.Save.size()!=1){
            System.out.println("current node:"+Main.CurrentNode);
            Main.CurrentNode= reSimulate.Save.get(reSimulate.Save.size()-1);
            Node node= graph.getNode(String.valueOf(Main.CurrentNode));
            node.setAttribute("ui.style","stroke-mode: plain;shape:circle;fill-color: #f5f29f;size: 20px; text-alignment: center;");
            reClearRoute();
            jTextField4.setText("");
            reSimulate.Save.remove(reSimulate.Save.size()-1);
            jTextField5.setText(String.valueOf(reSimulate.Save));
            for(int i=0;i<ReSimulate.Save.size();i++){
                Node node2= graph.getNode(String.valueOf(ReSimulate.Save.get(i)));
                node2.setAttribute("ui.style","stroke-mode: plain;shape:circle;fill-color: #c277ed;size: 25px; text-alignment: center;");
            }
            Main.CurrentNode= reSimulate.Save.get(reSimulate.Save.size()-1);
            reSimulate.Recommend();
            reDrawRoute();
            System.out.println("Save:"+reSimulate.Save);
        }else{
            Node node= graph.getNode(String.valueOf(reSimulate.Save.get(0)));
            node.setAttribute("ui.style","stroke-mode: plain;shape:circle;fill-color: #f5f29f;size: 20px; text-alignment: center;");
            jTextField3.setText(String.valueOf(Main.start));
            jTextField5.setText("");
        }
    }

    // key enter
    static String getNumberNode;
    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:

    }

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        resetRow();
        if(check){
            reClearRoute();
        }
        int x= jTable1.getSelectedRow();
        String y=(String)jTable1.getValueAt(x,0);
        y=y.trim();
        temp2= y.split(" ");
        for(int i=0;i<temp2.length-1;i++){
            System.out.println(temp2[i]+""+temp2[i+1]);
            if(graph.getEdge(temp2[i]+""+temp2[i+1])==null) continue;
            graph.getEdge((temp2[i]+""+temp2[i+1])).setAttribute("ui.style","arrow-shape: arrow;fill-color :#32a852;arrow-size :8px;size: 4px;");
        }
        for(int i=0;i<temp2.length;i++){
            Node node=graph.getNode(temp2[i]);
            node.setAttribute("ui.style", "shadow-mode: plain; shadow-width: 0px; shadow-color: #999;" +
                    " shadow-offset: 3px, -3px;stroke-mode: plain;shape: circle;fill-color: #c277ed;size: 25px; text-alignment: center;");
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



    void reClearRoute(){
        if(reSimulate.Save.size()>=2){
            for(int i=0;i<reSimulate.Save.size()-1;i++){
                Edge e=graph.getEdge(reSimulate.Save.get(i)+""+reSimulate.Save.get(i+1));
                e.setAttribute("ui.style","arrow-shape: arrow;fill-color :#7a7a73;size :1px;arrow-size :5px;");
                // check=true;
            }
        }
    }

    void reDrawRoute(){
        if(reSimulate.Save.size()>=2){
            for(int i=0;i<reSimulate.Save.size()-1;i++){
                Edge e=graph.getEdge(reSimulate.Save.get(i)+""+reSimulate.Save.get(i+1));
                e.setAttribute("ui.style","arrow-shape: arrow;fill-color :#32a852;arrow-size :8px;size: 4px;");
                // check=true;
            }
        }
    }
    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        String msg ="+Take screen:chụp ảnh và lưu lại ảnh của đồ thị bất cứ khi nào người dùng muốn.\n" +
                    "+Simulation:có hai kiểu simulation tùy từng truòng hợp người dùng muốn sử dụng.\n" +
                    "Ví dụ: khi người dùng muốn tìm đường đi từ hai đỉnh bất kì và muốn chắc chắn rằng mình sẽ đến được đích thì\n" +
                    "nên dùng chức năng Simulation with suggestion, còn nếu người đó muốn tự do đi thì nên chọn Simulation with no guildline.\n" +
                    "+Folder icon:dùng để mở file cần chọn.\n" +
                    "+Display graph icon: vẽ đồ thị.\n" +
                    "+Pencil icon: chỉnh sửa đồ thị(thêm cạnh,xóa cạnh,thêm nút,xóa nút)\n" +
                    "+Lock icon:khóa đồ thị và reset lại đồ thị.\n" +
                    "+Search icon:tìm kiếm tất cả dựa vào hai điểm đầu cuối do người dùng nhập.";
        JOptionPane optionPane = new JOptionPane();
        optionPane.setMessage(msg);
        optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog = optionPane.createDialog(null, "Graph visualization");
        dialog.setVisible(true);

    }
    private void jMenu4MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        String message="Đào Xuân An – 20190076\n" +
                       "Đào Trung Kiên – 20194306\n" +
                       "Nguyễn Tiến Đạt- 20194243\n" +
                       "Nguyễn Quang Long -2019432\n" +
                       "Nguyễn Bá Bình – 2019423\n" +
                       "Đinh Trọng Nghĩa -20194340\n";
        JOptionPane optionPane = new JOptionPane();
        optionPane.setMessage(message);
        optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog = optionPane.createDialog(null, "Member");
        dialog.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jMenuItem4 = new javax.swing.JMenuItem();
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
        jTextField3 = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();

        jMenuItem4.setText("jMenuItem4");

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

        jTable1.setBackground(new java.awt.Color(204, 204, 255));
        jTable1.setFont(new java.awt.Font("Dubiel", 1, 14)); // NOI18N
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
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));

        jButton1.setBackground(new java.awt.Color(204, 204, 255));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(("C:\\Users\\admin\\IdeaProjects\\OopsBigAssignment\\images\\opened_folder_30px.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(204, 204, 255));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(("C:\\Users\\admin\\IdeaProjects\\OopsBigAssignment\\images\\system_task_30px.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(204, 204, 255));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(("C:\\Users\\admin\\IdeaProjects\\OopsBigAssignment\\images\\edit_30px.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(204, 204, 255));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(("C:\\Users\\admin\\IdeaProjects\\OopsBigAssignment\\images\\lock_30px.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(204, 204, 255));
        jButton5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(("C:\\Users\\admin\\IdeaProjects\\OopsBigAssignment\\images\\search_30px.png"))); // NOI18N
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
        jButton6.setIcon(new javax.swing.ImageIcon(("C:\\Users\\admin\\IdeaProjects\\OopsBigAssignment\\images\\u_turn_to_left_30px.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(("C:\\Users\\admin\\IdeaProjects\\OopsBigAssignment\\images\\fast_forward_30px.png"))); // NOI18N
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

        jButton8.setIcon(new javax.swing.ImageIcon(("C:\\Users\\admin\\IdeaProjects\\OopsBigAssignment\\images\\u_turn_to_left_30px.png"))); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setIcon(new javax.swing.ImageIcon(("C:\\Users\\admin\\IdeaProjects\\OopsBigAssignment\\images\\hand_right_25px.png"))); // NOI18N
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
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jButton5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                                                        .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButton1)
                                .addGap(34, 34, 34)
                                .addComponent(jButton2)
                                .addGap(34, 34, 34)
                                .addComponent(jButton3)
                                .addGap(35, 35, 35)
                                .addComponent(jButton4)
                                .addGap(35, 35, 35)
                                .addComponent(jButton5)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenuBar1.setBackground(new java.awt.Color(204, 204, 255));
        jMenuBar1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jMenu1.setText("Take Screen");
        jMenu1.setFont(new java.awt.Font("Sitka Text", 1, 15)); // NOI18N
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Simulation");
        jMenu2.setFont(new java.awt.Font("Sitka Text", 1, 15)); // NOI18N

        jMenuItem2.setFont(new java.awt.Font("Sitka Text", 1, 14)); // NOI18N
        jMenuItem2.setText("Simulation with suggestion");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem3.setFont(new java.awt.Font("Sitka Text", 1, 14)); // NOI18N
        jMenuItem3.setText("Simulation with no guild line");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        jMenu3.setBackground(new java.awt.Color(204, 204, 255));
        jMenu3.setText("Usage");
        jMenu3.setFont(new java.awt.Font("Sitka Text", 1, 15)); // NOI18N
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu3);

        jMenu4.setText("Information");
        jMenu4.setFont(new java.awt.Font("Sitka Text", 1, 15)); // NOI18N
        jMenu4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu4MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
    void setComponent(){
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
    }

    //browse file
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        Remove();
        setComponent();
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
    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        saveImage();
    }
    private void saveImage(){
        JFileChooser chooser= new JFileChooser();
        chooser.showOpenDialog(null);
        File file=chooser.getSelectedFile();
        BufferedImage imagebuf=null;
        try {
            imagebuf = new Robot().createScreenCapture(jPanel1.bounds());
        } catch (AWTException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        Graphics2D graphics2D = imagebuf.createGraphics();
        jPanel1.paint(graphics2D);
        try {
            ImageIO.write(imagebuf,"jpg", new File(file.getAbsolutePath()));
            JOptionPane.showMessageDialog(null,"Save image successfully! ");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("error");
        }
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
                e1.setAttribute("ui.style", "stroke-mode: plain;shape:circle;fill-color: #f5f29f;size: 20px; text-alignment: center;");
                e1.setAttribute("ui.label", String.valueOf(index));
                e1.setAttribute("ui.class","marked");
            }
            for (Integer node : Main.point[index].getList()) {
                Edge edge;
                graph.addNode(String.valueOf(node));
                e1=graph.getNode(String.valueOf(node));
                e1.setAttribute("ui.style", "stroke-mode: plain;shape: circle;fill-color: #f5f29f;size: 20px; text-alignment: center;");
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
        e1.setAttribute("ui.style", "stroke-mode: plain;shape:circle;fill-color: #f5f29f;size: 20px; text-alignment: center;");
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
        if(graph.getNode(node)!=null){
            graph.removeNode(node);
        }else JOptionPane.showMessageDialog(null,"No node found!!");
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
        if(graph.getEdge(s1+s2)!=null){
            graph.removeEdge(s1+s2);
        }else JOptionPane.showMessageDialog(null,"No edge found!");
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
        setComponent();
        if(temp2!=null){
            for(int i=0;i<temp2.length;i++){
                Node node=graph.getNode(temp2[i]);
                node.removeAttribute("shadow-mode: plain; shadow-width: 0px; shadow-color: #999; shadow-offset: 3px, -3px;");
                node.setAttribute("ui.style", "stroke-mode: plain;shape: circle;fill-color: #f5f29f;size: 20px; text-alignment: center;");
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
        resetRow();
        if(check){
            clear();
        }
        defaultTableModel= new DefaultTableModel();
        Main.start= Integer.parseInt(JOptionPane.showInputDialog(null, "Input your first node:"));
        Main.end= Integer.parseInt(JOptionPane.showInputDialog(null, "Input your second node:"));
        //System.out.println(Main.start+ " "+ Main.end);
        jTable1.setModel(new DefaultTableModel(null,new String[]{"All of routes"}));
        defaultTableModel.addColumn("All of routes:");
        ReSimulate reSimulate= new ReSimulate();
        reSimulate.FindAllPaths();
         String routes= reSimulate.FindShortestPath();
         JOptionPane.showMessageDialog(null,"Shortest route is:"+routes);
        System.out.println("shortest routes:"+routes);
         temp2= routes.trim().split(" ");
        for(int i=0;i<temp2.length-1;i++){
            graph.getEdge(temp2[i]+""+temp2[i+1]).setAttribute("ui.style","arrow-shape: arrow;fill-color :#32a852;arrow-size :8px;size: 4px;");
        }
        for(int i=0;i<temp2.length;i++){
            graph.getNode(temp2[i]).setAttribute("ui.style","stroke-mode: plain;shape: circle;fill-color: #c277ed;size: 25px; text-alignment: center;");
        }
//        resetRow();
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
                node.setAttribute("ui.style", "stroke-mode: plain;shape: circle;fill-color: #f5f29f;size: 20px; text-alignment: center;");
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
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable jTable1;
    public static javax.swing.JTextField jTextField1;
    public static javax.swing.JTextField jTextField2;
    public static javax.swing.JTextField jTextField3;
    public  static javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration
}