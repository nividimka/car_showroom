package com.example.ui;

import com.example.ServiceLayer.CarService;
import com.example.model.CarModel;
import com.example.model.CarNumberModel;
import com.example.model.CategoryModel;
import com.example.model.ManufacturerModel;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EditAutoDialog extends JDialog {

    private JPanel contentPane;
    private JPanel pan1;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox<CarNumberModel> comboBox1;
    private JButton addNumber;
    private JButton addCategory;
    private JComboBox<CategoryModel> comboBox2;
    private JComboBox<ManufacturerModel> comboBox3;
    private JButton addManufacturer;
    private JPanel creationDatePanel;
    private JTextField name;
    private JTextField price;
    JDatePickerImpl creationDatePicker;
    CarService carService = new CarService();
    long carId;

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
        pan1.setLayout(new GridLayoutManager(7, 4, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(pan1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Название авто");
        pan1.add(label1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Номер авто");
        pan1.add(label2, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Категория авто");
        pan1.add(label3, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        pan1.add(spacer1, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(0, 25), new Dimension(0, 25), new Dimension(0, 25), 0, false));
        comboBox1 = new JComboBox();
        pan1.add(comboBox1, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        addNumber = new JButton();
        addNumber.setText("Добавить");
        pan1.add(addNumber, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        comboBox2 = new JComboBox();
        pan1.add(comboBox2, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        addCategory = new JButton();
        addCategory.setText("Добавить");
        pan1.add(addCategory, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Производитель");
        pan1.add(label4, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        comboBox3 = new JComboBox();
        pan1.add(comboBox3, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        addManufacturer = new JButton();
        addManufacturer.setText("Добавить");
        pan1.add(addManufacturer, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Дата создания");
        pan1.add(label5, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pan1.add(creationDatePanel, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        name = new JTextField();
        pan1.add(name, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Стоимость(руб/час)");
        pan1.add(label6, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        price = new JTextField();
        pan1.add(price, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(panel3, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonOK = new JButton();
        buttonOK.setText("Изменить");
        panel3.add(buttonOK, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonCancel = new JButton();
        buttonCancel.setText("Отмена");
        panel3.add(buttonCancel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    public interface OnCarAddListener {
        public void onAdd(CarModel carModel);
    }

    OnCarAddListener onCarAddListener;

    public void setOnCarAddListener(OnCarAddListener onCarAddListener) {
        this.onCarAddListener = onCarAddListener;
    }

    public EditAutoDialog(long carId) {
        $$$setupUI$$$();
        this.carId = carId;
        setTitle("Редактирование автомобиля");
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
        initItems(carId);
    }

    private void initItems(long id) {
        CarModel carModel = carService.getCar(id);
        List<CarNumberModel> carNumbers = carService.getCarNumberList();
        for (int i = 0; i < carNumbers.size(); i++) {
            CarNumberModel model = carNumbers.get(i);
            comboBox1.addItem(model);
            if (carModel.getCarNumber().getId() == model.getId()) {
                comboBox1.setSelectedIndex(i);
            }
        }
        List<CategoryModel> categories = carService.getCarCategoryList();
        for (int i = 0; i < categories.size(); i++) {
            CategoryModel model = categories.get(i);
            comboBox2.addItem(model);
            if (carModel.getCategory().getId() == model.getId()) {
                comboBox2.setSelectedIndex(i);
            }
        }
        List<ManufacturerModel> manufacturers = carService.getManufacturerList();
        for (int i = 0; i < manufacturers.size(); i++) {
            ManufacturerModel model = manufacturers.get(i);
            comboBox3.addItem(model);
            if (carModel.getManufacturer().getId() == model.getId()) {
                comboBox3.setSelectedIndex(i);
            }
        }
        price.setText(String.valueOf(carModel.getPrice()));
        name.setText(carModel.getName());
        Date date = carModel.getCreationDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        creationDatePicker.getModel().setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        creationDatePicker.getModel().setSelected(true);
    }


    private void addListeners() {
        addNumber.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                NewCarNumberDialog dialog = new NewCarNumberDialog();
                dialog.setOnAddListener(carNumberModel -> {
                    comboBox1.addItem(carNumberModel);
                    comboBox1.setSelectedIndex(comboBox1.getItemCount() - 1);
                });
                dialog.pack();
                dialog.setVisible(true);
            }
        });
        addCategory.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                NewCarCategoryDialog dialog = new NewCarCategoryDialog();
                dialog.setOnAddListener(carCategoryModel -> {
                    comboBox2.addItem(carCategoryModel);
                    comboBox2.setSelectedIndex(comboBox2.getItemCount() - 1);
                });
                dialog.pack();
                dialog.setVisible(true);
            }
        });
        addManufacturer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                NewCarManufacturerDialog dialog = new NewCarManufacturerDialog();
                dialog.setOnAddListener(manufacturerModel -> {
                    comboBox3.addItem(manufacturerModel);
                    comboBox3.setSelectedIndex(comboBox3.getItemCount() - 1);
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
        if (name.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Введите пожалуйста название машины", "Ошибка", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (comboBox1.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this, "Добавьте пожалуйста номер машины", "Ошибка", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (comboBox2.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this, "Добавьте пожалуйста категорию машины", "Ошибка", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (comboBox3.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this, "Добавьте пожалуйста производителя машины", "Ошибка", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        Date creationDate = (Date) creationDatePicker.getModel().getValue();
        if (creationDate == null) {
            JOptionPane.showMessageDialog(this, "Дата создания машины не введена", "Ошибка", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        Double priceDouble = null;
        try {
            priceDouble = Double.parseDouble(price.getText());
        } catch (NumberFormatException ignored) {
        }
        if (priceDouble == null) {
            JOptionPane.showMessageDialog(this, "Цена автомобиля не соответствует формату", "Ошибка", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        long carId = carService.updateCar(this.carId, name.getText(),
                comboBox1.getItemAt(comboBox1.getSelectedIndex()),
                comboBox2.getItemAt(comboBox2.getSelectedIndex()),
                comboBox3.getItemAt(comboBox3.getSelectedIndex()),
                creationDate, priceDouble);
        if (carId == -1) {
            JOptionPane.showMessageDialog(this, "Не удалось обновить машину", "Ошибка", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (onCarAddListener != null) {
            onCarAddListener.onAdd(carService.getCar(carId));
        }
        JOptionPane.showMessageDialog(this, "Автомобиль успешно обновлен!", "Успешно", JOptionPane.INFORMATION_MESSAGE);
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
    }

}
