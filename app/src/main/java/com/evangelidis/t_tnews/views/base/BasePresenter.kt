package com.evangelidis.t_tnews.views.base

import com.evangelidis.t_tnews.interfaces.BaseContract

open class BasePresenter<T : BaseContract.View> : BaseContract.Presenter<T> {

    var view: T? = null

    override fun onAttach(view: T) {
        this.view = view
    }

    override fun onDetach() {
        this.view = null
    }

    override fun navigateToSaved() {
        view?.navigateToSavedArticles()
    }

    fun isViewAttached(): Boolean = view != null
}
