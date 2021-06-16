package UI;

import de.craften.ui.swingmaterial.MaterialWindow;
import helper.Utils;

import javax.swing.*;

public class Main {

    static MaterialWindow jWindow;
    static StrippedUI strippedUI;
    static JPanel jPanel;

    public Main() {


        jWindow = new MaterialWindow();
        jWindow.setWindowSize(Utils.max_width / 4, Utils.max_height / 2);

        strippedUI = new StrippedUI(jWindow.getWindowWidth(), jWindow.getWindowHeight());
        jPanel = new LoginUI(jWindow.getWindowWidth(), jWindow.getWindowHeight());
        strippedUI.setContentPane(jPanel);

        jWindow.add(strippedUI);

        //to center
        Utils.centerWindow(jWindow);
        //jWindow.pack();
        jWindow.setVisible(true);

    }

    public static void switchWindow(JPanel to) {

        if (strippedUI != null)
            jWindow.remove(strippedUI);
        //strippedUI = to;
        //strippedUI.setContentPane(to);
        jWindow.add(to);
        jWindow.setWindowSize(to.getWidth(), to.getHeight());

        Utils.centerWindow(jWindow);
        strippedUI.revalidate();
    }
}

