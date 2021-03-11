package astro.settings;

import javax.swing.*;

import astro.MainFrame;
import com.formdev.flatlaf.intellijthemes.*;
import config.Props;

import java.awt.event.*;
import java.util.HashMap;

import static astro.Main.propertyService;

public class GUISettings extends JFrame {
    public JPanel GUISettingsPanel;
    private JTable themesTable;
    private JTable fontFamilySettings;
    private JPanel GUIThemesPanel;
    private JPanel FontFamilyPanel;
    public JList lightThemesList;
    private JComboBox themesComBox;
    public JList darkThemesList;
    private JPanel darkThemesPanel;
    private JPanel lightThemesPanel;
    private JScrollPane leftScrollPanel;
    private JScrollPane rightScrollPanel;
    private JPanel lightThemePanel;
    private JPanel darkThemePanel;
    private JLabel lightThemeLabel;
    private JLabel darkThemeLabel;
    private JLabel restoreDefaultThemesLabel;
    private JLabel saveLightTheme;
    private JLabel saveDarkTheme;
    private String lightTheme;
    private String darkTheme;
    private FlatAllIJThemes flatAllIJThemes;
    private int rowCountLight = 0;
    private int rowCountDark = 0;
    private MainFrame mainFrame;
    private String defaultLightTheme;
    private String defaultDarkTheme;

    private Boolean isDarkTheme = true;
    //private PropertyService propertyService;
    private FlatAllIJThemes.FlatIJLookAndFeelInfo flatIJLookAndFeelInfo;

    public static final FlatAllIJThemes.FlatIJLookAndFeelInfo[] INFOSCORE = new FlatAllIJThemes.FlatIJLookAndFeelInfo[]{
        new FlatAllIJThemes.FlatIJLookAndFeelInfo("Flat Light", "com.formdev.flatlaf.FlatLightLaf", false),
        new FlatAllIJThemes.FlatIJLookAndFeelInfo("Flat Dark", "com.formdev.flatlaf.FlatDarkLaf", true),
        new FlatAllIJThemes.FlatIJLookAndFeelInfo("Flat IntelliJ", "com.formdev.flatlaf.FlatIntelliJLaf", false),
        new FlatAllIJThemes.FlatIJLookAndFeelInfo("Flat Darcula", "com.formdev.flatlaf.FlatDarculaLaf", true)
    };

    public void setIsDarkTheme(Boolean isDarkTheme){
        this.isDarkTheme = isDarkTheme;
    }
    public Boolean getIsDarkTheme(){
        return isDarkTheme;
    }
    public void setLightTheme(String lightTheme){
        this.lightTheme = lightTheme;
    }
    public String getLightTheme(){
        return lightTheme;
    }
    public void setDarkTheme(String darkTheme){
        this.darkTheme = darkTheme;
    }
    public String getDarkTheme(){
        return darkTheme;
    }
    public void setDefaultLightTheme(String setDefaultLightTheme){
        this.defaultLightTheme = defaultLightTheme;
    }
    public String getDefaultLightTheme(){
        return defaultLightTheme;
    }
    public void setDefaultDarkTheme(String setDefaultDarkTheme){
        this.defaultDarkTheme = defaultDarkTheme;
    }
    public String getDefaultDarkTheme(){
        return defaultDarkTheme;
    }

    public GUISettings(MainFrame mainFrame) {

        this.mainFrame = mainFrame;

        lightTheme = propertyService.getString(Props.LightTheme);
        darkTheme = propertyService.getString(Props.DarkTheme);
        isDarkTheme = propertyService.getBoolean(Props.isDarkTheme);

        defaultLightTheme = "Flat Light";
        defaultDarkTheme = "Flat Dark";

        lightThemeLabel.setText(lightTheme);
        darkThemeLabel.setText(darkTheme);
        //propertyService.
        //propertyService = new PropertyServiceImpl(propertiesFileName);

        //start Font settings




        //end Font settings

        //start GUI Themes settings
        flatAllIJThemes = new FlatAllIJThemes();

        DefaultListModel<String> lightListModel = new DefaultListModel<>();
        lightThemesList.setModel(lightListModel);
        DefaultListModel<String> darkListModel = new DefaultListModel<>();
        darkThemesList.setModel(darkListModel);
/*
        DefaultTableModel tableModel = new DefaultTableModel();
        themesTable.setModel(tableModel);
        tableModel.addColumn("Light");
        tableModel.addColumn("Dark");
*/

        HashMap<String, FlatAllIJThemes.FlatIJLookAndFeelInfo> lightThemesHashMap = new HashMap<>();
        HashMap<String, FlatAllIJThemes.FlatIJLookAndFeelInfo> darkThemesHashMap = new HashMap<>();

        for (FlatAllIJThemes.FlatIJLookAndFeelInfo theme : INFOSCORE) {
            //theme list
            if (!theme.isDark()) {
                lightListModel.addElement(theme.getName());
                lightThemesHashMap.put(theme.getName(),theme);
            } else {
                darkListModel.addElement(theme.getName());
                darkThemesHashMap.put(theme.getName(),theme);
            }
        }

        for (FlatAllIJThemes.FlatIJLookAndFeelInfo theme : flatAllIJThemes.INFOS)
        {

        /*
            //theme table
            if (!theme.isDark()){
                tableModel.addRow(new Object[]{"v1", "v2"});
                tableModel.getRowCount();
            }
            else{

            }
        */

            //theme list
            if(!theme.isDark()){
                lightListModel.addElement(theme.getName());
                lightThemesHashMap.put(theme.getName(),theme);
            }
            else{
                darkListModel.addElement(theme.getName());
                darkThemesHashMap.put(theme.getName(),theme);
            }

            /*
            try {
                UIManager.setLookAndFeel("com.formdev.intellijthemes." + theme);
            } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | UnsupportedLookAndFeelException exception) {
                exception.printStackTrace();
            }
            //propertyService.setProperty("theme", "FlatDarkLaf");
            //break;
/*
            //theme combobox
            themesComBox.addItem(theme.getName());
*/
            //set light theme
            //set dark theme
            //save changes
            //set defaults
        }

        //set GUI from properties
        if(isDarkTheme){
           try {
               UIManager.setLookAndFeel(darkThemesHashMap.get(darkTheme).getClassName());
            } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | UnsupportedLookAndFeelException exception) {
                exception.printStackTrace();
            }
        }
        else{
            try {
                UIManager.setLookAndFeel(lightThemesHashMap.get(lightTheme).getClassName());
            } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | UnsupportedLookAndFeelException exception) {
                exception.printStackTrace();
            }
        }

        //flatIJLookAndFeelInfo = flatAllIJThemes.INFOS[0];
        //flatIJLookAndFeelInfo.getName();
/*
        lightThemesList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                try {
                    UIManager.setLookAndFeel(lightThemesArrayList.get(lightThemesList.getSelectedIndex()).getClassName());
                } catch (ClassNotFoundException | InstantiationException
                    | IllegalAccessException | UnsupportedLookAndFeelException exception) {
                    exception.printStackTrace();
                }
                SwingUtilities.updateComponentTreeUI(mainFrame);
            }
        });
        darkThemesList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                try {
                    UIManager.setLookAndFeel(darkThemesArrayList.get(darkThemesList.getSelectedIndex()).getClassName());
                } catch (ClassNotFoundException | InstantiationException
                    | IllegalAccessException | UnsupportedLookAndFeelException exception) {
                    exception.printStackTrace();
                }
                SwingUtilities.updateComponentTreeUI(mainFrame);
            }
        });*/
        lightThemesList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                isDarkTheme = false;
                mainFrame.isDarkThemeLabel.setText("LightTheme");
                try {
                    UIManager.setLookAndFeel(lightThemesHashMap.get(lightThemesList.getSelectedValue()).getClassName());
                    lightThemeLabel.setText(lightThemesList.getSelectedValue().toString());
                } catch (ClassNotFoundException | InstantiationException
                    | IllegalAccessException | UnsupportedLookAndFeelException exception) {
                    exception.printStackTrace();
                }
                SwingUtilities.updateComponentTreeUI(mainFrame);
            }
        });
        darkThemesList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                isDarkTheme = true;
                //propertyService.setProperty("isDarkTheme", "false");
                mainFrame.isDarkThemeLabel.setText("DarkTheme");
                try {
                    UIManager.setLookAndFeel(darkThemesHashMap.get(darkThemesList.getSelectedValue()).getClassName());
                    darkThemeLabel.setText(darkThemesList.getSelectedValue().toString());
                } catch (ClassNotFoundException | InstantiationException
                    | IllegalAccessException | UnsupportedLookAndFeelException exception) {
                    exception.printStackTrace();
                }
                SwingUtilities.updateComponentTreeUI(mainFrame);
            }
        });

        //set darkThemesList and lightThemesList selected values from properties to be able to set light or dark theme by reading selected values from the lists
        lightThemesList.setSelectedValue(lightTheme,false);
        darkThemesList.setSelectedValue(darkTheme,false);

        //button to set Dark or Light theme
        if(isDarkTheme) {
            //later set icon
            mainFrame.isDarkThemeLabel.setText("DarkTheme");
        }
        else{
            //later set icon
            mainFrame.isDarkThemeLabel.setText("LightTheme");
        }
        //change between LightTheme and DarkTheme
        mainFrame.isDarkThemeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                //propertyService.reload(propertiesFileName);
                if (isDarkTheme){
                    isDarkTheme = false;
                    mainFrame.isDarkThemeLabel.setText("LightTheme");
                    try {
                        UIManager.setLookAndFeel(lightThemesHashMap.get(lightThemesList.getSelectedValue()).getClassName());
                    } catch (ClassNotFoundException | InstantiationException
                        | IllegalAccessException | UnsupportedLookAndFeelException exception) {
                        exception.printStackTrace();
                    }
                }
                else{
                    isDarkTheme = true;
                    mainFrame.isDarkThemeLabel.setText("DarkTheme");
                    try {
                        UIManager.setLookAndFeel(darkThemesHashMap.get(darkThemesList.getSelectedValue()).getClassName());
                        //UIManager.setLookAndFeel(darkThemesHashMap.get(propertyService.getString(Props.DarkTheme)).getClassName());
                    } catch (ClassNotFoundException | InstantiationException
                        | IllegalAccessException | UnsupportedLookAndFeelException exception) {
                        exception.printStackTrace();
                    }
                }
                SwingUtilities.updateComponentTreeUI(mainFrame);
            }
        });
        restoreDefaultThemesLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                propertyService.setProperty("LightTheme", defaultLightTheme);
                lightThemesList.setSelectedValue(defaultLightTheme,false);
                lightThemeLabel.setText(defaultLightTheme);
                propertyService.setProperty("DarkTheme", defaultDarkTheme);
                darkThemesList.setSelectedValue(defaultDarkTheme,false);
                darkThemeLabel.setText(defaultDarkTheme);
                if(isDarkTheme){
                    try {
                        UIManager.setLookAndFeel(darkThemesHashMap.get(defaultDarkTheme).getClassName());
                    } catch (ClassNotFoundException | InstantiationException
                        | IllegalAccessException | UnsupportedLookAndFeelException exception) {
                        exception.printStackTrace();
                    }
                }
                else{
                    try {
                        UIManager.setLookAndFeel(lightThemesHashMap.get(defaultLightTheme).getClassName());
                    } catch (ClassNotFoundException | InstantiationException
                        | IllegalAccessException | UnsupportedLookAndFeelException exception) {
                        exception.printStackTrace();
                    }
                }
                SwingUtilities.updateComponentTreeUI(mainFrame);
            }
        });
        //end GUI Themes settings
        SwingUtilities.updateComponentTreeUI(mainFrame);
    }
}
