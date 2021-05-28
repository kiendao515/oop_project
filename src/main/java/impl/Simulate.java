package impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.swing.JOptionPane;

import controller.MainFrame2;

public class Simulate{

    public static int trace[] = new int[Main.MAXN];
    public static ArrayList<ArrayList<Integer>> Path = new ArrayList<ArrayList<Integer>>();
    public static ArrayList<Integer> Save = new ArrayList<Integer>();
    public static ArrayList<Integer> paths = new ArrayList<Integer>();
    public static ArrayList<Integer> Suggest = new ArrayList<Integer>();

    public Simulate(){
        Path.clear();
        Save.clear();
        paths.clear();
        Suggest.clear();
    }

    static void Reset()
    {
        for(int i= 0; i<= Main.NumberNode; i++)
            trace[i]= 0;
    }

    //  In ra đường đi
    static void Print() {
        // in ra tất cả đường đi
        for (ArrayList<Integer> a : Path) {
            for (Integer b : a)
                System.out.print(b + " ");
            System.out.println();
        }

        // in ra đường đi ngắn nhất
        System.out.print("Đường đi ngắn nhất: ");
        int ans= 1000000000;
        for (ArrayList<Integer> a : Path) {
            ans= Math.min(a.size(), ans);
        }

        for (ArrayList<Integer> a : Path) {
            if(ans== a.size()){
                for (Integer b : a)
                    System.out.print(b + " ");
                break;
            }
        }
        System.out.println();
    }

    static void Trace(int index) {
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
        Path.add(path);
    }

    static void DFS(int index) {
        if (index == Main.end) {
            Trace(Main.end);
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

    public static void OnTheWay(){
        Save.add(Main.CurrentNode);
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
        //System.out.println(Left+ " "+ Right);

        Suggest.clear();
        int Pre= 0;
        for(int index= Left; index< Right; index++)
        {
            paths= Path.get(index);
            //if(Suggest.get(Suggest.size()- 1)!= paths.get(Save.size()))
            if(paths.size()== Save.size()){
                Main.CurrentNode= Main.end;
                return;
            }
            if(paths.get(Save.size())!= Pre)
            {
                Suggest.add(paths.get(Save.size()));
                Pre= paths.get(Save.size());
            }
        }

        MainFrame2.jTextField1.setText(String.valueOf(Suggest));
        System.out.println(Suggest);
        System.out.println("Your choice: ");
        Main.CurrentNode= Integer.parseInt(MainFrame2.jTextField2.getText());
        boolean nice= Suggest.contains(Main.CurrentNode);

    }

    //  Tìm tất cả đường đi
    public void FindAllPaths()
    {
        Reset();
        trace[Main.start]= -1;
        DFS(Main.start);
        Print();
    }
}