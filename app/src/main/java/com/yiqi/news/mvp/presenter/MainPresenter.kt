package com.yiqi.news.mvp.presenter

import android.app.Application
import android.support.v4.app.Fragment
import com.flyco.tablayout.listener.CustomTabEntity

import com.jess.arms.integration.AppManager
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.mvp.BasePresenter
import com.jess.arms.http.imageloader.ImageLoader
import com.yiqi.news.R
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject

import com.yiqi.news.mvp.contract.MainContract
import com.yiqi.news.mvp.model.entity.TabEntity
import com.yiqi.news.mvp.ui.fragment.NewsFragment
import com.yiqi.news.mvp.ui.fragment.UserFragment
import com.yiqi.news.mvp.ui.fragment.VideoFragment
import java.util.ArrayList


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 03/26/2019 21:28
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
class MainPresenter
@Inject
constructor(model: MainContract.Model, rootView: MainContract.View) :
        BasePresenter<MainContract.Model, MainContract.View>(model, rootView) {
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

    fun requestTab() {
        val fragments = arrayOf<Fragment>(
                NewsFragment.newInstance(),
                VideoFragment.newInstance(),
                UserFragment.newInstance())
        val tabEntity = ArrayList<CustomTabEntity>()
        val titles = mApplication.resources.getStringArray(R.array.tab_title)
        val iconSu = mApplication.resources.obtainTypedArray(R.array.tab_selected)
        val iconUn = mApplication.resources.obtainTypedArray(R.array.tab_normal)
        for (i in titles.indices) {
            tabEntity.add(TabEntity(titles[i], iconSu.getResourceId(i, 0), iconUn.getResourceId(i, 0)))
        }
        mRootView.initTab(fragments, tabEntity)
    }

}
