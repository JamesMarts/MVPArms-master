package com.yiqi.huize

import android.content.Context
import android.support.annotation.ColorRes
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.jess.arms.http.imageloader.glide.ImageConfigImpl
import com.jess.arms.utils.ArmsUtils
import com.yiqi.news.R
import okhttp3.MultipartBody
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * 点击事件扩展方法
 */
fun View.onClick(method: () -> Unit): View {
    setOnClickListener { method.invoke() }
    return this
}



/**
 * 点击事件扩展方法
 */
fun View.onClick(listener: View.OnClickListener): View {
    setOnClickListener(listener)
    return this
}



fun EditText.focuse() {
    this.isFocusable = true;
    this.isFocusableInTouchMode = true;
    this.requestFocus();
}

fun List<*>?.isNullOrEmpty(): Boolean {
    return this == null || this.isEmpty()
}

fun Any?.isNull(): Boolean {
    return this == null
}

fun String?.isNullorEmpty(): Boolean {
    return this == null || this.isEmpty()
}

/**
 * editext 代替addTextChangedListener
 */
fun EditText.setOnTextChanged(method: (s: CharSequence?, start: Int, before: Int, count: Int) -> Unit): EditText {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            method.invoke(s, start, before, count)
        }
    })
    return this
}

fun ViewPager.OnPageChangeListener(method: (position: Int) -> Unit) {
    addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {

        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

        }

        override fun onPageSelected(position: Int) {
            method.invoke(position)
        }
    })
}

fun click(vararg views: View, method: (view: View) -> Unit) {
    for (item in views) {
        item.setOnClickListener {
            method.invoke(it)
        }
    }
}

fun MultipartBody.Builder.addFormDataStringPart(key: String, value: String?) {
    this.addFormDataPart(key, if (value == null) "" else value)
}

fun String.toDate(pattern: String): Date {
    return SimpleDateFormat(pattern).parse(this)
}

fun Date.format(pattern: String): String {
    return SimpleDateFormat(pattern).format(this)
}

/**
 * 设置View的可见
 */
fun View.isVisible(isVisible: Boolean): View {
    visibility = if (isVisible) VISIBLE else GONE
    return this
}

fun Number.format(): String {
    val eps = 1e-10

    val numberFormat = NumberFormat.getInstance()
    if (this.toDouble() - Math.floor(this.toDouble()) < eps) {
        return numberFormat.format(this.toInt())
    }
    return numberFormat.format(this.toDouble())
}


fun TextView.setTextColorRes(@ColorRes res: Int) {
    this.setTextColor(ContextCompat.getColor(this.context, res))
}


fun Context.getColorRes(@ColorRes res: Int): Int {
    return ContextCompat.getColor(this, res)
}

fun Context.dp2px(dp: Number): Int {
    return ArmsUtils.dip2px(this, dp.toFloat())
}


fun ImageView.loadImage(url: String?) {
    loadImage(url, R.color.color_gray_d8, R.color.color_gray_d8)
}


fun ImageView.loadImage(url: String?, placeholder: Int, error: Int) {

    if (TextUtils.isEmpty(url)) {
        return
    }

    val impl: ImageConfigImpl.Builder? = ImageConfigImpl.builder()


    impl?.let {
        it.cacheStrategy(3)
        ArmsUtils.obtainAppComponentFromContext(this.context)
                .imageLoader()
                .loadImage(this.context, it
                        .url(url)
                        .placeholder(placeholder)
                        .errorPic(error)
                        .imageView(this)
                        .build())
    }

}

