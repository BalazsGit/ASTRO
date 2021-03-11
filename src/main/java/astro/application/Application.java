package astro.application;

import astro.settings.ApplicationSettings;
import com.formdev.flatlaf.icons.*;
import com.formdev.flatlaf.ui.FlatArrowButton;
import util.FlatTabbedPaneAddIcon;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;

import java.time.LocalDateTime;

import static astro.Main.*;

public class Application extends JFrame{


    public JPanel applicationPanel;
    private JPanel defaultBackground;
    public JPanel applicationBackground;
    private JPanel applicationMenu;
    public JPanel cardPanel;
    private JLabel startButton;
    private JLabel stopButton;
    private JLabel deleteButton;
    private JLabel backgroundLabel;
    private JPanel bottomMenu;
    private JLabel settingsButton;
    private JLabel applicationName;
    public JLabel applicationImage;
    private JPanel namePanel;
    private JPanel applicationImagePanel;
    private JLabel img;
    private JMenu add;
    private JButton closeButton;
    private JMenuItem newMenuItem;
    private JPanel menuPanel;
    private JLayeredPane jLayeredPanel;
    public Component applicationsPanel;
    public int row;
    public int column;
    public int tab;
    public FlatFileChooserHomeFolderIcon flatFileChooserHomeFolderIcon;

    private Color color;

    public String applicationURL;
    public String applicationAbsolutePath = null;
    public String applicationRelativePath = null;
    public File applicationFile = null;
    public String applicationImageRelativePath = null;
    public String applicationImageAbsolutePath = null;
    public String applicationDefaultImagePath = null;
    public File applicationDefaultImage;
    public String applicationID;
    public ApplicationSettings applicationSettings;

    private float applicationImagePanelAspectRatio;
    private float imageAspectRatio;



    public BufferedImage bufferedImage = null;
    private Image image = null;
    public ImageIcon imageicon = null;
    public ImageIcon applicationPreview = null;

    public int applicationType = 0; //0 = Undefined | 1 = Graphical | 2 = Console | 3 = Text | 4 = Web
    public int state = 3; //0 = not running | 1 = running | 2 = stopped | 3 = empty

    public void setApplicationFile(){
        applicationFile = new File(applicationRelativePath);
    }

    public void setApplicationType(int type){
        applicationType = type;
    }

    public int getApplicationType(){
        return applicationType;
    }

    private void addApplication(){
        setApplicationImageIcon();
        applicationBackground.setVisible(true);
        defaultBackground.setVisible(false);
        applicationImage.setIcon(imageicon);
    }

    private void deleteApplication(){
        applicationBackground.setVisible(false);
        defaultBackground.setVisible(true);
    }
public int getApplicationImagePanelHeight(){
        //calculation of applicationImagePanel height
      //return applicationPanel.getHeight() - applicationName.getHeight() - bottomMenu.getHeight();
    return cardPanel.getHeight() - applicationName.getHeight() - bottomMenu.getHeight();
}

public void setApplicationImageIcon() {
    pack();
    applicationDefaultImage = new File(applicationImageRelativePath);
    try {
        bufferedImage = ImageIO.read(applicationDefaultImage);
    } catch (IOException ioException) {
        ioException.printStackTrace();
    }
    //resize image
    if (bufferedImage != null) {
        applicationImagePanelAspectRatio = (float)applicationPanel.getWidth() / (float)getApplicationImagePanelHeight();
        imageAspectRatio = (float)bufferedImage.getWidth() / (float)bufferedImage.getHeight();
        if(applicationImagePanelAspectRatio <= imageAspectRatio){
            //fit image to applicationPanel width
            image = bufferedImage.getScaledInstance(applicationPanel.getWidth(), (int)(applicationPanel.getWidth() / imageAspectRatio), Image.SCALE_SMOOTH);
        }
        else{
            //fit image to applicationPanel height
            image = bufferedImage.getScaledInstance((int)(getApplicationImagePanelHeight() * imageAspectRatio), getApplicationImagePanelHeight(), Image.SCALE_SMOOTH);
        }
        imageicon.setImage(image);
    } else {
        imageicon = null;
    }
    //redraw and align the image to the right position after click window resize button
    applicationImagePanel.setSize(this.getWidth(), this.getHeight());
}

public void setApplicationPreview() {

    //display application preview in settings panel
    pack();
    Component c = applicationPanel;
    BufferedImage im = new BufferedImage(c.getWidth(), c.getHeight(), BufferedImage.TYPE_INT_ARGB);
    c.paint(im.getGraphics());
    applicationPreview.setImage(im);
}

public ImageIcon getApplicationPreview() {

        return applicationPreview;
}

    private void showApplicationSettings () {

        setApplicationPreview();

        // set applicationSettings Panel dimension
        // min width 800px
        // min height 600px
        Dimension dimension = new Dimension();
        dimension.setSize(800 > 4 * applicationPanel.getWidth() ? 800 : 4 * applicationPanel.getWidth(), 600 > 3 * applicationPanel.getHeight() ? 600 : 3 * applicationPanel.getHeight());
        applicationSettings.applicationSettings.setPreferredSize(dimension);

        //put center of the screen and set components minimum size like application and others

        //JOptionPane.showMessageDialog(applicationsPanel, applicationSettings.getWidth() + " " + applicationSettings.getHeight(), "Application Settings", JOptionPane.CLOSED_OPTION);

        JOptionPane.showMessageDialog(applicationsPanel, applicationSettings.applicationSettings, "Application Settings", JOptionPane.CLOSED_OPTION);

        cardPanel.repaint();
        cardPanel.revalidate();
    }

    public Application(Component applicationsPanel){

        this.add(applicationPanel);
        cardPanel.add(defaultBackground);
        cardPanel.add(applicationBackground);
        cardPanel.add(applicationMenu);
        applicationID = LocalDateTime.now().toString();

        this.applicationsPanel = applicationsPanel;

        //put to class variables?
        //BufferedImage bufferedImage = null;
        //ImageIcon imageicon = new ImageIcon();
        //imageicon = null;
        imageicon = new ImageIcon();
        applicationPreview = new ImageIcon();
        applicationImageRelativePath = "PROJECT/IMAGES/applicationImage.png";
        applicationDefaultImage = new File(applicationImageRelativePath);
        applicationImageAbsolutePath = applicationDefaultImage.getAbsolutePath();
        //setApplicationImageIcon();


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

            if (applicationImageRelativePath == null) {
                //JOptionPane.showConfirmDialog(applicationPanel, "Add new Application?", "ATTENTION1", JOptionPane.YES_NO_OPTION);
                applicationImage.setIcon(imageicon);
            }
            else{
                //JOptionPane.showConfirmDialog(applicationPanel, "Add new Application?", "ATTENTION2", JOptionPane.YES_NO_OPTION);
                applicationImage.setIcon(imageicon);
            }


        }

        applicationSettings = new ApplicationSettings(this);

        defaultBackground.setVisible(true);
        //applicationBackground.setVisible(true);
        applicationMenu.setVisible(false);

        flatFileChooserHomeFolderIcon = new FlatFileChooserHomeFolderIcon();
        //flatFileChooserHomeFolderIcon.paintIcon(applicationMenu, applicationMenu.getGraphics());
        FlatMenuArrowIcon flatMenuArrowIcon = new FlatMenuArrowIcon();
        FlatMenuItemArrowIcon flatMenuItemArrowIcon = new FlatMenuItemArrowIcon();
        FlatWindowCloseIcon flatWindowCloseIcon = new FlatWindowCloseIcon();
        FlatTreeClosedIcon flatTreeClosedIcon = new FlatTreeClosedIcon();
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

        //setapplicationPreview();

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
                super.mouseClicked(e);
                //String a =  getClass().getClassLoader().getResource("./PROJECT/CONFIG/images/set_name1.png").toString();
                //String a = imageicon.getClass().getResource("./").toString();

                //JOptionPane.showConfirmDialog(applicationPanel, a, "ATTENTION", JOptionPane.YES_NO_OPTION);

                int confirmation = JOptionPane.showConfirmDialog(applicationPanel, "Add new Application?", "ATTENTION", JOptionPane.YES_NO_OPTION);
                if(confirmation == JOptionPane.YES_OPTION){
                    addApplication();
                }
                else{
                    deleteApplication();
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
                //should change only size, or color, or position instead
                startButton.setText(play1);

              /*  applicationMenu.setVisible(true);
                cardPanel.repaint();
                cardPanel.revalidate();
*/
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                //JOptionPane.showMessageDialog(applicationsPanel, applicationSettings.applicationSettings, "Settings", JOptionPane.CLOSED_OPTION);
                //JOptionPane.showMessageDialog(applicationsPanel, applicationRelativePath, "Settings", JOptionPane.CLOSED_OPTION);

                if(applicationRelativePath != null) {

                    applicationFile = new File(applicationRelativePath);
                    Desktop desktop = Desktop.getDesktop();
                    switch (applicationType){
                        case 1:
                            try {
                                desktop.open(applicationFile);
                            } catch (Exception exception) {
                                int confirmation = JOptionPane.showConfirmDialog(applicationPanel, "Please check the application settings.\n" +
                                    "Would you like to open application settings panel?", "Application Launch ERROR", JOptionPane.YES_NO_OPTION);
                                exception.printStackTrace();
                                if (confirmation == JOptionPane.YES_OPTION) {
                                    showApplicationSettings();
                                }
                            }
                            break;
                        case 2:
                            try {
                                //windows console
                                setApplicationFile();
                                Runtime.getRuntime().exec("cmd.exe /c start " + applicationFile.getName(), null, applicationFile.getParentFile());
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            break;
                        default:
                            break;
                            /*
                            try {
                                desktop.open(applicationFile);
                            } catch (Exception exception) {
                                int confirmation = JOptionPane.showConfirmDialog(applicationPanel, "Please check the application settings.\n" +
                                    "Would you like to open application settings panel?", "Application Launch ERROR", JOptionPane.YES_NO_OPTION);
                                exception.printStackTrace();
                                if (confirmation == JOptionPane.YES_OPTION) {
                                    showApplicationSettings();
                                }
                            }

                             */

                    }
                }
                else{
                    int confirmation = JOptionPane.showConfirmDialog(applicationPanel, "There is no application path added!\nWould you like to set the application path?", "ATTENTION", JOptionPane.YES_NO_OPTION);
                    if(confirmation == JOptionPane.YES_OPTION){
                        showApplicationSettings();
                    }
                    else{
                    }
                }
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
            @Override
            public void mousePressed(MouseEvent e) {
                super.mouseExited(e);

                settingsButton.setText(settings1);
                showApplicationSettings();
                /*
                if(confirmation == JOptionPane.YES_OPTION){
                    applicationBackground.setVisible(true);
                    defaultBackground.setVisible(false);
                    applicationImage.setIcon(imageicon);
                }

                 */
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
                int confirmation = JOptionPane.showConfirmDialog(applicationPanel, "Are you sure to delete this application from the database?", "ATTENTION", JOptionPane.YES_NO_OPTION);

                if(confirmation == JOptionPane.YES_OPTION){

                    //If  delete application, than set applicationType = 0
                    applicationType = 0;
                    applicationBackground.setVisible(false);
                    defaultBackground.setVisible(true);

                }

            }
        });

        applicationPanel.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                setApplicationImageIcon();
                /*applicationPanel.repaint();
                applicationPanel.revalidate();
                applicationImagePanel.repaint();
                applicationImage.repaint();

                 */
            }
        });

        cardPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                cardPanel.requestFocus();
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

