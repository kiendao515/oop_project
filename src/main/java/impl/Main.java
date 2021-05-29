package impl;
import entity.Point;

import java.util.Scanner; // Import the Scanner class to read text files

public class Main {

    public static int NumberNode = 0;
    static int MAXN = 10000005;
    public static int start;
    public static int end;
    public static boolean exist[] = new boolean[MAXN];
    public static Point point[] = new Point[MAXN];
    public static int CurrentNode = 1;

    public static void ReSet() {
        NumberNode = 0;
        for (int index = 1; index < MAXN; index++) {
            point[index] = new Point();
            exist[index] = false;
        }
    }

//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        // Nhập điểm đầu và điểm cuối
//        System.out.print("Start: ");
//        start = scanner.nextInt();
//        System.out.print("End: ");
//        end = scanner.nextInt();
//
//
//        // Trường hợp có gợi ý
//        // Tìm đường đi với DFS
//        Simulate simulate = new Simulate();
//
//        //simulate.FindAllPaths();
//        // Gợi ý đường đi
//        System.out.println("You now are in node " + start);
//        while (true) {
//            Simulate.OnTheWay();
//            if (CurrentNode == NumberNode)
//                break;
//        }
//
//
//        /// Trường hợp ko có gợi ý
//        ReSimulate reSimulate = new ReSimulate();
//
//        reSimulate.FindAllPaths();
//        reSimulate.FindShortestPath();
//    }

}