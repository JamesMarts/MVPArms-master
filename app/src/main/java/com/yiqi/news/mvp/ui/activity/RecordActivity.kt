package com.yiqi.news.mvp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import com.jess.arms.widget.DividerItemDecoration
import com.novel.cn.ext.dp2px

import com.yiqi.news.di.component.DaggerRecordComponent
import com.yiqi.news.di.module.RecordModule
import com.yiqi.news.mvp.contract.RecordContract
import com.yiqi.news.mvp.presenter.RecordPresenter

import com.yiqi.news.R
import com.yiqi.news.mvp.ui.adapter.CoinsAdapter
import com.yiqi.news.mvp.ui.adapter.WithdrawRecordAdapter
import kotlinx.android.synthetic.main.activity_record.*
import kotlinx.android.synthetic.main.activity_withdraw.*
import kotlinx.android.synthetic.main.include_title.*
import kotlinx.android.synthetic.main.include_title.view.*


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/18/2019 18:19
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
class RecordActivity : BaseActivity<RecordPresenter>(), RecordContract.View {

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerRecordComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .recordModule(RecordModule(this))
                .build()
                .inject(this)
    }

    private lateinit var mAdapter: WithdrawRecordAdapter

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_record //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }



    override fun initData(savedInstanceState: Bundle?) {
        toolbar.toolbar_title.text = "提现记录"
        initAdapter()
    }

    private fun initAdapter() {

        rv_record.layoutManager = LinearLayoutManager(this)
        rv_record.addItemDecoration(DividerItemDecoration(DividerItemDecoration.VERTICAL).setHeight(dp2px(4)))
        mAdapter = WithdrawRecordAdapter()
        mAdapter.bindToRecyclerView(rv_record)
        val mList: List<String> = listOf("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "")
        mAdapter.setNewData(mList)


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
