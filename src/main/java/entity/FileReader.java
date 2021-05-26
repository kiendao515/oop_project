/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

/**
 *
 * @author admin
 */
public class FileReader {
    public static int NumberNode= 0;
    public static final int MAXN= 10000005;
    public static Point point[]= new Point[MAXN];
    public static void convert(String s) {
        int index= 0, node, next;
        String string= "";
        while(s.charAt(index)!= ' ')
            string+= s.charAt(index++);
        string.trim();
        node= Integer.parseInt(string);
        NumberNode= Math.max(NumberNode, node);
        string= "";
        ++index;

        while(index<= s.length()){
            if(index== s.length()|| s.charAt(index)== ' '){
                next= Integer.parseInt(string);
                NumberNode= Math.max(NumberNode, next);
                point[next].getList().add(node);
                ++index;
                string= "";
                continue;
            }
            string+= s.charAt(index++);
        }
    }


}
