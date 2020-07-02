package jp.medpeer.benkyou.extension

import android.view.View
import android.view.View.*
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter


object ExtViewLayout {

    @BindingAdapter("isGone")
    @JvmStatic
    fun setViewGone(view: View, isGone: Boolean) {
        view.visibility = if (isGone) GONE else VISIBLE
    }

    @BindingAdapter("isInvisible")
    @JvmStatic
    fun setViewInvisible(view: View, isInvisible: Boolean) {
        view.visibility = if (isInvisible) INVISIBLE else VISIBLE
    }

    @BindingAdapter("backgroundResource")
    @JvmStatic
    fun setBackgroundResource(view: View, @DrawableRes resourceId: Int) {
        view.setBackgroundResource(resourceId)
    }
}

fun View.isGone(isGone: Boolean) {
    ExtViewLayout.setViewGone(this, isGone)
}
