/*
 * Copyright 2017 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yiqi.news.mvp.model.api.service

import com.yiqi.news.entity.BaseResponse
import io.reactivex.Observable

import com.yiqi.news.entity.User
import retrofit2.Retrofit
import retrofit2.http.*

/**
 * ================================================
 * 展示 [Retrofit.create] 中需要传入的 ApiService 的使用方式
 * 存放关于用户的一些 API
 *
 *
 * Created by JessYan on 08/05/2016 12:05
 * [Contact me](mailto:jess.yan.effort@gmail.com)
 * [Follow me](https://github.com/JessYanCoding)
 * ================================================
 */
interface UserService {


    /**
     * 发送短信验证码
     * */
    @POST("api/sms")

    fun sendCode(@Query("phone") phone: String): Observable<BaseResponse<User>>


    /**
     * 登录
     * */
    @POST("api/login")

    fun login(@Query("mobile") mobile: String, @Query("code") code: String): Observable<BaseResponse<User>>

    /**
     * 绑定邀请码
     * */
    @POST("api/register")

    fun bind(@Body map: HashMap<String, Any?>): Observable<BaseResponse<User>>



    /**
     * 获取用户信息
     * */
    @POST("api/user/userInfo")

    fun userInfo(): Observable<BaseResponse<User>>


}