package mx.com.agutierrezm.mynasaapp.app;

import android.app.Application;
import com.facebook.drawee.backends.pipeline.Fresco;


public class NasaApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
