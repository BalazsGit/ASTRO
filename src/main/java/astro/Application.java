package astro;

import com.formdev.flatlaf.icons.*;
import com.formdev.flatlaf.ui.FlatArrowButton;
import util.FlatTabbedPaneAddIcon;
import util.RotateLabel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
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
    private JLabel deleteButton;
    private JLabel backgroundLabel;
    private JPanel bottomMenu;
    private JLabel settingsButton;
    private JLabel applicationName;
    private JLabel applicationImage;
    private JPanel namePanel;
    private JLabel img;
    private JMenu add;
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
    public String applicationAbsolutePath;
    public String applicationRelativePath;
    public String applicationImagePath = null;
    public File applicationDefaultImage;

    public BufferedImage bufferedImage = null;
    public ImageIcon imageicon = null;

    public int applicationType = 0; //0 = undefined | 1 = runnable | 2 = Console | 3 = text | 4 = web
    public int state = 3; //0 = not running | 1 = running | 2 = stopped | 3 = empty





    public Application(){

        this.add(applicationPanel);
        cardPanel.add(defaultBackground);
        cardPanel.add(applicationBackground);
        cardPanel.add(applicationMenu);

        //put to class variables?
        //BufferedImage bufferedImage = null;
        //ImageIcon imageicon = new ImageIcon();
        //imageicon = null;
        imageicon = new ImageIcon();

        applicationDefaultImage = new File("./PROJECT/CONFIG/images/applicationImage.png");
        try {
            bufferedImage = ImageIO.read(applicationDefaultImage);
            //imageicon.setImage(bufferedImage);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        //Image dimg = bufferedImage.getScaledInstance(applicationImage.getWidth(), applicationImage.getHeight(), Image.SCALE_SMOOTH);
        //resize image
        if(bufferedImage != null) {
            Image dimg = bufferedImage.getScaledInstance(100, 50, Image.SCALE_SMOOTH);
            imageicon.setImage(dimg);
        }
        else{
            imageicon = null;
        }


        //ImageIcon imageicon = new ImageIcon();
        FlatTabbedPaneAddIcon flatTabbedPaneAddIcon = new FlatTabbedPaneAddIcon();
        backgroundLabel.setIcon(flatTabbedPaneAddIcon);

        cardPanel.setComponentZOrder(applicationMenu,0);
        cardPanel.setComponentZOrder(applicationBackground,1);
        cardPanel.setComponentZOrder(defaultBackground,2);
        if(applicationType == 0){
            applicationBackground.setVisible(false);

            applicationImage.setIcon(imageicon);

            //cardPanel.setComponentZOrder(defaultBackground,1);
        }
        else {
            applicationBackground.setVisible(true);
            //cardPanel.setComponentZOrder(defaultBackground, 2);
            //cardPanel.setComponentZOrder(applicationBackground, 1);
            //Set image later

            if (applicationImagePath == null) {
                //JOptionPane.showConfirmDialog(applicationPanel, "Add new Application?", "ATTENTION1", JOptionPane.YES_NO_OPTION);
                applicationImage.setIcon(imageicon);
            }
            else{
                //JOptionPane.showConfirmDialog(applicationPanel, "Add new Application?", "ATTENTION2", JOptionPane.YES_NO_OPTION);
                applicationImage.setIcon(imageicon);
            }


        }




        defaultBackground.setVisible(true);
        //applicationBackground.setVisible(true);
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
        deleteButton.setIcon(flatTabbedPaneCloseIcon);

        color = UIManager.getColor ( "Panel.background" );



        cardPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
           /*     applicationMenu.setBackground(new Color(color.getRed()+10,color.getGreen()+10,color.getBlue()+10,100));
                if(applicationMenu.isVisible()){
                    applicationMenu.setVisible(false);
                }
                else{
                    applicationMenu.setVisible(true);
                }
                cardPanel.repaint();
                cardPanel.revalidate();
          */  }


            //defined later?
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
               // cardPanel.repaint();
               // cardPanel.revalidate();
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

        //click to background + = add new empty application
        backgroundLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);


            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);


            }
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                //String a =  getClass().getClassLoader().getResource("./PROJECT/CONFIG/images/set_name1.png").toString();
                //String a = imageicon.getClass().getResource("./").toString();

                //JOptionPane.showConfirmDialog(applicationPanel, a, "ATTENTION", JOptionPane.YES_NO_OPTION);

                int comfirmation = JOptionPane.showConfirmDialog(applicationPanel, "Add new Application?", "ATTENTION", JOptionPane.YES_NO_OPTION);
                if(comfirmation == JOptionPane.YES_OPTION){
                    applicationBackground.setVisible(true);
                    defaultBackground.setVisible(false);
                    applicationImage.setIcon(imageicon);
                }
                else{
                    defaultBackground.setVisible(true);
                    applicationBackground.setVisible(false);
                }
                //applicationBackground.setSize(100,100);
                cardPanel.repaint();
                cardPanel.revalidate();
            }

        });

        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                startButton.setText(play2);
               /*
                applicationMenu.setVisible(true);
                cardPanel.repaint();
                cardPanel.revalidate();
*/
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                startButton.setText(play1);

              /*  applicationMenu.setVisible(true);
                cardPanel.repaint();
                cardPanel.revalidate();
*/
            }


        });

        stopButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);

                stopButton.setText(stop2);
    /*            applicationMenu.setVisible(true);
                cardPanel.repaint();
                cardPanel.revalidate();

*/

            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                stopButton.setText(stop1);
            /*    applicationMenu.setVisible(true);
                cardPanel.repaint();
                cardPanel.revalidate();
*/
            }
        });

        settingsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);

                settingsButton.setText(settings2);
               /* applicationMenu.setVisible(true);
                cardPanel.repaint();
                cardPanel.revalidate();


                 */
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);

                settingsButton.setText(settings1);
               /*  applicationMenu.setVisible(true);
                cardPanel.repaint();
                cardPanel.revalidate();
*/
            }
        });
/*
        //add new application card
        addButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                //applicationMenu.setVisible(true);
                //cardPanel.repaint();
                //cardPanel.revalidate();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                //applicationMenu.setVisible(true);
                //cardPanel.repaint();
                //cardPanel.revalidate();
            }
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                //applicationType 0 = undefined | 1 = runnable | 2 = Terminal | 3 = text  | 4 = web
                //select type
                //JOptionPane.showConfirmDialog(applicationPanel, "Are you sure to delete this application from the database?", "ATTENTION", JOptionPane.YES_NO_OPTION);


                JOptionPane plainOptionPane = new JOptionPane();
                JDialog jDialog = plainOptionPane.createDialog(applicationPanel, "New Application");
                plainOptionPane.setMessage("Select application type:");
                //here the selectable application types
                jDialog.show();

                //int confirmDialog = JOptionPane.showInputDialog(applicationPanel, "Are you sure to delete this application from the database?", "SELECT APPLICATION TYPE", JOptionPane.YES_NO_OPTION);
                //applicationType = 1;
                browseApplication();

            }
        });
*/

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
                //applicationMenu.setVisible(true);
                //cardPanel.repaint();
                //cardPanel.revalidate();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                //applicationMenu.setVisible(true);
                //cardPanel.repaint();
                //cardPanel.revalidate();
            }
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                int comfirmation = JOptionPane.showConfirmDialog(applicationPanel, "Are you sure to delete this application from the database?", "ATTENTION", JOptionPane.YES_NO_OPTION);

                if(comfirmation == JOptionPane.YES_OPTION){

                    //If  delete application, than set applicationType = 0
                    applicationType = 0;
                    applicationBackground.setVisible(false);
                    defaultBackground.setVisible(true);

                }

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

        public void browseApplication() {

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

            final JFileChooser jFileChooser = new JFileChooser("./");
            int r = jFileChooser.showOpenDialog(null);

            // If the user selects a file
            if (r == javax.swing.JFileChooser.APPROVE_OPTION) {
                // Set the label to the path of the selected directory
                applicationAbsolutePath = jFileChooser.getSelectedFile().getAbsolutePath();

                String base = "./";
                applicationRelativePath = new File(base).toURI().relativize(new File(applicationAbsolutePath).toURI()).getPath();

                applicationType = 1;
                applicationBackground.setVisible(true);
                //cardPanel.setComponentZOrder(defaultBackground, 2);
                //cardPanel.setComponentZOrder(applicationBackground, 1);
                cardPanel.repaint();
                cardPanel.revalidate();

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

