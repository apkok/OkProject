package com.jssonok.project.common.ui.component;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jssonok.project.common.R;

public class OkBaseActivity extends AppCompatActivity implements OkBaseActionInterface{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.activity_base_layout);
    }
}
