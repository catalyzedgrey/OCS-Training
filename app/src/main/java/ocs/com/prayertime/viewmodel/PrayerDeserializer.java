package ocs.com.prayertime.viewmodel;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import ocs.com.prayertime.model.Timings;

public class PrayerDeserializer implements JsonDeserializer<List<Timings>> {
    ArrayList prayerList;

    @Override
    public List<Timings> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        Log.v("serializer", "prayer serializer called");
        prayerList = new ArrayList<Timings>();
        JsonObject timingsJsonObject = json.getAsJsonObject();

        JsonArray timingsJsonElement = timingsJsonObject.getAsJsonArray("data");

        for (JsonElement element : timingsJsonElement) {
            JsonElement e = element.getAsJsonObject().get("timings");

            prayerList.add(new Timings(e.getAsJsonObject().get("Fajr").toString(), e.getAsJsonObject().get("Dhuhr").toString(), e.getAsJsonObject().get("Asr").toString(),
                    e.getAsJsonObject().get("Maghrib").toString(), e.getAsJsonObject().get("Isha").toString()));
        }
        return prayerList;
    }
}