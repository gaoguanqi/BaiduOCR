package com.maple.baiduocr.example

import android.app.Activity
import android.bluetooth.BluetoothProfile
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.baidu.ocr.sdk.model.IDCardParams
import com.baidu.ocr.ui.camera.CameraActivity
import com.blankj.utilcode.util.PathUtils
import com.maple.baiduocr.R
import com.maple.baiduocr.utils.RecognizeService
import java.io.File

class BankCardActivity:AppCompatActivity(), View.OnClickListener {

    private var tvContent: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bank_card)
        tvContent = this.findViewById(R.id.tv_content)
        this.findViewById<Button>(R.id.btn_bankcard)?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_bankcard ->{
                openBankcard()
            }
        }
    }

    private val filePath = PathUtils.getExternalAppFilesPath()
    private val pathCard = filePath+"bank_card.jpg"

    private val REQUEST_CODE_BANKCARD = 111

    private fun openBankcard() {
        val directory: File = File(filePath)
        if(!directory.exists() || directory.isFile){
            directory.mkdir()
        }

        val intent: Intent = Intent(this, CameraActivity::class.java)
        intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,pathCard)
        intent.putExtra(CameraActivity.KEY_CONTENT_TYPE,CameraActivity.CONTENT_TYPE_BANK_CARD)
        startActivityForResult(intent,REQUEST_CODE_BANKCARD)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            when(requestCode){
                // 识别成功回调，银行卡识别
                REQUEST_CODE_BANKCARD ->{
                    RecognizeService.recBankCard(this,pathCard,object :RecognizeService.ServiceListener{
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