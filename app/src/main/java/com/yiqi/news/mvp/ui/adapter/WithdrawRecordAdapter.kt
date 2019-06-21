package com.yiqi.news.mvp.ui.adapter

import android.view.View.GONE
import android.view.View.VISIBLE
import cn.jzvd.JzvdStd
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.socks.library.KLog
import com.yiqi.huize.loadImage
import com.yiqi.news.R
import com.yiqi.news.app.utils.TimeUtils
import com.yiqi.news.app.utils.UIUtils
import com.yiqi.news.app.video.VideoPathDecoder
import com.yiqi.news.app.video.VideoStateListenerAdapter
import com.yiqi.news.entity.News
import kotlinx.android.synthetic.main.item_video.view.*

class WithdrawRecordAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_withdraw_record) {

    override fun convert(helper: BaseViewHolder, item: String) {
        val itemView = helper.itemView

    }
}