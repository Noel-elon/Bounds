package com.noelon.bounds

import android.graphics.Matrix
import android.graphics.RectF
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


        val extra = intent.extras
        val rectf = extra?.get("Rect")

        val matrix = Matrix()
        //matrix.mapRect(rectf as RectF)
        displayview.imageMatrix = matrix
    }
}