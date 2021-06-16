package DataClasses;

public class Transactions {

    public String customer_name = "";
    public String customer_contact = "";
    public int grand_total = 0;
    public String order_id = "";

    public Transactions(Orders order) {
        this.customer_contact = order.getCustomer_contact();
        this.customer_name = order.getCustomer_name();
        this.grand_total = order.getGrand_total();
        this.order_id = order.getTimestamp();
    }

    public Transactions() {
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

    public int getGrand_total() {
        return grand_total;
    }

    public void setGrand_total(int grand_total) {
        this.grand_total = grand_total;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    @Override
    public String toString() {
        return "Transactions{" +
                "customer_name='" + customer_name + '\'' +
                ", customer_contact='" + customer_contact + '\'' +
                ", grand_total=" + grand_total +
                ", order_id='" + order_id + '\'' +
                '}';
    }
}
