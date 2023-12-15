package org.example;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Login {

    JFrame logInFrame;
    private JPanel loginPanel;
    private JTextField userField;
    private JCheckBox iHaveBeenGoodCheckBox;
    private JButton logInButton;
    private JButton registreraButton;
    private JLabel loginMessage;
    private JPasswordField passwordField1;
    private JRadioButton mute;
    private String adminUserName = "Julen";
    private String adminPassword = "2023";

    Map<String, String> userCredentials;

    ArrayList<Kid> kidObjList = new ArrayList<>(); // HÄR LADDAS ALLA BARN IN TILL NÄR LOGIN LADDAS


    public void loadAllData() {
        userCredentials = readUserCredentials("Kids.csv");
        loadWishLists("wish_list.txt", userCredentials);
        System.out.println(kidObjList);
        // Allt ska nu va sparat under kidObjList
    }

    private Map<String, String> readUserCredentials(String filePath) {
        Map<String, String> userCredentials = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine();//Hoppa överförsta raden
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                String[] parts = line.split(",");
                String username = parts[1].trim();
                String password = parts[2].trim();
                userCredentials.put(username, password);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userCredentials;
    }

    private void loadWishLists(String filePath, Map<String, String> userCredentials) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] variables = line.split(",");
                String name = variables[0];

                //Kollar vilket konto som finns med samma namn som wishlist.
                if (userCredentials.containsKey(name)) {
                    String password = userCredentials.get(name);
                    HashMap<String, Boolean> wishes = new HashMap<>();
                    for (int varNum = 1; varNum < variables.length; varNum += 2) {
                        String wishName = variables[varNum];
                        boolean wishStatus = Boolean.valueOf(variables[varNum + 1]);
                        wishes.put(wishName, wishStatus);
                    }

                    // Skapa Kid-objekt och lägg till i listan
                    Kid kid = new Kid(name, password, wishes);
                    kidObjList.add(kid);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    MusicPlayer musicPlayer=new MusicPlayer();

    public Login() {
        logInFrame = new JFrame();
        logInFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        logInFrame.setSize(500, 500);
        logInFrame.setContentPane(loginPanel);
        logInFrame.setResizable(false);
        logInFrame.setVisible(true);

        loadAllData();

        musicPlayer.startMusic();

        registreraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Register(userCredentials);
                logInFrame.dispose();
            }
        });

        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredUsername = userField.getText();
                String enteredPassword = passwordField1.getText();

                if (enteredUsername.equals(adminUserName) && enteredPassword.equals(adminPassword)) {
                    new TomtensView(kidObjList, musicPlayer); // FEED KIDS WITH THEIR WISHLIST.
                    logInFrame.dispose();
                } else if (userCredentials.containsKey(enteredUsername) &&
                        userCredentials.get(enteredUsername).equals(enteredPassword) && iHaveBeenGoodCheckBox.isSelected()) {
                    Wishlist wishlist = new Wishlist(enteredUsername,musicPlayer);
                    logInFrame.dispose();
                } else if (!iHaveBeenGoodCheckBox.isSelected() && !enteredUsername.equals(adminUserName)) {
                    loginMessage.setText("Vänligen kryssa i 'Jag har varit snäll' för att logga in!");
                } else {
                    loginMessage.setText("Fel inloggningsuppgifter!");
                    userField.setText("");
                    passwordField1.setText("");
                }
            }
        });


        mute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(mute.isSelected()){
                    musicPlayer.stopMusic();
                }else{
                    musicPlayer.startMusic();
                }

            }
        });
    }
}
