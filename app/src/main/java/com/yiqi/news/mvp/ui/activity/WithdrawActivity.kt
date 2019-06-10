package com.yiqi.news.mvp.ui.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import com.gyf.barlibrary.ImmersionBar

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.yiqi.news.di.component.DaggerWithdrawComponent
import com.yiqi.news.di.module.WithdrawModule
import com.yiqi.news.mvp.contract.WithdrawContract
import com.yiqi.news.mvp.presenter.WithdrawPresenter

import com.yiqi.news.R
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



}
