package com.example.kotlineasynote.ui

import com.example.kotlineasynote.entities.OneNote
import java.util.*

class RepositorySharedImpl:Repository {
    override fun getData(): MutableList<OneNote> {
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


        }
    fun getData2(): MutableList<OneNote> {
        return listOf<OneNote>(OneNote("token1","11111","Berlin is the capital", Date()),
            OneNote("token1","3333 world","222 is the capital", Date()),
            OneNote("token1","444 world","Berlin is the capital", Date()),
            OneNote("token1","555555 world","Berlin is the capital", Date()),
            OneNote("token1","Hello world","Berlin is the capital", Date()),
            OneNote("token1","Hello world","Berlin is the capital", Date()),
            OneNote("token1","Hello world","Berlin is the capital", Date()),
            OneNote("token1","Hello world","Berlin is the capital", Date()),
            OneNote("token1","Hello world","Berlin is the capital", Date()),
            OneNote("token1","Hello world","Berlin is the capital", Date())).toMutableList()


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
    }
}