package com.yiqi.news.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.yiqi.news.mvp.contract.NewsContract
import com.yiqi.news.mvp.model.NewsModel


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/06/2019 13:59
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
//构建NewsModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class NewsModule(private val view: NewsContract.View) {
    @ActivityScope
    @Provides
    fun provideNewsView(): NewsContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideNewsModel(model: NewsModel): NewsContract.Model {
        return model
    }
}
