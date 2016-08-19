package mx.com.agutierrezm.mynasaapp.iu.view.apod.list.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import mx.com.agutierrezm.mynasaapp.R;
import mx.com.agutierrezm.mynasaapp.model.favorite;

/**
 * Created by Administrator on 19/08/2016.
 */
public class FavoriteAdapter extends RecyclerView.Adapter<NassaApodViewHolder> {

    private List<favorite> favorites;

    //public FavoriteAdapter(List<favorite> favorites) {this.favorites=favorites;}

    @Override
    public NassaApodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NassaApodViewHolder((LayoutInflater.from(parent.getContext()).inflate(R.layout.nasa_apod_item,parent,false)));
    }

    public void setFavoritePhotos(List<favorite> favorites){
        this.favorites = favorites;
    }


    @Override
    public void onBindViewHolder(NassaApodViewHolder holder, int position) {
        favorite fav = favorites.get(position);
        String url = fav.getUrl();
        //Log.d("photo", photo.getEarthDate());
        //Picasso.with(holder.item_apod_image.getContext()).load(url).into(holder.item_apod_image);
        holder.item_apod_image.setImageURI(url);
        holder.item_apod_text.setText(String.valueOf(fav.getId()));

    }

    @Override
    public int getItemCount() {
        return favorites != null ? favorites.size() : 0;
    }
}
