package com.example.airport.ui.base.composables


import androidx.compose.ui.graphics.vector.ImageVector

data class BaseTopAppBarState(
    val title: String,
    val iconUpAction: ImageVector,
    val upAction: () -> Unit,
    val actions: List<Action>

)

data class Action(
    val name: String,
    val icon: ImageVector?,
    val contentDescription: String?,
    val onClick: () -> Unit,
    val isVisible: Boolean = true
)

/**sealed class Action(
val name: String,
val onClick: () -> Unit,
val isVisible: Boolean = true
) {

class ActionVector(
name: String,
val icon: ImageVector,
onClick: () -> Unit,
isVisible: Boolean = true
) : Action(name,  onClick, isVisible)
class ActionPainter(
name: String,
val icon: Painter,
onClick: () -> Unit,
isVisible: Boolean = true
) : Action(name,  onClick, isVisible)
}**/
