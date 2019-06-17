package com.yiqi.news.mvp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import butterknife.OnClick

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.yiqi.news.di.component.DaggerBindMobileComponent
import com.yiqi.news.di.module.BindMobileModule
import com.yiqi.news.mvp.contract.BindMobileContract
import com.yiqi.news.mvp.presenter.BindMobilePresenter

import com.yiqi.news.R
import kotlinx.android.synthetic.main.activity_bind_mobile.*
import kotlinx.android.synthetic.main.activity_login.*


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/17/2019 17:30
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
class BindMobileActivity : BaseActivity<BindMobilePresenter>(), BindMobileContract.View, View.OnFocusChangeListener, TextWatcher {


    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerBindMobileComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .bindMobileModule(BindMobileModule(this))
                .build()
                .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_bind_mobile //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        edt_bind_phone.onFocusChangeListener = this
        edt_bind_code.onFocusChangeListener = this

        edt_bind_phone.addTextChangedListener(this)
        edt_bind_code.addTextChangedListener(this)
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

    @OnClick(R.id.btn_bind_back)
    fun onClick(view: View) {
        when (view.id) {
            R.id.btn_bind_back -> killMyself()

        }
    }
    override fun initImmersionBar() {
        super.initImmersionBar()
        mImmersionBar.titleBar(R.id.tool_bind).init()
    }
    override fun afterTextChanged(s: Editable?) {
        if (edt_bind_phone.text.isNotEmpty() && edt_bind_code.text.isNotEmpty()) btn_bind.isEnabled = true else false
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }

    override fun onFocusChange(view: View?, hasFocus: Boolean) {
        when (view?.id) {
            R.id.edt_bind_phone -> if (hasFocus) view_bind_phone.setBackgroundColor(ArmsUtils.getColor(this, R.color.color_A22217)) else view_bind_phone.setBackgroundColor(ArmsUtils.getColor(this, R.color.color_CCCCCC))
            R.id.edt_bind_code -> if (hasFocus) view_bind_code.setBackgroundColor(ArmsUtils.getColor(this, R.color.color_A22217)) else view_bind_code.setBackgroundColor(ArmsUtils.getColor(this, R.color.color_CCCCCC))
        }
    }

}
