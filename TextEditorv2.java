import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class TextEditorv2 extends JFrame implements ActionListener {
    JTextArea szovegdoboz;
    JDialog frame;
    String FilePATH;
    String[] filename;
    boolean wraptext = false;
    int textsize = 12;

    public void TextEdit(String PATH) {
        filename = PATH.split("\\\\");
        frame = new JDialog(Gvar.frame,filename[filename.length - 1]);
        FilePATH = PATH;
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(TextEditorv2.class.getName()).log(Level.SEVERE, null, ex);
            Terminal.Log.append(ex + "\n");
        }

        szovegdoboz = new JTextArea();
        Font nemtom = new Font("Serif", Font.BOLD, textsize);
        szovegdoboz.setFont(nemtom);
        JScrollPane gorgo = new JScrollPane(szovegdoboz);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(gorgo);
        frame.setSize(640, 480);
        JMenuBar menu_main = new JMenuBar();
        JMenu menu_file = new JMenu("File");
        JMenu menu_view = new JMenu("View");
        JMenuItem menuitem_save = new JMenuItem("Save");
        JMenuItem menuitem_clear = new JMenuItem("Clear");
        JMenuItem menuitem_rename = new JMenuItem("Rename File");
        JMenuItem menuitem_quit = new JMenuItem("Exit");
        JMenuItem menuitem_textwraparound = new JMenuItem("Text wrap");
        JMenuItem menuitem_textsizep = new JMenuItem("Text Size +");
        JMenuItem menuitem_textsizem = new JMenuItem("Text Size -");
        menuitem_save.addActionListener(this);
        menuitem_clear.addActionListener(this);
        menuitem_rename.addActionListener(this);
        menuitem_quit.addActionListener(this);
        menuitem_textwraparound.addActionListener(this);
        menuitem_textsizep.addActionListener(this);
        menuitem_textsizem.addActionListener(this);
        menu_main.add(menu_file);

        menu_file.add(menuitem_save);
        menu_file.add(menuitem_clear);
        menu_file.add(menuitem_rename);
        menu_file.add(menuitem_quit);

        menu_main.add(menu_view);
        menu_view.add(menuitem_textwraparound);
        menu_view.add(menuitem_textsizep);
        menu_view.add(menuitem_textsizem);
        frame.setJMenuBar(menu_main);

        File fxs = new File(FilePATH);
        String ingest = "";
        frame.setVisible(true);
        // file beolvasása és kiiratása a szövegdobobza
        szovegdoboz.setLineWrap(false);
        try {
            FileReader read = new FileReader(fxs);
            Scanner scan = new Scanner(read);
            while (scan.hasNextLine()) {
                String line = scan.nextLine() + "\n";
                ingest += line;
            }
            scan.close();
            szovegdoboz.setText(ingest);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Terminal.Log.append(ex + "\n");
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String ae = e.getActionCommand();
        if (ae.equals("Save")) {
            try {
                File f = new File(FilePATH);
                FileWriter out = new FileWriter(f);
                out.write(szovegdoboz.getText());
                out.close();
            } catch (FileNotFoundException ex) {
                Component f = null;
                JOptionPane.showMessageDialog(f, "nem találtuk a filet.");
                Terminal.Log.append(ex + "\n");
            } catch (IOException ex) {
                Component f = null;
                JOptionPane.showMessageDialog(f, "Error.");
                Terminal.Log.append(ex + "\n");
            }
        } else if (ae.equals("Clear")) {
            szovegdoboz.setText("");
        } else if (ae.equals("Exit")) {
            frame.dispose();

        } else if (ae.equals("Text Size +")) {
            textsize += 4;

            Font nemtom = new Font("Serif", Font.BOLD, textsize);
            szovegdoboz.setFont(nemtom);
        } else if (ae.equals("Text Size -")) {
            if (textsize > 4) {
                textsize -= 4;

                Font nemtom = new Font("Serif", Font.BOLD, textsize);
                szovegdoboz.setFont(nemtom);
            }

        }

        if (ae.equals("Text wrap")) {
            if (wraptext) {
                szovegdoboz.setLineWrap(false);
                wraptext = false;
            } else {
                szovegdoboz.setLineWrap(true);
                wraptext = true;
            }
        } else if (ae.equals("Rename File")) {

            JFrame renamefile = new JFrame("Rename File");
            renamefile.setLayout(null);
            JTextArea szovegd = new JTextArea();
            szovegd.setText("New name for the file:");
            szovegd.setBounds(20, 0, 200, 15);
            szovegd.setEditable(false);
            szovegd.setFocusable(false);
            JTextArea szovegdb = new JTextArea();
            Font nemtom = new Font("Serif", Font.BOLD, 12);
            szovegdb.setFont(nemtom);
            renamefile.add(szovegdb);
            renamefile.add(szovegd);
            renamefile.setSize(300, 100);
            renamefile.setVisible(true);
            szovegdb.setBounds(20, 20, 200, 21);
            renamefile.setResizable(false);
            szovegdb.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        String text = szovegdb.getText();
                        szovegdb.setText("");
                        File oldFile = new File(FilePATH);
                        String pathrename = "";
                        for (String name : filename) {
                            if (name.equals(filename[filename.length - 1])) {
                                break;
                            }
                            pathrename += name + "\\";
                        }
                        pathrename += text;
                        File newFile = new File(pathrename);
                        boolean success = oldFile.renameTo(newFile);
                        if (!success) {
                            System.out.println("nem sikerult kecseg atnevezni");
                        }
                        frame.dispose();
                        renamefile.dispose();
                        TextEdit(pathrename);
                    }
                }
            });

        }

    }
}