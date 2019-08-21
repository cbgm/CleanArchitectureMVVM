package com.christian.mvvmclean.presentation.overview

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.christian.mvvmclean.R
import com.christian.mvvmclean.presentation.detail.model.PostEntity


class PostsAdapter constructor(
    private var listener: PostClickListener,
    private var mData: List<PostEntity>
) :
    RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    // inflates the row layout from xml when needed
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.post_item, parent, false)
        return ViewHolder(view)
    }

    // binds the data to the TextView in each row
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mData[position]
        holder.itemView.setOnClickListener{
            listener.onclick(item)
        }
        holder.myTextView.text = item.title
    }

    // total number of rows
    override fun getItemCount(): Int {
        return mData.size
    }

    // stores and recycles views as they are scrolled off screen
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var myTextView: TextView = itemView.findViewById(R.id.title_text)
    }

    fun replaceData(data: List<PostEntity>) {
        mData = data
        this.notifyDataSetChanged()
    }

    interface PostClickListener {
        fun onclick(postEntity: PostEntity)
    }
}