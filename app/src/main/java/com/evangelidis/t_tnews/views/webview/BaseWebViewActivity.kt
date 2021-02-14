package com.evangelidis.t_tnews.views.webview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.webkit.WebViewClient
import com.evangelidis.t_tnews.databinding.ActivityBaseWebViewBinding
import com.evangelidis.t_tnews.interfaces.BaseContract
import com.evangelidis.t_tnews.views.base.BaseActivity
import com.evangelidis.t_tnews.views.base.BasePresenter

class BaseWebViewActivity : BaseActivity<BaseContract.View, BasePresenter<BaseContract.View>>() {

    companion object {
        const val URL = "URL"

        fun createIntent(context: Context, url: String): Intent =
            Intent(context, BaseWebViewActivity::class.java)
                .putExtra(URL, url)
    }

    private val binding: ActivityBaseWebViewBinding by lazy { ActivityBaseWebViewBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        hideToolbar()
        showLoader()
        val url = intent.getStringExtra(URL)
        binding.webView.webViewClient = WebViewClient()
        binding.webView.loadUrl(url.orEmpty())
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.settings.setSupportZoom(true)
        hideLoader()
    }

    override fun onBackPressed() {
        if (binding.webView.canGoBack())
            binding.webView.goBack()
        else
            super.onBackPressed()
    }

    override fun createPresenter(): BasePresenter<BaseContract.View> = BasePresenter()
}
