package com.yiqi.huize.mvp.dialog

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.flyco.dialog.widget.base.BaseDialog
import com.yiqi.huize.onClick
import com.yiqi.news.R
import kotlinx.android.synthetic.main.dialog_open_page.*

class OpenPageDialog(context: Context) : BaseDialog<OpenPageDialog>(context) {

    private lateinit var mDesc: String

    private var mOnClickListener: OnClickListener? = null

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.mOnClickListener = onClickListener
    }

    init {
        widthScale(0.75f)
    }

    override fun onCreateView(): View {
        return LayoutInflater.from(mContext).inflate(R.layout.dialog_open_page, null, false)
    }

    override fun setUiBeforShow() {
        mDesc?.let { tv_desc.text = it }
        tv_cancel.onClick { dismiss() }
        tv_open.onClick {
            mOnClickListener?.onOpen()
            dismiss()
        }
    }

    fun setDesc(desc: String) {
        mDesc = desc
    }

    interface OnClickListener {
        fun onOpen()
    }

}
