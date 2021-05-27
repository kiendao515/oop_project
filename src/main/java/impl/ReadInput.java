package impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Scanner;

public class ReadInput {

    //static Point point[] = new Point[Main.MAXN];

    public ReadInput(){}

    public void convert(String s){
        int index = 0, node, next;
        String string = "";
        while (s.charAt(index) != ' ')
            string += s.charAt(index++);
        node = Integer.parseInt(string);
        Main.exist[node]= true;
        Main.NumberNode = Math.max(Main.NumberNode, node);
        string = "";
        ++index;

        while (index <= s.length()) {
            if (index == s.length() || s.charAt(index) == ' ') {
                next = Integer.parseInt(string);
                //System.out.println(next);
                Main.exist[next]= true;
                Main.NumberNode = Math.max(Main.NumberNode, next);
                Main.point[node].getList().add(next);
                ++index;
                string = "";
                continue;
            }
            string += s.charAt(index++);
        }
        Collections.sort(Main.point[node].getList());
    }

    public void Read(String filename){
        Main.ReSet();
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                this.convert(data);
                //System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}