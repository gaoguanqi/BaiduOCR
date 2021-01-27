package com.maple.baiduocr.example

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.king.zxing.CameraScan
import com.maple.baiduocr.R

class QRCodeActivity:AppCompatActivity(), View.OnClickListener {
    private val REQUEST_CODE_QRCODE = 121

    private var tvContent: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qrcode)

        tvContent = this.findViewById(R.id.tv_content)
        this.findViewById<Button>(R.id.btn_qrcode)?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_qrcode ->{
                openQRCode()
            }
        }
    }

    private fun openQRCode() {
        startActivityForResult(Intent(this,MyCaptureActivity::class.java),REQUEST_CODE_QRCODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            when(requestCode){
                REQUEST_CODE_QRCODE ->{
                    data?.let {
                        val content = it.getStringExtra(CameraScan.SCAN_RESULT)
                        if(!TextUtils.isEmpty(content)){
                            tvContent?.text = "扫描结果：${content}"
                        }
                    }
                }
            }
        }
    }
}