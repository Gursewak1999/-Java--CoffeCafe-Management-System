package UI.custom;

import DataClasses.CoffeeData;
import helper.db.CoffeeHelper;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StatsUI extends JPanel {

    int max = 0;
    int min = 0;
    private ArrayList<Pair<String, Integer>> statsData = new ArrayList<>();

    public StatsUI() {
        initDemo();

        init();
    }

    private void init() {

    }

    private void initDemo() {
        int i = 0;
        for (CoffeeData coff : new CoffeeHelper().getAllCoffee()) {
            if (i == 7)
                break;
            i++;
            statsData.add(new Pair<>(coff.getName(), coff.getQuantity()));
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        int thickness = 3;
        for (int i = 0; i < thickness; i++) {
            g.setColor(Color.BLACK);
            g.drawLine(i, 0, i, getHeight());
        }

        g.setColor(Color.BLACK);
        int blockSize = getHeight() / 10;
        for (int j = 0; j < getHeight(); j++) {
            if (j % blockSize == 0) {
                for (int i = 0; i < thickness; i++)
                    g.drawLine(0, j + i, 10, j + i);
            }
        }
    }
}
