package com.maple.baiduocr.example

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.baidu.ocr.sdk.model.IDCardParams
import com.baidu.ocr.ui.camera.CameraActivity
import com.blankj.utilcode.util.FileUtils
import com.blankj.utilcode.util.ImageUtils
import com.blankj.utilcode.util.PathUtils
import com.blankj.utilcode.util.TimeUtils
import com.maple.baiduocr.R
import com.maple.baiduocr.utils.LogUtils
import java.io.File

class TakeIDCardActivity:AppCompatActivity(), View.OnClickListener {
    private var ivIDCard:ImageView? = null
    private val type:Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_take_idcard)
        ivIDCard = this.findViewById(R.id.iv_idcard)
        this.findViewById<Button>(R.id.btn_idCardA)?.setOnClickListener(this)
        this.findViewById<Button>(R.id.btn_idCardB)?.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_idCardA ->{
                openIdCardA()
            }
            R.id.btn_idCardB ->{
                openIdCardB()
            }
        }
    }

    private val filePath = PathUtils.getExternalAppFilesPath()
    private val pathA = filePath+"take_idcard_a.jpg"
    private val pathB = filePath+"take_idcard_b.jpg"

    private val REQUEST_CODE_CAMERA = 103

    private fun openIdCardA() {
        val directory: File = File(filePath)
        if(!directory.exists() || directory.isFile){
            directory.mkdir()
        }

        val intent: Intent = Intent(this, CameraActivity::class.java)
        intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,pathA)
        intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_ID_CARD_FRONT)
        intent.putExtra(CameraActivity.KEY_CAMERA_TYPE, type)
        startActivityForResult(intent,REQUEST_CODE_CAMERA)

    }

    private fun openIdCardB() {
        val directory: File = File(filePath)
        if(!directory.exists() || directory.isFile){
            directory.mkdir()
        }

        val intent: Intent = Intent(this, CameraActivity::class.java)
        intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,pathB)
        intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_ID_CARD_BACK)
        intent.putExtra(CameraActivity.KEY_CAMERA_TYPE, type)
        startActivityForResult(intent,REQUEST_CODE_CAMERA)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            when(requestCode){
                REQUEST_CODE_CAMERA ->{
                    data?.let {
                        val contentType = it.getStringExtra(CameraActivity.KEY_CONTENT_TYPE)
                        if(!TextUtils.isEmpty(contentType)){
                            if(TextUtils.equals(contentType,CameraActivity.CONTENT_TYPE_ID_CARD_FRONT)){
                                //正面
                                saveIDCard(IDCardParams.ID_CARD_SIDE_FRONT,pathA)
                            }else if(TextUtils.equals(contentType,CameraActivity.CONTENT_TYPE_ID_CARD_BACK)){
                                //反面
                                saveIDCard(IDCardParams.ID_CARD_SIDE_BACK,pathB)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun saveIDCard(idCardSide: String, filePath: String) {

        val imageFile = File(filePath)
        LogUtils.logGGQ("压缩前->${FileUtils.getSize(imageFile)}")
        val tempImage = File(this.getCacheDir(),TimeUtils.getNowString())
        val imageQuality = 80  //压缩 20 - 100
        com.baidu.ocr.sdk.utils.ImageUtil.resize(imageFile.getAbsolutePath(),tempImage.getAbsolutePath(),1280,1280,imageQuality)
        LogUtils.logGGQ("压缩后->${FileUtils.getSize(tempImage)}")

        LogUtils.logGGQ("是否是图片-->>${ImageUtils.isImage(tempImage)}")
        if(ImageUtils.isImage(tempImage)){
            ivIDCard?.setImageBitmap(ImageUtils.getBitmap(tempImage))
        }

    }

}