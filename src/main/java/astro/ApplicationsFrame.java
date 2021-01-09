package astro;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApplicationsFrame extends JFrame {

    public JPanel applicationsPanel;
    public JTable applicationsTable;
    public ApplicationsTabHeader header;
    public Component applicationsTab;

    private int index;
    private int tabCount;

    public ApplicationsFrame(MainFrame mainFrame, ApplicationsTabHeader header){

        this.add(applicationsPanel);
        this.header = header;

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


}
