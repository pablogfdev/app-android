package com.example.airport.ui.network


sealed class BaseResultList<T>{
    data class Success<T> (var data: ArrayList<T>?) : BaseResultList<T>()
    data class Error (var exception: Exception): BaseResultList<Nothing>()
}