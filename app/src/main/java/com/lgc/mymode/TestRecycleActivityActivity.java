package com.lgc.mymode;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.lgc.lgcutillibrary.util.L;
import com.lgc.mymode.adapter.DepartmentAdapter;
import com.lgc.mymode.util.listener.OnItemClickListener;
import com.lgc.mymode.util.CustomHeader;
import com.lgc.mymode.util.DataUtil;

import java.util.List;

import me.dkzwm.widget.srl.RefreshingListenerAdapter;
import me.dkzwm.widget.srl.SmoothRefreshLayout;
import me.dkzwm.widget.srl.extra.footer.ClassicFooter;
import me.dkzwm.widget.srl.extra.header.ClassicHeader;
import me.dkzwm.widget.srl.indicator.IIndicator;

/**
 * <pre>
 *     author : feijin_lgc
 *     e-mail : 595184932@qq.com
 *     time   : 2017/10/31 10:16
 *     desc   :    阻尼效果上下拉刷新
 *     version: 1.0
 * </pre>
 */
public class TestRecycleActivityActivity extends AppCompatActivity {
    private SmoothRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private DepartmentAdapter mAdapter;
    private Handler mHandler = new Handler();
    private int mCount = 0;
    private ClassicHeader mClassicHeader;
    private ClassicFooter mClassicFooter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_qq_activity_style);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_test_qq_activity_style);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new DepartmentAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mRefreshLayout = (SmoothRefreshLayout) findViewById(R.id.smoothRefreshLayout_test_qq_activity_style);
        mClassicHeader = new ClassicHeader(this);
        mClassicHeader.setLastUpdateTimeKey("header_last_update_time");
        mClassicFooter = new ClassicFooter(this);
        mClassicFooter.setLastUpdateTimeKey("footer_last_update_time");
        mRefreshLayout.setHeaderView(mClassicHeader);
        mRefreshLayout.setFooterView(mClassicFooter);
        mRefreshLayout.setEnableKeepRefreshView(true);
        mRefreshLayout.setDisableLoadMore(false);
        mRefreshLayout.setOnRefreshListener(new RefreshingListenerAdapter() {
            @Override
            public void onRefreshBegin(final boolean isRefresh) {
                /**
                 * isRefresh 为True 则 代表下拉  false 为上拉
                 */
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 模拟加载完成数据
                        if (isRefresh) {
                            mCount = 0;
                            List<String> list = DataUtil.createList(mCount, 20);
                            mCount += 20;
                            mAdapter.notifyDataSetChangedChat(list);
                        } else {
                            List<String> list = DataUtil.createList(mCount, 20);
                            mCount += 20;
                            mAdapter.notifyDataSetChanged(list);
                        }
                        mRefreshLayout.refreshComplete();
                    }
                }, 2000);
            }

        });
        //UI位置变化
        mRefreshLayout.addOnUIPositionChangedListener(new SmoothRefreshLayout.OnUIPositionChangedListener() {
            @Override
            public void onChanged(byte status, IIndicator indicator) {
                if (mRefreshLayout.isInStartPosition()) {
                } else {
                }
            }
        });
        mRefreshLayout.autoRefresh(false);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                L.e("xx", "点击位置 " + position);
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }


}
