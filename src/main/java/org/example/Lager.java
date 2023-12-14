package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Lager extends JFrame{
    JFrame LagerVeiwFrame;
    private JPanel LagerVeiw;
    private JList WarehouseList;
    private JButton closebtn;
    private JPanel WarehousePannel;
    private JList AmountList;
    private ArrayList<String> wishNames;
    private int[] wishAmount;

    private int[] item;

    public Lager() {
        wishNames = new ArrayList<>();
        LagerVeiwFrame = new JFrame();
        LagerVeiwFrame.pack();
        LagerVeiwFrame.setSize(500, 500);
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


        closebtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LagerVeiwFrame.dispose();
            }
        });
    }
    public void SendPresents() throws FileNotFoundException {
        Scanner main = new Scanner(new File("wish_list_for_kids.txt"));

        PrintWriter  mainOutput = new PrintWriter  (new File("temp.txt"));
        int i = 0;
        while (main.hasNext()){
            String[] nextTokens=main.nextLine().split(",");
            for(String token:nextTokens)
                if(Objects.equals(token, nextTokens[2]))
                {
                    mainOutput.print(Integer.parseInt(token) - wishAmount[i]);
                }else {

                    mainOutput.print(token+",");
                }
            mainOutput .println();
            i++;
        }

        mainOutput .close();
    }
}
