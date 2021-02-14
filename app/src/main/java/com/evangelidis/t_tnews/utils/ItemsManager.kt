package com.evangelidis.t_tnews.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners
import com.evangelidis.t_tnews.R
import com.evangelidis.tantintoast.TanTinToast

object ItemsManager {

    fun getImageTopRadius(context: Context, url: String, target: ImageView) {
        Glide.with(context)
            .load(url)
            .transform(CenterInside(), GranularRoundedCorners(context.resources.getDimension(R.dimen.image_top_radius), context.resources.getDimension(R.dimen.image_top_radius), 0f, 0f))
            .into(target)
    }

    fun warningToast(context: Context, text: String) {
        TanTinToast
            .Warning(context)
            .text(text)
            .typeface(R.font.open_sans_regular)
            .show()
    }
}
