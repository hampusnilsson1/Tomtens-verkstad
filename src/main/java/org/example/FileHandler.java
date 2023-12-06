package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {

    Scanner scanner = new Scanner(System.in);
    File wishlistFile = new File("wish_list_for_kids.txt");
    ArrayList<Wish> wishes = new ArrayList<>();

    public FileHandler(){
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
        }
        catch (NumberFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void listWishList() {
    for (Wish wish: wishes) {
        wish.printWishes();
    }
    }

}
