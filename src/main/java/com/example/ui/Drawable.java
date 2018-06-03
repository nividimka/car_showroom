package com.example.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.List;

public interface Drawable {

    public Object[][] getModel();
    public String[] columnNames();
    public JTable getTable();
    public CustomTableModel getTableModel();
}
