package com.lgc.mymode.util.web.api;


import com.lgc.lgcutillibrary.BuildConfig;

/**
 * @autor feijin_lgc
 * <p>
 * create at 2017/9/9 12:19
 */
public class URLManager {

    static {
        //配合retrofit，需要以/结尾
        if (BuildConfig.DEBUG) {
            BASE_URL = "http://education.51feijin.com";
        } else {
            BASE_URL = "http://education.51feijin.com";
        }
    }

    public static final String BASE_URL;
}



