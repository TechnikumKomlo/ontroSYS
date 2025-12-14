import javax.swing.*;

import SystemFiles.SSCC.CookieClicker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Terminal {
    public static JTextArea Log = new JTextArea();
    public void term() {
        JDialog Term = new JDialog(Gvar.frame,"Terminal",false);
        
        Term.setSize(400, 300);
        Term.setLayout(null);

        JTextField inputfield = new JTextField("", 20);
        
        JScrollPane TermSP = new JScrollPane(Log);
        ArrayList<String> Memory = new ArrayList<>();
        String commands = new String("/load \n" + "/help \n" + "/clear \n" + "/exit \n" + "/terminate \n"+"/gameload (L/R) \n"+"/Launch ck || rts \n");

        inputfield.setBounds(50, 30, 300, 30);
        TermSP.setBounds(50, 70, 300, 150);

        Term.add(inputfield);
        Term.add(TermSP);

        inputfield.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputtext = inputfield.getText();
                Memory.add(inputtext);
                Log.append(inputtext + "\n");
                inputfield.setText("");
                if (inputtext.equalsIgnoreCase("/reload")) {
                    
                }
                
                if (inputtext.equalsIgnoreCase("/help")) {
                    Log.append(commands);
                }
                if (inputtext.equalsIgnoreCase("/clear")) {
                    Log.setText("");
                }
                if (inputtext.equalsIgnoreCase("/exit")) {
                    Term.dispose();
                }
                if (inputtext.equalsIgnoreCase("/load")) {
                    System.out.println("Error_DRTXX2");
                }
                if (inputtext.equalsIgnoreCase("/terminate")) {
                    System.exit(0);
                }
                if (inputtext.equalsIgnoreCase("/Launch rts")) {
                
                }
                
                if (inputtext.equalsIgnoreCase("/Launch cookieclicker")) {
                    CookieClicker nemtom = new CookieClicker();
                    nemtom.main(null);
                }
                if (inputtext.equalsIgnoreCase("/Launch ck")) {
                    CookieClicker nemtom = new CookieClicker();
                    nemtom.main(null);
                }
                else {
                    System.out.println("Not existing command or wrong prefix");
                }
            }
        });

        Term.setVisible(true);
        Term.setResizable(false);
    }
}
