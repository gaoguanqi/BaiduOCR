package com.maple.baiduocr.widget.dialog

import android.content.Context
import android.os.Bundle
import android.widget.Button
import com.maple.baiduocr.R


class PictureOptionDialog(context: Context,val listener:OnClickListener):BaseBottomDialog(context,style = R.style.CommonDialogBottomStyle,isCancelable = true){

    companion object{
        const val TYPE_CAMERA = 1
        const val TYPE_PHOTO = 2
        const val TAG_A = 10
        const val TAG_B = 20
    }

    private var tag:Int = 0

    interface OnClickListener {
        fun onCameraClick(type:Int,tag:Int)
        fun onPhotoClick(type:Int,tag:Int)
        fun onCancelClick(){}
    }

    fun setTag(tag:Int):PictureOptionDialog {
        this.tag = tag
        return this
    }

    fun getTag():Int = tag



    override fun getLayoutId(): Int = R.layout.dialog_picture_option

    override fun initData(savedInstanceState: Bundle?) {

        findViewById<Button>(R.id.btn_camera)?.setOnClickListener {
            this.onCancel()
            this.listener.onCameraClick(TYPE_CAMERA,getTag())
        }

        findViewById<Button>(R.id.btn_photo)?.setOnClickListener {
            this.onCancel()
            this.listener.onPhotoClick(TYPE_PHOTO,getTag())
        }

        findViewById<Button>(R.id.btn_cancel)?.setOnClickListener {
            this.onCancel()
            this.listener.onCancelClick()
        }
    }
}