package astro.astro.texteditor;

import astro.astro.mainframe.MainFrame;
import astro.astro.config.Props;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import static astro.astro.Main.propertyService;

public class TextEditor extends JFrame{

    public JPanel textEditorPanel;
    public JPanel menuBarPanel;


    private DefaultMutableTreeNode root;
    private DefaultTreeModel treeModel;


    private JTabbedPane folderPanel;
    private JScrollPane JScrollFolderPaneA;
    private JScrollPane JScrollFolderPaneB;
    private JPanel folderPanelA;
    private JPanel folderPanelB;
    private JPanel textPanel;
    public JPanel textPanelA;
    public JPanel textPanelB;
    private JTextPane textPaneB;
    private JPanel menuPanel;

    private JLabel showHideBrowserPanel;
    private JLabel singleDoubleView;
    public JSplitPane JSplitFolderTextPanel;
    public JSplitPane JSplitTextPanel;

    public Component textEditorTab;
    public MainFrame mainFrame;

    public int index = -1;
    public int tableCount = 0;

    private Boolean textEditorDoubleSideView;

    public void setJSplitPanelDividers() {
        JSplitFolderTextPanel.setDividerLocation(0.25);
        JSplitTextPanel.setDividerLocation(0.5);
    }

    public void setTextEditorDoubleSideView(Boolean view) {
        // true - double side view
        // false - single side view

        if(view) {
            textPanelB.setVisible(true);
            folderPanel.add(JScrollFolderPaneB);
            folderPanelB.setVisible(true);
            folderPanel.setTitleAt(1,"Browser B");
        }
        else {
            textPanelB.setVisible(false);
            folderPanel.remove(JScrollFolderPaneB);
        }
        setJSplitPanelDividers();
        textEditorDoubleSideView = view;
    }

        public TextEditor(MainFrame mainFrame, TextEditorTabHeader header) {

            this.add(textEditorPanel);
            this.pack();

            textEditorDoubleSideView = propertyService.getBoolean(Props.textEditorDoubleSideView);
            setTextEditorDoubleSideView(textEditorDoubleSideView);

            TextPanelContent textPanelContentA = new TextPanelContent();
            TextPanelContent textPanelContentB = new TextPanelContent();

            textPanelA.add(textPanelContentA.textPanelContent);
            textPanelB.add(textPanelContentB.textPanelContent);

            FolderPanelContent folderPanelContentA = new FolderPanelContent(folderPanelA, textPanelContentA);
            FolderPanelContent folderPanelContentB = new FolderPanelContent(folderPanelB, textPanelContentB);

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
                    setTextEditorDoubleSideView(!textEditorDoubleSideView);
                }
            });

        }
}
