package com.android.eminogoview

import android.content.Context
import android.content.res.Configuration
import android.graphics.*
import android.graphics.drawable.VectorDrawable
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi
import com.android.getBitmap


class EminogoView : View {

    lateinit var circleShape: Path
    lateinit var circlePaint: Paint
    lateinit var circleBitMap: Bitmap
    lateinit var circleDrawable: VectorDrawable
    lateinit var lineDrawable: VectorDrawable
    lateinit var twichDrawable: VectorDrawable
    lateinit var lineBitmap: Bitmap
    lateinit var twitchBitmap: Bitmap
    lateinit var twitchPaint: Paint

    val circleSizeRatio = 0.75

    constructor(context: Context?) : super(context) {
        init(context)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun init(context: Context?) {
        circlePaint = Paint().apply {

        }
        twitchPaint = Paint().apply {
            strokeWidth = 20f
            color = Color.WHITE
            style = Paint.Style.STROKE
        }
        circleDrawable = context?.resources?.getDrawable(R.drawable.ic_circle) as VectorDrawable
        lineDrawable = context.resources?.getDrawable(R.drawable.ic_image) as VectorDrawable
        twichDrawable = context.resources?.getDrawable(R.drawable.ic_twitch_logo) as VectorDrawable


    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

//        var width = if (widthMode == MeasureSpec.AT_MOST) widthSize else widthSize
//
//        var height = if (heightMode == MeasureSpec.AT_MOST) widthSize else heightSize

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setMeasuredDimension(heightSize, heightSize)
        } else {
            setMeasuredDimension(widthSize, widthSize)
        }
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        if (::circleBitMap.isInitialized) return
        else {
            circleBitMap = getBitmap(circleDrawable, (width *circleSizeRatio).toInt(), (height *circleSizeRatio).toInt())!!
            lineBitmap = getBitmap(lineDrawable, width, height)!!
            twitchBitmap = getBitmap(twichDrawable, width, height)!!
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val midX = width / 2f
        val midY = height / 2f

        val circleOffSetX = midX - (width *circleSizeRatio) /2f
        val circleOffSetY = midY - (height *circleSizeRatio) /2f
        canvas?.drawBitmap(lineBitmap, 0f, 0f, circlePaint)
        canvas?.drawBitmap(circleBitMap, circleOffSetX.toFloat(), circleOffSetY.toFloat(), circlePaint)


    }
}