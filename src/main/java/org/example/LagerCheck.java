package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LagerCheck {
    private JPanel panel1;
    private JPanel check1;
    private JPanel check2;
    private JPanel check3;
    private JPanel check4;
    private JPanel check5;
    private JPanel check6;
    private JPanel check9;
    private JPanel check10;
    private JPanel check7;
    private JPanel check8;
    private JPanel wishName1;
    private JPanel wishName2;
    private JPanel wishName3;
    private JPanel wishName4;
    private JPanel wishName5;
    private JPanel wishName6;
    private JPanel wishName7;
    private JPanel wishName8;
    private JPanel wishName9;
    private JPanel wishName10;
    private JButton closeWindow;
    private JLabel wishList;
    private JLabel storageCheck;
    private JLabel wish1;
    private JLabel wish2;
    private JLabel wish3;
    private JLabel wish4;
    private JLabel wish5;
    private JLabel wish6;
    private JLabel wish7;
    private JLabel wish8;
    private JLabel wish9;
    private JLabel wish10;
    private JLabel status1;
    private JLabel status2;
    private JLabel status3;
    private JLabel status4;
    private JLabel status5;
    private JLabel status6;
    private JLabel status7;
    private JLabel status8;
    private JLabel status9;
    private JLabel status10;
    private JPanel wishPanel;

    JLabel[] wishLabels = new JLabel[]{
            wish1, wish2, wish3, wish4, wish5,
            wish6, wish7, wish8, wish9, wish10,
    };

    JLabel[] statusLabels = new JLabel[]{
            status1, status2, status3, status4,
            status5, status6, status7, status8,
            status9, status10
    };

    File file = new File("wish_list_for_kids.txt");


    public LagerCheck (DefaultListModel wishesListModel) {
        JFrame checkFrame = new JFrame();
        checkFrame.setContentPane(panel1);
        checkFrame.setSize(500, 500);
        checkFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        checkFrame.setVisible(true);

        //Sätter namnen för de olika componenterna i Formen. Detta så att jag i getCompenentsByName funktionen kan å in och hitta
        // den JLabel som jag vill ändra texten på.
        wishName1.setName("wishName1");
        wishName2.setName("wishName2");
        wishName3.setName("wishName3");
        wishName4.setName("wishName4");
        wishName5.setName("wishName5");
        wishName6.setName("wishName6");
        wishName7.setName("wishName7");
        wishName8.setName("wishName8");
        wishName9.setName("wishName9");
        wishName10.setName("wishName10");

        wish1.setName("wish1");
        wish2.setName("wish2");
        wish3.setName("wish3");
        wish4.setName("wish4");
        wish5.setName("wish5");
        wish6.setName("wish6");
        wish7.setName("wish7");
        wish8.setName("wish8");
        wish9.setName("wish9");
        wish10.setName("wish10");

        status1.setName("status1");
        status2.setName("status2");
        status3.setName("status3");
        status4.setName("status4");
        status5.setName("status5");
        status6.setName("status6");
        status7.setName("status7");
        status8.setName("status8");
        status9.setName("status9");
        status10.setName("status10");

        
        for (JLabel label : wishLabels) { //Detta nollar texten i JLabels
            label.setText("");
        }
        for (JLabel label : statusLabels) { // Samma sak.
            label.setText("");
        }



        //För att fylla ut alla JLabels i vänstra halvan där alla önskningarna ska stå.
        for (int i = 0; i < wishesListModel.size() && i < 10; i++) {
            String wish = (String) wishesListModel.get(i);
            wish = wish.replaceAll(" håller nu på att skickas.", "");
            wish = wish.replaceAll(" är nu skickad!", "");
            System.out.println(wish);
            JLabel wishLabel = (JLabel) getComponentByName("wish" + (i + 1));
            wishLabel.setText(wish);
        }
        updateStatus();
        closeWindow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkFrame.dispose();
            }
        });
    }





    //Funktion som hittar en specifik JLabel och ger tillbaka den som en component tillbaka till
    //for loopen ovanför. Den går igenom hela "trädet" av components och om ett namn matchar med
    //det man jämför med så skickas den komponenten tillbaka till rad 128.
    public Component getComponentByName(String name) {
        return findComponentByName(panel1, name);
    }

    private Component findComponentByName(Component component, String name) {
        if (component.getName() != null && component.getName().equals(name)) {
            return component;
        }

        if (component instanceof Container) {
            Container container = (Container) component;
            for (Component child : container.getComponents()) {
                Component found = findComponentByName(child, name);
                if (found != null) {
                    return found;
                }
            }
        }
        return null;
    }







    //Funktion som tar önskningen som sitter i JLabel och jämför den med önskningarna som är i hashmapen jag har gjort
    //nedanför (se readWishQuantities). Därefter kollas kvantiteten och beroende på om den är noll eller ej
    //så sätts texten i JLabels i högra halvan av fönstret till "Finns i lager" eller "Ej i lager".
    private void updateStatus () {
        File file = new File("wish_list_for_kids.txt");
        Map<String, Integer> wishQuantities = readWishQuantities(file);

        for (int i = 0; i < wishLabels.length; i++) {
            if (wishLabels[i].getText()!= "") {
                String wish = wishLabels[i].getText();
                int quantity = wishQuantities.getOrDefault(wish, 0);
                statusLabels[i].setText(quantity > 0 ? "Finns i Lager" : "Ej i Lager");
                    if (statusLabels[i].getText()=="Finns i Lager"){
                        statusLabels[i].setForeground(Color.GREEN);
                    }
                    else {
                        statusLabels[i].setForeground(Color.RED);
                    }
            }
        }
    }



    //Läser av txt filen wish_list_for_kids.txt och sätter in önskningen samt kvantiteten av den önskningen
    //i en hashmap.
    private Map<String, Integer> readWishQuantities (File file){
        Map<String, Integer> wishQuantities = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String wish = parts[1].trim();
                    int quantity = Integer.parseInt(parts[2].trim());
                    wishQuantities.put(wish, quantity);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return wishQuantities;
    }
}






