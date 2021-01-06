package astro;

import com.formdev.flatlaf.*;
import config.PropertyService;
import config.PropertyServiceImpl;
import config.Props;
import org.cef.CefApp;
import org.cef.CefSettings;

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

    //never be the first tab
    static void createNewBrowserTab(CefApp cefApp, MainFrame astro, String URL){

        BrowserTabHeader header = new BrowserTabHeader("Loading ...");
        BrowserFrame browserFrame = new BrowserFrame(cefApp, astro, header, URL);

        astro.tabbedBrowserPanel.insertTab("Loading ...",null, browserFrame.browserPanel,"tooltip", astro.tabbedBrowserPanel.getTabCount()-1);
        //astro.tabbedBrowserPanel.insertTab("Loading ...",null, browserUI,"tooltip", astro.tabbedBrowserPanel.getTabCount());
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

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
//        if (!CefApp.startup()) {
//            System.out.println("Startup initialization failed!");
//            return;
//        }
/*
        if (System.getProperty("log4j.configurationFile") == null) {
            System.setProperty("log4j.configurationFile", "logging_astro.xml");
        }

                Logger logger = LoggerFactory.getLogger(Main.class);

 */

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
                default:
                    UIManager.setLookAndFeel(new FlatDarkLaf());
                    break;
            }
            UIManager.put( "TabbedPane.showTabSeparators", true );
            UIManager.put( "TabbedPane.scrollButtonsPolicy", "asNeededSingle" );
            UIManager.put( "TabbedPane.tabsPopupPolicy", "never" );
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }



    /*
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        SwingUtilities.updateComponentTreeUI(frame);
*/
        //propertyservice test
/*
                if (propertyService.getBoolean(Props.firstRun))
                {
                    JOptionPane.showMessageDialog(null,
                            Props.walletURL,
                            "Warning",
                            JOptionPane.WARNING_MESSAGE);
                }


 */




//fast up GUI
        System.setProperty("sun.java2d.noddraw", Boolean.TRUE.toString());
        setDefaultLookAndFeelDecorated(true);

        boolean useOsr = false;


        CefSettings DEFAULT_SETTINGS = new CefSettings();
        DEFAULT_SETTINGS.windowless_rendering_enabled = false;

        CefApp cefApp = CefApp.getInstance(DEFAULT_SETTINGS);

 /*       CefClient client = cefApp.createClient();
*/


                //CefBrowser browser1 = client.createBrowser("https://global.bittrex.com/Market/Index?MarketName=BTC-BURST", false, false);
                //Component browserUI = browser.getUIComponent();
                //Component browserUI1 = browser1.getUIComponent();

                //Browser browser = new Browser("http://openburstpool.ddns.net:8126/", useOsr, false);
                //Browser browser1 = new Browser("http://openburstpool.ddns.net:8125/", useOsr, false);

        //new TabbedPaneTestFrame("http://www.baidu.com");

        //BrowserTabHeader header = new BrowserTabHeader("Loading ...");

        MainFrame astro = new MainFrame("ASTRO");
        //BrowserFrame browserFrame = new BrowserFrame(cefApp, astro, header);

        switch (theme){
            case "FlatDarkLaf":
                astro.themeBox.setSelectedIndex(0);
                astro.themeBox.updateUI();
                break;
            case "FlatLightLaf":
                astro.themeBox.setSelectedIndex(1);
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

        //Text Editor

        //new text editor tab -close tab button- the first tab
        JButton newTextEditorCloseTabButton = new JButton("+");

        //new text editor tab
        astro.tabbedTextEditor.insertTab("+",null, null,"tooltip", astro.tabbedBrowserPanel.getTabCount());
        astro.tabbedTextEditor.setTabComponentAt(astro.tabbedTextEditor.getTabCount()-1, newTextEditorCloseTabButton);
        astro.tabbedTextEditor.getComponentAt(astro.tabbedTextEditor.getTabCount()-1);

        //new tab before the close tab
        createNewTextEditorTab(astro, null);


        //TextEditor textEditor = new TextEditor();
        //astro.tabbedTextEditor.insertTab("Loading ...",null, textEditor.textEditorPanel,"tooltip", astro.tabbedTextEditor.getTabCount());

        astro.pack();

        //Browser
        //new browser tab -close tab button- the first tab
        JButton newBrowserTabButton = new JButton("+");
        Component newTab;

        //new browser tab
        astro.tabbedBrowserPanel.insertTab("+",null, null,"tooltip", astro.tabbedBrowserPanel.getTabCount());
        astro.tabbedBrowserPanel.setTabComponentAt(astro.tabbedBrowserPanel.getTabCount()-1, newBrowserTabButton);
        astro.tabbedBrowserPanel.getComponentAt(astro.tabbedBrowserPanel.getTabCount()-1);

        //new tab before the close tab
        createNewBrowserTab(cefApp, astro, null);

        astro.pack();


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
        newBrowserTabButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                createNewBrowserTab(cefApp, astro, propertyService.getString(Props.newtab));

            }
        });

        //add new text editor tab with "+" button
        newTextEditorCloseTabButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                createNewTextEditorTab(astro, null);

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
