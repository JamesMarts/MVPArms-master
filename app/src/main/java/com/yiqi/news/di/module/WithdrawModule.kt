package com.yiqi.news.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.yiqi.news.mvp.contract.WithdrawContract
import com.yiqi.news.mvp.model.WithdrawModel


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/10/2019 17:02
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
//构建WithdrawModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class WithdrawModule(private val view: WithdrawContract.View) {
    @ActivityScope
    @Provides
    fun provideWithdrawView(): WithdrawContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideWithdrawModel(model: WithdrawModel): WithdrawContract.Model {
        return model
    }
}
