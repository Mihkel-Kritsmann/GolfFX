package com.example.golffx;

import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class NameAndNumberDisplay extends JFrame {
    private static final long serialVersionUID = 1L;
    private List<String> nimed;
    private List<Double> HCP;
    private int currentIndex;

    public NameAndNumberDisplay() {
        super("Golfimäng");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        nimed = new ArrayList<>();
        HCP = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("names.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] osad = line.split(",");
                nimed.add(osad[0]);
                HCP.add(Double.parseDouble(osad[1]));
            }
        } catch (IOException e) {
            System.err.println("Faililugemiserror " + e.getMessage());
        }

        JPanel panel = new JPanel();
        panel.setLayout(null);

        Font suur = new Font("Arial",Font.BOLD,20);

        JLabel nameLabel = new JLabel("Mängija");
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setBounds(150, 50, 100, 25);
        nameLabel.setFont(suur);
        panel.add(nameLabel);

        JLabel numberLabel = new JLabel("HCP");
        numberLabel.setHorizontalAlignment(SwingConstants.CENTER);
        numberLabel.setBounds(350, 50, 100, 25);
        numberLabel.setFont(suur);
        panel.add(numberLabel);

        JLabel nameDisplay = new JLabel(nimed.get(0));
        nameDisplay.setHorizontalAlignment(SwingConstants.CENTER);
        nameDisplay.setBounds(150, 100, 100, 25);
        panel.add(nameDisplay);

        JLabel numberDisplay = new JLabel(HCP.get(0).toString());
        numberDisplay.setHorizontalAlignment(SwingConstants.CENTER);
        numberDisplay.setBounds(350, 100, 100, 25);
        panel.add(numberDisplay);


        JButton edasiButton = new JButton("Järgmine");
        edasiButton.setBounds(250, 150, 100, 25);
        edasiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentIndex++;
                if (currentIndex >= nimed.size()) {
                    currentIndex = 0;
                }
                nameDisplay.setText(nimed.get(currentIndex));
                numberDisplay.setText(HCP.get(currentIndex).toString());
            }
        });
        panel.add(edasiButton);

        JButton gameButton = new JButton("Mängima!");
        gameButton.setBounds(250, 200, 100, 25);
        gameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedName = nameDisplay.getText();
                String selectedNumber = numberDisplay.getText();
                GolfMäng.main(null);
            }
        });
        panel.add(gameButton);


        JButton addButton = new JButton("Lisa mängija");
        addButton.setBounds(246, 250, 108, 25);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame inputFrame = new JFrame();
                inputFrame.setTitle("Mängija lisamine");
                inputFrame.setSize(300, 300);
                inputFrame.setLocationRelativeTo(null);
                inputFrame.setResizable(false);

                JTextField nameField = new JTextField();
                nameField.setBounds(150, 50, 100, 25);
                inputFrame.add(nameField);

                JTextField numberField = new JTextField();
                numberField.setBounds(150, 100, 100, 25);
                inputFrame.add(numberField);

                JLabel nimi = new JLabel("nimi: ");
                nimi.setHorizontalAlignment(SwingConstants.CENTER);
                nimi.setBounds(50, 50, 100, 25);
                inputFrame.add(nimi);

                JLabel hcp = new JLabel("HCP: ");
                hcp.setHorizontalAlignment(SwingConstants.CENTER);
                hcp.setBounds(50, 100, 100, 25);
                inputFrame.add(hcp);

                JButton lisaButton = new JButton("Lisa");
                lisaButton.setBounds(100, 150, 100, 25);
                lisaButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String uusnimi = nameField.getText();
                        String uushcp = numberField.getText();
                        if (uusnimi.isEmpty() || uushcp.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Lisa nimi ja HCP");
                            return;
                        }

                        try {
                            double hcp = Double.parseDouble(uushcp);
                            if (hcp <= 0 || hcp >= 54) {
                                JOptionPane.showMessageDialog(null, "HCP peab olema vahemikus 0-54");
                                return;
                            }
                            nimed.add(uusnimi);
                            HCP.add(hcp);
                            currentIndex = nimed.size() - 1;
                            nameDisplay.setText(uusnimi);
                            numberDisplay.setText(Double.toString(hcp));
                            nameField.setText("");
                            numberField.setText("");

                            try (BufferedWriter bw = new BufferedWriter(new FileWriter("names.txt", true))) {
                                bw.write(uusnimi + "," + Double.toString(hcp) + "\n");
                            } catch (IOException ex) {
                                System.err.println("failikirjutamise error " + ex.getMessage());
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Lisa normaalne number!");
                        }
                        inputFrame.dispose();

                    }
                });
                inputFrame.add(lisaButton);

                inputFrame.setLayout(null);
                inputFrame.setVisible(true);
            }
        });
        panel.add(addButton);

        setContentPane(panel);
        setVisible(true);
        setResizable(false);
    }
    public static void main(String[] args) {
        new NameAndNumberDisplay();
    }}

