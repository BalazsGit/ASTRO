package util;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class RotateLabel extends JLabel {

    private double angle;

    public RotateLabel(String text, double angle) {

        super(text);
        this.angle = angle;
        Font font = new Font(this.getFont().getFontName(),this.getFont().getStyle(),this.getFont().getSize());
        FontMetrics metrics = new FontMetrics(font){};
        Rectangle2D bounds = metrics.getStringBounds(text, null);
        setBounds(0, 0, (int) bounds.getWidth(), (int) bounds.getHeight());
    }
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D gx = (Graphics2D) g;
        gx.rotate(this.angle, getWidth()/2, getHeight()/2);
        super.paintComponent(g);
    }
}
