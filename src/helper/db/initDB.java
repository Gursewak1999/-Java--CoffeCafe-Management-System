package helper.db;

import DataClasses.CoffeeData;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import com.sun.deploy.cache.BaseLocalApplicationProperties;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class initDB {

    private ArrayList<String> cover = new ArrayList<>();
    private ArrayList<String> names = new ArrayList<>();
    private ArrayList<String> small = new ArrayList<>();
    private ArrayList<String> medium = new ArrayList<>();
    private ArrayList<String> large = new ArrayList<>();
    private ArrayList<String> desc = new ArrayList<>();

    public initDB() {
        names.add("Coffee Americano");
        names.add("Decaf");
        names.add("Coffee Milk");
        names.add("Cafe Mocha");
        names.add("Espresso Romano");
        names.add("Cappuccino");
        names.add("Cafe Zorro");
        names.add("Guillermo");
        names.add("Greek Frapped");
        names.add("Green Eye");
        names.add("Raspberry Mocha");
        names.add("Red Tie");
        names.add("YuanYang");

        cover.add("coffee_americano");
        cover.add("decaf");
        cover.add("coffee_milk");
        cover.add("cafe_mocha");
        cover.add("espresso_romano");
        cover.add("cappuccino");
        cover.add("cafe_zorro");
        cover.add("guillermo");
        cover.add("greek_frapped");
        cover.add("green_eye");
        cover.add("raspberry_mocha");
        cover.add("red_tie");
        cover.add("yuanyang");


        desc.add("Style of coffee prepared by \nadding hot waterto espresso,\ngiving a similar strength to but\ndifferent flavor from regular drip\ncoffee.");
        desc.add("Beverage made with\ndecaffeinated beans.");
        desc.add("Drink similar to chocolate milk;\nhowever, instead of chocolate\nsyrup, coffee syrup is used.");
        desc.add("It is typically one third espresso\nand two thirds steamed milk,\nbut a portion of chocolate is\nadded, typically in the form\nof a chocolate syrup, although\nother vending systems use\ninstant chocolate powder");
        desc.add("a shot of espresso with a small\nring of lemon and sugar added");
        desc.add("Coffee-based drink prepared\nwith espresso, hot milk, and steamed\nmilk foam.");
        desc.add("Double espresso added to\nhot water. ratio 1:1");
        desc.add("One or two shots of hot\nespresso, poured over slices of\nlime it can also be served\non ice, sometimes with a touch of milk.");
        desc.add("Foam-covered iced coffee\ndrink made from spray-dried\ninstant coffee.");
        desc.add("Dripped coffee with a\ntriple shot of espresso.");
        desc.add("A regular mocha with\nraspberry flavoring.");
        desc.add("A traditional\nThai Iced Tea,\nwhich is a spicy and sweet");
        desc.add("Made of a mixture of\ncoffee and Hong Kong-style\nmilk tea.");


        small.add("120");
        medium.add("145");
        large.add("178");


        small.add("88");
        medium.add("120");
        large.add("0");

        small.add("90");
        medium.add("100");
        large.add("120");

        small.add("135");
        medium.add("145");
        large.add("165");

        small.add("188");
        medium.add("224");
        large.add("244");

        small.add("0");
        medium.add("122");
        large.add("144");

        small.add("0");
        medium.add("190");
        large.add("220");

        small.add("0");
        medium.add("179");
        large.add("196");

        small.add("65");
        medium.add("75");
        large.add("90");

        small.add("123");
        medium.add("135");
        large.add("142");

        small.add("126");
        medium.add("145");
        large.add("0");

        small.add("154");
        medium.add("166");
        large.add("179");

        small.add("97");
        medium.add("127");
        large.add("149");

        ArrayList<CoffeeData> coffeeData = new ArrayList<>();
        Random random = new Random();
        DatabaseHelper db = new DatabaseHelper();
        for (int i = 0; i < names.size(); i++) {

            String query = "INSERT INTO `coffee_menu`(`name`, `description`, `quantity`, `price_small`, `price_medium`, `price_large`, `cover`) VALUES "
                    + "('" + names.get(i) + "' , "
                    + "'" + desc.get(i) + "' , "
                    + "'" + random.nextInt(20) + "' , "
                    + "'" + Integer.parseInt(small.get(i)) + "' , "
                    + "'" + Integer.parseInt(medium.get(i)) + "' , "
                    + "'" + Integer.parseInt(large.get(i)) + "' , "
                    + "'" + "http://localhost/coffee/img/"+cover.get(i)+".jpg" + "')";
            try {
                db.executeQuery(query);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }

    }
}
