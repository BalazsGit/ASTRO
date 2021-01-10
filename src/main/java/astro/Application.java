package astro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Application extends JFrame{


    public JButton applicationBrowser;
    public JPanel applicationPanel;
    private JButton blueButton;
    private JButton greenButton;
    private JButton redButton;
    private JPanel red;
    private JPanel green;
    private JPanel blue;
    private JPanel cardPanel;
    private JPanel jPanel;
    private JLayeredPane jLayeredPanel;
    public Component applicationsTab;
    public int row;
    public int column;

    public Application(){

        this.add(applicationPanel);

        applicationBrowser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                browse();

            }
        });
        redButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //cardPanel.removeAll();
                cardPanel.add(red);
                cardPanel.add(green);
                cardPanel.add(blue);
                red.setVisible(true);
                green.setVisible(true);
                blue.setVisible(true);
                red.setBackground(new Color(100,100,100,100));
                cardPanel.repaint();
                cardPanel.revalidate();

            }
        });
        greenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //cardPanel.removeAll();
                cardPanel.remove(red);
                cardPanel.repaint();
                cardPanel.revalidate();

            }
        });
        blueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                cardPanel.removeAll();
                cardPanel.add(blue);
                cardPanel.repaint();
                cardPanel.revalidate();

            }
        });
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

