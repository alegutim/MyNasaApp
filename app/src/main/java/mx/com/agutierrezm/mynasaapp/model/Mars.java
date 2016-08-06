package mx.com.agutierrezm.mynasaapp.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;


public class Mars {

    @SerializedName("photos")
    private List<Photo> photos = new ArrayList<Photo>();

    /**
     *
     * @return
     * The photos
     */
    public List<Photo> getPhotos() {
        return photos;
    }

    /**
     *
     * @param photos
     * The photos
     */
    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

}