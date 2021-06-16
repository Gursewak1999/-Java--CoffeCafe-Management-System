package helper.db;

import DataClasses.CoffeeData;
import DataClasses.Orders;
import DataClasses.Transactions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.util.Pair;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TransactionsHelper {

    private final String ORDER_ID = "order_id";
    private final String CUSTOMER_NAME = "customer_name";
    private final String CUSTOMER_CONTACT = "customer_contact";
    private final String GRAND_TOTAL = "grand_total";

    private final String table_name = "transactions";
    private final DatabaseHelper db;

    public TransactionsHelper() {
        db = new DatabaseHelper();
    }

    public boolean addNewTransaction(Orders order) {
        String query = "INSERT INTO `" + table_name + "`(`order_id`, `customer_name`, `customer_contact`, `grand_total`) VALUES "
                + "('" + order.getTimestamp() + "'"
                + ",'" + order.getCustomer_name() + "'"
                + ",'" + order.getCustomer_contact() + "'"
                + ",'" + order.getGrand_total() + "'"
                + ")";

        try {
            return db.executeQuery(query) == 1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public ArrayList<Transactions> getAll() {

        String query = "SELECT * FROM `" + table_name + "` WHERE 1";

        ResultSet result = null;
        try {
            result = db.executeSelectQuery(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        ArrayList<Transactions> arrayList = new ArrayList<>();

        if (result != null) {
            try {
                //result.first();
                while (result.next()) {

                    Transactions order = new Transactions();
                    order.setOrder_id(result.getString(ORDER_ID));
                    order.setCustomer_contact(result.getString(CUSTOMER_CONTACT));
                    order.setCustomer_name(result.getString(CUSTOMER_NAME));
                    order.setGrand_total(result.getInt(GRAND_TOTAL));

                    arrayList.add(order);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return arrayList;
    }


    public Transactions get(String timestamp) {

        String query = "SELECT * FROM `" + table_name + "` WHERE `order_id` = '" + timestamp + "'";

        ResultSet result = null;
        try {
            result = db.executeSelectQuery(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        Transactions trxn = new Transactions();
        if (result != null) {
            try {
                //result.first();
                result.next();
                trxn.setOrder_id(result.getString(ORDER_ID));
                trxn.setCustomer_contact(result.getString(CUSTOMER_CONTACT));
                trxn.setCustomer_name(result.getString(CUSTOMER_NAME));
                trxn.setGrand_total(result.getInt(GRAND_TOTAL));

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return trxn;
    }


}
