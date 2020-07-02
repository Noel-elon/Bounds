package com.noelon.bounds


import android.annotation.SuppressLint
import android.graphics.PointF
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    var pinX: Float? = null
    var pinY: Float? = null

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val originalContainer = ZoomviewContainer
        val smallImageView = smallImageContainer
        val parentLayout = parentLayout






        showDetails.setOnClickListener {

            val displayRect = originalContainer.displayRect
            val displaywidth = displayRect.width()
            val originalContainerWidth = originalContainer.width
            var smallImageWidth = smallImageView.width
            val originalContainerHeight = originalContainer.height
            val displayheight = displayRect.height()
            var parentLayoutWidth = parentLayout.width
            var parentLayoutHeight = parentLayout.height
            var smallImageHeight = smallImageView.height


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


//            smallImageView.minimumWidth = smallImageWidth
//            smallImageView.minimumHeight = smallImageHeight

            val params = smallImageView.layoutParams
            params.height = smallImageHeight
            params.width = smallImageWidth
            smallImageView.layoutParams = params

            val scrollX = relativeLeft * parentLayoutWidth
            val scrollY = relativeTop * parentLayoutHeight
            smallImageView.scrollX = -scrollX.toInt()
            smallImageView.scrollY = -scrollY.toInt()

            if (smallViewPinX != null && smallViewPinY != null) {

                pin_image_view.x = smallViewPinX
                pin_image_view.y = smallViewPinY
            }

//
//            parentLayout.scrollX = scrollX.toInt()
//            parentLayout.scrollY = scrollY.toInt()


            // customview2.width = relativeWidth * parentLayout.width
            // ScrollX = relativeLeft * parentLayout.width


            Log.d("ImageViewWidth ", smallImageHeight.toString())
            Log.d("Display Rect ", smallImageWidth.toString())


        }

        originalContainer.setOnViewTapListener { view, x, y ->
           // originalContainer.setPin(PointF(x, y))
            zoom_pin_view.x = x
            zoom_pin_view.y = y
            Toast.makeText(this, "$x and $y", Toast.LENGTH_SHORT).show()
            pinX = x
            pinY = y


        }

    }


}