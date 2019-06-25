package com.yiqi.news.mvp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import butterknife.OnClick

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import com.yiqi.news.Constant

import com.yiqi.news.di.component.DaggerCodeComponent
import com.yiqi.news.di.module.CodeModule
import com.yiqi.news.mvp.contract.CodeContract
import com.yiqi.news.mvp.presenter.CodePresenter

import com.yiqi.news.R
import com.yiqi.news.app.widget.SeparatedEditText
import kotlinx.android.synthetic.main.activity_code.*


class CodeActivity : BaseActivity<CodePresenter>(), CodeContract.View, SeparatedEditText.TextChangedListener {
    override fun showBindError(errMsg: String) {
       showMessage(errMsg)
    }

    override fun showBindSuccess() {
        finish()
    }


    private val mMobile: String by lazy {
        return@lazy intent.getStringExtra("mobile")
    }

    private val mCode: String by lazy {
        return@lazy intent.getStringExtra("code")
    }

    override fun textChanged(changeText: CharSequence?) {
        btn_login_code_enter.isEnabled = changeText?.length == 6
    }

    override fun textCompleted(text: CharSequence?) {
        btn_login_code_enter.isEnabled = true
    }

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerCodeComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .codeModule(CodeModule(this))
                .build()
                .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_code //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        edt_login_code.setTextChangedListener(this)
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
        mImmersionBar.titleBar(R.id.tool_code).init()
    }

    @OnClick(R.id.btn_code_back,R.id.btn_login_code_enter)
    fun onClick(view: View) {
        when (view.id) {
            R.id.btn_code_back -> killMyself()
            R.id.btn_login_code_enter->mPresenter?.bind(mMobile,mCode,edt_login_code.text.toString())

        }
    }
}
