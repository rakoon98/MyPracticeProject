package com.example.ui

import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.test.R
import com.example.test.base.BaseActivity
import com.example.test.data.network.ApiState
import com.example.test.databinding.ActivityKtorBinding
import com.example.test.viewmodel.KtorViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class KtorActivity(override val layoutResourceId: Int = R.layout.activity_ktor)
    : BaseActivity<ActivityKtorBinding>() {

    private val viewModel by viewModels<KtorViewModel> { defaultViewModelProviderFactory }

    override fun aboutBinding() {
        viewDataBinding.lifecycleOwner = this
        viewDataBinding.switchBtn.setOnClickListener { viewModel.getSwitchGames() }
    }

    override fun observeData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.getSwitchGamesStateFlow.buffer().collectLatest { state ->
                    when ( state ) {
                        is ApiState.Success -> {
                            Log.d("데이터체크","getSwitchGames in activity => ${state.data}")
                            viewDataBinding.result.text = "${state.data}"
                            viewDataBinding.progress.visibility = View.GONE
                        }
                        is ApiState.Error -> {
                            Log.d("데이터체크","getSwitchGames in activity => ${state.exception}")
                            viewDataBinding.progress.visibility = View.GONE
                        }
                        is ApiState.Loading -> {
                            viewDataBinding.progress.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }
}