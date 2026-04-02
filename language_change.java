import javax.swing.JDialog;
import javax.swing.JList;

public class language_change {
    public static JList languagelist;
    public static String old_position;
    public void changelanguage(){
        dataprocess data = new dataprocess();
        old_position = Gvar.pathleft;

        Gvar.pathleft = "./src/main/java/SystemFiles";
        data.reloadleftsidestucture();

    }
    
}
