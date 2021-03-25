package com.maple.baiduocr.shared.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.maple.baiduocr.R
import com.maple.baiduocr.ext.layoutInflater
import com.maple.baiduocr.ext.loadImage
import com.maple.baiduocr.utils.LogUtils

class SharedAdapter(val context: Context) : RecyclerView.Adapter<SharedAdapter.ViewHolder>() {

    private val list: MutableList<String> = mutableListOf()

    private var listener: OnClickListener? = null

    fun setData(l: List<String>) {
        this.list.addAll(l)
        this.notifyDataSetChanged()
    }

    fun setListener(l: OnClickListener) {
        this.listener = l
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = context.layoutInflater.inflate(R.layout.item_shared, parent, false)
        val holder: ViewHolder = ViewHolder(view)
        holder.itemRoot.setOnClickListener {
            listener?.let { l ->
                l.onItemClick(holder.ivImg,holder.adapterPosition, list.get(holder.adapterPosition))
            }
        }
        return holder
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(position, list.get(position))
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemRoot: ConstraintLayout = itemView.findViewById(R.id.item_root)
        val ivImg: ImageView = itemView.findViewById(R.id.iv_img)
        val tvTxt: TextView = itemView.findViewById(R.id.tv_txt)

        fun setData(pos: Int, data: String?) {
            tvTxt.text = "item-${pos}"
            data?.let {
                LogUtils.logGGQ("--url->${it}")
                ivImg.loadImage(context,it)
            }
        }
    }

    interface OnClickListener {
        fun onItemClick(view:View,pos: Int, item: String?)
    }
}