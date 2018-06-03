package com.example.ui;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.HashMap;
import java.util.List;

public class BaseDecorator implements Drawable {
    private Drawable drawable;
    HashMap<Integer, ClickListener> listeners = new HashMap<>();

    public BaseDecorator(Drawable drawable) {
        this.drawable = drawable;
    }

    @Override
    public Object[][] getModel() {
        return drawable.getModel();
    }

    @Override
    public String[] columnNames() {
        return drawable.columnNames();
    }

    @Override
    public JTable getTable() {
        return drawable.getTable();
    }

    @Override
    public CustomTableModel getTableModel() {
        return drawable.getTableModel();
    }

    public void addListener(int id,ClickListener clickListener){
        listeners.put(id, clickListener);
    }

    public interface ClickListener{
        public void onClick(long id);
    }
}
