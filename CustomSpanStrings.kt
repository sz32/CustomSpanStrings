import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.view.View
import android.widget.TextView

interface ClickAbleCustomSpanListener {
    fun onClickSpan()
}

class CustomSpanStrings {

    private var termsAndCond: TextView? = null
    private var colorList: IntArray = intArrayOf()
    private var stringsList: Array<out String> = arrayOf()
    private var spannableStringBuilder: SpannableStringBuilder? = null
    private var completeString: String = ""
    private var clickAbleCustomSpanListener: ClickAbleCustomSpanListener? = null


    fun setCompleteString(completeString: String): CustomSpanStrings {
        this.completeString = completeString
        spannableStringBuilder = SpannableStringBuilder(completeString)
        return this
    }

    fun setStrings(vararg strings: String): CustomSpanStrings {
        this.stringsList = strings
        return this
    }

    fun setClickableSpan(clickAbleCustomSpanListener: ClickAbleCustomSpanListener): CustomSpanStrings {
        this.clickAbleCustomSpanListener = clickAbleCustomSpanListener
        stringsList.forEach { it ->
            spannableStringBuilder?.setSpan(
                object : ClickableSpan() {
                    override fun onClick(widget: View) {
                        clickAbleCustomSpanListener.onClickSpan()
                    }
                },
                completeString.indexOf(it), completeString.indexOf(it) + it.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }

        return this
    }

    fun setColorSpan(vararg colorList: Int): CustomSpanStrings {
        this.colorList = colorList
        stringsList.forEachIndexed { index, it ->
            spannableStringBuilder?.setSpan(
                object : ForegroundColorSpan(colorList[index]) {},
                completeString.indexOf(it),
                completeString.indexOf(it) + it.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        return this
    }

    fun setUnderlineSpan(): CustomSpanStrings {
        stringsList.forEach {
            spannableStringBuilder?.setSpan(
                object : UnderlineSpan() {},
                completeString.indexOf(it),
                completeString.indexOf(it) + it.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )

        }
        return this
    }

    fun setBoldSpan(): CustomSpanStrings {
        stringsList.forEach {
            spannableStringBuilder?.setSpan(
                StyleSpan(Typeface.BOLD),
                completeString.indexOf(it),
                completeString.indexOf(it) + it.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        return this
    }

    fun setTextView(termsAndCond: TextView): CustomSpanStrings {
        this.termsAndCond = termsAndCond
        return this
    }

    fun build(): SpannableStringBuilder? {
        this.termsAndCond?.setText(spannableStringBuilder, TextView.BufferType.SPANNABLE)
        this.termsAndCond?.movementMethod = LinkMovementMethod.getInstance()
        return spannableStringBuilder
    }


}
