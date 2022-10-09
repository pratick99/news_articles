package com.pratik.news.articlelist.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import com.pratik.news.articlelist.repository.INewsRepository
import com.pratik.news.articlelist.ui.util.ArticleListFragmentQualifier
import com.pratik.news.articlelist.ui.util.ArticleListViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
class ArticlesModule {

    @ArticleListFragmentQualifier
    @Provides
    fun provideFactory(fragment: Fragment, repository: INewsRepository): AbstractSavedStateViewModelFactory {
        return ArticleListViewModelFactory(fragment, fragment.arguments, repository)
    }
}
