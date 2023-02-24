package com.jssonok.project.logic;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.FragmentManager;

import com.jssonok.project.R;
import com.jssonok.project.common.tab.OkFragmentTabView;
import com.jssonok.project.common.tab.OkTabViewAdapter;
import com.jssonok.project.fragment.CategoryFragment;
import com.jssonok.project.fragment.FavoriteFragment;
import com.jssonok.project.fragment.HomePageFragment;
import com.jssonok.project.fragment.ProfileFragment;
import com.jssonok.project.fragment.RecommendFragment;
import com.jssonok.ui.tab.bottom.OkTabBottomInfo;
import com.jssonok.ui.tab.bottom.OkTabBottomLayout;
import com.jssonok.ui.tab.common.IOkTabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 将MainActivity的一些逻辑内聚在这，让MainActivity更加清爽
 */
public class MainActivityLogic {
    private OkFragmentTabView fragmentTabView;
    private OkTabBottomLayout okTabBottomLayout;
    private List<OkTabBottomInfo<?>> infoList;
    private ActivityProvider activityProvider;
    private int currentItemIndex;
    private final static String SAVED_CURRENT_ID = "SAVED_CURRENT_ID";

    public MainActivityLogic(ActivityProvider activityProvider, Bundle savedInstanceState) {
        this.activityProvider = activityProvider;
        // fix：开发人员选项开启不保留活动导致的Fragment重叠问题
        if (savedInstanceState != null) {
            currentItemIndex = savedInstanceState.getInt(SAVED_CURRENT_ID);
        }
        initTabBottom();
    }

    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(SAVED_CURRENT_ID, currentItemIndex);
    }

    public OkFragmentTabView getFragmentTabView() {
        return fragmentTabView;
    }

    public OkTabBottomLayout getOkTabBottomLayout() {
        return okTabBottomLayout;
    }

    public List<OkTabBottomInfo<?>> getInfoList() {
        return infoList;
    }

    /**
     * 初始化底部导航栏
     */
    private void initTabBottom() {
        okTabBottomLayout = activityProvider.findViewById(R.id.tab_bottom_layout);
        okTabBottomLayout.setTabBottomAlpha(0.85f);
        infoList = new ArrayList<>();
        int defaultColor = activityProvider.getResources().getColor(R.color.tabBottomDefaultColor);
        int tintColor = activityProvider.getResources().getColor(R.color.tabBottomTintColor);

        OkTabBottomInfo homeInfo = new OkTabBottomInfo<Integer>(
                "首页",
                "fonts/iconfont.ttf",
                activityProvider.getString(R.string.if_home),
                null,
                defaultColor,
                tintColor
        );
        homeInfo.fragment = HomePageFragment.class;

        OkTabBottomInfo favoriteInfo = new OkTabBottomInfo<Integer>(
                "收藏",
                "fonts/iconfont.ttf",
                activityProvider.getString(R.string.if_favorite),
                null,
                defaultColor,
                tintColor
        );
        favoriteInfo.fragment = FavoriteFragment.class;

        OkTabBottomInfo categoryInfo = new OkTabBottomInfo<Integer>(
                "分类",
                "fonts/iconfont.ttf",
                activityProvider.getString(R.string.if_category),
                null,
                defaultColor,
                tintColor
        );
        categoryInfo.fragment = CategoryFragment.class;

        OkTabBottomInfo recommendInfo = new OkTabBottomInfo<Integer>(
                "推荐",
                "fonts/iconfont.ttf",
                activityProvider.getString(R.string.if_recommend),
                null,
                defaultColor,
                tintColor
        );
        recommendInfo.fragment = RecommendFragment.class;

        OkTabBottomInfo profileInfo = new OkTabBottomInfo<Integer>(
                "我的",
                "fonts/iconfont.ttf",
                activityProvider.getString(R.string.if_profile),
                null,
                defaultColor,
                tintColor
        );
        profileInfo.fragment = ProfileFragment.class;

        infoList.add(homeInfo);
        infoList.add(favoriteInfo);
        infoList.add(categoryInfo);
        infoList.add(recommendInfo);
        infoList.add(profileInfo);

        okTabBottomLayout.inflateInfo(infoList);
        initFragmentTabView();
        okTabBottomLayout.addTabSelectedChangeListener(new IOkTabLayout.OnTabSelectedListener<OkTabBottomInfo<?>>() {
            @Override
            public void onTabSelectedChange(int index, @Nullable OkTabBottomInfo<?> prevInfo, @NonNull OkTabBottomInfo<?> nextInfo) {
                fragmentTabView.setCurrentItem(index);
                MainActivityLogic.this.currentItemIndex = index;
            }
        });
        okTabBottomLayout.defaultSelected(infoList.get(currentItemIndex));
    }

    /**
     * 初始化FragmentrTabView
     */
    private void initFragmentTabView() {
        OkTabViewAdapter tabViewAdapter = new OkTabViewAdapter(activityProvider.getSupportFragmentManager(), infoList);
        fragmentTabView = activityProvider.findViewById(R.id.fragment_tab_view);
        fragmentTabView.setAdapter(tabViewAdapter);
    }

    public interface ActivityProvider {
        <T extends View> T findViewById(@IdRes int id);

        Resources getResources();

        FragmentManager getSupportFragmentManager();

        String getString(@StringRes int resId);
    }
}
