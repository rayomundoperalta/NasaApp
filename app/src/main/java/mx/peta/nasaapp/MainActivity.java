package mx.peta.nasaapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import mx.peta.nasaapp.data.ApodService;
import mx.peta.nasaapp.data.Data;
import mx.peta.nasaapp.model.Apod;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ImageView imagen;
    TextView  titulo;
    TextView  fecha;
    TextView  explicacion;
    TextView  copyright;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imagen = (ImageView) findViewById(R.id.imagen);
        titulo = (TextView)  findViewById(R.id.titulo);
        fecha  = (TextView)  findViewById(R.id.fecha);
        explicacion = (TextView) findViewById(R.id.explicacion);
        copyright = (TextView) findViewById(R.id.copyright);



        Log.d("Build Config", BuildConfig.BUILD_TYPE + " " + BuildConfig.URL);
        Toast.makeText(getApplicationContext(),"arranque",Toast.LENGTH_SHORT).show();

        ApodService apodService = Data.getRetrofitInstance().create(ApodService.class);
        // Call<Apod> callApodService = apodService.getTodayApod();
        Call<Apod> callApodService = apodService.getTodayApodWithQuery("1b0qt1wfXNdK7Pbz1E7kEETE3wDjAv6I6MA9dxz9");

        callApodService.enqueue(new Callback<Apod>() {
            @Override
            public void onResponse(Call<Apod>  call, Response<Apod> response) {
                Log.d("APOD", response.body().getTitle());
                Picasso.with(getApplicationContext()).load(response.body().getUrl()).into(imagen);
                titulo.setText(response.body().getTitle());
                fecha.setText(response.body().getDate());
                explicacion.setText(response.body().getExplanation());
                copyright.setText("(C) " + response.body().getCopyright());
            }

            @Override
            public void onFailure(Call<Apod> call, Throwable t) {

            }
        });


    }
}
