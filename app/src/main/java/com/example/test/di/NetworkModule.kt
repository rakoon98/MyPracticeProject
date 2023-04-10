package com.example.test.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
//    fun provideUrl() = "https://carecrew-api.huray.net/v1"
    fun provideUrl() = "https://api.sampleapis.com/"

    @Provides
    @Singleton
    fun provideOkHttpClient() = run {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
//            .addInterceptor {
//                val original: Request = it.request()
//                val request: Request = original.newBuilder()
////                    .header(SampleHeaders.APP_VERSION, Build.VERSION.RELEASE)
////                    .header(SampleHeaders.DEVICE_ID, Build.MODEL)
////                    .header(SampleHeaders.OS_TYPE, "")
////                    .header(SampleHeaders.OS_VERSION, "")
////                    .header(SampleHeaders.APP_VERSION, "")
////                    .header(SampleHeaders.AUTHORIZATION, "1")
//                    .build()
//                it.proceed(request)
//            }
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    fun provideStockRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit =
        Retrofit.Builder()
//            .addConverterFactory(Json.asConverterFactory("application/json".toMediaTypeOrNull()!!))
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()


}