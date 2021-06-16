package UI;

import UI.Fragments.DashboardContent;
import helper.Utils;

import javax.swing.*;
import java.awt.*;

public class DashboardUI extends JPanel {

    private JPanel window_pane;

    public DashboardUI() {
        //this.setBackground(ColorUtils.backgroundLight);
        int _width = Utils.max_width * 4 / 5, _height = Utils.max_height * 4 / 5;
        this.setSize(_width, _height);

        this.setLayout(new GridBagLayout());
        JPanel sidebar = new SideBarPane();

        sidebar.setMinimumSize(new Dimension(_width / 5, _height));
        sidebar.setSize(new Dimension(_width / 5, _height));
        sidebar.setPreferredSize(new Dimension(_width / 5, _height));

        window_pane = new DashboardContent(this, _width - sidebar.getWidth() - 30, _height);

        window_pane.setMinimumSize(new Dimension(_width - sidebar.getWidth(), _height));
        window_pane.setSize(new Dimension(_width - sidebar.getWidth(), _height));
        window_pane.setPreferredSize(new Dimension(_width - sidebar.getWidth(), _height));

        Insets insets = getInsets();
        sidebar.setBounds(insets.left, insets.top, sidebar.getWidth(), sidebar.getHeight());
        window_pane.setBounds(sidebar.getWidth() + insets.left, insets.top, window_pane.getWidth(), window_pane.getHeight());

        this.setLayout(null);
        this.add(sidebar, 0);
        this.add(window_pane, 1);
    }

    public DashboardUI(int _width, int _height) {

        this.setSize(_width, _height);

        this.setLayout(new GridBagLayout());
        JPanel sidebar = new SideBarPane();

        sidebar.setMinimumSize(new Dimension(_width / 5, _height));
        sidebar.setSize(new Dimension(_width / 5, _height));
        sidebar.setPreferredSize(new Dimension(_width / 5, _height));

        window_pane = new JPanel();
        window_pane.setMinimumSize(new Dimension(_width - sidebar.getWidth(), _height));
        window_pane.setSize(new Dimension(_width - sidebar.getWidth(), _height));
        window_pane.setPreferredSize(new Dimension(_width - sidebar.getWidth(), _height));

        Insets insets = getInsets();
        sidebar.setBounds(insets.left, insets.top, sidebar.getWidth(), sidebar.getHeight());
        window_pane.setBounds(sidebar.getWidth() + insets.left, insets.top, window_pane.getWidth(), window_pane.getHeight());

        this.setLayout(null);
        this.add(sidebar, 0);
        this.add(window_pane, 1);
    }

    public void setWindow_pane(JPanel from, JPanel to) {
        window_pane.setLayout(new CardLayout());
        if (from != null)
            window_pane.remove(from);
        System.out.println();
        window_pane.add(to);
        window_pane.revalidate();
        window_pane.repaint();
    }

}
