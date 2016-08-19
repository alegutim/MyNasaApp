package mx.com.agutierrezm.mynasaapp;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.drawee.view.SimpleDraweeView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import butterknife.BindView;
import butterknife.ButterKnife;

import mx.com.agutierrezm.mynasaapp.fragment.fragment_favorite;
import mx.com.agutierrezm.mynasaapp.fragment.fragment_list;
import mx.com.agutierrezm.mynasaapp.fragment.fragment_today_apod;


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
                        getFragmentManager().beginTransaction().replace(R.id.fragmentHolder,new fragment_favorite()).commit();
                        //Snackbar.make(findViewById(android.R.id.content),"Favorite", Snackbar.LENGTH_SHORT).show();
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

        Log.d("KeyHash:", "hola1");

        // Add code to print out the key hash
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "mx.com.agutierrezm.mynasaapp",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }


        Log.d("KeyHash:", "hola2");


        getFBUserInfo( );
    }


    private  void getFBUserInfo( ){

        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    SimpleDraweeView userImagen = (SimpleDraweeView)findViewById(R.id.simple_image);
                    userImagen.setImageURI("http://graph.facebook.com/"+object.getString("id")+"/picture?type=large" );
                    TextView simple_text = (TextView) findViewById(R.id.simple_text);
                    simple_text.setText(object.getString("name"));
                    //Log.d("name",object.getString("name") );
                    Log.d("id","http://graph.facebook.com/"+object.getString("id")+"/picture?type=large");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }});
        request.executeAsync();

    }


}

