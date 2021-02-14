package com.evangelidis.t_tnews.interfaces

interface BaseContract {

    interface View {
        fun setToolbarTitle(title: String)
        fun showLoader()
        fun hideLoader()
        fun hideToolbar()
        fun navigateToSavedArticles()
        fun hideSavedArticlesImage()
    }

    interface Presenter<in T : View> {
        fun onAttach(view: T)
        fun onDetach()
        fun navigateToSaved()
    }
}
