package com.yiqi.news.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.yiqi.news.mvp.contract.UserinfoContract
import com.yiqi.news.mvp.model.UserinfoModel


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/20/2019 16:02
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
//构建UserinfoModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class UserinfoModule(private val view: UserinfoContract.View) {
    @ActivityScope
    @Provides
    fun provideUserinfoView(): UserinfoContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideUserinfoModel(model: UserinfoModel): UserinfoContract.Model {
        return model
    }
}
