package com.mazouri.data;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.Environment;

import com.f2prateek.rx.preferences.RxSharedPreferences;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mazouri.base.injector.scope.PerApplication;
import com.mazouri.data.api.ApiModule;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;

import static android.content.Context.MODE_PRIVATE;

@Module(includes = ApiModule.class)
public final class DataModule {
    static final int DISK_CACHE_SIZE = 50 * 1024 * 1024; // 50MB
    public static final String KEY_URL = "KEY_URL";
    public static final boolean ENCRYPTED = false;

    @Provides
    @PerApplication
    SharedPreferences provideSharedPreferences(Application app) {
        return app.getSharedPreferences("mazouri", MODE_PRIVATE);
    }

    @Provides
    @PerApplication
    @Named("Cache")
    File provideCacheDir(Application application){
        File cacheDir;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cacheDir = application.getExternalCacheDir();
        } else {
            cacheDir = application.getCacheDir();
        }
        return cacheDir;
    }

    @Provides
    @PerApplication
    @Named("Root")
    File provideRootDir(Application application){
        File cacheDir;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                                    || !Environment.isExternalStorageRemovable()) {
            cacheDir = application.getExternalFilesDir(null);
        } else {
            cacheDir = application.getFilesDir();
        }
        return cacheDir;
    }

    @Provides
    @PerApplication
    @Named("Data")
    File provideDataCacheDir(@Named("Root") File dir){
        return new File(dir, "data");
    }


    @Provides
    @PerApplication
    RxSharedPreferences provideRxSharedPreferences(SharedPreferences preferences) {
        return RxSharedPreferences.create(preferences);
    }

    static OkHttpClient.Builder createOkHttpClient(Application app) {
        // Install an HTTP cache in the application cache directory.
        File cacheDir = new File(app.getCacheDir(), "http");
        Cache cache = new Cache(cacheDir, DISK_CACHE_SIZE);
        return new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .cache(cache);

    }
}
