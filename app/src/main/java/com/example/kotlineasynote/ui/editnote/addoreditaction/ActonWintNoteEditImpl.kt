package com.example.kotlineasynote.ui.editnote.addoreditaction

import com.example.kotlineasynote.ui.editnote.BottomSheetInterface

class ActonWintNoteEditImpl(var dialog: BottomSheetInterface) : ActionWithNote() {
    override var buttonText = "EDIT"

    override fun action() {
       dialog.updateNote()
    }
}