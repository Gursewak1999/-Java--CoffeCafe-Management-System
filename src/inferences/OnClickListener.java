package inferences;

import DataClasses.CoffeeData;

import javax.swing.*;

public interface OnClickListener {

    public void onCLick(JComponent view);

    public void getCoffee(CoffeeData data, String selected_price);
}
