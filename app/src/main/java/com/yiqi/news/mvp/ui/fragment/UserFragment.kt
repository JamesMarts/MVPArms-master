package com.yiqi.news.mvp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Message
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import butterknife.OnClick

import com.jess.arms.base.BaseFragment
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import com.yiqi.huize.loadImage

import com.yiqi.news.di.component.DaggerUserComponent
import com.yiqi.news.di.module.UserModule
import com.yiqi.news.mvp.contract.UserContract
import com.yiqi.news.mvp.presenter.UserPresenter

import com.yiqi.news.R
import com.yiqi.news.app.ui.QMUITipDialog
import com.yiqi.news.mvp.ui.activity.WithdrawActivity
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.fragment_user.*


class UserFragment : BaseFragment<UserPresenter>(), UserContract.View {


    override fun lazyFetchData() {

    }

    companion object {
        fun newInstance(): UserFragment {
            val fragment = UserFragment()
            return fragment
        }
    }

    private val mQMUIInfoDialog: QMUITipDialog by lazy {
        return@lazy QMUITipDialog.Builder(mContext)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_SUCCESS)
                .setTipWord("复制邀请码成功！")
                .create()
    }

    override fun setupFragmentComponent(appComponent: AppComponent) {
        DaggerUserComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .userModule(UserModule(this))
                .build()
                .inject(this)
    }

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    override fun initData(savedInstanceState: Bundle?) {
        iv_user_avatar.loadImage("http://img3.imgtn.bdimg.com/it/u=3914950518,3569645197&fm=26&gp=0.jpg")
    }

    override fun setData(data: Any?) {


    }

    override fun showMessage(message: String) {

    }

    @OnClick(R.id.tv_user_invited_code, R.id.btn_user_withdraw)
    fun onClick(view: View) {
        when (view.id) {
            R.id.tv_user_invited_code -> mQMUIInfoDialog.show()
            R.id.btn_user_withdraw -> ArmsUtils.startActivity(WithdrawActivity::class.java )
        }
    }

}
