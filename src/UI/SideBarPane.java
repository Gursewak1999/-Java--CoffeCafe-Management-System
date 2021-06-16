package UI;

import helper.ColorUtils;
import helper.InstanceHelper;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SideBarPane extends JPanel {

    private final SidebarItems dashboard_label;
    private final SidebarItems list_coffee_label;
    private final SidebarItems notice_label;
    private final SidebarItems target_label;
    private final SidebarItems todays_special_label;
    private final SidebarItems add_orders_label;
    private final SidebarItems orders_label;
    private final SidebarItems logout_label;

    private String selected_item = "";

    public SideBarPane() {
        setBackground(ColorUtils.sidebar_background);
        setLayout(new FlowLayout());

        JLabel headerLabel = new JLabel("Coffee Cafe");
        headerLabel.setFont(new Font("Lovely Coffee", Font.BOLD, 44));
        headerLabel.setBorder(new EmptyBorder(20, 0, 20, 0));

        JPanel content = new JPanel();
        dashboard_label = new SidebarItems("Dashboard", "icons/dashboard.png");
        dashboard_label.addMouseListener(onLabelClicked(InstanceHelper.FRAGMENT_DASHBOARD_HOME));
        list_coffee_label = new SidebarItems("Add Coffee", "icons/add.png");
        list_coffee_label.addMouseListener(onLabelClicked(InstanceHelper.FRAGMENT_LIST_COFFEE));
        notice_label = new SidebarItems("Add Notice Board", "icons/notice.png");
        notice_label.addMouseListener(onLabelClicked(InstanceHelper.FRAGMENT_NOTICEBOARD));
        target_label = new SidebarItems("Add Target", "icons/target.png");
        target_label.addMouseListener(onLabelClicked(InstanceHelper.FRAGMENT_TARGET));
        todays_special_label = new SidebarItems("Update Today's Special", "icons/special.png");
        todays_special_label.addMouseListener(onLabelClicked(InstanceHelper.FRAGMENT_TODAYS_SPECIAL));
        add_orders_label = new SidebarItems("Add Orders", "icons/add.png");
        add_orders_label.addMouseListener(onLabelClicked(InstanceHelper.FRAGMENT_ADD_ORDERS));
        orders_label = new SidebarItems("Show Orders", "icons/order.png");
        orders_label.addMouseListener(onLabelClicked(InstanceHelper.FRAGMENT_ORDERS));
        logout_label = new SidebarItems("Logout", "icons/order.png");
        logout_label.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
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
        });

        this.add(headerLabel);
        this.add(content);
        content.setBackground(getBackground());

        if (selected_item.equals(""))
            selected_item = InstanceHelper.FRAGMENT_DASHBOARD_HOME;
        dashboard_label.setBackground(ColorUtils.selected);

        content.setBounds(0, headerLabel.getHeight() + 40, getWidth(), getHeight() - 40);
        content.setLayout(new GridLayout(12, 1));
        content.add(dashboard_label);
        content.add(list_coffee_label);
        content.add(notice_label);
        content.add(target_label);
        content.add(todays_special_label);
        content.add(add_orders_label);
        content.add(orders_label);
        content.add(logout_label);
    }

    private MouseListener onLabelClicked(String fragment_name) {

        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (getSidebarFragment(selected_item) != null) {
                    getSidebarFragment(selected_item).setBackground(ColorUtils.deselected);
                }
                selected_item = fragment_name;
                if (getSidebarFragment(fragment_name) != null) {
                    getSidebarFragment(fragment_name).setBackground(ColorUtils.selected);
                }
                InstanceHelper.gotoFragment(fragment_name);
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

    private SidebarItems getSidebarFragment(String name) {

        switch (name) {

            case InstanceHelper.FRAGMENT_DASHBOARD_HOME:
                return dashboard_label;
            case InstanceHelper.FRAGMENT_NOTICEBOARD:
                return notice_label;
            case InstanceHelper.FRAGMENT_LIST_COFFEE:
                return list_coffee_label;
            case InstanceHelper.FRAGMENT_TARGET:
                return target_label;
            case InstanceHelper.FRAGMENT_TODAYS_SPECIAL:
                return todays_special_label;
            case InstanceHelper.FRAGMENT_ADD_ORDERS:
                return add_orders_label;
            case InstanceHelper.FRAGMENT_ORDERS:
                return orders_label;
        }
        return null;
    }

    private void setComponentSize(JComponent dashboard_label) {
        int item_height = 40;
        int item_width = 220;
        Dimension dim = new Dimension(item_width, item_height);
        dashboard_label.setSize(dim);
        dashboard_label.setPreferredSize(dim);
        dashboard_label.setMinimumSize(dim);
    }

}
