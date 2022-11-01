package com.example.test.data.model.rv

import androidx.annotation.LayoutRes

/**
 *  휴레이에서 쓰고있는 안드팀 rv 관련구조
 */
interface IvModel {

    /** 해당 모델에 1:1로 매칭되는 레이아웃 */
    @get:LayoutRes
    val layoutId : Int

    val viewType : ViewType

    val onClickAction : (Any) -> Unit
    val onLongClickAction : (Any) -> Unit

    fun areItemTheSame(other : IvModel) : Boolean
    fun areContentsSame(other : IvModel) : Boolean
        = ( this == other )
}

interface IvModelId : IvModel {
    var id : Long

//    override fun areItemTheSame(other: IvModel): Boolean
//        = ( this.id == other.id )
}

// rv 관련 viewType enum class
enum class ViewType(val typeInt : Int) {
    HOLDER_RV(0)
}