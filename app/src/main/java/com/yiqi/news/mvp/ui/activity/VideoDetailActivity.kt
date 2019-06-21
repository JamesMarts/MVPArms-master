package com.yiqi.news.mvp.ui.activity

import android.content.Intent
import android.os.Bundle

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.yiqi.news.di.component.DaggerVideoDetailComponent
import com.yiqi.news.di.module.VideoDetailModule
import com.yiqi.news.mvp.contract.VideoDetailContract
import com.yiqi.news.mvp.presenter.VideoDetailPresenter

import com.yiqi.news.R
import com.yiqi.news.mvp.ui.adapter.LabelAdapter
import com.yiqi.news.mvp.ui.holder.NewsDetailHeaderView
import kotlinx.android.synthetic.main.activity_video_detail.*


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/19/2019 18:16
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
/**
 * 如果没presenter
 * 你可以这样写
 *
 * @ActivityScope(請注意命名空間) class NullObjectPresenterByActivity
 * @Inject constructor() : IPresenter {
 * override fun onStart() {
 * }
 *
 * override fun onDestroy() {
 * }
 * }
 */
class VideoDetailActivity : BaseActivity<VideoDetailPresenter>(), VideoDetailContract.View {

    private val mHeaderView: NewsDetailHeaderView by lazy {
        return@lazy NewsDetailHeaderView(this)
    }


    private val mCommentAdapter: LabelAdapter by lazy {
        return@lazy LabelAdapter()
    }


    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerVideoDetailComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .videoDetailModule(VideoDetailModule(this))
                .build()
                .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_video_detail //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        mCommentAdapter.bindToRecyclerView(rv_comment_video)
        mCommentAdapter.setHeaderView(mHeaderView)
    }


    override fun showLoading() {

    }

    override fun hideLoading() {

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
        mImmersionBar.titleBar(R.id.fl_top).init()
    }

}
