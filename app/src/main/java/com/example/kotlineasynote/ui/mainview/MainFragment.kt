package com.example.kotlineasynote.ui.mainview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.example.kotlineasynote.databinding.FragmentMainBinding
import com.example.kotlineasynote.entities.CallBack
import com.example.kotlineasynote.entities.OneNote
import com.example.kotlineasynote.ui.RepositorySharedImpl
import com.example.kotlineasynote.ui.editnote.ModalBottomSheet
import com.example.kotlineasynote.ui.mainview.viewmodel.MainViewModel
import com.example.kotlineasynote.ui.mainview.viewmodel.ViewModelPresenter

const val EDIT = "EDIT"
const val ADD = "ADD"

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private var recyclerAdapter = RecyclerViewAdapter()


    private val model: MainViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    private fun initRecycler() {
        model.getNotes().observe(viewLifecycleOwner, Observer<MutableList<OneNote>> {
            recyclerAdapter.data = it
            recyclerAdapter.notifyDataSetChanged()

        })




        binding.idRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), VERTICAL, false)
        binding.idRecyclerView.adapter = recyclerAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model.init(object :CallBack<Boolean>{
            override fun onSuccess(data: Boolean) {

            }
        })


        initRecycler()

        recyclerAdapter.clickedNote = object : RecyclerViewAdapter.ClickedNote {
            override fun clicked(note: OneNote) {
                model.editNoteClicked(note)
            }

        }

        binding.floatingActionButton.setOnClickListener {
            model.addNoteClicked()
        }
        initListeners()




    }

    private fun initListeners() {
        model.isNeedToEdit.observe(viewLifecycleOwner, Observer<OneNote> {
            val modalBottomSheet = ModalBottomSheet(EDIT, it)
            if (model.indicator){
                modalBottomSheet.show(parentFragmentManager, ModalBottomSheet.TAG)
            }


        })


        model.isNeedToAdd.observe(viewLifecycleOwner, Observer<OneNote> {
            val modalBottomSheet = ModalBottomSheet(ADD, OneNote())
            if (model.indicator) {
                modalBottomSheet.show(parentFragmentManager, ModalBottomSheet.TAG)
            }

        })
    }




}

