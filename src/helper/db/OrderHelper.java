package helper.db;

import DataClasses.CoffeeData;
import DataClasses.Orders;
import com.google.gson.Gson;
import javafx.util.Pair;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderHelper {

    private final String TIMESTAMP = "timestamp";
    private final String CUSTOMER_NAME = "customer_name";
    private final String CUSTOMER_CONTACT = "customer_contact";
    private final String GRAND_TOTAL = "grand_total";
    private final String DETAILS = "details";
    private final String table_name = "orders";
    private final DatabaseHelper db;

    public OrderHelper() {
        db = new DatabaseHelper();
    }

    public boolean addNewOrder(Orders order) {
        String query = "INSERT INTO `" + table_name + "`(`timestamp`, `customer_name`, `customer_contact`, `grand_total`, `details`) VALUES "
                + "('" + order.getTimestamp() + "'"
                + ",'" + order.getCustomer_name() + "'"
                + ",'" + order.getCustomer_contact() + "'"
                + ",'" + order.getGrand_total() + "'"
                + ",'" + order.getContentAsString() + "'"
                + ")";

        ArrayList<Pair<Pair<CoffeeData, String>, Integer>> ar = order.getContent();

        for(Pair<Pair<CoffeeData, String>, Integer> pr : ar){
            String cofName = pr.getKey().getKey().getName();
            int quan = pr.getValue();

            String qur = "SELECT `quantity` FROM `coffee_menu` WHERE `name`='"+cofName+"'";
            try {
                ResultSet re = db.executeSelectQuery(qur);
                int avail = 0;
                if(re.next())
                    avail = re.getInt(CoffeeData.QUANTITY);
                else continue;

                int rem = avail-quan;

                qur = "UPDATE `coffee_menu` SET `quantity`="+rem+" WHERE `name`='"+cofName+"'";
                db.executeQuery(qur);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        try {
            boolean result = db.executeQuery(query) == 1;

            if (result)
                new TransactionsHelper().addNewTransaction(order);

            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public ArrayList<Orders> getAll() {

        String query = "SELECT * FROM `" + table_name + "` WHERE 1";

        ResultSet result = null;
        try {
            result = db.executeSelectQuery(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        ArrayList<Orders> arrayList = new ArrayList<>();

        if (result != null) {
            try {
                //result.first();
                while (result.next()) {

                    Orders order = new Orders();
                    order.setTimestamp(result.getString(TIMESTAMP));
                    order.setCustomer_contact(result.getString(CUSTOMER_CONTACT));
                    order.setCustomer_name(result.getString(CUSTOMER_NAME));
                    order.setGrand_total(result.getInt(GRAND_TOTAL));
                    Gson gson = new Gson();
                    order.setContent(gson.fromJson(result.getString(DETAILS), Orders.typeToken));
                    arrayList.add(order);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return arrayList;
    }

    public Orders get(String timestamp) {

        String query = "SELECT * FROM `" + table_name + "` WHERE `timestamp` = '" + timestamp + "'";

        ResultSet result = null;
        try {
            result = db.executeSelectQuery(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        Orders order = new Orders();
        if (result != null) {
            try {
                //result.first();
                result.next();
                order.setTimestamp(result.getString(TIMESTAMP));
                order.setCustomer_contact(result.getString(CUSTOMER_CONTACT));
                order.setCustomer_name(result.getString(CUSTOMER_NAME));
                order.setGrand_total(result.getInt(GRAND_TOTAL));
                Gson gson = new Gson();
                order.setContent(gson.fromJson(result.getString(DETAILS), Orders.typeToken));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return order;
    }

}
