package com.yiqi.news.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.yiqi.news.mvp.contract.NewsDetailContract
import com.yiqi.news.mvp.model.NewsDetailModel


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/19/2019 18:01
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
//构建NewsDetailModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class NewsDetailModule(private val view: NewsDetailContract.View) {
    @ActivityScope
    @Provides
    fun provideNewsDetailView(): NewsDetailContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideNewsDetailModel(model: NewsDetailModel): NewsDetailContract.Model {
        return model
    }
}
