package com.pratik.news.articlelist.ui.util

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.pratik.news.articlelist.repository.INewsRepository
import com.pratik.news.articlelist.ui.ArticlesViewModel

class ArticleListViewModelFactory(
    owner: SavedStateRegistryOwner,
    args: Bundle? = null,
    val repository: INewsRepository) :
    AbstractSavedStateViewModelFactory(owner, args) {
    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return ArticlesViewModel(repository) as T
    }
}
