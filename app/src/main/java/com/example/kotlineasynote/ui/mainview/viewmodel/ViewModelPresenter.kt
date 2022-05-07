package com.example.kotlineasynote.ui.mainview.viewmodel

import com.example.kotlineasynote.entities.CallBack
import com.example.kotlineasynote.entities.OneNote
import javax.security.auth.callback.Callback

interface ViewModelPresenter {
    fun init(callback: CallBack<Boolean>)
    fun addNote(note: OneNote)
    fun addNoteClicked()
    fun deleteNote(note:OneNote)
    fun deleteNoteClicked(note:OneNote)

    fun editNote(note: OneNote,newNote: OneNote)
    fun editNoteClicked(note: OneNote)
}