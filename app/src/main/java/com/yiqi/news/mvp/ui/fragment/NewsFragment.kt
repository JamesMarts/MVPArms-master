package com.yiqi.news.mvp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Message
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.OnClick

import com.jess.arms.base.BaseFragment
import com.jess.arms.base.BaseLazyLoadFragment
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.yiqi.news.di.component.DaggerNewsComponent
import com.yiqi.news.di.module.NewsModule
import com.yiqi.news.mvp.contract.NewsContract
import com.yiqi.news.mvp.presenter.NewsPresenter

import com.yiqi.news.R
import com.yiqi.news.mvp.ui.activity.SearchActivity
import kotlinx.android.synthetic.main.fragment_news.*
import java.util.ArrayList


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/06/2019 13:59
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
class NewsFragment : BaseFragment<NewsPresenter>(), NewsContract.View {
    override fun initView() {

    }


    override fun getTabNewsData(tabs: Array<String>, fragment: ArrayList<Fragment>) {
        tl_news.setFragmentViewPager(vp, tabs, this, fragment)
    }

    override fun initEvent() {

    }

    override fun lazyFetchData() {
    }


    companion object {
        fun newInstance(): NewsFragment {
            val fragment = NewsFragment()
            return fragment
        }
    }


    override fun setupFragmentComponent(appComponent: AppComponent) {
        DaggerNewsComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .newsModule(NewsModule(this))
                .build()
                .inject(this)
    }

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    override fun initData(savedInstanceState: Bundle?) {
        mPresenter?.getTabData()
    }

    override fun setData(data: Any?) {

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
        mImmersionBar.titleBar(rl_title).init()
    }

    override fun useImmersionBar(): Boolean? = true

    @OnClick(R.id.iv_news_search, R.id.iv_news_calender)
    fun onClick(view: View) {
        when (view.id) {
            R.id.iv_news_search -> ArmsUtils.startActivity(SearchActivity::class.java)
        }
    }
}
