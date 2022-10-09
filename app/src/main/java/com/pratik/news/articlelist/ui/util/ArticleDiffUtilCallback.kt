package com.pratik.news.articlelist.ui.util

import androidx.recyclerview.widget.DiffUtil
import com.pratik.news.articlelist.data.models.Article

class ArticleDiffUtilCallback(
    private val oldArticlesList: List<Article>,
    private val newArticlesList: List<Article>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldArticlesList.size
    }

    override fun getNewListSize(): Int {
        return newArticlesList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val (_, _, _, _, oldArticleUrl) = oldArticlesList[oldItemPosition]
        val (_, _, _, _, newArticleUrl) = newArticlesList[newItemPosition]
        return oldArticleUrl == newArticleUrl
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val (_, _, _, oldArticleDescription) = oldArticlesList[oldItemPosition]
        val (_, _, _, newArticleDescription) = newArticlesList[newItemPosition]
        return oldArticleDescription == newArticleDescription
    }
}
