package com.yiqi.news.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.yiqi.news.mvp.contract.CollectionContract
import com.yiqi.news.mvp.model.CollectionModel


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/19/2019 10:44
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
//构建CollectionModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class CollectionModule(private val view: CollectionContract.View) {
    @ActivityScope
    @Provides
    fun provideCollectionView(): CollectionContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideCollectionModel(model: CollectionModel): CollectionContract.Model {
        return model
    }
}
