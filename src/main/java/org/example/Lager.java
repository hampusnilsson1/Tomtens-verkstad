package org.example;

import javax.swing.*;

public class Lager extends JFrame{
    JFrame LagerVeiwFrame;
    private JPanel LagerVeiw;
    private JList WarehouseList;
    private JButton button1;
    private JPanel WarehousePannel;
    private JList AmountList;

    public Lager() {
        LagerVeiwFrame = new JFrame();
        LagerVeiwFrame.setSize(500, 500);
        LagerVeiwFrame.setVisible(true);
        LagerVeiwFrame.setContentPane(WarehousePannel);
        LagerVeiwFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        LagerVeiwFrame.pack();
        //ReadWishes();
        
    }
}
