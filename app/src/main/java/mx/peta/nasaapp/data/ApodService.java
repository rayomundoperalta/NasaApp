package mx.peta.nasaapp.data;

import mx.peta.nasaapp.model.Apod;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by rayo on 7/30/16.
 */
public interface ApodService {
    @GET("planetary/apod?api_key=1b0qt1wfXNdK7Pbz1E7kEETE3wDjAv6I6MA9dxz9")
    Call<Apod> getTodayApod();

    @GET("planetary/apod")
    Call<Apod> getTodayApodWithQuery(@Query("api_key") String apiKey);
}
