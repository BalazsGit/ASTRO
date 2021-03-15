package astro.astro.mainframe;

import javax.swing.*;

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
    //public JTextField urlField;
    public JButton start_scavenger;
    public JButton start_turboplotter;
    public JButton start_burst;
    public JComboBox themeBox;
    public JPanel textEditor1;
    public JTabbedPane tabbedTextEditor;
    public JTabbedPane tabbedBrowserPanel;
    public JTabbedPane tabbedApplications;
    public JLabel isDarkThemeLabel;
    public JTabbedPane tabbedSettings;
    private JPanel mainCardPanel;
    private JPanel topPanel;
    private JPanel centerPanel;
    private JTabbedPane tabbedPane;
    private JPanel browserPanel;
    private JPanel applicationsPanel;
    private JPanel textEditorPanel;
    private JPanel settingsPanel;
    private JPanel BRSAPIPanel;
    private JPanel BRSAPI;
    private JPanel bottomPanel;
    private JPanel rightPanel;
    private JPanel loadingPagePanel;
    private JPanel lockScreenPanel;
    //public Boolean isDarkTheme = true;

    //public CefBrowser browser;


    //public ArrayList browserList = new ArrayList<CefBrowser>();

    //public boolean browserFocus_ = true;

    public MainFrame(String title) {

        this.add(mainCardPanel);
        mainCardPanel.add(mainPanel);
        mainCardPanel.add(loadingPagePanel);
        mainCardPanel.add(lockScreenPanel);

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
