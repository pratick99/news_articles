package com.pratik.news.articlelist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pratik.news.articlelist.data.models.Article
import com.pratik.news.articlelist.ui.util.ArticleListFragmentQualifier
import com.pratik.news.databinding.FragmentArticlesBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ArticlesFragment : Fragment() {

    private var binding: FragmentArticlesBinding ? = null

    @ArticleListFragmentQualifier
    @Inject
    lateinit var factory: AbstractSavedStateViewModelFactory

    private val articlesViewModel: ArticlesViewModel by viewModels(
        factoryProducer = { factory }
    )
    private var articlesListRecyclerView: RecyclerView ? = null
    private var articleListAdapter: ArticleListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArticlesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        articlesListRecyclerView = binding?.articlesListView
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        articlesListRecyclerView?.layoutManager = layoutManager
        articleListAdapter = ArticleListAdapter()
        articlesListRecyclerView?.adapter = articleListAdapter
    }

    override fun onResume() {
        super.onResume()
        articlesViewModel.fetchArticles()
        articlesViewModel.listLiveData.observe(this.viewLifecycleOwner) { articles: List<Article>? ->
            articles?.let {
                articleListAdapter?.setArticles(articles)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
