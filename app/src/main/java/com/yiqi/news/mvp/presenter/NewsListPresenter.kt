package com.yiqi.news.mvp.presenter

import android.app.Application
import com.google.gson.Gson

import com.jess.arms.integration.AppManager
import com.jess.arms.di.scope.FragmentScope
import com.jess.arms.mvp.BasePresenter
import com.jess.arms.http.imageloader.ImageLoader
import com.yiqi.news.R
import com.yiqi.news.app.utils.PreUtils
import com.yiqi.news.app.utils.RxUtils
import com.yiqi.news.entity.News
import com.yiqi.news.entity.NewsResponse
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject

import com.yiqi.news.mvp.contract.NewsListContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import java.util.ArrayList


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
@FragmentScope
class NewsListPresenter
@Inject
constructor(model: NewsListContract.Model, rootView: NewsListContract.View) :
        BasePresenter<NewsListContract.Model, NewsListContract.View>(model, rootView) {
    private var lastTime: Long = 0
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


    fun getNewsData(channelCode: String) {
        mModel.getNewsList(channelCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(object : ErrorHandleSubscriber<NewsResponse>(mErrorHandler){
                    override fun onNext(t: NewsResponse) {
                        lastTime = System.currentTimeMillis() / 1000
                        PreUtils.putLong(channelCode, lastTime)//保存刷新的时间戳

                        val data = t.data
                        val newsList = ArrayList<News>()
                        if (!data.isEmpty()) {
                            for (newsData in data) {
                                val news = Gson().fromJson(newsData.content, News::class.java)
                                newsList.add(news)
                            }
                        }
                        mRootView.showNewsData(newsList)
                    }
                })
    }
}
