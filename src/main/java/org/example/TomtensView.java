package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TomtensView extends JFrame{
    JFrame tomtensViewFrame;
    private JPanel tomtensVeiw;
    private JButton lagersaldoButton;
    private JButton loggaUtButton;
    private JPanel JPBarn;
    private JPanel JPButtLager;
    private JPanel JPÖnskeLista;
    private JPanel JPButtLoggaUt;
    private JList list1leksaker;
    private JScrollPane kidsName;


    public TomtensView(){
        tomtensViewFrame = new JFrame();
        tomtensViewFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        tomtensViewFrame.setSize(400, 600);
        tomtensViewFrame.setVisible(true);
        tomtensViewFrame.setContentPane(tomtensVeiw);


        lagersaldoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        loggaUtButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tomtensViewFrame.dispose();
                new Login();

            }
        });
    }

}
