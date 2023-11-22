import javax.swing.*;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Register {

    JFrame RegisterFrame;
    JTextField UsernameField;
    JPasswordField PasswordField;
    JPasswordField RePasswordField;

    public Register() {
        RegisterFrame = new JFrame("Registration");
        UsernameField = new JTextField();
        PasswordField = new JPasswordField();
        RePasswordField = new JPasswordField();

        JLabel UsernameLabel = new JLabel("Username:");
        JLabel PasswordLabel = new JLabel("Password:");
        JLabel RePasswordLabel = new JLabel("Re-enter Password:");
        JButton RegisterButton = new JButton("Register");
        // Actionlistener needed

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
        RegisterFrame.setSize(300, 150);
        RegisterFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        RegisterFrame.setVisible(true);
    }
}