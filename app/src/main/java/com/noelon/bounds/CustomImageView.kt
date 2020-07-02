package com.noelon.bounds

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import com.github.chrisbanes.photoview.PhotoView
import java.util.jar.Attributes

class CustomImageView constructor(context: Context, attr: AttributeSet? = null) :
    androidx.appcompat.widget.AppCompatImageView(context,attr) {

    private val paint = Paint()
    private val vPin = PointF()
    private var sPin: PointF? = null
    private var pin: Bitmap

    init {
        val density = resources.displayMetrics.densityDpi.toFloat()
        val bitmap = BitmapFactory.decodeResource(this.resources, R.drawable.pushpin_green)
        val w = density / 420f * bitmap.width
        val h = density / 420f * bitmap.height
        pin = Bitmap.createScaledBitmap(bitmap, w.toInt(), h.toInt(), true)

    }

    fun setPin(cord: PointF) {
        this.sPin = cord
        invalidate()
    }




    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (sPin != null) {
            val vX = sPin!!.x - pin.width / 2
            val vY = sPin!!.y - pin.height
            canvas?.drawBitmap(pin, vX, vY, paint)
        }

    }
}