package com.jssonok.project.common.tab;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * 借助OkTabViewAdapter来实现对Fragment的管理
 * 1、将Fragment的操作内聚
 * 2、提供通用的一些API
 */
public class OkFragmentTabView extends FrameLayout {

    private OkTabViewAdapter mAdapter;
    private int currentPosition;

    public OkFragmentTabView(@NonNull Context context) {
        super(context);
    }

    public OkFragmentTabView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public OkFragmentTabView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public OkTabViewAdapter getAdapter() {
        return mAdapter;
    }

    public void setAdapter(OkTabViewAdapter adapter) {
        if(this.mAdapter != null || adapter == null) {
            return;
        }
        this.mAdapter = adapter;
        currentPosition = -1;
    }

    public void setCurrentItem(int position) {
        if(position < 0 || position >= mAdapter.getCount()) {
            return;
        }
        if(currentPosition != position) {
            currentPosition = position;
            mAdapter.instantiateItem(this, position);
        }
    }

    /**
     * 获取当前位置
     * @return
     */
    public int getCurrentItem() {
        return currentPosition;
    }

    /**
     * 获取当前Fragment
     * @return
     */
    public Fragment getCurrentFragment() {
        if(this.mAdapter == null) {
            throw new IllegalArgumentException("please call setAdapter first.");
        }
        return mAdapter.getCurrentFragment();
    }
}
