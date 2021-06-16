package UI.Fragments;

import DataClasses.CoffeeData;
import UI.Dialog.ConfirmationDialog;
import UI.custom.Toast;
import helper.ColorUtils;
import helper.InstanceHelper;
import helper.Utils;
import helper.db.CoffeeHelper;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AddCoffeeFragment extends JPanel {

    private final CoffeeData data;
    private JTextField name_field, desc_field, cover_field;
    private JTextField price_large_field, price_medium_field, price_small_field, quantity_field;

    public AddCoffeeFragment(int fragment_width, int fragment_height) {

        this.setBorder(new EmptyBorder(0, 40, 40, 40));
        if (InstanceHelper.doEdit) {
            this.data = InstanceHelper.data;
            InstanceHelper.doEdit = false;
        } else {

            data = null;
        }
        init();
    }

    private void init() {
        this.setLayout(new GridLayout(19, 0));

        JLabel back_label = new JLabel("<html><p>&larr;</p></html>");
        add(back_label);
        add(new JPanel());
        back_label.setFont(new Font("Poppins", Font.BOLD, 32));
        back_label.setForeground(ColorUtils.backgroundSecondary);
        back_label.setBackground(new Color(0, 0, 0, 0));
        back_label.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                InstanceHelper.gotoFragment(InstanceHelper.FRAGMENT_LIST_COFFEE);
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

        JLabel title = new JLabel();
        if (data == null)
            title.setText("Add Coffee");
        else
            title.setText("Update Coffee Data");

        title.setBounds(10, 10, 300, 80);
        title.setFont(new Font("Poppins", Font.PLAIN, 32));
        add(title);
        add(new JPanel());

        JPanel name_row = new JPanel(new GridLayout(0, 3));
        JLabel name_label = new JLabel("Name");
         name_field = new JTextField();
        if (data != null)
            name_field.setText(data.getName());
        name_row.add(name_label);
        name_row.add(name_field);
        add(name_row);
        add(new JPanel());

        JPanel desc_row = new JPanel(new GridLayout(0, 3));
        JLabel desc_label = new JLabel("Description");
         desc_field = new JTextField();
        if (data != null)
            desc_field.setText(data.getDescription());
        desc_row.add(desc_label);
        desc_row.add(desc_field);
        add(desc_row);
        add(new JPanel());

        JPanel cover_row = new JPanel(new GridLayout(0, 3));
        JLabel cover_label = new JLabel("Cover");
         cover_field = new JTextField();
        if (data != null)
            cover_field.setText(data.getCover());
        cover_row.add(cover_label);
        cover_row.add(cover_field);
        add(cover_row);
        add(new JPanel());

        JPanel price_small_row = new JPanel(new GridLayout(0, 3));
        JLabel price_small_label = new JLabel("Price (Small)");
        price_small_field = new JTextField();
        if (data != null)
            price_small_field.setText(String.valueOf(data.getPrice_small()));
        price_small_row.add(price_small_label);
        price_small_row.add(price_small_field);
        add(price_small_row);
        add(new JPanel());

        JPanel price_medium_row = new JPanel(new GridLayout(0, 3));
        JLabel price_medium_label = new JLabel("Price (Medium)");
        price_medium_field = new JTextField();
        if (data != null)
            price_medium_field.setText(String.valueOf(data.getPrice_medium()));
        price_medium_row.add(price_medium_label);
        price_medium_row.add(price_medium_field);
        add(price_medium_row);
        add(new JPanel());

        JPanel price_large_row = new JPanel(new GridLayout(0, 3));
        JLabel price_large_label = new JLabel("Price (Large)");
        price_large_field = new JTextField();
        if (data != null)
            price_large_field.setText(String.valueOf(data.getPrice_large()));
        price_large_row.add(price_large_label);
        price_large_row.add(price_large_field);
        add(price_large_row);
        add(new JPanel());

        JPanel quantity_row = new JPanel(new GridLayout(0, 3));
        JLabel quantity_label = new JLabel("Initial Quantity");
         quantity_field = new JTextField();
        if (data != null)
            quantity_field.setText(String.valueOf(data.getQuantity()));
        quantity_row.add(quantity_label);
        quantity_row.add(quantity_field);
        add(quantity_row);
        add(new JPanel());

        JButton add_button = new JButton("Add Coffee");

        add_button.addActionListener(e -> {

            if (validated()) {
                CoffeeData coffeeData = new CoffeeData();
                coffeeData.setName(name_field.getText());
                coffeeData.setDescription(desc_field.getText());
                coffeeData.setCover(cover_field.getText());
                coffeeData.setPrice_small(Integer.parseInt(price_small_field.getText()));
                coffeeData.setPrice_medium(Integer.parseInt(price_medium_field.getText()));
                coffeeData.setPrice_large(Integer.parseInt(price_large_field.getText()));
                coffeeData.setQuantity(Integer.parseInt(quantity_field.getText()));

                if (data == null) {
                    //insert data
                    System.out.println("Insert Coffee");
                    new ConfirmationDialog("Add Coffee Data",
                            "Do you want to Add " + coffeeData.getName() + " to Database ..",
                            "Yes", "No",
                            () -> {
                                boolean d = new CoffeeHelper().add_coffee(coffeeData);
                                if (d)
                                    new Toast(coffeeData.getName() + "is added to Database", false);
                                else
                                    new Toast("Failed to add " + coffeeData.getName(), false);
                                return null;
                            },
                            () -> null).setVisible(true);
                } else {
                    //update data
                    System.out.println("Update Coffee");
                    new ConfirmationDialog("Update Coffee Data",
                            "Do you want to Update " + coffeeData.getName() + " to Database ..",
                            "Yes", "No",
                            () -> {
                                boolean d = new CoffeeHelper().update_coffee(data.getName(), coffeeData);
                                if (d)
                                    new Toast(coffeeData.getName() + "is Updated to Database", false);
                                else
                                    new Toast("Failed to update " + coffeeData.getName(), false);
                                return null;
                            },
                            () -> null).setVisible(true);

                }
            }
        });
        add(add_button);
    }

    private boolean validated() {
        System.out.println("Validating : In progress");
        if (name_field.getText().isEmpty()) {
            new Toast("Name Field Can't be Empty", true).showtoast();
            return false;
        } else if (desc_field.getText().isEmpty()) {
            new Toast("Description Field Can't be Empty", true).showtoast();
            return false;
        } else if (cover_field.getText().isEmpty()) {
            new Toast("Cover Field Can't be Empty", true).showtoast();
            return false;
        } else if (price_small_field.getText().isEmpty()) {
            new Toast("Price (Small) Field Can't be Empty", true).showtoast();
            return false;
        } else if (price_medium_field.getText().isEmpty()) {
            new Toast("Price (Medium) Field Can't be Empty", true).showtoast();
            return false;
        } else if (price_large_field.getText().isEmpty()) {
            new Toast("Price (Large) Field Can't be Empty", true).showtoast();
            return false;
        } else if (quantity_field.getText().isEmpty()) {
            new Toast("Quantity Field Can't be Empty", true).showtoast();
            return false;
        } else if (!Utils.isNumeric(price_small_field.getText())) {
            new Toast("Price (Small) Field should be Integer", true).showtoast();
            return false;
        } else if (!Utils.isNumeric(price_medium_field.getText())) {
            new Toast("Price (Medium) Field should be Integer", true).showtoast();
            return false;
        } else if (!Utils.isNumeric(price_large_field.getText())) {
            new Toast("Price (Large) Field should be Integer", true).showtoast();
            return false;
        } else if (!Utils.isNumeric(quantity_field.getText())) {
            new Toast("Quantity Field should be Integer", true).showtoast();
            return false;
        } else {
            System.out.println("Validated : true");
            return true;
        }
    }
}
