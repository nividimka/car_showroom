package com.example.ui;

import javax.swing.*;

public class BorderlessTableDecorator extends BaseDecorator {
    public BorderlessTableDecorator(Drawable drawable) {
        super(drawable);
    }

    @Override
    public JTable getTable() {
        JTable table = super.getTable();
        table.setShowGrid(false);
        return table;
    }
}
