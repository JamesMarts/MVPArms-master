package com.yiqi.news.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.yiqi.news.mvp.contract.BindMobileContract
import com.yiqi.news.mvp.model.BindMobileModel


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/17/2019 17:30
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
//构建BindMobileModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class BindMobileModule(private val view: BindMobileContract.View) {
    @ActivityScope
    @Provides
    fun provideBindMobileView(): BindMobileContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideBindMobileModel(model: BindMobileModel): BindMobileContract.Model {
        return model
    }
}
