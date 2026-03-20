package com.example.airport.ui.base.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


/**
 * Base top app bar
 * Función que dado un estado de una top app, contruye los elementos
 * de la misma
 *
 * @param appBarState
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseTopAppBar(appBarState: BaseTopAppBarState) {

    // Obtener el esquema de colores según el tema actual (oscuro o claro)
    val colors = MaterialTheme.colorScheme
    //1. Lógica de la vista

    // Filtrar acciones visibles y ocultas
    val visibleActions = appBarState.actions.filter { it.isVisible }
    val hiddenActions = appBarState.actions.filter { !it.isVisible }

    // Estado para controlar la apertura del menú desplegable
    var menuExpanded by remember { mutableStateOf(false) }

    TopAppBar(
        // Título de la app bar
        title = {
            //Se aplica fillMaxWith para que ocupe todo el espacio y el título se centre
            Text(text = appBarState.title, Modifier.fillMaxWidth())
        },
        // Icono de navegación (abrir el drawer o bien <- )
        navigationIcon = {
            IconButton(onClick = { appBarState.upAction() }) {
                Icon(
                    imageVector = appBarState.iconUpAction,
                    contentDescription = null,
                    tint = colors.onPrimary // Color de los íconos de acción
                )
            }
        },
        //Acciones que son visibles
        actions = {
            visibleActions.forEach { action ->
                if (action.isVisible) {
                    IconButton(onClick = { action.onClick() }) {
                        Icon(
                            imageVector = action.icon?: Icons.Filled.Add,
                            contentDescription = action.contentDescription,
                            tint = colors.onPrimary // Color de los íconos de acción
                        )
                    }
                }
            }

            // Si existen acciones ocultas, se muestra el icono de menú desplegable
            if (hiddenActions.isNotEmpty()) {
                IconButton(onClick = { menuExpanded = true }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "Más acciones",
                        tint = colors.onPrimary // Color de los íconos de acción
                    )
                }
                DropdownMenu(
                    expanded = menuExpanded,
                    onDismissRequest = { menuExpanded = false }
                ) {
                    hiddenActions.forEach { action ->
                        DropdownMenuItem(
                            onClick = {
                                action.onClick()
                                menuExpanded = false
                            }, text = {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = action.icon?: Icons.Filled.Add,
                                        contentDescription = action.contentDescription
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(text = action.name)
                                }
                            }
                        )
                    }

                }
            }

        }, //Fin del bloque de actions

        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = colors.primary, // Color de fondo, que cambia según el tema
            titleContentColor = colors.onPrimary // Color del texto en función del fondo
        ),
        //comportamiento "pinned", la barra superior permanece fija en la parte superior de la pantalla cuando se desplaza el contenido. Esto significa que, aunque el usuario haga scroll, la barra no se oculta ni se retrae, sino que se "ancla" en su posición.
        scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    )//Cierre de la llamada la función.
}

@Preview
@Composable
private fun PreviewTopAppBar() {
    BaseTopAppBar(
        appBarState = BaseTopAppBarState(
            title = "Título",
            upAction = { },
            iconUpAction = Icons.Filled.Menu,
            actions = listOf(
                Action(
                    name = "Acción 1",
                    icon = Icons.Filled.Edit,
                    contentDescription = "",
                    onClick = {},
                    isVisible = true,
                ),
                Action(
                    name = "Acción 2",
                    icon = Icons.Filled.Delete,
                    contentDescription = "",
                    onClick = {},
                    isVisible = false,
                )
            )
        )
    )
}
