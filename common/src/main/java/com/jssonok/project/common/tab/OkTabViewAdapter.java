package com.jssonok.project.common.tab;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.jssonok.ui.tab.bottom.OkTabBottomInfo;

import java.util.List;

/**
 * 仿照ViewPageAdapter，配合OkTabView完成Fragment的管理
 */
public class OkTabViewAdapter {
    // 底部导航栏数据集合
    private List<OkTabBottomInfo<?>> mInfoList;
    // 当前选中的导航栏对应的Fragment实例
    private Fragment mCurFragment;
    // Fragment管理器
    private FragmentManager mFragmentManager;

    public OkTabViewAdapter(FragmentManager fragmentManager, List<OkTabBottomInfo<?>> infoList) {
        this.mFragmentManager = fragmentManager;
        this.mInfoList = infoList;
    }

    /**
     * 根据选中的TabView传递过来的position来实例化以及显示Fragment（实例化以及显示指定位置的fragment）
     * @param container
     * @param position
     */
    public void instantiateItem(View container, int position) {
        FragmentTransaction mCurTransaction = mFragmentManager.beginTransaction();
        if(mCurFragment != null) {
            // 隐藏当前fragment
            mCurTransaction.hide(mCurFragment);
        }
        // 生成fragment的Tag
        String name = container.getId() + "" + position;
        // 根据Tag查询Fragment
        Fragment fragment = mFragmentManager.findFragmentByTag(name);
        // 如果查询到了对应的Fragment，则说明在FragmentManager里面已添加
        if(fragment != null) {
            mCurTransaction.show(fragment);
        }else {
            // 创建Fragment
            fragment = getItem(position);
            // 判断fragment是否已经添加至FragmentManager中
            if (!fragment.isAdded()) {
                mCurTransaction.add(container.getId(), fragment, name);
            }
        }

        mCurFragment = fragment;
        mCurTransaction.commitAllowingStateLoss();
    }

    public Fragment getCurrentFragment() {
        return mCurFragment;
    }
    public Fragment getItem(int position) {
        try {
            // 创建对应的Fragment
            mInfoList.get(position).fragment.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public int getCount() {
        return mInfoList != null ? mInfoList.size() : 0;
    }
}
