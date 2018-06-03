package com.example.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class DeleteDecorator extends BaseDecorator {

    public DeleteDecorator(Drawable drawable) {
        super(drawable);
    }


    @Override
    public JTable getTable() {
        JTable table = super.getTable();
        table.setModel(getTableModel());
        table.getColumnModel().getColumn(table.getColumnCount()-1).setCellRenderer(new ClientsTableButtonRenderer());
        table.getColumnModel().getColumn(table.getColumnCount()-1).setCellEditor(new ClientsTableRenderer(new JCheckBox(), (id, row) -> {
            System.out.println("DELETE");
        }));
        return table;
    }

    @Override
    public CustomTableModel getTableModel() {
        CustomTableModel tableModel = super.getTableModel();
        List<String> colData = new ArrayList<>(super.getTable().getRowCount());
        for (int i = 0; i < super.getTable().getRowCount(); i++) {
            colData.add("Delete");
        }
        tableModel.addColumn("Удалить", colData.toArray());
        tableModel.addColumnEditable(tableModel.getColumnCount()-1);
        return tableModel;
    }
}
