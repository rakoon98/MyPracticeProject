package com.example.test.data.model.rv

data class RvModel(
    val id : Long,
    override val layoutId: Int,
    override val viewType: ViewType,
    override val onClickAction: (Any) -> Unit,
    override val onLongClickAction: (Any) -> Unit,
    var isClicked : Boolean = false,
) : IvModel {
    override fun areItemTheSame(other: IvModel): Boolean {
        return (other is RvModel) && (id == other.id) && (isClicked == other.isClicked)
    }
}
