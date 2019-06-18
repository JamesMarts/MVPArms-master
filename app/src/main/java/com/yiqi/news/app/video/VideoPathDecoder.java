package com.yiqi.news.app.video;

import android.annotation.SuppressLint;
import android.util.Base64;
import android.util.Log;

import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.integration.RepositoryManager;
import com.jess.arms.utils.ArmsUtils;
import com.socks.library.KLog;
import com.yiqi.news.app.utils.RxUtils;
import com.yiqi.news.entity.ResultResponse;
import com.yiqi.news.entity.VideoModel;
import com.yiqi.news.mvp.model.api.service.CommonService;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.CRC32;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
/**
 * Created by Administrator on 2017/2/9 0009.
 */

public abstract class VideoPathDecoder {

    public static final String TAG = VideoPathDecoder.class.getSimpleName();

    @SuppressLint("CheckResult")
    public void decodePath(String srcUrl) {
        KLog.e(TAG,"srcUrl: " + srcUrl);
        IRepositoryManager repositoryManager= new RepositoryManager();
         repositoryManager.obtainRetrofitService(CommonService.class)
                 .getVideoHtml(srcUrl)
                 .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
//                 .compose(RxUtils.applySchedulers(mRootView))
                 .subscribe(new Observer<String>() {
                     @Override
                     public void onSubscribe(Disposable d) {
                         ArmsUtils.snackbarText("onSubscribe");
                     }

                     @Override
                     public void onNext(String s) {
                         ArmsUtils.snackbarText(s);
                     }

                     @Override
                     public void onError(Throwable e) {
                         ArmsUtils.snackbarText(e.getLocalizedMessage());
                     }

                     @Override
                     public void onComplete() {
                         ArmsUtils.snackbarText("onComplete");
                     }
                 });





    }


    private String getRandom() {
        Random random = new Random();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            result.append(random.nextInt(10));
        }
        return result.toString();
    }

    public abstract void onSuccess(String url);
    public abstract void onDecodeError(String errorMsg);
}
