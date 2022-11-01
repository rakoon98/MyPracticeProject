package com.example.test.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.example.test.data.model.rv.HurayHolder
import com.example.test.data.model.rv.IvModel
import com.example.test.data.model.rv.RvDiff

class RvAdapter : ListAdapter<IvModel, HurayHolder>(RvDiff()) {
    private val viewTypeLayoutIdMap: MutableMap<Int, Int> = mutableMapOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HurayHolder {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(parent.context), viewTypeLayoutIdMap[viewType] ?: 0, parent, false)
        return HurayHolder(binding)
    }

    override fun onBindViewHolder(holder: HurayHolder, position: Int) {
        holder.bind(currentList[position])
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        if (!viewTypeLayoutIdMap.containsKey(item.viewType.typeInt)) {
            viewTypeLayoutIdMap[item.viewType.typeInt] = item.layoutId
        }

        return item.viewType.typeInt
    }
}