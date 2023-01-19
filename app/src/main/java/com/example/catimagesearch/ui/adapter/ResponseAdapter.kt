package com.example.catimagesearch.ui.adapter

import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.catimagesearch.R
import com.example.catimagesearch.data.google_responce.Item
import com.example.catimagesearch.ui.search_screen.SearchScreen
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.response_item.view.*

class ResponseAdapter : RecyclerView.Adapter<ResponseAdapter.ViewHolder>() {

    private var values: MutableList<Item> = mutableListOf()
    lateinit var downloadListener: (item: String)->Unit
    lateinit var copyListener: (item: String)->Unit
    lateinit var shareListener: (bitmap: BitmapDrawable)->Unit

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

        Picasso.get()
            .load(Uri.parse(item.image?.thumbnailLink))
            .fit()
            .centerCrop()
            .into(holder.image)

        holder.download.setOnClickListener {
            downloadListener(item.image?.contextLink.toString())
        }
        holder.copyLink.setOnClickListener {
            copyListener(item.image?.contextLink.toString())
        }
        holder.share.setOnClickListener {
            shareListener(holder.image.drawable as BitmapDrawable)
        }


    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.imageView
        val download: ImageButton = view.downloadButton
        val copyLink: ImageButton = view.copyLinkButton
        val share: ImageButton = view.shareButton
    }
}
