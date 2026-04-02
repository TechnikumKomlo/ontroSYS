import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Gvar {
    // kijelzö
    public static JFrame frame;
    public static JPanel panel;

    // kijelzö elemek

    // elrendezés kialakitása
    public static GridBagConstraints gbc = new GridBagConstraints();

    // fentimenüsor
    public static JMenuBar menubar = new JMenuBar();

    // nyelv
    public static String nyelv;

    public static int lookandfeelnum;

    // LAN kapcsolat

    public static String ip;
    public static int port;
    public static String command;
    // gombok
    public static JButton WEBTEST;
    public static JButton Filemanager;
    // fenti második sor gombok

    public static JButton back1;
    public static JButton back2;
    public static JComboBox leftdrive;
    public static JComboBox rightdrive;

    // fájl pozició

    public static JLabel leftfolderpoz;
    public static JLabel rightfolderpoz;

    // oszlop elnevezések
    public static JButton name1;
    public static JButton extension1;
    public static JButton lastmod1;
    public static JButton size1;

    public static JButton name2;
    public static JButton extension2;
    public static JButton lastmod2;
    public static JButton size2;

    // listák

    public static JList namelist1;
    public static JList extensionlist1;
    public static JList lastmodlist1;
    public static JList sizelist1;

    public static JList namelist2;
    public static JList extensionlist2;
    public static JList lastmodlist2;
    public static JList sizelist2;

    // lapozható legyen

    public static JScrollPane namescrol1;
    public static JScrollPane extensionscrol1;
    public static JScrollPane lastmodscrol1;
    public static JScrollPane sizescrol1;

    public static JScrollPane namescrol2;
    public static JScrollPane extensionscrol2;
    public static JScrollPane lastmodscrol2;
    public static JScrollPane sizescrol2;

    // gombok / menüoszlopok adatváltozói

    public static ArrayList<String> leftdrivedata = new ArrayList<>();
    public static ArrayList<String> rightdrivedata = new ArrayList<>();
    // nyers elérési utak oszlopokhoz
    public static ArrayList<String> leftdatastructureraw = new ArrayList<>();
    public static ArrayList<String> rightdatastructureraw = new ArrayList<>();

    // szortirozot oszlopok
    public static ArrayList<String> leftdatastructurerawsorted = new ArrayList<>();
    public static ArrayList<String> rightdatastructurerawsorted = new ArrayList<>();

    // másolandó információk
    public static ArrayList<String> copy = new ArrayList<>();

    // poziciók

    public static String pathleft;
    public static String pathright;
    // felesleges változók de valahogy mégis kellenek

    public static int actions;
    public static int loadgame;

}
