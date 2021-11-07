package com.example.notesappfragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.notesapproom.Room.Notes

class FragmentUpdate : Fragment() {
    // Declaring ViewModel
    private val myViewModel by lazy { ViewModelProvider(this).get(myViewModel::class.java) }
    // Declaring UI Elements
    lateinit var etUpdate: EditText
    lateinit var btnUpdate: Button
    // Declaring SharedPreference
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_update, container, false)
        etUpdate = view.findViewById(R.id.etUpdate)
        btnUpdate = view.findViewById(R.id.btnUpdate)
        // Initializing SharedPreference
        sharedPreferences = requireActivity().getSharedPreferences("Notes", Context.MODE_PRIVATE)
        btnUpdate.setOnClickListener {
            update(Notes(sharedPreferences.getInt("NoteID",0), etUpdate.text.toString()))
            Navigation.findNavController(view).navigate(R.id.action_fragmentUpdate_to_fragmentNotes)
        }
        return view
    }
    fun update(note: Notes){
        val newNote = etUpdate.text.toString()
        if(newNote.isNotEmpty()){
            myViewModel.updateNote(Notes(note.id, newNote))
        }
        else{
            Toast.makeText(requireContext(), "You cannot update it with empty!", Toast.LENGTH_SHORT).show()
        }
    }
}