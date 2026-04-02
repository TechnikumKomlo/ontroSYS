import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class AdminPanel extends JPanel {

    private JComboBox<String> tablaValaszto;
    private JTable tabla;
    private DefaultTableModel model;

    private String host;
    private String port;
    private String adatbazis;
    private String felhasznalo;
    private String jelszo;

    public AdminPanel() {
        kapcsolatAdatokBekerese();
        initGUI();
        tablaNevekBetoltese();
    }

    private void kapcsolatAdatokBekerese() {
        host = JOptionPane.showInputDialog("Adatbazis host (pl. localhost):");
        port = JOptionPane.showInputDialog("Port (pl. 3306):");
        adatbazis = JOptionPane.showInputDialog("Adatbazis neve:");
        felhasznalo = JOptionPane.showInputDialog("Felhasznalonev:");
        jelszo = JOptionPane.showInputDialog("Jelszo:");
    }

    private void initGUI() {
        setLayout(new BorderLayout());

        tablaValaszto = new JComboBox<>();
        tabla = new JTable();
        model = new DefaultTableModel();
        tabla.setModel(model);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JPanel felsoPanel = new JPanel();
        felsoPanel.add(new JLabel("Tablak:"));
        felsoPanel.add(tablaValaszto);

        JButton hozzaadBtn = new JButton("Hozzaadas");
        JButton torlesBtn = new JButton("Torles");
        JButton modositasBtn = new JButton("Modositas");

        felsoPanel.add(hozzaadBtn);
        felsoPanel.add(torlesBtn);
        felsoPanel.add(modositasBtn);

        add(felsoPanel, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        tablaValaszto.addActionListener(e -> tablaBetoltese());
        hozzaadBtn.addActionListener(e -> ujSor());
        torlesBtn.addActionListener(e -> sorTorles());
        modositasBtn.addActionListener(e -> sorModositas());
    }

    private Connection kapcsolat() throws Exception {
    String url = "jdbc:mysql://" + host + ":" + port + "/" + adatbazis
            + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    return DriverManager.getConnection(url, felhasznalo, jelszo);
    }

    private void tablaNevekBetoltese() {
        try (Connection conn = kapcsolat()) {
            ResultSet rs = conn.createStatement().executeQuery("SHOW TABLES");
while (rs.next()) {
    tablaValaszto.addItem(rs.getString(1));
}

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Hiba: " + e.getMessage());
        }
    }

    private void tablaBetoltese() {
        String tablaNev = (String) tablaValaszto.getSelectedItem();
        if (tablaNev == null) return;

        model.setRowCount(0);
        model.setColumnCount(0);

        try (Connection conn = kapcsolat()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tablaNev);

            ResultSetMetaData meta = rs.getMetaData();
            int oszlopok = meta.getColumnCount();

            for (int i = 1; i <= oszlopok; i++) {
                model.addColumn(meta.getColumnName(i));
            }

            while (rs.next()) {
                Object[] sor = new Object[oszlopok];
                for (int i = 1; i <= oszlopok; i++) {
                    sor[i - 1] = rs.getObject(i);
                }
                model.addRow(sor);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Hiba: " + e.getMessage());
        }
    }

    private void ujSor() {
        String tablaNev = (String) tablaValaszto.getSelectedItem();
        if (tablaNev == null) return;

        int oszlopok = model.getColumnCount();
        Object[] ertekek = new Object[oszlopok];

        for (int i = 0; i < oszlopok; i++) {
            ertekek[i] = JOptionPane.showInputDialog("Add meg: " + model.getColumnName(i));
        }

        try (Connection conn = kapcsolat()) {
            StringBuilder sql = new StringBuilder("INSERT INTO " + tablaNev + " VALUES(");
            for (int i = 0; i < oszlopok; i++) {
                sql.append("?");
                if (i < oszlopok - 1) sql.append(",");
            }
            sql.append(")");

            PreparedStatement stmt = conn.prepareStatement(sql.toString());

            for (int i = 0; i < oszlopok; i++) {
                stmt.setObject(i + 1, ertekek[i]);
            }

            stmt.executeUpdate();
            tablaBetoltese();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Hiba: " + e.getMessage());
        }
    }

    private void sorTorles() {
        int sor = tabla.getSelectedRow();
        if (sor == -1) return;

        String tablaNev = (String) tablaValaszto.getSelectedItem();
        Object id = model.getValueAt(sor, 0);

        try (Connection conn = kapcsolat()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "DELETE FROM " + tablaNev + " WHERE id = ?"
            );
            stmt.setObject(1, id);
            stmt.executeUpdate();
            tablaBetoltese();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Hiba: " + e.getMessage());
        }
    }

    private void sorModositas() {
        int sor = tabla.getSelectedRow();
        if (sor == -1) return;

        String tablaNev = (String) tablaValaszto.getSelectedItem();
        int oszlopok = model.getColumnCount();

        Object id = model.getValueAt(sor, 0);

        try (Connection conn = kapcsolat()) {

            StringBuilder sql = new StringBuilder("UPDATE " + tablaNev + " SET ");

            for (int i = 1; i < oszlopok; i++) {
                sql.append(model.getColumnName(i)).append(" = ?");
                if (i < oszlopok - 1) sql.append(", ");
            }

            sql.append(" WHERE id = ?");

            PreparedStatement stmt = conn.prepareStatement(sql.toString());

            for (int i = 1; i < oszlopok; i++) {
                Object ujErtek = JOptionPane.showInputDialog(
                        "Uj ertek (" + model.getColumnName(i) + "):",
                        model.getValueAt(sor, i)
                );
                stmt.setObject(i, ujErtek);
            }

            stmt.setObject(oszlopok, id);

            stmt.executeUpdate();
            tablaBetoltese();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Hiba: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Univerzalis Admin Felulet");
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new AdminPanel());
        frame.setVisible(true);
    }
}
