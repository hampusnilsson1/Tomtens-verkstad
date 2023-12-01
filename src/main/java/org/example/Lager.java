package org.example;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.WeakHashMap;

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
        wishNames = new ArrayList<>();
        wishAmount = new ArrayList<>();
        LagerVeiwFrame = new JFrame();
        LagerVeiwFrame.setSize(500, 500);
        LagerVeiwFrame.setVisible(true);
        LagerVeiwFrame.setContentPane(WarehousePannel);
        LagerVeiwFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        LagerVeiwFrame.pack();
        DefaultListModel<String> model = new DefaultListModel<>();
        WarehouseList.setModel(model);
        try {
            FileReader fileReader = new FileReader("wish_list_for_kids.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] variables = line.split(",");
                model.addElement(variables[1]);
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
