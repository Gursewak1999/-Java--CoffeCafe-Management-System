package DataClasses;

import com.google.gson.annotations.SerializedName;

public class CoffeeData {

    public static final String NAME="name";
    public static final String QUANTITY="quantity";
    public static int minQuantity=1;

    @SerializedName(NAME)
    private String name = "";

    @SerializedName("description")
    private String description = "";

    @SerializedName(QUANTITY)
    private int quantity = 0;

    @SerializedName("price_small")
    private int price_small = 0;

    @SerializedName("price_medium")
    private int price_medium = 0;

    @SerializedName("price_large")
    private int price_large = 0;

    @SerializedName("cover")
    private String cover = "";

    public CoffeeData() {
    }

    public CoffeeData(String name, String description, int quantity, int price_small, int price_medium, int price_large) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price_small = price_small;
        this.price_medium = price_medium;
        this.price_large = price_large;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice_small() {
        return price_small;
    }

    public void setPrice_small(int price_small) {
        this.price_small = price_small;
    }

    public int getPrice_medium() {
        return price_medium;
    }

    public void setPrice_medium(int price_medium) {
        this.price_medium = price_medium;
    }

    public int getPrice_large() {
        return price_large;
    }

    public void setPrice_large(int price_large) {
        this.price_large = price_large;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    @Override
    public String toString() {
        return "CoffeeData{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", price_small=" + price_small +
                ", price_medium=" + price_medium +
                ", price_large=" + price_large +
                ", cover=" + cover +
                '}';
    }
}
