package ocs.com.prayertime.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ocs.com.prayertime.model.Timings;
import ocs.com.prayertime.viewmodel.PrayerAPI;
import ocs.com.prayertime.viewmodel.PrayerDeserializer;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;



public class RetrofitFactory {
    public static <T> T createAPI(Class<T> clazz){
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .registerTypeAdapter(Timings.class, new PrayerDeserializer())
                .create();

        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PrayerAPI.ENDPOINT)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(clazz);
    }

}
