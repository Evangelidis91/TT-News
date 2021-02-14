package com.evangelidis.t_tnews.views.main

import androidx.recyclerview.widget.RecyclerView
import com.evangelidis.t_tnews.R
import com.evangelidis.t_tnews.databases.SavedArticle
import com.evangelidis.t_tnews.databinding.ItemArticleBinding
import com.evangelidis.t_tnews.extensions.gone
import com.evangelidis.t_tnews.extensions.show
import com.evangelidis.t_tnews.models.modelsClasses.Article
import com.evangelidis.t_tnews.utils.ItemsManager.getImageTopRadius
import org.joda.time.DateTime
import org.joda.time.DateTimeZone

class ArticleViewHolder(private val binding: ItemArticleBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(article: Article, isArticleSaved: Boolean, callback: ArticleItemCallback) {
        if (article.urlToImage.isNullOrEmpty()) {
            binding.imageDivider.show()
            binding.articleNews.gone()
        } else {
            binding.imageDivider.gone()
            getImageTopRadius(itemView.context, article.urlToImage.toString(), binding.articleNews)
        }

        binding.paperUrl.text = article.source?.name.orEmpty()
        binding.title.text = article.title
        setTime(article.publishedAt)

        if (isArticleSaved) {
            binding.wishlistImg.setImageResource(R.drawable.ic_enable_watchlist)
        } else {
            binding.wishlistImg.setImageResource(R.drawable.ic_disable_watchlist)
        }

        binding.root.setOnClickListener {
            callback.navigateToUrl(article.url)
        }

        binding.wishlistImg.setOnClickListener {
            if (isArticleSaved) {
                callback.removeSavedArticle(article.url.orEmpty())
            } else {
                callback.saveArticle(
                    SavedArticle(
                        article.url.orEmpty(),
                        article.source?.name.orEmpty(),
                        article.title.orEmpty(),
                        article.publishedAt.orEmpty(),
                        article.urlToImage
                    )
                )
            }
        }
    }

    private fun setTime(date: String?) {
        if (date.isNullOrEmpty()) {
            binding.postTime.gone()
        } else {
            val gr = DateTime().withZone(DateTimeZone.forID("Europe/Athens"))
            val postHour = date.drop(11).dropLast(7)

            binding.postTime.text = (gr.hourOfDay - postHour.toInt()).toString() + " ώρες πριν"
            binding.postTime.show()
        }
    }
}
