import javax.swing.*;

public class lanconnection {

    public void LANconnect() {

        ConnectDialog dialog = new ConnectDialog(Gvar.frame);
        dialog.setVisible(true);

        if (dialog.isConfirmed()) {
            String ip = dialog.getIp();
            int port = dialog.getPort();
            Gvar.ip = ip;
            Gvar.port = port;
            if (port <= 0) {
                JOptionPane.showMessageDialog(Gvar.frame,
                        "Invalid port number",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            
            


        } else {
            System.out.println("User cancelled connection");
        }
    }
}