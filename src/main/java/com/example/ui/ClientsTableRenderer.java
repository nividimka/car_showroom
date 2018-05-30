package com.example.ui;

import javafx.event.ActionEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ClientsTableRenderer extends DefaultCellEditor {
    private JButton button;
    private String label;
    private boolean clicked;
    private int row, col;
    private JTable table;
    public interface  OnButtonClickListener{
        public void onClick(long id,int row);
    }
    public OnButtonClickListener onClickListener;


    public ClientsTableRenderer(JCheckBox checkBox,OnButtonClickListener onClickListener) {
        super(checkBox);
        button = new JButton();
        this.onClickListener = onClickListener;
        button.setOpaque(true);
        button.addActionListener(e -> fireEditingStopped());
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.table = table;
        this.row = row;
        this.col = column;

        button.setForeground(Color.black);
        button.setBackground(UIManager.getColor("Button.background"));
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        clicked = true;
        return button;
    }

    public Object getCellEditorValue() {
        if (clicked) {
        if(onClickListener!=null){
            onClickListener.onClick(Long.parseLong(String.valueOf(table.getValueAt(row, 0))),row);
        }
        }
        clicked = false;
        return new String(label);
    }

    public boolean stopCellEditing() {
        clicked = false;
        return super.stopCellEditing();
    }

    protected void fireEditingStopped() {
        try {
            super.fireEditingStopped();
        }catch (Exception ignored){}
    }
}
