package com.yiqi.news.mvp.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yiqi.huize.loadImage
import com.yiqi.news.R
import kotlinx.android.synthetic.main.item_video.view.*

class VideoAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_video) {

    override fun convert(helper: BaseViewHolder, item: String) {
        val itemView = helper.itemView
        itemView.iv_video_bg.loadImage("http://p3.pstatp.com/large/pgc-image/RQIXwJSVBgdNP")

    }
}
