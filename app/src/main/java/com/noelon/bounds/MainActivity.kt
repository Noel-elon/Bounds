package com.noelon.bounds


import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
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
            smallImageHeight = (relativeHeight * parentLayout.height).toInt()

            smallImageView.maxWidth = smallImageWidth
            smallImageView.maxHeight = smallImageHeight

//            smallImageView.minimumWidth = smallImageWidth
//            smallImageView.minimumHeight = smallImageHeight

//            val params = parentLayout.layoutParams
//            params.height = -smallImageHeight
//            params.width = -smallImageWidth
//            parentLayout.layoutParams = params

            val scrollX = relativeLeft * parentLayoutWidth
            val scrollY = relativeTop * parentLayoutHeight
            smallImageView.scrollX = scrollX.toInt()
            smallImageView.scrollY = scrollY.toInt()
//
//            parentLayout.scrollX = scrollX.toInt()
//            parentLayout.scrollY = scrollY.toInt()


            // customview2.width = relativeWidth * parentLayout.width
            // ScrollX = relativeLeft * parentLayout.width


            Log.d("ImageViewWidth ", originalContainerWidth.toString())
            Log.d("Display Rect ", displayRect.toString())


        }
    }
}