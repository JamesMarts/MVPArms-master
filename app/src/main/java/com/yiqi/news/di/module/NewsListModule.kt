package com.yiqi.news.di.module

import com.jess.arms.di.scope.FragmentScope

import dagger.Module
import dagger.Provides

import com.yiqi.news.mvp.contract.NewsListContract
import com.yiqi.news.mvp.model.NewsListModel


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/11/2019 15:52
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
//构建NewsListModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class NewsListModule(private val view: NewsListContract.View) {
    @FragmentScope
    @Provides
    fun provideNewsListView(): NewsListContract.View {
        return this.view
    }

    @FragmentScope
    @Provides
    fun provideNewsListModel(model: NewsListModel): NewsListContract.Model {
        return model
    }
}
