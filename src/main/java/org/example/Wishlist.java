package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

public class Wishlist {

    private DefaultListModel<String> listModel;     //List were the kids wishes are listed.
    private DefaultListModel<String> availableWishes; //List were all the available wishes the kids can chose from are shown.
    MusicPlayer musicPlayer;

    JFrame wishList;
    private JPanel panel1;
    private JButton Add;
    private JButton Remove;
    private JList wishlist;
    private JList listOfAvailableWishes;
    private JLabel ErrorMessage;
    private JButton Savebutton;
    private JLabel wishlistLabel;
    private JRadioButton mute;
    File wishlistFile = new File("wish_list_for_kids.txt");
    File theKidsOwnWishlist = new File("wish_list.txt");
    ArrayList<Wish> kidsWishes = new ArrayList<>(); //Array that is used for listing the kids wishes on to the JList.
    ArrayList<Wish> wishes = new ArrayList<>();  //Array that is used for listing all possible wishes the kids can chose from.
    ArrayList<String> tempArray = new ArrayList<>(); // An empty array that is used for saving. When pressing save the JList (listmodel) is
                                                        // loaded on to the array. After that the filewriter reads it into the csv file.



    public Wishlist(String enteredUsername, MusicPlayer musicPlayer) {
        this.musicPlayer= musicPlayer;
        wishList = new JFrame();
        wishList.setSize(900, 500);
        wishList.setVisible(true);
        wishList.setContentPane(panel1);
        wishList.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.musicPlayer = musicPlayer;

        musicPlayer.stopMusic();



        if (!Files.exists(Path.of("wish_list_temp.txt"))){      //Creates a temporary file which is used when saving.
            try {
                Files.createFile(Path.of("wish_list_temp.txt"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        listModel = new DefaultListModel<>(); //List were the kids wishes are listed.
        wishlist.setModel(listModel);

        availableWishes = new DefaultListModel<>(); //List that all the available wishes the kids can chose from is shown.
        listOfAvailableWishes.setModel(availableWishes);




        try {
            FileReader fileReader = new FileReader(theKidsOwnWishlist); //Filereader for reading the kids wishes from the csv file into the JList.
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while (line != null) {

                if (line.contains(enteredUsername)){


                    String[] variables = line.split(",");
                    int u = variables.length;

                    for (int i = 1; i < u; i++,i++) {

                        String wishName = variables[i];
                        Wish wish = new Wish(wishName);
                        kidsWishes.add(wish);
                    }
                }

                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            fileReader.close();
            kidWishList();


        }
        catch (NumberFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }




        try {
            FileReader fileReader = new FileReader(wishlistFile); //Filereader for the same thing as above but from the csv file for all available wishes.
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
            bufferedReader.close();
            fileReader.close();
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

                else if (listModel.contains(listOfAvailableWishes.getSelectedValue())){
                    ErrorMessage.setText("Bara en av samma önskning får göras. Var vänlig välj någonting annat.");
                }
                else {
                    String add = String.valueOf(listOfAvailableWishes.getSelectedValue());
                    Wish wish = new Wish(add);
                    listModel.addElement(add);
                    kidsWishes.add(wish);
                    }
            }
        });

        Remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedValue = (String) wishlist.getSelectedValue();
                    listModel.removeElement(selectedValue);
            }
        });


        Savebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    for (int i = 0; i < listModel.size(); i++){ //I load all the wishes from the list onto the temporary array.
                        tempArray.add(listModel.getElementAt(i));
                    }

                    File tempFile = new File("wish_list_temp.txt");
                    PrintWriter writer = new PrintWriter(new BufferedWriter((new FileWriter("wish_list_temp.txt"))));
                    FileReader fileReader = new FileReader(theKidsOwnWishlist);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String line = bufferedReader.readLine();

                        while (line != null) {
                            if (line.contains(enteredUsername)) {
                                line = line.replace(line,(enteredUsername+getCSV())); //I rewrite the line in the text file which belongs to the user by writing the temporary array into the filewriter.
                            }
                            writer.println(line);
                            line = bufferedReader.readLine();
                        }

                        writer.close();
                        bufferedReader.close();
                        fileReader.close();

                    Path oldFilePath = Paths.get("wish_list.txt");
                    Files.deleteIfExists(oldFilePath);
                    Files.move(tempFile.toPath(), oldFilePath, StandardCopyOption.REPLACE_EXISTING);


                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } finally{
                    Login login = new Login();
                    musicPlayer.stopMusic();
                    wishList.dispose();
                }
            }
        });

        mute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(mute.isSelected()){
                    musicPlayer.stopMusic();
                }else {
                    musicPlayer.startMusic();
                }
            }
        });
    }

    public void listWishList() {
        for (Wish wish: wishes) {
            availableWishes.addElement(wish.getWishName());
        }
    }

    public void kidWishList() {
        for (Wish kidwish: kidsWishes) {
            listModel.addElement(kidwish.getWishName());
        }
    }

    private String getCSV(){
        int u = tempArray.size(); //creates a string with every wish from the JList.
        String saveWish = "";
        for(int i = 0; i < u; i++) {
            saveWish = saveWish + ",";
            saveWish = saveWish + tempArray.get(i);
            saveWish = saveWish + ",false";
        }
        return saveWish;
    }
}

