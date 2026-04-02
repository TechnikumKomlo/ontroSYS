import javax.swing.*;
import java.awt.*;
public class screen {

    public void scr(){
        Gvar.frame = new JFrame("OntroSYS");
        Gvar.panel = new JPanel(new GridBagLayout());
        //teljes kijelzőre
        


        Gvar.frame.setSize(1280, 720);
        Gvar.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Gvar.frame.setVisible(true);
        Gvar.frame.setMinimumSize(new Dimension(800, 600));
        //kijelző elemek betöltése
        GUI ui = new GUI();
        ui.UI();
        Gvar.frame.setContentPane(Gvar.panel);



    }



}
