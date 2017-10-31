package com.lgc.lgcutillibrary.net.retrofit;


import android.util.Log;


import com.lgc.lgcutillibrary.R;
import com.lgc.lgcutillibrary.util.ResUtil;

import retrofit2.Call;
import retrofit2.Response;

/**
 * @autor feijin_lgc
 * <p>
 * create at 2017/9/9 12:18
 */
public class DefResponseCallBackImpl<T> implements ResponseCallBack<T> {

    private final static String NETWORK_TIPS;

    static {
        NETWORK_TIPS = ResUtil.getString(R.string.main_net_error);
    }

    @Override
    public void onBodyNull(Call<T> call, Response<T> response) {
        Log.e("xx", "def onBodyNull");
    }

    @Override
    public void onSuccess(Call<T> call, Response<T> response) {
        Log.e("xx", "def onSuccess");
    }

    @Override
    public void onFailure(Call<T> call, Response<T> response, int status) {
        Log.e("xx", "def onFailure status");
    }

    @Override
    public void onServerError(Call<T> call, Response<T> response) {
        Log.e("xx", "def onServerError");
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        Log.e("xx", "def onResponse");
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        Log.e("xx", "def onFailure");
    }
}
