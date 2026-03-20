package com.example.airport.ui.add

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerFormatter
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.airport.R
import com.example.airport.data.model.Cities
import com.example.airport.home.sendBasicNotification
import com.example.airport.ui.base.components.AlertDialogOK
import com.example.airport.ui.base.components.LoadingUi
import com.example.airport.ui.base.composables.TopAppBar

/**
 * fun ListAirportScreen()
 * fun AddAirportContent()
 *
 * Muestra la interfaz de la creacion/edicion de un Airport
 */
@Composable
fun AddAirportScreen(id: Int, goToList: () -> Unit, viewModel: AddAirportViewModel) {
    val state = viewModel.state
    LaunchedEffect(Unit) { viewModel.isEdition(id) }

    val events = AddAirportEvents(
        onSave = viewModel::onSave,
        onCityValueChange = viewModel::onCityValueChange,
        onCodeValueChange = viewModel::onCodeValueChange,
        onDateValueChange = viewModel::onDateValueChange,
        onDescriptionValueChange = viewModel::onDescriptionValueChange,
        onShowDialogValueChange = viewModel::onShowDialogValueChange,
        onErrorValueChange = viewModel::onErrorValueChange
    )

    when {
        state.isError -> {
            AlertDialogOK(
                state.titleError,
                state.messageError
            ) { events.onErrorValueChange() }
        }
        state.onBack -> {
            goToList()
            sendBasicNotification(LocalContext.current, "Se ha añadido un nuevo viaje", "Viaje a ${state.code}: ${state.city}")
        }
        state.isLoading -> LoadingUi()

        else -> {
            Scaffold(
                topBar = { TopAppBar(null) { } },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = { events.onSave() },
                        modifier = Modifier
                            .padding(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Done,
                            contentDescription = null
                        )
                    }

                }
                // El parámetro innerPadding contiene el relleno necesario para que el contenido no se superponga con la TopAppBar.
            ) { padding ->

                AddAirportContent(padding, events, state)


            }
        }
    }




}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAirportContent(padding: PaddingValues, events: AddAirportEvents, state: AddAirportState) {
    // Estados para los TextField

    val options by remember { mutableStateOf(Cities.entries.toTypedArray()) }
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(Cities.MALAGA.toString()) }
    //val changeOption = { it = selectedOption }

    Box(modifier = Modifier.fillMaxSize().padding(padding)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            // Fila 1: tvCode y edtCode
            if(!state.isEdition){
                Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = stringResource(id = R.string.txtCode),
                        fontSize = 18.sp,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    TextField(
                        value = state.code,
                        onValueChange = { events.onCodeValueChange(it) },
                        placeholder = { Text(text = stringResource(id = R.string.edtCode)) },
                        modifier = Modifier
                            .height(52.dp)
                            .weight(1f),
                        singleLine = true
                    )
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
            // Fila 2: tvCity y Spinner simulado
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(id = R.string.txtCity),
                    fontSize = 18.sp,
                    modifier = Modifier.width(68.dp)
                )
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { expanded = it }
                    ) {
                        OutlinedTextField(
                            value = state.city.toString(),
                            onValueChange = { },
                            readOnly = true,
                            placeholder = { Text(text = "Selecciona Ciudad") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .menuAnchor() // Importante para ExposedDropdownMenuBox
                                .clickable { expanded = true }
                        )

                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            modifier = Modifier.wrapContentSize()
                        ) {
                            options.forEach { option ->
                                DropdownMenuItem(
                                    onClick = {
                                        events.onCityValueChange(option.toString())
                                        expanded = false
                                      },
                                    text = {Text(option.toString())}
                                )
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
            // Fila 3: tvTitleDate y edDate
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.tvTitleDate),
                    fontSize = 18.sp,
                    modifier = Modifier.padding(end = 8.dp)
                )
                TextField(
                    value = state.date,
                    onValueChange = { events.onDateValueChange(it) },
                    placeholder = { Text(text = stringResource(id = R.string.edDate)) },
                    modifier = Modifier
                        .height(52.dp)
                        .weight(1f),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            // tvDescription y edtDescription
            Text(
                text = stringResource(id = R.string.tvDescription),
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = state.description,
                onValueChange = { events.onDescriptionValueChange(it) },
                placeholder = { Text(text = "Descripción") },
                modifier = Modifier.fillMaxWidth().height(300.dp),
                maxLines = Int.MAX_VALUE
            )
        }
    }
}