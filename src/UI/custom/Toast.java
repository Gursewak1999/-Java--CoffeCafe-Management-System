package UI.custom;

import de.craften.ui.swingmaterial.MaterialWindow;
import helper.ColorUtils;
import helper.Utils;

import javax.swing.*;
import java.awt.*;

public class Toast extends JFrame {

    //String of toast
    String s;

    // JWindow
    MaterialWindow w;

    public Toast(String s, boolean isError) {
        Color color;
        if (isError)
            color = ColorUtils.errorColor;
        else color = ColorUtils.selected;
        w = new MaterialWindow();
        int wid = 600;
        int hei = 20;
        // make the background transparent
        w.setBackground(new Color(0, 0, 0, 0));

        final int[] str_width = {0};
        // create a panel
        JPanel p = new JPanel() {
            public void paintComponent(Graphics g) {
//                int wid = g.getFontMetrics().stringWidth(s);
//                int hei = g.getFontMetrics().getHeight();

                str_width[0] = wid;

                // draw the boundary of the toast and fill it
                g.setColor(color);
                g.fillRect(10, 10, wid + 30, hei + 10);
                g.setColor(color);
                g.drawRect(10, 10, wid + 30, hei + 10);

                // set the color of text
                g.setColor(ColorUtils.background_super_light);
                g.drawString(s, (wid - g.getFontMetrics().stringWidth(s)) / 2, 27);
                int t = 250;

                // draw the shadow of the toast
                for (int i = 0; i < 4; i++) {
                    t -= 60;
                    g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), t));
                    g.drawRect(10 - i, 10 - i, wid + 30 + i * 2,
                            hei + 10 + i * 2);
                }
            }
        };

        int x = (Utils.max_width - wid) / 2;
        int y = Utils.max_height / 2 + 200 + hei;

        w.setLayout(new CardLayout());
        w.add(p);
        w.setWindowLocation(x, y);
        w.setWindowSize(wid, 40);
    }


    // function to pop up the toast
    public void showtoast() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    w.setOpacity(1);
                    w.setVisible(true);

                    // wait for some time
                    Thread.sleep(3000);

                    // make the message disappear  slowly
                    for (double d = 1.0; d > 0.2; d -= 0.1) {
                        Thread.sleep(100);
                        w.setOpacity((float) d);
                    }

                    // set the visibility to false
                    w.setVisible(false);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }).start();
    }
}