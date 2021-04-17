package test;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Demo {
    public static void main(String[] args) {
        System.setProperty("org.graphstream.ui","swing");//

        Graph graph = new SingleGraph("Tutorial 1");

        graph.setStrict(false);
        graph.setAutoCreate( true );
    /*
    1 2 3
    2 4
    3 4
     */
        Map<Integer, List<Integer>> map= new HashMap<>();
        List<Integer> list= new ArrayList<>();
        list.add(2);
        list.add(3);

        List<Integer> list2= new ArrayList<>();
        list2.add(4);

        List<Integer> list3= new ArrayList<>();
        list3.add(4);
        map.put(1,list);
        map.put(2,list2);
        map.put(3,list3);

        System.out.println(String.valueOf(1+""+list.get(0)));
        for (Map.Entry<Integer,List<Integer>> e : map.entrySet()){
            System.out.println("Key: " + e.getKey()
                    + " Value: " + e.getValue());
        }

        /**
         *  cai phan duoi nayf để vẽ đồ thị
         *  thì nó có ba tham số tham số 1 là cái cạnh nối 2 đỉnh ví dụ kiểu 12, tham số thứ 2 là đỉnh 1, tham số
         *  thứ 3 laf đỉnh 2
         *  thì cần phải lưu trữ cái output đoc từ file như nào để cho dễ code cái vẽ nhỉ??
         *  ênguyn 2 đỉnh ko đươcn à :v
         *  kiểu như này  á
         *  12 àl sao
         *  đm len fb
         */
        graph.addEdge((1+""+list.get(0)),String.valueOf(1),String.valueOf(list.get(0)));
        graph.addEdge("12","1","2");// kiểu này
        graph.addEdge((1+""+list.get(1)),String.valueOf(1),String.valueOf(list.get(1)));
        graph.addEdge((2+""+list2.get(0)),String.valueOf(2),String.valueOf(list2.get(0)));
        graph.addEdge((3+""+list3.get(0)), String.valueOf(3),String.valueOf(list3.get(0)));

        graph.display();
    }

}
