package com.example.ui;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class CustomTableModel extends DefaultTableModel {
    public CustomTableModel() {
    }

    public CustomTableModel(int rowCount, int columnCount) {
        super(rowCount, columnCount);
    }

    public CustomTableModel(Vector columnNames, int rowCount) {
        super(columnNames, rowCount);
    }

    public CustomTableModel(Object[] columnNames, int rowCount) {
        super(columnNames, rowCount);
    }

    public CustomTableModel(Vector data, Vector columnNames) {
        super(data, columnNames);
    }

    public CustomTableModel(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }

    List<Integer> columns = new ArrayList<>();

    @Override
    public boolean isCellEditable(int row, int column) {
        for (Integer column1 : columns) {
            if (column1 == column) {
                return true;
            }
        }
        return false;
    }

    public void addColumnEditable(int column) {
        columns.add(column);
        for (int i = 0; i < getRowCount(); i++) {
            this.fireTableCellUpdated(i, column);
        }
    }
}
