package com.yiqi.news.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.yiqi.news.mvp.contract.MessageContract
import com.yiqi.news.mvp.model.MessageModel


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/19/2019 11:09
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
//构建MessageModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class MessageModule(private val view: MessageContract.View) {
    @ActivityScope
    @Provides
    fun provideMessageView(): MessageContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideMessageModel(model: MessageModel): MessageContract.Model {
        return model
    }
}
