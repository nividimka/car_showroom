package com.example.ui;

import com.example.ServiceLayer.UsersService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class NewUserDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField loginField;
    private JPasswordField password1Field;
    private JPasswordField password2Field;
    private JLabel SuccessField;
    private JPanel pan1;
    private JLabel loginLabel;
    private JLabel password1Label;
    private JLabel password2Label;
    //    private CityService cityService = new CityService();
    private UsersService usersService = new UsersService();


    public NewUserDialog() {
        setTitle("\u0420\u0435\u0433\u0438\u0441\u0442\u0440\u0430\u0446\u0438\u044F \u043D\u043E\u0432\u043E\u0433\u043E \u043F\u043E\u043B\u044C\u0437\u043E\u0432\u0430\u0442\u0435\u043B\u044F");
        contentPane = new JPanel(new GridLayout(0, 2));
        contentPane.setPreferredSize(new Dimension(320,165));
        setContentPane(contentPane);
        setResizable(false);
        setModal(true);
        setLocationRelativeTo(null);
        loginLabel = new JLabel("\u041B\u043E\u0433\u0438\u043D", SwingConstants.CENTER);
        loginLabel.setName("login");
        password1Label= new JLabel("\u041F\u0430\u0440\u043E\u043B\u044C", SwingConstants.CENTER);
        password1Label.setName("password1");
        password2Label= new JLabel("\u041F\u043E\u0432\u0442\u043E\u0440\u0438\u0442\u0435 \u041F\u0430\u0440\u043E\u043B\u044C", SwingConstants.CENTER);
        password2Label.setName("password2");

        loginField = new JTextField();
        password1Field = new JPasswordField();
        password2Field = new JPasswordField();
        contentPane.add(loginLabel);
        contentPane.add(loginField);
        contentPane.add(password1Label);
        contentPane.add(password1Field);
        contentPane.add(password2Label);
        contentPane.add(password2Field);
        buttonCancel = new JButton("\u041E\u0442\u043C\u0435\u043D\u0430");
        buttonOK = new JButton("\u0420\u0435\u0433\u0438\u0441\u0442\u0440\u0430\u0446\u0438\u044F");
        buttonOK.setName("okButton");
        contentPane.add(buttonCancel);
        contentPane.add(buttonOK);
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }

        });
        //
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
        this.pack();
//        this.setVisible(true);
    }

    private void onOK() {
        if (loginField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "\u0412\u0432\u0435\u0434\u0438\u0442\u0435 \u0432\u0441\u0435 \u0434\u0430\u043D\u043D\u044B\u0435", "Ошибка", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (!String.valueOf(password1Field.getPassword()).equals(String.valueOf(password1Field.getPassword())) || password1Field.getPassword().length == 0 || password2Field.getPassword().length == 0) {
            JOptionPane.showMessageDialog(this, "Пароли не совпадают либо не введены", "Ошибка", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
//        if (!cityService.checkCityExists(textField4.getText(), textField5.getText())){
//            JOptionPane.showMessageDialog(this,"Города "+textField4.getText()+" или страны "+ textField5.getText()+" нет в базе","Ошибка",JOptionPane.INFORMATION_MESSAGE);
//            return;
//        }
        boolean res = usersService.newUser(loginField.getText(), String.valueOf(password1Field.getPassword()));
        if (!res) {
            JOptionPane.showMessageDialog(this, "Логин " + loginField.getText() + " уже существует", "Ошибка", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(this, "Пользователь добавлен!", "Успешно", JOptionPane.INFORMATION_MESSAGE);
        onCancel();
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }
}
