package astro.astro.texteditor;

import com.formdev.flatlaf.icons.FlatTabbedPaneCloseIcon;

import javax.swing.*;

public class TextEditorTabHeader extends JFrame{

    public JLabel xButton;
    public JPanel headerPanel;
    public JLabel jLabel;

    public TextEditorTabHeader() {

        this.add(headerPanel);
        headerPanel.setVisible(true);
        jLabel.setText("Text Editor");
        FlatTabbedPaneCloseIcon flatTabbedPaneCloseIcon = new FlatTabbedPaneCloseIcon();
        xButton.setIcon(flatTabbedPaneCloseIcon);

    }
}
