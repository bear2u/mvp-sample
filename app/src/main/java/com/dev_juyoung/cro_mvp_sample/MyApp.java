package com.dev_juyoung.cro_mvp_sample;

import android.app.Activity;
import android.app.Application;

import com.dev_juyoung.cro_mvp_sample.injection.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by tommy on 2018-03-25.
 */

public class MyApp extends Application implements HasActivityInjector{
    @Inject
    DispatchingAndroidInjector<Activity> _androidInjector;
    @Override
    public void onCreate() {
        super.onCreate();
        initDagger();
    }

    private void initDagger(){
        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return _androidInjector;
    }
}
