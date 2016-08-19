package mx.com.agutierrezm.mynasaapp.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.com.agutierrezm.mynasaapp.DetailActivity;
import mx.com.agutierrezm.mynasaapp.R;
import mx.com.agutierrezm.mynasaapp.data.ApodService;
import mx.com.agutierrezm.mynasaapp.data.Data;
import mx.com.agutierrezm.mynasaapp.iu.view.apod.list.adapter.NassaApodAdapter;
import mx.com.agutierrezm.mynasaapp.model.Mars;
import mx.com.agutierrezm.mynasaapp.model.Photo;
import mx.com.agutierrezm.mynasaapp.util.Comunicador;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Alumno on 12/08/2016.
 */
public class fragment_list extends Fragment {

    View view;
    @BindView(R.id.mars_rover_listing)
    RecyclerView marsRoverListingRecycler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.activity_listing, container, false);
        ButterKnife.bind(this,view);
        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        //StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(10,StaggeredGridLayoutManager.VERTICAL);

        marsRoverListingRecycler.setLayoutManager(gridLayoutManager);

        final NassaApodAdapter nassaApodAdapter = new NassaApodAdapter();

        nassaApodAdapter.setOnItemClickListener(new NassaApodAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Photo photo) {
                Comunicador.setObjeto(photo);
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                startActivity(intent);
            }
        });


        ApodService apodService = Data.getRetrofitInstance().create(ApodService.class);

        Call<Mars> callApodService = apodService.getPhotosMars();
        callApodService.enqueue(new Callback<Mars>() {
            @Override
            public void onResponse(Call<Mars> call, Response<Mars> response) {
                Log.d("MARS", "No");
                nassaApodAdapter.setMarsPhotos(response.body().getPhotos());
                marsRoverListingRecycler.setAdapter(nassaApodAdapter);

            }

            @Override
            public void onFailure(Call<Mars> call, Throwable t) {
                Log.d("MARS", "Fallo");
            }
        });


        return view;
    }





}
