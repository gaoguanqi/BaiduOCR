package com.maple.baiduocr.ext

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

//常用图片加载
fun ImageView.loadImage(context: Context,any: Any){
    Glide.with(context).load(any).into(this)
}
