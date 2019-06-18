package com.yiqi.news.mvp.ui.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import butterknife.OnClick
import com.gyf.barlibrary.ImmersionBar

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import com.jess.arms.widget.DividerItemDecoration
import com.novel.cn.ext.dp2px

import com.yiqi.news.di.component.DaggerWithdrawComponent
import com.yiqi.news.di.module.WithdrawModule
import com.yiqi.news.mvp.contract.WithdrawContract
import com.yiqi.news.mvp.presenter.WithdrawPresenter

import com.yiqi.news.R
import com.yiqi.news.mvp.ui.adapter.CoinsAdapter
import com.yiqi.news.mvp.ui.adapter.NewsAdapter
import com.yiqi.news.mvp.ui.adapter.VideoAdapter
import kotlinx.android.synthetic.main.activity_withdraw.*
import kotlinx.android.synthetic.main.fragment_news_list.*
import kotlinx.android.synthetic.main.include_title.*


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/10/2019 17:02
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
class WithdrawActivity : BaseActivity<WithdrawPresenter>(), WithdrawContract.View {

    private lateinit var mAdapter: CoinsAdapter

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerWithdrawComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .withdrawModule(WithdrawModule(this))
                .build()
                .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_withdraw //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        initAdapter()
    }

    private fun initAdapter() {

        rv_coins_record.layoutManager = LinearLayoutManager(this)
        rv_coins_record.addItemDecoration(DividerItemDecoration(DividerItemDecoration.VERTICAL).setHeight(dp2px(4)))
        mAdapter = CoinsAdapter()
        mAdapter.bindToRecyclerView(rv_coins_record)
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

    override fun useImmersionBar(): Boolean {
        return true
    }

    override fun initImmersionBar() {
        super.initImmersionBar()
        mImmersionBar.titleBar(R.id.tool_withdraw).init()
    }

    @OnClick(R.id.btn_user_withdraw)
    fun onClick(view: View) {
        when (view.id) {
            R.id.btn_user_withdraw -> ArmsUtils.startActivity(ExchangeActivity::class.java)

        }
    }

}
