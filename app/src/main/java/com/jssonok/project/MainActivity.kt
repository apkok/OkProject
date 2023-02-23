package com.jssonok.project

import android.os.Bundle
import com.jssonok.project.common.ui.component.OkBaseActivity

class MainActivity : OkBaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}