package ocs.com.prayertime.view;

import javax.inject.Singleton;

import dagger.Component;
import ocs.com.prayertime.viewmodel.ViewModelModule;

@Singleton
@Component(modules = ViewModelModule.class)
interface MainComponent {
    void inject(MainActivity mainActivity);
}
