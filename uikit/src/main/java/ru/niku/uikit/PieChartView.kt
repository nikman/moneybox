package ru.niku.uikit

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.Typeface
import android.os.Parcelable
import android.text.Layout
import android.text.StaticLayout
import android.text.TextDirectionHeuristic
import android.text.TextDirectionHeuristics
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.annotation.ColorInt
import android.R
import android.content.res.Resources.Theme
import android.util.TypedValue

class PieChartView(context: Context, attributeSet: AttributeSet):
    View(context, attributeSet) {

    companion object {
        private const val DEFAULT_MARGIN_TEXT_1 = 2
        private const val DEFAULT_MARGIN_TEXT_2 = 10
        private const val DEFAULT_MARGIN_TEXT_3 = 2
        private const val DEFAULT_MARGIN_SMALL_CIRCLE = 12
        private const val TEXT_WIDTH_PERCENT = 0.30
        private const val CIRCLE_WIDTH_PERCENT = 0.60
        private const val DEFAULT_VIEW_SIZE_HEIGHT = 150
        private const val DEFAULT_VIEW_SIZE_WIDTH = 250
    }

    private var marginTextFirst: Float = context.dpToPixels(DEFAULT_MARGIN_TEXT_1)
    private var marginTextSecond: Float = context.dpToPixels(DEFAULT_MARGIN_TEXT_2)
    private var marginTextThird: Float = context.dpToPixels(DEFAULT_MARGIN_TEXT_3)
    private var marginSmallCircle: Float = context.dpToPixels(DEFAULT_MARGIN_SMALL_CIRCLE)
    private val marginText: Float = marginTextFirst + marginTextSecond
    private val circleRect = RectF()
    private var circleStrokeWidth: Float = context.dpToPixels(5)
    private var circleRadius: Float = 0F
    private var circlePadding: Float = context.dpToPixels(5)
    private var circleSectionSpace: Float = 0F
    private var circleCenterX: Float = 0F
    private var circleCenterY: Float = 0F
    private var numberTextPaint: TextPaint = TextPaint()
    private var descriptionTextPaint: TextPaint = TextPaint()
    private var amountTextPaint: TextPaint = TextPaint()
    private var textStartX: Float = 0F
    private var textStartY: Float = 0F
    private var textHeight: Int = 0
    private var textCircleRadius: Float = context.dpToPixels(4)
    private var textAmountStr: String = ""
    private var textAmountY: Float = 0F
    private var textAmountXNumber: Float = 0F
    private var textAmountXDescription: Float = 0F
    private var textAmountYDescription: Float = 0F
    private var totalAmount: Double = 0.0
    private var pieChartColors: List<String> = listOf()
    private var percentageCircleList: List<PieChartModel> = listOf()
    private var textRowList: MutableList<StaticLayout> = mutableListOf()
    private var dataList: List<PayLoadModel> = listOf()
    private var animationSweepAngle: Int = 0

    init {

        val typedValue = TypedValue()
        val theme = context.theme
        theme.resolveAttribute(R.attr.colorForeground, typedValue, true)
        @ColorInt val colorForeground = typedValue.data
        theme.resolveAttribute(R.attr.colorForeground, typedValue, true)
        @ColorInt val colorForegroundInverse = typedValue.data

        // Задаем базовые значения и конвертируем в px
        val textAmountSize: Float = context.spToPixels(5)
        val textNumberSize: Float = context.spToPixels(14)
        val textDescriptionSize: Float = context.spToPixels(14)
        val textAmountColor: Int = Color.WHITE
        val textNumberColor: Int = colorForeground
        val textDescriptionColor: Int = colorForegroundInverse // Color.GRAY

        val typeArray = context.obtainStyledAttributes(attributeSet, ru.niku.uiatoms.R.styleable.PieChartView)

        // Секция списка цветов
        val colorResId =
            typeArray.getResourceId(ru.niku.uiatoms.R.styleable.PieChartView_pieChartColors, 0)
        pieChartColors = typeArray.resources.getStringArray(colorResId).toList()

        circlePadding += circleStrokeWidth

        initPains(amountTextPaint, textAmountSize, textAmountColor)
        initPains(numberTextPaint, textNumberSize, textNumberColor)
        initPains(descriptionTextPaint, textDescriptionSize, textDescriptionColor, true)

        typeArray.recycle()

    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        //callbacks = context as Callbacks?
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        //callbacks = null
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        /*if (dataList.isEmpty()) {
            return
        }*/

        textRowList.clear()

        val initSizeWidth = resolveDefaultSize(widthMeasureSpec, DEFAULT_VIEW_SIZE_WIDTH)

        val textTextWidth = (initSizeWidth * TEXT_WIDTH_PERCENT)
        val initSizeHeight = calculateViewHeight(heightMeasureSpec, textTextWidth.toInt())

        textStartX = initSizeWidth - textTextWidth.toFloat()
        textStartY = initSizeHeight.toFloat() / 2 - textHeight / 2

        calculateCircleRadius(initSizeWidth, initSizeHeight)

        setMeasuredDimension(initSizeWidth, initSizeHeight)
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val baseChartState = state as? BaseChartState
        super.onRestoreInstanceState(baseChartState?.superState ?: state)

        dataList = baseChartState?.dataList ?: listOf()
    }

    override fun onSaveInstanceState(): Parcelable {
        val superState = super.onSaveInstanceState()
        return BaseChartState(superState, dataList)
    }

    override fun onDraw(canvas: Canvas) {

        super.onDraw(canvas)

        drawCircle(canvas)
        drawText(canvas)

    }

    private fun initPains(textPaint: TextPaint, textSize: Float, textColor: Int, isDescription: Boolean = false) {
        textPaint.color = textColor
        textPaint.textSize = textSize
        textPaint.isAntiAlias = true

        if (!isDescription) textPaint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
    }

    fun setValues(list: List<PayLoadModel>) {
        dataList = list
        calculatePercentageOfData()
    }

    private fun calculatePercentageOfData() {

        totalAmount = dataList.sumOf { payLoadItem -> payLoadItem.amount }

        var startAt = circleSectionSpace
        percentageCircleList = dataList.mapIndexed { index, payLoad ->
            var percent = payLoad.amount * 100 / totalAmount.toFloat()
            percent = if (percent < 0F) 0.0 else percent

            val resultModel = PieChartModel(
                percentToStartAt = startAt,
                percentOfCircle = percent.toFloat(),
                absPercentOfCircle = startAt + percent.toFloat(),
                colorOfLine = Color.parseColor(pieChartColors[index % pieChartColors.size]),
                valueModel = payLoad
            )
            if (percent.toFloat() != 0F) startAt += percent.toFloat() + circleSectionSpace
            resultModel
        }
    }

    private fun getMultilineText(
        text: CharSequence,
        textPaint: TextPaint,
        width: Int,
        start: Int = 0,
        end: Int = text.length,
        alignment: Layout.Alignment = Layout.Alignment.ALIGN_NORMAL,
        textDir: TextDirectionHeuristic = TextDirectionHeuristics.LTR,
        spacingMult: Float = 1f,
        spacingAdd: Float = 0f) : StaticLayout {

        return StaticLayout.Builder
            .obtain(text, start, end, textPaint, width)
            .setAlignment(alignment)
            .setTextDirection(textDir)
            .setLineSpacing(spacingAdd, spacingMult)
            .build()
    }

    private fun drawCircle(canvas: Canvas) {

        for(percent in percentageCircleList) {

            if (animationSweepAngle > percent.percentToStartAt + percent.percentOfCircle) {
                canvas.drawArc(
                    circleRect,
                    percent.percentToStartAt,
                    percent.percentOfCircle,
                    true,
                    percent.paint)
            } else if (animationSweepAngle > percent.percentToStartAt) {
                canvas.drawArc(
                    circleRect,
                    percent.percentToStartAt,
                    animationSweepAngle - percent.percentToStartAt,
                    true,
                    percent.paint)
            }

        }
    }

    private fun drawText(canvas: Canvas) {

        var textBuffY = textStartY

        textRowList.forEachIndexed { index, staticLayout ->
            if (index % 2 == 0) {
                staticLayout.draw(canvas, textStartX + marginSmallCircle + textCircleRadius, textBuffY)
                canvas.drawCircle(
                    textStartX + marginSmallCircle / 2,
                    textBuffY + staticLayout.height / 2 + textCircleRadius / 2,
                    textCircleRadius,
                    Paint().apply {
                        color = Color.parseColor(pieChartColors[(index / 2) % pieChartColors.size])
                    }
                )
                // Прибавим высоту и отступ к Y
                textBuffY += staticLayout.height + marginTextFirst
            } else {
                // Описание значения
                staticLayout.draw(canvas, textStartX, textBuffY)
                textBuffY += staticLayout.height + marginTextSecond
            }
        }

    }

    fun startAnimation() {
        val animator = ValueAnimator.ofInt(0, 360).apply {
            duration = 1000 // длительность 1.0 секунда
            interpolator = FastOutSlowInInterpolator()
            addUpdateListener { valueAnimator ->
                animationSweepAngle = valueAnimator.animatedValue as Int
                invalidate()
            }
        }
        animator.start()
    }

    private fun calculateCircleRadius(width: Int, height: Int) {

        val circleViewWidth = (width * CIRCLE_WIDTH_PERCENT)
        circleRadius = if (circleViewWidth > height) {
            (height.toFloat() - circlePadding) / 2
        } else {
            circleViewWidth.toFloat() / 2
        }

        with(circleRect) {
            left = circlePadding
            top = height / 2 - circleRadius
            right = circleRadius * 2 + circlePadding
            bottom = height / 2 + circleRadius
        }

        circleCenterX = (circleRadius * 2 + circlePadding + circlePadding) / 2
        circleCenterY = (height / 2 + circleRadius + (height / 2 - circleRadius)) / 2

        textAmountY = circleCenterY

        val sizeTextAmountNumber = getWidthOfAmountText(
            totalAmount.toString(),
            amountTextPaint
        )

        textAmountXNumber = circleCenterX -  sizeTextAmountNumber.width() / 2
        textAmountXDescription = circleCenterX - getWidthOfAmountText(textAmountStr, descriptionTextPaint).width() / 2
        textAmountYDescription = circleCenterY + sizeTextAmountNumber.height() + marginTextThird
    }

    private fun getWidthOfAmountText(text: String, textPaint: TextPaint): Rect {
        val bounds = Rect()
        textPaint.getTextBounds(text, 0, text.length, bounds)
        return bounds
    }

    private fun calculateViewHeight(heightMeasureSpec: Int, textWidth: Int): Int {
        val initSizeHeight = resolveDefaultSize(heightMeasureSpec, DEFAULT_VIEW_SIZE_HEIGHT)
        textHeight = (dataList.size * marginText + getTextViewHeight(textWidth)).toInt()

        val textHeightWithPadding = textHeight + paddingTop + paddingBottom
        return if (textHeightWithPadding > initSizeHeight) textHeightWithPadding else initSizeHeight
    }

    private fun resolveDefaultSize(spec: Int, defValue: Int): Int {
        return when(MeasureSpec.getMode(spec)) {
            MeasureSpec.UNSPECIFIED -> context.dpToPixels(defValue).toInt()
            else -> MeasureSpec.getSize(spec)
        }
    }

    private fun getTextViewHeight(maxWidth: Int): Int {
        var legendHeight = 0
        dataList.forEach {
            val textLayoutNumber = getMultilineText(
                text = it.amount.toInt().toString(),
                textPaint = numberTextPaint,
                width = maxWidth
            )
            val textLayoutDescription = getMultilineText(
                text = it.category,
                textPaint = descriptionTextPaint,
                width = maxWidth
            )
            textRowList.apply {
                add(textLayoutNumber)
                add(textLayoutDescription)
            }
            legendHeight += textLayoutNumber.height + textLayoutDescription.height
        }

        return legendHeight
    }

}