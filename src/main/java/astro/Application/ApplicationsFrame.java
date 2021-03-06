package astro.Application;

import astro.MainFrame;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

import static astro.Main.browserFocus_;

public class ApplicationsFrame extends JFrame {

    public JPanel applicationsPanel;
    private JPanel applicationContainer1;
    public JTable applicationsTable;
    public ApplicationsTabHeader header;
    public Component applicationsTab;
    public DefaultTableModel dtm;
    public GridLayout gridLayout;
    public String applicationsFrameName = null;

    JPanel[][] applicationPanel;
    private int rowCount = 4;
    private int columnCount = 6;
    private int applicationCount = 0;
    private int index;
    private int tabCount;

    void setApplicationsFrameName(String applicationsFrameName){
        this.applicationsFrameName = applicationsFrameName;
    }
    public String getApplicationsFrameName(){
        return this.applicationsFrameName;
    }

    public ApplicationsFrame(MainFrame mainFrame, ApplicationsTabHeader header){

        //this.add(applicationsPanel);
        this.header = header;
        //setter and getter creation
        setApplicationsFrameName(header.nameTextField.getText());

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

        header.nameTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);

                if (!browserFocus_) return;
                browserFocus_ = false;
                KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();

                header.nameTextField.grabFocus();
                header.nameTextField.requestFocus();
            }
        });

        header.xButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                int confirmDialog = JOptionPane.showConfirmDialog(applicationsPanel, "Are you sure to delete this tab and containing applications from the database? ", "ATTENTION", JOptionPane.YES_NO_OPTION);

                if (mainFrame.tabbedApplications.getTabCount() > 2 && confirmDialog == JOptionPane.YES_OPTION) {

                    mainFrame.tabbedApplications.remove(applicationsTab);

                }

                //if the last close tab is selected, than give the focus to the one left browser panel
                index = mainFrame.tabbedApplications.getSelectedIndex();
                tabCount = mainFrame.tabbedApplications.getTabCount();

                if (index == tabCount - 1) {

                    mainFrame.tabbedApplications.setSelectedIndex(index - 1);
                    mainFrame.tabbedApplications.getSelectedComponent().requestFocus();

                }
            }
        });

        //save new application name
        header.nameTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                header.nameJLabel.setText(header.nameTextField.getText());
                setApplicationsFrameName(header.nameTextField.getText());
                header.nameTextFieldJPanel.setVisible(false);
                header.nameJLabelJPanel.setVisible(true);
            }
        });

        //save new application name
        header.nameTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                mainFrame.tabbedApplications.setSelectedComponent(applicationsTab);
                if (e.getClickCount() %2 == 0 && !e.isConsumed()) {
                    e.consume();
                    //handle double click event
                    header.nameJLabel.setText(header.nameTextField.getText());
                    header.nameTextFieldJPanel.setVisible(false);
                    header.nameJLabelJPanel.setVisible(true);
                }
            }
        });

        //change application name
        header.nameJLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                mainFrame.tabbedApplications.setSelectedComponent(applicationsTab);
                if (e.getClickCount() %2 == 0 && !e.isConsumed()) {
                    e.consume();
                    //handle double click event
                    header.nameTextField.setText(header.nameJLabel.getText());
                    header.nameTextFieldJPanel.setVisible(true);
                    header.nameJLabelJPanel.setVisible(false);
                }
            }
        });

        //change aapplicationTab
        header.nameJLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mouseClicked(e);
                mainFrame.tabbedApplications.setSelectedComponent(applicationsTab);
            }
        });

        /*
        //later need log press to modify application name
        header.applicationsFrameNameJLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                header.setApplicationsFrameNameJPanel.setVisible(true);
                header.applicationsFrameNameJPanel.setVisible(false);
            }
        });
         */

    }



    private void createApplicationsTable(int rowCount, int columnCount){

        GridLayout gridLayout = new GridLayout(rowCount,columnCount);
        applicationsPanel.setLayout(gridLayout);
        //applicationPanel = new JPanel[rowCount][columnCount];
        for(int i = 0; i < rowCount; i++){
            for(int j = 0; j < columnCount; j++) {

                Application application = new Application(applicationsPanel);
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
