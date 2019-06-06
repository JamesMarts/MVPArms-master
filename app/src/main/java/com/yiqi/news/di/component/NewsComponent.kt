package com.yiqi.news.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.yiqi.news.di.module.NewsModule

import com.jess.arms.di.scope.ActivityScope
import com.yiqi.news.mvp.ui.fragment.NewsFragment


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
@ActivityScope
@Component(modules = arrayOf(NewsModule::class), dependencies = arrayOf(AppComponent::class))
interface NewsComponent {
    fun inject(fragment: NewsFragment)
}
