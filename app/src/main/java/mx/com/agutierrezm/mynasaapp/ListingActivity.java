package mx.com.agutierrezm.mynasaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.com.agutierrezm.mynasaapp.data.ApodService;
import mx.com.agutierrezm.mynasaapp.data.Data;
import mx.com.agutierrezm.mynasaapp.iu.view.apod.list.adapter.NassaApodAdapter;
import mx.com.agutierrezm.mynasaapp.model.Mars;
import mx.com.agutierrezm.mynasaapp.model.Photo;
import mx.com.agutierrezm.mynasaapp.util.Comunicador;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListingActivity extends AppCompatActivity {

    @BindView(R.id.mars_rover_listing)
    RecyclerView marsRoverListingRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);
        ButterKnife.bind(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(10,StaggeredGridLayoutManager.VERTICAL);

        marsRoverListingRecycler.setLayoutManager(gridLayoutManager);

        final NassaApodAdapter nassaApodAdapter = new NassaApodAdapter();

        nassaApodAdapter.setOnItemClickListener(new NassaApodAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Photo photo) {
                Comunicador.setObjeto(photo);
                Intent intent = new Intent(getApplicationContext(),DetailActivity.class);
                startActivity(intent);

                //Log.d("mars",photo.getImgSrc());
            }
        });



        ApodService apodService = Data.getRetrofitInstance().create(ApodService.class);

        Call<Mars> callApodService = apodService.getPhotosMars();
        callApodService.enqueue(new Callback<Mars>() {
            @Override
            public void onResponse(Call<Mars> call, Response<Mars> response) {
                Log.d("MARS","No");
                nassaApodAdapter.setMarsPhotos(response.body().getPhotos());
                marsRoverListingRecycler.setAdapter(nassaApodAdapter);

            }

            @Override
            public void onFailure(Call<Mars> call, Throwable t) {
                Log.d("MARS","Fallo");
            }
        });
    }
}
