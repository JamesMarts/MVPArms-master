package com.yiqi.news.mvp.presenter

import android.app.Application
import android.os.Bundle
import android.support.v4.app.Fragment

import com.jess.arms.integration.AppManager
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.mvp.BasePresenter
import com.jess.arms.http.imageloader.ImageLoader
import com.yiqi.news.Constant
import com.yiqi.news.R
import com.yiqi.news.entity.Channel
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject

import com.yiqi.news.mvp.contract.NewsContract
import com.yiqi.news.mvp.ui.fragment.NewsListFragment
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
@ActivityScope
class NewsPresenter
@Inject
constructor(model: NewsContract.Model, rootView: NewsContract.View) :
        BasePresenter<NewsContract.Model, NewsContract.View>(model, rootView) {
    @Inject
    lateinit var mErrorHandler: RxErrorHandler
    @Inject
    lateinit var mApplication: Application
    @Inject
    lateinit var mImageLoader: ImageLoader
    @Inject
    lateinit var mAppManager: AppManager


    override fun onDestroy() {
        super.onDestroy();
    }

    fun getTabData() {
        val mFragments = ArrayList<Fragment>()
        val mSelectedChannels = ArrayList<Channel>()
        val channels = mApplication.resources.getStringArray(R.array.channel)
        val channelCodes = mApplication.resources.getStringArray(R.array.channel_code)

        //默认添加了全部频道
        for (i in channelCodes.indices) {
            val title = channels[i]
            val code = channelCodes[i]
            mSelectedChannels.add(Channel(title, code))
        }

        for (channel in mSelectedChannels) {
            val newsFragment = NewsListFragment.newInstance()
            val bundle = Bundle()
            bundle.putString(Constant.CHANNEL_CODE, channel.channelCode)
            bundle.putBoolean(Constant.IS_VIDEO_LIST, channel.channelCode.equals(channelCodes[1]))//是否是视频列表页面,根据判断频道号是否是视频
            newsFragment.setArguments(bundle)
            mFragments.add(newsFragment)//添加到集合中
        }

        mRootView.getTabNewsData(channels, mFragments)
    }
}
