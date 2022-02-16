package com.vijay.newsapp.view.adapter

import android.text.format.DateUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.vijay.newsapp.R
import com.vijay.newsapp.databinding.ItemViewBinding
import com.vijay.newsapp.model.remote.ArticlesItem
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * This is our adapter class use to bind data with views
 * and uses same views again and again
 * to make our app scroll Smoothly
 *
 */

class RecyclerViewAdapter(
    private val list: ArrayList<ArticlesItem>
) :
    RecyclerView.Adapter<RecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemViewBinding: ItemViewBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_view, parent, false)
        return RecyclerViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bindData(list[position])
    }

    override fun getItemCount(): Int = list.size
}

class RecyclerViewHolder(private val itemViewBinding: ItemViewBinding) :
    RecyclerView.ViewHolder(itemViewBinding.root) {
    fun bindData(articlesItem: ArticlesItem) {
        itemViewBinding.item = articlesItem

        val publishAt=articlesItem.publishedAt
        val format=SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val date=format.parse(publishAt)
        val currentDate=Date()

        val timeStamp=
            date?.time?.let { DateUtils.getRelativeTimeSpanString(it,currentDate.time,DateUtils.MINUTE_IN_MILLIS) }
        itemViewBinding.tvTimeStamp.text=timeStamp






    }
}