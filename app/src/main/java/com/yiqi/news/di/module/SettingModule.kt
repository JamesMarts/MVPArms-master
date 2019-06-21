package com.yiqi.news.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.yiqi.news.mvp.contract.SettingContract
import com.yiqi.news.mvp.model.SettingModel


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/19/2019 11:34
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
//构建SettingModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class SettingModule(private val view: SettingContract.View) {
    @ActivityScope
    @Provides
    fun provideSettingView(): SettingContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideSettingModel(model: SettingModel): SettingContract.Model {
        return model
    }
}
