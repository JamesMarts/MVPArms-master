package com.yiqi.news.mvp.model

import android.app.Application
import com.google.gson.Gson
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel

import com.jess.arms.di.scope.ActivityScope
import com.yiqi.news.entity.BaseResponse
import com.yiqi.news.entity.User
import javax.inject.Inject

import com.yiqi.news.mvp.contract.CodeContract
import com.yiqi.news.mvp.model.api.service.UserService
import io.reactivex.Observable


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
class CodeModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager), CodeContract.Model {
    override fun bindInvitedCode(map: HashMap<String, Any?>): Observable<BaseResponse<User>> {
        return mRepositoryManager.obtainRetrofitService(UserService::class.java).bind(map)
    }

    @Inject
    lateinit var mGson: Gson;
    @Inject
    lateinit var mApplication: Application;

    override fun onDestroy() {
        super.onDestroy();
    }
}
