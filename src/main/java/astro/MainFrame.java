package astro;

import config.Props;

import javax.swing.*;
import java.awt.event.*;

import static astro.Main.propertyService;

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
    public JPanel textEditorPanel;
    public JPanel textEditor1;
    public JTabbedPane tabbedTextEditor;
    private JPanel browserPanel;
    public JTabbedPane tabbedBrowserPanel;
    private JPanel BRSAPIPanel;
    private JPanel BRSAPI;
    private JPanel applicationsPanel;
    public JTabbedPane tabbedApplications;
    public JLabel isDarkThemeLabel;
    public JTabbedPane tabbedSettings;
    //public Boolean isDarkTheme = true;

    //public CefBrowser browser;


    //public ArrayList browserList = new ArrayList<CefBrowser>();

    //public boolean browserFocus_ = true;

    public MainFrame(String title) {

        //this.add(mainPanel);

        //urlField.setText("Loading ...");
        //urlField.setFocusable(true);
        //urlField.setVisible(true);
        //browserTab.setVisible(true);

        this.setTitle(title);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
/*
        themeBox.addItem("FlatDarkLaf");
        themeBox.addItem("FlatLightLaf");
        themeBox.addItem("ARC - Orange");

        isDarkTheme = propertyService.getBoolean(Props.isDarkTheme);

        if(isDarkTheme) {
            //later set icon
            isDarkThemeLabel.setText("DarkTheme");
        }
        else{
            //later set icon
            isDarkThemeLabel.setText("LightTheme");
        }

        isDarkThemeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                if (isDarkTheme){
                    isDarkTheme = false;
                    propertyService.setProperty("isDarkTheme", "true");
                    isDarkThemeLabel.setText("DarkTheme");
                    UIManager.setLookAndFeel(darkThemesHashMap.get(propertyService.getString(Props.DarkTheme)).getClassName());
                }
                else{
                    isDarkTheme = true;
                    propertyService.setProperty("isDarkTheme", "false");
                    isDarkThemeLabel.setText("LightTheme");
                    UIManager.setLookAndFeel(darkThemesHashMap.get(propertyService.getString(Props.DarkTheme)).getClassName());
                }
                SwingUtilities.updateComponentTreeUI(mainPanel);

            }
        });
        */
    }
}
