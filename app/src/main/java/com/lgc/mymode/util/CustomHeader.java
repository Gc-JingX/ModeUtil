package com.lgc.mymode.util;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.lgc.mymode.R;

import me.dkzwm.widget.srl.SmoothRefreshLayout;
import me.dkzwm.widget.srl.extra.IRefreshView;
import me.dkzwm.widget.srl.indicator.IIndicator;

/**
 * <pre>
 *     author : feijin_lgc
 *     e-mail : 595184932@qq.com
 *     time   : 2017/10/31 10:13
 *     desc   :    头部
 *     version: 1.0
 * </pre>
 */
public class CustomHeader extends FrameLayout implements IRefreshView {
    private TextView mTextViewTitle;
    private boolean mStartedCounter;
    private int mCount = 0;

    public CustomHeader(@NonNull Context context) {
        this(context, null);
    }

    public CustomHeader(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomHeader(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View header = LayoutInflater.from(context).inflate(R.layout.layout_custom_qq_activity_header, this);
        mTextViewTitle = (TextView) header.findViewById(R.id.textView_qq_activity_header_title);
    }


    @Override
    public int getType() {
        return TYPE_HEADER;
    }

    @Override
    public int getStyle() {
        return STYLE_PIN;
    }

    @Override
    public int getCustomHeight() {
        return 0;
    }

    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @Override
    public void onFingerUp(SmoothRefreshLayout layout, IIndicator indicator) {
        final int mOffsetToRefresh = indicator.getOffsetToRefresh();
        final int currentPos = indicator.getCurrentPos();

        if (currentPos > mOffsetToRefresh) {
            mCount++;
            mTextViewTitle.setText("x" + mCount);
        }
    }

    @Override
    public void onReset(SmoothRefreshLayout layout) {
        mTextViewTitle.setText(R.string.brush);
        mStartedCounter = false;
        mCount = 0;
    }

    @Override
    public void onRefreshPrepare(SmoothRefreshLayout layout) {

    }

    @Override
    public void onRefreshBegin(SmoothRefreshLayout layout, IIndicator indicator) {

    }

    @Override
    public void onRefreshComplete(SmoothRefreshLayout layout, boolean isSuccessful) {

    }

    @Override
    public void onRefreshPositionChanged(SmoothRefreshLayout layout, byte status, IIndicator indicator) {
        final int mOffsetToRefresh = indicator.getOffsetToRefresh();
        final int currentPos = indicator.getCurrentPos();

        if (currentPos > mOffsetToRefresh && !mStartedCounter) {
            if (indicator.hasTouched() && status == SmoothRefreshLayout.SR_STATUS_PREPARE) {
                mStartedCounter = true;
                mTextViewTitle.setText("x" + mCount);
            }
        }
    }

    @Override
    public void onPureScrollPositionChanged(SmoothRefreshLayout layout, byte status, IIndicator indicator) {

    }
}
