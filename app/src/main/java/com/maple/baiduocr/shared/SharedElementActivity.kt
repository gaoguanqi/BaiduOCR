package com.maple.baiduocr.shared

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.maple.baiduocr.R
import com.maple.baiduocr.shared.adapter.SharedAdapter


class SharedElementActivity : AppCompatActivity() {

    private lateinit var rvList: RecyclerView
    private val sharedAdapter by lazy { SharedAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_element)

        val list = java.util.ArrayList<String>()
        list.add("http://oss-public.hyntech.net/appUpload/20201202/2018LENoOyAYmq/f6dfa0aad1dc4672b2725b55546f147e.jpg")
        list.add("http://oss-public.hyntech.net/appUpload/20201202/2018LENoOyAYmq/41fd054c25ea40ec80b621215117d0b7.jpg")
        list.add("http://oss-public.hyntech.net/appUpload/20201202/2018LENoOyAYmq/7f6dbad84fbe4c498f147f53468edb63.jpg")
        list.add("http://oss-public.hyntech.net/appUpload/20201202/2018LENoOyAYmq/6ab148ac98f34e738122aa8e38b9947a.jpg")
        list.add("http://oss-public.hyntech.net/appUpload/20201202/2018LENoOyAYmq/6ab148ac98f34e738122aa8e38b9947a.jpg")
        list.add("http://oss-public.hyntech.net/appUpload/20201202/2018LENoOyAYmq/6ab148ac98f34e738122aa8e38b9947a.jpg")
        list.add("http://oss-public.hyntech.net/appUpload/20201202/2018LENoOyAYmq/6ab148ac98f34e738122aa8e38b9947a.jpg")
        list.add("http://oss-public.hyntech.net/appUpload/20201202/2018LENoOyAYmq/6ab148ac98f34e738122aa8e38b9947a.jpg")


        rvList = this.findViewById(R.id.rv_list)
        rvList.layoutManager = GridLayoutManager(this, 2)
        rvList.adapter = sharedAdapter
        sharedAdapter.setData(list)
        sharedAdapter.setListener(object : SharedAdapter.OnClickListener {
            override fun onItemClick(view: View, pos: Int, item: String?) {

                item?.let {
                    val intent: Intent = Intent(this@SharedElementActivity, PreviewActivity::class.java)
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                        val pair:androidx.core.util.Pair<View, String> = androidx.core.util.Pair(view,pos.toString())

                        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                            this@SharedElementActivity,
                            pair
                        )
                        val bundle = Bundle()
                        bundle.putSerializable("list",list)
                        bundle.putInt("index",pos)
                        intent.putExtras(bundle)
                        startActivity(intent, options.toBundle())
                    }else{
                        startActivity(intent)
                    }
                }
            }
        })
    }
}