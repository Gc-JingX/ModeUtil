package com.lgc.mymode;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.lgc.lgcutillibrary.util.dialog.TipDialog;
import com.lgc.lgcutillibrary.util.myactivity.MyActivity;

/**
 * <pre>
 *     author : feijin_lgc
 *     e-mail : 595184932@qq.com
 *     time   : 2017/10/28 9:32
 *     desc   :   首页
 *     version: 1.0
 * </pre>
 */
public class MainActivity extends MyActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.test_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               TipDialog tipDialog = null;
                int position = 1;
                switch (position) {
                    case 0:
                        tipDialog = new TipDialog.Builder(MainActivity.this)
                                .setIconType(TipDialog.Builder.ICON_TYPE_LOADING)
                                .setTipWord("正在加载")
                                .create();
                        break;
                    case 1:
                        tipDialog = new TipDialog.Builder(MainActivity.this)
                                .setIconType(TipDialog.Builder.ICON_TYPE_SUCCESS)
                                .setTipWord("发送成功")
                                .create();
                        break;
                    case 2:
                        tipDialog = new TipDialog.Builder(MainActivity.this)
                                .setIconType(TipDialog.Builder.ICON_TYPE_FAIL)
                                .setTipWord("发送失败")
                                .create();
                        break;
                    case 3:
                        tipDialog = new TipDialog.Builder(MainActivity.this)
                                .setIconType(TipDialog.Builder.ICON_TYPE_INFO)
                                .setTipWord("请勿重复操作")
                                .create();
                        break;
                    case 4:
                        tipDialog = new TipDialog.Builder(MainActivity.this)
                                .setIconType(TipDialog.Builder.ICON_TYPE_SUCCESS)
                                .create();
                        break;
                    case 5:
                        tipDialog = new TipDialog.Builder(MainActivity.this)
                                .setTipWord("请勿重复操作")
                                .create();
                        break;
                    case 6:
//                tipDialog = new TipDialog.CustomBuilder(this)
//                        .setContent(R.layout.tipdialog_custom)
//                        .create();
                        break;
                    default:
                        tipDialog = new TipDialog.Builder(MainActivity.this)
                                .setIconType(TipDialog.Builder.ICON_TYPE_LOADING)
                                .setTipWord("正在加载")
                                .create();
                }
                tipDialog.show();
                final TipDialog finalTipDialog = tipDialog;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finalTipDialog.dismiss();
                    }
                },2000);
            }
        });
    }
}
