//package impl;
//import entity.Point;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//public class ReadInput {
//    static Point point= new Point();
//
//    public ReadInput(){
//    }
//
//    public void convert(String s){
//        int index = 0, node, next;
//        String string = "";
//        while (s.charAt(index) != ' ')
//            string += s.charAt(index++);
//        node = Integer.parseInt(string);
//        point.setNumberNode(Math.max(point.getNumberNode(),node));
//        System.out.println(Math.max(point.getNumberNode(),node));
//
//        string = "";
//        ++index;
//
//        while (index <= s.length()) {
//            if (index == s.length() || s.charAt(index) == ' ') {
//                next = Integer.parseInt(string);
//                point.setNumberNode(Math.max(point.getNumberNode(),next));
//                System.out.println(point.getLists().get(node).getList());
//                List<Point> pointList=  point.getLists();
//                pointList.get(node).getList().add(next);
//
//                ++index;
//                string = "";
//                continue;
//            }
//            string += s.charAt(index++);
//        }
//        Collections.sort(point.getLists().get(node).getList());
//    }
//
//}
