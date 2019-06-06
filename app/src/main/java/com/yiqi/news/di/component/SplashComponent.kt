package com.yiqi.news.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.yiqi.news.di.module.SplashModule

import com.jess.arms.di.scope.ActivityScope
import com.yiqi.news.mvp.ui.activity.SplashActivity


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/06/2019 16:42
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
@Component(modules = arrayOf(SplashModule::class), dependencies = arrayOf(AppComponent::class))
interface SplashComponent {
    fun inject(activity: SplashActivity)
}
