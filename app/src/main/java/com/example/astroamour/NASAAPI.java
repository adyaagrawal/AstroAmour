package com.example.astroamour;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface NASAAPI {

    @GET("apod")
    Call<Model> getDayPic(@Query("api_key") String api_key);

    @GET("apod")
    Call<List<Model>> getPic(@Query("api_key") String api_key,
                        @Query("count")int count);

    @GET("apod")
    Call<List<Model>> getDatePic(@Query("api_key") String api_key,
                             @Query("start_date") String date1,
                                 @Query("end_date") String date2);
}
