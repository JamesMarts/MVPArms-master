package com.yiqi.news.mvp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.KeyEvent
import com.flyco.tablayout.listener.CustomTabEntity

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import com.yiqi.news.R
import com.yiqi.news.di.component.DaggerMainComponent
import com.yiqi.news.di.module.MainModule
import com.yiqi.news.mvp.contract.MainContract
import com.yiqi.news.mvp.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList


class MainActivity : BaseActivity<MainPresenter>(), MainContract.View {

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerMainComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .mainModule(MainModule(this))
                .build()
                .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_main //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        mPresenter?.requestTab()
    }


    override fun launchActivity(intent: Intent) {
        ArmsUtils.startActivity(intent)
    }

    override fun killMyself() {
        finish()
    }

    override fun initTab(fragments: Array<Fragment>, tabLayout: ArrayList<CustomTabEntity>) {
        tab_home.setTabData(tabLayout, this, R.id.fl_change, fragments.toList() as ArrayList<Fragment>)
    }

    override fun useImmersionBar(): Boolean? = true

    override fun showMessage(message: String) {

    }

    private var exitTime: Long = 0
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - exitTime > 2000) {
                ArmsUtils.snackbarText("再按一次退出聚财赚")
                exitTime = System.currentTimeMillis()
            } else {
                finish()
                ArmsUtils.exitApp()
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

}
