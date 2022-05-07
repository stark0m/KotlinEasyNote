package com.example.kotlineasynote.ui

import android.util.Log
import com.example.kotlineasynote.entities.OneNote
import com.example.kotlineasynote.ui.editnote.ModalBottomSheet.Companion.TAG
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*

//class RepositoryFirebaseImpl:Repository {
//
//    companion object{
//        val COLLECTION = "notes"
//
//    }
//    val db = Firebase.firestore
//
//    override fun getData(): MutableList<OneNote> {
//        var collection: MutableList<OneNote>
//        db.collection(COLLECTION)
//            .get()
//            .addOnSuccessListener { result ->
//                for (document in result) {
//                    val text = document.get("text").toString()
//                    val date = document.get("date").toString()
//                    val description = document.get("description").toString()
//                    val token = document.id
//                    val readedNote =OneNote(token, description = description,text=text, date = Date())
//
//                    Log.d(TAG, "${document.id} => ${document.data}")
//                }
//            }
//            .addOnFailureListener { exception ->
//                Log.d(TAG, "Error getting documents: ", exception)
//            }
//    }
//
//    override fun addNote(note: OneNote): Boolean {
//
//        val document = note.token
//        val firebaseNOte = hashMapOf(
//            "description" to note.description,
//            "text" to note.text,
//            "date" to note.date.toString())
//
//        db.collection(COLLECTION)
//            .document(document)
//            .set(firebaseNOte)
//            .addOnSuccessListener { documentReference ->
//
//                Log.d(TAG, "DocumentSnapshot successfully written!")
//            }
//            .addOnFailureListener { e ->
//                Log.w(TAG, "Error adding document", e)
//            }
//
//    }
//
//    override fun updateNote(oldNote: OneNote, newNote: OneNote): Boolean {
//        val document = oldNote.token
//        val firebaseNOte = hashMapOf(
//            "description" to newNote.description,
//            "text" to newNote.text,
//            "date" to newNote.date.toString())
//
//        db.collection(COLLECTION)
//            .document(document)
//            .set(firebaseNOte)
//            .addOnSuccessListener { documentReference ->
//
//                Log.d(TAG, "DocumentSnapshot successfully written!")
//            }
//            .addOnFailureListener { e ->
//                Log.w(TAG, "Error adding document", e)
//            }
//    }
//
//    override fun deleteNote(note: OneNote): Boolean {
//        val document = note.token
//        db.collection(COLLECTION)
//            .document(document)
//            .delete()
//            .addOnSuccessListener { documentReference ->
//
//                Log.d(TAG, "DocumentSnapshot successfully written!")
//            }
//            .addOnFailureListener { e ->
//                Log.w(TAG, "Error adding document", e)
//            }
//    }
//}