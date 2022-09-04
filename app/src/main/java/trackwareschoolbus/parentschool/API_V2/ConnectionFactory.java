package trackwareschoolbus.parentschool.API_V2;


import android.util.Log;

import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import io.reactivex.schedulers.Schedulers;
import me.linshen.retrofit2.adapter.LiveDataCallAdapterFactory;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import trackwareschoolbus.parentschool.API_new.APIs.Constants;
import trackwareschoolbus.parentschool.BuildConfig;
import trackwareschoolbus.parentschool.app.Application;
import trackwareschoolbus.parentschool.toolsV2.ConstantsV2;

public class ConnectionFactory implements ConstantsV2 {

    private static WebServicesV2 service = null;

//    public static WebServices getInstance() {
//        return getInstance(null);
//    }

    public static WebServicesV2 getInstance() {
        if (service == null) {
            service = buildRetrofit(buildOkHttpClient(), URLS.BASE_URL).create(WebServicesV2.class);
        }
        return service;
    }


    public static class NullOnEmptyConverterFactory extends Converter.Factory {
        @Override
        public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
            final Converter<ResponseBody, ?> delegate = retrofit.nextResponseBodyConverter(this, type, annotations);
            return new Converter<ResponseBody, Object>() {
                @Override
                public Object convert(ResponseBody body) throws IOException {
                    if (body.contentLength() == 0) {
                        return null;
                    } else {
                        return delegate.convert(body);
                    }
                }
            };
        }
    }

    private static Retrofit buildRetrofit(OkHttpClient client, String base_url) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(base_url);
        builder.client(client);
        builder.addCallAdapterFactory(new LiveDataCallAdapterFactory());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()));
        builder.addConverterFactory(new NullOnEmptyConverterFactory());
        builder.addConverterFactory(GsonConverterFactory.create(new GsonBuilder().serializeNulls().create()));


        return builder.build();
    }

    private static OkHttpClient buildOkHttpClient() {


        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .addInterceptor(buildInterceptor());

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            Log.v("AUTHORIZATION", Application.getPreferencesHelper().getAuthorization());
            Log.v("LOCALE", Application.getPreferencesHelper().getCurrentAppLanguage());


//            builder.networkInterceptors().add(new StethoInterceptor());
            builder.networkInterceptors().add(interceptor);
        }

        return builder.build();
    }

    private static Interceptor buildInterceptor() {
        return chain -> {
            Request.Builder request = chain.request().newBuilder();
            if (!chain.request().url().toString().equals(Constants.SCHOOLS_LIST)){
            request.addHeader(HEADERS_KEYS.AUTHORIZATION, Application.getPreferencesHelper().getAuthorization()).build();
            request.addHeader(HEADERS_KEYS.LOCALE, Application.getPreferencesHelper().getCurrentAppLanguage()).build();
            }

            return chain.proceed(request.build());
        };
    }


}
