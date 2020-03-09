package com.afterwork.mygithubsearch.common

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.facebook.drawee.view.SimpleDraweeView
import kotlin.math.ln
import kotlin.math.pow

@BindingAdapter("avatarImage")
fun avatarImage(view: SimpleDraweeView, url: String){
    view.setImageURI(url)
}

@BindingAdapter(value = ["defaultBranch", "starCount", "forkCount", "openIssueCount"], requireAll = false)
fun prefixtText(view: TextView, branch: String?, stars: Int?, forks: Int?, issues: Int?){

    if(branch != null){
        view.text = "${view.tag.toString()}: ${branch}"
    }else if(stars != null){
        view.text = prettyNumber(view.tag.toString(), stars)
    } else if(forks != null){
        view.text = prettyNumber(view.tag.toString(), forks)
    } else if(issues != null){
        view.text = prettyNumber(view.tag.toString(), issues)
    }
}

@BindingAdapter(value = ["highlightText", "keyword"], requireAll = true)
fun highlightText(view: TextView, strs: String?, keyword: String?){
    if(strs.isNullOrEmpty() == true || keyword.isNullOrEmpty() == true) return

    val startIndex = strs.toLowerCase().indexOf(keyword.toLowerCase())
    if(startIndex >= 0){
        var ssb = SpannableStringBuilder(strs)
        ssb.setSpan(ForegroundColorSpan(Color.MAGENTA), startIndex, (startIndex+keyword.length), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        view.text = ssb
    }else{
        if(keyword.last().toLowerCase() == 's'){
            highlightText(view, strs, keyword.substring(0, keyword.length-1))
            return
        }
        view.text = strs
    }
}

fun prettyNumber(prefix: String, count: Int): String {
    if (count < 1000) return "${prefix}: $count"
    val exp = (ln(count.toDouble()) / ln(1000.0)).toInt()
    return String.format("%s: %.1f%c", prefix, count / 1000.0.pow(exp.toDouble()), "kMGTPE"[exp - 1])
}