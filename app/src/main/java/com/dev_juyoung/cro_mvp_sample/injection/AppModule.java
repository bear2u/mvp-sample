package com.dev_juyoung.cro_mvp_sample.injection;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    @Singleton
    @Provides
    Context provideContext(Application application) {
        return application;
    }
}
