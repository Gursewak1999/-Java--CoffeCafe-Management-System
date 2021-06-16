package helper.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GeneralDataHelper {

    public static final String TODAYS_TARGET = "todays_target";
    public static final String TODAY_ORDER = "order_today";
    public static final String MONTH_ORDER = "order_month";
    public static final String PROFIT_TODAY = "profit_today";
    private DatabaseHelper db;

    public static final String TODAYS_SPECIAL = "todays_special";
    public static final String NOTICE_BOARD_KEY = "notice_board";

    public GeneralDataHelper() {
        db = new DatabaseHelper();
    }

    public boolean addNew(String key, String value) {

        String query = "INSERT INTO `general_data`(`name`, `value`) VALUES ('" + key + "','" + value + "')";

        try {
            Statement stmt = db.getConnection().createStatement();
            ResultSet temp = stmt.executeQuery(query);

            boolean isFound = false;
            while (temp.next()) {
                isFound = true;
                break;
            }
            return isFound;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    public boolean set(String key, String value) {

        String query = "UPDATE `general_data` SET `value`='" + value + "' WHERE `name` = '" + key + "'";

        System.out.println(query);
        try {
            Statement stmt = db.getConnection().createStatement();
            int temp = stmt.executeUpdate(query);
            stmt.close();
            return temp==1;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    public String get(String key) {

        String query = "SELECT * FROM `general_data` WHERE name = '" + key + "'";

        try {
            Statement stmt = db.getConnection().createStatement();
            ResultSet temp = stmt.executeQuery(query);

            boolean isFound = false;
            while (temp.next()) {
                isFound = true;
                break;
            }
            if (isFound)
                return temp.getString("value");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "";
    }

    public String calculateTodayProfit() {
        int profit = 10;

        String query = "";

        return String.valueOf(profit);
    }
}
