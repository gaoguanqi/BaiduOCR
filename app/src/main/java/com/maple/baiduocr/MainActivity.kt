package com.maple.baiduocr

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.maple.baiduocr.example.*
import com.maple.baiduocr.shared.SharedElementActivity
import com.maple.baiduocr.utils.LogUtils
import com.maple.baiduocr.utils.PermissionUtil
import com.maple.baiduocr.utils.RequestPermission
import com.tbruyelle.rxpermissions2.RxPermissions
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val rxPermissions: RxPermissions = RxPermissions(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.findViewById<Button>(R.id.btn_idcard)?.setOnClickListener(this)
        this.findViewById<Button>(R.id.btn_bankcard)?.setOnClickListener(this)
        this.findViewById<Button>(R.id.btn_take_idcard)?.setOnClickListener(this)
        this.findViewById<Button>(R.id.btn_qrcode)?.setOnClickListener(this)
        this.findViewById<Button>(R.id.btn_license_plate)?.setOnClickListener(this)
        this.findViewById<Button>(R.id.btn_receipt)?.setOnClickListener(this)
        this.findViewById<Button>(R.id.btn_ocr_api)?.setOnClickListener(this)
        this.findViewById<Button>(R.id.btn_shared_element)?.setOnClickListener(this)

        val date = Date()
        val myear = date.year - 100
        LogUtils.logGGQ("myear:${date.year}")
        LogUtils.logGGQ("myear:${myear}")
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_idcard ->{
                applyPermissions(0)
            }

            R.id.btn_bankcard ->{
                applyPermissions(1)
            }

            R.id.btn_take_idcard ->{
                applyPermissions(2)
            }

            R.id.btn_qrcode ->{
                applyPermissions(3)
            }

            R.id.btn_license_plate ->{
                applyPermissions(4)
            }

            R.id.btn_receipt ->{
                applyPermissions(5)
            }

            R.id.btn_ocr_api ->{
                startActivity(Intent(this,OcrApiActivity::class.java))
            }

            R.id.btn_shared_element ->{
                startActivity(Intent(this,SharedElementActivity::class.java))
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
                    2 ->{
                        startActivity(Intent(this@MainActivity,TakeIDCardActivity::class.java))
                    }
                    3 ->{
                        startActivity(Intent(this@MainActivity,QRCodeActivity::class.java))
                    }
                    4 ->{
                        startActivity(Intent(this@MainActivity,LicensePlateActivity::class.java))
                    }
                    5 ->{
                        startActivity(Intent(this@MainActivity,ReceiptActivity::class.java))
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