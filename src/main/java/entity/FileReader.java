/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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
    public static void main(String[] argv) throws Exception {
        DefaultTableModel tableModel = new DefaultTableModel();
        JTable table = new JTable(tableModel);
        tableModel.addColumn("Language/ Technology");
        tableModel.addColumn("Difficulty Level");
        tableModel.insertRow(0, new Object[] { "CSS", "Easy" });
        tableModel.insertRow(0, new Object[] { "HTML5", "Easy"});
        tableModel.insertRow(0, new Object[] { "JavaScript", "Intermediate" });
        tableModel.insertRow(0, new Object[] { "jQuery", "Intermediate" });
        tableModel.insertRow(0, new Object[] { "AngularJS", "Difficult"});
        // adding a new row
        tableModel.insertRow(tableModel.getRowCount(), new Object[] { "ExpressJS", "Intermediate" });
        // appending a new row
        tableModel.addRow(new Object[] { "WordPress", "Easy" });
        JFrame f = new JFrame();
        f.setSize(550, 350);
        f.add(new JScrollPane(table));
        f.setVisible(true);
    }


}
