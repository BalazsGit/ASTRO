package astro.texteditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class FolderPanelContent {

    public JFileChooser jFileChooser;
    private JPanel folderPanel;
    private TextPanelContent textPanelContent;

    public void setPath(File file) {
        jFileChooser.setSelectedFile(file);
    }

    private void loadText(File file) {

        // Set the label to the path of the selected directory
        textPanelContent.absolutePathField.setText(file.getAbsolutePath());

        String path = file.getAbsolutePath();
        String base = "./";
        String relative = new File(base).toURI().relativize(new File(path).toURI()).getPath();

        textPanelContent.relativePathField.setText(relative);

        try {
            // String
            String s1 = "", sl = "";

            // File reader
            FileReader fr = new FileReader(file);

            // Buffered reader
            BufferedReader br = new BufferedReader(fr);

            // Initialize sl
            sl = br.readLine();

            // Take the input from the file
            while ((s1 = br.readLine()) != null) {
                sl = sl + "\n" + s1;
            }

            // Set the text
            textPanelContent.textPane.setText(sl);
            jFileChooser.setSelectedFile(file);
        }
        catch (Exception evt) {
            JOptionPane.showMessageDialog(textPanelContent.textPane, evt.getMessage());
        }
    }

    public FolderPanelContent(JPanel folderPanel, TextPanelContent textPanelContent ) {

        this.folderPanel = folderPanel;
        this.textPanelContent = textPanelContent;

        //file browser
        jFileChooser = new JFileChooser("./");
        jFileChooser.setPreferredSize(new Dimension(100, folderPanel.getHeight()));
        jFileChooser.setControlButtonsAreShown(false);
        jFileChooser.setDragEnabled(true);
        folderPanel.add(jFileChooser);
        jFileChooser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals(JFileChooser.APPROVE_SELECTION)) {
                    File file = new File(jFileChooser.getSelectedFile().getAbsolutePath());
                    loadText(file);
                }
                // If the user cancelled the operation
                else {
                }
            }

        });
        //end file browser

        //newMenuItemA.addActionListener(this);
        textPanelContent.openMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Create an object of JFileChooser class
                JFileChooser jFileChooserPopup = new javax.swing.JFileChooser("./");

                // Invoke the showsOpenDialog function to show the save dialog
                int r = jFileChooserPopup.showOpenDialog(null);

                // If the user selects a file
                if (r == javax.swing.JFileChooser.APPROVE_OPTION) {

                    File file = new File(jFileChooserPopup.getSelectedFile().getAbsolutePath());
                    loadText(file);

                }
                // If the user cancelled the operation
                else {
                }
            }
        });

    }

}
