package UI.Fragments;

import DataClasses.CoffeeData;
import DataClasses.Orders;
import UI.Dialog.CoffeeChooser;
import UI.custom.JImagePanel;
import UI.custom.MaterialCardView;
import UI.custom.Toast;
import helper.ColorUtils;
import helper.InstanceHelper;
import helper.Utils;
import helper.db.OrderHelper;
import inferences.OnClickListener;
import javafx.util.Pair;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class AddOrderFragment extends JPanel {

    private final JLabel grand_total_label;
    private final JTextField contact_field, name_field;
    private final Orders data;
    private final JButton add_order_button;
    private JImagePanel table;
    private int grand_total = 0;

    private ArrayList<Pair<Pair<CoffeeData, String>, Integer>> coffeeDataArrayList = new ArrayList<>();

    public AddOrderFragment(int fragment_width, int fragment_height) {

        if (InstanceHelper.doEdit) {
            data = InstanceHelper.order;
        } else data = null;

        Dimension dim = new Dimension(fragment_width, fragment_height);
        setMinimumSize(dim);
        setPreferredSize(dim);
        this.setLayout(null);

        JLabel title = new JLabel("Add Orders");
        title.setBounds((fragment_width - 100) / 2, 10, 100, 20);
        add(title);

        JLabel name_label = new JLabel("Name ");
        name_label.setBounds(10, 40, 200, 30);
        JLabel contact_label = new JLabel("Contact no. ");
        contact_label.setBounds(10, 80, 200, 30);
        name_field = new JTextField();
        name_field.setBounds(220, 40, 200, 30);
        contact_field = new JTextField();
        contact_field.setBounds(220, 80, 200, 30);

        JLabel time_label = new JLabel();
        time_label.setBounds(440, 40, 400, 30);

        if (data == null) {
            updateWithTime(time_label);
        } else {
            System.out.println(data.getTimestamp());
            time_label.setText("Time and Date : " + Utils.convertToDate(data.getTimestamp()));;
            title.setText("Order Details");
        }
        MaterialCardView cardView = new MaterialCardView(fragment_width - 100, fragment_height - 100 - 80);
        cardView.setBounds((fragment_width - cardView.getPreferredSize().width) / 2, 110, cardView.getPreferredSize().width, cardView.getPreferredSize().height);

        setupOrderCard(cardView);

        grand_total_label = new JLabel("Grand Total : " + grand_total + "/-");
        grand_total_label.setBounds((fragment_width - cardView.getPreferredSize().width) / 2, 110 + fragment_height - 100 - 80, 300, 40);
        add_order_button = new JButton("Add Order");
        add_order_button.setBounds(fragment_width - 120, 110 + fragment_height - 100 - 80, 100, 40);
        add_order_button.addActionListener(addOrderClick());

        add(name_label);
        add(contact_label);
        add(name_field);
        add(contact_field);
        add(time_label);
        add(cardView);
        add(grand_total_label);
        add(add_order_button);


        if (data != null) {
            setupShowDetails();
            revalidate();
            repaint();
        }
        //System.out.println(InstanceHelper.doEdit + " " + ((data == null) ? "null" : data.toString()));
        InstanceHelper.doEdit = false;

    }

    private void updateWithTime(JLabel live_time_label) {
        Runnable runnable = () -> {
            try {
                while (true) {
                    live_time_label.setText("Time and Date : " + Utils.convertToDate(Utils.generateId()));
                    sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        new Thread(runnable).start();
    }

    private void setupShowDetails() {

        name_field.setText(data.getCustomer_name());
        name_field.setEditable(false);

        contact_field.setText(data.getCustomer_contact());
        contact_field.setEditable(false);

        grand_total = data.getGrand_total();
        grand_total_label.setText("Grand Total : " + grand_total + "/-");

        remove(add_order_button);
        removeAllAction();

        for (Pair<Pair<CoffeeData, String>, Integer> dat : data.getContent()) {

            addRow(dat);
        }
    }

    private ActionListener addOrderClick() {

        return e -> {
            if (verified()) {
                Orders order = new Orders();
                order.setCustomer_name(name_field.getText());
                order.setCustomer_contact(contact_field.getText());
                order.setGrand_total(grand_total);
                order.setContent(coffeeDataArrayList);
                boolean result = new OrderHelper().addNewOrder(order);
                if (result)
                    new Toast("Order Added Successfully", false).showtoast();
                else
                    new Toast("Order Failed To add", true).showtoast();
                removeAllAction();
            }
        };
    }

    private boolean verified() {

        if (name_field.getText().isEmpty()) {
            new Toast("Error: Please Enter Name", true).showtoast();
            return false;
        } else if (contact_field.getText().isEmpty()) {
            new Toast("Error: Please Enter Contact Number", true).showtoast();
            return false;
        } else if (Utils.validatePhone(contact_field.getText())) {
            new Toast("Error: Please Enter Correct Contact Number", true).showtoast();
            return false;
        } else if (coffeeDataArrayList.size() == 0) {
            new Toast("Error: Please Add any Item", true).showtoast();
            return false;
        }
        return true;
    }

    private void setupOrderCard(MaterialCardView cardView) {
        this.setBackground(ColorUtils.background_super_light);

        cardView.content_panel.setLayout(null);
        cardView.setCardBackground(Color.WHITE);
        JPanel root_layout = new JPanel();

        root_layout.setLayout(null);
        int width = cardView.getInnerWidth() - 20;
        int height = cardView.getInnerHeight() - 20;
        root_layout.setBounds(10, 10, width, height);

        //Setting Up Upper Toolbar
        JPanel toolbar = new JPanel();
        toolbar.setBounds(0, 0, width, 30);

        JButton addCoffee = new JButton("+ Add");
        addCoffee.setBounds(0, 0, 80, 24);
        addCoffee.addActionListener(OnAddCoffeeButtonClick());

        toolbar.add(addCoffee);

        JButton removeAllCoffee = new JButton("- Remove All");
        removeAllCoffee.setBounds(80, 0, 80, 24);
        removeAllCoffee.addActionListener(OnRemoveAllButtonClicked());
        toolbar.add(removeAllCoffee);

        toolbar.setBackground(ColorUtils.background);
        if (data == null)
            root_layout.add(toolbar);

        //Setting Up Order Table
        table = new JImagePanel();
        table.setBackground(new Color(0, 0, 0, 0));

        table.setBounds(0, 32, width, height - 32);
        // table.setBackgroundImage("res/drawables/table_bg.png");
        table.setLayout(new GridLayout(10, 0));

        table.add(getTableHeader());
        root_layout.add(table);

        cardView.addContent(root_layout);

    }

    private Component getTableHeader() {

        JPanel row = new JPanel();
        row.setBackground(new Color(0, 0, 0, 0));
        row.setLayout(new GridLayout(0, 6));

        JLabel col_name = new JLabel("  Name");
        JLabel col_size = new JLabel("  Size");
        JLabel col_price = new JLabel("  Price");
        JLabel col_quantity = new JLabel("  Quantity");
        JLabel col_total = new JLabel("  Total");
        JLabel col_action = new JLabel("  Action");

        col_quantity.setBorder(LineBorder.createBlackLineBorder());
        col_name.setBorder(LineBorder.createBlackLineBorder());
        col_size.setBorder(LineBorder.createBlackLineBorder());
        col_price.setBorder(LineBorder.createBlackLineBorder());
        col_total.setBorder(LineBorder.createBlackLineBorder());
        col_action.setBorder(LineBorder.createBlackLineBorder());

        row.add(col_name);
        row.add(col_size);
        row.add(col_price);
        row.add(col_quantity);
        row.add(col_total);
        row.add(col_action);

        return row;
    }

    private ActionListener OnRemoveAllButtonClicked() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                removeAllAction();
            }
        };
    }

    private void removeAllAction() {

        table.removeAll();
        table.add(getTableHeader());
        grand_total = 0;
        grand_total_label.setText("Grand Total : " + grand_total + "/-");

        revalidate();
        repaint();
    }

    private ActionListener OnAddCoffeeButtonClick() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new CoffeeChooser(new OnClickListener() {

                    @Override
                    public void onCLick(JComponent view) {

                    }

                    @Override
                    public void getCoffee(CoffeeData data, String selected_price) {

                        // System.out.println(selected_price + " - " +

                        boolean isFound = false;
                        for (Pair<Pair<CoffeeData, String>, Integer> p : coffeeDataArrayList)
                            if (data.getName().equals(p.getKey().getKey().getName()))
                                isFound = true;
                        if (!isFound)
                            addRow(data, selected_price);
                        else new Toast("Already added to list", true).showtoast();
                    }
                });
            }
        };
    }

    private void addRow(CoffeeData data, String selected_price) {

        final int[] quantity = {1};

        JPanel row = new JPanel();
        row.setBackground(new Color(0, 0, 0, 0));
        row.setLayout(new GridLayout(0, 6));

        JLabel col_name = new JLabel("  " + data.getName());
        JLabel col_size = new JLabel("  " + selected_price);
        int price = selected_price.equals("S") ? data.getPrice_small() : (selected_price.equals("M") ? data.getPrice_medium() : data.getPrice_large());
        JLabel col_price = new JLabel("  " + price + "/-");

        // JLabel col_quantity = new JLabel("  " + String.valueOf(quantity));
        JPanel col_quantity = new JPanel(new GridLayout(0, 3));
        JLabel col_total = new JLabel("  " + price * quantity[0] + "/-");
        JButton col_remove = new JButton("- Remove");

        col_quantity.setBorder(LineBorder.createBlackLineBorder());
        col_name.setBorder(LineBorder.createBlackLineBorder());
        col_size.setBorder(LineBorder.createBlackLineBorder());
        col_price.setBorder(LineBorder.createBlackLineBorder());
        col_total.setBorder(LineBorder.createBlackLineBorder());
        col_remove.setBorder(LineBorder.createBlackLineBorder());

        //col_quantity
        JButton add_button = new JButton("+");
        JLabel col_quan = new JLabel("  " + String.valueOf(quantity[0]));
        JButton reduce_button = new JButton("-");

        add_button.setFont(new Font("Poppins", Font.BOLD, 8));
        reduce_button.setFont(new Font("Poppins", Font.BOLD, 8));

        add_button.addActionListener(e -> {

            SwingUtilities.invokeLater(() -> {
                quantity[0] = quantity[0] + 1;

                if (quantity[0] > data.getQuantity()) {
                    new Toast("Doesn't have enough quantity available.", true).showtoast();
                    quantity[0] = quantity[0] - 1;
                } else {

                    col_quan.setText("  " + quantity[0]);
                    col_total.setText("  " + price * quantity[0] + "/-");

                    grand_total = grand_total + price;
                    grand_total_label.setText("Grand Total : " + grand_total + "/-");

                    repaint();
                    revalidate();

                    ar_replace(data.getName(), new Pair<>(new Pair<>(data, selected_price), quantity[0]));
                }
            });

        });
        reduce_button.addActionListener(e -> {

            SwingUtilities.invokeLater(() -> {
                if (quantity[0] > 1) {
                    quantity[0] = quantity[0] - 1;

                    col_quan.setText("  " + quantity[0]);
                    col_total.setText("");
                    col_total.setText("  " + price * quantity[0] + "/-");

                    grand_total = grand_total - price;
                    grand_total_label.setText("Grand Total : " + grand_total + "/-");

                    repaint();
                    revalidate();

                    ar_replace(data.getName(), new Pair<>(new Pair<>(data, selected_price), quantity[0]));
                } else new Toast("Quantity must be minimum " + CoffeeData.minQuantity + ".", true).showtoast();
            });
        });

        col_quantity.add(add_button);
        col_quantity.add(col_quan);
        col_quantity.add(reduce_button);

        row.add(col_name);
        row.add(col_size);
        row.add(col_price);
        row.add(col_quantity);
        row.add(col_total);
        row.add(col_remove);

        grand_total = grand_total + price * quantity[0];
        grand_total_label.setText("Grand Total : " + grand_total + "/-");

        col_remove.addActionListener(e -> {
            grand_total = grand_total - price * quantity[0];
            grand_total_label.setText("Grand Total : " + grand_total + "/-");
            table.remove(row);

            ar_remove(data.getName());

            revalidate();
            repaint();
        });

        coffeeDataArrayList.add(new Pair<>(new Pair<>(data, selected_price), quantity[0]));
        table.add(row);
        revalidate();
        repaint();
    }

    private void ar_remove(String name) {
        Pair<Pair<CoffeeData, String>, Integer> toRem = null;
        for (Pair<Pair<CoffeeData, String>, Integer> pr : coffeeDataArrayList) {
            if (pr.getKey().getKey().getName().equals(name))
                toRem = pr;
        }
        if (toRem != null)
            coffeeDataArrayList.remove(toRem);
    }

    private void ar_replace(String name, Pair<Pair<CoffeeData, String>, Integer> toRepl) {
        ar_remove(name);
        coffeeDataArrayList.add(toRepl);
    }

    private void addRow(Pair<Pair<CoffeeData, String>, Integer> pair_data) {

        final int quantity = pair_data.getValue();

        String selected_price = pair_data.getKey().getValue();
        CoffeeData cafDat = pair_data.getKey().getKey();

        JPanel row = new JPanel();
        row.setBackground(new Color(0, 0, 0, 0));
        row.setLayout(new GridLayout(0, 6));

        JLabel col_name = new JLabel("  " + cafDat.getName());
        JLabel col_size = new JLabel("  " + selected_price);
        int price = selected_price.equals("S") ? cafDat.getPrice_small() : (selected_price.equals("M") ? cafDat.getPrice_medium() : cafDat.getPrice_large());
        JLabel col_price = new JLabel("  " + price + "/-");
        JLabel col_quantity = new JLabel("  " + quantity);
        JLabel col_total = new JLabel("  " + price * quantity + "/-");
        JButton col_remove = new JButton("- Remove");
        col_remove.setEnabled(false);

        col_quantity.setBorder(LineBorder.createBlackLineBorder());
        col_name.setBorder(LineBorder.createBlackLineBorder());
        col_size.setBorder(LineBorder.createBlackLineBorder());
        col_price.setBorder(LineBorder.createBlackLineBorder());
        col_total.setBorder(LineBorder.createBlackLineBorder());
        col_remove.setBorder(LineBorder.createBlackLineBorder());

        row.add(col_name);
        row.add(col_size);
        row.add(col_price);
        row.add(col_quantity);
        row.add(col_total);
        row.add(col_remove);

        grand_total = grand_total + price * quantity;
        grand_total_label.setText("Grand Total : " + grand_total + "/-");

        table.add(row);
        revalidate();
        repaint();
    }

}
