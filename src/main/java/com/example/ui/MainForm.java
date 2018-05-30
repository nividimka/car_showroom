package com.example.ui;

import com.example.ServiceLayer.CarService;
import com.example.ServiceLayer.OrderService;
import com.example.ServiceLayer.UsersService;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by yuraf_000 on 19.06.2014.
 */
public class MainForm extends JFrame {
    private JPanel rootPanel;
    private JTabbedPane tabbedPane1;
    private JLabel UserLabel;
    private JTextField searchCityField;
    private JButton forecastButton;
    private JTable autoTable;
    private JScrollPane JscrollPane1;
    private JPanel JPanel1;
    private JButton exitProfileButton;
    private JTable orderTable;
    private JLabel label1;
    private JButton newAutoButton;
    private JButton newOrderButton;
    private UsersService usersService = new UsersService();
    private CarService carService = new CarService();
    private OrderService orderService = new OrderService();
    //final MainForm rootForm = this;


    public MainForm(Object user) {
        $$$setupUI$$$();
        usersService.setCurrentUser(user);
        setTitle("Аренда автомобилей");
        setSize(800, 450);
        setLocationRelativeTo(null);
        setContentPane(rootPanel);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        UserLabel.setText(usersService.getCurrentUserLogin());

        //actual data for the table in a 2d array
        addListeners();
        pack();
        setVisible(true);
    }

    private void addListeners() {
        searchCityField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String searchText = searchCityField.getText();
                if (searchText.contains(",")) {
                    searchCityField.setText(searchText.substring(0, searchText.indexOf(',')));
                }
            }
        });
        searchCityField.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent focusEvent) {

            }

            @Override
            public void focusLost(FocusEvent focusEvent) {

            }
        });

        exitProfileButton.addActionListener(actionEvent -> {
            dispose();
            SwingUtilities.invokeLater(() -> {
                AuthorizationForm authorizationForm = new AuthorizationForm();
            });
        });
        newAutoButton.addActionListener(e -> SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                NewAutoDialog dialog = new NewAutoDialog();
                dialog.setOnCarAddListener(carModel -> {
                    ((DefaultTableModel) autoTable.getModel()).addRow(carModel.toObject());
                });
                dialog.pack();
                dialog.setVisible(true);

            }
        }));
        newOrderButton.addActionListener(e -> SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                NewOrderDialog dialog = new NewOrderDialog();
                dialog.setOnOrderAddListener(orderModel -> {
                    ((DefaultTableModel) orderTable.getModel()).addRow(orderModel.toObject());
                });
                dialog.pack();
                dialog.setVisible(true);

            }
        }));
    }

    public static void main(String[] args) {

    }

    private void createUIComponents() {
        String[] columnNames = {"id", "Название машины", "Дата создания", "Категория", "Номер машины", "Производитель", "Стоимость руб/ч", "Редактировать", "Удалить"};
        Object[][] data = carService.getCarTableObjects();
        TableModel model = new DefaultTableModel(data, columnNames) {
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return column == 7 || column == 8;
            }
        };
        autoTable = new JTable(model);
        autoTable.getColumnModel().getColumn(7).setCellRenderer(new ClientsTableButtonRenderer());
        autoTable.getColumnModel().getColumn(7).setCellEditor(new ClientsTableRenderer(new JCheckBox(), (id, row) -> {
            EditAutoDialog dialog = new EditAutoDialog(id);
            dialog.setOnCarAddListener(carModel -> {
                ((DefaultTableModel) autoTable.getModel()).removeRow(row);
                ((DefaultTableModel) autoTable.getModel()).addRow(carModel.toObject());
                ((DefaultTableModel) autoTable.getModel()).moveRow(autoTable.getModel().getRowCount() - 1, autoTable.getModel().getRowCount() - 1, row);
            });
            dialog.pack();
            dialog.setVisible(true);
        }));
        autoTable.getColumnModel().getColumn(8).setCellRenderer(new ClientsTableButtonRenderer());
        autoTable.getColumnModel().getColumn(8).setCellEditor(new ClientsTableRenderer(new JCheckBox(), (id, row) -> {
            boolean res = carService.deleteCar(id);
            if (res) {
                JOptionPane.showMessageDialog(this, "Автомобиль успешно удален", "Успешно", JOptionPane.INFORMATION_MESSAGE);
                ((DefaultTableModel) autoTable.getModel()).removeRow(row);
            } else {
                JOptionPane.showMessageDialog(this, "Произошла ошибка удаления", "Ошибка", JOptionPane.INFORMATION_MESSAGE);
            }
        }));
        String[] orderColumnNames = {"id", "Название машины", "Имя пользователя", "Дата начала проката", "Дата окончания проката"};
        Object[][] orderData = orderService.getOrderTableObjects();
        TableModel orderModel = new DefaultTableModel(orderData, orderColumnNames) {
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        orderTable = new JTable(orderModel);
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        rootPanel = new JPanel();
        rootPanel.setLayout(new GridLayoutManager(4, 7, new Insets(15, 15, 15, 15), -1, -1));
        rootPanel.setMinimumSize(new Dimension(-1, -1));
        rootPanel.setPreferredSize(new Dimension(800, 450));
        tabbedPane1 = new JTabbedPane();
        rootPanel.add(tabbedPane1, new GridConstraints(3, 0, 1, 6, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(367, 200), null, 0, false));
        JPanel1 = new JPanel();
        JPanel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPane1.addTab("Авто", JPanel1);
        JscrollPane1 = new JScrollPane();
        JPanel1.add(JscrollPane1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        autoTable.setRowSelectionAllowed(true);
        JscrollPane1.setViewportView(autoTable);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPane1.addTab("Заказы", panel1);
        final JScrollPane scrollPane1 = new JScrollPane();
        panel1.add(scrollPane1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        scrollPane1.setViewportView(orderTable);
        final Spacer spacer1 = new Spacer();
        rootPanel.add(spacer1, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, new Dimension(20, -1), null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        rootPanel.add(spacer2, new GridConstraints(3, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 300), null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Автомобиль");
        rootPanel.add(label2, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        searchCityField = new JTextField();
        rootPanel.add(searchCityField, new GridConstraints(2, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, -1), null, 0, false));
        forecastButton = new JButton();
        forecastButton.setText("Найти");
        rootPanel.add(forecastButton, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        exitProfileButton = new JButton();
        exitProfileButton.setText("Выйти");
        rootPanel.add(exitProfileButton, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, new Dimension(80, -1), 0, false));
        UserLabel = new JLabel();
        UserLabel.setText("");
        rootPanel.add(UserLabel, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 14), null, 0, false));
        label1 = new JLabel();
        label1.setText("Пользователь:");
        rootPanel.add(label1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        newAutoButton = new JButton();
        newAutoButton.setText("Добавить Авто");
        rootPanel.add(newAutoButton, new GridConstraints(2, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        newOrderButton = new JButton();
        newOrderButton.setText("Добавить заказ");
        rootPanel.add(newOrderButton, new GridConstraints(1, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }

    //    private void updateUser (){
//        user = db.getUser(user.getUserLogin());
//    }

}

