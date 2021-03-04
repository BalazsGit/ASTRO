package astro;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import static astro.Main.browserFocus_;

public class TextEditor extends JFrame{

    public JPanel textEditorPanel;
    private JTextField absolutePathField;
    private JTextField relativePathField;
    private JLabel absolutePath;
    private JLabel relativePath;
    private JButton copyAbsolutePath;
    private JButton copyRelativePath;
    public JPanel menuBarPanel;
    private JPanel MenuBarPanelA;
    private JPanel pathPanel;
    private JButton cutAbsolutePath;
    private JButton pasteAbsolutePath;
    private JButton cutRelativePath;
    private JButton pasteRelativePath;
    private JButton clearRelativePath;
    private JButton clearAbsolutePath;


    private DefaultMutableTreeNode root;
    private DefaultTreeModel treeModel;

    public JMenuBar menuBarA;
    public JMenu fileMenuA;
    public JMenu editMenuA;
    public JMenu closeMenuA;
    private JMenuItem newMenuItemA;
    private JMenuItem openMenuItemA;
    private JMenuItem saveMenuItemA;
    private JMenuItem printMenuItemA;
    private JMenuItem cutMenuItemA;
    private JMenuItem copyMenuItemA;
    private JMenuItem pasteMenuItemA;
    private JMenuItem closeMenuItemA;


    private JTabbedPane folderPanel;
    private JScrollPane JScrollFolderPaneA;
    private JScrollPane JScrollFolderPaneB;
    private JPanel folderPanelA;
    private JPanel folderPanelB;
    private JPanel textPanel;
    public JPanel textPanelA;
    public JPanel textPanelB;
    private JTextPane textPaneB;
    private JTextPane textPaneA;
    private JScrollPane JScrollTextPaneA;
    private JScrollPane JScrollTextPaneB;
    private JPanel menuPanel;
    private JPanel MenuBarPanelB;

    private JLabel showHideBrowserPanel;
    private JLabel singleDoubleView;
    public JSplitPane JSplitFolderTextPanel;
    public JSplitPane JSplitTextPanel;

    public Component textEditorTab;
    public MainFrame mainFrame;

    public int index = -1;
    public int tableCount = 0;

    public void setJSplitPanelDividers() {
        JSplitFolderTextPanel.setDividerLocation(0.25);
        JSplitTextPanel.setDividerLocation(0.5);
    }

        public TextEditor(MainFrame mainFrame, TextEditorTabHeader header) {

            this.add(textEditorPanel);
            this.pack();

            textPaneA.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    super.focusGained(e);

                    if (!browserFocus_) return;
                    browserFocus_ = false;
                    KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();

                    textPaneA.grabFocus();
                    textPaneA.requestFocus();
                }
            });

            //file browser
            final JFileChooser jFileChooser = new JFileChooser("./");
            //jFileChooser.setPreferredSize(new Dimension(100, getHeight()));
            jFileChooser.setControlButtonsAreShown(false);
            jFileChooser.setDragEnabled(true);
            folderPanelA.add(jFileChooser);
            jFileChooser.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (e.getActionCommand().equals(JFileChooser.APPROVE_SELECTION)) {
                            // Set the label to the path of the selected directory
                            File file = new File(jFileChooser.getSelectedFile().getAbsolutePath());

                            absolutePathField.setText(file.getAbsolutePath());

                            String path = file.getAbsolutePath();
                            String base = "./";
                            String relative = new File(base).toURI().relativize(new File(path).toURI()).getPath();

                            relativePathField.setText(relative);

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
                                textPaneA.setText(sl);
                            }
                            catch (Exception evt) {
                                JOptionPane.showMessageDialog(textPaneA, evt.getMessage());
                            }
                        }
                        // If the user cancelled the operation
                        else {
                        }
                    }
            });
            //end file browser

            absolutePathField.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    super.focusGained(e);

                    if (!browserFocus_) return;
                    browserFocus_ = false;
                    KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();

                    textPaneA.grabFocus();
                    textPaneA.requestFocus();
                }
            });

            relativePathField.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    super.focusGained(e);

                    if (!browserFocus_) return;
                    browserFocus_ = false;
                    KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();

                    textPaneA.grabFocus();
                    textPaneA.requestFocus();
                }
            });

            // File Menu
            newMenuItemA.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    textPaneA.setText("");

                }
            });

            //newMenuItemA.addActionListener(this);
            openMenuItemA.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    // Create an object of JFileChooser class
                    JFileChooser jFileChooser = new javax.swing.JFileChooser("./");

                    // Invoke the showsOpenDialog function to show the save dialog
                    int r = jFileChooser.showOpenDialog(null);

                    // If the user selects a file
                    if (r == javax.swing.JFileChooser.APPROVE_OPTION) {
                        // Set the label to the path of the selected directory
                        File file = new File(jFileChooser.getSelectedFile().getAbsolutePath());

                        absolutePathField.setText(file.getAbsolutePath());

                        String path = file.getAbsolutePath();
                        String base = "./";
                        String relative = new File(base).toURI().relativize(new File(path).toURI()).getPath();

                        relativePathField.setText(relative);

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
                            textPaneA.setText(sl);
                        }
                        catch (Exception evt) {
                            JOptionPane.showMessageDialog(textPaneA, evt.getMessage());
                        }
                    }
                    // If the user cancelled the operation
                    else {
                    }
                }
            });

            saveMenuItemA.addActionListener(new ActionListener() {
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
                            w.write(textPaneA.getText());

                            w.flush();
                            w.close();
                        }
                        catch (Exception evt) {
                            JOptionPane.showMessageDialog(textPaneA, evt.getMessage());
                        }
                    }
                   // If the user cancelled the operation
                    else {
                        JOptionPane.showMessageDialog(textPaneA, "The user cancelled the operation!");
                    }
                }
            });

            printMenuItemA.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    try {
                        // print the file
                        textPaneA.print();
                    }
                    catch (Exception evt) {
                        JOptionPane.showMessageDialog(textPaneA, evt.getMessage());
                    }

                }
            });

            // Edit Menu
            cutMenuItemA.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    textPaneA.cut();

                }
            });

            copyMenuItemA.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    textPaneA.copy();

                }
            });

            pasteMenuItemA.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    textPaneA.paste();

                }
            });

            // Close Menu
            closeMenuItemA.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    closeMenuA.setVisible(false);

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
            showHideBrowserPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    super.mousePressed(e);
                    if(folderPanel.isVisible()) {
                        folderPanel.setVisible(false);
                        JSplitFolderTextPanel.setDividerLocation(0);
                    }
                    else {
                        folderPanel.setVisible(true);
                    }
                    setJSplitPanelDividers();

                }
            });

            singleDoubleView.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    super.mousePressed(e);
                    if(textPanelB.isVisible()) {
                        textPanelB.setVisible(false);
                        folderPanel.remove(JScrollFolderPaneB);
                    }
                    else {
                        textPanelB.setVisible(true);
                        folderPanel.add(JScrollFolderPaneB);
                        folderPanelB.setVisible(true);
                        folderPanel.setTitleAt(1,"Browser B");
                    }
                    setJSplitPanelDividers();

                }
            });

        }
}
