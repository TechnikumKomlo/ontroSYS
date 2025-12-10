package SystemFiles.SSCC;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

import javax.swing.*;

public class CookieClicker {
    private static int cookies = 0;
    private static int multiplier = 1; 
    private static int upgradeCost = 10; 
    private static int upgradecounter = 0;
    private static String cookietext = "Click Me!";

    public static void main(String[] args) {
        JFrame frame = new JFrame("CookieClicker madzsar edísőn");
        JButton cookie = new JButton();
        JButton upgrade = new JButton();
        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");
        JButton background = new JButton("Background unlock");
        JTextField score = new JTextField();

        ArrayList<String> imagePaths = new ArrayList<>();
        imagePaths.add("SystemFiles\\SSCC\\textures\\0.png");
        imagePaths.add("SystemFiles\\\\SSCC\\\\textures\\\\2.png");
        imagePaths.add("SystemFiles\\\\SSCC\\\\textures\\\\3.png");
        imagePaths.add("SystemFiles\\\\SSCC\\\\textures\\\\4.png");
        imagePaths.add("SystemFiles\\\\SSCC\\\\textures\\\\5.png");
        imagePaths.add("SystemFiles\\\\SSCC\\\\textures\\\\6.png");
        imagePaths.add("SystemFiles\\\\SSCC\\\\textures\\\\7.png");
        imagePaths.add("SystemFiles\\\\SSCC\\\\textures\\\\8.png");
        imagePaths.add("SystemFiles\\\\SSCC\\\\textures\\\\9.gif");

        frame.setLayout(null);
        cookie.setBounds(250, 25, 300, 250);
        upgrade.setBounds(275, 300, 250, 50);
        score.setBounds(300, 375, 200, 50);
        saveButton.setBounds(600, 25, 100, 50);
        loadButton.setBounds(600, 100, 100, 50);
        background.setBounds(600, 300, 300, 50);

        frame.add(cookie);
        frame.add(upgrade);
        frame.add(score);
        frame.add(saveButton);
        frame.add(loadButton);
        frame.add(background);



        score.setEditable(false);

        cookie.setText(cookietext);
        cookie.setForeground(Color.red); 
        upgrade.setText("Upgrade (" + upgradeCost + " cookies)");
        score.setText("Score: " + cookies);

   
        cookie.setContentAreaFilled(false);
        cookie.setBorderPainted(false);
        cookie.setHorizontalTextPosition(SwingConstants.CENTER);
        cookie.setVerticalTextPosition(SwingConstants.CENTER);

        cookie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cookies += multiplier; 
                score.setText("Score: " + cookies);
            }
        });

        upgrade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cookies >= upgradeCost) {
                    System.out.println("Upgrade purchased!");
                    cookies -= upgradeCost; 
                    multiplier *= 2; 
                    upgradeCost *= 2; 
                    upgrade.setText("Upgrade (" + upgradeCost + " cookies)");
                    score.setText("Score: " + cookies);
                }
            }
        });
        
        background.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cookies>= upgradeCost*2) {
                    System.out.println("Background Unlocked!");
                            cookies -= upgradeCost*2;
                            ImageIcon cookieIcon = new ImageIcon(imagePaths.get(upgradecounter));
                            Image ogcookie = cookieIcon.getImage();
                            Image scaling = ogcookie.getScaledInstance(cookie.getWidth(), cookie.getHeight(), Image.SCALE_SMOOTH);
                            ImageIcon scaledcookie = new ImageIcon(scaling);
                            cookie.setIcon(scaledcookie);
                            score.setText("Score: " + cookies);
                            upgradecounter++;
                }

        }});
        
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int option = fileChooser.showSaveDialog(frame);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    saveGame(file.getPath());
                }
            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int option = fileChooser.showOpenDialog(frame);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    loadGame(file.getPath());
                    score.setText("Score: " + cookies);
                    upgrade.setText("Upgrade (" + upgradeCost + " cookies)");
                }
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    static void saveGame(String path) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path))) {
            writer.println(cookies);
            writer.println(multiplier);
            writer.println(upgradeCost);
            writer.println(upgradecounter);
        } catch (IOException e) {
            System.out.println("Save error: " + e.getMessage());
            
        }
    }

    static void loadGame(String path) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            cookies = Integer.parseInt(reader.readLine().trim());
            multiplier = Integer.parseInt(reader.readLine().trim());
            upgradeCost = Integer.parseInt(reader.readLine().trim());
            upgradecounter = Integer.parseInt(reader.readLine().trim());
        } catch (IOException e) {
            System.out.println("Load error: " + e.getMessage());
        }
    }
}
