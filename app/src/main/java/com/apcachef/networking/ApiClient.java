package com.apcachef.networking;

import com.apcachef.constant.AppConstants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static Retrofit retrofit = null;

    public static Retrofit getClient() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(AppConstants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getLogBuilderWithoutToken().build())
                    .build();
        }
        return retrofit;
    }


    /*public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(AppConstants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getLogBuilderWithOutToken().build())
                    .build();
        }
        return retrofit;
    }*/


    /*public static Retrofit getClientFiaWithoutHeader() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(AppConstants.BASE_URL_VIDEO_KYC)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getLogBuilderWithoutToken().build())
                    .build();
        }
        return retrofit;
    }*/


    private static OkHttpClient.Builder getLogBuilderWithoutToken() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
       // logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS);

        httpClient.addInterceptor(logging);

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder()
                        .addHeader("Content-Type", "application/json");

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        return httpClient;
    }


    /*private static OkHttpClient.Builder getLogBuilderWithOutToken() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS);

        httpClient.addInterceptor(logging);

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder()
                        .addHeader("apitoken", AppConstants.apiToken)
                        .addHeader("Content-Type", "application/json");

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        return httpClient;
    }*/


    /*private static OkHttpClient.Builder getLogBuilder() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS);

        httpClient.addInterceptor(logging);

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder()
                        .addHeader("apitoken", AppConstants.apiToken)
                        .addHeader("usertoken", AppConstants.userToken)
                        .addHeader("Content-Type", "application/json");

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        return httpClient;
    }*/


}
