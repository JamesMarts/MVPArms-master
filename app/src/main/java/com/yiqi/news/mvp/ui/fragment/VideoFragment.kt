package com.yiqi.news.mvp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Message
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.flyco.tablayout.listener.OnTabSelectListener

import com.jess.arms.base.BaseFragment
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import com.novel.cn.ext.dp2px
import com.novel.cn.ext.sp2px

import com.yiqi.news.di.component.DaggerVideoComponent
import com.yiqi.news.di.module.VideoModule
import com.yiqi.news.mvp.contract.VideoContract
import com.yiqi.news.mvp.presenter.VideoPresenter

import com.yiqi.news.R
import kotlinx.android.synthetic.main.fragment_video.*
import java.util.ArrayList


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/06/2019 14:01
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
 * @FragmentScope(請注意命名空間) class NullObjectPresenterByFragment
 * @Inject constructor() : IPresenter {
 * override fun onStart() {
 * }
 *
 * override fun onDestroy() {
 * }
 * }
 */
class VideoFragment : BaseFragment<VideoPresenter>(), VideoContract.View{


    override fun initEvent() {

    }

    override fun getTabVideoData(tabs: Array<String>,fragment: ArrayList<Fragment>) {
        tl_video.setFragmentViewPager(vp, tabs, this, fragment)
    }

    override fun lazyFetchData() {

    }

    companion object {
        fun newInstance(): VideoFragment {
            val fragment = VideoFragment()
            return fragment
        }
    }


    override fun initView() {

    }

    override fun setupFragmentComponent(appComponent: AppComponent) {
        DaggerVideoComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .videoModule(VideoModule(this))
                .build()
                .inject(this)
    }

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_video, container, false);
    }

    override fun initData(savedInstanceState: Bundle?) {
        mPresenter?.getTabData()
    }


    override fun setData(data: Any?) {

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

    }
    override fun initImmersionBar() {
        super.initImmersionBar()
        mImmersionBar.statusBarDarkFont(true).init()
        mImmersionBar.titleBar(rl_title_video).init()
    }

    override fun useImmersionBar(): Boolean? = true
}
