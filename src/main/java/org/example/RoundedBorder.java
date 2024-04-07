package org.example;

import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedBorder extends AbstractBorder {
    private final Color borderColor;
    private final int cornerRadius;

    public RoundedBorder(Color borderColor, int cornerRadius) {
        this.borderColor = borderColor;
        this.cornerRadius = cornerRadius;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setColor(borderColor);
        g2d.draw(new RoundRectangle2D.Double(x, y, width - 1, height - 1, cornerRadius, cornerRadius));

        g2d.dispose();
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(cornerRadius, cornerRadius, cornerRadius, cornerRadius);
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        insets.left = insets.top = insets.right = insets.bottom = cornerRadius;
        return insets;
    }
}
