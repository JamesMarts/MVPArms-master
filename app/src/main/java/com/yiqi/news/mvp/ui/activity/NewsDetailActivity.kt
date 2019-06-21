package com.yiqi.news.mvp.ui.activity

import android.content.Intent
import android.os.Bundle

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.yiqi.news.di.component.DaggerNewsDetailComponent
import com.yiqi.news.di.module.NewsDetailModule
import com.yiqi.news.mvp.contract.NewsDetailContract
import com.yiqi.news.mvp.presenter.NewsDetailPresenter

import com.yiqi.news.R
import com.yiqi.news.mvp.ui.adapter.LabelAdapter
import com.yiqi.news.mvp.ui.holder.NewsDetailHeaderView
import kotlinx.android.synthetic.main.activity_news_detail.*
import kotlinx.android.synthetic.main.activity_video_detail.*


class NewsDetailActivity : BaseActivity<NewsDetailPresenter>(), NewsDetailContract.View {

    private val mHeaderView: NewsDetailHeaderView by lazy {
        return@lazy NewsDetailHeaderView(this)
    }


    private val mCommentAdapter: LabelAdapter by lazy {
        return@lazy LabelAdapter()
    }



    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerNewsDetailComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .newsDetailModule(NewsDetailModule(this))
                .build()
                .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_news_detail //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        mCommentAdapter.bindToRecyclerView(rv_comment_news)
        mCommentAdapter.setHeaderView(mHeaderView)
    }



    override fun showMessage(message: String) {
        ArmsUtils.snackbarText(message)
    }

    override fun launchActivity(intent: Intent) {
        ArmsUtils.startActivity(intent)
    }

    override fun killMyself() {
        finish()
    }

    override fun initImmersionBar() {
        super.initImmersionBar()
        mImmersionBar.titleBar(R.id.toolbar).init()
    }
}
