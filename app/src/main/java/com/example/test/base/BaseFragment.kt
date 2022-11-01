package com.example.test.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import com.example.test.R

abstract class BaseFragment<T : ViewDataBinding> : Fragment(),
    LifecycleOwner, MenuProvider {

    abstract val layoutResourceId: Int

    private var _binding: T? = null
    protected val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<T>(inflater, layoutResourceId, container, false).also {
            _binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.onBackPressedDispatcher?.addCallback(backPressedDispatcher)
        onBindView()
    }

    abstract fun onBindView()

//    private fun bindView() {
//        with(binding) {
//            lifecycleOwner = viewLifecycleOwner
//        }
//    }

//    private fun setToolbar() =
//        (activity as? AppCompatActivity)?.let {
//            setActionBar(it.supportActionBar)
//            setHomeAsUpIndicator(R.drawable.ic_back_arrow)
//            setActionBarHide()
//        }

    /**
     * Fragment Back Button 동작을 Activity 전달하기 위한 Dispatcher
     */
    private val backPressedDispatcher = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            onBackPressed()
        }
    }

    /**
     * Fragment Back Button 기본 동작.
     * 이전화면이 있다면 이전화면으로 이동
     * Activity 의 마지막 화면이라면 액티비티 종료
     * 별도로 BackButton 동작이 필요 시 Override 이용 재정의
     */
    protected fun onBackPressed() {
        try {
            if (parentFragmentManager.backStackEntryCount == 0) {
                activity?.finish()
            } else {
                findNavController().navigateUp()
            }
        } catch (e: Exception) {
//            Timber.w(e)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}