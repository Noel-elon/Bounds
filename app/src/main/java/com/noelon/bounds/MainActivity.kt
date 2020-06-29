package com.noelon.bounds

import android.graphics.Matrix
import android.graphics.RectF
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.values
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        showDetails.setOnClickListener {
            //getting values (used and unused)
            val rect = customview.drawable.bounds
            val rectf = RectF(rect)
            val displayRect = customview.displayRect
            val scl = customview.scaleX
            val sclY = customview.scaleY
            val imageWidth = customview.drawable.intrinsicWidth
            val imageWidthMin = customview.measuredWidth
            val imageHeight = customview.measuredHeight
            val width = displayRect.width()
            val imageViewWidth1 = customview.width

            //getting matrix for big crop view
            val matrix = customview.imageMatrix
            val floatArray = matrix.values()
            customview.imageMatrix.mapRect(rectf)


            val imageViewWidth2 = customview2.width

            //comparing both imageview widths
            val percentageDecrease =
                (imageViewWidth2.toFloat() / imageViewWidth1.toFloat()) * 100.toFloat()

            val newDisplayWidth = (percentageDecrease / 100) * width


            //getting new scale for small crop view
            val newScale = newDisplayWidth / imageWidth
            val left = displayRect.left
            val top = displayRect.top

            //reducing left and top coordinates
            val newLeft = (percentageDecrease / 100) * left
            val newTop = (percentageDecrease / 100) * top

            //new array for new matrix
            val newFloatArray =
                floatArrayOf(newScale, 0.0f, newLeft, 0.0f, newScale, newTop, 0.0f, 0.0f, 0.1f)

            //getting the displayRect for small crop
            val rect2 = customview2.drawable.bounds
            val displayRect2 = RectF(rect2)
            customview2.imageMatrix.mapRect(displayRect2)

            val scale = customview.scale

            //image width in small crop(same as bigCrop)
            val width2 = customview2.drawable.intrinsicWidth


            val positiveleft = Math.abs(newLeft.toInt())
            customview2.isZoomable = false

            //attempt to offset with scrollX
            customview2.scrollX = positiveleft

            //setting a new matrix
            val mat2 = Matrix()
            mat2.setValues(newFloatArray)
            customview2.imageMatrix = mat2

            //In-app toast used to confirm these values at runtime
            Toast.makeText(
                this,
                "$displayRect and $matrix and $newLeft ",
                Toast.LENGTH_LONG
            )
                .show()


//
//            val intent = Intent(this, SecondActivity::class.java)
//            intent.putExtra("Rect", rectf)
//            startActivity(intent)


        }
    }
}