package com.yiqi.news.mvp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.yiqi.news.app.widget.qmui.QMUITipDialog
import com.yiqi.news.mvp.ui.activity.*
import kotlinx.android.synthetic.main.fragment_user.*


class UserFragment : BaseFragment<UserPresenter>(), UserContract.View {

    override fun initEvent() {

    }

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
                .setTipWord(getString(R.string.dialog_copy_code_success))
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

    override fun initView() {

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

    @OnClick(R.id.tv_user_invited_code, R.id.btn_user_withdraw, R.id.btn_user_info, R.id.btn_user_record, R.id.btn_user_collection,R.id.btn_user_setting,R.id.btn_user_msg,R.id.iv_user_avatar)
    fun onClick(view: View) {
        when (view.id) {
            R.id.iv_user_avatar -> ArmsUtils.startActivity(UserinfoActivity::class.java)
            R.id.tv_user_invited_code -> mQMUIInfoDialog.show()
            R.id.btn_user_withdraw -> ArmsUtils.startActivity(WithdrawActivity::class.java)
            R.id.btn_user_info -> ArmsUtils.startActivity(LoginActivity::class.java)
            R.id.btn_user_record -> ArmsUtils.startActivity(RecordActivity::class.java)
            R.id.btn_user_collection -> ArmsUtils.startActivity(CollectionActivity::class.java)
            R.id.btn_user_setting -> ArmsUtils.startActivity(SettingActivity::class.java)
            R.id.btn_user_msg -> ArmsUtils.startActivity(MessageActivity::class.java)
        }
    }

}
