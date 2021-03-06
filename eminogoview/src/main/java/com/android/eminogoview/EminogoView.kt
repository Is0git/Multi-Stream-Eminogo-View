package com.android.eminogoview

import android.content.Context
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.VectorDrawable
import android.os.Build
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import com.android.getBitmap

class EminogoView : View {

    lateinit var circlePaint: Paint
    lateinit var circleBitMap: Bitmap
    lateinit var circleDrawable: VectorDrawable
    lateinit var lineDrawable: VectorDrawable
    lateinit var twitchDrawable: VectorDrawable
    lateinit var lineBitmap: Bitmap
    lateinit var twitchBitmap: Bitmap
    lateinit var twitchPaint: Paint
    lateinit var underCirclePaint: Paint
    lateinit var linesPaint: Paint
    lateinit var strokedCircle: Paint
    var circleAlpha = 0
    val circleSizeRatio = 0.75f

    var logoAlpha = 0
    var logoHeightSizeRatio = 0.70f
    var logoWidthSizeRatio = 1f
    var logoOffsetRatio = 0.50f

    var linesAlpha = 0
    var linesX = 0f
    var linesY = 0f

    var startAngle = -45f
    var sweepAngle = 180f

    var strokedCircleSweepAngle = 0f

    var circleDrawableId = R.drawable.ic_circle
    var lineDrawableId = R.drawable.ic_lines
    var logoDrawableId = R.drawable.ic_twitch_logo
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    set(value) {
        field = value
        twitchDrawable = ResourcesCompat.getDrawable(resources, logoDrawableId, null) as VectorDrawable
        twitchBitmap = getBitmap(twitchDrawable, (width *logoWidthSizeRatio).toInt(), (height *logoHeightSizeRatio).toInt())!!
    }

    constructor(context: Context?) : super(context) {
        init(context)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs)
    }

    private fun init(context: Context?, attrs: AttributeSet? = null) {

        if (attrs != null) {
            context?.obtainStyledAttributes(attrs, R.styleable.EminogoView)?.apply {

                recycle()
            }
        }

        underCirclePaint = Paint().apply {
            color = ResourcesCompat.getColor(resources, R.color.colorSurface, null)
        }

        circlePaint = Paint().apply {
            alpha = circleAlpha

        }
        twitchPaint = Paint().apply {
            alpha = logoAlpha

        }

        linesPaint = Paint().apply {
            alpha = linesAlpha
        }

        strokedCircle = Paint().apply {
            color = ResourcesCompat.getColor(resources, R.color.colorSurface, null)
            style = Paint.Style.STROKE
            strokeWidth = convertDpToPixel(10f, getContext())
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        val widthSize = (MeasureSpec.getSize(widthMeasureSpec) * 0.80f).toInt()
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setMeasuredDimension(heightSize, heightSize)
        } else {
            setMeasuredDimension(widthSize, widthSize)
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        circleDrawable = ResourcesCompat.getDrawable(resources, circleDrawableId, null) as VectorDrawable
        lineDrawable = ResourcesCompat.getDrawable(resources, lineDrawableId, null) as VectorDrawable
        twitchDrawable = ResourcesCompat.getDrawable(resources, logoDrawableId, null) as VectorDrawable

        circleBitMap = getBitmap(circleDrawable, (width *circleSizeRatio).toInt(), (height *circleSizeRatio).toInt())!!
        lineBitmap = getBitmap(lineDrawable, width, height)!!
        twitchBitmap = getBitmap(twitchDrawable, (width * logoWidthSizeRatio).toInt(), (height * logoHeightSizeRatio).toInt())!!


    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val midX = width / 2f
        val midY = height / 2f

        val circleOffSetX = midX - (width *circleSizeRatio) /2f
        val circleOffSetY = midY - (height *circleSizeRatio) /2f

        val logoOffSetX = midX - (width *logoWidthSizeRatio) /2f
        val logoOffSetY = midY - (height *logoHeightSizeRatio) /2f

        linesPaint.alpha = linesAlpha
        twitchPaint.alpha = logoAlpha
        circlePaint.alpha = circleAlpha
        canvas?.drawArc(40f, 40f, width.toFloat() * 1f *0.90f, height.toFloat() * 1f*0.90f, 0f, strokedCircleSweepAngle, false, strokedCircle)
        canvas?.drawArc(0f, 0f, width.toFloat() * 1f, height.toFloat() * 1f, startAngle, sweepAngle, true, underCirclePaint)
        canvas?.drawBitmap(lineBitmap, linesX, linesY, linesPaint)
        canvas?.drawBitmap(circleBitMap, circleOffSetX, circleOffSetY, circlePaint)
        canvas?.drawBitmap(twitchBitmap, logoOffSetX, logoOffSetY * logoOffsetRatio, twitchPaint)

    }

    fun convertDpToPixel(dp: Float, context: Context): Float {
        return dp * (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }

}
