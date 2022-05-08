package com.example.kotlineasynote.ui.mainview

import android.annotation.SuppressLint
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.example.kotlineasynote.R
import com.example.kotlineasynote.databinding.FragmentMainBinding
import com.example.kotlineasynote.entities.CallBack
import com.example.kotlineasynote.entities.OneNote
import com.example.kotlineasynote.ui.RepositorySharedImpl
import com.example.kotlineasynote.ui.editnote.ModalBottomSheet
import com.example.kotlineasynote.ui.mainview.viewmodel.MainViewModel
import com.example.kotlineasynote.ui.mainview.viewmodel.ViewModelPresenter
import kotlin.math.roundToInt

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

    /**
     * initRecycler() функция подготавливающая RecyclerView к показу
     * внутри определяем лейаутменеджер, адаптер, поведение при свайпе (удаление элемета)
     *
     */
    private fun initRecycler() {
        model.getNotes().observe(viewLifecycleOwner, Observer<MutableList<OneNote>> {
            recyclerAdapter.data = it
            recyclerAdapter.notifyDataSetChanged()

        })

        binding.idRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), VERTICAL, false)
        binding.idRecyclerView.adapter = recyclerAdapter

        val myCallback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false


            @SuppressLint("UseCompatLoadingForDrawables")
            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                super.onChildDraw(
                    c, recyclerView, viewHolder,
                    dX, dY, actionState, isCurrentlyActive
                )

                c.clipRect(
                    viewHolder.itemView.width.toFloat() + dX,
                    viewHolder.itemView.top.toFloat(),
                    viewHolder.itemView.width.toFloat(),
                    viewHolder.itemView.bottom.toFloat()
                )

                /**
                 * iconSize- размер иконки отображаемой при свайпе
                 * trashBinIcon - иконка для отображения удаления заметки
                 */
                val iconSize = viewHolder.itemView.bottom - viewHolder.itemView.top
                val trashBinIcon = resources.getDrawable(
                    R.drawable.ic_baseline_delete_sweep_24,
                    null
                )
                val textMargin = resources.getDimension(R.dimen.text_margin)
                    .roundToInt()
                trashBinIcon.bounds = Rect(
                    viewHolder.itemView.width - iconSize,
                    viewHolder.itemView.top + textMargin,
                    viewHolder.itemView.width - textMargin,
                    viewHolder.itemView.bottom -
                            textMargin
                )

                /**
                 * отображаем иконку корзины если свайпнули заметку более чем на 1/3 экрана
                 */
                if (Math.abs(dX) > viewHolder.itemView.width / 3)
                    trashBinIcon.draw(c)
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                model.deleteNote(recyclerAdapter.data.get(viewHolder.adapterPosition))

            }

        }

        val myHelper = ItemTouchHelper(myCallback)
        myHelper.attachToRecyclerView(binding.idRecyclerView)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         * model.init получает данные о заметках из репозитория
         */
        model.init(object : CallBack<Boolean> {
            override fun onSuccess(data: Boolean) {

            }
        })


        initRecycler()

        recyclerAdapter.clickedNote = object : RecyclerViewAdapter.ClickedNote {
            override fun clicked(note: OneNote) {
                model.editNoteClicked(note)
            }

        }

        /**
         * binding.floatingActionButton
         * нажата кнопка добавления
         */
        binding.floatingActionButton.setOnClickListener {
            model.addNoteClicked()
        }
        initListeners()


    }

    /**
     * initListeners()
     * устанавливаем все слушатели VM
     */
    private fun initListeners() {
        model.isNeedToEdit.observe(viewLifecycleOwner, Observer<OneNote> {
            val modalBottomSheet = ModalBottomSheet(EDIT, it)
            if (model.indicator) {
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

