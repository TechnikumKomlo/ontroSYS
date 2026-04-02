import org.cef.CefApp;
import org.cef.CefClient;
import org.cef.CefSettings;
import org.cef.browser.CefBrowser;
import org.cef.handler.CefFocusHandler;
import org.cef.handler.CefFocusHandler.FocusSource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OntroSYS_WEBTEST {

    private static JButton back = new JButton("<");
    private static JButton forward = new JButton(">");
    private static JTextField url = new JTextField();
    private static JPanel menubar = new JPanel();
    public static JPanel frame = new JPanel();
    private static volatile boolean allowBrowserFocus = false;

    public static void webengine() {
        
        CefApp.startup(null);

        CefSettings settings = new CefSettings();
        settings.windowless_rendering_enabled = true;

        CefApp app = CefApp.getInstance(settings);
        CefClient client = app.createClient();

        client.addFocusHandler(new CefFocusHandler() {
            @Override
            public boolean onSetFocus(CefBrowser browser, FocusSource source) {
                return !allowBrowserFocus;
            }

            @Override
            public void onTakeFocus(CefBrowser browser, boolean next) {
                allowBrowserFocus = false;
                SwingUtilities.invokeLater(() -> url.requestFocusInWindow());
            }

            @Override
            public void onGotFocus(CefBrowser browser) {
            }
        });

        SwingUtilities.invokeLater(() -> {
            frame.setLayout(new GridBagLayout());

            
            GridBagConstraints gbcMenu = new GridBagConstraints();
            gbcMenu.gridx = 0;
            gbcMenu.gridy = 0;
            gbcMenu.weightx = 1.0;
            gbcMenu.weighty = 0.0;
            gbcMenu.fill = GridBagConstraints.BOTH;
            frame.add(menubar, gbcMenu);

            JPanel browserPanel = new JPanel(new BorderLayout());
            GridBagConstraints gbcBrowser = new GridBagConstraints();
            gbcBrowser.gridx = 0;
            gbcBrowser.gridy = 1;
            gbcBrowser.weightx = 1.0;
            gbcBrowser.weighty = 1.0;
            gbcBrowser.fill = GridBagConstraints.BOTH;
            frame.add(browserPanel, gbcBrowser);
            frame.setVisible(true);

            CefBrowser browser = client.createBrowser("https://www.google.com", true, false);
            Component browserUI = browser.getUIComponent();
            browserUI.setFocusable(true);

            browserUI.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    allowBrowserFocus = true;
                    browser.setFocus(true);
                    browserUI.requestFocusInWindow();
                }
            });
            browserUI.addMouseWheelListener(e -> {
                e.consume();
                int scrollAmount = e.getWheelRotation() * 60; // Adjusted for better feel
                int x = e.getX();
                int y = e.getY();

                // JS Script to find the specific element under the mouse and scroll it
                String script = "(function() {" +
                        "  var x = " + x + ";" +
                        "  var y = " + y + ";" +
                        "  var amount = " + scrollAmount + ";" +
                        "  var el = document.elementFromPoint(x, y);" +
                        "  while (el) {" +
                        "    var styles = window.getComputedStyle(el);" +
                        "    if ((el.scrollHeight > el.clientHeight) && " +
                        "        (styles.overflowY === 'auto' || styles.overflowY === 'scroll')) {" +
                        "      el.scrollBy(0, amount);" +
                        "      return;" +
                        "    }" +
                        "    el = el.parentElement;" +
                        "  }" +
                        "  window.scrollBy(0, amount);" +
                        "})();";
                browser.executeJavaScript(script, "", 0);
            });

            back.addActionListener(e -> {
                if (browser.canGoBack())
                    browser.goBack();
            });
            forward.addActionListener(e -> {
                if (browser.canGoForward())
                    browser.goForward();
            });

            url.addActionListener(e -> {
                String text = url.getText().trim();
                if (!text.isEmpty()) {
                    if (!text.matches("^[a-zA-Z][a-zA-Z0-9+.-]*:.*"))
                        text = "http://" + text;
                    browser.loadURL(text);
                }
            });

            browserPanel.add(browserUI, BorderLayout.CENTER);
            browserPanel.revalidate();
            browserPanel.repaint();

            SwingUtilities.invokeLater(() -> url.requestFocusInWindow());
        });
    }

    public static void buildTopBar() {
        menubar.setLayout(new GridBagLayout());
        GridBagConstraints c1 = new GridBagConstraints();
        c1.gridx = 0;
        c1.weightx = 0.05;
        c1.fill = GridBagConstraints.BOTH;
        menubar.add(back, c1);

        GridBagConstraints c2 = new GridBagConstraints();
        c2.gridx = 1;
        c2.weightx = 0.05;
        c2.fill = GridBagConstraints.BOTH;
        menubar.add(forward, c2);

        GridBagConstraints c3 = new GridBagConstraints();
        c3.gridx = 2;
        c3.weightx = 1.0;
        c3.fill = GridBagConstraints.BOTH;
        url.setPreferredSize(new Dimension(200, 24));
        menubar.add(url, c3);
    }
}
