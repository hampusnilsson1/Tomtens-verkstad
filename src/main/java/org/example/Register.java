package org.example;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class Register {

    JFrame RegisterFrame;
    JTextField UsernameField;
    JPasswordField PasswordField;
    JPasswordField RePasswordField;
    Map<String, String> KidHashMap ;


    public Register(Map<String, String> userCredentials ) {
        KidHashMap = userCredentials ;
        RegisterFrame = new JFrame("KontoRegistrering");
        UsernameField = new JTextField();
        PasswordField = new JPasswordField();
        RePasswordField = new JPasswordField();

        JLabel UsernameLabel = new JLabel("Användarnamn:");
        JLabel PasswordLabel = new JLabel("Lösenord:");
        JLabel RePasswordLabel = new JLabel("Repetera Lösenord:");
        JButton RegisterButton = new JButton("Registera");
        ActionListener RegisterAL = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String Username = UsernameField.getText();
                String Password = new String(PasswordField.getPassword());
                String RePassword = new String(RePasswordField.getPassword());

                if(Username.length() < 3) {
                    showMessage("Felmeddelande", "Användarnamnet måste bestå av minst 3 tecken!",false);

                }
                else if(!(KidHashMap.get(Username) == null)) {
                    showMessage("Felmeddelande", "Användarnamnet är redan taget!",false);

                }
                else if(Password.length() < 8) {
                    showMessage("Felmeddelande", "Ditt lösenord måste vara minst 8 tecken långt!",false);
                    
                }
                else if(Password.equals(RePassword)) {
                    Kid kid = new Kid(Username, Password);
                    KidHashMap.put(Username, Password);
                    addToCSV(kid);
                    addToTxt(kid);
                    PasswordField.setText("");
                    UsernameField.setText("");
                    RePasswordField.setText("");
                    showMessage("Registrering klar!", ("Du är nu registrerad under användarnamnet: " + Username),true);

                } 
                else {
                    showMessage("Felmeddelande", "Lösenorden stämmer inte överens!",false);
                }
            }

        };
        RegisterButton.addActionListener(RegisterAL);

        JPanel RegisterPanel = new JPanel();
        RegisterPanel.setLayout(new GridLayout(4,2,10,10));
        RegisterPanel.add(UsernameLabel);
        RegisterPanel.add(UsernameField);
        RegisterPanel.add(PasswordLabel);
        RegisterPanel.add(PasswordField);
        RegisterPanel.add(RePasswordLabel);
        RegisterPanel.add(RePasswordField);
        RegisterPanel.add(new JLabel()); 
        RegisterPanel.add(RegisterButton);
        
        RegisterFrame.getContentPane().add(RegisterPanel);
        RegisterFrame.setSize(400, 150);
        RegisterFrame.setResizable(false);
        RegisterFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        RegisterFrame.setVisible(true);
    }

    private void addToCSV(Kid kid) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Kids.csv", true))) {
            writer.write(kid.getKidId() + "," + kid.getKidName() + "," + kid.getKidPassword());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addToTxt(Kid kid) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("wish_list.txt", true))) {
            writer.write(kid.getKidName());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showMessage(String title, String message,boolean close) {
        JOptionPane.showMessageDialog(RegisterFrame, message, title, JOptionPane.INFORMATION_MESSAGE);
        if(close){
            new Login();
            RegisterFrame.dispose();
        }
    }
}