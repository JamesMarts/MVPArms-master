package com.yiqi.news.mvp.presenter

import android.app.Application

import com.jess.arms.integration.AppManager
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.mvp.BasePresenter
import com.jess.arms.http.imageloader.ImageLoader
import com.yiqi.news.app.utils.RxUtils
import com.yiqi.news.app.utils.ValidatorUtil
import com.yiqi.news.entity.BaseResponse
import com.yiqi.news.entity.User
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject

import com.yiqi.news.mvp.contract.CodeContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/17/2019 16:28
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
class CodePresenter
@Inject
constructor(model: CodeContract.Model, rootView: CodeContract.View) :
        BasePresenter<CodeContract.Model, CodeContract.View>(model, rootView) {
    @Inject
    lateinit var mErrorHandler: RxErrorHandler
    @Inject
    lateinit var mApplication: Application
    @Inject
    lateinit var mImageLoader: ImageLoader
    @Inject
    lateinit var mAppManager: AppManager


    fun bind(mobile: String, code: String, invitationCode: String) {

        if (invitationCode.length != 6) {
            mRootView.showBindError("邀请码格式有误")
            return
        }

        var map = HashMap<String, Any?>()
        map["mobile"] = mobile
        map["code"] = code
        map["invitationCode"] = invitationCode
        mModel.bindInvitedCode(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(object : ErrorHandleSubscriber<BaseResponse<User>>(mErrorHandler) {

                    override fun onNext(t: BaseResponse<User>) {
                        mRootView.showBindSuccess()
                    }
                })

    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
