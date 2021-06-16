package UI.Fragments;

import DataClasses.CoffeeData;
import UI.Dialog.ConfirmationDialog;
import helper.InstanceHelper;
import helper.Utils;
import helper.db.CoffeeHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ListCoffeeFragment extends JPanel {

    public ListCoffeeFragment(int fragment_width, int fragment_height) {

        Dimension dim = new Dimension(fragment_width, fragment_height);
        setMinimumSize(dim);
        setPreferredSize(dim);

        JPanel d = new JPanel();

        JLabel title = new JLabel(" -- List Coffee -- ");
        d.add(title);
        JButton add_button = new JButton("+ Add");
        add_button.addActionListener(onAddCoffeeButtonClick());
        d.add(add_button);
        //d = new JDialog(f, "Choose Your Coffee", true);
        d.setLayout(new GridLayout(20, 0));

        d.add(getHeader());
        ArrayList<CoffeeData> coffeeData = new CoffeeHelper().getAllCoffee();

        for (CoffeeData data : coffeeData)
            d.add(getRows(data));

        d.setSize(Utils.max_width, Utils.max_height);

        this.setLayout(new CardLayout());
        this.add(d);
    }

    private ActionListener onAddCoffeeButtonClick() {
        return e -> InstanceHelper.gotoFragment(InstanceHelper.FRAGMENT_ADD_COFFEE);
    }

    private Component getRows(CoffeeData data) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 7));

        JLabel col_name = new JLabel("  " + data.getName());
        JLabel col_price_small = new JLabel("  " + data.getPrice_small());
        JLabel col_price_medium = new JLabel("  " + data.getPrice_medium());
        JLabel col_price_large = new JLabel("  " + data.getPrice_large());
        JLabel col_remaining = new JLabel("  " + data.getQuantity());

        JButton btn_edit = new JButton("Edit");
        JButton btn_remove = new JButton("Remove");

        btn_edit.addActionListener(e -> {
            InstanceHelper.setData(data, true);
            InstanceHelper.gotoFragment(InstanceHelper.FRAGMENT_ADD_COFFEE);
        });
        btn_remove.addActionListener(e -> {

            ConfirmationDialog dialog = new ConfirmationDialog("Confirm Delete",
                    "Are You sure to Delete " + data.getName() + " from Coffee List",
                    "Yes", "No",
                    () -> {
                        new CoffeeHelper().delete_coffee(data.getName());
                        InstanceHelper.gotoFragment(InstanceHelper.FRAGMENT_LIST_COFFEE);
                        return null;
                    }, () -> null);

            dialog.setVisible(true);
        });

        panel.add(col_name);
        panel.add(col_price_small);
        panel.add(col_price_medium);
        panel.add(col_price_large);
        panel.add(col_remaining);
        panel.add(btn_edit);
        panel.add(btn_remove);

        return panel;
    }

    private Component getHeader() {

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 7));

        JLabel col_name = new JLabel("  Name");
        JLabel col_price_small = new JLabel("  Price Small");
        JLabel col_price_medium = new JLabel("  Price Medium");
        JLabel col_price_large = new JLabel("  Price Large");
        JLabel col_remaining = new JLabel("  Remaining");
        JLabel col_action = new JLabel("  Action");

        panel.add(col_name);
        panel.add(col_price_small);
        panel.add(col_price_medium);
        panel.add(col_price_large);
        panel.add(col_remaining);
        panel.add(col_action);

        return panel;
    }

}
