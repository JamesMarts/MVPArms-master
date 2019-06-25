package com.yiqi.news.mvp.ui.activity

import android.content.Intent
import android.os.Bundle

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.yiqi.news.di.component.DaggerSplashComponent
import com.yiqi.news.di.module.SplashModule
import com.yiqi.news.mvp.contract.SplashContract
import com.yiqi.news.mvp.presenter.SplashPresenter

import com.yiqi.news.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import java.util.concurrent.TimeUnit


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
/**
 * 如果没presenter
 * 你可以这样写
 *
 * @ActivityScope(請注意命名空間) class NullObjectPresenterByActivity
 * @Inject constructor() : IPresenter {
 * override fun onStart() {
 * }
 *
 * override fun onDestroy() {
 * }
 * }
 */
class SplashActivity : BaseActivity<SplashPresenter>(), SplashContract.View {

    lateinit var subscribe: Disposable//保存订阅者
    var count: Int = 2

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerSplashComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .splashModule(SplashModule(this))
                .build()
                .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_splash //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        start()

    }

    fun start() {

        subscribe = Observable.interval(1, TimeUnit.SECONDS)//按时间间隔发送整数的Observable
                .observeOn(AndroidSchedulers.mainThread())//切换到主线程修改UI
                .subscribe {
                    val show = count - it
                    if (show < 0.toLong()) {//当倒计时小于0,计时结束
                        stop()
                        ArmsUtils.startActivity(MainActivity::class.java)
                        finish()
                        return@subscribe//使用标记跳出方法
                    }
                }
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showMessage(message: String) {

    }

    override fun launchActivity(intent: Intent) {
        ArmsUtils.startActivity(intent)
    }

    override fun killMyself() {
        finish()
    }
    /**
     * 结束计时,重新开始
     */
    fun stop() {
        subscribe.dispose()//取消订阅
        return
    }

    override fun useImmersionBar(): Boolean {
        return true
    }

    override fun initImmersionBar() {
        super.initImmersionBar()
        mImmersionBar.fullScreen(true).navigationBarColor(R.color.colorWhite).init()
    }

    override fun onDestroy() {
        super.onDestroy()

    }

}
