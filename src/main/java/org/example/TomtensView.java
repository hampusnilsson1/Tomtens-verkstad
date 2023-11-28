package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TomtensView extends JFrame{
    JFrame tomtensViewFrame;
    private JPanel tomtensVeiw;
    private JButton stock;
    private JList wishList;
    private JScrollPane kidsName;
    private JButton logOut;


    public TomtensView(){
        tomtensViewFrame = new JFrame();
        tomtensViewFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        tomtensViewFrame.setSize(400, 600);
        tomtensViewFrame.setVisible(true);
        tomtensViewFrame.setContentPane(tomtensVeiw);


        stock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        logOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

}
