package com.example.test.data.model.rv

import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.DiffUtil

class RvDiff : DiffUtil.ItemCallback<IvModel>() {
    override fun areItemsTheSame(oldItem: IvModel, newItem: IvModel): Boolean {
        return newItem.areItemTheSame(oldItem)
    }

    override fun areContentsTheSame(oldItem: IvModel, newItem: IvModel): Boolean {
        return newItem.areContentsSame(oldItem)
    }
}

val diff = object : DiffUtil.ItemCallback<RvModel>() {
    override fun areItemsTheSame(oldItem: RvModel, newItem: RvModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: RvModel, newItem: RvModel): Boolean {
        return oldItem.id == newItem.id &&
                oldItem.isClicked == newItem.isClicked
    }
}