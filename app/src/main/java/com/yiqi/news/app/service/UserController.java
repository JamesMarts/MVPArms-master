package com.yiqi.news.app.service;

import com.yiqi.news.app.base.BaseApplication;
import com.yiqi.news.app.utils.SPUtil;
import com.yiqi.news.entity.User;

public class UserController {
    /**
     * 保存用户信息时使用的SharedPreferences文件名
     */
    private static final String FILE_NAME = "yiqi_user_file";

    // 登录结果信息,用于在SharedPreferences中保存或获取用户信息的key
    private static final String LOGIN_INFO = "LOGIN_INFO";

    // 用户登录的用户名等信息,用于在SharedPreferences中保存或获取用户信息的key
    private static final String LOGIN_USER = "LOGIN_USER";

    /**
     * 用户是否接收推送消息
     */
    public static final String CONFIG_KEY_ALLOW_LOOK = "CONFIG_ALLOW_LOOK";

    private static final String USER_CENTER = "USER_CENTER";

    private static final String IS_REMEMBER_PASSWORD = "IS_REMEMBER_PASSWORD";

    private static final Object lock = new Object();


    private User mTokenInfo;

    private static UserController instance;

    private UserController() {
    }

    public static UserController getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new UserController();
                }
            }
        }
        return instance;
    }

    /**
     * 从本地获取保存的“记住密码”状态
     *
     * @return
     */
    public boolean isRememberPassword() {
        boolean isRememberPassword = false;
        try {
            isRememberPassword = (boolean) SPUtil.get(BaseApplication.getContext(), FILE_NAME, IS_REMEMBER_PASSWORD, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isRememberPassword;
    }

    /**
     * 设置记住密码的状态-保存到本地
     *
     * @param isRememberPassword
     */
    public void setRememberPassword(boolean isRememberPassword) {
        SPUtil.put(BaseApplication.getContext(), FILE_NAME, IS_REMEMBER_PASSWORD, isRememberPassword);
    }

    /**
     * 保存用户帐号信息
     *
     * @param info
     */
    public void saveUserCache(User info) {

        if (info != null) {
            SPUtil.saveObj(BaseApplication.getContext(), FILE_NAME, LOGIN_USER, info);
        } else {
            SPUtil.clearByKey(BaseApplication.getContext(), FILE_NAME, LOGIN_USER);
        }
    }

    /**
     * 获取保存的用户帐号信息
     *
     * @return
     */
    public User getUserCache() {
        User user = null;
        try {
            user = (User) SPUtil.readObj(BaseApplication.getContext(), FILE_NAME, LOGIN_USER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * 保存登录结果
     *
     * @param info
     */
    public void saveLoginResultCache(User info) {
        //保存到内存
        mTokenInfo = info;
        //保存到本地
        if (info != null) {
            SPUtil.saveObj(BaseApplication.getContext(), FILE_NAME, LOGIN_INFO, info);
        } else {
            SPUtil.clearByKey(BaseApplication.getContext(), FILE_NAME, LOGIN_INFO);
        }
    }

    /**
     * 获取登录结果，首先从内存中获取，内存中没有从磁盘缓存中取
     *
     * @return
     */
    public User getLoginResultCache() {

        if (mTokenInfo == null) {
            try {
                mTokenInfo = (User) SPUtil.readObj(BaseApplication.getContext(), FILE_NAME, LOGIN_INFO);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mTokenInfo;
    }


    /**
     * 获取Token
     *
     * @return
     */
    public String getToken() {
        User info = getLoginResultCache();
        if (info != null) {
            return info.getToken();
        }
        return null;
    }

    /**
     * 获取刷新Token
     *
     * @return
     */
    public String getRefreshToken() {
        User info = getLoginResultCache();
        if (info != null) {
            return info.getToken();
        }
        return null;
    }

    /**
     * 刷新Token后保存新登录信息
     *
     * @param newInfo
     */
    public void refreshToken(User newInfo) {
        saveLoginResultCache(newInfo);
    }


    /**
     * 是否登录
     *
     * @return true:已登录 false:未登录
     */
    public boolean isLogin() {
        return getLoginResultCache() != null;
    }

    /**
     * 退出登录 ，清空登录结果，及登录保存的用户名密码等
     */
    public void loginOut() {
        //清空登录结果，token信息等
        saveLoginResultCache(null);

        //如果不是记住密码-就清空用户的登录信息
        if (!isRememberPassword()) {
            saveUserCache(null);
        }
    }

    /**
     * 是否允许锁屏显示
     *
     * @return
     */
    public  boolean isAllowLook() {
        try {
            return (boolean) SPUtil.get(BaseApplication.getContext(), CONFIG_KEY_ALLOW_LOOK, false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public  void setAllowLook(boolean isAllowPush) {
        SPUtil.put(BaseApplication.getContext(), CONFIG_KEY_ALLOW_LOOK, isAllowPush);
    }
}
