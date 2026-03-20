package com.example.airport.ui.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.airport.data.model.Airport
import com.example.airport.ui.base.components.AlertDialogOkOrNot
import com.example.airport.ui.base.components.LoadingUi
import com.example.airport.ui.base.components.NoDataScreen
import com.example.airport.ui.base.composables.TopAppBar
import com.example.airport.ui.base.icon_composables.airportIcon
import com.example.airport.ui.base.icon_composables.orderByAsc
import com.example.airport.ui.base.icon_composables.orderByDesc

/**
 * fun ListAirportScreen()
 * fun ListAirportContent()
 *
 * Muestra la interfaz de la lista de Airports
 */
@Composable
fun ListAirportScreen(viewModel: ListAirportViewModel, gotoAdd: (String) -> Unit) {

    val state = viewModel.state
    LaunchedEffect(Unit) { viewModel.getList() }

    val events = ListAirportEvents(
        onSelectedAirportValueChange = viewModel::onSelectedAirportValueChange,
        onSort = viewModel::onSort,
        onDelete = viewModel::onDelete,
        onShowDialogValueChange = viewModel::onShowDialogValueChange
    )

    Scaffold(
        topBar = { TopAppBar(if (state.isSort) orderByDesc() else orderByAsc(), events.onSort) },
        floatingActionButton = {
            FloatingActionButton(onClick = { gotoAdd("0") }) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Añadir Avería"
                )
            }

        }
        // El parámetro innerPadding contiene el relleno necesario para que el contenido no se superponga con la TopAppBar.
    ) { padding ->

        when {
            state.noData -> NoDataScreen()
            state.isLoading -> LoadingUi()
            state.airports.isNotEmpty() -> ListAirportContent(innerPadding = padding, state, events, gotoAdd)
        }

        if (state.isShowDialog) {
            AlertDialogOkOrNot(
                "Eliminar Airport",
                "¿Estas seguro? No hay vuelta atras",
                {
                    events.onShowDialogValueChange()
                    events.onDelete()
                },
                {
                    events.onShowDialogValueChange()
                }
            )
        }
    }
}


@Composable
fun ListAirportContent(
    innerPadding: PaddingValues,
    state: ListAirportState,
    events: ListAirportEvents,
    gotoAdd: (String) -> Unit
) {

    LazyColumn(
        contentPadding = innerPadding,
        verticalArrangement = Arrangement.spacedBy(8.dp),

        ) {
            items(state.airports){
                AirportItem(modifier = Modifier, it, events, gotoAdd)
            }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AirportItem(
    modifier: Modifier = Modifier,
    airport: Airport,
    events: ListAirportEvents,
    gotoAdd: (String) -> Unit
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 6.dp,
        modifier = modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .combinedClickable(
                onClick = { gotoAdd(airport._id.toString())},
                onLongClick = {
                    events.onSelectedAirportValueChange(airport)
                    events.onShowDialogValueChange()
                }
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)) // Fondo suave
            ) {
                Image(
                    imageVector = airportIcon(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp) // Espaciado interno para separar del borde
                )
            }
            Text(
                text = "${airport.code}: ${airport.city} ${airport.date}",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}
