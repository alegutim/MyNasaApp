package mx.com.agutierrezm.mynasaapp.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.com.agutierrezm.mynasaapp.R;
import mx.com.agutierrezm.mynasaapp.data.ApodService;
import mx.com.agutierrezm.mynasaapp.data.Data;
import mx.com.agutierrezm.mynasaapp.model.Apod;
import mx.com.agutierrezm.mynasaapp.sql.AppDataSource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Alumno on 12/08/2016.
 */
public class fragment_today_apod extends Fragment {

    AppDataSource appDataSource;
    String url;

    @BindView(R.id.main_image)
    ImageView main_image;
    @BindView(R.id.main_date)
    TextView main_date;
    @BindView(R.id.main_copyright)
    TextView main_copyright;
    @BindView(R.id.main_title)
    TextView main_title;
    @BindView(R.id.main_explanation)
    TextView main_explanation;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        appDataSource = new AppDataSource(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_main, container, false);
        ButterKnife.bind(this, view);
        ApodService apodService = Data.getRetrofitInstance().create(ApodService.class);
        Call<Apod> callApodService = apodService.getTodayApodWithQuery("8eWI3YOpg95epi4yLcAqR5ZrJPqcWjvQqgJgSAoB");

        callApodService.enqueue(new Callback<Apod>() {
            @Override
            public void onResponse(Call<Apod> call, Response<Apod> response) {
                //Log.d("APOD",response.body().getTitle());
                //Log.d("MARS","hola");
                Apod apod = response.body();
                main_date.setText(apod.getDate());
                url = apod.getHdurl();
                Picasso.with(getActivity()).load(url).into(main_image);
                main_copyright.setText(apod.getCopyright());
                main_title.setText(apod.getTitle());
                main_explanation.setText(apod.getExplanation());
            }

            @Override
            public void onFailure(Call<Apod> call, Throwable t) {
                Log.d("MARS","Fallo");
            }
        });

        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.apod_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Log.d("Snacl","todo ok si entro 1 ");
        switch (item.getItemId()){
            case R.id.share_today_apod:
                //Log.d("Snacl","todo ok si entro 2 ");
                //Snackbar.make(getView(),"Shared", Snackbar.LENGTH_SHORT).show();
                shareText("Compartiendo");
                return true;
            case R.id.favorite_today_apod:
                appDataSource.saveFavorite(url);
                Snackbar.make(getView(),"Added", Snackbar.LENGTH_SHORT).show();
                return true;
            default:
          //      Log.d("Snacl","todo ok si entro 3 ");
                return super.onOptionsItemSelected(item);

        }
    }



    private void shareText(String text){
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT,text);
        startActivity(Intent.createChooser(shareIntent,"Shared"));
    }
}
