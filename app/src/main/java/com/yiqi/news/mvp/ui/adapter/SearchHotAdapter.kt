package com.yiqi.news.mvp.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yiqi.news.R
import kotlinx.android.synthetic.main.item_label.view.*

class SearchHotAdapter: BaseQuickAdapter<String,BaseViewHolder>(R.layout.item_search_hot){
    override fun convert(helper: BaseViewHolder, item: String) {
        val itemView = helper.itemView

    }

}
