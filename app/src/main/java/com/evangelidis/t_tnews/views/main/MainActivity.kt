package com.evangelidis.t_tnews.views.main

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.evangelidis.t_tnews.R
import com.evangelidis.t_tnews.databases.ArticlePresenter
import com.evangelidis.t_tnews.databases.SavedArticle
import com.evangelidis.t_tnews.databinding.ActivityMainBinding
import com.evangelidis.t_tnews.extensions.gone
import com.evangelidis.t_tnews.extensions.show
import com.evangelidis.t_tnews.extensions.showMainDialog
import com.evangelidis.t_tnews.interfaces.NewsContract
import com.evangelidis.t_tnews.models.modelsClasses.Article
import com.evangelidis.t_tnews.utils.InternetStatus
import com.evangelidis.t_tnews.utils.ItemsManager.warningToast
import com.evangelidis.t_tnews.views.base.BaseActivity
import com.evangelidis.t_tnews.views.base.NewsPresenter
import com.evangelidis.t_tnews.views.webview.BaseWebViewActivity
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<NewsContract.View, NewsPresenter>(), NewsContract.View, ArticleItemCallback {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val newsAdapter: NewsAdapter by lazy { NewsAdapter(this) }
    private val articlePresenter: ArticlePresenter by viewModel()

    companion object {
        fun createIntent(context: Context): Intent =
            Intent(context, MainActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setConnectivityListener()
    }

    @SuppressLint("CheckResult")
    private fun setConnectivityListener() {
        ReactiveNetwork
            .observeInternetConnectivity()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { isConnectedToInternet ->
                run {
                    if (isConnectedToInternet && newsAdapter.itemCount == 0) {
                        presenter.loadNews()
                        binding.errorText.gone()
                    } else if (!isConnectedToInternet && newsAdapter.itemCount == 0) {
                        displayErrorMessage()
                        binding.errorText.show()
                    }
                }
            }
    }

    override fun onResume() {
        super.onResume()
        articlePresenter.getAll().observe(this, Observer<MutableList<SavedArticle>> {
            newsAdapter.savedArticles = it
        })

        if (newsAdapter.itemCount == 0) {
            if (InternetStatus.isConnected(this)) {
                presenter.loadNews()
            }
        }
    }

    override fun setRecyclerView(articles: MutableList<Article>) {
        newsAdapter.articles = articles
        binding.newsList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = newsAdapter
        }
    }

    override fun displayErrorMessage() {
        warningToast(this, getString(R.string.no_internet))
    }

    override fun navigateToUrl(url: String?) {
        if (url.isNullOrEmpty()) {
            warningToast(this, getString(R.string.no_url))
        } else if (!InternetStatus.isConnected(this)) {
            displayErrorMessage()
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

    override fun onBackPressed() {
        this.showMainDialog {
            title = "Close app"
            message = "Do you want to close the app"
            setPositiveButton("Yes") {
                finish()
            }
            setNegativeButton("Cancel") { dialog.dismiss() }
        }.show()
    }

    override fun createPresenter() = NewsPresenter()
}
