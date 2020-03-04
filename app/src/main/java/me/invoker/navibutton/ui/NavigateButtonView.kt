package me.invoker.navibutton.ui

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.*
import android.widget.FrameLayout
import android.widget.Toast
import androidx.core.content.getSystemService
import me.invoker.navibutton.Constant
import me.invoker.navibutton.R

class NavigateButtonView : FrameLayout, View.OnTouchListener {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)

    init {
        LayoutInflater.from(context).inflate(R.layout.navigate_button, this)
        setOnTouchListener(this)
    }

    private var distance: Pair<Float, Float>? = null

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        val param = layoutParams as WindowManager.LayoutParams

        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                distance = (param.x - event.rawX) to (param.y - event.rawY)
            }
            MotionEvent.ACTION_MOVE -> {
                param.x = (event.rawX + distance!!.first).toInt()
                param.y = (event.rawY + distance!!.second).toInt()

                context.getSystemService<WindowManager>()?.updateViewLayout(this, param)
                invalidate()
            }
        }
        return gestureDetector.onTouchEvent(event)
    }

    private val gestureDetector = GestureDetector(context, object :
        GestureDetector.SimpleOnGestureListener() {
        
        override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
            Toast.makeText(context, context.getString(R.string.back), Toast.LENGTH_SHORT).show()
            context.sendBroadcast(Intent(Constant.ACTION_MOAN_BACK))
            return true
        }

        override fun onDoubleTap(e: MotionEvent?): Boolean {
            Toast.makeText(context, context.getString(R.string.back_to_home), Toast.LENGTH_SHORT).show()

            val intent = Intent(Intent.ACTION_MAIN)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.addCategory(Intent.CATEGORY_HOME)

            context.startActivity(intent)

            return true
        }
    })
}