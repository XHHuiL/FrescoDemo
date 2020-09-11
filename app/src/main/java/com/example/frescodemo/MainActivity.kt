package com.example.frescodemo

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.common.ResizeOptions
import com.facebook.imagepipeline.request.ImageRequest
import com.facebook.imagepipeline.request.ImageRequestBuilder

class MainActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        val TAG = "MainActivity"
        val uriString = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1599476519923&di=562cc1350c0d8c3fda63d8ff075cacae&imgtype=0&src=http%3A%2F%2Fattach.bbs.miui.com%2Fforum%2F201311%2F17%2F174124tp3sa6vvckc25oc8.jpg"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.big_image_bt).setOnClickListener(this)

        val iv = findViewById<SimpleDraweeView>(R.id.iv)
        val uri = Uri.parse(BigImageActivity.uriString)

        val request = ImageRequestBuilder.newBuilderWithSource(uri)
            .setResizeOptions(ResizeOptions(480, 300))
            .setLowestPermittedRequestLevel(ImageRequest.RequestLevel.BITMAP_MEMORY_CACHE)
            .build()
        val controller = Fresco.newDraweeControllerBuilder()
            .setOldController(iv.controller)
            .setImageRequest(request)
            .build()
        iv.controller = controller
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.big_image_bt -> {
                val intent = Intent(this, BigImageActivity::class.java)
                startActivity(intent)
            }
        }
    }
}