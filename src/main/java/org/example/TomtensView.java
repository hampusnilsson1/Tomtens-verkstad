package org.example;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

public class TomtensView extends JFrame{
    JFrame tomtensViewFrame;
    private JPanel tomtensView;
    private JButton stock;
    private JList wishesJList;
    private JButton logOut;
    private JList kidsJList;
    DefaultListModel<String> kidsListModel;
    DefaultListModel<String> wishesListModel;


    public TomtensView(ArrayList<Kid> kidObjList){
        tomtensViewFrame = new JFrame();
        tomtensViewFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        tomtensViewFrame.setSize(400, 600);
        tomtensViewFrame.setVisible(true);
        tomtensViewFrame.setContentPane(tomtensView);
        wishesListModel = new DefaultListModel<>();
        wishesJList.setModel(wishesListModel);
        kidsListModel = new DefaultListModel<>();
        kidsJList.setModel(kidsListModel);



        loadKidsToList(kidObjList);


        stock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tomtensViewFrame.dispose();
            }
        });


        logOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login();
                tomtensViewFrame.dispose();
            }
        });

        kidsJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                wishesListModel.clear();
                String selectedKid = (String)kidsJList.getSelectedValue();
                for (Kid kid:kidObjList) {
                    if(kid.getKidName().equals(selectedKid))
                    {
                        System.out.println("Got him "+kid.getKidName());
                        for (Map.Entry<String, Boolean> currentWish : kid.getWishList().entrySet()) {
                            wishesListModel.addElement(currentWish.getKey());
                        }
                    }
                }
            }
        });
    }

    void loadKidsToList(ArrayList<Kid> kidObjList)
    {
        kidsJList.setModel(kidsListModel);//Load JList Model to above
        for (Kid kid:kidObjList)
            kidsListModel.addElement(kid.getKidName());
    }

}
