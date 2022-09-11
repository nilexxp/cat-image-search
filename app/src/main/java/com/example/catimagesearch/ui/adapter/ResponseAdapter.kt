package com.example.catimagesearch.ui.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.catimagesearch.R
import com.example.catimagesearch.data.google_responce.Item
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.response_item.view.*

class ResponseAdapter : RecyclerView.Adapter<ResponseAdapter.ViewHolder>() {

    private var values: MutableList<Item> = mutableListOf()
    lateinit var listener: (String)->Unit

    fun refresh(list: List<Item>){
        values.clear()
        values.addAll(list)
        notifyDataSetChanged()
    }

    fun clear(){
        values.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.response_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ResponseAdapter.ViewHolder, position: Int) {
        val item = values[position]
        holder.link.text = item.image?.contextLink.toString()
        holder.itemView.setOnClickListener {
            listener(item.image?.contextLink.toString())
        }
        Picasso.get()
            .load(Uri.parse(item.image?.thumbnailLink))
            .fit()
            .centerCrop()
            .into(holder.image)

    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.imageView
        val link: TextView = view.linkResponse
    }
}
