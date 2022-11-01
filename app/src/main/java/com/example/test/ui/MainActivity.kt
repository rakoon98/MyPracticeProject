package com.example.test.ui

import com.example.test.R
import com.example.test.base.BaseActivity
import com.example.test.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 *  메인이 되는 액티비티
 *  여기서 분기처리가 된다.
 */
@AndroidEntryPoint
class MainActivity(override val layoutResourceId: Int = R.layout.activity_main)
    : BaseActivity<ActivityMainBinding>() {

    override fun onBindView() {

    }

}