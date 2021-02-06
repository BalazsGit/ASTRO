package astro.Settings;

import astro.Application;
import com.formdev.flatlaf.icons.FlatCapsLockIcon;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class ApplicationSettings extends JFrame{
    public JPanel applicationSettings;
    private JTextField applicationRelativePath;
    private JTextField applicationAbsolutePath;
    private JTextField applicationImageRelativePath;
    private JTextField applicationImageAbsolutePath;
    private JPanel applicationPath;
    private JPanel applicationImage;
    private JLabel browseApplication;
    private JLabel browseImage;
    public Application application;

    void repaintSettingsPane(){

        this.repaint();

    }

    public ApplicationSettings(Application application){

        this.application = application;

        //UIManager.getInsets("PopupMenu.borderInsets"), UIManager.getColor("PopupMenu.borderColor"));

        FlatCapsLockIcon flatCapsLockIcon = new FlatCapsLockIcon();

        browseApplication.setIcon(flatCapsLockIcon);
        browseImage.setIcon(flatCapsLockIcon);

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

    }


}
