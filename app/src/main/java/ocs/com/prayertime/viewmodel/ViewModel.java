package ocs.com.prayertime.viewmodel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ocs.com.prayertime.model.Timings;

public class ViewModel {
    PrayerAPI prayerAPI;
    Locale[] locales;
    ArrayList<String> countries;

    public ViewModel(PrayerAPI api) {
        InitComponents();
        prayerAPI =api;
//        CreatePrayerAPI();
    }
    public void InitComponents() {
        locales = Locale.getAvailableLocales();
        countries = new ArrayList<>();
    }

//    public Call<Timings> getTimings(String city, String country) {
//        return prayerAPI.getPrayerTime(city, country);
//    }

    public Single<Timings> getTimings(String city, String country) {
        return prayerAPI.getPrayerTime(city, country)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Call<List<Timings>> getTimingsList(String city, String country) {
        return prayerAPI.getPrayerTimelist(city, country);
    }

    public PrayerAPI getPrayerAPI() {
        return prayerAPI;
    }

    //    public void CreatePrayerAPI() {
//        Gson gson = new GsonBuilder()
//                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
//                .registerTypeAdapter(Timings.class, new PrayerDeserializer())
//                .create();
//
//        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(PrayerAPI.ENDPOINT)
//                .client(okHttpClient)
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .build();
//
//        prayerAPI = retrofit.create(PrayerAPI.class);
//    }

    public boolean IsCountryAvailable(String givenCountry) {

        for (Locale locale : locales) {
            String country = locale.getDisplayCountry();
            if (country.trim().length() > 0 && !countries.contains(country)) {
                countries.add(country);
            }
        }
        return countries.contains(givenCountry);
    }
}

