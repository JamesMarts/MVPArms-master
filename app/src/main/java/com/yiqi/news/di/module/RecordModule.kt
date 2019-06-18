package com.yiqi.news.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.yiqi.news.mvp.contract.RecordContract
import com.yiqi.news.mvp.model.RecordModel


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/18/2019 18:19
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
//构建RecordModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class RecordModule(private val view: RecordContract.View) {
    @ActivityScope
    @Provides
    fun provideRecordView(): RecordContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideRecordModel(model: RecordModel): RecordContract.Model {
        return model
    }
}
