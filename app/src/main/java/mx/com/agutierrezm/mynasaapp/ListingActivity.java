package mx.com.agutierrezm.mynasaapp;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.com.agutierrezm.mynasaapp.data.ApodService;
import mx.com.agutierrezm.mynasaapp.data.Data;
import mx.com.agutierrezm.mynasaapp.fragment.fragment_list;
import mx.com.agutierrezm.mynasaapp.fragment.fragment_today_apod;
import mx.com.agutierrezm.mynasaapp.iu.view.apod.list.adapter.NassaApodAdapter;
import mx.com.agutierrezm.mynasaapp.model.Mars;
import mx.com.agutierrezm.mynasaapp.model.Photo;
import mx.com.agutierrezm.mynasaapp.util.Comunicador;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListingActivity extends AppCompatActivity {



    @BindView(R.id.listing_toolbar)
    Toolbar toolbar;
    @BindView(R.id.listing_navigation_view)
    NavigationView navigationView;
    @BindView(R.id.listing_navigation_drawer)
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listing_navigation_activity);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        //navigationView.setCheckedItem(0);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                drawerLayout.closeDrawers();

                switch (item.getItemId()) {
                    case R.id.today_apod_item:
                        getFragmentManager().beginTransaction().replace(R.id.fragmentHolder,new fragment_today_apod()).commit();
                        //Snackbar.make(findViewById(android.R.id.content),"Today Apod Item", Snackbar.LENGTH_SHORT).show();
                        return true;
                    case R.id.mars_rover_item:
                        getFragmentManager().beginTransaction().replace(R.id.fragmentHolder,new fragment_list()).commit();
                        //Snackbar.make(findViewById(android.R.id.content),"Mars Rover", Snackbar.LENGTH_SHORT).show();
                        return true;
                    case R.id.favorite_item:
                        Snackbar.make(findViewById(android.R.id.content),"Favorite", Snackbar.LENGTH_SHORT).show();
                        return true;
                    default:
                        return false;
                }

            }
        });

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name,R.string.app_name){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };


        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

    }
}

