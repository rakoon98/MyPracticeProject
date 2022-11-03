package com.example.test.ui

import android.content.Intent
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.test.R
import com.example.test.base.BaseFragment
import com.example.test.compose.ui.ComposeActivity
import com.example.test.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main){

    override fun onBindView() {
        with ( binding ) {
            lifecycleOwner = viewLifecycleOwner
            recyclerViewBtn.setOnClickListener { findNavController().navigate(MainFragmentDirections.actionMainToRv()) }
            notiBtn.setOnClickListener { startActivity(Intent(requireContext(), Notification13Activity::class.java)) }
            composeBtn.setOnClickListener { startActivity(Intent(requireContext(), ComposeActivity::class.java)) }
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {

    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return true
    }

}