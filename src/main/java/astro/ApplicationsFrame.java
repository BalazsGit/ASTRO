package astro;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApplicationsFrame extends JFrame {

    public JPanel applicationsPanel;
    private JPanel applicationContainer1;
    public JTable applicationsTable;
    public ApplicationsTabHeader header;
    public Component applicationsTab;
    public DefaultTableModel dtm;
    public GridLayout gridLayout;

    JPanel[][] applicationPanel;
    private int rowCount = 4;
    private int columnCount = 6;
    private int applicationCount = 0;
    private int index;
    private int tabCount;

    public ApplicationsFrame(MainFrame mainFrame, ApplicationsTabHeader header){

        this.add(applicationsPanel);
        this.header = header;

        createApplicationsTable(rowCount, columnCount);
      //  Application application = new Application();

        //JButton b1=new JButton("1");
        //applicationContainer1.add(application.applicationPanel);
        //applicationsPanel.add(b1);



        //applicationsPanel.add(b1,10);


        JButton b1=new JButton("1");
        JButton b2=new JButton("2");
        JButton b3=new JButton("3");
        JButton b4=new JButton("4");
        JButton b5=new JButton("5");
        JButton b6=new JButton("6");
        JButton b7=new JButton("7");
        JButton b8=new JButton("8");
        JButton b9=new JButton("9");
        JButton b10=new JButton("10");
        JButton b11=new JButton("11");
        JButton b12=new JButton("12");
        JButton b13=new JButton("13");
        JButton b14=new JButton("14");
        JButton b15=new JButton("15");
        JButton b16=new JButton("16");
        JButton b17=new JButton("17");
        JButton b18=new JButton("18");
        JButton b19=new JButton("19");
        JButton b20=new JButton("20");
        JButton b21=new JButton("21");
        JButton b22=new JButton("22");
        JButton b23=new JButton("23");
        JButton b24=new JButton("24");

/*
        applicationPanel[0][0].add(b1);
        applicationPanel[1][0].add(b2);
        applicationPanel[0][1].add(application.applicationPanel);

/*
        applicationContainer1.add(application.applicationPanel);


        dtm = (DefaultTableModel) applicationsTable.getModel();
        dtm.setRowCount(4); // instead you can use your textfiled value here
        dtm.setColumnCount(6);
        applicationsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        applicationsTable.setModel(dtm);

       applicationsTable.add(b2);


        this.pack();
        //Application application = new Application();
/*
        applicationsPanel.setLayout(new GridLayout(rowCount,columnCount));
        //applicationsPanel.add(application.applicationPanel);
        applicationsPanel.add(JPanel);
        applicationsPanel.add(b2);
        applicationsPanel.add(b3);
        applicationsPanel.add(b4);
        applicationsPanel.add(b5);
        applicationsPanel.add(b6);
        applicationsPanel.add(b7);
        applicationsPanel.add(b8);
        applicationsPanel.add(b9);
        applicationsPanel.add(b10);
        applicationsPanel.add(b11);
        applicationsPanel.add(b12);
        applicationsPanel.add(b13);
        applicationsPanel.add(b14);
        applicationsPanel.add(b15);
        applicationsPanel.add(b16);
        applicationsPanel.add(b17);
        applicationsPanel.add(b18);
        applicationsPanel.add(b19);
        applicationsPanel.add(b20);
        applicationsPanel.add(b21);
        applicationsPanel.add(b22);
        applicationsPanel.add(b23);
        applicationsPanel.add(b24);


        //applicationsPanel.add(gridLayout);
      /*
        dtm = (DefaultTableModel) applicationsTable.getModel();
        dtm.setRowCount(4); // instead you can use your textfiled value here
        dtm.setColumnCount(6);
       */
        //dtm.setRowCount(Integer.parseInt((txtFieldName.getText()));
        //tabel1.setModel(dtm);

        //applicationsTable.setDropMode();
        //applicationsTable.row

        mainFrame.tabbedApplications.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                if (mainFrame.tabbedApplications.getTabCount() == 2) {
                    header.xButton.setVisible(false);
                } else {
                    header.xButton.setVisible(true);
                }
            }
        });

        header.xButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int confirmDialog = JOptionPane.showConfirmDialog(applicationsPanel, "Deleting this applicaion tab will remove all of your collected applications from the database, " +
                        "although you can add the applications and new tab later if you want. " +
                        "Are you sure to delete this tab and containing applications from the database? ", "ATTENTION", JOptionPane.YES_NO_OPTION);

                if (mainFrame.tabbedApplications.getTabCount() > 2 && confirmDialog == JOptionPane.YES_OPTION) {

                    mainFrame.tabbedApplications.remove(applicationsTab);

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

    }

    private void createApplicationsTable(int rowCount, int columnCount){

        //Hozzáadni az applicationPanel-t  ami többrétegű és fel van konfigurálva mous over ... háttérkép logika
        applicationsPanel.setLayout(new GridLayout(rowCount,columnCount));
        //applicationPanel = new JPanel[rowCount][columnCount];
        for(int i = 0; i < rowCount; i++){
            for(int j = 0; j < columnCount; j++) {

                Application application = new Application();
                application.row = i;
                application.column = j;
                applicationsPanel.add(application.applicationPanel);
               /* applicationPanel[i][j] = new JPanel();
                applicationPanel[i][j].setLayout(new CardLayout());
                applicationsPanel.add(applicationPanel[i][j]);

                */
            }
        }

    }



}
