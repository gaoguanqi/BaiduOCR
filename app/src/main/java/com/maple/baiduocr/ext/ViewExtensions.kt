package com.maple.baiduocr.ext

import android.widget.ImageView
import com.maple.baiduocr.R
import com.maple.baiduocr.app.MyApplication
import com.maple.ximageloader.ImageLoader
import com.maple.ximageloader.glide.GlideImageConfig
import com.maple.ximageloader.glide.TransType

//常用图片加载
fun ImageView.loadImage(any: Any) = ImageLoader.getInstance().loadImage(
    MyApplication.instance,
    GlideImageConfig(
        any,
        this,
        placeholder = R.drawable.ic_default_placeholder,
        errorPic = R.drawable.ic_default_errorpic
    ).also { c -> c.type = TransType.NORMAL })

//自定义配置图片加载
fun ImageView.loadConfigImage(
    any: Any,
    config: GlideImageConfig = GlideImageConfig(
        any,
        this,
        placeholder = R.drawable.ic_default_placeholder,
        errorPic = R.drawable.ic_default_errorpic
    ).also { c -> c.type = TransType.NORMAL }
) = ImageLoader.getInstance().loadImage(MyApplication.instance, config)
