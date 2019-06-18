package com.yiqi.news.mvp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomSheetDialog
import android.support.v7.widget.DefaultItemAnimator
import android.view.LayoutInflater
import android.view.View
import butterknife.OnClick

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import com.jess.arms.widget.DividerItemDecoration
import com.novel.cn.ext.dp2px
import com.yiqi.news.Constant

import com.yiqi.news.di.component.DaggerExchangeComponent
import com.yiqi.news.di.module.ExchangeModule
import com.yiqi.news.mvp.contract.ExchangeContract
import com.yiqi.news.mvp.presenter.ExchangePresenter

import com.yiqi.news.R
import com.yiqi.news.mvp.ui.adapter.LabelAdapter
import kotlinx.android.synthetic.main.dialog_bottom_sheet_label.view.*
import kotlinx.android.synthetic.main.fragment_news_list.*
import kotlinx.android.synthetic.main.include_title.*
import kotlinx.android.synthetic.main.include_title.view.*


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/18/2019 14:24
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
class ExchangeActivity : BaseActivity<ExchangePresenter>(), ExchangeContract.View, View.OnClickListener {


    private lateinit var mView: View

    private val mBottomSheetDialog: BottomSheetDialog by lazy {
        return@lazy BottomSheetDialog(this)
    }

    private val mBatchAdapter: LabelAdapter by lazy {
        return@lazy LabelAdapter()
    }


    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerExchangeComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .exchangeModule(ExchangeModule(this))
                .build()
                .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_exchange //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        toolbar.toolbar_title.text = "提现兑换"
        toolbar.toolbar_menu.text = "规则说明"
        toolbar.toolbar_back.setOnClickListener(this)
        toolbar.toolbar_menu.setOnClickListener(this)
        initBottomSheetDialog()


    }

    override fun showMyBankcardList(cardList: ArrayList<String>) {
        mBatchAdapter.setNewData(cardList)
        mBottomSheetDialog.show()
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


    @OnClick(R.id.toolbar_back, R.id.btn_withdraw_bankcard)
    override fun onClick(view: View) {
        when (view.id) {
            R.id.toolbar_back -> killMyself()
            R.id.btn_withdraw_bankcard -> {
                mPresenter?.doGetBanklist()
            }
        }
    }


    private fun initBottomSheetDialog() {
        mView = LayoutInflater.from(this).inflate(R.layout.dialog_bottom_sheet_label, null, false)
        mView.recycler_view.addItemDecoration(DividerItemDecoration(DividerItemDecoration.VERTICAL).setHeight(dp2px(4)))
        mBatchAdapter.bindToRecyclerView(mView.recycler_view)
        mBottomSheetDialog.setContentView(mView)
        (mView.parent as View).setBackgroundColor(resources.getColor(android.R.color.transparent))
        mView.tv_dialog_close.setOnClickListener { mBottomSheetDialog.dismiss() }
        mBatchAdapter.setOnItemClickListener { adapter, view, position ->
            if (position == adapter.data.size - 1) {
                ArmsUtils.startActivity(BankcardActivity::class.java)
            } else {
                mBottomSheetDialog.dismiss()
            }
        }
    }
}
