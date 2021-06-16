package UI.custom;

import DataClasses.CoffeeData;
import inferences.OnClickListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.concurrent.Callable;

public class CoffeeListItem extends MaterialCardView {

    private JLabel title_label;
    private JImagePanel cover;
    private JButton button_lower, button_higher, button_medium;
    private OnClickListener listener;
    private Callable<Void> exitMethod;

    public CoffeeListItem(int width, int height, CoffeeData data, OnClickListener onClick, Callable<Void> exit) {
        super(width, height);

        this.exitMethod = exit;
        this.listener = onClick;
        cover = new JImagePanel();
        title_label = new JLabel(data.getName());
        this.setToolTipText(data.getDescription());
        button_lower = new JButton();
        button_medium = new JButton();
        button_higher = new JButton();
        button_lower.setText("S - " + data.getPrice_small() + "/-");
        button_medium.setText("M - " + data.getPrice_medium() + "/-");
        button_higher.setText("L - " + data.getPrice_large() + "/-");

        button_higher.setActionCommand("L");
        button_lower.setActionCommand("S");
        button_medium.setActionCommand("M");

        if(data.getQuantity()<CoffeeData.minQuantity){
            title_label.setText(title_label.getText()+"  (Not Available)");
            title_label.setForeground(Color.RED);
            button_lower.setEnabled(false);
            button_medium.setEnabled(false);
            button_higher.setEnabled(false);
        }

        button_lower.addActionListener(onButtonClick(data, "S"));
        button_medium.addActionListener(onButtonClick(data, "M"));
        button_higher.addActionListener(onButtonClick(data, "L"));

        cover.setBounds(10, 10, getInnerHeight() - 20, getInnerHeight() - 20);
        title_label.setBounds(
                height - 20,
                10,
                title_label.getPreferredSize().width,
                title_label.getPreferredSize().height);
        button_lower.setBounds(
                height - 20,
                title_label.getPreferredSize().height + 20 + 10,
                90, 18);
        button_medium.setBounds(
                height + 90 - 20,
                title_label.getPreferredSize().height + 20 + 10,
                90, 18);
        button_higher.setBounds(
                height + 90 + 90 - 20,
                title_label.getPreferredSize().height + 20 + 10,
                90, 18);

        cover.setRoundedBackgroundImage(data.getCover());

        this.content_panel.setLayout(null);
        this.addContent(title_label);
        this.addContent(cover);
        this.addContent(button_higher);
        this.addContent(button_lower);
        this.addContent(button_medium);

    }

    private ActionListener onButtonClick(CoffeeData data, String s) {
        return e -> {
            listener.getCoffee(data, s);
            try {
                exitMethod.call();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        };
    }
}
