/*
 * Copyright 2016 Yan Zhenjie
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
package com.lgc.mymode.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.lgc.mymode.R;
import com.lgc.mymode.util.listener.OnItemClickListener;
import com.lgc.mymode.util.listener.OnItemLongClickListener;

import java.util.ArrayList;
import java.util.List;


/**
 * <pre>
 *     author : feijin_lgc
 *     e-mail : 595184932@qq.com
 *     time   : 2017/10/13 14:14
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class DepartmentAdapter extends BaseAdapter<DepartmentAdapter.ViewHolder> {

    private List<String> mDataList = new ArrayList<>();
    private Context mContext;


    private boolean isEdu=true;

    public DepartmentAdapter(Context context) {
        super(context);
        mContext = context;

    }

    public void setIsEdu(boolean _isEdu) {
        this.isEdu = _isEdu;
    }

    /**
     * 触发清空
     */
    public void clear() {
        int count = mDataList.size();
        if (count > 0) {
            mDataList.clear();
        }
        notifyDataSetChanged();
    }

    public void notifyDataSetChangedChat(List<String> dataList) {
        if (dataList != null) {
            mDataList = dataList;
            notifyDataSetChanged();
        }
    }

    public void notifyDataSetChanged(List<String> dataList) {

        if (dataList != null) {
            mDataList.addAll(dataList);
            notifyDataSetChanged();
        }
    }

    public List<String> getAllData() {
        if (mDataList != null) {
            return mDataList;
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ResultViewHolder(getInflater().inflate(R.layout.layout_list_view_item, parent, false));
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(holder.itemView, position);
                }
            });

        }

        if (mOnItemLongClickListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemLongClickListener.onItemLongClick(holder.itemView, position);
                    return true;
                }
            });
        }
        holder.setData(mDataList.get(position), position);
    }

    static class ViewHolder<T> extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }

        public void setData(T list, int position) {

        }
    }

    /**
     *
     */
    public class ResultViewHolder extends ViewHolder<String> {


        private TextView mTextView;
        private ImageView mImageView;
        public ResultViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.imageView_list_item);
            mTextView = (TextView) itemView.findViewById(R.id.textView_list_item);
        }

        public void setData(final String person, int position) {

            mTextView.setText(String.valueOf(position));
            Log.e("xx", "onBindViewHolder ....");
//            Glide.with(mContext).asBitmap().load(mDataList.get(position)).into(mImageView);

        }
    }

    /**
     * 获取res String
     */
    private String getString(int res) {
        return mContext.getString(res);
    }
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mOnItemClickListener = itemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener itemLongClickListener) {
        this.mOnItemLongClickListener = itemLongClickListener;
    }
}
