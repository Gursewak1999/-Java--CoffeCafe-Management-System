package helper.MaterialColor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MaterialColorHelper {

    private ColorData data;
    private String previous_color;
    public static Type typeToken = new TypeToken<ArrayList<ColorData>>() {
    }.getType();

    public MaterialColorHelper() {

        Gson gson = new Gson();
        try {
            File file = new File("res/color.txt");

            BufferedReader br = new BufferedReader(new FileReader(file));
            StringBuilder content = new StringBuilder();
            String st;
            while ((st = br.readLine()) != null)
                content.append(st);

            data = gson.fromJson(content.toString(), ColorData.class);
        } catch (Exception fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }

    }

    public String getRandomColor() {
        List<Ar> temp = data.getData()
                .get(new Random().nextInt(data.getData().size() - 1))
                .getAr();
        String color = temp.get(new Random().nextInt(temp.size() - 1))
                .getColor();
        if (previous_color!=null && previous_color.equals(color))
            return getRandomColor();
        else return color;
    }

    public String getRandomColor(int i) {
        List<Ar> temp = data.getData()
                .get(i)
                .getAr();
        return temp.get(new Random().nextInt(temp.size() - 1))
                .getColor();
    }
}
