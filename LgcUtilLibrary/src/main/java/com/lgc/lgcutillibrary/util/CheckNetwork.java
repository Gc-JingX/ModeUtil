package com.lgc.lgcutillibrary.util;

import android.content.Context;
import android.net.ConnectivityManager;


/**
 * <pre>
 *     author : feijin_lgc
 *     e-mail : 595184932@qq.com
 *     time   : 2017/10/31 10:11
 *     desc   :检查网络
 *     version: 1.0
 * </pre>
 */
public class CheckNetwork {

    /**
     * 单纯检查网络
     */
    public static boolean checkNetwork(final Context myContext) {
        // ProgressDialogUtil.show(myContext);
        boolean flag = false;
        ConnectivityManager CM = (ConnectivityManager) myContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (CM.getActiveNetworkInfo() != null)
            flag = CM.getActiveNetworkInfo().isAvailable();
        return flag;
    }
}
