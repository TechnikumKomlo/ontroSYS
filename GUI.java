import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;

import java.awt.*;

public class GUI {
    public void UI(){
        FileIO nyelvinput = new FileIO();

        //sulyozot méret változtatás összes elemre
        Gvar.gbc.weightx = 1;   
        Gvar.gbc.weighty = 0;   
        Gvar.gbc.fill = GridBagConstraints.BOTH;


        //fenti menüsor
        Gvar.gbc.gridx = 0;
        Gvar.gbc.gridy = 0;
        Gvar.gbc.gridwidth =10;
        //fenti menüsor elemek hozzáadása
        menubar menu = new menubar();
        menu.menubaritems();
        Gvar.panel.add(Gvar.menubar, Gvar.gbc);




        //fenti második oszlop elemei
        //elsö vissza gomb
        String item = nyelvinput.ReadLine(Gvar.nyelv, 5);
        Gvar.gbc.gridwidth =1;
        Gvar.gbc.gridx = 0;
        Gvar.gbc.gridy =1;
        Gvar.back1 = new JButton(item);
        Gvar.panel.add(Gvar.back1, Gvar.gbc);

        //elsö meghajtó választó

        Gvar.gbc.gridx = 1;
        Gvar.gbc.gridy =1;
        Gvar.leftdrive = new JComboBox<>();
        Gvar.panel.add(Gvar.leftdrive, Gvar.gbc);


        //bal fájl pozició


        Gvar.gbc.gridx = 2;
        Gvar.gbc.gridy =1;

        Gvar.gbc.gridwidth =3;

        Gvar.leftfolderpoz = new JLabel();

        Gvar.panel.add(Gvar.leftfolderpoz, Gvar.gbc);


        // második vissza gomb
        Gvar.gbc.gridx = 5;
        Gvar.gbc.gridy = 1;
        Gvar.gbc.gridwidth =1;
        item = nyelvinput.ReadLine(Gvar.nyelv, 5);
        Gvar.back2 = new JButton(item);
        Gvar.panel.add(Gvar.back2,Gvar.gbc);

        // második meghajtó választó
        Gvar.gbc.gridx = 6;
        Gvar.gbc.gridy = 1;
        Gvar.rightdrive = new JComboBox<>();
        Gvar.panel.add(Gvar.rightdrive,Gvar.gbc);


        //jobb fájl pozició

        Gvar.gbc.gridx = 7;
        Gvar.gbc.gridy =1;

        Gvar.gbc.gridwidth =3;

        Gvar.rightfolderpoz = new JLabel();

        Gvar.panel.add(Gvar.rightfolderpoz, Gvar.gbc);




        //harmadik sor oszlopok elnevezése


        Gvar.gbc.gridx = 0;
        Gvar.gbc.gridy = 2;
        Gvar.gbc.gridwidth =2;
        item = nyelvinput.ReadLine(Gvar.nyelv, 6);
        Gvar.name1 = new JButton(item);
        
        // úgy nézzen ki mint egy textfield
        Gvar.name1.setFocusPainted(false); 
        Gvar.name1.setBorderPainted(false); 
        Gvar.name1.setContentAreaFilled(false);
        Gvar.name1.setFont(UIManager.getFont("TextField.font"));
        Gvar.name1.setFocusPainted(false);
        Gvar.panel.add(Gvar.name1, Gvar.gbc);

        //extension
        Gvar.gbc.gridx = 2;
        Gvar.gbc.gridy = 2;
        Gvar.gbc.gridwidth =1;
        item = nyelvinput.ReadLine(Gvar.nyelv, 7);
        Gvar.extension1 = new JButton(item);
        
        // úgy nézzen ki mint egy textfield
        Gvar.extension1.setFocusPainted(false); 
        Gvar.extension1.setBorderPainted(false); 
        Gvar.extension1.setContentAreaFilled(false);
        Gvar.extension1.setFont(UIManager.getFont("TextField.font"));
        Gvar.extension1.setFocusPainted(false);
        Gvar.panel.add(Gvar.extension1, Gvar.gbc);



        // utolsó Módositás dátuma

        Gvar.gbc.gridx = 3;
        Gvar.gbc.gridy = 2;

        item = nyelvinput.ReadLine(Gvar.nyelv, 8);
        Gvar.lastmod1 = new JButton(item);
        
        // úgy nézzen ki mint egy textfield
        Gvar.lastmod1.setFocusPainted(false); 
        Gvar.lastmod1.setBorderPainted(false); 
        Gvar.lastmod1.setContentAreaFilled(false);
        Gvar.lastmod1.setFont(UIManager.getFont("TextField.font"));
        Gvar.lastmod1.setFocusPainted(false);
        Gvar.panel.add(Gvar.lastmod1, Gvar.gbc);
        
        
        // méret

        Gvar.gbc.gridx = 4;
        Gvar.gbc.gridy = 2;

        item = nyelvinput.ReadLine(Gvar.nyelv, 9);
        Gvar.size1 = new JButton(item);
        
        // úgy nézzen ki mint egy textfield
        Gvar.size1.setFocusPainted(false); 
        Gvar.size1.setBorderPainted(false); 
        Gvar.size1.setContentAreaFilled(false);
        Gvar.size1.setFont(UIManager.getFont("TextField.font"));
        Gvar.size1.setFocusPainted(false);
        Gvar.panel.add(Gvar.size1, Gvar.gbc);
        



        //második fele:



        Gvar.gbc.gridx = 5;
        Gvar.gbc.gridy = 2;
        Gvar.gbc.gridwidth = 2;
        item = nyelvinput.ReadLine(Gvar.nyelv, 6);
        Gvar.name2 = new JButton(item);
        
        // úgy nézzen ki mint egy textfield
        Gvar.name2.setFocusPainted(false); 
        Gvar.name2.setBorderPainted(false); 
        Gvar.name2.setContentAreaFilled(false);
        Gvar.name2.setFont(UIManager.getFont("TextField.font"));
        Gvar.name2.setFocusPainted(false);
        Gvar.panel.add(Gvar.name2, Gvar.gbc);

        //extension
        Gvar.gbc.gridx = 7;
        Gvar.gbc.gridy = 2;
        Gvar.gbc.gridwidth =1;
        item = nyelvinput.ReadLine(Gvar.nyelv, 7);
        Gvar.extension2 = new JButton(item);
        
        // úgy nézzen ki mint egy textfield
        Gvar.extension2.setFocusPainted(false); 
        Gvar.extension2.setBorderPainted(false); 
        Gvar.extension2.setContentAreaFilled(false);
        Gvar.extension2.setFont(UIManager.getFont("TextField.font"));
        Gvar.extension2.setFocusPainted(false);
        Gvar.panel.add(Gvar.extension2, Gvar.gbc);



        // utolsó Módositás dátuma

        Gvar.gbc.gridx = 8;
        Gvar.gbc.gridy = 2;

        item = nyelvinput.ReadLine(Gvar.nyelv, 8);
        Gvar.lastmod2 = new JButton(item);
        
        // úgy nézzen ki mint egy textfield
        Gvar.lastmod2.setFocusPainted(false); 
        Gvar.lastmod2.setBorderPainted(false); 
        Gvar.lastmod2.setContentAreaFilled(false);
        Gvar.lastmod2.setFont(UIManager.getFont("TextField.font"));
        Gvar.lastmod2.setFocusPainted(false);
        Gvar.panel.add(Gvar.lastmod2, Gvar.gbc);
        
        
        // méret

        Gvar.gbc.gridx = 9;
        Gvar.gbc.gridy = 2;

        item = nyelvinput.ReadLine(Gvar.nyelv, 9);
        Gvar.size2 = new JButton(item);
        
        // úgy nézzen ki mint egy textfield
        Gvar.size2.setFocusPainted(false); 
        Gvar.size2.setBorderPainted(false); 
        Gvar.size2.setContentAreaFilled(false);
        Gvar.size2.setFont(UIManager.getFont("TextField.font"));
        Gvar.size2.setFocusPainted(false);
        Gvar.panel.add(Gvar.size2, Gvar.gbc);
        




        // oszlopok hozzáadása a scrollpane hez
        Gvar.gbc.gridwidth =2;
        Gvar.gbc.weightx =  0;   
        Gvar.gbc.weighty = 1;  


        Gvar.gbc.gridx = 0;
        Gvar.gbc.gridy = 3;

        Gvar.namelist1 = new JList<>();

        Gvar.namescrol1 = new JScrollPane(Gvar.namelist1);
        Gvar.namescrol1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        Gvar.namescrol1.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
        Gvar.panel.add(Gvar.namescrol1,Gvar.gbc);



        Gvar.gbc.gridwidth =1;
        Gvar.gbc.gridx = 2;
        Gvar.gbc.gridy = 3;

        Gvar.extensionlist1 = new JList<>();
      
        Gvar.extensionscrol1 = new JScrollPane(Gvar.extensionlist1);
        Gvar.extensionscrol1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        Gvar.extensionscrol1.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
        Gvar.panel.add(Gvar.extensionscrol1,Gvar.gbc);



        Gvar.gbc.gridx = 3;
        Gvar.gbc.gridy = 3;

        Gvar.lastmodlist1 = new JList<>();
       
        Gvar.lastmodscrol1 = new JScrollPane(Gvar.lastmodlist1);
        Gvar.lastmodscrol1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        Gvar.lastmodscrol1.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
        Gvar.panel.add(Gvar.lastmodscrol1,Gvar.gbc);


        Gvar.gbc.gridx = 4;
        Gvar.gbc.gridy = 3;

        Gvar.sizelist1 = new JList<>();
        
        Gvar.sizescrol1 = new JScrollPane(Gvar.sizelist1);
        Gvar.sizescrol1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        Gvar.panel.add(Gvar.sizescrol1,Gvar.gbc);

        //jobb oldal

        Gvar.gbc.gridwidth =2;
        Gvar.gbc.weightx =  0;   
        Gvar.gbc.weighty = 1;  


        Gvar.gbc.gridx = 5;
        Gvar.gbc.gridy = 3;
        


        Gvar.namelist2 = new JList<>();
  
        Gvar.namescrol2 = new JScrollPane(Gvar.namelist2);
        Gvar.namescrol2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        Gvar.namescrol2.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
        Gvar.panel.add(Gvar.namescrol2,Gvar.gbc);





        Gvar.gbc.gridwidth =1;
        Gvar.gbc.gridx = 7;
        Gvar.gbc.gridy = 3;
        
        Gvar.extensionlist2 = new JList<>();

        Gvar.extensionscrol2 = new JScrollPane(Gvar.extensionlist2);
        Gvar.extensionscrol2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        Gvar.extensionscrol2.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
        Gvar.panel.add(Gvar.extensionscrol2,Gvar.gbc);




        Gvar.gbc.gridx = 8;
        Gvar.gbc.gridy = 3;
        
        Gvar.lastmodlist2 = new JList<>();
 
        Gvar.lastmodscrol2 = new JScrollPane(Gvar.lastmodlist2);
        Gvar.lastmodscrol2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        Gvar.lastmodscrol2.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
        Gvar.panel.add(Gvar.lastmodscrol2,Gvar.gbc);



        Gvar.gbc.gridx = 9;
        Gvar.gbc.gridy = 3;
        
        Gvar.sizelist2 = new JList<>();

        Gvar.sizescrol2 = new JScrollPane(Gvar.sizelist2);
        Gvar.sizescrol2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        Gvar.panel.add(Gvar.sizescrol2,Gvar.gbc);



        //görgethetőek legyenek az oszlopok
        







    }



}





