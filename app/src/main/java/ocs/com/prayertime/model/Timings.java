package ocs.com.prayertime.model;
public class Timings {

    String Fajr, Dhuhr, Asr, Maghrib, Isha;

    public Timings(String Fajr, String Dhuhr, String Asr, String Maghrib, String Isha){
        this.Fajr = Fajr;
        this.Dhuhr = Dhuhr;
        this.Asr = Asr;
        this.Maghrib = Maghrib;
        this.Isha = Isha;
    }

    public String getFajr() {
        return Fajr;
    }

    public String getDhuhr() {
        return Dhuhr;
    }

    public String getAsr() {
        return Asr;
    }

    public String getMaghrib() {
        return Maghrib;
    }

    public String getIsha() {
        return Isha;
    }
}
