package com.jedun.sabipay.common.di

import com.jedun.sabipay.common.data.network.NetworkConstants
import com.jedun.sabipay.common.data.network.NewsApi
import com.jedun.sabipay.common.data.repository.NewsApiRepository
import com.jedun.sabipay.common.domain.mappers.DomainArticleMapper
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
            return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
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
        fun providePixaBayService(retrofit: Retrofit): NewsApi {
            return retrofit.create(NewsApi::class.java)
        }

        @Provides
        @Singleton
        fun provideRepository(newsApi: NewsApi, mapper: DomainArticleMapper): NewsRepository {
            return NewsApiRepository(newsApi, mapper)
        }
    }

}