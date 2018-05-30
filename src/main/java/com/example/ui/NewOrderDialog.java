package com.example.ui;

import com.example.ServiceLayer.CarService;
import com.example.ServiceLayer.OrderService;
import com.example.ServiceLayer.UsersService;
import com.example.model.*;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NewOrderDialog extends JDialog {

    private JPanel contentPane;
    private JPanel pan1;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox<CarModel> comboBox1;
    private JButton addCar;
    private JComboBox<UserModel> comboBox2;
    private JButton addManufacturer;
    private JPanel creationDatePanel;
    private JPanel expirationDatePanel;
    private JTextField name;
    JDatePickerImpl creationDatePicker;
    JDatePickerImpl expirationDatePicker;
    CarService carService = new CarService();
    UsersService usersService = new UsersService();
    OrderService orderService = new OrderService();

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        contentPane = new JPanel();
        contentPane.setLayout(new GridLayoutManager(1, 1, new Insets(10, 10, 10, 10), -1, -1));
        panel1.add(contentPane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        pan1 = new JPanel();
        pan1.setLayout(new GridLayoutManager(5, 4, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(pan1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Название машины");
        pan1.add(label1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        pan1.add(spacer1, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(0, 25), new Dimension(0, 25), new Dimension(0, 25), 0, false));
        comboBox1 = new JComboBox();
        pan1.add(comboBox1, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(100, -1), null, null, 0, false));
        addCar = new JButton();
        addCar.setText("Добавить");
        pan1.add(addCar, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Дата создания");
        pan1.add(label2, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pan1.add(creationDatePanel, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Дата окончания");
        pan1.add(label3, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pan1.add(expirationDatePanel, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Пользователь");
        pan1.add(label4, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        comboBox2 = new JComboBox();
        pan1.add(comboBox2, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(panel3, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonOK = new JButton();
        buttonOK.setText("Создать");
        panel3.add(buttonOK, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonCancel = new JButton();
        buttonCancel.setText("Отмена");
        panel3.add(buttonCancel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    //    OrderService carService = new OrderService();

    public interface OnOrderAddListener {
        public void onAdd(OrderModel orderModel);
    }

    OnOrderAddListener onOrderAddListener;

    public void setOnOrderAddListener(OnOrderAddListener onOrderAddListener) {
        this.onOrderAddListener = onOrderAddListener;
    }

    public NewOrderDialog() {
        $$$setupUI$$$();
        setTitle("Добавление нового заказа");
        setContentPane(contentPane);
        setResizable(false);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setLocationRelativeTo(null);
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        addListeners();
        initItems();
    }

    private void initItems() {
        List<CarModel> carNumbers = carService.getCars();
        for (CarModel model : carNumbers) {
            comboBox1.addItem(model);
        }
        List<UserModel> users = usersService.getUserList();
        for (UserModel model : users) {
            comboBox2.addItem(model);
        }
    }


    private void addListeners() {
        addCar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                NewAutoDialog dialog = new NewAutoDialog();
                dialog.setOnCarAddListener(car -> {
                    comboBox1.addItem(car);
                    comboBox1.setSelectedIndex(comboBox1.getItemCount() - 1);
                });
                dialog.pack();
                dialog.setVisible(true);
            }
        });
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        if (comboBox1.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this, "Добавьте пожалуйста машину которую вы хотите арендовать", "Ошибка", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (comboBox2.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this, "Добавьте пожалуйста пользователя", "Ошибка", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        Date creationDate = (Date) creationDatePicker.getModel().getValue();
        if (creationDate == null) {
            JOptionPane.showMessageDialog(this, "Дата начала заказа не введена", "Ошибка", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        Date expirationDate = (Date) expirationDatePicker.getModel().getValue();
        if (expirationDate == null) {
            JOptionPane.showMessageDialog(this, "Дата окончания заказа не введена", "Ошибка", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        CarModel carModel = comboBox1.getItemAt(comboBox1.getSelectedIndex());
        UserModel userModel = comboBox2.getItemAt(comboBox2.getSelectedIndex());
        long orderId = orderService.newOrder(carModel, userModel, creationDate, expirationDate);
        if (orderId == -1) {
            JOptionPane.showMessageDialog(this, "Не удалось создать заказ", "Ошибка", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (onOrderAddListener != null) {
            onOrderAddListener.onAdd(orderService.getOrder(orderId));
        }
        JOptionPane.showMessageDialog(this, "Заказ успешно добавлен!", "Успешно", JOptionPane.INFORMATION_MESSAGE);
        onCancel();
    }


    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    public static void main(String[] args) {

    }

    private void createUIComponents() {
        DateFormat format = new SimpleDateFormat("yyyy/mm/dd");
        // TODO: place custom component creation code here
        creationDatePanel = new JPanel();
        UtilDateModel creationModel = new UtilDateModel();
        JDatePanelImpl creationPanel = new JDatePanelImpl(creationModel);
        creationDatePicker = new JDatePickerImpl(creationPanel);
        creationDatePanel.add(creationDatePicker);
        expirationDatePanel = new JPanel();
        UtilDateModel expirationModel = new UtilDateModel();
        JDatePanelImpl expirationPanel = new JDatePanelImpl(expirationModel);
        expirationDatePicker = new JDatePickerImpl(expirationPanel);
        expirationDatePanel.add(expirationDatePicker);
    }

}
