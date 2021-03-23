package com.maple.baiduocr.app

import android.app.Application
import android.os.Handler
import android.os.Looper
import com.baidu.ocr.sdk.OCR
import com.baidu.ocr.sdk.OnResultListener
import com.baidu.ocr.sdk.exception.OCRError
import com.baidu.ocr.sdk.model.AccessToken
import com.blankj.utilcode.util.Utils
import com.maple.baiduocr.utils.LogUtils
import com.maple.ximageloader.ImageLoader
import com.maple.ximageloader.glide.MyAppGlideModule


class MyApplication : Application() {
    companion object {
        @JvmStatic
        lateinit var instance: MyApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        Utils.init(this)
        initSDK()
    }

    private fun initSDK() {


        // 延时执行
//        Handler(Looper.getMainLooper()).postDelayed(Runnable {
//            OCR.getInstance(this).initAccessToken(object : OnResultListener<AccessToken> {
//                override fun onResult(accessToken: AccessToken?) {
//                    LogUtils.logGGQ("ocr token ->>${accessToken?.accessToken}")
//                }
//
//                override fun onError(ocrError: OCRError?) {
//                    LogUtils.logGGQ("ocr error ->>${ocrError?.errorCode}")
//                }
//            }, "aip.license",applicationContext)
//        }, 500)
    }
}