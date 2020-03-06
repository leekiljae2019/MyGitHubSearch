package com.afterwork.mygithubsearch.common

import kotlin.math.ln
import kotlin.math.pow

object Util{
    fun prettyNumber(prefix: String, count: Int): String {
        if (count < 1000) return "${prefix}: $count"
        val exp = (ln(count.toDouble()) / ln(1000.0)).toInt()
        return String.format("%s: %.1f%c", prefix, count / 1000.0.pow(exp.toDouble()), "kMGTPE"[exp - 1])
    }
}