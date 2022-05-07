package com.example.kotlineasynote.ui.mainview.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlineasynote.entities.CallBack
import com.example.kotlineasynote.entities.OneNote
import com.example.kotlineasynote.ui.RepositoryFirebaseImpl
import com.example.kotlineasynote.ui.RepositorySharedImpl

class MainViewModel : ViewModel(), ViewModelPresenter {

    var repository = RepositoryFirebaseImpl()
//    var repository = RepositorySharedImpl()
    var indicator=false
//

/*    val data: MutableLiveData<MutableList<OneNote>> by lazy {
        MutableLiveData<MutableList<OneNote>>().also {
            var temp1  = MutableList<OneNote>(1,{
                OneNote()
            })
            temp1.add(OneNote())
            it.value = temp1
        }
    }*/
val data: MutableLiveData<MutableList<OneNote>> by lazy {
        MutableLiveData<MutableList<OneNote>>().also {
            it.value = mutableListOf()
        }
    }

    val isNeedToEdit: MutableLiveData<OneNote> by lazy { MutableLiveData<OneNote>().also {
        it.value= OneNote()
    } }

    val isNeedToAdd: MutableLiveData<OneNote> by lazy { MutableLiveData<OneNote>().also {
        it.value= OneNote()
    } }


    fun getNotes(): LiveData<MutableList<OneNote>> {
        return data
    }



    override fun editNoteClicked(note: OneNote) {

        indicator = true
        isNeedToEdit.value=note
        indicator = false
//        viewFragment.openEditWindow(note)

    }


    override fun addNoteClicked() {
        indicator = true
        isNeedToAdd.value= OneNote()
        indicator = false
//        viewFragment.openAddWindow()
    }
    override fun deleteNoteClicked(note: OneNote) {

        deleteNote(note)
    }

    override fun init(callBack: CallBack<Boolean>) {

                repository.getData(object :CallBack<MutableList<OneNote>> {
                    override fun onSuccess(dataRecived: MutableList<OneNote>) {
                        data.value = dataRecived

                    }

                })

    }

    override fun addNote(newNote: OneNote) {
        data.value?.add(0, newNote)
        data.value = data.value

//        repository.addNote(newNote)
        repository.addNote(newNote,object :CallBack<Boolean>{
            override fun onSuccess(data: Boolean) {
//                TODO("Not yet implemented")
            }
        })

    }




    override fun deleteNote(note: OneNote) {
        val indexOldNote= data.value?.indexOf(note)
        data.value?.removeAt(indexOldNote!!)
        data.value = data.value

        repository.deleteNote(note,object :CallBack<Boolean>{
            override fun onSuccess(data: Boolean) {
//                TODO("Not yet implemented")
            }
        })
    }



    override fun editNote(note: OneNote, newNote: OneNote) {
        val indexOldNote= data.value?.indexOf(note)
        data.value?.set(indexOldNote!!,newNote)
        data.value = data.value
        repository.updateNote(note,newNote,object :CallBack<Boolean>{
            override fun onSuccess(data: Boolean) {
//                TODO("Not yet implemented")
            }
        })
    }






}