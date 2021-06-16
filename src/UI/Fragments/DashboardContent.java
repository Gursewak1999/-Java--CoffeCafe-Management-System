package UI.Fragments;

import DataClasses.CoffeeData;
import UI.DashboardUI;
import UI.custom.JImagePanel;
import UI.custom.MaterialCardView;
import helper.ColorUtils;
import helper.StatsHelper;
import helper.db.CoffeeHelper;
import helper.db.GeneralDataHelper;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.SQLException;

public class DashboardContent extends JImagePanel {


    private MaterialCardView notice_board_card, today_special_card;
    private MaterialCardView card1, card2, card3, card4;

    public int main_heading_size = 24;
    public int sub_content_size = 18;
    public int small_heading_size = 24;
    public int small_content_size = 28;

    public DashboardContent(DashboardUI parent, int _width, int _height) {

        setUnScaledBackgroundImage("res/drawables/seemless_background.jpg");
        setLayout(null);
        //Today's Special

        today_special_card = new MaterialCardView(_width - 40, 200);
        Insets insets = getInsets();
        today_special_card.setPreferredSize(new Dimension(_width - 40, 200));
        today_special_card.setBounds(20 + insets.left, 20 + insets.top, today_special_card.getWidth(), 200);
        today_special_card.setCardBackground(ColorUtils.background_super_light);

        notice_board_card = new MaterialCardView(today_special_card.getWidth() / 2 - 100, _height - 20 - today_special_card.getHeight() - 40);
        notice_board_card.setPreferredSize(new Dimension(_width - 40, 200));
        notice_board_card.setBounds(
                20 + insets.left,
                20 + today_special_card.getHeight() + insets.top,
                notice_board_card.getWidth(), notice_board_card.getHeight());

        int mini_card_width = (today_special_card.getWidth() - notice_board_card.getWidth()) / 2;
        int mini_card_height = notice_board_card.getHeight() / 2;

        card1 = new MaterialCardView(
                mini_card_width,
                mini_card_height);
        card1.setBounds(
                20 + insets.left + notice_board_card.getWidth(),
                20 + today_special_card.getHeight() + insets.top,
                card1.getWidth(), card1.getHeight());

        card2 = new MaterialCardView(
                mini_card_width,
                mini_card_height);
        card2.setBounds(
                20 + insets.left + notice_board_card.getWidth() + card1.getWidth(),
                20 + today_special_card.getHeight() + insets.top,
                card2.getWidth(), card2.getHeight());

        card3 = new MaterialCardView(
                mini_card_width,
                mini_card_height);
        card3.setBounds(
                20 + insets.left + notice_board_card.getWidth(),
                20 + today_special_card.getHeight() + insets.top + card1.getHeight(),
                card3.getWidth(), card3.getHeight());

        card4 = new MaterialCardView(
                mini_card_width,
                mini_card_height);
        card4.setBounds(
                20 + insets.left + notice_board_card.getWidth() + card1.getWidth(),
                20 + today_special_card.getHeight() + insets.top + card1.getHeight(),
                card4.getWidth(), card4.getHeight());

        this.add(today_special_card);
        this.add(notice_board_card);
        this.add(card1);
        this.add(card2);
        this.add(card3);
        this.add(card4);

        initComponents();
    }

    private void initComponents() {
        setNoticeBoardCard();
        setTodaysSpecial();
        setProfitToday();
        setTarget();
        settupOrders();
        settupOrdersThisMonth();
    }

    private void setTodaysSpecial() {

        JPanel panel = new JPanel();
        panel.setLayout(null);
        String name = new GeneralDataHelper().get(GeneralDataHelper.TODAYS_SPECIAL);
        CoffeeData coffeeData = new CoffeeHelper().getCoffee(name);

        int _w = today_special_card.getInnerWidth(), _h = today_special_card.getInnerHeight();

        JLabel heading = new JLabel(" TODAY's SPECIAL  ");
        heading.setFont(new Font("Poppins", Font.BOLD, 24));

        Insets insets = today_special_card.getInsets();
        heading.setBounds(insets.left + 40, insets.top, heading.getPreferredSize().width, heading.getPreferredSize().height);

        JImagePanel jImagePanel = new JImagePanel();
        jImagePanel.setPreferredSize(new Dimension(_h - heading.getPreferredSize().height,
                _h - heading.getPreferredSize().height));

        jImagePanel.setBounds(insets.left, insets.top + heading.getPreferredSize().height + 10,
                _h - heading.getPreferredSize().height + 40,
                _h - heading.getPreferredSize().height - 20);
        jImagePanel.setRoundedBackgroundImage(coffeeData.getCover());

        JLabel name_label = new JLabel(coffeeData.getName());
        JLabel description_label = new JLabel(String.format("<html><div style=\"width:%dpx;\">%s</div></html>", _w - 400, coffeeData.getDescription()));
        JButton price_lower = new JButton(coffeeData.getPrice_small() + "/- ");
        JButton price_medium = new JButton(coffeeData.getPrice_medium() + "/- ");
        JButton price_upper = new JButton(coffeeData.getPrice_large() + "/- ");

        name_label.setBounds(jImagePanel.getWidth() + jImagePanel.getX() + 40,
                jImagePanel.getY() + 10,
                name_label.getPreferredSize().width + 200,
                name_label.getPreferredSize().height);
        name_label.setFont(new Font("Poppins", Font.BOLD, 16));

        description_label.setBounds(jImagePanel.getWidth() + jImagePanel.getX() + 40,
                name_label.getY() + 10 + name_label.getHeight(),
                description_label.getPreferredSize().width,
                description_label.getPreferredSize().height);

        price_lower.setBounds(jImagePanel.getWidth() + jImagePanel.getX() + 40,
                description_label.getY() + 10 + description_label.getPreferredSize().height,
                price_lower.getPreferredSize().width,
                price_lower.getPreferredSize().height);

        price_medium.setBounds(jImagePanel.getWidth() + jImagePanel.getX() + 40 + price_lower.getPreferredSize().width,
                description_label.getY() + 10 + description_label.getPreferredSize().height,
                price_medium.getPreferredSize().width,
                price_medium.getPreferredSize().height);

        price_upper.setBounds(jImagePanel.getWidth() + jImagePanel.getX() + 40 + 2 * price_lower.getPreferredSize().width,
                description_label.getY() + 10 + description_label.getPreferredSize().height,
                price_upper.getPreferredSize().width,
                price_upper.getPreferredSize().height);

        panel.add(heading);
        panel.add(jImagePanel);
        panel.add(name_label);
        panel.add(description_label);
//        panel.add(price_lower);
//        panel.add(price_medium);
//        panel.add(price_upper);
        panel.setPreferredSize(today_special_card.getPreferredSize());
        panel.setBackground(new Color(0, 0, 0, 0));

        today_special_card.addContent(panel);
    }

    private void setNoticeBoardCard() {
        String notice = new GeneralDataHelper().get(GeneralDataHelper.NOTICE_BOARD_KEY);

        JLabel heading = new JLabel("NOTICE BOARD");
        JLabel content = new JLabel(notice);

        heading.setFont(new Font("Poppins", Font.BOLD, 28));
        content.setFont(new Font("Poppins Black", Font.PLAIN, 18));
        content.setForeground(ColorUtils.secondayText);
        heading.setForeground(ColorUtils.primaryText);
        content.setVerticalAlignment(SwingConstants.CENTER);
        content.setHorizontalAlignment(SwingConstants.LEFT);
        Dimension dim = notice_board_card.getPreferredSize();
        int _w = dim.width, _h = dim.height;

        heading.setBounds(0, 0, _w, heading.getPreferredSize().height);

        content.setBounds(0, heading.getPreferredSize().height, _w, _h - (heading.getPreferredSize().height));
        content.setBorder(new EmptyBorder(40, 0, 0, 0));

        //notice_board_card.setLayout(null);
        notice_board_card.addContent(heading);
        notice_board_card.addContent(content);
    }

    private void setProfitToday() {
        String text = "0";
        try {
            text = String.valueOf(new StatsHelper().calculateTodayProfit());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        JLabel heading = new JLabel("      Today's Profit     ");
        JLabel content = new JLabel(text + "/- ");

        heading.setFont(new Font("Poppins", Font.BOLD, small_heading_size));
        content.setFont(new Font("Poppins Black", Font.PLAIN, small_content_size));
        content.setForeground(ColorUtils.secondayText);
        heading.setForeground(ColorUtils.primaryText);
        content.setVerticalAlignment(SwingConstants.CENTER);
        content.setHorizontalAlignment(SwingConstants.LEFT);
        Dimension dim = notice_board_card.getPreferredSize();
        int _w = dim.width, _h = dim.height;

        heading.setBounds(0, 0, _w, heading.getPreferredSize().height);

        content.setBounds(0, heading.getPreferredSize().height, _w, _h - (heading.getPreferredSize().height));
        content.setBorder(new EmptyBorder(40, 0, 0, 0));

        //notice_board_card.setLayout(null);
        card1.addContent(heading);
        card1.addContent(content);
    }

    private void setTarget() {
        String text = new GeneralDataHelper().get(GeneralDataHelper.TODAYS_TARGET);

        JLabel heading = new JLabel("      Today's Target      ");
        JLabel content = new JLabel(text);

        heading.setFont(new Font("Poppins", Font.BOLD, small_heading_size));
        content.setFont(new Font("Poppins Black", Font.PLAIN, small_content_size));
        content.setForeground(ColorUtils.secondayText);
        heading.setForeground(ColorUtils.primaryText);
        content.setVerticalAlignment(SwingConstants.CENTER);
        content.setHorizontalAlignment(SwingConstants.LEFT);
        Dimension dim = notice_board_card.getPreferredSize();
        int _w = dim.width, _h = dim.height;

        heading.setBounds(0, 0, _w, heading.getPreferredSize().height);

        content.setBounds(0, heading.getPreferredSize().height, _w, _h - (heading.getPreferredSize().height));
        content.setBorder(new EmptyBorder(40, 0, 0, 0));

        //notice_board_card.setLayout(null);
        card2.addContent(heading);
        card2.addContent(content);
    }

    private void settupOrders() {
        String text = "0";
        try {
            text = String.valueOf(new StatsHelper().calculateTodayOrders());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        JLabel heading = new JLabel("      Orders Today      ");
        JLabel content = new JLabel(text);

        content.setForeground(ColorUtils.secondayText);
        heading.setForeground(ColorUtils.primaryText);
        heading.setFont(new Font("Poppins", Font.BOLD, small_heading_size));
        content.setFont(new Font("Poppins Black", Font.PLAIN, small_content_size));
        content.setVerticalAlignment(SwingConstants.CENTER);
        content.setHorizontalAlignment(SwingConstants.LEFT);
        Dimension dim = notice_board_card.getPreferredSize();
        int _w = dim.width, _h = dim.height;

        heading.setBounds(0, 0, _w, heading.getPreferredSize().height);

        content.setBounds(0, heading.getPreferredSize().height, _w, _h - (heading.getPreferredSize().height));
        content.setBorder(new EmptyBorder(40, 0, 0, 0));

        //notice_board_card.setLayout(null);
        card3.addContent(heading);
        card3.addContent(content);
    }

    private void settupOrdersThisMonth() {
        String text = "0";
        try {
            text = String.valueOf(new StatsHelper().calculateMonthlyOrders());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        JLabel heading = new JLabel("Orders This Month");
        JLabel content = new JLabel(text);

        heading.setFont(new Font("Poppins", Font.BOLD, small_heading_size));
        content.setForeground(ColorUtils.secondayText);
        heading.setForeground(ColorUtils.primaryText);
        content.setFont(new Font("Poppins Black", Font.PLAIN, small_content_size));
        content.setVerticalAlignment(SwingConstants.CENTER);
        content.setHorizontalAlignment(SwingConstants.LEFT);
        Dimension dim = notice_board_card.getPreferredSize();
        int _w = dim.width, _h = dim.height;

        heading.setBounds(0, 0, _w, heading.getPreferredSize().height);

        content.setBounds(0, heading.getPreferredSize().height, _w, _h - (heading.getPreferredSize().height));
        content.setBorder(new EmptyBorder(40, 0, 0, 0));

        //notice_board_card.setLayout(null);
        card4.addContent(heading);
        card4.addContent(content);
    }
}
