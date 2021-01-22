package util;

import astro.Main;
import com.formdev.flatlaf.icons.FlatAbstractIcon;
import com.formdev.flatlaf.icons.FlatTabbedPaneCloseIcon;
import com.formdev.flatlaf.ui.FlatButtonUI;
import com.formdev.flatlaf.*;
import com.formdev.flatlaf.ui.FlatUIUtils;

import java.awt.geom.Path2D;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import javax.swing.UIManager;

public class FlatTabbedPaneAddIcon extends FlatTabbedPaneCloseIcon {

    protected void paintIcon(Component c, Graphics2D g) {
        Color bg = FlatButtonUI.buttonStateColor(c, this.background, (Color)null, (Color)null, this.hoverBackground, this.pressedBackground);
        if (bg != null) {
            g.setColor(FlatUIUtils.deriveColor(bg, c.getBackground()));
            g.fillRoundRect((this.width - this.size.width) / 2, (this.height - this.size.height) / 2, this.size.width, this.size.height, this.arc, this.arc);
        }

        Color fg = FlatButtonUI.buttonStateColor(c, this.foreground, (Color)null, (Color)null, this.hoverForeground, this.pressedForeground);
        g.setColor(FlatUIUtils.deriveColor(fg, c.getForeground()));
        float mx = (float)(this.width / 2);
        float my = (float)(this.height / 2);
        float r = (bg != null ? this.crossFilledSize : this.crossPlainSize) / 2.0F;
        Path2D path = new Path2D.Float(0);
        path.append(new java.awt.geom.Line2D.Float(mx - r, my - r, mx + r, my + r), false);
        path.append(new java.awt.geom.Line2D.Float(mx - r, my + r, mx + r, my - r), false);
        g.rotate(Math.PI/4, mx, my);
        g.setStroke(new BasicStroke(this.closeCrossLineWidth));
        g.draw(path);
    }
}
