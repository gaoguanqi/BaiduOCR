package com.maple.baiduocr.utils

import android.util.Log

class LogUtils {
    companion object {
        private const val ISDEBUG:Boolean = true
        @JvmStatic
        fun logGGQ(s: String?) {
            if (ISDEBUG) {
                Log.i("GGQ", "${s}")
            }
        }
    }
}