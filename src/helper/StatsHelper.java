package helper;

import DataClasses.Orders;
import helper.db.DatabaseHelper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StatsHelper {

    private final int profit_per_coffee = 45;
    DatabaseHelper db = new DatabaseHelper();

    public StatsHelper() {
        try {
            System.out.println("Today Orders : " + calculateTodayOrders());
            System.out.println("Month Orders : " + calculateMonthlyOrders());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public int calculateTodayOrders() throws SQLException {

        int count = 0;
        String qr = "SELECT `timestamp` FROM `orders` WHERE 1";

        ResultSet res = db.executeSelectQuery(qr);
        while (res.next()) {
            String timestamp = res.getString(Orders.TIMESTAMP);
            if (Utils.convertToDay(timestamp).equals(Utils.convertToDay(String.valueOf(System.currentTimeMillis())))) {
                count++;
            }
        }

        return count;
    }

    public int calculateMonthlyOrders() throws SQLException {
        int count = 0;
        String qr = "SELECT `timestamp` FROM `orders` WHERE 1";

        ResultSet res = db.executeSelectQuery(qr);
        while (res.next()) {
            String timestamp = res.getString(Orders.TIMESTAMP);
            if (Utils.convertToMonth(timestamp).equals(Utils.convertToMonth(String.valueOf(System.currentTimeMillis())))) {
                count++;
            }
        }

        return count;
    }

    public int calculateTodayProfit() throws SQLException {
        return calculateTodayOrders() * profit_per_coffee;
    }

    public int calculateMonthlyProfit() throws SQLException {
        return calculateMonthlyOrders() * profit_per_coffee;
    }

}