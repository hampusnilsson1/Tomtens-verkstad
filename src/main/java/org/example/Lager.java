package org.example;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Lager extends JFrame{
    JFrame LagerVeiwFrame;
    private JPanel LagerVeiw;
    private JList WarehouseList;
    private JButton button1;
    private JPanel WarehousePannel;
    private JList AmountList;
    private ArrayList<String> wishNames;
    private ArrayList<Integer> wishAmount;

    public Lager() {
        wishName = new ArrayList<>();
        wishAmount = new ArrayList<>();
        LagerVeiwFrame = new JFrame();
        LagerVeiwFrame.setSize(500, 500);
        LagerVeiwFrame.setVisible(true);
        LagerVeiwFrame.setContentPane(WarehousePannel);
        LagerVeiwFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        LagerVeiwFrame.pack();

        try {
            FileReader fileReader = new FileReader();
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] variables = line.split(",");
                String wishName = variables[1];
                wishNames.add(wishName);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            fileReader.close();
        }
        catch (NumberFormatException | IOException e) {
            throw new RuntimeException(e);
        }

    }
}
