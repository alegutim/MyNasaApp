package mx.com.agutierrezm.mynasaapp.data;

import mx.com.agutierrezm.mynasaapp.model.Apod;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Alumno on 30/07/2016.
 */
public interface ApodService {
    @GET("planetary/apod?api_key=8eWI3YOpg95epi4yLcAqR5ZrJPqcWjvQqgJgSAoB")
    Call<Apod> getTodayApod();


    @GET("planetary/apod")
    Call<Apod> getTodayApodWithQuery(@Query("api_key") String apiKey);
}
