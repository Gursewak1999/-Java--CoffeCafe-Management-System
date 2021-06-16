package UI.custom;

import helper.ColorUtils;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class VerticleJLabel extends JLabel {

    public VerticleJLabel(String text) {
        super(text);

        //autoAdjust();
        this.setForeground(ColorUtils.background);
        // setBorder(new EmptyBorder(0,4,0,4));//top,left,bottom,right
        Border border = getBorder();
        Border margin = new EmptyBorder(0, -24, 0, -24);
        setBorder(new CompoundBorder(border, margin));
    }

    private void autoAdjust() {
        Font thisFont = this.getFont();
        String thisText = this.getText();
        int stringWidth = this.getFontMetrics(thisFont).stringWidth(thisText);
        int componentWidth = this.getWidth();

// Find out how much the font can grow in width.
        double widthRatio = (double) componentWidth / (double) stringWidth;

        int newFontSize = (int) (thisFont.getSize() * widthRatio);
        int componentHeight = this.getHeight() * 2;

// Pick a new font size so it will not be larger than the height of this.
        int fontSizeToUse = (newFontSize + componentHeight) / 2;

// Set the this's font size to the newly determined size.
        this.setFont(new Font("Poppins", Font.PLAIN, fontSizeToUse));

    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D gx = (Graphics2D) g;
        gx.rotate(Math.toRadians(-90), getX() + getWidth() / 2, getY() + getHeight() / 2);
        super.paintComponent(g);
    }
}
