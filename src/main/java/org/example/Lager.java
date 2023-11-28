package org.example;

import javax.swing.*;
import java.awt.*;

public class Lager extends JFrame{
    JFrame LagerVeiwFrame;
    private JPanel LagerVeiw;
    private JList WarehouseList;
    private JButton button1;
    private JPanel WarehousPannel;

    public Lager() {
        LagerVeiwFrame = new JFrame();
        LagerVeiwFrame.setSize(500, 500);
        LagerVeiwFrame.setVisible(true);
        LagerVeiwFrame.setContentPane(WarehousPannel);
        LagerVeiwFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
