package com.example.astroamour;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Date extends AppCompatActivity {
    Button start,end,search;
    EditText startdate,enddate;
    private int sYear, sMonth, sDay, eYear,eMonth,eDay;
    String sdate,edate;
    String api_key="ENmH7H2zRIqY9ecL0KabFcBiC06pq3tEeheOhhr0";
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setSelectedItemId(R.id.nav_date);
        start=findViewById(R.id.start);
        end=findViewById(R.id.end);
        startdate=findViewById(R.id.starttext);
        enddate=findViewById(R.id.endtext);
        search=findViewById(R.id.search);
        recyclerView=findViewById(R.id.rv2);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));


        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.nav_explore:
                        startActivity(new Intent(getApplicationContext(), Explore.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.nav_date:
                        return true;
                }
                return false;
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                sYear = c.get(Calendar.YEAR);
                sMonth = c.get(Calendar.MONTH);
                sDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(Date.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                startdate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                sdate=year+"-"+(monthOfYear + 1)+"-"+dayOfMonth;

                            }
                        }, sYear, sMonth, sDay);
                datePickerDialog.show();

            }
        });

        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                eYear = c.get(Calendar.YEAR);
                eMonth = c.get(Calendar.MONTH);
                eDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(Date.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                enddate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                edate=year+"-"+(monthOfYear + 1)+"-"+dayOfMonth;

                            }
                        }, eYear, eMonth, eDay);
                datePickerDialog.show();

            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit=new Retrofit.Builder()
                        .baseUrl("https://api.nasa.gov/planetary/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                NASAAPI nasaapi=retrofit.create(NASAAPI.class);
                Call<List<Model>> call=nasaapi.getDatePic(api_key,sdate,edate);
                call.enqueue(new Callback<List<Model>>() {
                    @Override
                    public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
                        if(!response.isSuccessful()){
                            return;
                        }
                        List<Model> t=response.body();
                        recyclerView.setAdapter(new ItemAdapter(Date.this,t));
                    }

                    @Override
                    public void onFailure(Call<List<Model>> call, Throwable t) {

                    }
                });

            }
        });

    }
}
