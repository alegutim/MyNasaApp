package mx.com.agutierrezm.mynasaapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import mx.com.agutierrezm.mynasaapp.data.ApodService;
import mx.com.agutierrezm.mynasaapp.data.Data;
import mx.com.agutierrezm.mynasaapp.model.Apod;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private ImageView main_image;
    private TextView main_date;
    private TextView main_copyright;
    private TextView main_title;
    private TextView main_explanation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main_date = (TextView) findViewById(R.id.main_date);
        main_image = (ImageView) findViewById(R.id.main_image);
        main_copyright = (TextView) findViewById(R.id.main_copyright);
        main_title = (TextView) findViewById(R.id.main_title);
        main_explanation = (TextView) findViewById(R.id.main_explanation);


        ApodService apodService = Data.getRetrofitInstance().create(ApodService.class);

        //Call<Apod> callApodService = apodService.getTodayApod();

        Call<Apod> callApodService = apodService.getTodayApodWithQuery("8eWI3YOpg95epi4yLcAqR5ZrJPqcWjvQqgJgSAoB");

        callApodService.enqueue(new Callback<Apod>() {
            @Override
            public void onResponse(Call<Apod> call, Response<Apod> response) {
                //Log.d("APOD",response.body().getTitle());
                Apod apod = response.body();
                main_date.setText(apod.getDate());
                Picasso.with(getApplicationContext()).load(apod.getHdurl()).into(main_image);
                main_copyright.setText(apod.getCopyright());
                main_title.setText(apod.getTitle());
                main_explanation.setText(apod.getExplanation());


            }

            @Override
            public void onFailure(Call<Apod> call, Throwable t) {

            }
        });


    }
}

