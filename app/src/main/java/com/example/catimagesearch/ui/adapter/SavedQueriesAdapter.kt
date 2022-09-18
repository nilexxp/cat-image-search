package com.example.catimagesearch.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.catimagesearch.R
import kotlinx.android.synthetic.main.item_queries.view.*

class SavedQueriesAdapter: RecyclerView.Adapter<SavedQueriesAdapter.ViewHolder>() {
    private var values: MutableList<String> = mutableListOf()
    lateinit var listener: (String)->Unit

    fun refresh(list: List<String>){
        values.clear()
        values.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_queries, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SavedQueriesAdapter.ViewHolder, position: Int) {
        val item = values[position]
        holder.title.text = item
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.titleQuery
        init {
            view.setOnClickListener {
                listener(it.titleQuery.text.toString())
            }
        }
    }
}