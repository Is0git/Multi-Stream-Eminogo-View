package com.android.eminogoview

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.graphics.drawable.VectorDrawable
import android.graphics.drawable.shapes.Shape
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
    lateinit var lineBitmap: Bitmap

    constructor(context: Context?) : super(context) {init(context)}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){init(context)}
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {init(context)}

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
        circleDrawable = context?.resources?.getDrawable(R.drawable.ic_circle) as VectorDrawable
        lineDrawable = context.resources?.getDrawable(R.drawable.ic_lines_vector) as VectorDrawable


    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        circleBitMap = getBitmap(circleDrawable, width-450, height-450)!!
        lineBitmap = getBitmap(lineDrawable, width, height)!!
        canvas?.drawBitmap(lineBitmap, 0f,0f, circlePaint)
        canvas?.drawBitmap(circleBitMap, 250f,170f, circlePaint)

    }
}