import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class menubar {

    public static JMenu settings;
    public static JMenu file;
    public static JMenu Look;
    public static JMenuItem Terminal;
    public static JMenuItem language;
    public static JMenuItem LAN;
    public static JMenuItem Metal;
    public static JMenuItem System;
    public static JMenuItem Nimbus;
    public static JMenuItem Motif;
    public void menubaritems(){
        //beállitások menüpont
        FileIO nyelvinput = new FileIO();
        String item = nyelvinput.ReadLine(Gvar.nyelv, 0);
        settings = new JMenu(item);
        Gvar.menubar.add(settings);
        //beállitások almenü
        item = nyelvinput.ReadLine(Gvar.nyelv, 1);
        Terminal = new JMenuItem(item);
        //nyelv beállitás
        item = nyelvinput.ReadLine(Gvar.nyelv, 2);

        language = new JMenuItem(item);

        //beállitásokhoz alpontok hozzáadása
        settings.add(Terminal);
        settings.add(language);
        


        //file menüsor
        item = nyelvinput.ReadLine(Gvar.nyelv, 3);
        file = new JMenu(item);
        Gvar.menubar.add(file);
        
        //file almenü
        item = nyelvinput.ReadLine(Gvar.nyelv, 4);
        LAN = new JMenuItem(item);
        file.add(LAN);
        
        //kinézet
        Look = new JMenu("GUI");
        Gvar.menubar.add(Look);

        Metal = new JMenuItem("Metal");
        System = new JMenuItem("System");
        Nimbus = new JMenuItem("Nimbus");
        Motif = new JMenuItem("Motif");
        Look.add(Metal);
        Look.add(System);
        Look.add(Nimbus);
        Look.add(Motif);

    }


}

