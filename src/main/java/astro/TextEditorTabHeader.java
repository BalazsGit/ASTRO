package astro;

import javax.swing.*;

public class TextEditorTabHeader extends JFrame{

    public JButton xButton;
    public JPanel headerPanel;
    public JLabel jLabel;

    TextEditorTabHeader(String title) {

        this.add(headerPanel);
        headerPanel.setVisible(true);
        jLabel.setText(title);
        xButton.setText("X");

    }
}
