package com.lgc.lgcutillibrary.util.data;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lgc.lgcutillibrary.util.config.MyApplication;
import com.lgc.lgcutillibrary.util.cusview.CircleTransform;

/**
 * created by feijin_lgc.
 * created date 2017/10/20 上午10:55.
 * desc   图片工具类
 * version 1.0
 */
public class GlideUtil {

    /**
     * 带圆角的图片
     *
     * @param avator
     * @param main_user_iv
     */
    public static void setImageCircle(String avator, ImageView main_user_iv, int errorPic) {
        if (avator != null) {
            if (avator.length() > 0) {
                //圆角处理
                Glide.with(MyApplication.getInstance())
                        .load(avator)
                        .error(errorPic)
                        .centerCrop()
                        .transform(new CircleTransform(MyApplication.getInstance()))
                        .into(main_user_iv);
            } else {
                main_user_iv.setImageResource(errorPic);
            }
        } else {
            main_user_iv.setImageResource(errorPic);
        }
    }

    public static void setImageCircle(String avator, ImageView main_user_iv) {
        if (avator.length() > 0) {
            //圆角处理
            Glide.with(MyApplication.getInstance())
                    .load(avator)
                    .centerCrop()
                    .transform(new CircleTransform(MyApplication.getInstance()))
                    .into(main_user_iv);
        }
    }

    /**
     * 不带圆角图片
     */
    public static void setImage(String avator, ImageView main_user_iv, int errorPic) {
        if (avator != null) {
            if (avator.length() > 0) {
                //圆角处理
                Glide.with(MyApplication.getInstance())
                        .load(avator)
                        .error(errorPic)
                        .centerCrop()
                        .into(main_user_iv);
            } else {
                main_user_iv.setImageResource(errorPic);
            }
        } else {
            main_user_iv.setImageResource(errorPic);
        }
    }

    public static void setImage(String avator, ImageView main_user_iv) {
        if (avator.length() > 0) {
            //圆角处理
            Glide.with(MyApplication.getInstance())
                    .load(avator)
                    .centerCrop()
                    .into(main_user_iv);
        }
    }
}
