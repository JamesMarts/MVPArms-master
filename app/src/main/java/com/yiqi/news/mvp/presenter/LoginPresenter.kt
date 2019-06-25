package com.yiqi.news.mvp.presenter

import android.app.Application
import com.google.gson.Gson

import com.jess.arms.integration.AppManager
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.mvp.BasePresenter
import com.jess.arms.http.imageloader.ImageLoader
import com.yiqi.news.app.service.UserController
import com.yiqi.news.app.utils.PreUtils
import com.yiqi.news.app.utils.RxUtils
import com.yiqi.news.entity.BaseResponse
import com.yiqi.news.entity.News
import com.yiqi.news.entity.NewsResponse
import com.yiqi.news.entity.User
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject

import com.yiqi.news.mvp.contract.LoginContract
import com.yiqi.news.mvp.model.api.HttpConstant
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import java.util.ArrayList


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/06/2019 13:47
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
class LoginPresenter
@Inject
constructor(model: LoginContract.Model, rootView: LoginContract.View) :
        BasePresenter<LoginContract.Model, LoginContract.View>(model, rootView) {
    @Inject
    lateinit var mErrorHandler: RxErrorHandler
    @Inject
    lateinit var mApplication: Application
    @Inject
    lateinit var mImageLoader: ImageLoader
    @Inject
    lateinit var mAppManager: AppManager


    fun sendCode(mobile: String) {
        mModel.sendCode(mobile)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(object : ErrorHandleSubscriber<BaseResponse<User>>(mErrorHandler) {

                    override fun onNext(t: BaseResponse<User>) {
                        mRootView.showCodeSuccess()
                    }
                })
    }

    fun login(mobile: String, pwd: String) {
        mModel.login(mobile, pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(object : ErrorHandleSubscriber<BaseResponse<User>>(mErrorHandler) {

                    override fun onNext(t: BaseResponse<User>) {
                        if (t.code == HttpConstant.LOGIN_NOT_BIND) {
                            mRootView.showUnBind()
                        } else {

                            mRootView.showLoginSuccess()
                        }
                    }
                })
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
