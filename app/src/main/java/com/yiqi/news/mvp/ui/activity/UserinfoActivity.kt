package com.yiqi.news.mvp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomSheetDialog
import android.view.LayoutInflater
import android.view.View
import butterknife.OnClick

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import com.jess.arms.widget.DividerItemDecoration
import com.novel.cn.ext.dp2px
import com.yiqi.huize.loadImage

import com.yiqi.news.di.component.DaggerUserinfoComponent
import com.yiqi.news.di.module.UserinfoModule
import com.yiqi.news.mvp.contract.UserinfoContract
import com.yiqi.news.mvp.presenter.UserinfoPresenter

import com.yiqi.news.R
import com.yiqi.news.app.widget.qmui.QMUITipDialog
import com.yiqi.news.mvp.ui.adapter.DialogAdapter
import com.yiqi.news.mvp.ui.adapter.LabelAdapter
import kotlinx.android.synthetic.main.activity_userinfo.*
import kotlinx.android.synthetic.main.dialog_bottom_sheet_label.view.*


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/20/2019 16:02
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
class UserinfoActivity : BaseActivity<UserinfoPresenter>(), UserinfoContract.View {
    private lateinit var mAvatarView: View
    private val mAvatarDialog: BottomSheetDialog by lazy {
        return@lazy BottomSheetDialog(this)
    }
    private val mGenderDialog: BottomSheetDialog by lazy {
        return@lazy BottomSheetDialog(this)
    }

    private val mAvatarAdapter: DialogAdapter by lazy {
        return@lazy DialogAdapter()
    }

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerUserinfoComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .userinfoModule(UserinfoModule(this))
                .build()
                .inject(this)
    }

    private val mQMUIInfoDialog: QMUITipDialog by lazy {
        return@lazy QMUITipDialog.Builder(this)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_SUCCESS)
                .setTipWord(getString(R.string.dialog_copy_code_success))
                .create()
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_userinfo //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        iv_userinfo_avatar.loadImage("http://img3.imgtn.bdimg.com/it/u=3914950518,3569645197&fm=26&gp=0.jpg")
        initBottomSheetDialog()
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


    @OnClick(R.id.iv_userinfo_avatar, R.id.tv_userinfo_invited)
    fun onClick(view: View) {
        when (view.id) {
            R.id.iv_userinfo_avatar -> mAvatarDialog.show()
            R.id.tv_userinfo_invited -> mQMUIInfoDialog.show()
        }
    }

    private fun initBottomSheetDialog() {
        mAvatarView = LayoutInflater.from(this).inflate(R.layout.dialog_bottom_sheet_label, null, false)
        mAvatarView.recycler_view.addItemDecoration(DividerItemDecoration(DividerItemDecoration.VERTICAL).setHeight(dp2px(4)))
        mAvatarView.tv_title.text = "更换头像"
        mAvatarAdapter.bindToRecyclerView(mAvatarView.recycler_view)
        var arrayAvatar = arrayOf("选择相册", "拍照")
        mAvatarAdapter.setNewData(arrayAvatar.toList())
        mAvatarDialog.setContentView(mAvatarView)
        (mAvatarView.parent as View).setBackgroundColor(resources.getColor(android.R.color.transparent))
        mAvatarView.tv_dialog_close.setOnClickListener { mAvatarDialog.dismiss() }
    }
}
