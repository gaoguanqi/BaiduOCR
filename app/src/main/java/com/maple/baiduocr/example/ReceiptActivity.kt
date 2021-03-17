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

class ReceiptActivity: AppCompatActivity(), View.OnClickListener {
    private var tvContent: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receipt)
        tvContent = this.findViewById(R.id.tv_content)
        this.findViewById<Button>(R.id.btn_receipt)?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_receipt ->{
                openReceipt()
            }
        }
    }

    private val filePath = PathUtils.getExternalAppFilesPath()
    private val pathCard = filePath+"receipt.jpg"

    private val REQUEST_CODE_RECEIPT = 113

    private fun openReceipt() {
        val intent: Intent = Intent(this, CameraActivity::class.java)
        intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,pathCard)
        intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_GENERAL)
        startActivityForResult(intent,REQUEST_CODE_RECEIPT)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            when(requestCode){
                // 识别成功回调，通用票据识别
                REQUEST_CODE_RECEIPT ->{
                    RecognizeService.recReceipt(this,pathCard,object : RecognizeService.ServiceListener{
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