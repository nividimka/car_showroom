package com.example.ui;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.util.ArrayList;
import java.util.List;

public class EditDecorator extends BaseDecorator {

    public EditDecorator(Drawable drawable) {
        super(drawable);
    }


    @Override
    public JTable getTable() {
        JTable table = super.getTable();
        table.setModel(getTableModel());
        return table;
    }

    @Override
    public CustomTableModel getTableModel() {
        CustomTableModel tableModel = super.getTableModel();
        List<String> colData = new ArrayList<>(super.getTable().getRowCount());
        for (int i = 0; i < super.getTable().getRowCount(); i++) {
            colData.add("Edit");
        }
        tableModel.addColumn("Редактировать", colData.toArray());
        tableModel.addColumnEditable(tableModel.getColumnCount() - 1);
        return tableModel;
    }
}
