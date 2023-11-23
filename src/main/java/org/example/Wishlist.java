package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Wishlist {

    private DefaultListModel<String> listModel;
    private DefaultListModel<String> availableWishes;

    JFrame wishList;
    private JPanel panel1;
    private JButton Add;
    private JButton Remove;
    private JList wishlist;
    private JList listOfAvailableWishes;
    private JLabel ErrorMessage;
    File wishlistFile = new File("wish_list_for_kids.txt");
    ArrayList<Wish> wishes = new ArrayList<>();

    public Wishlist() {
        wishList = new JFrame();
        wishList.setSize(500, 500);
        wishList.setVisible(true);
        wishList.setContentPane(panel1);
        wishList.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        listModel = new DefaultListModel<>();
        wishlist.setModel(listModel);

        availableWishes = new DefaultListModel<>();
        listOfAvailableWishes.setModel(availableWishes);


        try {
            FileReader fileReader = new FileReader(wishlistFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] variables = line.split(",");

                int wishID = Integer.parseInt(variables[0]);
                String wishName = variables[1];
                int quantity = Integer.parseInt(variables[2].trim());

                Wish wish = new Wish(wishID, wishName, quantity);
                wishes.add(wish);

                line = bufferedReader.readLine();
            }
            listWishList();
        }
        catch (NumberFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listModel.size() == 10) {
                    ErrorMessage.setText("Du har redan gjort 10 önskningar. Ta bort ifån listan innan du lägger till fler.");
                }
                else {
                    String add = String.valueOf(listOfAvailableWishes.getSelectedValue());
                    listModel.addElement(add);
                }
            }
        });
        Remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String remove = String.valueOf(wishlist.getSelectedValue());
                listModel.removeElement(remove);
                ErrorMessage.setText("");
            }
        });
    }

    public void listWishList() {
        for (Wish wish: wishes) {
            availableWishes.addElement(wish.getWishId() + " " + wish.getWishName());
        }
    }



}

