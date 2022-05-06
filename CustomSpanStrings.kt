import android.text.SpannableStringBuilder
import android.widget.TextView
import androidx.annotation.ColorInt
import android.text.style.ForegroundColorSpan
import android.text.Spanned
import android.text.style.UnderlineSpan
import android.text.style.StyleSpan
import android.graphics.Typeface
import android.text.style.ClickableSpan
import android.text.method.LinkMovementMethod
import android.view.View

class CustomSpanStrings private constructor() {
    private var completeString: String = ""
    private var spannableStringBuilder: SpannableStringBuilder? = null
    private var textView: TextView? = null

    fun applyColor(@ColorInt color: Int, vararg texts: String): CustomSpanStrings {
        for (text in texts) {
            val startIndex = completeString.indexOf(text)
            if (startIndex != -1) {
                spannableStringBuilder?.setSpan(
                    ForegroundColorSpan(color), startIndex,
                    startIndex + text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        }
        return this
    }

    fun applyUnderline(vararg texts: String): CustomSpanStrings {
        for (text in texts) {
            val startIndex = completeString.indexOf(text)
            if (startIndex != -1) {
                spannableStringBuilder?.setSpan(
                    object : UnderlineSpan() {}, startIndex,
                    startIndex + text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        }
        return this
    }

    fun applyBold(vararg texts: String): CustomSpanStrings {
        for (text in texts) {
            val startIndex = completeString.indexOf(text)
            if (startIndex != -1) {
                spannableStringBuilder?.setSpan(
                    StyleSpan(Typeface.BOLD), startIndex,
                    startIndex + text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        }
        return this
    }

    fun addClickListener(
        text: String,
        clickListener: OnCustomSpanClickListener
    ): CustomSpanStrings {
        val startIndex = completeString.indexOf(text)
        if (startIndex != -1) {
            autoDetectLinks()
            spannableStringBuilder?.setSpan(object : ClickableSpan() {
                override fun onClick(view: View) {
                    clickListener.onSpanClicked(view)
                }
            }, startIndex, startIndex + text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        return this
    }

    fun autoDetectLinks(): CustomSpanStrings {
        textView?.movementMethod = LinkMovementMethod.getInstance()
        return this
    }

    fun commit() {
        textView?.setText(
            spannableStringBuilder,
            TextView.BufferType.SPANNABLE
        )
    }

    fun interface OnCustomSpanClickListener {
        fun onSpanClicked(view: View?)
    }

    companion object {
        @JvmStatic
        fun withTextView(textView: TextView): CustomSpanStrings {
            val spanStrings = CustomSpanStrings()
            spanStrings.textView = textView
            spanStrings.completeString = textView.text.toString()
            spanStrings.spannableStringBuilder = SpannableStringBuilder(spanStrings.completeString)
            return spanStrings
        }
    }
}
