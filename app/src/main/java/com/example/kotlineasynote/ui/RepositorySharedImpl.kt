package com.example.kotlineasynote.ui

import com.example.kotlineasynote.entities.CallBack
import com.example.kotlineasynote.entities.OneNote
import java.util.*
import javax.security.auth.callback.Callback

class RepositorySharedImpl:Repository {
   /* override fun getData(): MutableList<OneNote> {
       return listOf<OneNote>(OneNote("token1","Hello world","Berlin is the capital", Date()),
            OneNote("token1","Hello world","Berlin is the capital", Date()),
            OneNote("token1","Hello world","Berlin is the capital", Date()),
            OneNote("token1","Hello world","Berlin is the capital", Date()),
            OneNote("token1","Hello world","Berlin is the capital", Date()),
            OneNote("token1","Hello world","Berlin is the capital", Date()),
            OneNote("token1","Hello world","Berlin is the capital", Date()),
            OneNote("token1","Hello world","Berlin is the capital", Date()),
            OneNote("token1","Hello world","Berlin is the capital", Date()),
            OneNote("token1","Hello world","Berlin is the capital", Date())).toMutableList()
*/
/*
        }



    override fun addNote(note: OneNote): Boolean {
        return true
    //        TODO("Not yet implemented")
    }

    override fun updateNote(oldNote: OneNote, newNote: OneNote): Boolean {
        return true
        TODO("Not yet implemented")
    }

    override fun deleteNote(note: OneNote):Boolean {
        return true
        TODO("Not yet implemented")
    }*/

    override fun getData(callback: CallBack<MutableList<OneNote>>) {

        callback.onSuccess(listOf<OneNote>(OneNote("token1","Hello world","Berlin is the capital", Date()),
            OneNote("token1","Hello world","Berlin is the capital", Date()),
            OneNote("token1","Hello world","Berlin is the capital", Date()),
            OneNote("token1","Hello world","Berlin is the capital", Date()),
            OneNote("token1","Hello world","Berlin is the capital", Date()),
            OneNote("token1","Hello world","Berlin is the capital", Date()),
            OneNote("token1","Hello world","Berlin is the capital", Date()),
            OneNote("token1","Hello world","Berlin is the capital", Date()),
            OneNote("token1","Hello world","Berlin is the capital", Date()),
            OneNote("token1","Hello world","Berlin is the capital", Date())).toMutableList())
    }



    override fun addNote(note: OneNote, callback: CallBack<Boolean>) {
        callback.onSuccess(true)
    }

    override fun updateNote(oldNote: OneNote, newNote: OneNote, callback: CallBack<Boolean>) {
        callback.onSuccess(true)
    }

    override fun deleteNote(note: OneNote, callback: CallBack<Boolean>) {
       callback.onSuccess(true)
    }


}