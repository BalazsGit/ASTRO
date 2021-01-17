package astro;

import org.cef.browser.CefBrowser;
import org.cef.handler.CefFocusHandlerAdapter;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.metal.*;
import javax.swing.text.*;

import static astro.Main.browserFocus_;

public class TextEditor extends JFrame{

    public JPanel textEditorPanel;
    private JTextField absolutePathField;
    private JTextField relativePathField;
    private JTree folderTree;
    private JTextPane textPane;
    private JLabel absolutePath;
    private JLabel relativePath;
    private JButton copyAbsolutePath;
    private JButton copyRelativePath;
    private JPanel MenuBarPanel;
    public JEditorPane editorPanel;
    public JPanel menuBarPanel;

    public JMenuBar menuBar;
    public JMenu fileMenu;
    public JMenu editMenu;
    public JMenu closeMenu;
    private JMenuItem newMenuItem;
    private JMenuItem openMenuItem;
    private JMenuItem saveMenuItem;
    private JMenuItem printMenuItem;
    private JMenuItem cutMenuItem;
    private JMenuItem copyMenuItem;
    private JMenuItem pasteMenuItem;
    private JMenuItem closeMenuItem;
    private JButton cutAbsolutePath;
    private JButton pasteAbsolutePath;
    private JButton cutRelativePath;
    private JButton pasteRelativePath;
    private JButton clearRelativePath;
    private JButton clearAbsolutePath;

    public Component textEditorTab;
    public MainFrame mainFrame;

    public int index = -1;
    public int tableCount = 0;

        public TextEditor(MainFrame mainFrame, TextEditorTabHeader header) {

            this.add(textEditorPanel);

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

            // File Menu
            newMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    textPane.setText("");

                }
            });

            //newMenuItem.addActionListener(this);
            openMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    // Create an object of JFileChooser class
                    JFileChooser j = new javax.swing.JFileChooser("./");

                    // Invoke the showsOpenDialog function to show the save dialog
                    int r = j.showOpenDialog(null);

                    // If the user selects a file
                    if (r == javax.swing.JFileChooser.APPROVE_OPTION) {
                        // Set the label to the path of the selected directory
                        File fi = new File(j.getSelectedFile().getAbsolutePath());

                        absolutePathField.setText(fi.getAbsolutePath());

                        String path = fi.getAbsolutePath();
                        String base = "./";
                        String relative = new File(base).toURI().relativize(new File(path).toURI()).getPath();

                        relativePathField.setText(relative);

                        try {
                            // String
                            String s1 = "", sl = "";

                            // File reader
                            FileReader fr = new FileReader(fi);

                            // Buffered reader
                            BufferedReader br = new BufferedReader(fr);

                            // Initilize sl
                            sl = br.readLine();

                            // Take the input from the file
                            while ((s1 = br.readLine()) != null) {
                                sl = sl + "\n" + s1;
                            }

                            // Set the text
                            textPane.setText(sl);
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
                        editorPanel.print();
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

            textEditorPanel.setVisible(true);

            mainFrame.tabbedTextEditor.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {

                    JTabbedPane sourceTabbedPane = (JTabbedPane) e.getSource();

                    if (mainFrame.tabbedTextEditor.getTabCount() == 2) {
                        header.xButton.setVisible(false);
                    } else {
                        header.xButton.setVisible(true);
                    }
                }
            });

            header.xButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {

                    if (mainFrame.tabbedTextEditor.getTabCount() > 2) {

                        mainFrame.tabbedTextEditor.remove(textEditorTab);

                    }

                    index = mainFrame.tabbedTextEditor.getSelectedIndex();
                    tableCount = mainFrame.tabbedTextEditor.getTabCount();

                    //if the last close tab is selected, than give the focus to the one left text editor tab
                    if (index == tableCount - 1) {

                        mainFrame.tabbedTextEditor.setSelectedIndex(index - 1);
                        mainFrame.tabbedTextEditor.getSelectedComponent().requestFocus();

                    }
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
