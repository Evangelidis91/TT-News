package com.evangelidis.t_tnews.views.savedArticles

import androidx.recyclerview.widget.RecyclerView
import com.evangelidis.t_tnews.R
import com.evangelidis.t_tnews.databases.SavedArticle
import com.evangelidis.t_tnews.databinding.ItemArticleBinding
import com.evangelidis.t_tnews.extensions.gone
import com.evangelidis.t_tnews.extensions.show
import com.evangelidis.t_tnews.utils.ItemsManager
import com.evangelidis.t_tnews.views.main.ArticleItemCallback

class SavedArticlesViewHolder(private val binding: ItemArticleBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(article: SavedArticle, isArticleSaved: Boolean, callback: ArticleItemCallback) {
        if (article.image.isNullOrEmpty() || article.image == "null") {
            binding.imageDivider.show()
        } else {
            binding.imageDivider.gone()
            ItemsManager.getImageTopRadius(itemView.context, article.image, binding.articleNews)
        }

        binding.paperUrl.text = article.source.orEmpty()
        binding.title.text = article.title
        binding.postTime.gone()

        if (isArticleSaved) {
            binding.wishlistImg.setImageResource(R.drawable.ic_enable_watchlist)
        } else {
            binding.wishlistImg.setImageResource(R.drawable.ic_disable_watchlist)
        }

        binding.root.setOnClickListener {
            callback.navigateToUrl(article.url)
        }

        binding.wishlistImg.setOnClickListener {
            callback.removeSavedArticle(article.url)
        }
    }
}
