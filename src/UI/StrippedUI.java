package UI;

import UI.custom.VerticleJLabel;
import helper.ColorUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class StrippedUI extends JPanel {


    public static JPanel contentPane;
    private static JPanel lay;

    public StrippedUI(int width, int height) {

        //width = width + 30;
        this.setPreferredSize(new Dimension(width, height));
        this.setSize(new Dimension(width, height));

        contentPane = new JPanel();
        contentPane.setPreferredSize(new Dimension(width - 30, height));
        contentPane.setBackground(ColorUtils.background);
        contentPane.setLayout(new CardLayout());

        VerticleJLabel secondaryText = new VerticleJLabel("COFFEE CAFE");
        secondaryText.addMouseListener(onSecondaryTextClick());
        secondaryText.setSize(300, getHeight());
        secondaryText.revalidate();
        JPanel sidePane = new JPanel();
        sidePane.setPreferredSize(new Dimension(30, height));
        sidePane.setLayout(new BorderLayout());
        sidePane.setBackground(ColorUtils.backgroundSecondary);
        sidePane.add(secondaryText, BorderLayout.CENTER);
        secondaryText.setBackground(Color.WHITE);

        setLayout(new GridBagLayout());
        add(contentPane);
        add(sidePane);

        //this.setContentPane(new LoginUI(contentPane.getWidth(), contentPane.getHeight()));
    }

    private MouseListener onSecondaryTextClick() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };
    }

    public void setContentPane(JPanel layout) {

        // layout.setBackground(this.getBackground());
        if (lay != null)
            contentPane.remove(lay);
        lay = layout;
        contentPane.add(lay);
        contentPane.updateUI();
        revalidate();
    }

}
