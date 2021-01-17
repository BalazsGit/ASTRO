package astro;

import com.formdev.flatlaf.icons.FlatTabbedPaneCloseIcon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApplicationsTabHeader {

    public JPanel headerPanel;
    public JLabel xButton;

    public ApplicationsTabHeader(){

        FlatTabbedPaneCloseIcon flatTabbedPaneCloseIcon = new FlatTabbedPaneCloseIcon();
        xButton.setIcon(flatTabbedPaneCloseIcon);

    }

}
