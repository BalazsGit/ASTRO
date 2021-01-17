package astro;

import com.formdev.flatlaf.icons.FlatTabbedPaneCloseIcon;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BrowserTabHeader extends JFrame {
    public JLabel jLabel;
    public JPanel headerPanel;
    public JLabel xButton;


    BrowserTabHeader(String title) {

        //this.add(headerPanel);
        headerPanel.setVisible(true);
        jLabel.setText(title);
        FlatTabbedPaneCloseIcon flatTabbedPaneCloseIcon = new FlatTabbedPaneCloseIcon();
        xButton.setIcon(flatTabbedPaneCloseIcon);

    }

}
