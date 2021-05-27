package entity;

import javax.swing.table.AbstractTableModel;

public class Model extends AbstractTableModel {
    private String[] columns;
    private Object[][] rows;

    public Model(String[] columns, Object[][] rows) {
        this.columns = columns;
        this.rows = rows;
    }
    @Override
    public int getRowCount() {
        return this.rows.length;
    }

    @Override
    public int getColumnCount() {
        return this.columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return this.rows[rowIndex][columnIndex];
    }
    public String getColumnName(int col){
        return this.columns[col];
    }

}
