import javax.swing.*;
import java.awt.*;

public class ConnectDialog extends JDialog {

    private JTextField ipField;
    private JTextField portField;
    private boolean confirmed = false;

    public ConnectDialog(Frame parent) {
        super(parent, "Connect to Device", true);
        initUI();
    }

    private void initUI() {
        ipField = new JTextField(15);
        portField = new JTextField(6);

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0;
        form.add(new JLabel("IP Address:"), gbc);

        gbc.gridx = 1;
        form.add(ipField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        form.add(new JLabel("Port:"), gbc);

        gbc.gridx = 1;
        form.add(portField, gbc);

        JButton connectBtn = new JButton("Connect");
        JButton cancelBtn = new JButton("Cancel");

        connectBtn.addActionListener(e -> {
            confirmed = true;
            dispose();
        });

        cancelBtn.addActionListener(e -> {
            confirmed = false;
            dispose();
        });

        JPanel buttons = new JPanel();
        buttons.add(connectBtn);
        buttons.add(cancelBtn);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(form, BorderLayout.CENTER);
        getContentPane().add(buttons, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(getParent());
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public String getIp() {
        return ipField.getText().trim();
    }

    public int getPort() {
        try {
            return Integer.parseInt(portField.getText().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}