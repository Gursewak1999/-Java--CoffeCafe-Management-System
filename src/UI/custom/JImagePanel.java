package UI.custom;

import helper.Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class JImagePanel extends JPanel {

    private Image backgroundImage = null;
    private boolean toScale = true;
    private boolean isRounded = false;

    public JImagePanel() {
    }

    public void setBackgroundImage(String resPath) {
        try {
            backgroundImage = ImageIO.read(new File(resPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        toScale = true;
    }

    public void setUnScaledBackgroundImage(String resPath) {
        try {
            backgroundImage = ImageIO.read(new File(resPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        toScale = false;
    }

    public void setBackgroundColor(Color color) {
        setOpaque(false);
        setBackground(color);
    }

    public JLabel setText(String text) {
        JLabel jLabel = new JLabel();
        jLabel.setText(text);
        add(jLabel);
        return jLabel;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null)
            if (toScale)
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            else
                g.drawImage(backgroundImage, 0, 0, this);
    }

    public void setRoundedBackgroundImage(String cover) {
        try {
            BufferedImage image = ImageIO.read(new URL(cover));
            backgroundImage = Utils.makeRoundedCorner(image,60);
        } catch (IOException e) {
            e.printStackTrace();
        }
        toScale = true;
    }
}
