package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class SnoepUI implements ActionListener {

    // alle namen van snoep om te controleren of er een snack knop is ingedrukt of de betaalknop
    public static String[] SnoepLijst = { "Mars", "Snickers", "Twix", "Bounty", "Bros", "Milky Way", "M&M's", "Lion", "KitKat" };

    // een aantal global variables voor de user interface voor gebruik in andere classes
    public static JLabel automaatScherm;
    public static JTextField geldInvoer;
    public static JLabel voorraadLabel;

    // maak het frame met daarin alle elementen van de user interface
    void maakWindow(){
        JFrame frame = new JFrame("Leon's Super Snoepautomaat! Voor al uw lekkere snacks");
        frame.setSize(600,240);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        JPanel knoppenPanel = new JPanel();
        knoppenPanel.setLayout(new GridLayout(0,3));
        frame.add(knoppenPanel, BorderLayout.PAGE_START);

        // maak 9 knoppen voor 9 snacks
        for(int i = 0; i < 9; i++){
            JButton button = new JButton(Snoep.SnoepLijst[i].Naam);
            button.addActionListener(this);
            knoppenPanel.add(button);
        }

        automaatScherm = new JLabel(" ");
        automaatScherm.setPreferredSize(new Dimension(200, 30));
        automaatScherm.setBorder(BorderFactory.createLineBorder(Color.black));
        frame.add(automaatScherm, BorderLayout.LINE_START);

        JButton betaalKnop = new JButton("Betaal");
        betaalKnop.addActionListener(this);
        frame.add(betaalKnop, BorderLayout.CENTER);

        geldInvoer = new JTextField(20);
        frame.add(geldInvoer, BorderLayout.LINE_END);

        voorraadLabel = new JLabel(" Voorraad:");
        voorraadLabel.setPreferredSize(new Dimension(600, 80));
        frame.add(voorraadLabel, BorderLayout.PAGE_END);
        Snoep.showVoorraad();

        frame.setVisible(true);
    }

    // functie voor reactie op het indrukken van een knop
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        JButton b = (JButton) o;
        String naam = b.getText();

        // als gebruiker op snack drukt: call functie koopSnoep
        if(Arrays.asList(SnoepLijst).contains(naam)) {
            Snoep.koopSnoep(naam);
        }

        // als gebruiker op betaalknop druk: call functie betaal met ingevoerde hoeveelheid geld
        else {
            Snoep.betaal(geldInvoer.getText());
        }
    }
}

