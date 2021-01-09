package astro;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import org.cef.browser.CefBrowser;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.function.BiConsumer;

public class MainFrame extends JFrame {

    /*
    //not working
    public JFrame frame = new JFrame();

    public JPanel mainPanel = new JPanel();
        public JPanel topPanel = new JPanel();
        public JPanel rightPanel = new JPanel();
        public JPanel leftPanel = new JPanel();
        public JPanel bottomPanel = new JPanel();
        public JPanel centerPanel = new JPanel();
            public JPanel browserPanel = new JPanel();
            public JPanel urlPanel = new JPanel();
                public JTextField urlField = new JTextField("pina3");

     */


    public JFrame frame;

    public JPanel mainPanel;
    public JPanel topPanel;
    public JPanel rightPanel;
    public JPanel bottomPanel;
    public JPanel centerPanel;
    //public JTextField urlField;
    public JButton start_scavenger;
    public JButton start_turboplotter;
    public JButton start_burst;
    public JTabbedPane tabbedPane;
    public JComboBox themeBox;
    public JPanel settingsPanel;
    public JPanel setting;
    public JPanel textEditorPanel;
    public JPanel textEditor1;
    public JTabbedPane tabbedTextEditor;
    private JPanel browserPanel;
    public JTabbedPane tabbedBrowserPanel;
    private JPanel BRSAPIPanel;
    private JPanel BRSAPI;
    private JPanel applicationsPanel;
    public JTabbedPane tabbedApplications;
    public String theme;

    //public CefBrowser browser;


    //public ArrayList browserList = new ArrayList<CefBrowser>();

    //public boolean browserFocus_ = true;

    public MainFrame(String title) {

        this.add(mainPanel);

        //urlField.setText("Loading ...");
        //urlField.setFocusable(true);
        //urlField.setVisible(true);
        //browserTab.setVisible(true);

        this.setTitle(title);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        textEditorPanel.setVisible(true);

        themeBox.addItem("FlatDarkLaf");
        themeBox.addItem("FlatLightLaf");



    }
}
