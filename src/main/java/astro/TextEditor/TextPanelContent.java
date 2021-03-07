package astro.TextEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.*;

//import static astro.Main.browserFocus_;

public class TextPanelContent extends JFrame{
    public JPanel textPanelContent;
    public JPanel MenuBarPanel;
    public JMenuBar menuBar;
    public JMenu fileMenu;
    public JMenuItem newMenuItem;
    public JMenuItem openMenuItem;
    public JMenuItem saveMenuItem;
    public JMenuItem printMenuItem;
    public JMenu editMenu;
    public JMenuItem cutMenuItem;
    public JMenuItem copyMenuItem;
    public JMenuItem pasteMenuItem;
    public JMenu closeMenu;
    public JMenuItem closeMenuItem;
    public JScrollPane JScrollTextPane;
    public JTextPane textPane;
    public JPanel pathPanel;
    public JTextField relativePathField;
    public JLabel absolutePath;
    public JLabel relativePath;
    public JButton copyAbsolutePath;
    public JButton copyRelativePath;
    public JTextField absolutePathField;
    public JButton cutAbsolutePath;
    public JButton pasteAbsolutePath;
    public JButton cutRelativePath;
    public JButton pasteRelativePath;
    public JButton clearAbsolutePath;
    public JButton clearRelativePath;

    public TextPanelContent(){

        this.add(textPanelContent);
/*
        textPane.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);

                if (!browserFocus_) return;
                browserFocus_ = false;
                KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();

                textPane.grabFocus();
                textPane.requestFocus();
            }
        });

        absolutePathField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);

                if (!browserFocus_) return;
                browserFocus_ = false;
                KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();

                textPane.grabFocus();
                textPane.requestFocus();
            }
        });

        relativePathField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);

                if (!browserFocus_) return;
                browserFocus_ = false;
                KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();

                textPane.grabFocus();
                textPane.requestFocus();
            }
        });
*/
        // File Menu
        newMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                textPane.setText("");

            }
        });

        saveMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Create an object of JFileChooser class
                JFileChooser j = new JFileChooser("./");

                // Invoke the showsSaveDialog function to show the save dialog
                int r = j.showSaveDialog(null);

                if (r == JFileChooser.APPROVE_OPTION) {

                    // Set the label to the path of the selected directory
                    File fi = new File(j.getSelectedFile().getAbsolutePath());

                    try {
                        // Create a file writer
                        FileWriter wr = new FileWriter(fi, false);

                        // Create buffered writer to write
                        BufferedWriter w = new BufferedWriter(wr);

                        // Write
                        w.write(textPane.getText());

                        w.flush();
                        w.close();
                    }
                    catch (Exception evt) {
                        JOptionPane.showMessageDialog(textPane, evt.getMessage());
                    }
                }
                // If the user cancelled the operation
                else {
                    JOptionPane.showMessageDialog(textPane, "The user cancelled the operation!");
                }
            }
        });

        printMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    // print the file
                    textPane.print();
                }
                catch (Exception evt) {
                    JOptionPane.showMessageDialog(textPane, evt.getMessage());
                }

            }
        });

        // Edit Menu
        cutMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                textPane.cut();

            }
        });

        copyMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                textPane.copy();

            }
        });

        pasteMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                textPane.paste();

            }
        });

        // Close Menu
        closeMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                closeMenu.setVisible(false);

            }
        });

        cutAbsolutePath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                absolutePathField.selectAll();
                absolutePathField.cut();

            }
        });

        copyAbsolutePath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                absolutePathField.selectAll();
                absolutePathField.copy();

            }
        });

        pasteAbsolutePath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                absolutePathField.selectAll();
                absolutePathField.paste();

            }
        });

        clearAbsolutePath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                absolutePathField.setText("");

            }
        });

        cutRelativePath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                relativePathField.selectAll();
                relativePathField.cut();

            }
        });

        copyRelativePath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                relativePathField.selectAll();
                relativePathField.copy();

            }
        });

        pasteRelativePath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                relativePathField.selectAll();
                relativePathField.paste();

            }
        });

        clearRelativePath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                relativePathField.setText("");

            }
        });

    }

}
