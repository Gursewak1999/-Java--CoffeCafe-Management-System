package DataClasses;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import helper.Utils;
import javafx.util.Pair;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Orders {

    public static final String TIMESTAMP = "timestamp";
    private String customer_name = "";
    private String customer_contact = "";
    private String timestamp = "";
    private int grand_total = 0;
    private String content = "";
    public static Type typeToken = new TypeToken<ArrayList<Pair<Pair<CoffeeData, String>, Integer>>>() {
    }.getType();

    public Orders() {
        this.timestamp = Utils.generateId();
    }

    public Orders(String timestamp, ArrayList<Pair<Pair<CoffeeData, String>, Integer>> content) {

        this.timestamp = timestamp;
        this.content = new Gson().toJson(content);
        this.grand_total = 0;
    }

    public Orders(String customer_name, String customer_contact, String timestamp, int grand_total, ArrayList<Pair<Pair<CoffeeData, String>, Integer>> content) {
        this.customer_name = customer_name;
        this.customer_contact = customer_contact;
        this.timestamp = timestamp;
        this.grand_total = grand_total;
        this.content = new Gson().toJson(content);
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getGrand_total() {
        return grand_total;
    }

    public void setGrand_total(int grand_total) {
        this.grand_total = grand_total;
    }

    public ArrayList<Pair<Pair<CoffeeData, String>, Integer>> getContent() {
        Gson gson = new Gson();
        return gson.fromJson(content, typeToken);
    }
    public String getContentAsString() {
        return content;
    }

    public void setContent(ArrayList<Pair<Pair<CoffeeData, String>, Integer>> content) {
        this.content = new Gson().toJson(content);
    }

    @Override
    public String toString() {
        return "Orders{" +
                ", timestamp='" + timestamp + '\'' +
                ", grand_total=" + grand_total +
                ", content=" + content +
                '}';
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_contact() {
        return customer_contact;
    }

    public void setCustomer_contact(String customer_contact) {
        this.customer_contact = customer_contact;
    }
}
