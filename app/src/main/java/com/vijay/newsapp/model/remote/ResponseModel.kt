package com.vijay.newsapp.model.remote

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.gson.annotations.SerializedName
import com.vijay.newsapp.R
import java.io.Serializable

/**
 * These are our data classes used to store names of variables available on server
 * This is reference model to call network.
 */

data class ResponseModel(

    @field:SerializedName("totalResults")
    val totalResults: Int? = null,

    @field:SerializedName("articles")
    val articles: List<ArticlesItem?>? = null,

    @field:SerializedName("status")
    val status: String? = null
)

data class Source(

    @field:SerializedName("name")
    var name: String? = null,

    @field:SerializedName("id")
    var id: String? = null
):Serializable


data class ArticlesItem(


    @field:SerializedName("publishedAt")
    var publishedAt: String? = null,

    @field:SerializedName("author")
    var author: String? = null,

    @field:SerializedName("urlToImage")
    var urlToImage: String? = null,

    @field:SerializedName("description")
    var description: String? = null,


    @field:SerializedName("source")
    var source: Source? = null,

    @field:SerializedName("title")
    var title: String? = null,

    @field:SerializedName("url")
    var url: String? = null,

    @field:SerializedName("content")
    var content: String? = null
) : Serializable {

}

@BindingAdapter("android:loadImage")
fun loadImage(imageView: ImageView, url: String?) {
    if (url != null) {
        Glide.with(imageView).load(url).placeholder(R.drawable.ic_launcher_foreground).into(imageView)
    }

}