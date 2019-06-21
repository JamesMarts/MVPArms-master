package com.yiqi.news.mvp.ui.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chaychan.uikit.refreshlayout.BGANormalRefreshViewHolder
import com.chaychan.uikit.refreshlayout.BGARefreshLayout
import com.jess.arms.base.BaseFragment

import com.jess.arms.base.BaseLazyLoadFragment
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import com.jess.arms.widget.DividerItemDecoration
import com.novel.cn.ext.dp2px
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import com.yiqi.news.Constant

import com.yiqi.news.di.component.DaggerNewsListComponent
import com.yiqi.news.di.module.NewsListModule
import com.yiqi.news.mvp.contract.NewsListContract
import com.yiqi.news.mvp.presenter.NewsListPresenter

import com.yiqi.news.R
import com.yiqi.news.entity.News
import com.yiqi.news.mvp.ui.activity.NewsDetailActivity
import com.yiqi.news.mvp.ui.activity.VideoDetailActivity
import com.yiqi.news.mvp.ui.adapter.NewsAdapter
import com.yiqi.news.mvp.ui.adapter.VideoAdapter
import kotlinx.android.synthetic.main.fragment_news_list.*


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/11/2019 15:52
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
class NewsListFragment : BaseFragment<NewsListPresenter>(), NewsListContract.View, OnRefreshListener, BaseQuickAdapter.OnItemClickListener, BGARefreshLayout.BGARefreshLayoutDelegate {
    override fun showEmpty() {
        mStateView.showEmpty()
    }


    private val mAdapter: NewsAdapter by lazy {
        return@lazy NewsAdapter()
    }
    private val mVideoAdapter: VideoAdapter by lazy {
        return@lazy VideoAdapter()
    }

    private val mChannelCode: String by lazy {
        return@lazy arguments!!.getString(Constant.CHANNEL_CODE)
    }

    private val isVideoList: Boolean by lazy {
        return@lazy arguments!!.getBoolean(Constant.IS_VIDEO_LIST)
    }


    override fun onRefresh(refreshLayout: RefreshLayout) {

    }


    override fun onBGARefreshLayoutBeginLoadingMore(refreshLayout: BGARefreshLayout?): Boolean {
        return false
    }

    @SuppressLint("CheckResult")
    override fun onBGARefreshLayoutBeginRefreshing(refreshLayout: BGARefreshLayout?) {
        mChannelCode?.let { mPresenter?.getNewsData(it) }

    }


    override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
        if (isVideoList) ArmsUtils.startActivity(VideoDetailActivity::class.java) else ArmsUtils.startActivity(NewsDetailActivity::class.java)
    }


    override fun showNewsData(string: List<News>, tips: String) {
        refresh_layout.endRefreshing()
        tip_view.show(tips.replace("今日头条","聚财赚"))
        mStateView.showContent()
        if (isVideoList) mVideoAdapter.setNewData(string) else mAdapter.setNewData(string)

    }

    override fun lazyFetchData() {

        mChannelCode?.let { mPresenter?.getNewsData(it) }
    }


    override fun initView() {
        initRefreshLayout()
    }

    override fun initEvent() {
        mStateView.showLoading()
    }


    companion object {
        fun newInstance(): NewsListFragment {
            val fragment = NewsListFragment()
            return fragment
        }
    }


    override fun setupFragmentComponent(appComponent: AppComponent) {
        DaggerNewsListComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .newsListModule(NewsListModule(this))
                .build()
                .inject(this)
    }

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_news_list, container, false);
    }

    override fun initData(savedInstanceState: Bundle?) {
        initAdapter(isVideoList)


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


    fun initRefreshLayout() {
        refresh_layout.setDelegate(this)
        // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
        val refreshViewHolder = BGANormalRefreshViewHolder(mContext, false)
        // 设置下拉刷新
        refreshViewHolder.setRefreshViewBackgroundColorRes(R.color.color_F3F5F4)//背景色
        refreshViewHolder.setPullDownRefreshText(getString(R.string.refresh_pull_down_text))//下拉的提示文字
        refreshViewHolder.setReleaseRefreshText(getString(R.string.refresh_release_text))//松开的提示文字
        refreshViewHolder.setRefreshingText(getString(R.string.refresh_ing_text))//刷新中的提示文字


        // 设置下拉刷新和上拉加载更多的风格
        refresh_layout.setRefreshViewHolder(refreshViewHolder)
        refresh_layout.shouldHandleRecyclerViewLoadingMore(rv_news)
    }

    private fun initAdapter(isVideoList: Boolean) {
        rv_news.layoutManager = LinearLayoutManager(activity)
        if (isVideoList) {
            mVideoAdapter.bindToRecyclerView(rv_news)
            mVideoAdapter.onItemClickListener = this
        } else {
            rv_news.addItemDecoration(DividerItemDecoration(DividerItemDecoration.VERTICAL).setHeight(dp2px(4)))
            mAdapter.bindToRecyclerView(rv_news)
            mAdapter.onItemClickListener = this
        }
    }
}
