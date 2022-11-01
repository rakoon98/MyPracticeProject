package com.example.test.data.model.rv

import android.graphics.Color
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.example.test.databinding.HolderRvBinding

class HurayHolder constructor(
    private val binding : ViewDataBinding
) : RecyclerView.ViewHolder(binding.root){

    fun bind(item : IvModel) {
        if ( item is RvModel ) {
            when ( item.isClicked ) {
                true  -> {
                    Color.BLUE
                }
                false -> {
                    Color.GREEN
                }
            }.also {
                (binding as HolderRvBinding).backG.setBackgroundColor(it)
                (binding as HolderRvBinding).root.setOnClickListener { item.onClickAction.invoke(item) }
            }
        }
    }

}