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
import java.io.IOException;

import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.plaf.metal.*;
import javax.swing.plaf.*;


public class actionlisteners {
    public void createactionlisteners(){
        FileIO write_language = new FileIO();

        // menüsor gombok
        menubar.language.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                language_change lang = new language_change();
                lang.changelanguage();

            }
        });


        menubar.Terminal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){

                Terminal asd = new Terminal();
                asd.term();
            }
        });


        //egyhangú görgetés bal oldal



            AdjustmentListener syncScroll1 = new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                JScrollBar source = (JScrollBar) e.getSource();
                JScrollBar[] targets = {
                        Gvar.namescrol1.getVerticalScrollBar(),
                        Gvar.extensionscrol1.getVerticalScrollBar(),
                        Gvar.lastmodscrol1.getVerticalScrollBar(),
                        Gvar.sizescrol1.getVerticalScrollBar()
                };
                for (JScrollBar target : targets) {
                    if (source != target) {
                        target.setValue(source.getValue());
                    }
                }
            }

        };

        Gvar.namescrol1.getVerticalScrollBar().addAdjustmentListener(syncScroll1);
        Gvar.extensionscrol1.getVerticalScrollBar().addAdjustmentListener(syncScroll1);
        Gvar.lastmodscrol1.getVerticalScrollBar().addAdjustmentListener(syncScroll1);
        Gvar.sizescrol1.getVerticalScrollBar().addAdjustmentListener(syncScroll1);


        //jobb oldal


    AdjustmentListener syncScroll2 = new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                JScrollBar source = (JScrollBar) e.getSource();
                JScrollBar[] targets = {
                        Gvar.namescrol2.getVerticalScrollBar(),
                        Gvar.extensionscrol2.getVerticalScrollBar(),
                        Gvar.lastmodscrol2.getVerticalScrollBar(),
                        Gvar.sizescrol2.getVerticalScrollBar()
                };
                for (JScrollBar target : targets) {
                    if (source != target) {
                        target.setValue(source.getValue());
                    }
                }
            }

        };

        Gvar.namescrol2.getVerticalScrollBar().addAdjustmentListener(syncScroll2);
        Gvar.extensionscrol2.getVerticalScrollBar().addAdjustmentListener(syncScroll2);
        Gvar.lastmodscrol2.getVerticalScrollBar().addAdjustmentListener(syncScroll2);
        Gvar.sizescrol2.getVerticalScrollBar().addAdjustmentListener(syncScroll2);
    


  






    //kijelölés össz helyen bal oldal
    

    ListSelectionListener listener1 = new ListSelectionListener() {
        @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    JList<String> source = (JList<String>) e.getSource();
                    int[] selectedIndices = source.getSelectedIndices();

                    Gvar.namelist1.removeListSelectionListener(this);
                    Gvar.extensionlist1.removeListSelectionListener(this);
                    Gvar.lastmodlist1.removeListSelectionListener(this);
                    Gvar.sizelist1.removeListSelectionListener(this);

                    if (source != Gvar.namelist1)
                        Gvar.namelist1.setSelectedIndices(selectedIndices);
                    if (source != Gvar.extensionlist1)
                        Gvar.extensionlist1.setSelectedIndices(selectedIndices);
                    if (source != Gvar.lastmodlist1)
                        Gvar.lastmodlist1.setSelectedIndices(selectedIndices);
                    if (source != Gvar.sizelist1)
                        Gvar.sizelist1.setSelectedIndices(selectedIndices);

                    Gvar.namelist1.addListSelectionListener(this);
                    Gvar.extensionlist1.addListSelectionListener(this);
                    Gvar.lastmodlist1.addListSelectionListener(this);
                    Gvar.sizelist1.addListSelectionListener(this);
                }
            }

        };
        Gvar.namelist1.addListSelectionListener(listener1);
        Gvar.extensionlist1.addListSelectionListener(listener1);
        Gvar.lastmodlist1.addListSelectionListener(listener1);
        Gvar.sizelist1.addListSelectionListener(listener1);
    
    
    

        //jobb oldal


            ListSelectionListener listener2 = new ListSelectionListener() {
        @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    JList<String> source = (JList<String>) e.getSource();
                    int[] selectedIndices = source.getSelectedIndices();

                    Gvar.namelist2.removeListSelectionListener(this);
                    Gvar.extensionlist2.removeListSelectionListener(this);
                    Gvar.lastmodlist2.removeListSelectionListener(this);
                    Gvar.sizelist2.removeListSelectionListener(this);

                    if (source != Gvar.namelist2)
                        Gvar.namelist2.setSelectedIndices(selectedIndices);
                    if (source != Gvar.extensionlist2)
                        Gvar.extensionlist2.setSelectedIndices(selectedIndices);
                    if (source != Gvar.lastmodlist2)
                        Gvar.lastmodlist2.setSelectedIndices(selectedIndices);
                    if (source != Gvar.sizelist2)
                        Gvar.sizelist2.setSelectedIndices(selectedIndices);

                    Gvar.namelist2.addListSelectionListener(this);
                    Gvar.extensionlist2.addListSelectionListener(this);
                    Gvar.lastmodlist2.addListSelectionListener(this);
                    Gvar.sizelist2.addListSelectionListener(this);
                }
            }

        };
        Gvar.namelist2.addListSelectionListener(listener2);
        Gvar.extensionlist2.addListSelectionListener(listener2);
        Gvar.lastmodlist2.addListSelectionListener(listener2);
        Gvar.sizelist2.addListSelectionListener(listener2);








        //fájl adat sor gombok

        Gvar.name1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dataprocess data = new dataprocess();
                data.sortbynameleft();

            }
        });

        Gvar.extension1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dataprocess data = new dataprocess();
                data.sortByExtensionLeft();

            }
        });

        Gvar.lastmod1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dataprocess data = new dataprocess();
                data.sortByLastModifiedLeft();

            }
        });

        Gvar.size1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dataprocess data = new dataprocess();
                data.sortBySizeLeft();

            }
        });


        // jobb oldal
        
        Gvar.name2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dataprocess data = new dataprocess();
                data.sortbynameright();

            }
        });

        Gvar.extension2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dataprocess data = new dataprocess();
                data.sortByExtensionRight();

            }
        });

        Gvar.lastmod2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dataprocess data = new dataprocess();
                data.sortByLastModifiedRight();

            }
        });

        Gvar.size2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dataprocess data = new dataprocess();
                data.sortBySizeRight();

            }
        });
    
        //bal oldal dupla kattintás
        Gvar.namelist1.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        int index = Gvar.namelist1.locationToIndex(e.getPoint());
                        String selectedItem = Gvar.leftdatastructureraw.get(index);
                        Path path = Paths.get(selectedItem);
                        if (Files.isDirectory(path)) {
                            dataprocess data = new dataprocess();
                            Gvar.pathleft = selectedItem;
                            data.reloadleftsidestucture();
                        }
                        else if (selectedItem.toLowerCase().endsWith(".anb_language")) {
                            System.out.println("siker"); 
                            Gvar.nyelv = selectedItem;
                            write_language.rewrite_to_end("data.anb_syst", selectedItem);
                            Gvar.frame.revalidate();
                            Gvar.frame.repaint();
                            Gvar.pathleft = language_change.old_position;
                        }
                        else if (selectedItem.toLowerCase().endsWith(".txt")) {
                            TextEditorv2 text = new TextEditorv2();
                            text.TextEdit(selectedItem);
                        }
                        else{
                            File file = new File(selectedItem);

                            if (Desktop.isDesktopSupported()) {
                                try {
                                    Desktop.getDesktop().open(file); // opens with default program
                                } catch (IOException x) {
                                    x.printStackTrace();
                                }
                            }

                            
                            
                        }

                    }
                }
            });
        Gvar.extensionlist1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = Gvar.extensionlist1.locationToIndex(e.getPoint());
                    String selectedItem = Gvar.leftdatastructureraw.get(index);
                    Path path = Paths.get(selectedItem);
                    if (Files.isDirectory(path)) {
                        dataprocess data = new dataprocess();
                        Gvar.pathleft = selectedItem;
                        data.reloadleftsidestucture();
                        }
                        else if (selectedItem.toLowerCase().endsWith(".anb_language")) {
                            System.out.println("siker"); 
                            Gvar.nyelv = selectedItem;
                            write_language.rewrite_to_end("data.anb_syst", selectedItem);
                            Gvar.frame.revalidate();
                         Gvar.frame.repaint();
                         Gvar.pathleft = language_change.old_position;
                        }
                        else if (selectedItem.toLowerCase().endsWith(".txt")) {
                            TextEditorv2 text = new TextEditorv2();
                            text.TextEdit(selectedItem);
                        }
                        else{
                            File file = new File(selectedItem);

                            if (Desktop.isDesktopSupported()) {
                                try {
                                    Desktop.getDesktop().open(file); // opens with default program
                                } catch (IOException x) {
                                    x.printStackTrace();
                                }
                            }

                            
                            
                        }
                }
            }
        });

            Gvar.lastmodlist1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = Gvar.lastmodlist1.locationToIndex(e.getPoint());
                    String selectedItem = Gvar.leftdatastructureraw.get(index);
                    Path path = Paths.get(selectedItem);
                    if (Files.isDirectory(path)) {
                        dataprocess data = new dataprocess();
                        Gvar.pathleft = selectedItem;
                        data.reloadleftsidestucture();
                    }
                    else if (selectedItem.toLowerCase().endsWith(".anb_language")) {     
                        System.out.println("siker"); 
                        Gvar.nyelv = selectedItem;
                        write_language.rewrite_to_end("data.anb_syst", selectedItem);
                        Gvar.frame.revalidate();
                         Gvar.frame.repaint();
                         Gvar.pathleft = language_change.old_position;
                        }
                        else if (selectedItem.toLowerCase().endsWith(".txt")) {
                            TextEditorv2 text = new TextEditorv2();
                            text.TextEdit(selectedItem);
                        }
                        else{
                            File file = new File(selectedItem);

                            if (Desktop.isDesktopSupported()) {
                                try {
                                    Desktop.getDesktop().open(file); // opens with default program
                                } catch (IOException x) {
                                    x.printStackTrace();
                                }
                            }

                            
                            
                        }
                }
            }
        });

            Gvar.sizelist1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = Gvar.sizelist1.locationToIndex(e.getPoint());
                    String selectedItem = Gvar.leftdatastructureraw.get(index);
                    Path path = Paths.get(selectedItem);
                    if (Files.isDirectory(path)) {
                        dataprocess data = new dataprocess();
                        Gvar.pathleft = selectedItem;
                        data.reloadleftsidestucture();
                    }
                    else if (selectedItem.toLowerCase().endsWith(".anb_language")) {   
                        System.out.println("siker"); 
                        Gvar.nyelv = selectedItem;
                        write_language.rewrite_to_end("data.anb_syst", selectedItem);
                        Gvar.frame.revalidate();
                         Gvar.frame.repaint();
                         Gvar.pathleft = language_change.old_position;
                        }
                        else if (selectedItem.toLowerCase().endsWith(".txt")) {
                            TextEditorv2 text = new TextEditorv2();
                            text.TextEdit(selectedItem);
                        }
                        else{
                            File file = new File(selectedItem);

                            if (Desktop.isDesktopSupported()) {
                                try {
                                    Desktop.getDesktop().open(file); // opens with default program
                                } catch (IOException x) {
                                    x.printStackTrace();
                                }
                            }

                            
                            
                        }
                }
            }
        });



        // jobb oldal



            Gvar.namelist2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = Gvar.namelist2.locationToIndex(e.getPoint());
                    String selectedItem = Gvar.rightdatastructureraw.get(index);
                    Path path = Paths.get(selectedItem);
                    if (Files.isDirectory(path)) {
                        dataprocess data = new dataprocess();
                        Gvar.pathright = selectedItem;
                        data.reloadrightsidestucture();
                    }
                    else if (selectedItem.toLowerCase().endsWith(".txt")) {
                            TextEditorv2 text = new TextEditorv2();
                            text.TextEdit(selectedItem);
                        }
                    else{
                            File file = new File(selectedItem);

                            if (Desktop.isDesktopSupported()) {
                                try {
                                    Desktop.getDesktop().open(file); // opens with default program
                                } catch (IOException x) {
                                    x.printStackTrace();
                                }
                            }

                            
                            
                        }
                }
            }
        });
        Gvar.extensionlist2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = Gvar.extensionlist2.locationToIndex(e.getPoint());
                    String selectedItem = Gvar.rightdatastructureraw.get(index);
                    Path path = Paths.get(selectedItem);
                    if (Files.isDirectory(path)) {
                        dataprocess data = new dataprocess();
                        Gvar.pathright = selectedItem;
                        data.reloadrightsidestucture();
                    }
                    else if (selectedItem.toLowerCase().endsWith(".txt")) {
                            TextEditorv2 text = new TextEditorv2();
                            text.TextEdit(selectedItem);
                        }
                    else{
                            File file = new File(selectedItem);

                            if (Desktop.isDesktopSupported()) {
                                try {
                                    Desktop.getDesktop().open(file); // opens with default program
                                } catch (IOException x) {
                                    x.printStackTrace();
                                }
                            }

                            
                            
                        }
                }
            }
        });

            Gvar.lastmodlist2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = Gvar.lastmodlist2.locationToIndex(e.getPoint());
                    String selectedItem = Gvar.rightdatastructureraw.get(index);
                    Path path = Paths.get(selectedItem);
                    if (Files.isDirectory(path)) {
                        dataprocess data = new dataprocess();
                        Gvar.pathright = selectedItem;
                        data.reloadrightsidestucture();
                    }
                    else if (selectedItem.toLowerCase().endsWith(".txt")) {
                            TextEditorv2 text = new TextEditorv2();
                            text.TextEdit(selectedItem);
                        }
                    else{
                            File file = new File(selectedItem);

                            if (Desktop.isDesktopSupported()) {
                                try {
                                    Desktop.getDesktop().open(file); // opens with default program
                                } catch (IOException x) {
                                    x.printStackTrace();
                                }
                            }

                            
                            
                        }
                }
            }
        });

            Gvar.sizelist2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = Gvar.sizelist2.locationToIndex(e.getPoint());
                    String selectedItem = Gvar.rightdatastructureraw.get(index);
                    Path path = Paths.get(selectedItem);
                    if (Files.isDirectory(path)) {
                        dataprocess data = new dataprocess();
                        Gvar.pathright = selectedItem;
                        data.reloadrightsidestucture();
                    }
                    else if (selectedItem.toLowerCase().endsWith(".txt")) {
                            TextEditorv2 text = new TextEditorv2();
                            text.TextEdit(selectedItem);
                        }
                    else{
                            File file = new File(selectedItem);

                            if (Desktop.isDesktopSupported()) {
                                try {
                                    Desktop.getDesktop().open(file); // opens with default program
                                } catch (IOException x) {
                                    x.printStackTrace();
                                }
                            }

                            
                            
                        }
                }
            }
        });



        // vissza gomb müködése
        //bal
     
        Gvar.back1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
             
            

            File current = new File(Gvar.pathleft);
            File parent = current.getParentFile();
            if (parent != null) {
                Gvar.pathleft = parent.getAbsolutePath();
            }
            dataprocess data = new dataprocess();
            data.reloadleftsidestucture();
            }
        });


        //jobb

            Gvar.back2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
             
            

            File current = new File(Gvar.pathright);
            File parent = current.getParentFile();
            if (parent != null) {
                Gvar.pathright = parent.getAbsolutePath();
            }
            dataprocess data = new dataprocess();
            data.reloadrightsidestucture();
            }
        });


        //meghajtó kiválasztása
        Gvar.leftdrive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) Gvar.leftdrive.getSelectedItem();
                Gvar.pathleft = selectedItem;
                dataprocess data = new dataprocess();
                data.reloadleftsidestucture();
            }
        });
        //jobb meghajtó
        Gvar.rightdrive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) Gvar.rightdrive.getSelectedItem();
                Gvar.pathright = selectedItem;
                dataprocess data = new dataprocess();
                data.reloadrightsidestucture();
            }
        });
        




        menubar.Metal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lookandfeel kinezet = new lookandfeel();
                kinezet.setCrossPlatformLookAndFeel();
                Gvar.panel.revalidate();
                Gvar.panel.repaint();
            }
        });
        menubar.Motif.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lookandfeel kinezet = new lookandfeel();
                kinezet.setMotifLookAndFeel();
                Gvar.panel.revalidate();
                Gvar.panel.repaint();
            }
        });
        menubar.System.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lookandfeel kinezet = new lookandfeel();
                kinezet.setSystemLookAndFeel();
                Gvar.panel.revalidate();
                Gvar.panel.repaint();
            }
        });
        menubar.Nimbus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lookandfeel kinezet = new lookandfeel();
                kinezet.setNimbusLookAndFeel();
                Gvar.panel.revalidate();
                Gvar.panel.repaint();
            }
        });


        









    }

    



}

