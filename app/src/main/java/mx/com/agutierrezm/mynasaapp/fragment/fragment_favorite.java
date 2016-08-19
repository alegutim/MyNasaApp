package mx.com.agutierrezm.mynasaapp.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.com.agutierrezm.mynasaapp.R;
import mx.com.agutierrezm.mynasaapp.iu.view.apod.list.adapter.FavoriteAdapter;
import mx.com.agutierrezm.mynasaapp.iu.view.apod.list.adapter.NassaApodAdapter;
import mx.com.agutierrezm.mynasaapp.sql.AppDataSource;

/**
 * Created by Administrator on 19/08/2016.
 */
public class fragment_favorite extends Fragment {

    AppDataSource appDataSource;
    View view;
    @BindView(R.id.mars_rover_listing)
    RecyclerView marsRoverListingRecycler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_listing, container, false);
        ButterKnife.bind(this,view);
        appDataSource = new AppDataSource(getActivity());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        marsRoverListingRecycler.setLayoutManager(gridLayoutManager);
        final FavoriteAdapter favoriteAdapter = new FavoriteAdapter();
        favoriteAdapter.setFavoritePhotos(appDataSource.getAllItems());
        marsRoverListingRecycler.setAdapter(favoriteAdapter);
        return view;
    }
}
