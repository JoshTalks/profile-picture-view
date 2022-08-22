package com.joshtalks.profilepictureview

import android.content.res.Resources

object Utils {
    fun dpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }
}