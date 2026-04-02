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

class TextEditor extends JFrame implements ActionListener {
    JTextArea szovegdoboz;
    JFrame frame;
    String FilePATH;

    public void TextEdit(String PATH) {
        String[] filename = PATH.split("\\\\");
        frame = new JFrame(filename[filename.length - 1]);
        FilePATH = PATH;
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(TextEditor.class.getName()).log(Level.SEVERE, null, ex);
        }

        szovegdoboz = new JTextArea();
        Font nemtom = new Font("Verdana", Font.BOLD, 12);
        szovegdoboz.setFont(nemtom);
        JScrollPane gorgo = new JScrollPane(szovegdoboz);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gorgo);
        frame.setSize(640, 480);
        JMenuBar menu_main = new JMenuBar();
        JMenu menu_file = new JMenu("Settings");
        JMenuItem menuitem_save = new JMenuItem("Save");
        JMenuItem menuitem_clear = new JMenuItem("Clear");
        JMenuItem menuitem_rename = new JMenuItem("Rename File");
        JMenuItem menuitem_quit = new JMenuItem("Exit");

        menuitem_save.addActionListener(this);
        menuitem_clear.addActionListener(this);
        menuitem_rename.addActionListener(this);
        menuitem_quit.addActionListener(this);

        menu_main.add(menu_file);

        menu_file.add(menuitem_save);
        menu_file.add(menuitem_clear);
        menu_file.add(menuitem_rename);
        menu_file.add(menuitem_quit);

        frame.setJMenuBar(menu_main);

        File fxs = new File(FilePATH);
        String ingest = "";
        frame.setVisible(true);
        // file beolvasása és kiiratása a szövegdobobza
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
            } catch (IOException ex) {
                Component f = null;
                JOptionPane.showMessageDialog(f, "Error.");
            }
        } else if (ae.equals("Clear")) {
            szovegdoboz.setText("");
        } else if (ae.equals("Exit")) {
            System.exit(0);
        } else if (ae.equals("Rename File")) {
            File f = new File(FilePATH);
            JFrame renamefile = new JFrame("Rename File");
            JTextArea szovegdb = new JTextArea();
            Font nemtom = new Font("Verdana", Font.BOLD, 12);
            szovegdb.setFont(nemtom);
            renamefile.add(szovegdb);
            renamefile.setSize(300, 150);
            renamefile.setVisible(true);
            File oldFile = new File(FilePATH);
            File newFile = new File("");

        }

    }
}