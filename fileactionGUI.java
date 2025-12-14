import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
public class fileactionGUI {
    
public void graphdialog(String finalpath, ArrayList<String> absolutepaths){

        FileIO nyelv = new FileIO();

        JDialog dialog = new JDialog(Gvar.frame, null, false); 
        dialog.setSize(300, 100);
        dialog.setLocationRelativeTo(Gvar.frame);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLayout(new BorderLayout());
        String labelnyelv ="";
        if (Gvar.actions ==1) {
          labelnyelv=nyelv.ReadLine(Gvar.nyelv, 11);
        }
        if (Gvar.actions==2) {
            labelnyelv=nyelv.ReadLine(Gvar.nyelv, 12);
        }
                if (Gvar.actions==3) {
            labelnyelv=nyelv.ReadLine(Gvar.nyelv, 20);
        }
        JLabel label = new JLabel(labelnyelv, JLabel.CENTER);
        dialog.add(label, BorderLayout.NORTH);
        dialog.setVisible(true);
        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        dialog.add(progressBar, BorderLayout.SOUTH);

        dialog.setResizable(false);
        
        
        JPanel buttonPanel = new JPanel();
        JButton pauseButton = new JButton(nyelv.ReadLine(Gvar.nyelv, 14));
        JButton stopButton = new JButton(nyelv.ReadLine(Gvar.nyelv, 15));
        buttonPanel.add(pauseButton);
        buttonPanel.add(stopButton);
        dialog.add(buttonPanel, BorderLayout.WEST );

        FileCopier newcop = new FileCopier();
        FileCut cutfile = new FileCut();
        Deletefile deletefile = new Deletefile();



        pauseButton.addActionListener(ev -> {
            newcop.stop = !newcop.stop;

        });

        stopButton.addActionListener(ev -> {
            newcop.cancel = true;
        });
        //másolás inditása
        if (Gvar.actions ==1) {
            new Thread(() -> {
            newcop.copy(finalpath, absolutepaths);
            dialog.dispose();
            dataprocess reloadfiles = new dataprocess();
            if (Gvar.pathleft == finalpath) {
                reloadfiles.reloadleftsidestucture();
            }
            if (Gvar.pathright == finalpath) {
                reloadfiles.reloadrightsidestucture();
            }
            
            
        }).start();
        }
        //kivágás
        if (Gvar.actions ==2) {
            new Thread(() -> {
            cutfile.cut(finalpath, absolutepaths);
            dialog.dispose();
            dataprocess reloadfiles = new dataprocess();
            if (Gvar.pathleft == finalpath) {
                reloadfiles.reloadleftsidestucture();
            }
            if (Gvar.pathright == finalpath) {
                reloadfiles.reloadrightsidestucture();
            }

        }).start();

        }
        //törlés
        if (Gvar.actions ==3) {
            new Thread(() -> {
            deletefile.delete(absolutepaths);
            dialog.dispose();
            dataprocess reloadfiles = new dataprocess();
            if (Gvar.pathleft == finalpath) {
                reloadfiles.reloadleftsidestucture();
            }
            if (Gvar.pathright == finalpath) {
                reloadfiles.reloadrightsidestucture();
            }
        }).start();

        }
        //másolás
        if (Gvar.actions ==1) {
            
            // GUI update timer
        int maximumnumfile =0;
        for (String string : absolutepaths) {
            maximumnumfile += countFiles(string);
            label.setText(nyelv.ReadLine(Gvar.nyelv, 19));
        }
        progressBar.setMaximum(maximumnumfile);
        System.out.println(maximumnumfile);
        Timer uiTimer = new Timer(100, e -> {
      
        label.setText(nyelv.ReadLine(Gvar.nyelv, 17) + newcop.speedstring);

        progressBar.setValue(newcop.copiedCount);
        progressBar.setIndeterminate(false);

        if (newcop.complete) {
            dialog.dispose();
        }

        if (newcop.cancel) {
            ((Timer) e.getSource()).stop();
            progressBar.setIndeterminate(true);
            label.setText(nyelv.ReadLine(Gvar.nyelv, 18));
        }
    });
    uiTimer.start();
        }
        //kivágás
        if (Gvar.actions ==2) {
            
            // GUI update timer
        int maximumnumfile =0;
        for (String string : absolutepaths) {
            maximumnumfile += countFiles(string);
            label.setText(nyelv.ReadLine(Gvar.nyelv, 19));
        }
        progressBar.setMaximum(maximumnumfile);
        System.out.println(maximumnumfile);
        Timer uiTimer = new Timer(100, e -> {
      
        label.setText(nyelv.ReadLine(Gvar.nyelv, 17) + cutfile.speedstring);

        progressBar.setValue(cutfile.copiedCount);
        progressBar.setIndeterminate(false);

        if (cutfile.complete) {
            dialog.dispose();
        }

        if (cutfile.cancel) {
            ((Timer) e.getSource()).stop();
            progressBar.setIndeterminate(true);
            label.setText(nyelv.ReadLine(Gvar.nyelv, 18));
        }
    });
    uiTimer.start();
        }
        //törlés
        if (Gvar.actions ==3) {
            
            // GUI update timer
        int maximumnumfile =0;
        for (String string : absolutepaths) {
            maximumnumfile += countFiles(string);
            label.setText(nyelv.ReadLine(Gvar.nyelv, 19));
        }
        progressBar.setMaximum(maximumnumfile);
        System.out.println(maximumnumfile);
        Timer uiTimer = new Timer(100, e -> {
      
        label.setText(nyelv.ReadLine(Gvar.nyelv, 17) + deletefile.speedstring);

        progressBar.setValue(deletefile.deletedCount);
        progressBar.setIndeterminate(false);

        if (deletefile.complete) {
            dialog.dispose();
        }

        if (deletefile.cancel) {
            ((Timer) e.getSource()).stop();
            progressBar.setIndeterminate(true);
            label.setText(nyelv.ReadLine(Gvar.nyelv, 18));
        }
    });
    uiTimer.start();
        }



    }



    public static int countFiles(String absolutePath) {
        File root = new File(absolutePath);
        if (!root.exists()) {
            System.err.println("Path does not exist: " + absolutePath);
            return 0;
        }
        return countRecursive(root);
    }

    // minden fájl számolása beágyazot fájlokal együt
    private static int countRecursive(File dir) {
        int count = 0;
        File[] files = dir.listFiles();
        if (files == null) return 0; 

        for (File file : files) {
            if (file.isDirectory()) {
                count += countRecursive(file); // beágyazot fájlokért
            } else {
                count++; 
            }
        }
        return count;
    }

  
}