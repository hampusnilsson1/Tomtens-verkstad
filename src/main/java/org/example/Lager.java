package org.example;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
public class Lager extends JFrame{
    JFrame LagerVeiwFrame;
    private JPanel LagerVeiw;
    private JList WarehouseList;
    private JButton button1;
    private JPanel WarehousePannel;
    private JList AmountList;
    private ArrayList<String> wishNames;
    private int[] wishAmount;

    private int[] item;

    public Lager() {
        wishNames = new ArrayList<>();
        LagerVeiwFrame = new JFrame();
        LagerVeiwFrame.pack();
        LagerVeiwFrame.setSize(500, 400);
        LagerVeiwFrame.setVisible(true);
        LagerVeiwFrame.setContentPane(WarehousePannel);
        LagerVeiwFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        DefaultListModel<String> model1 = new DefaultListModel<>();

        try {
            FileReader fileReader = new FileReader("wish_list_for_kids.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] variables = line.split(",");
                model1.addElement(variables[1] + "/" + variables[2]);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            fileReader.close();
        }
        catch (NumberFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        WarehouseList.setModel(model1);
        wishAmount = new int[model1.getSize()];
        try {
            FileReader fileReader = new FileReader("wish_list.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] variables = line.split(",");
                for (String var:variables) {
                    for (int i = 0; i < model1.getSize();i++){
                        if (Objects.equals(var, model1.get(i).split("/")[0])){
                            wishAmount[i]++;
                        }
                    }
                }
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            fileReader.close();
        }
        catch (NumberFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        DefaultListModel<String> model2 = new DefaultListModel<>();
        AmountList.setModel(model2);
        for (int i:wishAmount) {
            model2.addElement(String.valueOf(i));
        }


    }
    public void SendPresents(){
        try {
            FileReader fileReader = new FileReader("wish_list_for_kids.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] variables = line.split(",");

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
