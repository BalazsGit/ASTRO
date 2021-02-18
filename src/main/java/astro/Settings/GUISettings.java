package astro.Settings;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.intellijthemes.*;

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
    private FlatAllIJThemes.FlatIJLookAndFeelInfo flatIJLookAndFeelInfo;

    public GUISettings() {


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
            }
            else{
                darktListModel.addElement(theme.getName());
            }
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



    }
}
