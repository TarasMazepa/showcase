package com.showcase.movies.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.showcase.R
import com.showcase.movies.Movie
import com.squareup.picasso.Picasso

class MoviesAdapter(val onClick: (Movie) -> Unit) : Adapter<MoviesAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView by lazy { view.findViewById(R.id.image) }
        val title: TextView by lazy { view.findViewById(R.id.title) }
    }

    var items: List<Movie> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items.getOrNull(position) ?: return
        holder.title.text = item.title
        Picasso.get().load(item.image).into(holder.image)
        holder.itemView.setOnClickListener {
            onClick(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}