import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class imageviewer {
    public JFrame frame = new JFrame("Image Viewer");
    public int x;
    public int y;
    public ImageIcon backgroundImage;
    public JLabel backgroundLabel;
    public int height = 1000;
    public int width = 600;
    public int imgscale = 0;

    public void huzogato() {
        MouseAdapter mouseAdapter = new MouseAdapter() {
            private Point initialClick;

            @Override
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
                SwingUtilities.convertPointToScreen(initialClick, frame);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                Point current = e.getPoint();
                SwingUtilities.convertPointToScreen(current, frame);
                int xMoved = current.x - initialClick.x;
                int yMoved = current.y - initialClick.y;
                x += xMoved;
                y += yMoved;

                initialClick = current;
            }

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                imgscale += e.getWheelRotation() * 20;
                System.out.println(imgscale);
            }

        };

        frame.addMouseListener(mouseAdapter);
        frame.addMouseMotionListener(mouseAdapter);
        frame.addMouseWheelListener(mouseAdapter);
    }

    public void program(String path) {
        frame.setSize(1000, 800);
        frame.setVisible(true);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setLayout(null);
        backgroundImage = new ImageIcon(path);
        backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(x, y, frame.getWidth(), frame.getHeight());
        frame.add(backgroundLabel);
        Image img = backgroundImage.getImage();
        Image scaledImg = img.getScaledInstance(1000, 600, Image.SCALE_SMOOTH);
        Timer timer = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Image scaledImg = img.getScaledInstance(1000 + imgscale, 600 + imgscale, Image.SCALE_FAST);
                backgroundLabel.setIcon(new ImageIcon(scaledImg));
                backgroundLabel.setBounds(x, y, width + imgscale, height + imgscale);
                backgroundLabel.revalidate();
                backgroundLabel.repaint();

            }
        });
        timer.start();
    }

    public static void main(String[] args) {
        imageviewer imageviewer = new imageviewer();
        imageviewer.program("./images/closebutton.png");
        imageviewer.huzogato();
    }

}
