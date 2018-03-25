package com.dev_juyoung.cro_mvp_sample.injection;

import com.dev_juyoung.cro_mvp_sample.MainActivityModule;
import com.dev_juyoung.cro_mvp_sample.MyApp;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;


@Component( modules = {
        AppModule.class,
        AndroidInjectionModule.class,
        ActivityModule.class
})
public interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance Builder application(MyApp myApp);
        AppComponent build();
    }

    void inject(MyApp myApp);
}
