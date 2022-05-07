package com.example.kotlineasynote.ui.editnote.addoreditaction

import com.example.kotlineasynote.ui.editnote.BottomSheetInterface

class ActonWintNoteAddImpl(var dialog: BottomSheetInterface) : ActionWithNote() {
    override var buttonText = "ADD"

    override fun action() {
       dialog.addNewNote()
    }
}