package astro.application;

import com.formdev.flatlaf.icons.FlatTabbedPaneCloseIcon;

import javax.swing.*;

public class ApplicationsTabHeader extends JFrame{

    public JPanel headerPanel;
    public JLabel xButton;
    public JLabel nameJLabel;
    public JTextField nameTextField;
    private JPanel cardPanel;
    public JPanel nameTextFieldJPanel;
    public JPanel nameJLabelJPanel;


    public ApplicationsTabHeader(){

        FlatTabbedPaneCloseIcon flatTabbedPaneCloseIcon = new FlatTabbedPaneCloseIcon();
        xButton.setIcon(flatTabbedPaneCloseIcon);
        //setApplicationsFrameNameTextField.setVisible(false);
        //applicationsFrameNameJLabel.setVisible(true);


        cardPanel.add(nameTextFieldJPanel);
        cardPanel.add(nameJLabelJPanel);

        nameTextFieldJPanel.setVisible(false);
        nameJLabelJPanel.setVisible(true);

        cardPanel.setComponentZOrder(nameTextFieldJPanel,0);
        cardPanel.setComponentZOrder(nameJLabelJPanel,1);

    }

}
