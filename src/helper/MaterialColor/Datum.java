
package helper.MaterialColor;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Datum implements Serializable
{

    @SerializedName("ar_name")
    @Expose
    private String arName;
    @SerializedName("ar")
    @Expose
    private List<Ar> ar = null;


    public Datum() {
    }

    public Datum(String arName, List<Ar> ar) {
        super();
        this.arName = arName;
        this.ar = ar;
    }

    public String getArName() {
        return arName;
    }

    public void setArName(String arName) {
        this.arName = arName;
    }

    public List<Ar> getAr() {
        return ar;
    }

    public void setAr(List<Ar> ar) {
        this.ar = ar;
    }

    @Override
    public String toString() {
        return "Datum{" +
                "arName='" + arName + '\'' +
                ", ar=" + ar +
                '}';
    }
}
