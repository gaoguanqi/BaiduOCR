package com.maple.baiduocr.example

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.baidu.ocr.sdk.OCR
import com.baidu.ocr.sdk.OnResultListener
import com.baidu.ocr.sdk.exception.OCRError
import com.baidu.ocr.sdk.model.IDCardParams
import com.baidu.ocr.sdk.model.IDCardResult
import com.baidu.ocr.ui.camera.CameraActivity
import com.baidu.ocr.ui.camera.CameraNativeHelper
import com.baidu.ocr.ui.camera.CameraView
import com.blankj.utilcode.util.ImageUtils
import com.blankj.utilcode.util.PathUtils
import com.maple.baiduocr.R
import java.io.File

class IDCardActivity :AppCompatActivity(), View.OnClickListener {
    private var ivImg: ImageView? = null
    private var tvContent:TextView? = null
    private val type:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_id_card)

        ivImg = this.findViewById(R.id.iv_img)
        tvContent = this.findViewById(R.id.tv_content)
        this.findViewById<Button>(R.id.btn_idCardA)?.setOnClickListener(this)
        this.findViewById<Button>(R.id.btn_idCardB)?.setOnClickListener(this)

        //  初始化本地质量控制模型,释放代码在onDestory中
        //  调用身份证扫描必须加上 intent.putExtra(CameraActivity.KEY_NATIVE_MANUAL, true); 关闭自动初始化和释放本地模型
        CameraNativeHelper.init(this,OCR.getInstance(this).license,object :CameraNativeHelper.CameraNativeInitCallback{
            override fun onError(errorCode: Int, e: Throwable?) {
                val msg = when(errorCode){
                    CameraView.NATIVE_SOLOAD_FAIL -> "加载so失败，请确保apk中存在ui部分的so"
                    CameraView.NATIVE_AUTH_FAIL -> "授权本地质量控制token获取失败"
                    CameraView.NATIVE_INIT_FAIL -> "本地质量控制"
                    else -> errorCode.toString()
                }
                tvContent?.text = "本地质量控制初始化错误，错误原因： ${msg}"
            }
        })
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_idCardA ->{
                openIdCardA()
            }
            R.id.btn_idCardB ->{
                openIdCardB()
            }
        }
    }

    private val filePath = PathUtils.getExternalAppFilesPath()
    private val pathA = filePath+"idcard_a.jpg"
    private val pathB = filePath+"idcard_b.jpg"

    private val REQUEST_CODE_CAMERA = 102

    private fun openIdCardA() {
        val directory:File = File(filePath)
        if(!directory.exists() || directory.isFile){
            directory.mkdir()
        }

        val intent:Intent = Intent(this,CameraActivity::class.java)
        intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,pathA)
        intent.putExtra(CameraActivity.KEY_NATIVE_ENABLE,true)
        intent.putExtra(CameraActivity.KEY_NATIVE_MANUAL,true)
        intent.putExtra(CameraActivity.KEY_CONTENT_TYPE,CameraActivity.CONTENT_TYPE_ID_CARD_FRONT)
        intent.putExtra(CameraActivity.KEY_CAMERA_TYPE, type)
        startActivityForResult(intent,REQUEST_CODE_CAMERA)
    }

    private fun openIdCardB() {
        val directory:File = File(filePath)
        if(!directory.exists() || directory.isFile){
            directory.mkdir()
        }

        val intent:Intent = Intent(this,CameraActivity::class.java)
        intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,pathB)
        intent.putExtra(CameraActivity.KEY_NATIVE_ENABLE,true)
        intent.putExtra(CameraActivity.KEY_NATIVE_MANUAL,true)
        intent.putExtra(CameraActivity.KEY_CONTENT_TYPE,CameraActivity.CONTENT_TYPE_ID_CARD_BACK)
        intent.putExtra(CameraActivity.KEY_CAMERA_TYPE, type)
        startActivityForResult(intent,REQUEST_CODE_CAMERA)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            when(requestCode){
                REQUEST_CODE_CAMERA ->{
                    data?.let {
                        val contentType = it.getStringExtra(CameraActivity.KEY_CONTENT_TYPE)
                        if(!TextUtils.isEmpty(contentType)){
                            if(TextUtils.equals(contentType,CameraActivity.CONTENT_TYPE_ID_CARD_FRONT)){
                                //正面
                                recIDCard(IDCardParams.ID_CARD_SIDE_FRONT,pathA)
                            }else if(TextUtils.equals(contentType,CameraActivity.CONTENT_TYPE_ID_CARD_BACK)){
                                //反面
                                recIDCard(IDCardParams.ID_CARD_SIDE_BACK,pathB)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun recIDCard(idCardSide: String, filePath: String) {
        val param:IDCardParams = IDCardParams()
        param.imageFile = File(filePath)
        // 设置身份证正反面
        param.idCardSide = idCardSide
        // 设置方向检测
        param.isDetectDirection = true
        // 设置图像参数压缩质量0-100, 越大图像质量越好但是请求时间越长。 不设置则默认值为20
        param.imageQuality = 20

        ivImg?.setImageBitmap(ImageUtils.getBitmap(filePath))
        OCR.getInstance(this).recognizeIDCard(param,object : OnResultListener<IDCardResult>{
            override fun onResult(result: IDCardResult?) {
                result?.let {
                    tvContent?.text = it.toString()
                }
            }

            override fun onError(error: OCRError?) {
                Toast.makeText(this@IDCardActivity,"${error?.message}",Toast.LENGTH_SHORT).show()
            }
        })
    }


    override fun onDestroy() {
        //释放本地质量控制模型
        CameraNativeHelper.release()
        super.onDestroy()
    }

}