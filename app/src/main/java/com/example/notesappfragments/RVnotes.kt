package com.example.notesappfragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesappfragments.databinding.ItemNoteBinding
import com.example.notesapproom.Room.Notes


class RVnotes(val Fragment: FragmentNotes, var notesArray: List<Notes>) : RecyclerView.Adapter<RVnotes.ViewHolder>() {
    class ViewHolder(val binding: ItemNoteBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )}

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val aNote = notesArray[position]
        holder.binding.apply {
            tvNote.text = aNote.note
            ibEdit.setOnClickListener {
                with(Fragment.sharedPreferences.edit()){
                    putInt("NoteID",aNote.id)
                    apply()
                }
                Fragment.pageUpdate()
            }
            ibDelete.setOnClickListener{
                Fragment.alertDelete(aNote)
            }
        }
    }

    override fun getItemCount(): Int = notesArray.size
    fun rvChange(notes: List<Notes>) {
        this.notesArray = notes
        notifyDataSetChanged()
    }
}