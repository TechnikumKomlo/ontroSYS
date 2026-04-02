import javax.swing.*;

public class lookandfeel {
    FileIO databus = new FileIO();

    // Set to system default look and feel
    public void setSystemLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(Gvar.frame);
            databus.write_to_Line("./src/main/java/data.anb_syst", "0", 1);
            Gvar.frame.pack();
            Gvar.frame.repaint();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Set to cross-platform (Metal) look and feel
    public void setCrossPlatformLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(Gvar.frame);
            databus.write_to_Line("./src/main/java/data.anb_syst", "1", 1);
            Gvar.frame.pack();
            Gvar.frame.repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Set to Nimbus look and feel
    public void setNimbusLookAndFeel() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    SwingUtilities.updateComponentTreeUI(Gvar.frame);
                    databus.write_to_Line("./src/main/java/data.anb_syst", "2", 1);
                    Gvar.frame.pack();
                    Gvar.frame.repaint();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Set to Motif look and feel (if available)
    public void setMotifLookAndFeel() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("CDE/Motif".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    SwingUtilities.updateComponentTreeUI(Gvar.frame);
                    databus.write_to_Line("./src/main/java/data.anb_syst", "3", 1);
                    Gvar.frame.pack();
                    Gvar.frame.repaint();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Set to Windows look and feel (if available)
    public void setWindowsLookAndFeel() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    SwingUtilities.updateComponentTreeUI(Gvar.frame);
                    databus.write_to_Line("./src/main/java/data.anb_syst", "4", 1);
                    Gvar.frame.pack();
                    Gvar.frame.repaint();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Utility: Print all available look and feels
    public void listAvailableLookAndFeels() {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            System.out.println(info.getName() + " - " + info.getClassName());
        }
    }

    // elözőleg kiválasztott betöltése
    public void loadprevious() {
        Gvar.lookandfeelnum = Integer.valueOf(databus.ReadLine("./src/main/java/data.anb_syst", 1));
        if (Gvar.lookandfeelnum == 0) {
            setSystemLookAndFeel();
        }
        if (Gvar.lookandfeelnum == 1) {
            setCrossPlatformLookAndFeel();
            ;
        }
        if (Gvar.lookandfeelnum == 2) {
            setNimbusLookAndFeel();
        }
        if (Gvar.lookandfeelnum == 3) {
            setMotifLookAndFeel();
        }
        if (Gvar.lookandfeelnum == 4) {
            setWindowsLookAndFeel();
        }

    }

}