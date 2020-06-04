package com.pedrorocha.covid19info.di;

import android.app.Application;

import androidx.work.WorkManager;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crashlytics.FirebaseCrashlytics;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return mApplication;
    }

    @Provides
    Executor providesExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    @Provides
    @Singleton
    WorkManager providesWorkManager(Application application) {
        return WorkManager.getInstance(application);
    }

    @Provides
    FirebaseAnalytics providesFirebaseAnalytics(Application application) {
        return FirebaseAnalytics.getInstance(application);
    }

    @Provides
    FirebaseCrashlytics providesFirebaseCrashlytics() {
        return FirebaseCrashlytics.getInstance();
    }
}
