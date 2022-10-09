package com.pratik.news.articlelist.ui

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pratik.news.articlelist.data.models.Article
import com.pratik.news.articlelist.ui.ArticleListAdapter.ArticleViewHolder
import com.pratik.news.articlelist.ui.util.ArticleDiffUtilCallback
import com.pratik.news.databinding.ArticleListItemBinding

class ArticleListAdapter : RecyclerView.Adapter<ArticleViewHolder>() {
    private val articles: MutableList<Article> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            ArticleListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]
        holder.bindView(article)
    }

    fun setArticles(articles: List<Article>) {
        val diffUtilCallback = ArticleDiffUtilCallback(this.articles, articles)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
        this.articles.clear()
        this.articles.addAll(articles)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    class ArticleViewHolder(private val articleListItemBinding: ArticleListItemBinding) :
        RecyclerView.ViewHolder(articleListItemBinding.root) {

        fun bindView(article: Article?) {
            article?.let {
                Glide.with(articleListItemBinding.root.context)
                    .load(article.urlToImage)
                    .into(articleListItemBinding.articleImage)
                articleListItemBinding.articleDescription.text = article.description
                articleListItemBinding.articleAuthor.text = article.author
                articleListItemBinding.root.setOnClickListener {
                    val webpage = Uri.parse(article.url)
                    val intent = Intent(Intent.ACTION_VIEW, webpage)
                    if (itemView.context == null) return@setOnClickListener
                    if (intent.resolveActivity(itemView.context.packageManager) != null) {
                        itemView.context.startActivity(intent)
                    }
                }
            }
        }
    }
}
