package com.dev_juyoung.cro_mvp_sample.injection;

import com.dev_juyoung.cro_mvp_sample.MainActivity;
import com.dev_juyoung.cro_mvp_sample.MainActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by tommy on 2018-03-25.
 */

@Module
public abstract class ActivityModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = MainActivityModule.class)
    public abstract MainActivity bindMainActivityInjector();
}
