package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Wishlist {

    private DefaultListModel<String> listModel;


    JFrame wishList;
    private JPanel panel1;
    private JButton Add;
    private JButton Remove;
    private JTextField textField1;
    private JList wishlist;



    public Wishlist() {
        wishList = new JFrame();
        wishList.setSize(500, 500);
        wishList.setVisible(true);
        wishList.setContentPane(panel1);
        wishList.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        listModel = new DefaultListModel<>();
        wishlist.setModel(listModel);




        Add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textField1.getText();
                listModel.addElement(text);
                textField1.setText("");


                wishlist.setModel(listModel);
            }
        });


        Remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeSelectedElement();
            }
        });


    }
    private void removeSelectedElement() {
        int selectedIndex = wishlist.getSelectedIndex();
        if (selectedIndex != -1) {
            listModel.remove(selectedIndex);
        }
    }
}

