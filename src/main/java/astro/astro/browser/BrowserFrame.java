package astro.astro.browser;

import astro.astro.mainframe.MainFrame;
import org.cef.CefApp;
import astro.astro.config.Props;
import org.cef.CefClient;
import org.cef.browser.CefBrowser;
import org.cef.handler.CefFocusHandlerAdapter;
import org.cef.handler.CefLoadHandlerAdapter;
import astro.astro.util.VerticalMirrorLabel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import static astro.astro.Main.*;


public class BrowserFrame extends JFrame {

    public JPanel browserPanel;
    public JPanel urlPanel;
    public JTextField urlField;
    public VerticalMirrorLabel backward;
    public JLabel forward;
    public JButton reload;
    public JButton stopLoad;
    public JButton load;
    public JPanel browserDisplay;
    private JPanel navBar;
    private JButton search;
    private JButton newTab;
    private JPanel backwardPanel;
    private JPanel forwardPanel;
    private JPanel backForwardPanel;
    private JPanel separatorPanel;
    private JLabel separatorLabel;

    public CefBrowser browser;
    public Component browserUI;
    public CefClient client;
    public CefApp cefApp;
    public MainFrame mainFrame;
    public BrowserTabHeader header;

    public Component browserTab;

    public int index = -1;
    public int tabCount = 0;

    public String browserFrameURL;



    public ArrayList browserList = new ArrayList<CefBrowser>();

    //public boolean browserFocus_ = true;



    public BrowserFrame(CefApp cefApp, MainFrame mainFrame, BrowserTabHeader header, String URL) {

        this.header = header;
        //cefApp = CefApp.getInstance(DEFAULT_SETTINGS);
        client = cefApp.createClient();

        //this.client = client;
        //this.add(browserPanel);

        urlField.setText("Loading ...");
        urlField.setFocusable(true);
        urlField.setVisible(true);

        newTab.setText(plus1);


        //setLocationRelativeTo(null);
        //setVisible(true);

        backward = new VerticalMirrorLabel(arrowRight3 + "  ");
        backwardPanel.add(backward);
        separatorLabel.setText(separator1);
        forward = new JLabel(arrowRight3);
        forwardPanel.add(forward);
        //backward.setText(arrowRight3);
        //Graphics2D g2 = (Graphics2D)backward.getGraphics();
        //g2.rotate(Math.PI,0,0);


        //forward.setFont(forward.getFont().deriveFont(20f));
        browserFrameURL = URL;

        if (URL != null) {
            urlField.setText(browserFrameURL);
            browser = client.createBrowser(URL, false, false);
        } else {
            browserFrameURL = propertyService.getString(Props.startPageURL);
            urlField.setText(browserFrameURL);
            browser = client.createBrowser(browserFrameURL, false, false);
        }
        browserUI = browser.getUIComponent();
        browserDisplay.add(browserUI);

        //open link in new tab instead of new window
        /*
        client.addLifeSpanHandler(new CefLifeSpanHandlerAdapter() {
            @Override
            public boolean onBeforePopup(CefBrowser browser, CefFrame frame, String target_url, String target_frame_name) {
                createNewBrowserTab(cefApp, mainFrame, target_url);
                //return super.onBeforePopup(browser, frame, target_url, target_frame_name);
                return true;
            }
        });
        */


        //header = new BrowserTabHeader("X");
        //mainFrame.tabbedBrowserPanel.setTabComponentAt(mainFrame.tabbedBrowserPanel.getTabCount()-1, header.headerPanel);

        //write the URL out
        client.addLoadHandler(new CefLoadHandlerAdapter() {
            @Override
            public void onLoadingStateChange(CefBrowser browser,
                                             boolean isLoading,
                                             boolean canGoBack,
                                             boolean canGoForward) {

                if (!isLoading) {
                    // The page has finished loading.
                    // Do something with |url|
                    browserFrameURL = browser.getURL();
                    //set urlField to the loaded URL
                    urlField.setText(browserFrameURL);
                    //set tab title to tab URL
                    //mainFrame.tabbedBrowserPanel.setTitleAt(index, URL);
                    header.jLabel.setText(browserFrameURL);
                } else {
                    //color change later
                    //set urlField to the entered URL
                    //urlField.setText(browserFrameURL);
                    //urlField.setText("Loading ...");
                    header.jLabel.setText("Loading ...");
                }
            }
        });

        header.xButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                if (mainFrame.tabbedBrowserPanel.getTabCount() > 2) {

                    mainFrame.tabbedBrowserPanel.remove(browserTab);
                    browser.getClient().dispose();

                }

                //if the last close tab is selected, than give the focus to the one left browser panel
                index = mainFrame.tabbedBrowserPanel.getSelectedIndex();
                tabCount = mainFrame.tabbedBrowserPanel.getTabCount();

                if (index == tabCount - 1) {

                    mainFrame.tabbedBrowserPanel.setSelectedIndex(index - 1);
                    mainFrame.tabbedBrowserPanel.getSelectedComponent().requestFocus();

                }
            }
        });

        mainFrame.tabbedBrowserPanel.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                JTabbedPane sourceTabbedPane = (JTabbedPane) e.getSource();

                if (mainFrame.tabbedBrowserPanel.getTabCount() == 2) {
                    header.xButton.setVisible(false);
                } else {
                    header.xButton.setVisible(true);
                }
            }
        });

        /*solution to give the focus to another instance from browser*/
/*
        urlField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);

                if (!browserFocus_) return;
                browserFocus_ = false;
                KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();

                urlField.grabFocus();
                urlField.requestFocus();
            }
        });

        client.addFocusHandler(new CefFocusHandlerAdapter() {
            @Override
            public void onGotFocus(CefBrowser browser) {
                if (browserFocus_) return;
                browserFocus_ = true;
                KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();
                browser.setFocus(true);
            }

            @Override
            public void onTakeFocus(CefBrowser browser, boolean next) {
                browserFocus_ = false;
            }
        });
*/
        /* END */

        client.addFocusHandler(new CefFocusHandlerAdapter() {
            @Override
            public void onGotFocus(CefBrowser browser) {
                KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();
            }
        });

        backward.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

              //browser.replaceMisspelling();

                browser.goBack();
                //browserFrameURL = browser.getURL();

            }
        });

        forward.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                browser.goForward();
                //browserFrameURL = browser.getURL();

            }
        });

        //load url if enter
        urlField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                browser.loadURL(urlField.getText());

            }
        });

        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                browser.loadURL(urlField.getText());

            }
        });

        reload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                browser.reload();

            }
        });

        stopLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                browser.stopLoad();

            }
        });

        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                createNewBrowserTab(cefApp, mainFrame, propertyService.getString(Props.searchEngineURL));

            }
        });
        newTab.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                createNewBrowserTab(cefApp, mainFrame, propertyService.getString(Props.newTabURL));

            }
        });
    }
}
