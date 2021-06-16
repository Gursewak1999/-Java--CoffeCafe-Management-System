package helper.db;

import DataClasses.CoffeeData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CoffeeHelper {

    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String PRICE_LARGE = "price_large";
    private static final String QUANTITY = "quantity";
    private static final String PRICE_MEDIUM = "price_medium";
    private static final String PRICE_SMALL = "price_small";
    private static final String COVER = "cover";
    private final String table_name = "coffee_menu";
    private final DatabaseHelper db;

    public CoffeeHelper() {
        db = new DatabaseHelper();
    }

    public boolean add_coffee(CoffeeData coffeeData) {
        String query = "INSERT INTO `" + table_name
                + "` ( `name`, `description`, `quantity`, `price`) "
                + "VALUES ('" + coffeeData.getName()
                + "','" + coffeeData.getDescription()
                + "','" + coffeeData.getQuantity()
                + "','" + coffeeData.getPrice_small()
                + "','" + coffeeData.getPrice_medium()
                + "','" + coffeeData.getPrice_large() + "')";

        int result = 0;
        try {
            result = db.executeQuery(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result != 0;
    }

    public boolean delete_coffee(String name) {
        String query = "DELETE FROM `" + table_name + "` WHERE name='" + name + "'";

        int result = 0;
        try {
            result = db.executeQuery(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result != 0;
    }

    public boolean update_coffee(String name, CoffeeData data) {
        String query = "UPDATE `coffee_menu` SET " +
                "`name`='" + data.getName() + "'," +
                "`description`='" + data.getDescription() + "'," +
                "`quantity`=" + data.getQuantity() + "," +
                "`price_small`=" + data.getPrice_small() +"," +
                "`price_medium`=" + data.getPrice_medium() +"," +
                "`price_large`=" + data.getPrice_large() +"" +
                " WHERE name='" + name + "'";

        int result = 0;
        try {
            result = db.executeQuery(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result == 1;
    }

    public CoffeeData getCoffee(String name) {

        String query = "SELECT * FROM `coffee_menu` WHERE `name` = '" + name + "'";

        ResultSet result = null;
        try {
            result = db.executeSelectQuery(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        CoffeeData coffee = new CoffeeData();
        if (result != null) {
            try {
                //result.first();
                result.next();
                coffee.setName(result.getString(CoffeeHelper.NAME));
                coffee.setDescription(result.getString(CoffeeHelper.DESCRIPTION));
                coffee.setQuantity(result.getInt(CoffeeHelper.QUANTITY));
                coffee.setPrice_large(result.getInt(CoffeeHelper.PRICE_LARGE));
                coffee.setPrice_medium(result.getInt(CoffeeHelper.PRICE_MEDIUM));
                coffee.setPrice_small(result.getInt(CoffeeHelper.PRICE_SMALL));
                coffee.setCover(result.getString(CoffeeHelper.COVER));

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return coffee;
    }

    public ArrayList<CoffeeData> getAllCoffee() {

        String query = "SELECT * FROM `coffee_menu` WHERE 1";

        ResultSet result = null;
        try {
            result = db.executeSelectQuery(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        ArrayList<CoffeeData> coffeeDataArrayList = new ArrayList<>();

        if (result != null) {
            try {
                //result.first();
                while (result.next()) {

                    CoffeeData coffee = new CoffeeData();
                    coffee.setName(result.getString(CoffeeHelper.NAME));
                    coffee.setDescription(result.getString(CoffeeHelper.DESCRIPTION));
                    coffee.setQuantity(result.getInt(CoffeeHelper.QUANTITY));
                    coffee.setPrice_large(result.getInt(CoffeeHelper.PRICE_LARGE));
                    coffee.setPrice_medium(result.getInt(CoffeeHelper.PRICE_MEDIUM));
                    coffee.setPrice_small(result.getInt(CoffeeHelper.PRICE_SMALL));
                    coffee.setCover(result.getString(CoffeeHelper.COVER));
                    coffeeDataArrayList.add(coffee);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return coffeeDataArrayList;
    }
}
