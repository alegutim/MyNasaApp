package mx.com.agutierrezm.mynasaapp.model;



import com.google.gson.annotations.SerializedName;

public class Camera_ {

    @SerializedName("name")
    private String name;
    @SerializedName("full_name")

    private String fullName;

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     *
     * @param fullName
     * The full_name
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}