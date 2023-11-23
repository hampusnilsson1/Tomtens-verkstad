package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {
    JFrame logInFrame;
    private JPanel loginPanel;
    private JTextField användarField;
    private JTextField lösenordField;
    private JCheckBox jagHarVaritSnällCheckBox;
    private JButton logInButton;
    private JButton registreraButton;
    private JLabel loginMessage;

    private String användarnamn = "Julen";
    private String lösenord = "2023";

    public Login()
    {
        logInFrame = new JFrame();
        logInFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        logInFrame.setSize(500,500);
        logInFrame.setContentPane(loginPanel);
        logInFrame.setResizable(false);
        logInFrame.setVisible(true);
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {



                if(användarField.getText().equals(användarnamn)
                    && lösenordField.getText().equals(lösenord)){
                    TomtensView tomtensView = new TomtensView();
                }else {
                    loginMessage.setText("Fel inloggningsuppgifter");
                    användarField.setText("");
                    lösenordField.setText("");
                }
            }
        });
        registreraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
