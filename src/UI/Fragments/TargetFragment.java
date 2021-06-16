package UI.Fragments;

import UI.custom.MaterialCardView;
import helper.ColorUtils;
import helper.db.GeneralDataHelper;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class TargetFragment extends JPanel {
    int label_height = 60;
    int field_height = 40;

    public TargetFragment(int fragment_width, int fragment_height) {

        Dimension dim = new Dimension(fragment_width, fragment_height);
        setMinimumSize(dim);
        setPreferredSize(dim);

        setBackground(ColorUtils.background_super_light);

        JLabel label = new JLabel("Edit Today's Target");
        Dimension lab_dim = new Dimension(fragment_width, label_height);
        label.setBorder(new EmptyBorder(20, 60, 20, 60));
        label.setFont(new Font("Poppins", Font.PLAIN, 24));
        label.setMinimumSize(lab_dim);
        label.setPreferredSize(lab_dim);

        JTextField field = new JTextField();
        field.setBorder(new EmptyBorder(0,0,0,0));
        field.setHorizontalAlignment(JTextField.CENTER);
        field.setBackground(ColorUtils.background_light_blue);
        MaterialCardView field_card = new MaterialCardView(fragment_width - 140, field.getPreferredSize().height + 60);
        field_card.setCardBackground(ColorUtils.background_light_blue);
        Dimension dim2 = new Dimension(field_card.getInnerWidth() - 40, field_card.getInnerHeight()-16);
        field.setMinimumSize(dim2);
        field.setPreferredSize(dim2);
        field.setBounds(field.getX(), field.getY() + 20, field.getPreferredSize().width, field.getPreferredSize().height);
        field.setText(new GeneralDataHelper().get(GeneralDataHelper.TODAYS_TARGET));
        field_card.addContent(field);

        JButton edit_button = new JButton();
        edit_button.setText("Edit");
        edit_button.setBorder(new EmptyBorder(0, 0, 0, 0));
        edit_button.setMinimumSize(new Dimension(fragment_width / 4, 30));
        edit_button.setPreferredSize(new Dimension(fragment_width / 4, 30));
        edit_button.setBackground(ColorUtils.selected);

        edit_button.addActionListener(updateValue(field));

        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 0;
        c.gridx = 0;
        add(label, c);
        c.gridy = 1;
        add(field_card, c);
        c.gridy = 2;
        add(edit_button, c);
    }

    private ActionListener updateValue(JTextField text) {
        return e -> {
            System.out.println(new GeneralDataHelper().set(GeneralDataHelper.TODAYS_TARGET, text.getText()));
        };
    }


}
