package com.example.kotlineasynote.ui.editnote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.kotlineasynote.R
import com.example.kotlineasynote.entities.OneNote
import com.example.kotlineasynote.ui.editnote.addoreditaction.ActionWithNote
import com.example.kotlineasynote.ui.editnote.addoreditaction.ActonWintNoteAddImpl
import com.example.kotlineasynote.ui.editnote.addoreditaction.ActonWintNoteEditImpl
import com.example.kotlineasynote.ui.mainview.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import java.util.*

class ModalBottomSheet(val param: String, val note: OneNote) : BottomSheetInterface,
    BottomSheetDialogFragment() {

    lateinit var description: TextInputEditText
    lateinit var text: TextInputEditText
    lateinit var button: MaterialButton
    lateinit var editPresenter: ActionWithNote
    private val model: MainViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.modal_bottom_sheet_content, container, false)

    companion object {
        const val TAG = "ModalBottomSheet"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        description = view.findViewById(R.id.id_input_description)
        text = view.findViewById(R.id.id_input_text)
        button = view.findViewById(R.id.bottomsheet_button)
        when (param) {
            "ADD" -> editPresenter = ActonWintNoteAddImpl(this)
            "EDIT" -> editPresenter = ActonWintNoteEditImpl(this)
            else -> editPresenter = ActonWintNoteAddImpl(this)
        }

        button.text = editPresenter.buttonText
        text.setText(note.text)
        description.setText(note.description)


        button.setOnClickListener {
            editPresenter.action()
            dismiss()

        }

    }

    override fun addNewNote() {
        model.addNote(OneNote(
                    description = this.description.text.toString(),
                    text = this.text.text.toString(),
                    date = Date()
                ))
    }

    override fun updateNote() {

        model.editNote(note,OneNote(
            description = this.description.text.toString(),
            text = this.text.text.toString(),
            date = Date()
        ))

    }

}