package mx.com.agutierrezm.mynasaapp.iu.view.apod.list.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import mx.com.agutierrezm.mynasaapp.R;
import mx.com.agutierrezm.mynasaapp.model.Apod;
import mx.com.agutierrezm.mynasaapp.model.Photo;

/**
 * Created by Alumno on 05/08/2016.
 */
public class NassaApodAdapter extends RecyclerView.Adapter<NassaApodViewHolder>{
    private List<Photo> photos;
    private OnItemClickListener onItemClickListener;
    //private List<Photo> marsPhotos;

    public NassaApodAdapter() {}
    public NassaApodAdapter(List<Photo> photos) {this.photos=photos;}

    @Override
    public NassaApodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NassaApodViewHolder((LayoutInflater.from(parent.getContext()).inflate(R.layout.nasa_apod_item,parent,false)));
    }

    @Override
    public void onBindViewHolder(NassaApodViewHolder holder, int position) {
        Photo photo = photos.get(position);
        String url = photo.getImgSrc();
        //Log.d("photo", photo.getEarthDate());
        //Picasso.with(holder.item_apod_image.getContext()).load(url).into(holder.item_apod_image);
        holder.item_apod_image.setImageURI(url);
        holder.item_apod_text.setText(String.valueOf(photo.getId()));
        holder.setItemClick(photo,onItemClickListener);

    }


    public void setOnItemClickListener( OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public void setMarsPhotos(List<Photo> photos){
        this.photos = photos;
    }


    @Override
    public int getItemCount() {
        return photos != null ? photos.size() : 0;
    }


    public interface  OnItemClickListener{
        void onItemClick(Photo photo);
    }
}
