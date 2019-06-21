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
package com.yiqi.news.mvp.model.entity;

/**
 * ================================================
 * User 实体类
 * <p>
 * Created by JessYan on 04/09/2016 17:14
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public class User {


    /**
     * userId : 3
     * username : 李雪焦
     * mobile : 13197281200
     * nickName : lxj
     * identityCard :
     * age : 24
     * sex : 1
     * headImg :
     * birthday : 1995-12-29
     * invitationCode : ugQEKK
     * readToday :
     * autoBrowse : 1
     * userAppVersion : 1
     * createTime : 1560828816903
     * updateTime : 1560917117454
     * token : 1780ca0427ce4aff9227f8fbc6cc7c03
     */

    private int userId;
    private String username;
    private String mobile;
    private String nickName;
    private String identityCard;
    private int age;
    private int sex;
    private String headImg;
    private String birthday;
    private String invitationCode;
    private String readToday;
    private int autoBrowse;
    private int userAppVersion;
    private long createTime;
    private long updateTime;
    private String token;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public String getReadToday() {
        return readToday;
    }

    public void setReadToday(String readToday) {
        this.readToday = readToday;
    }

    public int getAutoBrowse() {
        return autoBrowse;
    }

    public void setAutoBrowse(int autoBrowse) {
        this.autoBrowse = autoBrowse;
    }

    public int getUserAppVersion() {
        return userAppVersion;
    }

    public void setUserAppVersion(int userAppVersion) {
        this.userAppVersion = userAppVersion;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
