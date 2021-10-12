package com.example.weatherapp.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.weatherapp.R

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String) {
    Glide.with(imgView.context)
        .load(imgUrl)
        .apply (
            RequestOptions()
            .placeholder(R.drawable.loading_animation)
            .error(R.drawable.ic_baseline_wb_sunny_24)
        )
        .into(imgView)
}