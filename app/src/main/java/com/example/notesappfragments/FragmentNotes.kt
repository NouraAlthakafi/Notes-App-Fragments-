package com.example.notesappfragments

import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notesappfragments.myViewModel
import com.example.notesapproom.Room.Notes

class FragmentNotes : Fragment() {
    // Declaring ViewModel
    private val myViewModel by lazy { ViewModelProvider(this).get(myViewModel::class.java) }
    // Declaring UI Elements
    lateinit var rvNotes: RecyclerView
    lateinit var RVnotesAdapter: RVnotes
    lateinit var notesArray: List<Notes>
    lateinit var etNote: EditText
    lateinit var btnSubmit: Button
    // Declaring SharedPreference
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_notes, container, false)
        rvNotes = view.findViewById(R.id.rvNotes)
        notesArray = arrayListOf()
        etNote = view.findViewById(R.id.etNote)
        btnSubmit = view.findViewById(R.id.btnSubmit)
        RVnotesAdapter = RVnotes(this, notesArray)
        rvNotes.adapter = RVnotesAdapter
        rvNotes.layoutManager = LinearLayoutManager(requireContext())
// Initializing SharedPreference
        sharedPreferences = requireActivity().getSharedPreferences("Notes", Context.MODE_PRIVATE)

        myViewModel.getNotesList().observe(viewLifecycleOwner, {
            notesArray -> RVnotesAdapter.rvChange(notesArray)
        })
        btnSubmit.setOnClickListener {
            getAndSave()
        }
        return view
    }
    fun pageUpdate(){
        Navigation.findNavController(requireView()).navigate(R.id.action_fragmentNotes_to_fragmentUpdate)
    }

    fun delete(note: Notes) {
        myViewModel.deleteNote(note)
    }

    fun alertDelete(note: Notes){
        val builder1 = AlertDialog.Builder(requireContext())
        builder1.setMessage("Are you sure?")
        builder1.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, id ->
            delete(note)
        })
            .setNegativeButton("No", DialogInterface.OnClickListener { dialog, id ->
                dialog.cancel()
            })
        val delete = builder1.create()
        delete.setTitle("Delete")
        delete.show()
    }

    fun getAndSave(){
        val note = etNote.text.toString()
        if(note.isNotEmpty()){
            myViewModel.insertNote(Notes(0, note))
            etNote.text.clear()
            etNote.clearFocus()
        }
        else{
            Toast.makeText(requireContext(), "Please enter something!", Toast.LENGTH_SHORT).show()
        }
    }
}