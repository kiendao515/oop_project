package impl;
import entity.Point;

import java.util.Scanner; // Import the Scanner class to read text files

public class Main {

    static int NumberNode = 0;
    static int MAXN = 10000005;
    static int start;
    static int end;

    static Point point[] = new Point[MAXN];
    static int NumberPath = 0;
    static int CurrentNode = 1;


    public static void main(String[] args) {
        for (int index = 1; index < MAXN; index++)
            point[index] = new Point();

        //ReadInput readInput= new ReadInput();
      //  readInput.Read();

        Scanner scanner= new Scanner(System.in);

        // Nhập điểm đầu và điểm cuối
        System.out.print("Start: ");
        start= scanner.nextInt();
        System.out.print("End: ");
        end= scanner.nextInt();


        // Trường hợp có gợi ý
        // Tìm đường đi với DFS
        Simulate simulate= new Simulate();

       // simulate.FindAllPaths();
        // Gợi ý đường đi
        System.out.println("You now are in node " + start);
        while (true) {
            Simulate.OnTheWay();
            if (CurrentNode == NumberNode)
                break;
        }


        /// Trường hợp ko có gợi ý
        ReSimulate reSimulate= new ReSimulate();

        reSimulate.FindAllPaths();
        reSimulate.FindShortestPath();
    }
}