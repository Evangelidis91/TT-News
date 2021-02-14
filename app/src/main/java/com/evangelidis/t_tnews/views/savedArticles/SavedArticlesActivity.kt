package com.evangelidis.t_tnews.views.savedArticles

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.evangelidis.t_tnews.R
import com.evangelidis.t_tnews.databases.ArticlePresenter
import com.evangelidis.t_tnews.databases.SavedArticle
import com.evangelidis.t_tnews.databinding.ActivitySavedArticlesBinding
import com.evangelidis.t_tnews.interfaces.BaseContract
import com.evangelidis.t_tnews.utils.InternetStatus
import com.evangelidis.t_tnews.utils.ItemsManager.warningToast
import com.evangelidis.t_tnews.views.base.BaseActivity
import com.evangelidis.t_tnews.views.base.BasePresenter
import com.evangelidis.t_tnews.views.main.ArticleItemCallback
import com.evangelidis.t_tnews.views.webview.BaseWebViewActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SavedArticlesActivity : BaseActivity<BaseContract.View, BasePresenter<BaseContract.View>>(), ArticleItemCallback {

    companion object {
        fun createIntent(context: Context): Intent =
            Intent(context, SavedArticlesActivity::class.java)
    }

    private val binding: ActivitySavedArticlesBinding by lazy { ActivitySavedArticlesBinding.inflate(layoutInflater) }
    private val newsAdapter: SavedNewsAdapter by lazy { SavedNewsAdapter(this) }
    private val articlePresenter: ArticlePresenter by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        presenter.view?.hideSavedArticlesImage()
        presenter.view?.setToolbarTitle(getString(R.string.saved_articles_page_title))
        setRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        articlePresenter.getAll().observe(this, Observer<MutableList<SavedArticle>> {
            newsAdapter.articles = it
        })
    }

    private fun setRecyclerView() {
        binding.newsList.apply {
            layoutManager = LinearLayoutManager(this@SavedArticlesActivity)
            adapter = newsAdapter
        }
    }

    override fun navigateToUrl(url: String?) {
        if (url.isNullOrEmpty()) {
            warningToast(this, getString(R.string.no_url))
        } else if (!InternetStatus.isConnected(this)) {
            warningToast(this, getString(R.string.no_internet))
        } else {
            startActivity(BaseWebViewActivity.createIntent(this, url))
        }
    }

    override fun saveArticle(article: SavedArticle) {
        articlePresenter.insert(article)
    }

    override fun removeSavedArticle(url: String) {
        articlePresenter.delete(url)
    }

    override fun createPresenter(): BasePresenter<BaseContract.View> = BasePresenter()
}
