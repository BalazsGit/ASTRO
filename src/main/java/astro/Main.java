package astro;

import astro.Settings.GUISettings;
import config.PropertyService;
import config.PropertyServiceImpl;
import config.Props;
import org.cef.CefApp;
import org.cef.CefSettings;
import util.FlatTabbedPaneAddIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Timer;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import static javax.swing.JDialog.setDefaultLookAndFeelDecorated;



public class Main {

    static boolean browserFocus_ = true;

    //WALLET/BURST
    public static String  burstDirPath = "PROJECT/APPLICATIONS/WALLET/BURST/";
    //WALLET/PHOENIX
    public static String  phoenixDirPath = "PROJECT/APPLICATIONS/WALLET/PHOENIX/";
    //EXCHANGE/BTDEX
    public static String  btdexDirPath = "PROJECT/APPLICATIONS/EXCHANGE/BTDEX/";
    //MINER/SCAVENGER
    public static String  scavengerDirPath = "PROJECT/APPLICATIONS/MINER/SCAVENGER/";
    //PLOTTER/TURBOPLOTTER
    public static String  turboplotterDirPath = "PROJECT/APPLICATIONS/PLOTTER/TURBOPLOTTER/";
    //PLOTTER/ENGRAVER
    public static String  engraverDirPath = "PROJECT/APPLICATIONS/PLOTTER/ENGRAVER/";
    //IMAGES
    public static String  imagesDirPath = "PROJECT/IMAGES/";
    //ICONS
    public static String iconsDirPath = "PROJECT/ICONS/";
    //CONFIG
    public static String configDirPath = "PROJECT/CONFIG/";

    public static PropertyService propertyService;
    public static String propertiesFileName = "astro.properties";

    //empty play icon
    static String play1 = "\u25B7"; //OK

    //filled play icon
    static String play2 = "\u25B6"; //OK

    //empty stop icon
    static String stop1 = "\u25FB"; //OK

    //filled stop icon
    static String stop2 = "\u25FC"; //OK

    //settings icon ☰
    static String settings1 = "\u2630"; //OK

    //settings icon Ⅲ
    static String settings2 = "\u2162";

    //close icon
    static String close1 = "\u2715"; //OK
    static String close2 = "\u274C"; //OK

    //plus icon
    static String plus1 = "\uFF0B"; //OK
    static String plus2 = "\uFE62"; //same
    static String plus3 = "\u2795"; //OK
    static String plus4 = "\u2795"; //OK very good

    //arrow left "\u276E"
    static String arrowLeft1 = "\u2770"; //OK
    static String arrowLeft2 = "\u2770\u2796"; //OK

    //arrow right "\u276F"
    static String arrowRight1 = "\u2771"; //OK
    static String arrowRight2 = "\u2796\u25B6"; //OK
    static String arrowRight3 = "\u279C"; //OK best
    static String arrowRight4 = "\u2794"; //OK

    //vertical separator line
    public static String separator1 = "\u2758"; //OK


/*
    String s = "\u4E33";
    Font[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
        System.out.("Total fonts: \t" + fonts.length);
    int count = 0;
        for (Font font : fonts) {
        if (font.canDisplayUpTo(s) < 0) {
            count++;
            System.out.println(font.getName());
        }
    }
        System.out.println("Compatible fonts: \t" + count);

*/
    //never be the first tab
    static void createNewBrowserTab(CefApp cefApp, MainFrame mainFrame, String URL){

        BrowserTabHeader header = new BrowserTabHeader();
        BrowserFrame browserFrame = new BrowserFrame(cefApp, mainFrame, header, URL);

        mainFrame.tabbedBrowserPanel.insertTab("Loading ...",null, browserFrame.browserPanel,"tooltip", mainFrame.tabbedBrowserPanel.getTabCount()-1);
        mainFrame.tabbedBrowserPanel.setTabComponentAt(mainFrame.tabbedBrowserPanel.getTabCount()-2, header.headerPanel);
        //the new tab is the selected one
        mainFrame.tabbedBrowserPanel.setSelectedIndex(mainFrame.tabbedBrowserPanel.getTabCount()-2);
        browserFrame.browserTab = mainFrame.tabbedBrowserPanel.getSelectedComponent();

    }

    //never be the first tab
    static void createNewTextEditorTab(MainFrame mainFrame, String URL){

        TextEditorTabHeader header = new TextEditorTabHeader();
        TextEditor textEditor = new TextEditor(mainFrame, header);

        mainFrame.tabbedTextEditor.insertTab("Loading ...",null, textEditor.textEditorPanel,"tooltip", mainFrame.tabbedTextEditor.getTabCount()-1);
        //astro.tabbedBrowserPanel.insertTab("Loading ...",null, browserUI,"tooltip", astro.tabbedBrowserPanel.getTabCount());
        mainFrame.tabbedTextEditor.setTabComponentAt(mainFrame.tabbedTextEditor.getTabCount()-2, header.headerPanel);
        //the new tab is the selected one
        mainFrame.tabbedTextEditor.setSelectedIndex(mainFrame.tabbedTextEditor.getTabCount()-2);
        textEditor.textEditorTab = mainFrame.tabbedTextEditor.getSelectedComponent();

        mainFrame.pack();
        textEditor.setJSplitPanelDividers();
        //mainFrame.pack();
        //JOptionPane.showMessageDialog(textEditor.textEditorPanel, textEditor.textEditorPanel.getWidth() + " " + textEditor.textPanelA.getHeight() + " " + textEditor.textPanelB.getHeight(), "Application Settings", JOptionPane.CLOSED_OPTION);

        //textEditor.JSplitFolderTextPanel.setDividerLocation(0.25);
        //textEditor.JSplitTextPanel.setDividerLocation(0.5);
    }

    //never be the first tab
    static void createNewApplicationsTab(MainFrame mainFrame){

        ApplicationsTabHeader header = new ApplicationsTabHeader();
        ApplicationsFrame applicationsFrame = new ApplicationsFrame(mainFrame, header);

        mainFrame.tabbedApplications.insertTab("Loading ...",null, applicationsFrame.applicationsPanel,"tooltip", mainFrame.tabbedApplications.getTabCount()-1);
        //astro.tabbedBrowserPanel.insertTab("Loading ...",null, browserUI,"tooltip", astro.tabbedBrowserPanel.getTabCount());
        mainFrame.tabbedApplications.setTabComponentAt(mainFrame.tabbedApplications.getTabCount()-2, header.headerPanel);
        //the new tab is the selected one
        mainFrame.tabbedApplications.setSelectedIndex(mainFrame.tabbedApplications.getTabCount()-2);
        //applicationsTab = current applications tab
        applicationsFrame.applicationsTab = mainFrame.tabbedApplications.getSelectedComponent();

    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException {

                propertyService = new PropertyServiceImpl(configDirPath + propertiesFileName);


                Timer timer = new Timer();
                timer.schedule(new Reload(propertyService, configDirPath + propertiesFileName), 0, propertyService.getLong(Props.reload));
            //put to GUI settins later
            //UIManager settings
            //set GUI theme
            //Font defaultFont
            Font defaultFont = UIManager.getDefaults().getFont("defaultFont");

            Font userFont = new Font(propertyService.getString(Props.fontFamily), propertyService.getInt(Props.fontStyle), propertyService.getInt(Props.fontSize));

            //Menu.opaque = false
            //MenuItem.opaque = false

            UIManager.put( "defaultFont", userFont );
            UIManager.put( "TabbedPane.showTabSeparators", true );
            UIManager.put( "TabbedPane.scrollButtonsPolicy", "asNeededSingle" );
            UIManager.put( "TabbedPane.tabsPopupPolicy", "never" );
            UIManager.put( "TabbedPane.tabSelectionHeight", 1 );
            UIManager.put( "Table.showHorizontalLines", true );
            UIManager.put( "Table.showVerticalLines", true );
            UIManager.put( "SplitPaneDivider.gripDotCount", 0 );
            UIManager.put( "List.background", "@background" );
            //UIManager.put( "ToolBar.separatorSize", 1 );

        //not working
        /*
            UIManager.put( "ToolBar.separatorWidth", 20 );
        UIManager.put( "Separator.height", 20 );
        UIManager.put( "Separator.stripeWidth", 20 );
        UIManager.put( "Separator.stripeIndent", 20 );
        UIManager.put( "ToolBar.gripColor", "@foreground" );
        UIManager.put( "ToolBar.separatorColor", "$Separator.foreground" );

         */
        //end not working

        //fast up GUI
        System.setProperty("sun.java2d.noddraw", Boolean.TRUE.toString());
        setDefaultLookAndFeelDecorated(true);

        boolean useOsr = false;


        CefSettings DEFAULT_SETTINGS = new CefSettings();
        DEFAULT_SETTINGS.windowless_rendering_enabled = false;

        CefApp cefApp = CefApp.getInstance(DEFAULT_SETTINGS);
        //for Linux and MacOS
        cefApp.startup(args);

        MainFrame astro = new MainFrame("ASTRO");

        astro.add(astro.mainPanel);
/*
        switch (theme){
            case "FlatDarkLaf":
                astro.themeBox.setSelectedIndex(0);
                astro.themeBox.updateUI();
                break;
            case "FlatLightLaf":
                astro.themeBox.setSelectedIndex(1);
                astro.themeBox.updateUI();
            case "ARC - Orange":
                astro.themeBox.setSelectedIndex(2);
                astro.themeBox.updateUI();
                break;
            default:
                astro.themeBox.setSelectedIndex(0);
                astro.themeBox.updateUI();
                break;
        }
*/
 /*       astro.themeBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                try {
                    switch (astro.themeBox.getSelectedIndex()){
                        case 0:
                            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarkLaf");
                            propertyService.setProperty("theme", "FlatDarkLaf");
                            break;
                        case 1:
                            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatLightLaf");
                            propertyService.setProperty("theme", "FlatLightLaf");
                            break;
                        case 2:
                            UIManager.setLookAndFeel("com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme");
                            propertyService.setProperty("theme", "ARC - Orange");
                            break;
                        default:
                            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarkLaf");
                            break;
                    }
                }    catch (ClassNotFoundException | InstantiationException
                        | IllegalAccessException | UnsupportedLookAndFeelException exception) {
                    exception.printStackTrace();
                }

                SwingUtilities.updateComponentTreeUI(astro);

            }
        });
    */
        FlatTabbedPaneAddIcon flatTabbedPaneAddIcon = new FlatTabbedPaneAddIcon();

        //Settings Tab
        //add GUISettings form to GUISettings Panel
        GUISettings guiSettings = new GUISettings(astro);
        astro.tabbedSettings.insertTab("GUI",null, guiSettings.GUISettingsPanel,"tooltip", astro.tabbedSettings.getTabCount());

        //Text Editor Tab
        //add new textEditorTab button
        JLabel newTextEditorTabButton = new JLabel();
        newTextEditorTabButton.setIcon(flatTabbedPaneAddIcon);
        astro.tabbedTextEditor.insertTab("+",null, null,"tooltip", astro.tabbedTextEditor.getTabCount());
        astro.tabbedTextEditor.setTabComponentAt(astro.tabbedTextEditor.getTabCount()-1, newTextEditorTabButton);
        astro.tabbedTextEditor.getComponentAt(astro.tabbedTextEditor.getTabCount()-1);

        //create new textEditorTab
        createNewTextEditorTab(astro, null);

        //Applications Tab
        //add new applicationsTab button
        JLabel newApplicationsTabButton = new JLabel();
        newApplicationsTabButton.setIcon(flatTabbedPaneAddIcon);
        astro.tabbedApplications.insertTab("+",null, null,"tooltip", astro.tabbedApplications.getTabCount());
        astro.tabbedApplications.setTabComponentAt(astro.tabbedApplications.getTabCount()-1, newApplicationsTabButton);
        astro.tabbedApplications.getComponentAt(astro.tabbedApplications.getTabCount()-1);

        //create new applicationsTab
        createNewApplicationsTab(astro);


        //Browser Tab
        //add new browserTab button
        JLabel newBrowserTabButton = new JLabel();
        newBrowserTabButton.setIcon(flatTabbedPaneAddIcon);
        astro.tabbedBrowserPanel.insertTab("+",null, null,"tooltip", astro.tabbedBrowserPanel.getTabCount());
        astro.tabbedBrowserPanel.setTabComponentAt(astro.tabbedBrowserPanel.getTabCount()-1, newBrowserTabButton);
        astro.tabbedBrowserPanel.getComponentAt(astro.tabbedBrowserPanel.getTabCount()-1);

        //create new browserTab
        createNewBrowserTab(cefApp, astro, null);

        //render all GUI
        astro.pack();
        SwingUtilities.updateComponentTreeUI(astro);

        JButton b1=new JButton("1");

        //set Gui from properties using GUISettings methods


        //main page
       /* CefBrowser browser;
        browser = client.createBrowser(propertyService.getString(Props.walletURL), false, false);
        Component browserUI = browser.getUIComponent();
        browserFrame.browser = browser;
*/


/*


        //browserFrame.browserDisplay.add(browserUI);
        astro.tabbedBrowserPanel.insertTab("Loading ...",null, browserFrame.browserPanel,"tooltip", astro.tabbedBrowserPanel.getTabCount()-1);
        //astro.tabbedBrowserPanel.insertTab("Loading ...",null, browserUI,"tooltip", astro.tabbedBrowserPanel.getTabCount());
        astro.tabbedBrowserPanel.setTabComponentAt(astro.tabbedBrowserPanel.getTabCount()-2, header.headerPanel);
        astro.tabbedBrowserPanel.setSelectedIndex(astro.tabbedBrowserPanel.getTabCount()-2);
        browserFrame.browserTab = astro.tabbedBrowserPanel.getSelectedComponent();
        //browserFrame.index = astro.tabbedBrowserPanel.getSelectedIndex();
        //add new browser tab button


        astro.tabbedBrowserPanel.getSelectedComponent().setVisible(true);
        header.headerPanel.setVisible(true);
        astro.pack();

 */
/*
        newTab.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);

                if(newTab.isFocusOwner()){
                    //astro.tabbedBrowserPanel.setSelectedIndex(astro.tabbedBrowserPanel.getSelectedIndex()-1);
                }

            }
        });


 */
    /*    //write the URL out
        client.addLoadHandler(new CefLoadHandlerAdapter() {
            @Override
            public void onLoadingStateChange(CefBrowser browser,
                                             boolean isLoading,
                                             boolean canGoBack,
                                             boolean canGoForward) {
                if (!isLoading) {
                    // The page has finished loading.
                    // Do something with |url|
                    //set urlField to tab URL
                    String URL;
                    //URL = ((CefBrowser) browserFrame.browserList.get(astro.tabbedBrowserPanel.getSelectedIndex())).getURL();
                    URL = browser.getURL();
                    browserFrame.urlField.setText(URL);
                    //set tab title to tab URL
                    astro.tabbedBrowserPanel.setTitleAt(astro.tabbedBrowserPanel.getSelectedIndex(), URL);
                }
            }
        });
*/



        //add new browser tab with "+" button
        newBrowserTabButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                createNewBrowserTab(cefApp, astro, propertyService.getString(Props.newTabURL));

            }
        });

        //add new text editor tab with "+" button
        newTextEditorTabButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                createNewTextEditorTab(astro, null);

            }
        });

        //add new text editor tab with "+" button
        newApplicationsTabButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                createNewApplicationsTab(astro);

            }
        });

        //solution to give the focus to another instance from browser
/*
        client.addFocusHandler(new CefFocusHandlerAdapter() {
            @Override
            public void onGotFocus(CefBrowser browser) {
                if (browserFrame.browserFocus_) return;
                browserFrame.browserFocus_ = true;
                KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();
                browser.setFocus(true);
            }

            @Override
            public void onTakeFocus(CefBrowser browser, boolean next) {
                browserFrame.browserFocus_ = false;
            }
        });
*/

        //bug: closing site:https://www.origo.hu/sport/galeria/20201230-laza-sara-salamo-isco-real-madrid-par-szexi-kepek-galeria.html
        //close the whole application
        astro.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    //save isDarkTheme
                    if (guiSettings.getIsDarkTheme() != propertyService.getBoolean(Props.isDarkTheme)) {
                        propertyService.setProperty("isDarkTheme", guiSettings.getIsDarkTheme().toString());
                    }
                    //save LightTheme
                    if (guiSettings.getLightTheme() != guiSettings.lightThemesList.getSelectedValue().toString()) {
                        propertyService.setProperty("LightTheme", guiSettings.lightThemesList.getSelectedValue().toString());
                    }
                    //save DarkTheme
                    if (guiSettings.getDarkTheme() != guiSettings.darkThemesList.getSelectedValue().toString()) {
                        propertyService.setProperty("DarkTheme", guiSettings.darkThemesList.getSelectedValue().toString());
                    }
                } catch (Exception exception) {

                }
                //JOptionPane.showConfirmDialog(astro, guiSettings.getisDarkTheme().toString(), "Application Launch ERROR", JOptionPane.YES_NO_OPTION);
                cefApp.getInstance().dispose();
                astro.dispose();
            }

        });



/*
        URL website = new URL("https://github.com/burst-apps-team/burstcoin/releases/download/v2.5.3/burstcoin-2.5.3.zip");
        ReadableByteChannel rbc = Channels.newChannel(website.openStream());
        FileOutputStream fos = new FileOutputStream("./PROJECT/WALLET/BURST/burstcoin-2.5.3.zip");
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);


 */
        //Create file structure if not exists
        //WALLET/BURST
        File burstDir = new File(burstDirPath);
        if(!burstDir.exists()){
            burstDir.mkdirs();
        }
        //WALLET/PHOENIX
        File phoenixDir = new File(phoenixDirPath);
        if(!phoenixDir.exists()){
            phoenixDir.mkdirs();
        }
        //EXCHANGE/BTDEX
        File btdexDir = new File(btdexDirPath);
        if(!btdexDir.exists()){
            btdexDir.mkdirs();
        }
        //MINER/SCAVENGER
        File scavengerDir = new File(scavengerDirPath);
        if(!scavengerDir.exists()){
            scavengerDir.mkdirs();
        }
        //PLOTTER/TURBOPLOTTER
        File turboplotterDir = new File(turboplotterDirPath);
        if(!turboplotterDir.exists()){
            turboplotterDir.mkdirs();
        }
        //PLOTTER/ENGRAVER
        File engraverDir = new File(engraverDirPath);
        if(!engraverDir.exists()){
            engraverDir.mkdirs();
        }
        //IMAGES
        File imagesDir = new File(imagesDirPath);
        if(!imagesDir.exists()){
            imagesDir.mkdirs();
        }
        //ICONS
        File iconsDir = new File(iconsDirPath);
        if(!iconsDir.exists()){
            iconsDir.mkdirs();
        }
        //CONFIG
        File configDir = new File(configDirPath);
        if(!configDir.exists()){
            configDir.mkdirs();
        }

        //File burstDir = new File("./PROJECT/WALLET/BURST");

        //File scavengerDir = new File("./PROJECT/MINER/SCAVENGER/");
/*
        File turboplotter = new File("./PROJECT/PLOTTER/TURBOPLOTTER/TurboPlotter.exe");

        astro.start_burst.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.open(burst_maria);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }



                try {
                    Runtime.getRuntime().exec("cmd.exe /c start startburst.bat", null, burstDir);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }



            }
        });

        astro.start_scavenger.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    Runtime.getRuntime().exec("cmd.exe /c start scavenger.exe", null, scavengerDir);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

        }

        });

        astro.start_turboplotter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                try {
                    Process process = new ProcessBuilder("D:\\Dev_tools\\ASTRO\\target\\plotter\\turboplotter\\TurboPlotter.exe").start();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }



                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.open(turboplotter);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        });
    */
        astro.pack();
        //new BrowserFrame("http://openburstpool.ddns.net:8126/", useOsr, false);
    }

}
