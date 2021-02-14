package com.evangelidis.t_tnews.views.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.evangelidis.t_tnews.R
import com.evangelidis.t_tnews.databinding.ActivityBaseBinding
import com.evangelidis.t_tnews.extensions.gone
import com.evangelidis.t_tnews.extensions.hide
import com.evangelidis.t_tnews.extensions.show
import com.evangelidis.t_tnews.extensions.showIf
import com.evangelidis.t_tnews.interfaces.BaseContract
import com.evangelidis.t_tnews.views.savedArticles.SavedArticlesActivity

abstract class BaseActivity <in V : BaseContract.View, out P : BaseContract.Presenter<V>> : AppCompatActivity(), BaseContract.View {

    private val baseBinding: ActivityBaseBinding by lazy { ActivityBaseBinding.inflate(layoutInflater) }
    val presenter: P by lazy { createPresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        delegate.setContentView(baseBinding.root)
        setSupportActionBar(baseBinding.toolbar.toolbar)
        presenter.onAttach(this as V)

        baseBinding.toolbar.readSavedImage.setOnClickListener {
            presenter.navigateToSaved()
        }
    }

    override fun setToolbarTitle(title: String) {
        baseBinding.toolbar.toolbarTitle.apply {
            showIf { title.isNotBlank() }
            text = title
        }
    }

    override fun setContentView(view: View?) {
        baseBinding.baseContent.apply {
            removeAllViews()
            addView(view)
        }
    }

    override fun showLoader() {
        baseBinding.baseSpinner.show()
    }

    override fun hideLoader() {
        baseBinding.baseSpinner.hide()
    }

    override fun hideToolbar() {
        baseBinding.toolbar.toolbar.gone()
    }

    override fun navigateToSavedArticles() {
        startActivity(SavedArticlesActivity.createIntent(this.applicationContext))
    }

    override fun hideSavedArticlesImage() {
        baseBinding.toolbar.readSavedImage.hide()
    }

    abstract fun createPresenter(): P
}
