package com.example.notepad

import android.content.Context
import android.net.sip.SipSession
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesRVAdapter(private val context:Context,private val listener: INotesRVAdapter): RecyclerView.Adapter<NotesRVAdapter.NoteViewHolder>() {
    val allNotes=ArrayList<Note>()
    inner class NoteViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
        val textView=itemView.findViewById<TextView>(R.id.text)
        val deleteButton=itemView.findViewById<ImageView>(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewHolder = NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note, parent, false))
        viewHolder.deleteButton.setOnClickListener{
            listener.onItemClicked(allNotes[viewHolder.adapterPosition])
    }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NotesRVAdapter.NoteViewHolder, position: Int) {
     val current=allNotes[position]
        holder.textView.text=current.text
    }
fun updateList(newList:List<Note>){
    allNotes.clear()
    allNotes.addAll(newList)
    notifyDataSetChanged()
}
    override fun getItemCount(): Int {
        return allNotes.size;
    }
}
interface INotesRVAdapter{
    fun onItemClicked(note: Note)
}