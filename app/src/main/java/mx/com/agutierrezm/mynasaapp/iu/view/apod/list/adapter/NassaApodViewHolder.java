package mx.com.agutierrezm.mynasaapp.iu.view.apod.list.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.com.agutierrezm.mynasaapp.R;

/**
 * Created by Alumno on 05/08/2016.
 */
public class NassaApodViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.item_apod_image)
    ImageView item_apod_image;
    @BindView(R.id.item_apod_text)
    TextView item_apod_text;

    public NassaApodViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
