package com.example.kotlineasynote.entities

import java.util.*

data class OneNote (val token:String = UUID.randomUUID().toString(),var description:String = "",var text:String="",var date:Date=Date()){
}