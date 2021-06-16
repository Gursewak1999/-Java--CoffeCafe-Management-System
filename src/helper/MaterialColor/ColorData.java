package helper.MaterialColor;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ColorData implements Serializable
{

    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

    public ColorData() {
    }

    public ColorData(List<Datum> data) {
        super();
        this.data = data;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ColorData{" +
                "data=" + data +
                '}';
    }
}
