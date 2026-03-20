package com.example.airport.ui.add

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.airport.data.model.Airport
import com.example.airport.data.model.Cities
import com.example.airport.data.repository.AirportRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * class AddAirportViewModel
 *
 * Gestiona el estado y la logica de la UI
 */
@HiltViewModel
class AddAirportViewModel @Inject constructor(
    private val airportRepository: AirportRepository
) : ViewModel() {

    var state by mutableStateOf(AddAirportState())
        private set

    fun onSave(){
        state = state.copy(isLoading = true)
        val airport = getAirportFromState()
        viewModelScope.launch {
            if(isError()) return@launch
            if(state.isEdition) airportRepository.updateAirport(airport)
            else airportRepository.insertAirport(airport)
            onBack()
        }
        state = state.copy(isLoading = false)
    }

    private suspend fun isError(): Boolean {
        when{
            state.code.isEmpty() -> {
                state = state.copy(
                    isError = true,
                    titleError = "Campo code vacio",
                    messageError = "El campo code no puede estar vacio"
                )
            }
            state.description.isEmpty() -> {
                state = state.copy(
                    isError = true,
                    titleError = "Campo description vacio",
                    messageError = "El campo description no puede estar vacio"
                )
            }
            state.date.isEmpty() -> {
                state = state.copy(
                    isError = true,
                    titleError = "Campo date vacio",
                    messageError = "El campo date no puede estar vacio"
                )
            }
            state.code.length != 3 -> {
                state = state.copy(
                    isError = true,
                    titleError = "Campo code incorrecto",
                    messageError = "El campo code debe contener exactamente 3 caracteres"
                )
            }
            isExistAirport() -> {
                state = state.copy(
                    isError = true,
                    titleError = "Airport existente",
                    messageError = "Este airport ya existe en la base de datos"
                )
            }
        }
        return state.isError
    }

    private fun getAirportFromState(): Airport {
        return Airport(
            code = state.code,
            city = state.city,
            date = state.date,
            description = state.description
        )
    }

    suspend fun isEdition(id: Int){
        onReset()
        if(id==0) return
        state = state.copy(idSelected = id, isEdition = true)
        val airport = airportRepository.getAirportById(id)!!

        state = state.copy(
            code = airport.code,
            city = airport.city,
            date = airport.date,
            description = airport.description
        )
    }

    fun onCodeValueChange(value: String){
        state = state.copy(code = value)
    }

    fun onCityValueChange(value: String){
        state = state.copy(city = Cities.valueOf(value))
    }

    fun onDateValueChange(value: String){
        state = state.copy(date = value)
    }

    fun onDescriptionValueChange(value: String){
        state = state.copy(description = value)
    }

    fun onShowDialogValueChange(){
        state = state.copy(showDialog = !state.showDialog)
    }

    fun onErrorValueChange(){
        state = state.copy(isError = !state.isError)
    }

    private fun onBack(){
        state = state.copy(onBack = true)
    }

    private fun onReset(){
        state = state.copy(
            code = "",
            city = Cities.MALAGA,
            date = "",
            description = "",
            isError = false,
            titleError = "",
            messageError = "",
            idSelected = 0,
            isEdition = false,
            showDialog = false,
            isLoading = false,
            onBack = false
        )
    }

    private suspend fun isExistAirport(): Boolean{

        val airport: Airport? = airportRepository.getAirportByCodeAndDate(state.code, state.date)
        return airport != null && airport._id != state.idSelected




    }


}