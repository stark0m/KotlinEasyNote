package com.example.kotlineasynote.ui

import com.example.kotlineasynote.entities.OneNote

interface Repository {
    fun getData():MutableList<OneNote>
    fun addNote(note: OneNote):Boolean
    fun updateNote(oldNote: OneNote,newNote: OneNote):Boolean
    fun deleteNote(note: OneNote):Boolean
}