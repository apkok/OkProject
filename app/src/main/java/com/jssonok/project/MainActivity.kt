package com.jssonok.project

import android.os.Bundle
import com.jssonok.project.common.ui.component.OkBaseActivity
import com.jssonok.project.logic.MainActivityLogic
import com.jssonok.project.logic.MainActivityLogic.ActivityProvider

class MainActivity : OkBaseActivity(), ActivityProvider {

    var activityLogic : MainActivityLogic ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         activityLogic = MainActivityLogic(this, savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        activityLogic?.onSaveInstanceState(outState)
    }
}