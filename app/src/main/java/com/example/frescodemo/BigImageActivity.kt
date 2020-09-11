package com.example.frescodemo

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.common.ResizeOptions
import com.facebook.imagepipeline.request.ImageRequest
import com.facebook.imagepipeline.request.ImageRequestBuilder

class BigImageActivity : AppCompatActivity() {

    companion object {
        val TAG = "BigImageActivity"
        val uriString = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1599476519923&di=562cc1350c0d8c3fda63d8ff075cacae&imgtype=0&src=http%3A%2F%2Fattach.bbs.miui.com%2Fforum%2F201311%2F17%2F174124tp3sa6vvckc25oc8.jpg"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_big_image)

        val iv = findViewById<SimpleDraweeView>(R.id.iv)
        val uri = Uri.parse(uriString)

        val imagePipeline = Fresco.getImagePipeline()
        Log.i(TAG, "is in memory: ${imagePipeline.isInBitmapMemoryCache(uri)}")
        Log.i(TAG, "is in disk: ${imagePipeline.isInDiskCacheSync(uri)}")

        iv.setOnClickListener {
            imagePipeline.clearDiskCaches()
            imagePipeline.clearMemoryCaches()
        }

        // resizing虽然显示效果不佳，但是不耗内存
        val request = ImageRequestBuilder.newBuilderWithSource(uri)
            .setResizeOptions(ResizeOptions(480, 300))
            .setLowestPermittedRequestLevel(ImageRequest.RequestLevel.DISK_CACHE)
            .build()
        val controller = Fresco.newDraweeControllerBuilder()
            .setOldController(iv.controller)
            .setImageRequest(request)
            .build()
        iv.controller = controller
    }
}