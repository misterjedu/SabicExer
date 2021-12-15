package com.jedun.sabipay.common.di

import com.jedun.sabipay.BuildConfig
import com.jedun.sabipay.articles.presentation.articles.ArticleSource
import com.jedun.sabipay.common.data.network.NetworkConstants
import com.jedun.sabipay.common.data.network.NewsApi
import com.jedun.sabipay.common.data.repository.NewsApiRepository
import com.jedun.sabipay.common.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    companion object {

        @Provides
        @Singleton
        fun provideLogger(): HttpLoggingInterceptor {
            return if (BuildConfig.DEBUG) HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY) else HttpLoggingInterceptor().setLevel(
                HttpLoggingInterceptor.Level.NONE
            )
        }

        @Provides
        @Singleton
        fun provideClient(
            logger: HttpLoggingInterceptor
        ): OkHttpClient {
            return OkHttpClient.Builder()
                .connectTimeout(30L, TimeUnit.SECONDS)
                .readTimeout(30L, TimeUnit.SECONDS)
                .writeTimeout(30L, TimeUnit.SECONDS)
                .addInterceptor(logger)
                .build()
        }

        @Provides
        @Singleton
        fun provideConverterFactory(): Converter.Factory {
            return GsonConverterFactory.create()
        }

        @Provides
        @Singleton
        fun provideRetrofitClient(
            client: OkHttpClient,
            converterFactory: Converter.Factory
        ): Retrofit {
            return Retrofit.Builder()
                .baseUrl(NetworkConstants.BASE_URL)
                .addConverterFactory(converterFactory)
                .client(client)
                .build()
        }

        @Provides
        @Singleton
        fun provideNewsService(retrofit: Retrofit): NewsApi {
            return retrofit.create(NewsApi::class.java)
        }

        @Provides
        @Singleton
        fun provideRepository(articleSource: ArticleSource): NewsRepository {
            return NewsApiRepository(articleSource)
        }
    }

}