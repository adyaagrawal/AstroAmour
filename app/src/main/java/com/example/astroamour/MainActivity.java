package com.example.astroamour;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    ImageView img;
    TextView id,title,des,copyright;
    private ProgressBar spinner;

    String api_key="ENmH7H2zRIqY9ecL0KabFcBiC06pq3tEeheOhhr0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img=findViewById(R.id.imageView3);
        id=findViewById(R.id.text);
        title=findViewById(R.id.title);
        des=findViewById(R.id.explanation);
        copyright=findViewById(R.id.copyright);
        spinner = (ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.VISIBLE);

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://api.nasa.gov/planetary/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NASAAPI nasaapi=retrofit.create(NASAAPI.class);
        Call<Model> call=nasaapi.getDayPic(api_key);
        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                if(!response.isSuccessful()){
                    id.setText("Code: "+response.code());
                    return;
                }
                Model t=response.body();
                Picasso.with(getBaseContext()).load(t.getUrl()).into(img);
                id.setText(t.getDate());
                title.setText(t.getTitle());
                copyright.setText("Copyright: "+t.getCopyright());
                des.setText(t.getExplanation());
                spinner.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                id.setText(t.getMessage());
            }
        });

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setSelectedItemId(R.id.nav_home);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        return true;
                    case R.id.nav_explore:
                        startActivity(new Intent(getApplicationContext(), Explore.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.nav_date:
                        startActivity(new Intent(getApplicationContext(), Date.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }
}