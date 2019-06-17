package com.yiqi.news.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.yiqi.news.di.module.CodeModule

import com.jess.arms.di.scope.ActivityScope
import com.yiqi.news.mvp.ui.activity.CodeActivity


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
@ActivityScope
@Component(modules = arrayOf(CodeModule::class), dependencies = arrayOf(AppComponent::class))
interface CodeComponent {
    fun inject(activity: CodeActivity)
}
