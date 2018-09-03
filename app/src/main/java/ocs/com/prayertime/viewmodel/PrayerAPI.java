package ocs.com.prayertime.viewmodel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ocs.com.prayertime.model.Timings;


//public interface PrayerAPI {
//    String ENDPOINT = "http://api.aladhan.com/";
//
//    @GET("v1/calendarByCity")
//    Call<Timings> getPrayerTime(@Query("city") String city, @Query("country") String country);
//
//    @GET("v1/calendarByCity")
//    Call<List<Timings>> getPrayerTimelist(@Query("city") String city, @Query("country") String country);
//}

public interface PrayerAPI {
    String ENDPOINT = "http://api.aladhan.com/";

    @GET("v1/calendarByCity")
    Single<Timings> getPrayerTime(@Query("city") String city, @Query("country") String country);

    @GET("v1/calendarByCity")
    Call<List<Timings>> getPrayerTimelist(@Query("city") String city, @Query("country") String country);
}
