package ocs.com.prayertime.viewmodel;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import ocs.com.prayertime.util.RetrofitFactory;

@Module
public class ViewModelModule {
    @Provides
    @Singleton
    public ViewModel providesViewModel(PrayerAPI api) {
        return new ViewModel(api);
    }

    @Provides
    @Singleton
    public PrayerAPI providesPrayerAPI(){
        return RetrofitFactory.createAPI(PrayerAPI.class);
    }
}
