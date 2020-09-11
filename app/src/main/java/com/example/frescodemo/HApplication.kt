package com.example.frescodemo

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.core.ImagePipelineConfig

class HApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // Fresco初始化
        val  builder = ImagePipelineConfig.newBuilder(this)
            .setDownsampleEnabled(true)

        Fresco.initialize(this, builder.build())
    }

}