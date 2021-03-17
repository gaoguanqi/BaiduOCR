package com.maple.baiduocr.example

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.baidu.ocr.ui.camera.CameraActivity
import com.blankj.utilcode.util.PathUtils
import com.maple.baiduocr.R
import com.maple.baiduocr.utils.RecognizeService

class LicensePlateActivity: AppCompatActivity(), View.OnClickListener {
    private var tvContent: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_license_plate)
        tvContent = this.findViewById(R.id.tv_content)
        this.findViewById<Button>(R.id.btn_license_plate)?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_license_plate ->{
                openLicensePlate()
            }
        }
    }

    private val filePath = PathUtils.getExternalAppFilesPath()
    private val pathCard = filePath+"ebike_card.jpg"

    private val REQUEST_CODE_LICENSE_PLATE = 112

    private fun openLicensePlate() {
        val intent: Intent = Intent(this, CameraActivity::class.java)
        intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,pathCard)
        intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_GENERAL)
        startActivityForResult(intent,REQUEST_CODE_LICENSE_PLATE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            when(requestCode){
                // 识别成功回调，车牌识别
                REQUEST_CODE_LICENSE_PLATE ->{
                    RecognizeService.recLicensePlate(this,pathCard,object : RecognizeService.ServiceListener{
                        override fun onResult(result: String?) {
                            result?.let {
                                tvContent?.text = "${it}"
                            }
                        }
                    })
                }
            }
        }
    }
}