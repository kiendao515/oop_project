package entity;

import java.util.ArrayList;
import java.util.List;

public class Filee {
    static int NumberNode= 0;
    static final int MAXN= 10000005;
    public static Point point[];
    public static int trace[];

    public Filee() {
        point = new Point[MAXN];
        trace= new int[MAXN];
    }

    public  int getNumberNode() {
        return NumberNode;
    }

    public  void setNumberNode(int numberNode) {
        NumberNode = numberNode;
    }

    public int getMAXN() {
        return MAXN;
    }

    public static Point[] getPoint() {
        return point;
    }

    public static void setPoint(Point[] point) {
        Filee.point = point;
    }

    public static int[] getTrace() {
        return trace;
    }

    public static void setTrace(int[] trace) {
        Filee.trace = trace;
    }
}
