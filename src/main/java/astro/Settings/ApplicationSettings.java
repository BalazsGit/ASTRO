package astro.Settings;

import astro.Application;
import com.formdev.flatlaf.icons.FlatCapsLockIcon;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.*;
import java.io.File;

public class ApplicationSettings extends JFrame {
    public JPanel applicationSettings;
    private JTextField applicationRelativePath;
    private JTextField applicationAbsolutePath;
    private JTextField applicationImageRelativePath;
    private JTextField applicationImageAbsolutePath;
    private JPanel applicationPath;
    private JPanel applicationImage;
    private JLabel browseApplication;
    private JLabel browseImage;
    private JComboBox applicationTypeBox;
    public Application application;

    void repaintSettingsPane() {

        this.repaint();

    }

    public ApplicationSettings(Application application) {

        this.application = application;

        //UIManager.getInsets("PopupMenu.borderInsets"), UIManager.getColor("PopupMenu.borderColor"));

        FlatCapsLockIcon flatCapsLockIcon = new FlatCapsLockIcon();

        browseApplication.setIcon(flatCapsLockIcon);
        browseImage.setIcon(flatCapsLockIcon);

        //applicationType = 0; //0 = Undefined | 1 = Graphical | 2 = Console | 3 = Text | 4 = Web
        applicationTypeBox.addItem("Undefined");
        applicationTypeBox.addItem("Graphical");
        applicationTypeBox.addItem("Console");
        applicationTypeBox.addItem("Text");
        applicationTypeBox.addItem("Web");

        applicationTypeBox.setSelectedIndex(application.getApplicationType());
        applicationTypeBox.updateUI();

        //init application values
        applicationRelativePath.setText(application.applicationRelativePath);
        applicationAbsolutePath.setText(application.applicationAbsolutePath);

        applicationImageRelativePath.setText(application.applicationImageRelativePath);
        applicationImageAbsolutePath.setText(application.applicationImageAbsolutePath);

        browseApplication.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                // Create an object of JFileChooser class
                JFileChooser j = new javax.swing.JFileChooser("./");


                // Invoke the showsOpenDialog function to show the save dialog
                int r = j.showOpenDialog(null);

                // If the user selects a file
                if (r == javax.swing.JFileChooser.APPROVE_OPTION) {
                    // Set the label to the path of the selected directory
                    File fi = new File(j.getSelectedFile().getAbsolutePath());

                    application.applicationAbsolutePath = fi.getAbsolutePath();
                    applicationAbsolutePath.setText(fi.getAbsolutePath());

                    String path = fi.getAbsolutePath();
                    String base = "./";
                    String relative = new File(base).toURI().relativize(new File(path).toURI()).getPath();

                    application.applicationRelativePath = relative;
                    applicationRelativePath.setText(relative);


                }
            }
        });

        browseImage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                // Create an object of JFileChooser class
                JFileChooser j = new javax.swing.JFileChooser("./");


                // Invoke the showsOpenDialog function to show the save dialog
                int r = j.showOpenDialog(null);

                // If the user selects a file
                if (r == javax.swing.JFileChooser.APPROVE_OPTION) {
                    // Set the label to the path of the selected directory
                    File fi = new File(j.getSelectedFile().getAbsolutePath());

                    application.applicationImageAbsolutePath = fi.getAbsolutePath();
                    applicationImageAbsolutePath.setText(fi.getAbsolutePath());

                    String path = fi.getAbsolutePath();
                    String base = "./";
                    String relative = new File(base).toURI().relativize(new File(path).toURI()).getPath();

                    application.applicationImageRelativePath = relative;
                    applicationImageRelativePath.setText(relative);

                }


            }
        });
        /*
        applicationSettings.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }
        });
    */

        //delete later only test purposes
        applicationRelativePath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                application.applicationRelativePath = applicationRelativePath.getText();
            }
        });


        applicationTypeBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                application.setApplicationType(applicationTypeBox.getSelectedIndex());
            }
        });
    }
}
