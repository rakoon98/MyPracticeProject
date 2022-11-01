package com.example.test.ui

import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.example.test.R
import com.example.test.base.BaseFragment
import com.example.test.data.model.rv.RvModel
import com.example.test.data.model.rv.ViewType
import com.example.test.databinding.FragmentRecyclerviewBinding
import com.example.test.ui.adapter.RvAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecyclerViewFragment(override val layoutResourceId: Int = R.layout.fragment_recyclerview)
    : BaseFragment<FragmentRecyclerviewBinding>() {

    val list : List<RvModel> = mutableListOf<RvModel>(
        RvModel(
            0,
            R.layout.holder_rv,
            ViewType.HOLDER_RV,
            { clickAction(0) },
            {}
        ),
        RvModel(
            1,
            R.layout.holder_rv,
            ViewType.HOLDER_RV,
            { clickAction(1) },
            {}
        ),
        RvModel(
            2,
            R.layout.holder_rv,
            ViewType.HOLDER_RV,
            { clickAction(2) },
            {}
        ),
        RvModel(
            3,
            R.layout.holder_rv,
            ViewType.HOLDER_RV,
            onClickAction = { clickAction(3) },
            {}
        )
    )

    private fun clickAction ( position : Long ) {
        Log.d("데이터체크","submitList click action")
        list.toMutableList().map {
            it.apply { isClicked = ( position == id ) }
        }.also {
            Log.d("데이터체크","submitList click submit1")
            adapt.submitList(it) {
                Log.d("데이터체크","submitList click submit2")
            }
        }
    }

    lateinit var adapt : RvAdapter

    override fun onBindView() {
        with ( binding ) {
            lifecycleOwner = viewLifecycleOwner
            rv.apply {
                adapter = RvAdapter().also {
                    adapt = it
                    it.submitList(list)
                } // RvModel

            }
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {}

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return true
    }
}