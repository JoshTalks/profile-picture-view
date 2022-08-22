package com.joshtalks.profilepictureview

import android.content.Context
import android.graphics.*
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
This class is a custom view to show user profile picture.
If image url is null or blank this class will show initial letter of user name.
 */

class ProfilePictureView(context: Context, attr: AttributeSet) : AppCompatImageView(context, attr) {

    companion object {
        private const val CORNER_RADIUS = 16.0f
        private const val TEXT_COLOR = Color.WHITE
        private val BACKGROUND_COLOR = Color.parseColor("#107BE5")
        private const val TEXT_SIZE = 16f
        const val ROUNDED = 0
        const val CIRCLE = 1
        const val NORMAL = 2
        const val SINGLE = 0
        const val DOUBLE = 1
    }


    var imageUrl: String? = null
        set(value) {
            field = value
            invalidate()
        }

    var shape = ROUNDED
        set(value) {
            field = value
            invalidate()
        }

    var cornerRadius = CORNER_RADIUS
        set(value) {
            field = value
            invalidate()
        }

    var userName: String? = null
        set(value) {
            field = value
            invalidate()
        }


    var textColor = TEXT_COLOR
        set(value) {
            field = value
            invalidate()
        }

    var bgColor = BACKGROUND_COLOR
        set(value) {
            field = value
            invalidate()
        }

    var textSize = TEXT_SIZE.toInt()
        set(value) {
            field = value
            invalidate()
        }

    var nameLetters = DOUBLE
        set(value) {
            field = value
            invalidate()
        }

    private val path = Path()

    private var rectF: RectF? = null

    private var size = 0

    init {
        setupAttributes(attr)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        rectF = RectF(0f, 0f, w.toFloat(), h.toFloat())
        resetPath()
    }

    private fun resetPath() {
        when(shape){
            ROUNDED -> createRoundedPath()
            CIRCLE -> createCirclePath()
            NORMAL -> {}
            else -> {}
        }
    }

    private fun createCirclePath(){
        path.reset()
        path.addCircle(size / 2f, size / 2f, size / 2f, Path.Direction.CW)
        path.close()
    }

    private fun createRoundedPath(){
        rectF?.let {
            path.reset()
            path.addRoundRect(it, cornerRadius, cornerRadius, Path.Direction.CW)
            path.close()
        }
    }

    override fun dispatchDraw(canvas: Canvas?) {
        var save: Int? = null
        if (shape != NORMAL){
            save = canvas?.save()
            canvas?.clipPath(path)
        }
        super.dispatchDraw(canvas)
        save?.let { canvas?.restoreToCount(it) }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        size = Math.min(measuredHeight, measuredWidth)
        setMeasuredDimension(measuredWidth, measuredHeight)
    }

    private fun setupAttributes(attrs: AttributeSet) {
        val typedArray =
            context.theme.obtainStyledAttributes(attrs, R.styleable.ProfilePictureView, 0, 0)

        imageUrl = typedArray.getString(R.styleable.ProfilePictureView_imageUrl)
        userName = typedArray.getString(R.styleable.ProfilePictureView_userName)
        shape = typedArray.getInt(R.styleable.ProfilePictureView_shape, ROUNDED)
        cornerRadius =
            typedArray.getDimension(R.styleable.ProfilePictureView_cornerRadius, CORNER_RADIUS)
        textColor = typedArray.getColor(R.styleable.ProfilePictureView_textColor, TEXT_COLOR)
        textSize =
            typedArray.getDimension(R.styleable.ProfilePictureView_textSize, TEXT_SIZE).toInt()
        nameLetters = typedArray.getInt(R.styleable.ProfilePictureView_nameLetters, DOUBLE)
        background?.let {
            if (it is ColorDrawable) {
                bgColor = it.color
            }
        }

        typedArray.recycle()
    }

    override fun onDraw(canvas: Canvas?) {
        var save: Int? = null
        if (shape != NORMAL){
             save = canvas?.save()
            canvas?.clipPath(path)
        }
        super.onDraw(canvas)
        save?.let {
            canvas?.restoreToCount(it)
        }
        setImageOrName()
    }


    fun setUrlAndName(url: String? = null, name: String? = null) {
        this.imageUrl = url
        this.userName = name
    }

    private fun setImageOrName() {
        if (!imageUrl.isNullOrEmpty()) {
            setImageFromUrl()
        } else if (!userName.isNullOrEmpty()) {
            setUserNameInitial()
        }
    }

    private fun setImageFromUrl() {
        try {
            Glide.with(context)
                .load(imageUrl)
                .into(this)
            setBackgroundColor(Color.TRANSPARENT)
        } catch (e: Exception){
            e.printStackTrace()
            setUserNameInitial()
        }
    }

    private fun setUserNameInitial() {
       try {
           userName?.let {
//               val font = Typeface.createFromAsset(
//                   context.assets,
//                   "fonts/OpenSans-SemiBold.ttf"
//               )

               val drawable: TextDrawable = TextDrawable.builder()
                   .beginConfig()
                   .textColor(textColor)
//                   .useFont(font)
                   .fontSize(Utils.dpToPx(textSize))
                   .toUpperCase()
                   .endConfig()
                   .getTextDrawableShape()
               this.background = drawable
               this.setImageDrawable(drawable)
           }
       } catch (e: Exception) {
           e.printStackTrace()
       }
    }

    private fun getUserNameInShort(
        name: String
    ): String {
        return try {
            val nameSplit = name.split(" ")
            if (nameSplit.size > 1) {
                if (nameLetters == SINGLE){
                    name.substring(0, 1)
                } else {
                    nameSplit[0][0].plus(nameSplit[1][0].toString())
                }
            } else {
                name.substring(0, 1)
            }
        } catch (e: IndexOutOfBoundsException) {
            name.substring(0, name.length)
        }
    }

    private fun TextDrawable.IShapeBuilder.getTextDrawableShape(): TextDrawable{
         return when(shape){
            ROUNDED -> buildRoundRect(
                getUserNameInShort(userName!!),
                bgColor,
                cornerRadius.toInt()
            )
            CIRCLE -> buildRound(
                getUserNameInShort(userName!!),
                bgColor,
            )
            NORMAL -> buildRect(
                getUserNameInShort(userName!!),
                bgColor,
            )
            else -> buildRect(
                getUserNameInShort(userName!!),
                bgColor,
            )
        }
    }

}

@BindingAdapter("app:dynamicImageUrl")
fun ProfilePictureView.setDpUrl(dpUrl: String?) {
    this.imageUrl = dpUrl
}

@BindingAdapter("app:dynamicUserName")
fun ProfilePictureView.setDynamicUsername(name: String?) {
    this.userName = name
}

