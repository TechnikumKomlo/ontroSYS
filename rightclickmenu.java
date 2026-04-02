import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
public class rightclickmenu {
    
    public static JPopupMenu popupMenuL = new JPopupMenu();
    public static JPopupMenu popupMenuR = new JPopupMenu();
    public static JMenuItem refreshleft = new JMenuItem();
    public static JMenuItem refreshright = new JMenuItem();
    public static JMenuItem copyleft = new JMenuItem();
    public static JMenuItem copyright = new JMenuItem();
    public static JMenuItem cutleft = new JMenuItem();
    public static JMenuItem cutright = new JMenuItem();
    public static JMenuItem newfolderleft = new JMenuItem();
    public static JMenuItem newfolderright = new JMenuItem();
    public static JMenuItem pasteleft = new JMenuItem();
    public static JMenuItem pasteright = new JMenuItem();
     public static JMenuItem deleteleft = new JMenuItem();
    public static JMenuItem deleteright = new JMenuItem();
     public static JMenuItem renameleft = new JMenuItem();
    public static JMenuItem renameright = new JMenuItem();
    public void setuprightclickmenu(){
    
    //jobb click menühöz való választási opciók hozzáadása
    popupMenuL.add(refreshleft);
    popupMenuR.add(refreshright);
    popupMenuL.add(copyleft);
    popupMenuR.add(copyright);
    popupMenuL.add(cutleft);
    popupMenuR.add(cutright);
    popupMenuL.add(pasteleft);
    popupMenuR.add(pasteright);
    popupMenuL.add(deleteleft);
    popupMenuR.add(deleteright);
    popupMenuL.add(renameleft);
    popupMenuR.add(renameright);
    popupMenuL.add(newfolderleft);
    popupMenuR.add(newfolderright);
    
    FileIO nyelvinput = new FileIO();


    refreshleft.setText(nyelvinput.ReadLine(Gvar.nyelv, 10));
    refreshright.setText(nyelvinput.ReadLine(Gvar.nyelv, 10));
    copyleft.setText(nyelvinput.ReadLine(Gvar.nyelv, 11));
    copyright.setText(nyelvinput.ReadLine(Gvar.nyelv, 11));
    cutleft.setText(nyelvinput.ReadLine(Gvar.nyelv, 12));
    cutright.setText(nyelvinput.ReadLine(Gvar.nyelv, 12));
    newfolderleft.setText(nyelvinput.ReadLine(Gvar.nyelv, 13));
    newfolderright.setText(nyelvinput.ReadLine(Gvar.nyelv, 13));
    pasteleft.setText(nyelvinput.ReadLine(Gvar.nyelv, 16));
    pasteright.setText(nyelvinput.ReadLine(Gvar.nyelv, 16));
    deleteleft.setText(nyelvinput.ReadLine(Gvar.nyelv, 20));
    deleteright.setText(nyelvinput.ReadLine(Gvar.nyelv, 20));
    renameleft.setText(nyelvinput.ReadLine(Gvar.nyelv, 21));
    renameright.setText(nyelvinput.ReadLine(Gvar.nyelv, 21));
    leftside_rightclick();
    rightside_rightclick();

    rightclickmenu_Actionlisteners();
    }


    public void leftside_rightclick(){


        //listához tartozó actionlistener bal oldal

        Gvar.namelist1.addMouseListener(new MouseAdapter() {
            private void showPopup(MouseEvent e) {
                if (e.isPopupTrigger()) {


                    popupMenuL.show(Gvar.namelist1, e.getX(), e.getY());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                showPopup(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                showPopup(e);
            }
        });

        Gvar.extensionlist1.addMouseListener(new MouseAdapter() {
            private void showPopup(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popupMenuL.show(Gvar.extensionlist1, e.getX(), e.getY());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                showPopup(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                showPopup(e);
            }
        });
        Gvar.lastmodlist1.addMouseListener(new MouseAdapter() {
            private void showPopup(MouseEvent e) {
                    if (e.isPopupTrigger()) {
                        popupMenuL.show(Gvar.lastmodlist1, e.getX(), e.getY());
                    }
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                showPopup(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                showPopup(e);
            }
        });
        Gvar.sizelist1.addMouseListener(new MouseAdapter() {
            private void showPopup(MouseEvent e) {
                    if (e.isPopupTrigger()) {
                        popupMenuL.show(Gvar.sizelist1, e.getX(), e.getY());
                    }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                showPopup(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                showPopup(e);
            }
        });

        //jobb oldal
}
        
    public void rightside_rightclick(){

        //listához tartozó actionlistener jobb oldal

        Gvar.namelist2.addMouseListener(new MouseAdapter() {
            private void showPopup(MouseEvent e) {
                if (e.isPopupTrigger()) {

                        popupMenuR.show(Gvar.namelist2, e.getX(), e.getY());

            }
        }

            @Override
            public void mousePressed(MouseEvent e) {
                showPopup(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                showPopup(e);
            }
        });

        Gvar.extensionlist2.addMouseListener(new MouseAdapter() {
            private void showPopup(MouseEvent e) {
                if (e.isPopupTrigger()) {

                 
                        popupMenuR.show(Gvar.extensionlist2, e.getX(), e.getY());
                    }
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                showPopup(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                showPopup(e);
            }
        });
        Gvar.lastmodlist2.addMouseListener(new MouseAdapter() {
            private void showPopup(MouseEvent e) {
                if (e.isPopupTrigger()) {

                        popupMenuR.show(Gvar.lastmodlist2, e.getX(), e.getY());
                    }
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                showPopup(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                showPopup(e);
            }
        });
        Gvar.sizelist2.addMouseListener(new MouseAdapter() {
            private void showPopup(MouseEvent e) {
                if (e.isPopupTrigger()) { 

                        popupMenuR.show(Gvar.sizelist2, e.getX(), e.getY());
                    }
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                showPopup(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                showPopup(e);
            }
        });



    }



    //gomb actionlisteners
public static void rightclickmenu_Actionlisteners(){
    dataprocess data = new dataprocess();
    FileIO nyelvinput = new FileIO();
    refreshleft.addActionListener(ev -> {
        data.reloadleftsidestucture();
    });
  
    refreshright.addActionListener(ev -> {
        data.reloadrightsidestucture();
    });


    newfolderleft.addActionListener(ev -> {
    data.createnewfolder(Gvar.pathleft + "\\"+ nyelvinput.ReadLine(Gvar.nyelv, 13));
    data.reloadleftsidestucture();
    });

    newfolderright.addActionListener(ev -> {
    data.createnewfolder(Gvar.pathright + "\\"+ nyelvinput.ReadLine(Gvar.nyelv, 13));
    data.reloadrightsidestucture();
    });


    //másolás funkció

    copyleft.addActionListener(ev -> {
        //kiválasztott elemek megszerzése
        int[] selectedIndices = Gvar.namelist1.getSelectedIndices();
        //kiválasztott elemek listázása másolásra szánt arraylistbe
        Gvar.copy.clear();
        Gvar.actions = 1;
        for (int i : selectedIndices) {
            Gvar.copy.add(Gvar.leftdatastructureraw.get(i));
            System.out.println(selectedIndices);
        }

    
    });
        copyright.addActionListener(ev -> {
        //kiválasztott elemek megszerzése
        int[] selectedIndices = Gvar.namelist2.getSelectedIndices();
        //kiválasztott elemek listázása másolásra szánt arraylistbe
        Gvar.copy.clear();
        Gvar.actions = 1;
        for (int i : selectedIndices) {
            Gvar.copy.add(Gvar.rightdatastructureraw.get(i));
        }


    });

    //kivágás

       cutleft.addActionListener(ev -> {
        //kiválasztott elemek megszerzése
        int[] selectedIndices = Gvar.namelist1.getSelectedIndices();
        //kiválasztott elemek listázása másolásra szánt arraylistbe
        Gvar.copy.clear();
        Gvar.actions = 2;
        for (int i : selectedIndices) {
            Gvar.copy.add(Gvar.leftdatastructureraw.get(i));
            System.out.println(selectedIndices);
        }

    
    });
        cutright.addActionListener(ev -> {
        //kiválasztott elemek megszerzése
        int[] selectedIndices = Gvar.namelist2.getSelectedIndices();
        //kiválasztott elemek listázása másolásra szánt arraylistbe
        Gvar.copy.clear();
        Gvar.actions = 2;
        for (int i : selectedIndices) {
            Gvar.copy.add(Gvar.rightdatastructureraw.get(i));
        }


    });

    //törlés
        deleteleft.addActionListener(ev -> {
        //kiválasztott elemek megszerzése
        int[] selectedIndices = Gvar.namelist1.getSelectedIndices();
        //kiválasztott elemek listázása másolásra szánt arraylistbe
        Gvar.copy.clear();
        Gvar.actions = 3;
        for (int i : selectedIndices) {
            Gvar.copy.add(Gvar.leftdatastructureraw.get(i));
        }

        fileactionGUI copyfile = new fileactionGUI();
        copyfile.graphdialog(Gvar.pathleft, Gvar.copy);

    });

        deleteright.addActionListener(ev -> {
        //kiválasztott elemek megszerzése
        int[] selectedIndices = Gvar.namelist2.getSelectedIndices();
        //kiválasztott elemek listázása másolásra szánt arraylistbe
        Gvar.copy.clear();
        Gvar.actions = 3;
        for (int i : selectedIndices) {
            Gvar.copy.add(Gvar.rightdatastructureraw.get(i));
        }

        fileactionGUI copyfile = new fileactionGUI();
        copyfile.graphdialog(Gvar.pathright, Gvar.copy);

    });


    //beilesztés funkció

        pasteleft.addActionListener(ev -> {
        fileactionGUI copyfile = new fileactionGUI();
        
            copyfile.graphdialog(Gvar.pathleft, Gvar.copy);
        
    });

        pasteright.addActionListener(ev -> {
        fileactionGUI copyfile = new fileactionGUI();
            copyfile.graphdialog(Gvar.pathright, Gvar.copy);
        
  
            
        

    
    });



    //átnevezés
    renameleft.addActionListener(ev -> {
        int selectedIndice = Gvar.namelist1.getSelectedIndex();

        if (selectedIndice >= 0) {
        String path = Gvar.leftdatastructureraw.get(selectedIndice);
        File oldFile = new File(path);

        // Ask user for new name
        String newName = JOptionPane.showInputDialog(null,nyelvinput.ReadLine(Gvar.nyelv, 21) );
        if (newName != null && !newName.trim().isEmpty()) {
            File newFile = new File(oldFile.getParent(), newName);

            boolean success = oldFile.renameTo(newFile);
            if (success) {
                
                // Update your data structure with the new path
                Gvar.leftdatastructureraw.set(selectedIndice, newFile.getAbsolutePath());
                dataprocess refresh = new dataprocess();
                refresh.reloadleftsidestucture();
            } else {
            }
        }
    }



    });

    renameright.addActionListener(ev -> {
        int selectedIndice = Gvar.namelist2.getSelectedIndex();

        if (selectedIndice >= 0) {
        String path = Gvar.rightdatastructureraw.get(selectedIndice);
        File oldFile = new File(path);

        // Ask user for new name
        String newName = JOptionPane.showInputDialog(null,nyelvinput.ReadLine(Gvar.nyelv, 21) );
        if (newName != null && !newName.trim().isEmpty()) {
            File newFile = new File(oldFile.getParent(), newName);

            boolean success = oldFile.renameTo(newFile);
            if (success) {
                
                // Update your data structure with the new path
                Gvar.rightdatastructureraw.set(selectedIndice, newFile.getAbsolutePath());
                dataprocess refresh = new dataprocess();
                refresh.reloadrightsidestucture();
            } else {
            }
        }
    }



    });


}
}
