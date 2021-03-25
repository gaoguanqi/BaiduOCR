package com.maple.baiduocr.shared

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import com.maple.baiduocr.R
import com.maple.baiduocr.shared.adapter.PreviewAdapter

class PreviewActivity : AppCompatActivity() {
    private var vp:ViewPager2? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview)
        vp = findViewById(R.id.vp)

        this.intent?.extras?.let {
            val list = it.getSerializable("list") as? List<String>
            val index: Int = it.getInt("index", 0)
            if(!list.isNullOrEmpty()){
                vp?.adapter = PreviewAdapter(this).apply {
                    this.setData(list)
                }
                vp?.setCurrentItem(index,false)
                vp?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)

                    }
                })
            }

        }
    }
}