package com.example.astroamour;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Explore extends AppCompatActivity {
    String api_key="ENmH7H2zRIqY9ecL0KabFcBiC06pq3tEeheOhhr0";
    RecyclerView recyclerView;
    ProgressBar spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setSelectedItemId(R.id.nav_explore);
        recyclerView=findViewById(R.id.rv1);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        getdata();
        spinner = findViewById(R.id.progressBar3);
        spinner.setVisibility(View.VISIBLE);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.nav_explore:
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

    private void getdata() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://api.nasa.gov/planetary/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NASAAPI nasaapi=retrofit.create(NASAAPI.class);
        Call<List<Model>> call=nasaapi.getPic(api_key,25);
        call.enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(Explore.this,"Response unsuccessful",Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Model> t=response.body();
                recyclerView.setAdapter(new ItemAdapter(Explore.this,t));
                spinner.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {

            }
        });

    }
}