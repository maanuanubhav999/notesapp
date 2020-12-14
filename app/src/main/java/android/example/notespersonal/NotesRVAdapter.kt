package android.example.notespersonal

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesRVAdapter(private val context : Context, private val listner: INotesRVAdapter) : RecyclerView.Adapter<NotesRVAdapter.NoteViewholder>() {

    val allNotes = ArrayList<Note>()

    inner class NoteViewholder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textView = itemView.findViewById<TextView>(R.id.text)
        val deleteButton  = itemView.findViewById<ImageView>(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewholder {
    val viewholder =  NoteViewholder(LayoutInflater.from(context).inflate(R.layout.item_note, parent, false))
        viewholder.deleteButton.setOnClickListener{
        listner.onItemClicked(allNotes[viewholder.adapterPosition])
        }
        return  viewholder
    }

    override fun onBindViewHolder(holder: NoteViewholder, position: Int) {
        val currentNote = allNotes[position]
        holder.textView.text = currentNote.text
    }

    override fun getItemCount(): Int {
        return allNotes.size
        }

    fun updateList(newList : ArrayList<Note>){
        allNotes.clear()
        allNotes.addAll(newList )

        notifyDataSetChanged()
    }
}

interface INotesRVAdapter{
    fun onItemClicked(note: Note)
}
