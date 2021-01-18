package util;

    import javax.swing.*;
    import java.awt.*;
    import java.awt.geom.AffineTransform;
    import java.awt.geom.Rectangle2D;

public class HorizontalMirrorLabel extends JLabel {
    public HorizontalMirrorLabel(String text) {
        super(text);
        Font font = new Font(this.getFont().getFontName(),this.getFont().getStyle(),this.getFont().getSize());
        FontMetrics metrics = new FontMetrics(font){};
        Rectangle2D bounds = metrics.getStringBounds(text, null);
        setBounds(0, 0, (int) bounds.getWidth(), (int) bounds.getHeight());
    }
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D gx = (Graphics2D) g;
        AffineTransform tx = gx.getTransform();
        tx.scale(1.0, -1.0);
        tx.translate(0, -getHeight());
        gx.setTransform(tx);
        super.paintComponent(g);
    }
}
