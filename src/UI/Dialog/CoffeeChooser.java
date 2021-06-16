package UI.Dialog;

import DataClasses.CoffeeData;
import UI.custom.CoffeeListItem;
import helper.Utils;
import helper.db.CoffeeHelper;
import inferences.OnClickListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class CoffeeChooser {
    private static JDialog d;
    private OnClickListener onClick;

    public CoffeeChooser(OnClickListener onClickListener) {

        this.onClick = onClickListener;
        JFrame f = new JFrame();
        d = new JDialog(f, "Choose Your Coffee", true);
        d.setLayout(new FlowLayout());

        ArrayList<CoffeeData> coffeeData = new CoffeeHelper().getAllCoffee();

        for (CoffeeData data : coffeeData)
            d.add(new CoffeeListItem(400, 120, data, onClick, () -> {
                CoffeeChooser.d.setVisible(false);
                return null;
            }));
        //
        d.setSize(Utils.max_width, Utils.max_height);
        d.setVisible(true);

    }
}
