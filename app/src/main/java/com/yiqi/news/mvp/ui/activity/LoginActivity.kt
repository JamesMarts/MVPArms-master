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
import com.yiqi.huize.mvp.dialog.OpenPageDialog
import com.yiqi.news.Constant

import com.yiqi.news.di.component.DaggerLoginComponent
import com.yiqi.news.di.module.LoginModule
import com.yiqi.news.mvp.contract.LoginContract
import com.yiqi.news.mvp.presenter.LoginPresenter

import com.yiqi.news.R
import com.yiqi.news.app.widget.qmui.QMUITipDialog
import kotlinx.android.synthetic.main.activity_login.*


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/06/2019 13:47
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
class LoginActivity : BaseActivity<LoginPresenter>(), LoginContract.View, View.OnFocusChangeListener, TextWatcher, OpenPageDialog.OnClickListener {


    override fun showUnBind() {
        var bundle = Bundle()
        bundle.putString("mobile", edt_login_phone.text.toString())
        bundle.putString("code", edt_login_code.text.toString())
        ArmsUtils.startActivity(this, CodeActivity::class.java, bundle)
        killMyself()
    }

    override fun showLoginSuccess() {
        killMyself()
    }

    override fun showCodeSuccess() {
        mQMUIInfoDialog.show()
    }

    override fun onOpen() {
        ArmsUtils.startActivity(BindMobileActivity::class.java)
    }

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerLoginComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .loginModule(LoginModule(this))
                .build()
                .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_login //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        edt_login_phone.onFocusChangeListener = this
        edt_login_code.onFocusChangeListener = this

        edt_login_phone.addTextChangedListener(this)
        edt_login_code.addTextChangedListener(this)
    }

    override fun onFocusChange(view: View?, hasFocus: Boolean) {
        when (view?.id) {
            R.id.edt_login_phone -> if (hasFocus) view_login_phone.setBackgroundColor(ArmsUtils.getColor(this, R.color.color_A22217)) else view_login_phone.setBackgroundColor(ArmsUtils.getColor(this, R.color.color_CCCCCC))
            R.id.edt_login_code -> if (hasFocus) view_login_code.setBackgroundColor(ArmsUtils.getColor(this, R.color.color_A22217)) else view_login_code.setBackgroundColor(ArmsUtils.getColor(this, R.color.color_CCCCCC))
        }
    }


    @OnClick(R.id.btn_login_code, R.id.btn_login, R.id.btn_login_wechat, R.id.btn_login_weibo, R.id.btn_login_qq, R.id.btn_login_close)
    fun onClick(view: View) {
        when (view.id) {
            R.id.btn_login_code -> mPresenter?.sendCode(edt_login_phone.text.toString().trim())
            R.id.btn_login -> mPresenter?.login(edt_login_phone.text.toString().trim(), edt_login_code.text.toString().trim())
            R.id.btn_login_wechat -> openPage(Constant.WE_CHAT)
            R.id.btn_login_weibo -> openPage(Constant.WEIBO)
            R.id.btn_login_qq -> openPage(Constant.QQ)
            R.id.btn_login_close -> killMyself()
        }
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

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }

    override fun afterTextChanged(s: Editable?) {
        if (edt_login_phone.text.isNotEmpty() && edt_login_code.text.isNotEmpty()) btn_login.isEnabled = true else false
    }


    override fun initImmersionBar() {
        super.initImmersionBar()
        mImmersionBar.titleBar(R.id.tool_login).init()
    }

    private var mLoginType: Int = 0
    private val mQMUIInfoDialog: QMUITipDialog by lazy {
        return@lazy QMUITipDialog.Builder(this)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_SUCCESS)
                .setTipWord(getString(R.string.dialog_code_send_success))
                .create()
    }
    private val mOpenPageDialog by lazy {
        return@lazy OpenPageDialog(this)
    }


    private fun openPage(type: Int) {
        mLoginType = type
        when (type) {

            Constant.WE_CHAT -> {
                mOpenPageDialog.setDesc("\"NewsApp\"想要打开微信")
            }
            Constant.WEIBO -> {
                mOpenPageDialog.setDesc("\"NewsApp\"想要打开微博")
            }
            Constant.QQ -> {
                mOpenPageDialog.setDesc("\"NewsApp\"想要打开QQ")
            }
        }
        mOpenPageDialog.show()
        mOpenPageDialog.setOnClickListener(this)
    }


}
