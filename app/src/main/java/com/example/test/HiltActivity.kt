package com.example.test

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.test.databinding.ActivityHiltBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HiltActivity : AppCompatActivity() {

    lateinit var binding : ActivityHiltBinding

    val viewModel by viewModels<FlowViewModel> { defaultViewModelProviderFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityHiltBinding>(this, R.layout.activity_hilt).apply {
            vm = viewModel
            btn.setOnClickListener {
                viewModel.requestApiState(contents.text.toString())
            }
            switchBtn.setOnClickListener {
                viewModel.getSwitchGames()
            }


//            android:text="@{ vm.getApiStateFlow == ApiState.Success ? vm.getApiStateFlow : `` }"
//            android:visibility="@{vm.getApiStateFlow == ApiState.Loading ? View.VISIBLE : View.GONE, default = gone}"

            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.RESUMED) {
                    launch {
                        viewModel.getApiStateFlow.buffer().collectLatest { state ->
                            when ( state ) {
                                is ApiState.Success -> {
                                    result.text = state.data ?: ""
                                    progress.visibility = View.GONE
                                }
                                is ApiState.Error -> {
                                    progress.visibility = View.GONE
                                }
                                is ApiState.Loading -> {
                                    progress.visibility = View.VISIBLE
                                }
                            }
                        }
                    }
                    launch {

//                        viewModel.getSwitchGamesStateFlow
//                            .flowWithLifecycle(lifecycle, Lifecycle.State.RESUMED)
//                            .onEach { state ->
//                                Log.d("데이터체크","스위치게임즈 : state ${state}")
//                                when ( state ) {
//                                    is ApiState.Success -> {
//                                        result.text = "${state.data?.size} 개"
//                                        progress.visibility = View.GONE
//                                    }
//                                    is ApiState.Error -> {
//                                        progress.visibility = View.GONE
//                                    }
//                                    is ApiState.Loading -> {
//                                        progress.visibility = View.VISIBLE
//                                    }
//                                }
//                            }
                    }
                }
            }

            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.RESUMED) {
                    viewModel.getSwitchGamesStateFlow.buffer().collectLatest { state ->
                        when ( state ) {
                            is ApiState.Success -> {
                                result.text = "${state.data?.size} 개"
                                progress.visibility = View.GONE
                            }
                            is ApiState.Error -> {
                                progress.visibility = View.GONE
                            }
                            is ApiState.Loading -> {
                                progress.visibility = View.VISIBLE
                            }
                        }
                    }
                }
            }



//            viewModel.getApiStateFlow
//                .flowWithLifecycle(lifecycle, Lifecycle.State.RESUMED)
//                .onEach { state ->
//                    when ( state ) {
//                        is ApiState.Success -> {
//                            result.text = state.data ?: ""
//                            progress.visibility = View.GONE
//                        }
//                        is ApiState.Error -> {
//                            progress.visibility = View.GONE
//                        }
//                        is ApiState.Loading -> {
//                            progress.visibility = View.VISIBLE
//                        }
//                    }
//                }
//                .launchIn(lifecycleScope)
//
//            viewModel.getSwitchGamesStateFlow
//                .flowWithLifecycle(lifecycle, Lifecycle.State.RESUMED)
//                .onEach { state ->
//                    Log.d("데이터체크","스위치게임즈 : state ${state}")
//                    when ( state ) {
//                        is ApiState.Success -> {
//                            result.text = "${state.data?.size} 개"
//                            progress.visibility = View.GONE
//                        }
//                        is ApiState.Error -> {
//                            progress.visibility = View.GONE
//                        }
//                        is ApiState.Loading -> {
//                            progress.visibility = View.VISIBLE
//                        }
//                    }
//                }


        }
    }

}