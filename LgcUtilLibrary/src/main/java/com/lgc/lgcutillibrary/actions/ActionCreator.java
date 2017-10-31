package com.lgc.lgcutillibrary.actions;


import com.lgc.lgcutillibrary.R;
import com.lgc.lgcutillibrary.dispater.Dispatcher;
import com.lgc.lgcutillibrary.util.ResUtil;

/**
 * <pre>
 *     author : feijin_lgc
 *     e-mail : 595184932@qq.com
 *     time   : 2017/10/31 10:21
 *     desc   :   事件创建者
 *     version: 1.0
 * </pre>
 */

public class ActionCreator {
    public final static String NETWORK_TIPS;

    static {
        NETWORK_TIPS = ResUtil.getString(R.string.main_net_error);
    }

    public static int netError = -1;

    private static ActionCreator actionCreator;
    private static Dispatcher dispatcher;


    public static ActionCreator getInstance(Dispatcher _dispatcher) {
        if (actionCreator == null) {
            actionCreator = new ActionCreator(_dispatcher);
        }
        dispatcher = _dispatcher;
        return actionCreator;
    }

    public ActionCreator(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }


    /**
     * 发送事件
     *
     * @param type
     * @param data
     */
    public void sendEvent(String type, int errorType, Object... data) {
        dispatcher.dispatch(type, errorType, data);
    }


    public void updateChange() {
//        dispatcher.dispatch(LoginAction.STUDENT_INPUT_CHANGE, LoginAction.STUDENT_INPUT_CHANGE);
    }

    public Dispatcher getDispatcher() {
        return dispatcher;
    }

}
