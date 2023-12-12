package org.example;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

public class TomtensView extends JFrame{
    JFrame tomtensViewFrame;
    private JPanel tomtensView;
    private JButton stock;
    private JList wishesJList;

    private JList<String> kidsJList;
    private JButton logOut;
    private JPanel kidScrollPanel;
    private JButton skickaBtn;
    DefaultListModel<String> kidsListModel;
    DefaultListModel<String> wishesListModel;

    String selectedKidName = "";
    Kid selectedKid = null;


    public TomtensView(ArrayList<Kid> kidObjList){
        tomtensViewFrame = new JFrame();
        tomtensViewFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        tomtensViewFrame.setSize(550, 600);
        tomtensViewFrame.setVisible(true);
        tomtensViewFrame.setContentPane(tomtensView);
        tomtensViewFrame.setResizable(false);

        wishesListModel = new DefaultListModel<>();
        wishesJList.setModel(wishesListModel);

        kidsListModel = new DefaultListModel<>();
        kidsJList = new JList<>(kidsListModel);
        kidsJList.setModel(kidsListModel);
        loadKidsToList(kidObjList);

        JScrollPane scrollPane = new JScrollPane(kidsJList);
        kidScrollPanel.add(scrollPane,BorderLayout.CENTER);



        stock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LagerCheck lagerCheck = new LagerCheck(wishesListModel);
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
                selectedKidName = (String)kidsJList.getSelectedValue();
                for (Kid kid:kidObjList) {
                    if(kid.getKidName().equals(selectedKidName))
                    {
                        selectedKid = kid;
                        System.out.println("Got him "+kid.getKidName());
                        for (Map.Entry<String, Boolean> currentWish : kid.getWishList().entrySet()) {
                            if(currentWish.getValue() == true)
                                wishesListModel.addElement(currentWish.getKey()+" är nu skickad!");
                            else
                                wishesListModel.addElement(currentWish.getKey());
                        }
                    }
                }
            }
        });
        skickaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Säkerställer att koden som påverkar Swing-komponenter exekveras på EDT och undviker potentiella !trådrelaterade! problem. Därför kör invokeLater()!
                SwingUtilities.invokeLater(() -> {
                    // Hämta wish namnet
                    String selectedValue = (String) wishesJList.getSelectedValue();

                    // Hämta platsen där wishen finns i listan
                    int selectedIndex = wishesJList.getSelectedIndex();

                    if (selectedValue != null) {
                        // Söker igenom alla barnets önskningar
                        for (Map.Entry<String, Boolean> currentWish : selectedKid.getWishList().entrySet()) {
                            // Kollar om det är rätt önskning
                            if (currentWish.getKey().equals(selectedValue)) {
                                currentWish.setValue(true);

                                System.out.println("Wish was granted!");
                                wishesListModel.set(selectedIndex,currentWish.getKey()+" håller nu på att skickas.");
                            }
                        }
                    }
                });

            }
        });
    }

    void loadKidsToList(ArrayList<Kid> kidObjList)
    {
        kidsJList.setModel(kidsListModel);//Load JList Model to above
        for (Kid kid:kidObjList) {
            kidsListModel.addElement(kid.getKidName());
        }
    }

}
