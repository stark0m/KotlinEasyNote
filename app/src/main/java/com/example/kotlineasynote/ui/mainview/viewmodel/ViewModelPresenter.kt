package com.example.kotlineasynote.ui.mainview.viewmodel

import com.example.kotlineasynote.entities.OneNote

interface ViewModelPresenter {
    fun addNote(note: OneNote)
    fun addNoteClicked()
    fun deleteNote(note:OneNote)
    fun deleteNoteClicked(note:OneNote)

    fun editNote(note: OneNote,newNote: OneNote)
    fun editNoteClicked(note: OneNote)
}