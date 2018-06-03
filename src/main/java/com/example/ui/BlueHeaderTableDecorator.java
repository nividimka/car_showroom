package com.example.ui;

import javax.swing.*;
import java.awt.*;

public class BlueHeaderTableDecorator extends BaseDecorator {
    public BlueHeaderTableDecorator(Drawable drawable) {
        super(drawable);
    }

    @Override
    public JTable getTable() {
        JTable table = super.getTable();
        table.getTableHeader().setOpaque(false);
        table.getTableHeader().setBackground(Color.BLUE);
        return table;
    }
}
