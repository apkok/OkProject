package com.jssonok.project.logic;

import android.content.res.Resources;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.StringRes;
import androidx.fragment.app.FragmentManager;

import com.jssonok.project.R;
import com.jssonok.project.common.tab.OkFragmentTabView;
import com.jssonok.ui.tab.bottom.OkTabBottomInfo;
import com.jssonok.ui.tab.bottom.OkTabBottomLayout;

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

    public MainActivityLogic(ActivityProvider activityProvider) {
        this.activityProvider = activityProvider;
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

    }

    public interface ActivityProvider {
        <T extends View> T findViewById(@IdRes int id);

        Resources getResources();

        FragmentManager getSupportFragmentManager();

        String getString(@StringRes int resId);
    }
}
