package test;

import entity.Point;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Demo {


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

    public static void main(String[] args) {
        for (int index = 1; index < MAXN; index++)
            point[index] = new Point();
        try {
            File myObj = new File("input.txt");
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
        System.out.println("You now are in node 1!");
        while (true) {
            OnTheWay();
            if (CurrentNode == NumberNode)
                break;
        }

    }
}
