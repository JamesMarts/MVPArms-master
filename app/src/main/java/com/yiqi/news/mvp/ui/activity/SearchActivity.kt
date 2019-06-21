package com.yiqi.news.mvp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.yiqi.news.di.component.DaggerSearchComponent
import com.yiqi.news.di.module.SearchModule
import com.yiqi.news.mvp.contract.SearchContract
import com.yiqi.news.mvp.presenter.SearchPresenter

import com.yiqi.news.R
import com.yiqi.news.mvp.ui.adapter.SearchHistoryAdapter
import com.yiqi.news.mvp.ui.adapter.SearchHotAdapter
import com.yiqi.news.mvp.ui.adapter.VideoAdapter
import kotlinx.android.synthetic.main.activity_search.*


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/20/2019 10:21
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
class SearchActivity : BaseActivity<SearchPresenter>(), SearchContract.View {
    override fun showHotData(hot: List<String>) {
        mHotAdapter.setNewData(hot)
    }

    override fun showHistoryData(history: List<String>) {
        mHistoryAdapter.setNewData(history)
    }

    private val mHistoryAdapter: SearchHistoryAdapter by lazy {
        return@lazy SearchHistoryAdapter()
    }

    private val mHotAdapter: SearchHotAdapter by lazy {
        return@lazy SearchHotAdapter()
    }

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerSearchComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .searchModule(SearchModule(this))
                .build()
                .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_search //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        initAdapter()
        mPresenter?.getHotData()
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
        mImmersionBar.titleBar(tool_search).init()
    }

    private fun initAdapter() {
        rv_search_hot.layoutManager = GridLayoutManager(this, 5)
        rv_search_history.layoutManager = GridLayoutManager(this, 5)
        mHotAdapter.bindToRecyclerView(rv_search_hot)
        mHistoryAdapter.bindToRecyclerView(rv_search_history)
    }
}
