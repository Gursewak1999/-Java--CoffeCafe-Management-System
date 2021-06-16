package UI.Dialog;

import helper.Utils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.concurrent.Callable;

public class ConfirmationDialog extends JDialog {

    public ConfirmationDialog(String title,
                              String description,
                              String positiveText,
                              String negativeText,
                              Callable<Void> positiveCallable,
                              Callable<Void> negativeCallable) {

        setTitle(title);
        setLayout(new GridBagLayout());
        JPanel panel = new JPanel(new GridLayout(3, 0));

        JLabel desc_label = new JLabel(description);
        desc_label.setFont(new Font("Poppins", Font.PLAIN, 14));
        panel.add(desc_label);
        panel.setBorder(new EmptyBorder(30, 20, 30, 20));

        JPanel button_panel = new JPanel(new GridLayout(0, 2));
        JButton button_positive = new JButton(positiveText);
        JButton button_negative = new JButton(negativeText);
        button_panel.add(button_positive);
        button_panel.add(button_negative);

        button_positive.addActionListener(e -> {
            try {
                positiveCallable.call();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            setVisible(false);
        });
        button_negative.addActionListener(e -> {
            try {
                negativeCallable.call();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            setVisible(false);
        });

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        add(panel, c);
        c.gridy = 1;
        add(button_panel, c);

        setBounds((Utils.max_width - desc_label.getPreferredSize().width + 100) / 2,
                (Utils.max_height - desc_label.getPreferredSize().height + 100) / 2,
                desc_label.getPreferredSize().width + 100,
                getPreferredSize().height + 80);
        setVisible(true);
    }
}