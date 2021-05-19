package entity;

public class FileReader {
    public static int NumberNode= 0;
    public static final int MAXN= 10000005;
    public static Point point[]= new Point[MAXN];
    public static void convert(String s) {
        int index= 0, node, next;
        String string= "";
        while(s.charAt(index)!= ' ')
            string+= s.charAt(index++);
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
