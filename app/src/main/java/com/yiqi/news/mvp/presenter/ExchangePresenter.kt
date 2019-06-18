package com.yiqi.news.mvp.presenter

import android.app.Application

import com.jess.arms.integration.AppManager
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.mvp.BasePresenter
import com.jess.arms.http.imageloader.ImageLoader
import com.yiqi.news.R
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject

import com.yiqi.news.mvp.contract.ExchangeContract


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/18/2019 14:24
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
class ExchangePresenter
@Inject
constructor(model: ExchangeContract.Model, rootView: ExchangeContract.View) :
        BasePresenter<ExchangeContract.Model, ExchangeContract.View>(model, rootView) {
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

   fun doGetBanklist(){
       var array= mutableListOf<String>("建设银行","招商银行","农业银行","中国银行")
       array.add(array.size,"添加银行卡")
       mRootView.showMyBankcardList(array as ArrayList<String>)
   }
}
