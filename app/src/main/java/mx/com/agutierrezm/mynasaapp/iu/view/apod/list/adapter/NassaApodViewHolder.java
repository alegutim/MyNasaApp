package mx.com.agutierrezm.mynasaapp.iu.view.apod.list.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mx.com.agutierrezm.mynasaapp.R;
import mx.com.agutierrezm.mynasaapp.model.Photo;

/**
 * Created by Alumno on 05/08/2016.
 */
public class NassaApodViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.item_apod_image)
    SimpleDraweeView item_apod_image;
    @BindView(R.id.item_apod_text)
    TextView item_apod_text;

    private NassaApodAdapter.OnItemClickListener onItemListener;
    private Photo photo;

    public NassaApodViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void setItemClick(Photo photo, NassaApodAdapter.OnItemClickListener onItemListener){
        this.photo = photo;
        this.onItemListener = onItemListener;
    }

    @OnClick(R.id.item_apod_image)
    public void onViewCLick(View view){
        if (onItemListener!=null){
            onItemListener.onItemClick(photo);
        }
    }
}
