package com.maple.baiduocr

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.baidu.ocr.sdk.OCR
import com.baidu.ocr.sdk.OnResultListener
import com.baidu.ocr.sdk.exception.OCRError
import com.baidu.ocr.sdk.model.AccessToken
import com.baidu.ocr.ui.camera.CameraNativeHelper
import com.baidu.ocr.ui.camera.CameraView
import com.maple.baiduocr.example.BankCardActivity
import com.maple.baiduocr.example.IDCardActivity
import com.maple.baiduocr.utils.LogUtils
import com.maple.baiduocr.utils.PermissionUtil
import com.maple.baiduocr.utils.RequestPermission
import com.tbruyelle.rxpermissions2.RxPermissions

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val rxPermissions: RxPermissions = RxPermissions(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.findViewById<Button>(R.id.btn_idcard)?.setOnClickListener(this)
        this.findViewById<Button>(R.id.btn_bankcard)?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_idcard ->{
                applyPermissions(0)
            }

            R.id.btn_bankcard ->{
                applyPermissions(1)
            }
        }
    }

    private fun applyPermissions(type:Int) {
        PermissionUtil.applyCamera(object : RequestPermission {
            override fun onRequestPermissionSuccess() {
                //权限通过
                when(type){
                    0 ->{
                        startActivity(Intent(this@MainActivity,IDCardActivity::class.java))
                    }
                    1 ->{
                        startActivity(Intent(this@MainActivity,BankCardActivity::class.java))
                    }
                }
            }

            override fun onRequestPermissionFailure(permissions: List<String>) {
                Toast.makeText(this@MainActivity,"权限未通过",Toast.LENGTH_SHORT).show()
            }

            override fun onRequestPermissionFailureWithAskNeverAgain(permissions: List<String>) {
                Toast.makeText(this@MainActivity,"权限未通过",Toast.LENGTH_SHORT).show()
            }
        }, rxPermissions)
    }
}