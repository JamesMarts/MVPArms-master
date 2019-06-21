package com.yiqi.news.mvp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import com.jess.arms.widget.DividerItemDecoration
import com.novel.cn.ext.dp2px

import com.yiqi.news.di.component.DaggerCollectionComponent
import com.yiqi.news.di.module.CollectionModule
import com.yiqi.news.mvp.contract.CollectionContract
import com.yiqi.news.mvp.presenter.CollectionPresenter

import com.yiqi.news.R
import com.yiqi.news.entity.News
import com.yiqi.news.mvp.ui.adapter.NewsAdapter
import com.yiqi.news.mvp.ui.adapter.WithdrawRecordAdapter
import kotlinx.android.synthetic.main.activity_collection.*
import kotlinx.android.synthetic.main.activity_record.*
import kotlinx.android.synthetic.main.include_title.*
import kotlinx.android.synthetic.main.include_title.view.*


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/19/2019 10:44
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
class CollectionActivity : BaseActivity<CollectionPresenter>(), CollectionContract.View {

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerCollectionComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .collectionModule(CollectionModule(this))
                .build()
                .inject(this)
    }

    private lateinit var mAdapter: NewsAdapter

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_collection //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }



    override fun initData(savedInstanceState: Bundle?) {
        toolbar.toolbar_title.text = "我的收藏"
        initAdapter()
    }

    private fun initAdapter() {

        rv_collection.layoutManager = LinearLayoutManager(this)
        rv_collection.addItemDecoration(DividerItemDecoration(DividerItemDecoration.VERTICAL).setHeight(dp2px(4)))
        mAdapter = NewsAdapter()
        mAdapter.bindToRecyclerView(rv_collection)
        var userList: MutableList<News> = ArrayList()
        userList.add(0, News())
        userList.add(1,News())
        userList.add(2,News())
        userList.add(3,News())
        mAdapter.setNewData(userList)


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
        mImmersionBar.titleBar(R.id.toolbar).init()
    }
}
