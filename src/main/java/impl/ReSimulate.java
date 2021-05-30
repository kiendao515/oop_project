package impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import controller.MainFrame2;

public class ReSimulate{

    public  int trace[] = new int[Main.MAXN];
    public  boolean done= false;
    public static   ArrayList<Integer> Save = new ArrayList<Integer>();
    public  ArrayList<Integer> Suggest = new ArrayList<Integer>();

    public ReSimulate(){
        Save.clear();
        Suggest.clear();
    }

    void Reset()
    {
        for(int i= 0; i<= Main.NumberNode; i++)
            trace[i]= 0;
    }

    //  In ra đường đi
    String Trace(int index) {
        ArrayList<Integer> path = new ArrayList<Integer>();
        while (true) {
            //System.out.print(index+ " ");
            path.add(index);
            index = trace[index];
            if (index == Main.start)
                break;
        }
        // System.out.println(1);
        path.add(Main.start);
        Collections.reverse(path);
        String res= "";
        for(int u: path)
            res= res+ u+ " ";
        //System.out.println();
        return res;
    }


    void DFS(int index) {
        if (index == Main.end) {
            MainFrame2.defaultTableModel.addRow(new Object[]{Trace(Main.end)});
            return;
        }
        //System.out.println(index);

        for (Integer node : Main.point[index].getList()) {
            if (trace[node] != 0)
                continue;
            trace[node] = index;
            DFS(node);
            trace[node] = 0;
        }
    }

    public  void FindAllPaths()
    {
        Reset();
        trace[Main.start]= -1;
        DFS(Main.start);
    }

    //  Tìm đường đi ngắn nhất
    public String FindShortestPath()
    {
        String rs="";
        Reset();
        Deque<Integer> deque= new LinkedList<Integer>();
        deque.push(Main.start);
        trace[Main.start]= -1;
        System.out.print("Đường đi ngắn nhất: ");
        while(!deque.isEmpty()){
            int u= deque.getFirst();
            deque.removeFirst();
            for(int v: Main.point[u].getList()){
                if(trace[v]!= 0) continue;
                trace[v]= u;
                if(v== Main.end){
                    rs= Trace(v);
                    done= true;
                    break;
                }
                deque.offer(v);
            }
            if(done== true) break;
        }
        return rs;
    }

    public  void Recommend()
    {
        System.out.println(Main.CurrentNode);
        if(Main.CurrentNode== Main.end)
        {
            MainFrame2.jTextField3.setText("You re in the finish!");
            JOptionPane.showMessageDialog(null,"Finding routes successfully!!");
            return;
        }

        Suggest= Main.point[Main.CurrentNode].getList();
        Main.point[Main.CurrentNode].display( Main.point[Main.CurrentNode].getList());
        MainFrame2.jTextField3.setText(String.valueOf(Suggest));
    }

    public  void Input(){

        int input= Integer.parseInt(MainFrame2.jTextField4.getText());
        boolean nice= Suggest.contains(input);
        if(nice){
            MainFrame2.graph.getNode(String.valueOf(input)).setAttribute("ui.style", "shadow-mode: plain; shadow-width: 0px; shadow-color: #999;" +
                    " shadow-offset: 3px, -3px;stroke-mode: plain;shape: circle;fill-color: #c277ed;size: 25px; text-alignment: center;");

            System.out.println("Nice choice!!!");
            Main.CurrentNode= input;
            Save.add(Main.CurrentNode);
        }
        else
            System.out.println("Bad choice, please choose another one: ");

    }

}