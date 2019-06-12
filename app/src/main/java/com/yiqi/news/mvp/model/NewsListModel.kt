package com.yiqi.news.mvp.model

import android.app.Application
import com.google.gson.Gson
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel

import com.jess.arms.di.scope.FragmentScope
import com.yiqi.news.app.utils.PreUtils
import com.yiqi.news.entity.NewsResponse
import javax.inject.Inject

import com.yiqi.news.mvp.contract.NewsListContract
import com.yiqi.news.mvp.model.api.service.CommonService
import io.reactivex.Observable


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
class NewsListModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager), NewsListContract.Model {

    private var lastTime: Long = 0
    override fun getNewsList(channelCode: String): Observable<NewsResponse> {
        lastTime = PreUtils.getLong(channelCode, 0)//读取对应频道下最后一次刷新的时间戳
        if (lastTime == 0L) {
            //如果为空，则是从来没有刷新过，使用当前时间戳
            lastTime = System.currentTimeMillis() / 1000
        }

        return mRepositoryManager.obtainRetrofitService(CommonService::class.java).getNewsList(channelCode, lastTime, System.currentTimeMillis() / 1000)
    }


    @Inject
    lateinit var mGson: Gson;
    @Inject
    lateinit var mApplication: Application;

    override fun onDestroy() {
        super.onDestroy();
    }
}
