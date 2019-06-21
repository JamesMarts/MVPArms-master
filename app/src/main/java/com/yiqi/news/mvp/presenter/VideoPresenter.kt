package com.yiqi.news.mvp.presenter

import android.app.Application
import android.os.Bundle
import android.support.v4.app.Fragment
import com.flyco.tablayout.listener.CustomTabEntity

import com.jess.arms.integration.AppManager
import com.jess.arms.di.scope.FragmentScope
import com.jess.arms.mvp.BasePresenter
import com.jess.arms.http.imageloader.ImageLoader
import com.yiqi.news.Constant
import com.yiqi.news.R
import com.yiqi.news.entity.Channel
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject

import com.yiqi.news.mvp.contract.VideoContract
import com.yiqi.news.mvp.ui.fragment.NewsListFragment
import com.yiqi.news.mvp.ui.fragment.VideoListFragment
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
@FragmentScope
class VideoPresenter
@Inject
constructor(model: VideoContract.Model, rootView: VideoContract.View) :
        BasePresenter<VideoContract.Model, VideoContract.View>(model, rootView) {
    @Inject
    lateinit var mErrorHandler: RxErrorHandler
    @Inject
    lateinit var mApplication: Application
    @Inject
    lateinit var mImageLoader: ImageLoader
    @Inject
    lateinit var mAppManager: AppManager


    override fun onDestroy() {
        super.onDestroy()
    }

    fun getTabData() {
        val mFragments = ArrayList<Fragment>()
        val mSelectedChannels = ArrayList<Channel>()
        val channels = mApplication.resources.getStringArray(R.array.channel_video)
        val channelCodes = mApplication.resources.getStringArray(R.array.channel_code_video)

        //默认添加了全部频道
        for (i in channelCodes.indices) {
            val title = channels[i]
            val code = channelCodes[i]
            mSelectedChannels.add(Channel(title, code))
        }

        for (channel in mSelectedChannels) {
            val newsFragment = VideoListFragment.newInstance()
            val bundle = Bundle()
            bundle.putString(Constant.CHANNEL_CODE, channel.channelCode)
            newsFragment.setArguments(bundle)
            mFragments.add(newsFragment)//添加到集合中
        }

        mRootView.getTabVideoData(channels, mFragments)
    }
}
