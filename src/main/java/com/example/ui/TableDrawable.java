package com.example.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class TableDrawable implements Drawable {
    private Object[][] data;
    private String[] columns;
    private List<Integer> editableCells = new ArrayList<>();

    TableDrawable(Object[][] data, String[] columns) {
        this.columns = columns;
        this.data = data;
    }

    @Override
    public Object[][] getModel() {
        return data;
    }

    @Override
    public String[] columnNames() {
        return columns;
    }

    @Override
    public JTable getTable() {
        return new JTable(getTableModel());
    }

    @Override
    public CustomTableModel getTableModel() {
        return new CustomTableModel(getModel(), columnNames()) {
            private static final long serialVersionUID = 1L;
        };
    }
}
