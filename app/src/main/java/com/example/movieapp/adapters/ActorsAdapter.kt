package com.example.movieapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R

class ActorsAdapter(private val actors: ArrayList<String> = ArrayList<String>()) :
    RecyclerView.Adapter<ActorsAdapter.ActorsViewHolder>() {

    class ActorsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var movieActor: TextView = view.findViewById(R.id.movie_actor)

        fun bind(actor: String) {
            movieActor.text = actor
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorsAdapter.ActorsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.actors_layout, parent, false)
        return ActorsAdapter.ActorsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActorsAdapter.ActorsViewHolder, position: Int) {
        holder.bind(actors[position])
    }

    override fun getItemCount(): Int {
        return actors.size
    }

}