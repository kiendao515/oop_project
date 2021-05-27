package entity;

import java.util.ArrayList;
import java.util.List;

public class Point {
   private ArrayList<Integer> list = new ArrayList<Integer>();
    private static int MAXN = 10000005;
    private static int NumberNode = 0;
    private static List<Point> pointList;



    public Point(){
        list.clear();
    }


    public ArrayList<Integer> getList() {
        return list;
    }
}
