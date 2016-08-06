package mx.com.agutierrezm.mynasaapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.com.agutierrezm.mynasaapp.model.Photo;
import mx.com.agutierrezm.mynasaapp.util.Comunicador;

public class DetailActivity extends AppCompatActivity {
    @BindView(R.id.img_img)SimpleDraweeView img_img;
    @BindView(R.id.img_id)TextView img_id;
    @BindView(R.id.img_earth_day)TextView img_earth_day;
    @BindView(R.id.img_sol)TextView img_sol;
    @BindView(R.id.img_rover)TextView img_rover;
    @BindView(R.id.img_camera)TextView img_camera;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        Photo photo= (Photo) Comunicador.getObjeto();

        String url = photo.getImgSrc();
        img_img.setImageURI(url);
        img_id.setText(String.valueOf(photo.getId()));
        img_earth_day.setText(photo.getEarthDate());
        img_sol.setText(String.valueOf(photo.getSol()));
        img_rover.setText(photo.getRover().getName());
        img_camera.setText(photo.getCamera().getName());

    }
}
