package com.maple.baiduocr.app

import android.app.Application
import com.blankj.utilcode.util.Utils


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

    //
    private fun initSDK() {

    //原始的
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