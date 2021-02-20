package astro.Settings;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import astro.MainFrame;
import com.formdev.flatlaf.intellijthemes.*;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class GUISettings extends JFrame {
    public JPanel GUISettinsPanel;
    private JTable themesTable;
    private JTable fontFamilySettings;
    private JPanel GUIThemesPanel;
    private JPanel FontFamilyPanel;
    private JList lightThemesList;
    private JComboBox themesComBox;
    private JList darkThemesList;
    private JPanel darkThemesPanel;
    private JPanel lightThemesPanel;
    private JScrollPane leftScrollPanel;
    private JScrollPane rightScrollPanel;
    private JPanel lightThemePanel;
    private JPanel darkThemePanel;
    private JLabel lightThemeLabel;
    private JLabel darkThemeLabel;
    private FlatAllIJThemes flatAllIJThemes;
    private int rowCountLight = 0;
    private int rowCountDark = 0;
    private MainFrame mainFrame;
    private FlatAllIJThemes.FlatIJLookAndFeelInfo flatIJLookAndFeelInfo;

    public static final FlatAllIJThemes.FlatIJLookAndFeelInfo[] INFOSCORE = new FlatAllIJThemes.FlatIJLookAndFeelInfo[]{
        new FlatAllIJThemes.FlatIJLookAndFeelInfo("Flat Light", "com.formdev.flatlaf.FlatLightLaf", false),
        new FlatAllIJThemes.FlatIJLookAndFeelInfo("Flat Dark", "com.formdev.flatlaf.FlatDarkLaf", true),
        new FlatAllIJThemes.FlatIJLookAndFeelInfo("Flat IntelliJ", "com.formdev.flatlaf.FlatIntelliJLaf", false),
        new FlatAllIJThemes.FlatIJLookAndFeelInfo("Flat Darcula", "com.formdev.flatlaf.FlatDarculaLaf", true)
    };

    public GUISettings(MainFrame mainFrame) {

        this.mainFrame = mainFrame;

        flatAllIJThemes = new FlatAllIJThemes();

        DefaultListModel<String> lightListModel = new DefaultListModel<>();
        lightThemesList.setModel(lightListModel);
        DefaultListModel<String> darktListModel = new DefaultListModel<>();
        darkThemesList.setModel(darktListModel);
/*
        DefaultTableModel tableModel = new DefaultTableModel();
        themesTable.setModel(tableModel);
        tableModel.addColumn("Light");
        tableModel.addColumn("Dark");


*/
        ArrayList<FlatAllIJThemes.FlatIJLookAndFeelInfo> darkThemesArrayList = new ArrayList<>();
        ArrayList<FlatAllIJThemes.FlatIJLookAndFeelInfo> lightThemesArrayList = new ArrayList<>();



        for (FlatAllIJThemes.FlatIJLookAndFeelInfo theme : INFOSCORE) {
            //theme list
            if (!theme.isDark()) {
                lightListModel.addElement(theme.getName());
                lightThemesArrayList.add(theme);
            } else {
                darktListModel.addElement(theme.getName());
                darkThemesArrayList.add(theme);
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
                lightThemesArrayList.add(theme);
            }
            else{
                darktListModel.addElement(theme.getName());
                darkThemesArrayList.add(theme);
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
                super.mousePressed(e);
                try {
                    UIManager.setLookAndFeel(lightThemesArrayList.get(lightThemesList.getSelectedIndex()).getClassName());
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
                super.mousePressed(e);
            try {
                UIManager.setLookAndFeel(darkThemesArrayList.get(darkThemesList.getSelectedIndex()).getClassName());
            } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | UnsupportedLookAndFeelException exception) {
                exception.printStackTrace();
            }
            SwingUtilities.updateComponentTreeUI(mainFrame);
            }
        });
    }
}
