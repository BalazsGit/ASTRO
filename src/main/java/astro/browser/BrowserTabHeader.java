package astro.browser;

import com.formdev.flatlaf.icons.FlatTabbedPaneCloseIcon;

import javax.swing.*;

public class BrowserTabHeader extends JFrame {
    public JLabel jLabel;
    public JPanel headerPanel;
    public JLabel xButton;


    public BrowserTabHeader() {

        //this.add(headerPanel);
        headerPanel.setVisible(true);
        jLabel.setText("Loading ...");
        FlatTabbedPaneCloseIcon flatTabbedPaneCloseIcon = new FlatTabbedPaneCloseIcon();
        xButton.setIcon(flatTabbedPaneCloseIcon);

    }

}
