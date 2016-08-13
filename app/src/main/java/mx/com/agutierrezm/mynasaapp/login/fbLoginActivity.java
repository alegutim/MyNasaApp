package mx.com.agutierrezm.mynasaapp.login;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.com.agutierrezm.mynasaapp.ListingActivity;
import mx.com.agutierrezm.mynasaapp.R;

public class fbLoginActivity extends AppCompatActivity implements FacebookCallback<LoginResult> {

    @BindView(R.id.fb_login_button)
    LoginButton fb_login_button;

    private CallbackManager callbackManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fb_login);

        ButterKnife.bind(this);

        callbackManager = CallbackManager.Factory.create();
        fb_login_button.registerCallback(callbackManager,this);

        if (AccessToken.getCurrentAccessToken()!=null) {
            //Snackbar.make(findViewById(android.R.id.content),"PRE-Login",Snackbar.LENGTH_SHORT);
            Intent intent = new Intent(this, ListingActivity.class);
            startActivity(intent);
        }

    }

    @Override
    public void onSuccess(LoginResult loginResult) {
        Intent intent = new Intent(this, ListingActivity.class);
        startActivity(intent);

    }

    @Override
    public void onCancel() {
        Snackbar.make(findViewById(android.R.id.content),"Cancel login",Snackbar.LENGTH_SHORT);
    }

    @Override
    public void onError(FacebookException error) {
        Snackbar.make(findViewById(android.R.id.content),error.getMessage(),Snackbar.LENGTH_SHORT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
