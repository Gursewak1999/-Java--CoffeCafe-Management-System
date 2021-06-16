package UI.Fragments;

import DataClasses.CoffeeData;
import DataClasses.Orders;
import UI.custom.JImagePanel;
import UI.custom.MaterialCardView;
import helper.InstanceHelper;
import helper.Utils;
import helper.db.OrderHelper;
import javafx.util.Pair;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class ShowOrdersFragment extends JPanel {

    private JLabel grand_total_label;
    private JTextField contact_field;
    private JTextField name_field;
    private JImagePanel table;
    private int grand_total = 0;

    private ArrayList<Pair<Pair<CoffeeData, String>, Integer>> coffeeDataArrayList = new ArrayList<>();

    public ShowOrdersFragment(int fragment_width, int fragment_height) {

        Dimension dim = new Dimension(fragment_width, fragment_height);
        setMinimumSize(dim);
        setPreferredSize(dim);
        this.setLayout(null);

        MaterialCardView cardView = new MaterialCardView(InstanceHelper.fragment_width - 20, fragment_height - 20);
        cardView.setBounds(10, 10, cardView.getPreferredSize().width, cardView.getPreferredSize().height);

        setupOrderCard(cardView, new OrderHelper().getAll());

        add(cardView);
    }

    private void setupOrderCard(MaterialCardView cardView, ArrayList<Orders> orders) {

        cardView.content_panel.setLayout(null);
        cardView.setCardBackground(Color.WHITE);
        JPanel root_layout = new JPanel();
        root_layout.setLayout(null);
        int width = cardView.getInnerWidth() - 20;
        int height = cardView.getInnerHeight() - 20;
        root_layout.setBounds(10, 10, width, height);

        //Setting Up Order Table
        table = new JImagePanel();
        table.setBounds(0, 0, cardView.getInnerWidth()-20, cardView.getInnerHeight());
        table.setLayout(new GridLayout(10, 0));
        table.setBackground(new Color(0,0,0,0));
        table.add(getTableHeader());
        for (Orders order : orders)
            table.add(getTableRow(order));

        root_layout.add(table);

        cardView.addContent(root_layout);
    }

    private JPanel getTableRow(Orders order) {
        JPanel row = new JPanel();
        row.setLayout(new GridLayout(0, 5));

        JLabel col_date = new JLabel("  "+Utils.convertToDate(order.getTimestamp()));
        JLabel col_name = new JLabel("  "+order.getCustomer_name());
        JLabel col_size = new JLabel("  "+order.getCustomer_contact());
        JLabel col_price = new JLabel("  "+order.getGrand_total() + "/-");
        JButton col_action = new JButton("Check Details");

        col_date.setBorder(LineBorder.createBlackLineBorder());
        col_name.setBorder(LineBorder.createBlackLineBorder());
        col_size.setBorder(LineBorder.createBlackLineBorder());
        col_price.setBorder(LineBorder.createBlackLineBorder());
        col_action.setBorder(LineBorder.createBlackLineBorder());

        row.add(col_date);
        row.add(col_name);
        row.add(col_size);
        row.add(col_price);
        row.add(col_action);

        col_action.addActionListener(e -> gotoOrderDetails(order));

        return row;
    }

    private void gotoOrderDetails(Orders order) {
        InstanceHelper.setData(order,true);
        InstanceHelper.gotoFragment(InstanceHelper.FRAGMENT_ADD_ORDERS);
    }

    private JPanel getTableHeader() {

        JPanel row = new JPanel();
        row.setLayout(new GridLayout(0, 5));

        JLabel col_date = new JLabel("  Date");
        JLabel col_name = new JLabel("  Name");
        JLabel col_size = new JLabel("  Size");
        JLabel col_price = new JLabel("  Total");
        JLabel col_action = new JLabel("  Action");

        col_date.setBorder(LineBorder.createBlackLineBorder());
        col_name.setBorder(LineBorder.createBlackLineBorder());
        col_size.setBorder(LineBorder.createBlackLineBorder());
        col_price.setBorder(LineBorder.createBlackLineBorder());
        col_action.setBorder(LineBorder.createBlackLineBorder());

        row.add(col_date);
        row.add(col_name);
        row.add(col_size);
        row.add(col_price);
        row.add(col_action);

        return row;
    }

}
