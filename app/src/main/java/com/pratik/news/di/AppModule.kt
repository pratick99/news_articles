package com.pratik.news.di

import android.content.Context
import com.pratik.news.NewsApp
import com.pratik.news.articlelist.data.source.INewsRemoteDataSource
import com.pratik.news.articlelist.data.source.NewsRemoteDataSource
import com.pratik.news.articlelist.repository.INewsRepository
import com.pratik.news.articlelist.repository.NewsRepository
import com.pratik.news.util.Logger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): NewsApp {
        return app as NewsApp
    }

    @Provides
    @Singleton
    fun provideContext(application: NewsApp): Context {
        return application.applicationContext
    }

    @Provides
    fun provideCoroutineIODispatcher() = Dispatchers.IO

    @Provides
    @Singleton
    fun provideLogger() = Logger()

    @Provides
    @Singleton
    fun getHost(): String {
        return "https://candidate-test-data-moengage.s3.amazonaws.com/Android/news-api-feed/staticResponse.json"
    }

    @Provides
    @Singleton
    fun provideNewsRemoteDataSource(host: String, dispatcher: CoroutineDispatcher, logger: Logger): INewsRemoteDataSource {
        return NewsRemoteDataSource(host, dispatcher, logger)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(iNewsRemoteDataSource: INewsRemoteDataSource): INewsRepository {
        return NewsRepository(iNewsRemoteDataSource)
    }
}
