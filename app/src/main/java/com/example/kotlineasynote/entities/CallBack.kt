package com.example.kotlineasynote.entities

interface CallBack<T> {
    fun onSuccess(data:T)
}