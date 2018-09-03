package ocs.com.prayertime.view;

import android.app.Application;

import ocs.com.prayertime.viewmodel.ViewModelModule;


public class MyApplication  extends Application {
    private MainComponent mainComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mainComponent = createMainComponent();
    }

    MainComponent getMainComponent() {
        return mainComponent;
    }

    private MainComponent createMainComponent() {
        return DaggerMainComponent
                .builder().viewModelModule(new ViewModelModule())
                .build();
    }
}
