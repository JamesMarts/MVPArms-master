package com.yiqi.news.di.module

import com.jess.arms.di.scope.FragmentScope

import dagger.Module
import dagger.Provides

import com.yiqi.news.mvp.contract.UserContract
import com.yiqi.news.mvp.model.UserModel


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/06/2019 15:43
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
//构建UserModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class UserModule(private val view: UserContract.View) {
    @FragmentScope
    @Provides
    fun provideUserView(): UserContract.View {
        return this.view
    }

    @FragmentScope
    @Provides
    fun provideUserModel(model: UserModel): UserContract.Model {
        return model
    }
}
