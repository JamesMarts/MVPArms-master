package com.yiqi.news.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.yiqi.news.mvp.contract.CodeContract
import com.yiqi.news.mvp.model.CodeModel


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/17/2019 16:28
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
//构建CodeModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class CodeModule(private val view: CodeContract.View) {
    @ActivityScope
    @Provides
    fun provideCodeView(): CodeContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideCodeModel(model: CodeModel): CodeContract.Model {
        return model
    }
}
