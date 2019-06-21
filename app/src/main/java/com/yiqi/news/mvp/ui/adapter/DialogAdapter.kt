package com.yiqi.news.mvp.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yiqi.news.R
import kotlinx.android.synthetic.main.item_label.view.*
import kotlinx.android.synthetic.main.item_user_info.view.*

class DialogAdapter: BaseQuickAdapter<String,BaseViewHolder>(R.layout.item_user_info){
    override fun convert(helper: BaseViewHolder, item: String) {
        val itemView = helper.itemView
        itemView.tv_dialog_user.text=item
    }

}
