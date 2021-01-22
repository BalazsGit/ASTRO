package astro;

import com.formdev.flatlaf.icons.*;
import com.formdev.flatlaf.ui.FlatArrowButton;
import util.FlatTabbedPaneAddIcon;
import util.RotateLabel;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.io.*;

import java.util.Scanner;

import static astro.Main.*;

public class Application extends JFrame{


    public JPanel applicationPanel;
    private JPanel defaultBackground;
    private JPanel applicationBackground;
    private JPanel applicationMenu;
    private JPanel cardPanel;
    private JLabel startButton;
    private JLabel stopButton;
    private JLabel addButton;
    private JLabel deleteButton;
    private JLabel backgroundLabel;
    private JPanel leftMenu;
    private JPanel rightMenu;
    private JLabel settingsButton;
    private JButton closeButton;
    private JMenuItem newMenuItem;
    private JPanel menuPanel;
    private JLayeredPane jLayeredPanel;
    public Component applicationsTab;
    public int row;
    public int column;
    public FlatFileChooserHomeFolderIcon flatFileChooserHomeFolderIcon;

    private Color color;

    public String applicationURL;





    public Application(){

        this.add(applicationPanel);
        cardPanel.add(defaultBackground);
        cardPanel.add(applicationBackground);
        cardPanel.add(applicationMenu);

        FlatTabbedPaneAddIcon flatTabbedPaneAddIcon = new FlatTabbedPaneAddIcon();
        backgroundLabel.setIcon(flatTabbedPaneAddIcon);

        cardPanel.setComponentZOrder(applicationMenu,0);
        cardPanel.setComponentZOrder(applicationBackground,2);
        cardPanel.setComponentZOrder(defaultBackground,1);
        applicationMenu.setVisible(false);
        flatFileChooserHomeFolderIcon = new FlatFileChooserHomeFolderIcon();
        //flatFileChooserHomeFolderIcon.paintIcon(applicationMenu, applicationMenu.getGraphics());
        FlatMenuArrowIcon flatMenuArrowIcon = new FlatMenuArrowIcon();
        FlatMenuItemArrowIcon flatMenuItemArrowIcon = new FlatMenuItemArrowIcon();
        FlatWindowCloseIcon flatWindowCloseIcon = new FlatWindowCloseIcon();
        FlatTreeClosedIcon flatTreeClosedIconn = new FlatTreeClosedIcon();
        FlatInternalFrameCloseIcon flatInternalFrameCloseIcon = new FlatInternalFrameCloseIcon();
        FlatCheckBoxMenuItemIcon flatCheckBoxMenuItemIcon = new FlatCheckBoxMenuItemIcon();
        FlatTabbedPaneCloseIcon flatTabbedPaneCloseIcon = new FlatTabbedPaneCloseIcon();
        FlatTreeOpenIcon flatTreeOpenIcon = new FlatTreeOpenIcon();

        //direction 1: up 3: right 5: 7:

        FlatArrowButton flatArrowButton = new FlatArrowButton(3, "triangle", UIManager.getColor("Panel.foreground"), UIManager.getColor("Panel.disabledForeground"), UIManager.getColor("Panel.hoverForeground"), UIManager.getColor("Panel.hoverBackground"), UIManager.getColor("Panel.pressedForeground"), UIManager.getColor("Panel.pressedBackground"));

        startButton.setText(play1);
        stopButton.setText(stop1);
        settingsButton.setText(settings1);
        addButton.setText(plus1);
        deleteButton.setIcon(flatTabbedPaneCloseIcon);

        color = UIManager.getColor ( "Panel.background" );

        cardPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                applicationMenu.setBackground(new Color(color.getRed()+10,color.getGreen()+10,color.getBlue()+10,100));
                if(applicationMenu.isVisible()){
                    applicationMenu.setVisible(false);
                }
                else{
                    applicationMenu.setVisible(true);
                }
                cardPanel.repaint();
                cardPanel.revalidate();
            }
            //defined later?
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                cardPanel.repaint();
                cardPanel.revalidate();
            }
            /*@Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                applicationMenu.setVisible(false);
                cardPanel.repaint();
                cardPanel.revalidate();
            }*/
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                //drag and drop application
                applicationURL="";
            }
        });

        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                startButton.setText(play2);
                applicationMenu.setVisible(true);
                cardPanel.repaint();
                cardPanel.revalidate();

            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                startButton.setText(play1);
                applicationMenu.setVisible(true);
                cardPanel.repaint();
                cardPanel.revalidate();

            }


        });

        stopButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                stopButton.setText(stop2);
                applicationMenu.setVisible(true);
                cardPanel.repaint();
                cardPanel.revalidate();

            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                stopButton.setText(stop1);
                applicationMenu.setVisible(true);
                cardPanel.repaint();
                cardPanel.revalidate();

            }
        });

        settingsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                settingsButton.setText(settings2);
                applicationMenu.setVisible(true);
                cardPanel.repaint();
                cardPanel.revalidate();

            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                settingsButton.setText(settings1);
                applicationMenu.setVisible(true);
                cardPanel.repaint();
                cardPanel.revalidate();

            }
        });

        addButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                applicationMenu.setVisible(true);
                cardPanel.repaint();
                cardPanel.revalidate();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                applicationMenu.setVisible(true);
                cardPanel.repaint();
                cardPanel.revalidate();
            }
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                browse();
            }
        });
/*
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                browse();

            }
        });
*/
        deleteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                //applicationMenu.setBackground(new Color(color.getRed(),color.getGreen(),color.getBlue(),100));
                applicationMenu.setVisible(true);
                cardPanel.repaint();
                cardPanel.revalidate();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                applicationMenu.setVisible(true);
                cardPanel.repaint();
                cardPanel.revalidate();
            }
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                JOptionPane.showConfirmDialog(applicationPanel, "Are you sure to delete this application from the database?", "ATTENTION", JOptionPane.YES_NO_OPTION);
            }
        });
/*
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showConfirmDialog(applicationPanel, "Are you sure to delete this application from the database?", "ATTENTION", JOptionPane.YES_NO_OPTION);

            }
        });

 */
    }

        public void launch(){


        }

        public void delete(){


        }

        public void move(){


        }

        public void create(){


        }

        public void browse(){

/*
            // If the user selects a file
            if (r == javax.swing.JFileChooser.APPROVE_OPTION) {
                // Set the label to the path of the selected directory
                File fi = new File(j.getSelectedFile().getAbsolutePath());

                absolutePathField.setText(fi.getAbsolutePath());

                String path = fi.getAbsolutePath();
                String base = "./";
                String relative = new File(base).toURI().relativize(new File(path).toURI()).getPath();

                relativePathField.setText(relative);


 */

            final JFileChooser fc = new JFileChooser("./");
            fc.showOpenDialog(null);


            try {
                // Open an input stream
                Scanner reader = new Scanner(fc.getSelectedFile());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

        public void rename(){


        }

        public void showName(){


        }

        public void showPicture(){


        }

        public void selectPicture(){


        }

}

