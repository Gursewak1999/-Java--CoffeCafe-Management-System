package UI;

import UI.custom.JImagePanel;
import UI.custom.MaterialCardView;
import helper.ColorUtils;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SidebarItems extends JPanel {

    JLabel label;

    public SidebarItems(String text, String icon_path) {
        label = new JLabel();
        label.setText(text);
        label.setForeground(Color.WHITE);
        Font font = new Font("Poppins",Font.PLAIN,13);
        label.setFont(font);
        label.setBorder(new EmptyBorder(10, 10, 10, 10));

        setBackground(ColorUtils.sidebar_background);
        //this.setBackgroundImage("res/drawables/button_bg.png");
        //this.setOpaque(false);

        this.setLayout(new CardLayout());
        this.add(label);
        ImageIcon icon = new ImageIcon("res/" + icon_path);
        Image image = icon.getImage(); // transform it
        Image newimg = image.getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH);// scale it the smooth way
        ImageIcon imageIcon = new ImageIcon(newimg);
        label.setIcon(imageIcon);
    }

    public JLabel setText(String text) {
        label.setText(text);
        return label;
    }
}
