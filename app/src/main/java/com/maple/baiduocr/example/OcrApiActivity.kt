package com.maple.baiduocr.example

import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.FileUtils
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.SPUtils
import com.blankj.utilcode.util.ToastUtils
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.listener.OnResultCallbackListener
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.Response
import com.maple.baiduocr.R
import com.maple.baiduocr.config.Config
import com.maple.baiduocr.entity.IDCardEntity
import com.maple.baiduocr.entity.TokenEntity
import com.maple.baiduocr.entity.UploadEntity
import com.maple.baiduocr.ext.loadImage
import com.maple.baiduocr.utils.LogUtils
import com.maple.baiduocr.widget.dialog.PictureOptionDialog
import com.maple.baiduocr.widget.picture.GlideEngine
import java.io.File
import java.lang.StringBuilder

class OcrApiActivity : AppCompatActivity() {
    private val AppID: String = "23590854"
    private val APIKey: String = "hM1smModD5gMPoHvkOGDYLfq"
    private val SecretKey: String = "suZV9GdAkcBUoG0fsPEz7B7qOSeOoyWk"
    private var accessToken: String? = ""


    private lateinit var tvToken: TextView
    private lateinit var ivIDCardA: ImageView
    private lateinit var tvInfo: TextView


    private val pictureDialog by lazy {
        PictureOptionDialog(this, object : PictureOptionDialog.OnClickListener {
            override fun onCameraClick(type: Int, tag: Int) {
                //打开相机
                openCamer(type, tag)
            }

            override fun onPhotoClick(type: Int, tag: Int) {
                //打开相册
                openPhoto(type, tag)
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ocr_api)
        tvToken = findViewById(R.id.tv_token)
        this.findViewById<Button>(R.id.btn_token).setOnClickListener { onClickToken() }

        ivIDCardA = findViewById(R.id.iv_idcard_a)
        this.findViewById<Button>(R.id.btn_idcard_a).setOnClickListener {
            pictureDialog.setTag(PictureOptionDialog.TAG_A).show()
        }

        tvInfo = findViewById(R.id.tv_info)
    }


    private fun onClickToken() {
        getAuth()
    }


    private fun getAuth() {
        val token = SPUtils.getInstance().getString("token")
        if (TextUtils.isEmpty(token)) {
            OkGo.post<String>(Config.OCR_URL + "/oauth/2.0/token")
                .tag(this)
                .params("grant_type", "client_credentials")
                .params("client_id", APIKey)
                .params("client_secret", SecretKey)
                .execute(object : StringCallback() {
                    override fun onSuccess(response: Response<String>?) {
                        try {
                            response?.let {
                                val result = it.body()
                                result?.let { data ->
                                    LogUtils.logGGQ("data：${data}")
                                    val entity = GsonUtils.fromJson(data, TokenEntity::class.java)
                                    entity?.let { e ->
                                        val token = e.access_token
                                        if (!TextUtils.isEmpty(token)) {
                                            tvToken.text = "token:${token}"
                                            SPUtils.getInstance().put("token", token)
                                            accessToken = token
                                        }
                                    }
                                }
                            }
                        } catch (e: Exception) {
                            e.stackTrace
                            LogUtils.logGGQ("异常：${e.message}")
                        }
                    }
                })
        } else {
            accessToken = token
            tvToken.text = "token:${accessToken}"
        }
    }


    private fun openCamer(type: Int, tag: Int) {
        showToast("访问相机")
        PictureSelector.create(this)
            .openCamera(PictureMimeType.ofImage())
            .imageEngine(GlideEngine.createGlideEngine())
            .isCompress(true)// 是否压缩
            .forResult(object : OnResultCallbackListener<LocalMedia> {
                override fun onResult(result: MutableList<LocalMedia>?) {
                    result?.last()?.let {
                        var compressPath: String? = ""
                        if (it.isCompressed && !TextUtils.isEmpty(it.compressPath))
                            compressPath = it.compressPath
                        else {
                            compressPath = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                                it.androidQToPath
                            } else {
                                it.path
                            }
                        }
                        //上传图片
                        if (!TextUtils.isEmpty(compressPath)) {
                            //uploadImage(compressPath)
                            showToast("type->${type}-图片大小：${FileUtils.getSize(compressPath)}")
                            LogUtils.logGGQ("path->${compressPath}")
                            if (tag == PictureOptionDialog.TAG_A) {
                                ivIDCardA.loadImage(this@OcrApiActivity, compressPath)
                            } else if (tag == PictureOptionDialog.TAG_B) {

                            }
                            idcardOCR(compressPath)
                        } else {
                            showToast("拍照出错,请重新拍照！")
                        }
                    } ?: let {
                        showToast("拍照出错,请重新拍照！")
                    }
                }

                override fun onCancel() {
                    showToast("取消")
                }
            })
    }

    private fun openPhoto(type: Int, tag: Int) {
        showToast("访问相册")
        PictureSelector.create(this)
            .openGallery(PictureMimeType.ofImage())
            .imageEngine(GlideEngine.createGlideEngine())
            .selectionMode(PictureConfig.SINGLE)
            .isCamera(false)
            .isCompress(true)// 是否压缩
            .isGif(false)
            .forResult(object : OnResultCallbackListener<LocalMedia> {
                override fun onResult(result: MutableList<LocalMedia>?) {
                    result?.last()?.let {
                        var compressPath: String? = ""
                        if (it.isCompressed && !TextUtils.isEmpty(it.compressPath))
                            compressPath = it.compressPath
                        else {
                            compressPath = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                                it.androidQToPath
                            } else {
                                it.path
                            }
                        }
                        //上传图片
                        if (!TextUtils.isEmpty(compressPath)) {
                            showToast("type->${type}-图片大小：${FileUtils.getSize(compressPath)}")
                            LogUtils.logGGQ("path->${compressPath}")
                            if (tag == PictureOptionDialog.TAG_A) {
                                ivIDCardA.loadImage(this@OcrApiActivity, compressPath)
                            } else if (tag == PictureOptionDialog.TAG_B) {

                            }
                            idcardOCR(compressPath)
                        } else {
                            showToast("选择照片出错,请重新选择！")
                        }
                    } ?: let {
                        showToast("选择照片出错,请重新选择！")
                    }
                }

                override fun onCancel() {
                    showToast("取消")
                }
            })
    }


    //http://imaple.vip:3000/banner
    //http://imaple.vip:3000/album/newest

    private fun idcardOCR(path: String) {
        val file = File(path)
        OkGo.post<String>("https://padc.tangdoush.com/api/user/uploadImageFile")
            .tag(this)
            .params("file", file)
            .params(
                "token",
                "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MTY3Mjg4MDIsIm5iZiI6MTYxNjcyODgwMiwiZXhwIjoxNjE5MzIwODAyLCJ1c2VyX2lkIjoiNGExODUzNDU4M2M0MTFlYmI0NzcwMDE2M2UwMzExZDUifQ.rTaDjQsCQycaryZxPDHCUcqC592VDTQX74IFiKArrnU"
            )
            .isMultipart(true)
            .execute(object : StringCallback() {
                override fun onSuccess(response: Response<String>?) {
                    try {
                        response?.let {
                            val result = it.body()
                            result?.let { data ->
                                LogUtils.logGGQ("data：${data}")
                                val entity = GsonUtils.fromJson(data, UploadEntity::class.java)
                                entity?.let { e ->
                                    if (e.code == 1) {
                                        val imgURL = e.data?.src
                                        if (!TextUtils.isEmpty(imgURL)) {
                                            ocrImg("https://padc.tangdoush.com" + imgURL)
                                        }
                                    }
                                }
                            }
                        }
                    } catch (e: Exception) {
                        e.stackTrace
                        LogUtils.logGGQ("异常：${e.message}")
                    }
                }
            })
    }

    private fun ocrImg(imgURL: String) {
        if(TextUtils.isEmpty(accessToken)){
            showToast("先获取token")
            return
        }

        OkGo.post<String>(Config.OCR_URL + "/rest/2.0/ocr/v1/idcard")
            .tag(this)
            .params("id_card_side", "front")
            .params("url", imgURL)
            .params("access_token", accessToken)
            .execute(object : StringCallback() {
                override fun onSuccess(response: Response<String>?) {
                    try {
                        response?.let {
                            val result = it.body()
                            result?.let { data ->
                                LogUtils.logGGQ("data：${data}")
                                val entity = GsonUtils.fromJson(data, IDCardEntity::class.java)
                                entity?.let { e ->
                                    e.words_result?.let { res ->
                                        val name = res.姓名?.words
                                        val address = res.住址?.words
                                        val idNo = res.公民身份号码?.words
                                        val gender = res.性别?.words
                                        val birthday = res.出生?.words

                                        LogUtils.logGGQ("姓名：${name}")
                                        LogUtils.logGGQ("住址：${address}")
                                        LogUtils.logGGQ("身份证号：${idNo}")
                                        LogUtils.logGGQ("性别：${gender}")
                                        LogUtils.logGGQ("出生：${birthday}")

                                        val sp = StringBuilder()
                                        sp.append("姓名：${name}")
                                        sp.append("\n")
                                        sp.append("住址：${address}")
                                        sp.append("\n")
                                        sp.append("身份证号：${idNo}")
                                        sp.append("\n")
                                        sp.append("性别：${gender}")
                                        sp.append("\n")
                                        sp.append("出生：${birthday}")
                                        sp.append("\n")
                                        tvInfo.text = sp.toString()
                                    }
                                }
                            }
                        }
                    } catch (e: Exception) {
                        e.stackTrace
                        LogUtils.logGGQ("异常：${e.message}")
                    }
                }
            })
    }


    private fun showToast(s: String?) {
        ToastUtils.showShort(s)
    }
}