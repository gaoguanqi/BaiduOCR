package com.maple.baiduocr.shared.adapter

import android.content.Context
import android.os.Build
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.github.chrisbanes.photoview.PhotoView
import com.maple.baiduocr.R
import com.maple.baiduocr.ext.layoutInflater
import com.maple.baiduocr.ext.loadImage

class PreviewAdapter(val context: Context) : RecyclerView.Adapter<PreviewAdapter.ViewHolder>() {

    private val list: MutableList<String> = mutableListOf()

    private val is21 by lazy { Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP }

    fun setData(l: List<String>) {
        this.list.addAll(l)
        this.notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = context.layoutInflater.inflate(R.layout.item_preview, parent, false)
        val holder: ViewHolder = ViewHolder(view)
        return holder
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(position, list.get(position))
        if(is21){
            ViewCompat.setTransitionName(holder.pvImg, position.toString());
        }
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val pvImg = itemView.findViewById<PhotoView>(R.id.pv_img)
        fun setData(pos: Int, data: String?) {
            data?.let {
                pvImg.loadImage(context,it)
            }
        }
    }
}