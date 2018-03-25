package com.dev_juyoung.cro_mvp_sample;

import com.dev_juyoung.cro_mvp_sample.injection.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    @ActivityScope
    @Provides
    MainContract.View provideMainView(MainActivity mainActivity) {
        return mainActivity;
    }

    @ActivityScope
    @Provides
    MainContract.Presenter provideMainPresenter() {
        return new MainPresenter();
    }
}
