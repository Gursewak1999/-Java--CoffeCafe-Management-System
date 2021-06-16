package UI.Fragments;

import DataClasses.CoffeeData;
import UI.Dialog.CoffeeChooser;
import UI.custom.MaterialCardView;
import UI.custom.Toast;
import helper.ColorUtils;
import helper.InstanceHelper;
import helper.db.GeneralDataHelper;
import inferences.OnClickListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class TodaySpecialFragment extends JPanel {

    int label_height = 60;
    int field_height = 40;

    public TodaySpecialFragment(int fragment_width, int fragment_height) {

        Dimension dim = new Dimension(fragment_width, fragment_height);
        setMinimumSize(dim);
        setPreferredSize(dim);

        setBackground(ColorUtils.background_super_light);

        JLabel label = new JLabel("Edit Today's Special");
        Dimension lab_dim = new Dimension(fragment_width, label_height);
        label.setBorder(new EmptyBorder(20, 60, 20, 60));
        label.setFont(new Font("Poppins", Font.PLAIN, 24));
        label.setMinimumSize(lab_dim);
        label.setPreferredSize(lab_dim);

        JLabel field = new JLabel();
        field.setBorder(new EmptyBorder(0, 0, 0, 0));
        field.setHorizontalAlignment(JTextField.CENTER);
        field.setBackground(ColorUtils.background_light_blue);
        MaterialCardView field_card = new MaterialCardView(fragment_width - 140, field.getPreferredSize().height + 60);
        field_card.setCardBackground(ColorUtils.background_light_blue);
        Dimension dim2 = new Dimension(field_card.getInnerWidth() - 40, field_card.getInnerHeight() - 16);
        field.setMinimumSize(dim2);
        field.setPreferredSize(dim2);
        field.setBounds(field.getX(), field.getY() + 20, field.getPreferredSize().width, field.getPreferredSize().height);
        field.setText(new GeneralDataHelper().get(GeneralDataHelper.TODAYS_SPECIAL));
        field_card.addContent(field);

        JButton edit_button = new JButton();
        edit_button.setText("Edit");
        edit_button.setBorder(new EmptyBorder(0, 0, 0, 0));
        edit_button.setMinimumSize(new Dimension(fragment_width / 4, 30));
        edit_button.setPreferredSize(new Dimension(fragment_width / 4, 30));
        edit_button.setBackground(ColorUtils.selected);

        edit_button.addActionListener(updateValue());

        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 0;
        c.gridx = 0;
        add(label, c);
        c.gridy = 1;
        add(field_card, c);
        c.gridy = 2;
        add(edit_button, c);
    }

    private ActionListener updateValue() {
        return e -> {
            new CoffeeChooser(new OnClickListener() {
                @Override
                public void onCLick(JComponent view) {

                }

                @Override
                public void getCoffee(CoffeeData data, String selected_price) {
                    boolean result = new GeneralDataHelper().set(GeneralDataHelper.TODAYS_SPECIAL, data.getName());
                    if (result)
                        new Toast("Today's Special Updated", false).showtoast();
                    else new Toast("Error: Can't Update Special", true).showtoast();
                    revalidate();
                    InstanceHelper.gotoFragment(InstanceHelper.FRAGMENT_TODAYS_SPECIAL);
                    repaint();
                }
            });
        };
    }

}
