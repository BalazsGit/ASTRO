package astro;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BrowserTabHeader extends JFrame {
    public JLabel jLabel;
    public JPanel headerPanel;
    public JButton xButton;


    BrowserTabHeader(String title) {

        this.add(headerPanel);
        headerPanel.setVisible(true);
        jLabel.setText(title);
        xButton.setText("X");

    }

}
