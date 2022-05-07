package com.example.kotlineasynote.ui

import com.example.kotlineasynote.entities.CallBack
import com.example.kotlineasynote.entities.OneNote
import javax.security.auth.callback.Callback

interface Repository {
    fun getData(callback: CallBack<MutableList<OneNote>>)
    fun addNote(note: OneNote,callback: CallBack<Boolean>)
    fun updateNote(oldNote: OneNote,newNote: OneNote,callback: CallBack<Boolean>)
    fun deleteNote(note: OneNote,callback: CallBack<Boolean>)
}


