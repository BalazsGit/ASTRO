package astro;

import astro.Settings.GUISettings;
import com.formdev.flatlaf.*;
import com.formdev.flatlaf.intellijthemes.*;
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
import java.io.IOException;
import java.util.Timer;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import static javax.swing.JDialog.setDefaultLookAndFeelDecorated;



public class Main {

    static boolean browserFocus_ = true;

    static PropertyService propertyService;

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

    public static String defaultFontFamily;
    public static int defaultFontSize;
    public static int defaultFontStyle;





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
    static void createNewBrowserTab(CefApp cefApp, MainFrame astro, String URL){

        BrowserTabHeader header = new BrowserTabHeader("Loading ...");
        BrowserFrame browserFrame = new BrowserFrame(cefApp, astro, header, URL);

        astro.tabbedBrowserPanel.insertTab("Loading ...",null, browserFrame.browserPanel,"tooltip", astro.tabbedBrowserPanel.getTabCount()-1);
        astro.tabbedBrowserPanel.setTabComponentAt(astro.tabbedBrowserPanel.getTabCount()-2, header.headerPanel);
        //the new tab is the selected one
        astro.tabbedBrowserPanel.setSelectedIndex(astro.tabbedBrowserPanel.getTabCount()-2);
        browserFrame.browserTab = astro.tabbedBrowserPanel.getSelectedComponent();

    }

    //never be the first tab
    static void createNewTextEditorTab(MainFrame astro, String URL){

        TextEditorTabHeader header = new TextEditorTabHeader("Loading ...");
        TextEditor textEditor = new TextEditor(astro, header);

        astro.tabbedTextEditor.insertTab("Loading ...",null, textEditor.textEditorPanel,"tooltip", astro.tabbedTextEditor.getTabCount()-1);
        //astro.tabbedBrowserPanel.insertTab("Loading ...",null, browserUI,"tooltip", astro.tabbedBrowserPanel.getTabCount());
        astro.tabbedTextEditor.setTabComponentAt(astro.tabbedTextEditor.getTabCount()-2, header.headerPanel);
        //the new tab is the selected one
        astro.tabbedTextEditor.setSelectedIndex(astro.tabbedTextEditor.getTabCount()-2);
        textEditor.textEditorTab = astro.tabbedTextEditor.getSelectedComponent();

    }

    //never be the first tab
    static void createNewApplicationsTab(MainFrame astro){

        ApplicationsTabHeader header = new ApplicationsTabHeader();
        ApplicationsFrame applicationsFrame = new ApplicationsFrame(astro, header);

        astro.tabbedApplications.insertTab("Loading ...",null, applicationsFrame.applicationsPanel,"tooltip", astro.tabbedApplications.getTabCount()-1);
        //astro.tabbedBrowserPanel.insertTab("Loading ...",null, browserUI,"tooltip", astro.tabbedBrowserPanel.getTabCount());
        astro.tabbedApplications.setTabComponentAt(astro.tabbedApplications.getTabCount()-2, header.headerPanel);
        //the new tab is the selected one
        astro.tabbedApplications.setSelectedIndex(astro.tabbedApplications.getTabCount()-2);
        applicationsFrame.applicationsTab = astro.tabbedApplications.getSelectedComponent();

    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException {

                String propertiesFileName = "astro.properties";
                propertyService = new PropertyServiceImpl(propertiesFileName);
                String theme = propertyService.getString(Props.theme);

                Timer timer = new Timer();
                timer.schedule(new Reload(propertyService, propertiesFileName), 0, propertyService.getLong(Props.reload));

        //UIManager settings
        try {
            //set GUI theme
            switch (theme){
                case "FlatDarkLaf":
                    UIManager.setLookAndFeel(new FlatDarkLaf());
                    break;
                case "FlatLightLaf":
                    UIManager.setLookAndFeel(new FlatLightLaf());
                    break;
                case "ARC - Orange":
                    UIManager.setLookAndFeel(new FlatArcOrangeIJTheme());
                    break;
                default:
                    UIManager.setLookAndFeel(new FlatDarkLaf());
                    break;
            }
            //Font defaultFont
            Font defaultFont = UIManager.getDefaults().getFont("defaultFont");
            //Segeo UI
            defaultFontFamily = defaultFont.getFamily();
            //PLAIN = 0
            defaultFontStyle = defaultFont.getStyle();
            //12
            defaultFontSize = defaultFont.getSize();

            Font userFont = new Font(propertyService.getString(Props.fontFamily), propertyService.getInt(Props.fontStyle), propertyService.getInt(Props.fontSize));

            UIManager.put( "defaultFont", userFont );
            UIManager.put( "TabbedPane.showTabSeparators", true );
            UIManager.put( "TabbedPane.scrollButtonsPolicy", "asNeededSingle" );
            UIManager.put( "TabbedPane.tabsPopupPolicy", "never" );
            UIManager.put( "TabbedPane.tabSelectionHeight", 1 );
            UIManager.put( "Table.showHorizontalLines", true );
            UIManager.put( "Table.showVerticalLines", true );
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
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

        astro.themeBox.addItemListener(new ItemListener() {
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

        FlatTabbedPaneAddIcon flatTabbedPaneAddIcon = new FlatTabbedPaneAddIcon();

        //Settings Tab
        //add GUISettings form to GUISettings Panel
        GUISettings guiSettings = new GUISettings();
        astro.tabbedSettings.insertTab("GUI",null, guiSettings.GUISettinsPanel,"tooltip", astro.tabbedSettings.getTabCount());

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
        //add new applicatinsTab button
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

        astro.pack();

        JButton b1=new JButton("1");
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
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
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

        File burstDir = new File("./PROJECT/WALLET/BURST");

        File scavengerDir = new File("./PROJECT/MINER/SCAVENGER/");

        File turboplotter = new File("./PROJECT/PLOTTER/TURBOPLOTTER/TurboPlotter.exe");

        astro.start_burst.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

/*
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.open(burst_maria);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

 */

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

                /*
                try {
                    Process process = new ProcessBuilder("D:\\Dev_tools\\ASTRO\\target\\plotter\\turboplotter\\TurboPlotter.exe").start();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                 */

                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.open(turboplotter);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        });

        astro.pack();
        //new BrowserFrame("http://openburstpool.ddns.net:8126/", useOsr, false);
    }

}
