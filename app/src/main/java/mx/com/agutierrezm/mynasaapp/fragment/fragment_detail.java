package mx.com.agutierrezm.mynasaapp.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.com.agutierrezm.mynasaapp.R;
import mx.com.agutierrezm.mynasaapp.model.Photo;
import mx.com.agutierrezm.mynasaapp.sql.AppDataSource;
import mx.com.agutierrezm.mynasaapp.util.Comunicador;

/**
 * Created by Administrator on 19/08/2016.
 */
public class fragment_detail extends Fragment{

    AppDataSource appDataSource;
    String url;
    View view;
    @BindView(R.id.img_img)SimpleDraweeView img_img;
    @BindView(R.id.img_id)TextView img_id;
    @BindView(R.id.img_earth_day)TextView img_earth_day;
    @BindView(R.id.img_sol)TextView img_sol;
    @BindView(R.id.img_rover)TextView img_rover;
    @BindView(R.id.img_camera)TextView img_camera;

    @Nullable

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        appDataSource = new AppDataSource(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_detail, container, false);
        ButterKnife.bind(this,view);


        Photo photo= (Photo) Comunicador.getObjeto();

        url = photo.getImgSrc();
        img_img.setImageURI(url);
        img_id.setText(String.valueOf(photo.getId()));
        img_earth_day.setText(photo.getEarthDate());
        img_sol.setText(String.valueOf(photo.getSol()));
        img_rover.setText(photo.getRover().getName());
        img_camera.setText(photo.getCamera().getName());

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.listing_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_favorite_listing:
                Snackbar.make(getView(),"Added", Snackbar.LENGTH_SHORT).show();
                appDataSource.saveFavorite(url);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
