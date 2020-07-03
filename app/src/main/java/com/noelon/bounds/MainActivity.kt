package com.noelon.bounds


import android.annotation.SuppressLint
import android.graphics.PointF
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.github.chrisbanes.photoview.PhotoView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    var pinX: Float? = null
    var pinY: Float? = null

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val linearLayout = LinearLayout(this)
        val bigCropFrameLayout = FrameLayout(this)
        val photoView = PhotoView(this)
        val bigPinImageView = ImageView(this)
        val button = Button(this)
        val smallCropFrameLayout = FrameLayout(this)
        val smallCropView = ImageView(this)
        val smallPinImageView = ImageView(this)

        val linearParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.layoutParams = linearParams

        val bigFrameParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.WRAP_CONTENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
        )
        bigCropFrameLayout.layoutParams = bigFrameParams
        val photoViewParams = FrameLayout.LayoutParams(
            300,
            200
        )
        photoViewParams.setMargins(8, 8, 8, 8)
        photoView.layoutParams = photoViewParams
        photoView.setImageResource(R.drawable.landscape)

        val bigPinParams = FrameLayout.LayoutParams(
            50,
            50
        )
        bigPinImageView.layoutParams = bigPinParams
        bigPinImageView.setImageResource(R.drawable.pushpin_green)

        bigCropFrameLayout.addView(photoView)
        bigCropFrameLayout.addView(bigPinImageView)

        val buttonParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        button.layoutParams = buttonParams
        button.text = "Show Details"

        val smallFrameParams = FrameLayout.LayoutParams(
            150,
            100
        )
        smallCropFrameLayout.clipChildren = true
        smallCropFrameLayout.layoutParams = smallFrameParams

        val smallImageViewParams = FrameLayout.LayoutParams(
            150,
            100
        )
        smallCropView.setImageResource(R.drawable.landscape)
        smallCropView.layoutParams = smallImageViewParams

        val smallPinParams = FrameLayout.LayoutParams(
            20,
            20
        )
        smallPinImageView.layoutParams = smallPinParams
        smallPinImageView.setImageResource(R.drawable.pushpin_green)

        smallCropFrameLayout.addView(smallCropView)
        smallCropFrameLayout.addView(smallPinImageView)

        linearLayout.addView(bigCropFrameLayout)
        linearLayout.addView(button)
        linearLayout.addView(smallCropFrameLayout)

        setContentView(linearLayout)

        button.setOnClickListener {

            val displayRect = photoView.displayRect
            val displaywidth = displayRect.width()
            val originalContainerWidth = photoView.width
            var smallImageWidth = smallCropView.width
            val originalContainerHeight = photoView.height
            val displayheight = displayRect.height()
            var parentLayoutWidth = smallCropFrameLayout.width
            var parentLayoutHeight = smallCropFrameLayout.height
            var smallImageHeight = smallCropView.height

            val relativeWidth = displaywidth / originalContainerWidth
            val relativeHeight = displayheight / originalContainerHeight
            val relativeLeft = displayRect.left / originalContainerWidth
            val relativeTop = displayRect.top / originalContainerHeight

            smallImageWidth = (relativeWidth * parentLayoutWidth).toInt()
            smallImageHeight = (relativeHeight * parentLayoutHeight).toInt()

            val pinRelativeX = pinX?.div(originalContainerWidth)
            val pinRelativeY = pinY?.div(originalContainerHeight)

            val smallViewPinX = pinRelativeX?.times(parentLayoutWidth)
            val smallViewPinY = pinRelativeY?.times(parentLayoutHeight)

            Toast.makeText(this, "$smallViewPinX and $smallViewPinY", Toast.LENGTH_SHORT).show()

            val params = smallCropView.layoutParams
            params.height = smallImageHeight
            params.width = smallImageWidth
            smallCropView.layoutParams = params

            val scrollX = relativeLeft * parentLayoutWidth
            val scrollY = relativeTop * parentLayoutHeight
            smallCropView.scrollX = -scrollX.toInt()
            smallCropView.scrollY = -scrollY.toInt()

            if (smallViewPinX != null && smallViewPinY != null) {

                smallPinImageView.x = smallViewPinX
                smallPinImageView.y = smallViewPinY
            }

            Log.d("ImageViewWidth ", smallImageHeight.toString())
            Log.d("Display Rect ", smallImageWidth.toString())
        }

        photoView.setOnViewTapListener { view, x, y ->
            // originalContainer.setPin(PointF(x, y))
            bigPinImageView.x = x
            bigPinImageView.y = y
            Toast.makeText(this, "$x and $y", Toast.LENGTH_SHORT).show()
            pinX = x
            pinY = y

        }
    }
}