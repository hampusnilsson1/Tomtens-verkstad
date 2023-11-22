package org.example;

import javax.swing.*;

public class Login {
    JFrame logInFrame;
    private JPanel loginPanel;
    private JTextField användarField;
    private JTextField lösenordField;
    private JCheckBox jagHarVaritSnällCheckBox;
    private JButton logInButton;
    private JButton registreraButton;

    public Login()
    {
        logInFrame = new JFrame();
        logInFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        logInFrame.setSize(500,500);
        logInFrame.setContentPane(loginPanel);
        logInFrame.setResizable(false);
        logInFrame.setVisible(true);
    }
}
