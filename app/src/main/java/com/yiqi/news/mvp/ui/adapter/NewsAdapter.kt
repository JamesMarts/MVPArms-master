package com.yiqi.news.mvp.ui.adapter

import android.support.transition.Visibility
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yiqi.huize.loadImage
import com.yiqi.news.R
import com.yiqi.news.app.utils.TimeUtils
import com.yiqi.news.entity.News
import kotlinx.android.synthetic.main.item_news.view.*

class NewsAdapter : BaseQuickAdapter<News, BaseViewHolder>(R.layout.item_news) {

    override fun convert(helper: BaseViewHolder, item: News) {
        val itemView = helper.itemView

        itemView.iv_news_thumb.visibility = if (helper.adapterPosition == 0 || helper.adapterPosition == 1) GONE else VISIBLE
        itemView.iv_news_delete.visibility = if (helper.adapterPosition == 0 || helper.adapterPosition == 1) GONE else VISIBLE
        itemView.tv_news_title.text = item.title
        itemView.tv_news_read_count.text = item.read_count.toString() + "浏览"
        itemView.tv_news_read_time.text = TimeUtils.getShortTime(item.behot_time * 1000)

        if (item.image_list != null && !item.image_list.isEmpty()) {
            if (item.image_list[0] != null) {
                if (item.image_list[0].url_list != null && !item.image_list[0].url_list.isEmpty()) {
                    itemView.iv_news_thumb.loadImage(item.image_list[0].url_list[0].url)
                }
            }
        } else {
            itemView.iv_news_thumb.visibility = GONE
        }
    }
}
