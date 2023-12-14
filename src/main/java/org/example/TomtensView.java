package org.example;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TomtensView extends JFrame{
    JFrame tomtensViewFrame;
    private JPanel tomtensView;
    private JButton inStock;
    private JList wishesJList;
    private JList<String> kidsJList;
    private JButton logOut;
    private JPanel kidScrollPanel;
    private JButton skickaBtn;
    private JButton stock;
    private JRadioButton mute;
    DefaultListModel<String> kidsListModel;
    DefaultListModel<String> wishesListModel;

    String selectedKidName = "";
    Kid selectedKid = null;

    MusicPlayer musicPlayer;
    public TomtensView(ArrayList<Kid> kidObjList, MusicPlayer musicPlayer){
        this.musicPlayer = musicPlayer;
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
        Component c = kidsJList;
        c.setBackground(new Color(199,200,184));
        c.setForeground(new Color(0,81,5));

        loadKidsToList(kidObjList);

        JScrollPane scrollPane = new JScrollPane(kidsJList);
        kidScrollPanel.add(scrollPane,BorderLayout.CENTER);





        inStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LagerCheck lagerCheck = new LagerCheck(wishesListModel);
            }
        });
        logOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login();
                musicPlayer.stopMusic();
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

                    if (selectedValue != null && loadCurrentWish(selectedValue)) {
                        // Söker igenom alla barnets önskningar
                        for (Map.Entry<String, Boolean> currentWish : selectedKid.getWishList().entrySet()) {
                            // Kollar om det är rätt önskning
                            if (currentWish.getKey().equals(selectedValue)) {
                                currentWish.setValue(true);
                                saveNewProductAmount(selectedValue,1);
                                saveAllKidWishStatus(kidObjList); //Sparar om alla kids i listan till wish_list.txt (Skriver över)
                                System.out.println("Wish was granted!");
                                wishesListModel.set(selectedIndex,currentWish.getKey()+" håller nu på att skickas.");
                            }
                        }
                    }
                });

            }
        });

        stock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Lager();
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

    void loadKidsToList(ArrayList<Kid> kidObjList)
    {
        kidsJList.setModel(kidsListModel);//Load JList Model to above
        for (Kid kid:kidObjList) {
            kidsListModel.addElement(kid.getKidName());
            System.out.println(kid.getWishesCSV());
        }
    }

    void saveAllKidWishStatus(ArrayList<Kid> kidObjList)
    {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("wish_list.txt",false));// Append false så skriver över allt i wishlist.
            for (int i = 0; i< kidObjList.size(); i++)
            {
                writer.write(kidObjList.get(i).getWishesCSV()); // Skriver ner
                if(i < kidObjList.size() - 1)//Tar ny rad sålänge det inte är sista i listan
                    writer.newLine();
                System.out.println("Saved "+kidObjList.get(i).getWishesCSV());
            }
            writer.close(); // Stänger och sparar allt till filen
        } catch (IOException e) {

        }
    }

    boolean loadCurrentWish(String wishItem)
    {
        File file = new File("wish_list_for_kids.txt");

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = bufferedReader.readLine()) != null)
            {
                String[] part = line.split(",");
                String product = part[1];
                int amount = Integer.parseInt(part[2]);
                if(wishItem.equals(product) && amount > 0) {
                    return true;
                }else if(wishItem.equals(product) && amount <= 0){
                    return false;
                }
            }

        } catch (Exception e) {

        }
        return false;
    }

    void saveNewProductAmount(String wishItem,Integer removeamount){
        File file = new File("wish_list_for_kids.txt");
        ArrayList<Wish> products = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line ="";
            //Läser in alla grejer i filen o lägger in dem i en array temporärt och ändrar amount på den som ska.
            while((line = bufferedReader.readLine()) != null)
            {
                String[] part = line.split(",");
                int id = Integer.parseInt(part[0]);
                String product = part[1];
                int amount = Integer.parseInt(part[2]);
                if(product.equals(wishItem))
                {
                    amount = amount - removeamount;
                }
                Wish productObj = new Wish(id,product,amount);
                products.add(productObj);
            }
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            //Skriver in de nya världena i filen.
            for (int i = 0; i< products.size();i++)
            {
                bufferedWriter.write(products.get(i).getWishCSV());
                //Om det inte är sista raden så skriv
                if(i != products.size()-1)
                {
                    bufferedWriter.newLine();
                }
            }
            bufferedWriter.close();
        } catch (IOException e) {

        }
    }

}



